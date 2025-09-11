package com.TGarciaProgramacionNCapas25.Proyect.RestContoller;

import com.TGarciaProgramacionNCapas25.Proyect.DAO.CargaMasivaService;
import com.TGarciaProgramacionNCapas25.Proyect.JPA.Result;
import com.TGarciaProgramacionNCapas25.Proyect.JPA.StatusArchivo;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/usuario")
public class CargaMasivaController {

    private final Path uploadDir = Paths.get("src/main/resources/archivos");
    private final Path logFile = uploadDir.resolve("LogCargaMasiva.txt");
    private final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    @PostMapping("/cargamasiva")
    public ResponseEntity<Result> cargamasiva(@RequestParam("file") MultipartFile file) {
        Result result = new Result();

        try {
            //Validar archivo 
            if (file == null || file.isEmpty()) {
                result.correct = false;
                return ResponseEntity.badRequest().body(result);

            }
            Files.createDirectories(uploadDir);

            //Ruta cifrada
            String rutaCifrada = computeSHA1(file.getInputStream());

            //Guardar Archivo
            String savedFilename = System.currentTimeMillis() + "_" + file.getOriginalFilename();
            Path savedPath = uploadDir.resolve(savedFilename);
            try (InputStream in = file.getInputStream()) {
                Files.copy(in, savedPath, StandardCopyOption.REPLACE_EXISTING);
            }
            //Validacion del contenido
            List<String> errores = new ArrayList<>();
            List<String> lineas = Files.readAllLines(savedPath, StandardCharsets.UTF_8);
            int ln = 0;
            for (String linea : lineas) {
                ln++;
                if (linea == null || linea.trim().isEmpty()) {
                    errores.add("Línea " + Fln + ": está vacía");
                    continue;
                }

                String[] campos = linea.split("\\|", -1); // -1 conserva vacíos
                if (campos.length != 19) {
                    errores.add("Línea " + ln + ": se esperaban 19 campos, encontrados " + campos.length);
                    continue;
                }

                // --- Validaciones por campo ---
                // 1,2,3 texto (no vacío)
                for (int i = 0; i < 3; i++) {
                    if (campos[i].isBlank()) {
                        errores.add("Línea " + ln + ": campo " + (i + 1) + " no puede estar vacío");
                    }
                }

                // 4 fecha
                try {
                    LocalDate.parse(campos[3], DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                } catch (Exception e) {
                    errores.add("Línea " + ln + ": campo 4 debe ser fecha (yyyy-MM-dd)");
                }

                // 5 y 6: numérico de 10 dígitos
                if (!campos[4].matches("\\d{10}")) {
                    errores.add("Línea " + ln + ": campo 5 debe ser numérico de 10 dígitos");
                }
                if (!campos[5].matches("\\d{10}")) {
                    errores.add("Línea " + ln + ": campo 6 debe ser numérico de 10 dígitos");
                }

                // 7 CURP (regex simplificado de 18 caracteres alfanuméricos en mayúsculas)
                if (!campos[6].matches("[A-Z0-9]{18}")) {
                    errores.add("Línea " + ln + ": campo 7 debe ser CURP válido");
                }

                // 8 texto (no vacío)
                if (campos[7].isBlank()) {
                    errores.add("Línea " + ln + ": campo 8 no puede estar vacío");
                }

                // 9 correo electrónico
                if (!campos[8].matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$")) {
                    errores.add("Línea " + ln + ": campo 9 debe ser correo electrónico válido");
                }

                // 10 password (mínimo 8 caracteres)
                if (campos[9].length() < 8) {
                    errores.add("Línea " + ln + ": campo 10 debe tener al menos 8 caracteres");
                }

                // 11 solo M o H
                if (!campos[10].matches("[MH]")) {
                    errores.add("Línea " + ln + ": campo 11 debe ser 'M' o 'H'");
                }

                // 12 imagen (.jpg o .png)
                if (!campos[11].matches("(?i).+\\.(jpg|png)$")) {
                    errores.add("Línea " + ln + ": campo 12 debe ser imagen con extensión .jpg o .png");
                }

                // 13,14,15 número entero
                for (int i = 12; i <= 14; i++) {
                    if (!campos[i].matches("\\d+")) {
                        errores.add("Línea " + ln + ": campo " + (i + 1) + " debe ser un número entero");
                    }
                }

                // 16 texto (no vacío)
                if (campos[15].isBlank()) {
                    errores.add("Línea " + ln + ": campo 16 no puede estar vacío");
                }

                // 17,18,19 número entero
                for (int i = 16; i <= 18; i++) {
                    if (!campos[i].matches("\\d+")) {
                        errores.add("Línea " + ln + ": campo " + (i + 1) + " debe ser un número entero");
                    }
                }
                if (!errores.isEmpty()) {
                // 5) status = ERROR (0) -> log y devolver lista de errores
                appendLog(rutaCifrada, savedFilename, StatusArchivo.ERROR, String.join(" ; ", errores));
                result.correct=false;
                return ResponseEntity.badRequest().body(result);
            }
            }

        }

    }
}
