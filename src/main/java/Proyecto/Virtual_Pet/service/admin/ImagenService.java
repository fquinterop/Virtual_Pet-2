package Proyecto.Virtual_Pet.service.admin;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import Proyecto.Virtual_Pet.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.util.*;

@Service
public class ImagenService {

    @Autowired
    private Cloudinary cloudinary;

    @Autowired
    private ProductoRepository productoRepository;

    public Map<String, Object> subirImagenes(MultipartFile[] archivos) throws Exception {
        int subidas = 0;
        int errores = 0;
        int noEncontrados = 0;
        List<String> mensajes = new ArrayList<>();

        for (MultipartFile archivo : archivos) {
            String nombreArchivo = archivo.getOriginalFilename();
            if (nombreArchivo == null) continue;

            // Obtener referencia del nombre del archivo (sin extensión)
            String referencia = nombreArchivo.contains(".")
                    ? nombreArchivo.substring(0, nombreArchivo.lastIndexOf("."))
                    : nombreArchivo;

            try {
                // Subir a Cloudinary
                Map resultado = cloudinary.uploader().upload(
                        archivo.getBytes(),
                        ObjectUtils.asMap(
                                "folder", "virtualpet/productos",
                                "public_id", referencia,
                                "overwrite", true
                        )
                );

                String url = (String) resultado.get("secure_url");

                // Buscar producto por referencia y actualizar imagenUrl
                var productos = productoRepository.findByReferencia(referencia);
                if (productos.isEmpty()) {
                    noEncontrados++;
                    mensajes.add("Sin producto para: " + referencia);
                } else {
                    productos.forEach(p -> {
                        p.setImagenUrl(url);
                        productoRepository.save(p);
                    });
                    subidas++;
                }

            } catch (Exception e) {
                errores++;
                mensajes.add("Error con " + nombreArchivo + ": " + e.getMessage());
            }
        }

        Map<String, Object> resultado = new HashMap<>();
        resultado.put("subidas", subidas);
        resultado.put("errores", errores);
        resultado.put("noEncontrados", noEncontrados);
        resultado.put("mensajes", mensajes);
        return resultado;
    }
}