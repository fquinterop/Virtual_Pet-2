package Proyecto.Virtual_Pet.service.admin;

import Proyecto.Virtual_Pet.model.entity.Producto;
import Proyecto.Virtual_Pet.repository.ProductoRepository;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.*;

@Service
public class ImportacionService {

    @Autowired
    private ProductoRepository productoRepository;

    public Map<String, Object> importarProductos(MultipartFile archivo) throws Exception {
        int importados = 0;
        int errores = 0;
        List<String> mensajesError = new ArrayList<>();

        try (InputStream is = archivo.getInputStream();
             Workbook workbook = new XSSFWorkbook(is)) {

            Sheet sheet = workbook.getSheetAt(0);

            for (int i = 1; i <= sheet.getLastRowNum(); i++) {
                Row row = sheet.getRow(i);
                if (row == null) continue;

                try {
                    String id = getCellValue(row, 0);
                    if (id == null || id.isBlank()) continue;

                    Producto p = new Producto();
                    p.setReferencia(id);
                    p.setNombre(getCellValue(row, 1));
                    p.setMarca(getCellValue(row, 2));
                    p.setCategoria(getCellValue(row, 3));
                    p.setEspecie(getCellValue(row, 5));
                    p.setPresentaciones(getCellValue(row, 6));
                    p.setDescripcion(getCellValue(row, 8));

                    String precioStr = getCellValue(row, 9);
                    if (precioStr != null && !precioStr.isBlank()) {
                        p.setPrecio(new BigDecimal(precioStr.replace(",", "").replace("$", "").trim()));
                    }

                    String stockStr = getCellValue(row, 10);
                    if (stockStr != null && !stockStr.isBlank()) {
                        p.setStock((int) Double.parseDouble(stockStr));
                    }

                    p.setDisponibilidad(getCellValue(row, 11));
                    p.setActivo(true);

                    productoRepository.save(p);
                    importados++;

                } catch (Exception e) {
                    errores++;
                    mensajesError.add("Fila " + (i + 1) + ": " + e.getMessage());
                }
            }
        }

        Map<String, Object> resultado = new HashMap<>();
        resultado.put("importados", importados);
        resultado.put("errores", errores);
        resultado.put("mensajes", mensajesError);
        return resultado;
    }

    private String getCellValue(Row row, int col) {
        Cell cell = row.getCell(col);
        if (cell == null) return null;
        return switch (cell.getCellType()) {
            case STRING  -> cell.getStringCellValue().trim();
            case NUMERIC -> String.valueOf((long) cell.getNumericCellValue());
            case BOOLEAN -> String.valueOf(cell.getBooleanCellValue());
            default      -> null;
        };
    }
}