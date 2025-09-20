package com.TGarciaProgramacionNCapas25.Proyect.RestContoller;

import com.TGarciaProgramacionNCapas25.Proyect.DAO.UsuarioJPADAOImplementation;
import com.TGarciaProgramacionNCapas25.Proyect.JPA.Colonia;
import com.TGarciaProgramacionNCapas25.Proyect.JPA.Direccion;
import com.TGarciaProgramacionNCapas25.Proyect.JPA.ErrorCM;
import com.TGarciaProgramacionNCapas25.Proyect.JPA.Result;
import com.TGarciaProgramacionNCapas25.Proyect.JPA.Rol;
import com.TGarciaProgramacionNCapas25.Proyect.JPA.Usuario;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

import java.io.PrintWriter;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;

import java.nio.file.Paths;
import java.security.MessageDigest;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;

import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DataFormatter;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@Tag(name = "Controller de Usuario", description = "Controla los metodos del Usuario")
@RestController
@RequestMapping("usuarioapi")
public class UsuarioRestController {

    @Autowired
    private UsuarioJPADAOImplementation usuarioJPADAOImplementation;

    //MOSTRAR TODOS
    @GetMapping
    @Operation(description = "Metodo para retornar a todos los usuarios")
    @ApiResponse(responseCode = "200", description = "Usuarios encontrados")
    @ApiResponse(responseCode = "500", description = "proceso no exitoso")
    public ResponseEntity GetAll() {

        Result result = new Result();

        try {
            result = usuarioJPADAOImplementation.GetAll();
            result.correct = true;
            return ResponseEntity.status(200).body(result);

        } catch (Exception ex) {
            result = new Result();
            result.ex = ex;
            result.errorMessage = ex.getLocalizedMessage();
            result.correct = false;
            return ResponseEntity.status(500).body(result);

        }

    }

    //AGREGAR USUARIO
    @PostMapping
    @Operation(description = "Metodo para agregar a un usuario completo")
    @ApiResponse(responseCode = "200", description = "Usuario agregado ")
    @ApiResponse(responseCode = "500", description = "Error al agregar usuario")
    public ResponseEntity Add(@RequestBody Usuario usuario) {

        Result result = new Result();
        try {
            usuario.getIdUsuario();
            result = usuarioJPADAOImplementation.Add(usuario);
            return ResponseEntity.status(201).body(result);

        } catch (Exception ex) {
            result.ex = ex;
            result.correct = false;
            result.errorMessage = ex.getLocalizedMessage();
            return ResponseEntity.status(500).body(result);
        }

    }

    //MODIFICAR USUARIO
    @PutMapping("/{IdUsuario}")
    @Operation(description = "Metodo para modificar al usuario")
    @ApiResponse(responseCode = "200", description = "Usuario modificado exitosamente")
    @ApiResponse(responseCode = "500", description = "Proceso no exitoso")
    public ResponseEntity<Result> Update(@PathVariable int IdUsuario, @RequestBody Usuario usuario) {

        usuario.setIdUsuario(IdUsuario);
        Result result = usuarioJPADAOImplementation.Update(usuario);

        return ResponseEntity.status(result.Status).body(result);

    }

    //ELIMINAR USUARIO
    @DeleteMapping("/{IdUsuario}")
    @Operation(description = "Metodo para eliminar al usuario del index")
    @ApiResponse(responseCode = "200", description = "Usuario Eliminado")
    @ApiResponse(responseCode = "404", description = "Usuario NO Eliminado")
    public ResponseEntity<Result> Delete(@PathVariable int IdUsuario) {
        Result result = new Result();
        try {
            result = usuarioJPADAOImplementation.Delete(IdUsuario);
            result.correct = true;
            return ResponseEntity.status(200).body(result);

        } catch (Exception ex) {
            result.ex = ex;
            result.correct = false;
            result.errorMessage = ex.getLocalizedMessage();
            return ResponseEntity.status(500).body(result);
        }
    }

    //USUARIO BUSQUEDA POR ID
    @GetMapping("/{IdUsuario}")
    @Operation(description = "Metodo para obtener la informacion de un uduario en especial")
    @ApiResponse(responseCode = "200", description = "Usuario encontrado")
    @ApiResponse(responseCode = "500", description = "Usuario no encontrado")
    public ResponseEntity<Result> GetById(@PathVariable int IdUsuario) {
        Result result = new Result();

        try {
            result = usuarioJPADAOImplementation.GetById(IdUsuario);
            result.correct = true;
            return ResponseEntity.status(200).body(result);
        } catch (Exception ex) {
            result.correct = false;
            result.errorMessage = ex.getLocalizedMessage();
            result.ex = ex;
            return ResponseEntity.status(500).body(result);
        }
    }

    @Operation(summary = "Eliminar usuario lógicamente", description = "Cambia el estatus del usuario en lugar de borrarlo")
    @PatchMapping("/estatus/{IdUsuario}")
    public ResponseEntity BajaLogica(
            @Parameter(description = "ID del usuario a desactivar")
            @PathVariable int IdUsuario) {
        Result result = usuarioJPADAOImplementation.BajaLogica(IdUsuario);
        return ResponseEntity.status(result.Status).body(result);
    }

    @PostMapping("/cargamasiva")
    public ResponseEntity<Result> CargaMasiva(@RequestParam("file") MultipartFile file) {
        Result result = new Result();
        List<Usuario> usuarios = new ArrayList<>();
        List<ErrorCM> errores = new ArrayList<>();

        //Se valida extencion
        String extension = file.getOriginalFilename().split("\\.")[1];
        boolean valid = extension.equalsIgnoreCase("txt") || extension.equalsIgnoreCase("xlsx");

        if (!valid) {
            errores.add(new ErrorCM(1, "", "Tipo de Archivo invalido"));
            result.correct = false;
            result.errorMessage = "Solo se acepta TXT y XLSX";
            return ResponseEntity.status(400).body(result);
        }
        String uploadDir = System.getProperty("user.dir") + "/src/main/resources/files/files";
        File directory = new File(uploadDir);
        if (!directory.exists()) {
            directory.mkdirs();
        }
        try {
            //se genera el SHA1 y guarda el archivo
            String upDate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd HHmmss"));
            File destino = new File(uploadDir + upDate + "_" + file.getOriginalFilename());

            //se usa la ruta completa como un String
            String rutaCompleta = destino.getAbsolutePath();

            //SHA1 de la ruta
            MessageDigest md = MessageDigest.getInstance("SHA-1");
            byte[] messageDigest = md.digest(rutaCompleta.getBytes(StandardCharsets.UTF_8));
            BigInteger no = new BigInteger(1, messageDigest);

            //se convierte a string hexadecimal de 40 caracteres
            String pathHash = no.toString(16);
            while (pathHash.length() < 40) {
                pathHash = "0" + pathHash;
            }

            //creacion de archivo para el Log
            String logFile = System.getProperty("user.dir") + "/src/main/resources/files/logs/LogCargaMasiva.txt";
            File logDir = new File(logFile).getParentFile();
            if (!logDir.exists()) {
                logDir.mkdirs();
            }

//            String LogCargaMasiva = uploadDir + "LogCargaMasica.txt";
            if (destino.exists()) {
                result.correct = false;
                result.errorMessage = "El archivo ya fue cargado con el mismo contenido";
                return ResponseEntity.status(400).body(result);
            }

            file.transferTo(destino);

            //  Procesar el archico segun su extension
            if (extension.equalsIgnoreCase("txt")) {
                usuarios = ProcesarTXT(destino);
            } else if (extension.equalsIgnoreCase("xlsx")) {
                usuarios = ProcesarExcel(destino);
            }

            //validaciones 
            errores = ValidarDatos(usuarios);

            if (errores != null && !errores.isEmpty()) {
                escribirLog(logFile, pathHash, Status.ERROR, "Archivo con " + errores.size() + "errores");

                return ResponseEntity.status(400).body(result);
//                try (FileWriter fw = new FileWriter(LogCargaMasiva, true); PrintWriter writer = new PrintWriter(fw)) {
//                    String timeStamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
//                    writer.println("log|" + pathHash + "|" + upDate + "_" + file.getOriginalFilename() + "|" + status.ERROR.ordinal() + "|" + timeStamp + "|" + "Archvo con errores");
//                } catch (Exception ex) {
//                    ex.printStackTrace();
//                }

            } else {
                escribirLog(logFile, pathHash, Status.PROCESAR, "Archivo listo para procesar");
//                try (FileWriter fw = new FileWriter(LogCargaMasiva, true); PrintWriter writer = new PrintWriter(fw)) {
//                    String timeStamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
//                    writer.println("log|" + pathHash + "|" + upDate + "_" + file.getOriginalFilename() + "|" + status.PROCESAR.ordinal() + "|" + timeStamp + "|" + "Archivo listo para procesar");
//                } catch (Exception ex) {
//                    ex.printStackTrace();
//                }
            }
            if (errores != null && !errores.isEmpty()) {
                result.correct = false;
                result.errorMessage = "Archivo con errores";
                result.errores = errores;
                result.archivoCorrecto = false;
                result.path = null;
                return ResponseEntity.badRequest().body(result);
            } else {
                result.correct = true;
                result.errorMessage = null;
                result.errores = new ArrayList<>();
                result.archivoCorrecto = true;
                result.path = pathHash; // 👈 aquí le mandamos el hash
                return ResponseEntity.ok(result);
            }

        } catch (Exception e) {
            result.correct = false;
            result.errorMessage = "Error al procesar archivo: " + e.getMessage();
            result.archivoCorrecto = false;
            result.errores = new ArrayList<>();
            result.path = null;
            return ResponseEntity.internalServerError().body(result);
        }
    }

    @PostMapping("/cargamasiva/procesar")
    public ResponseEntity<Result> ProcesarArchivo(@RequestParam("hash") String hash) {
        Result result = new Result();

        String logFile = System.getProperty("user.dir") + "/src/main/resources/files/logs/LogCargaMasiva.txt";

        try {
            // leer log para encontrar el registro con ese hash
            List<String> lineas = Files.readAllLines(Paths.get(logFile));
            String ultimaLinea = lineas.stream()
                    .filter(linea -> linea.contains(hash))
                    .reduce((first, second) -> second) // obtiene la última coincidencia
                    .orElse(null);

            if (ultimaLinea == null || !ultimaLinea.contains("|1|")) {//debe estaqr en status 1
                result.correct = false;
                result.errorMessage = "No se encontro archivo en estado de procesar";
                return ResponseEntity.badRequest().body(result);
            }

            // Extraer fecha del log
            String[] parts = ultimaLinea.split("\\|");
            LocalDateTime fechaLog = LocalDateTime.parse(parts[3], DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

            //Validar los 2 minutos
            if (Duration.between(fechaLog, LocalDateTime.now()).toMinutes() > 2) {
                escribirLog(logFile, hash, Status.ERROR, "Error por tiempo expirado");
                result.correct = false;
                result.errorMessage = "Tiempo expirado para procesar el archivo";
                return ResponseEntity.badRequest().body(result);
            }

            //aqui se guardan en la base de datos 
            //UsuarioJPADAOImplemetation.add
            escribirLog(logFile, hash, Status.PROCESADO, "Datos en la BD");
            result.correct = true;
            result.errorMessage = "Archivo procesado y guardado en la BD";
            return ResponseEntity.ok(result);

        } catch (Exception e) {
            e.printStackTrace();
            result.correct = false;
            result.errorMessage = "Error al procesar archivo: " + e.getMessage();
            return ResponseEntity.internalServerError().body(result);
        }
    }

///////////////////////////////
    private void escribirLog(String logFile, String nombreArchivo, Status status, String observacion) {
        try (FileWriter fw = new FileWriter(logFile, true); PrintWriter writer = new PrintWriter(fw)) {
            String timeStamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
            writer.println("log|" + nombreArchivo + "|" + status.getValue() + "|" + timeStamp + "|" + observacion);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
//        return ResponseEntity.status(result.Status).body(result);

    private List<Usuario> ProcesarTXT(File file) {
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file, StandardCharsets.UTF_8));
            String linea = "";
            List<Usuario> usuarios = new ArrayList<>();
            while ((linea = bufferedReader.readLine()) != null) {
                String[] campos = linea.split("\\|");
                Usuario usuario = new Usuario();
                usuario.Rol = new Rol();
                usuario.Direcciones = new ArrayList<>();
                Direccion direccion = new Direccion();
                direccion.colonia = new Colonia(); 
                usuario.Direcciones.add(direccion);

                usuario.setNombre(campos[0]);
                usuario.setApellidoPaterno(campos[1]);
                usuario.setApellidoMaterno(campos[2]);
                if (campos[3] != null && !campos[3].trim().isEmpty()) {
                    LocalDate localDate = LocalDate.parse(campos[3], DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                    Date fecha = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
                    usuario.setFechaNacimiento(fecha);
                } else {
                    usuario.setFechaNacimiento(null);
                }
                usuario.setPassword(campos[4]);
                usuario.setSexo(campos[5]);
                usuario.setUserName(campos[6]);
                usuario.setEmail(campos[7]);
                usuario.setTelefono(campos[8]);
                usuario.setCelular(campos[9]);
                usuario.setCURP(campos[10]);
                usuario.Rol.setIdRol(Integer.parseInt(campos[11]));
                usuario.Direcciones.get(0).setCalle(campos[12]);
                usuario.Direcciones.get(0).setNumeroInterior(campos[13]);
                usuario.Direcciones.get(0).setNumeroExterior(campos[14]);
                usuario.Direcciones.get(0).colonia.setIdColonia(Integer.parseInt(campos[15]));
                usuario.setImagen("");
                usuarios.add(usuario);
            }
            return usuarios;
        } catch (Exception ex) {
            System.out.println(ex);
            return new ArrayList<>();
        }

    }

    private List<Usuario> ProcesarExcel(File file) {
        List<Usuario> usuarios = new ArrayList<>();

        try (XSSFWorkbook workbook = new XSSFWorkbook(file)) {
            DataFormatter formatter = new DataFormatter();
            Sheet sheet = workbook.getSheetAt(0);
            for (Row row : sheet) {
                Usuario usuario = new Usuario();
                usuario.setNombre(row.getCell(0) != null ? row.getCell(0).toString() : "");
                usuario.setApellidoPaterno(row.getCell(1) != null ? row.getCell(1).toString() : "");
                usuario.setApellidoMaterno(row.getCell(2) != null ? row.getCell(2).toString() : "");
                if (row.getCell(3) != null) {
                    if (row.getCell(3).getCellType() == CellType.NUMERIC) {
                        // Excel serial date → java.util.Date directo
                        Date fecha = row.getCell(3).getDateCellValue();
                        usuario.setFechaNacimiento(fecha);
                    } else {
                        // Si viene como String
                        String fechaStr = row.getCell(3).getStringCellValue();
                        DateTimeFormatter formatterDate = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                        LocalDate localDate = LocalDate.parse(fechaStr, formatterDate);
                        Date fecha = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
                        usuario.setFechaNacimiento(fecha);
                    }
                } else {
                    usuario.setFechaNacimiento(null);
                }

                usuario.setPassword(row.getCell(4).toString());
                usuario.setSexo(row.getCell(5).toString());
                usuario.setUserName(row.getCell(6).toString());
                usuario.setEmail(row.getCell(7).toString());
                usuario.setTelefono(formatter.formatCellValue(row.getCell(8)));
                usuario.setCelular(formatter.formatCellValue(row.getCell(9)));
                usuario.setCURP(row.getCell(10).toString());
                usuario.Rol = new Rol();
                usuario.Rol.setIdRol((int) row.getCell(11).getNumericCellValue());
                usuario.Direcciones = new ArrayList<>();
                Direccion direccion = new Direccion();
                direccion.colonia = new Colonia();
                direccion.setCalle(row.getCell(12).toString());
                direccion.setNumeroInterior(formatter.formatCellValue(row.getCell(13)));
                direccion.setNumeroExterior(formatter.formatCellValue(row.getCell(14)));
                direccion.colonia.setIdColonia((int) row.getCell(15).getNumericCellValue());
                usuario.Direcciones.add(direccion);

                usuarios.add(usuario);
            }

            return usuarios;

        } catch (Exception ex) {
            System.out.println(ex);
            return new ArrayList<>();

        }

    }

    public enum Status {
        ERROR(0), PROCESAR(1), PROCESADO(2);

        private final int value;

        Status(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }

    }

    private List<ErrorCM> ValidarDatos(List<Usuario> usuarios) {
        List<ErrorCM> errores = new ArrayList<>();
        int linea = 1;

        for (Usuario usuario : usuarios) {
            if (usuario.getNombre() == null
                    || "".equals(usuario.getNombre())) {
                errores.add(new ErrorCM(linea, "".equals(usuario.getNombre()) ? "vacio" : usuario.getNombre(), "Nombre es un campo obligatorio"));
            } else if (!OnlyLetters(usuario.getNombre())) {
                errores.add(new ErrorCM(linea, usuario.getNombre(), "Nombre no cumple con el formato requerido"));
            }
            if (usuario.getApellidoPaterno() == null
                    || "".equals(usuario.getApellidoPaterno())) {
                errores.add(new ErrorCM(linea, "".equals(usuario.getApellidoPaterno()) ? "vacio" : usuario.getApellidoPaterno(), "Apellido Paterno es un campo obligatorio"));
            } else if (!OnlyLetters(usuario.getApellidoPaterno())) {
                errores.add(new ErrorCM(linea, usuario.getApellidoPaterno(), "Apellido Paterno no cumple con el formato requerido"));
            }
            if (usuario.getApellidoMaterno() == null
                    || "".equals(usuario.getApellidoMaterno())) {
                errores.add(new ErrorCM(linea, "".equals(usuario.getApellidoMaterno()) ? "vacio" : usuario.getApellidoMaterno(), "Apellido Materno es un campo obligatorio"));
            } else if (!OnlyLetters(usuario.getApellidoMaterno())) {
                errores.add(new ErrorCM(linea, usuario.getApellidoMaterno(), "Apellido Materno no cumple con el formato requerido"));
            }
//            if (usuario.getPassword() == null
//                    || "".equals(usuario.getPassword())) {
//                errores.add(new ErrorCM(linea, "".equals(usuario.getPassword()) ? "vacio" : "Vacio", "Contraseña es un campo obligatorio"));
//            } else if (!validatePassword(usuario.getPassword())) {
//                errores.add(new ErrorCM(linea, usuario.getPassword(), "Contrasena no cumple con el formato requerido"));
//            }
            if (usuario.getEmail() == null
                    || "".equals(usuario.getEmail())) {
                errores.add(new ErrorCM(linea, "".equals(usuario.getEmail()) ? "vacio" : usuario.getEmail(), "Email es un campo obligatorio"));
            } else if (!validateEmail(usuario.getEmail())) {
                errores.add(new ErrorCM(linea, usuario.getEmail(), "Email no cumple con el formato requerido"));
            }
            if (usuario.getUserName() == null
                    || "".equals(usuario.getUserName())) {
                errores.add(new ErrorCM(linea, "".equals(usuario.getUserName()) ? "vacio" : usuario.getUserName(), "Username es un campo obligatorio"));
            } else if (!validateUsername(usuario.getUserName())) {
                errores.add(new ErrorCM(linea, usuario.getUserName(), "Username no cumple con el formato requerido"));
            }

            linea++;
        }
        return errores;
    }

    static boolean OnlyLetters(String text) {
        String regexOnlyLetters = "^[A-Za-zÁÉÍÓÚáéíóúÑñ\\s]+$";
        Pattern pattern = Pattern.compile(regexOnlyLetters);
        Matcher matcher = pattern.matcher(text);
        return matcher.matches();
    }

//    static boolean validatePassword(String text) {
//    String regexPassword = "^[A-Za-z\\d]{6,12}$";
//    Pattern pattern = Pattern.compile(regexPassword);
//    Matcher matcher = pattern.matcher(text);
//    return matcher.matches();
//}
    static boolean validateEmail(String text) {
        String regexEmail = "(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])";
        Pattern pattern = Pattern.compile(regexEmail);
        Matcher matcher = pattern.matcher(text);
        return matcher.matches();
    }

    static boolean validateUsername(String text) {
        String regexUsername = "^(?!.*[_.]{2})[a-zA-Z0-9](?!.*[_.]{2})[a-zA-Z0-9._]{1,14}[a-zA-Z0-9]$";
        Pattern pattern = Pattern.compile(regexUsername);
        Matcher matcher = pattern.matcher(text);
        return matcher.matches();
    }

}
