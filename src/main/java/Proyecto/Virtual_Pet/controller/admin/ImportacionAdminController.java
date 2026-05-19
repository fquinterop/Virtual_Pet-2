package Proyecto.Virtual_Pet.controller.admin;

import Proyecto.Virtual_Pet.service.admin.ImportacionService;
import Proyecto.Virtual_Pet.service.admin.ImagenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.util.Map;

@RestController
@RequestMapping("/api/admin/importar")
public class ImportacionAdminController {

    @Autowired
    private ImportacionService service;

    @Autowired
    private ImagenService imagenService;

    @PostMapping("/productos")
    public ResponseEntity<Map<String, Object>> importarProductos(
            @RequestParam("archivo") MultipartFile archivo) {
        try {
            if (archivo.isEmpty())
                return ResponseEntity.badRequest().body(Map.of("mensaje", "Archivo vacío"));
            return ResponseEntity.ok(service.importarProductos(archivo));
        } catch (Exception e) {
            return ResponseEntity.internalServerError()
                    .body(Map.of("mensaje", "Error al importar: " + e.getMessage()));
        }
    }

    @PostMapping("/imagenes")
    public ResponseEntity<Map<String, Object>> importarImagenes(
            @RequestParam("archivos") MultipartFile[] archivos) {
        try {
            if (archivos.length == 0)
                return ResponseEntity.badRequest().body(Map.of("mensaje", "Sin archivos"));
            return ResponseEntity.ok(imagenService.subirImagenes(archivos));
        } catch (Exception e) {
            return ResponseEntity.internalServerError()
                    .body(Map.of("mensaje", "Error al subir imágenes: " + e.getMessage()));
        }
    }
}