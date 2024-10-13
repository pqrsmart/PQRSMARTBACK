package Proyecto.PQRSMART.Controller;


import Proyecto.PQRSMART.Domain.Dto.RequestDTO;
import Proyecto.PQRSMART.Domain.Service.EmailServiceImpl;
import Proyecto.PQRSMART.Domain.Service.RequestServices;
import Proyecto.PQRSMART.Domain.Service.RequestStateService;
import Proyecto.PQRSMART.Persistence.Entity.Request;
import Proyecto.PQRSMART.Persistence.Entity.RequestState;
import Proyecto.PQRSMART.Persistence.Entity.User;
import Proyecto.PQRSMART.Persistence.Repository.RequestRepository;
import Proyecto.PQRSMART.Persistence.Repository.UsuarioRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/request")
public class RequestController {
    @Autowired
    private RequestServices requestServices;
    @Autowired
    private RequestRepository requestRepository;
    @Autowired
    private UsuarioRepository userRepository;
    @Autowired
    private RequestStateService requestStateService;

    private final EmailServiceImpl emailService;

    //private final Path fileStorageLocation = Paths.get("files").toAbsolutePath().normalize();
    // Ruta para guardar archivos
    @Value("${file.upload-dir:/var/data/uploads}")
    private String uploadDir;

    @PostMapping("/save")
    public ResponseEntity<String> guardarSolicitud(@RequestPart("request") RequestDTO request, @RequestPart(value = "archivo", required = false) MultipartFile archivo) {


        // Obtenemos el usuario autenticado
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        // Buscamos el usuario en la base de datos
        User user = userRepository.findByUser(userDetails.getUsername());
        System.out.println(request);

        if (user == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Usuario no encontrado");
        }
        String archivoGuardado = null;
        if (archivo != null && !archivo.isEmpty()) {
            try {

                //Si la Carpeta no Existe se crea
                //Files.createDirectories(fileStorageLocation);
                Files.createDirectories(Paths.get(uploadDir));

                // Guardar el archivo
                String fileName = archivo.getOriginalFilename();
                System.out.println(fileName);

                // Generar un nombre único para el archivo (ejemplo con timestamp)
                String uniqueFileName = System.currentTimeMillis() + "_" + fileName;
                System.out.println(uniqueFileName);

                Path targetLocation = Paths.get(uploadDir).resolve(uniqueFileName);
                System.out.println(targetLocation);

                Files.copy(archivo.getInputStream(), targetLocation);

                // Establecer la URL del archivo
                request.setArchivo(targetLocation.toString());

                archivoGuardado = targetLocation.toString();  // Guardamos la ruta del archivo para el adjunto

            } catch (IOException e) {
                System.out.println(e);
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al procesar el archivo " + e.getMessage());
            }
        }
        // Creamos la solicitud
        request.setUser(user);

        // Guardar solicitud usando el servicio
        RequestDTO savedRequest;
        RequestDTO searchRequest;
        try {
            savedRequest = requestServices.saves(request);

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al guardar la solicitud");
        }

        try{

                // Generar PDF con iText

                ByteArrayOutputStream pdfOutputStream = new ByteArrayOutputStream();

                // 1. Crear el documento PDF
                PdfWriter writer = new PdfWriter(pdfOutputStream);
                PdfDocument pdfDoc = new PdfDocument(writer);
                Document document = new Document(pdfDoc);

                // 2. Añadir contenido al PDF
                document.add(new Paragraph("Fecha: " + request.getDate()));
                document.add(new Paragraph(" "));
                document.add(new Paragraph(" "));
                document.add(new Paragraph("Usuario: " + user.getName()));
                document.add(new Paragraph(" "));
                document.add(new Paragraph("Tipo de Solicitud: " + savedRequest.getRequestType().getNameRequestType()));
                document.add(new Paragraph(" "));
                document.add(new Paragraph("Categoría: " + savedRequest.getCategory().getNameCategory()));
                document.add(new Paragraph(" "));
                document.add(new Paragraph("Detalle de la Solicitud: " ));
                document.add(new Paragraph( request.getDescription()));
                document.add(new Paragraph(" "));
                document.add(new Paragraph("Estado Actual de la Solicitud: " + savedRequest.getRequestState().getNameRequestState()));

                document.close();

                // Enviar el PDF por correo
                emailService.sendEmailWithPdf(user.getEmail(), "Detalle de Solicitud", "Adjunto encontrarás el PDF con los detalles de tu solicitud.", pdfOutputStream.toByteArray(), archivoGuardado);

            } catch (Exception e) {
                System.out.println(e);
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al procesar el archivo " + e.getMessage());
            }

        // Convertir la solicitud guardada a JSON y devolverla
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        String requestJson;
        try {
            requestJson = mapper.writeValueAsString(savedRequest);
            System.out.println(requestJson);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al convertir a JSON");
        }


System.out.println(requestJson);
        // Devolver respuesta HTTP con estado 201 (creado)
        return ResponseEntity.status(HttpStatus.CREATED).body(requestJson);
    }

    @GetMapping("/get")
    public List<RequestDTO> get() {
        return requestServices.getAll();
    }

    @PutMapping("/cancel/{id}")
    public ResponseEntity<RequestDTO> cancelarSolicitud(@PathVariable Long id) {
        Optional<RequestDTO> optionalRequest = requestServices.findById(id);
        if (optionalRequest.isPresent()) {
            RequestDTO request = optionalRequest.get();
            // Asignar el estado "CANCELADA" de la entidad RequestState

            Optional<RequestState> canceladoState = requestStateService.findByName("Cancelado");
            request.setRequestState(canceladoState.get());
            requestServices.save(request);
            return ResponseEntity.ok(request);
        }
        return ResponseEntity.ok().build();
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody RequestDTO requestDTO) {
        Optional<RequestDTO> requestDTOOptional = requestServices.findById(id);
        if (requestDTOOptional.isPresent()) {
            RequestDTO existingRequest = requestDTOOptional.get();
            existingRequest.setRequestState(requestDTO.getRequestState());
            existingRequest.setAnswer(requestDTO.getAnswer());
            // Actualizar otros campos si es necesario
            RequestDTO updatedRequestDTO = requestServices.save(existingRequest); // Guardar los cambios en la solicitud existente
            return ResponseEntity.ok(updatedRequestDTO);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/download/{filename}")
    public ResponseEntity<Resource> downloadFile(@PathVariable String filename) {
        try {
            //Path filePath = fileStorageLocation.resolve(filename).normalize();
            Path filePath = Paths.get(uploadDir).resolve(filename);
            Resource resource = new UrlResource(filePath.toUri());

            if (resource.exists()) {
                return ResponseEntity.ok()
                        .contentType(MediaType.valueOf(Files.probeContentType(filePath)))
                        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                        .body(resource);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}