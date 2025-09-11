
package com.TGarciaProgramacionNCapas25.Proyect.RestContoller;

import com.TGarciaProgramacionNCapas25.Proyect.DAO.DireccionJPADAOImplementation;
import com.TGarciaProgramacionNCapas25.Proyect.JPA.Direccion;
import com.TGarciaProgramacionNCapas25.Proyect.JPA.Result;
import com.TGarciaProgramacionNCapas25.Proyect.JPA.Usuario;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name="Controller de Direccion", description="Controla los metodos para las Direcciones")
@RestController
@RequestMapping("direccionapi")
public class DireccionRestController {
    
    @Autowired
    private DireccionJPADAOImplementation direccionJPADAOImplementation;
    
        
    //ELIMINAR DIRECCION
    @DeleteMapping("/{IdUsuario}/direcciones/{IdDireccion}")
    @Operation(description="Metodo para eliminar una Direccion")
    public ResponseEntity<Result>DeleteDireccion(@PathVariable int IdUsuario, @PathVariable int IdDireccion){
        Result result = new Result();
        try{
            result = direccionJPADAOImplementation.Delete(IdDireccion);
            result.correct = true;
            return ResponseEntity.status(200).body(result);
            
        }catch(Exception ex){
            result.ex=ex;
            result.correct=false;
            result.errorMessage=ex.getLocalizedMessage();
            return ResponseEntity.status(500).body(result);
        }
    }

    
    @PostMapping()
    @Operation(description="Metodo para agregar una direccion")
    public ResponseEntity Add(@RequestBody Usuario usuario) {
        Result result = new Result();
        try{
            
            result = direccionJPADAOImplementation.Add(usuario);
            result.correct=true;
            return ResponseEntity.status(200).body(result);
            
        }catch(Exception ex){
            result.correct=false;
            result.errorMessage=ex.getLocalizedMessage();
            result.ex=ex;
            return ResponseEntity.status(500).body(result);
        }
       

    }    
//ACTUALIZAR UNA DIRECCION ESPECIFICA
//@PutMapping("{IdDireccion}")
//@Operation(description="Metodo para actualizar una direccion especifica")
//public ResponseEntity Update(@PathVariable int IdDireccion,
//        @RequestBody Usuario usuario) {
//    
//    Result result = new Result();
//    try {
//        // Validar que el usuario tenga direcciones
//        if (usuario.Direcciones == null || usuario.Direcciones.isEmpty()) {
//            result.Status = 400;
//            result.errorMessage = "No se proporcionaron direcciones";
//            return ResponseEntity.status(result.Status).body(result);
//        }
//        
//        // Establecer el ID desde el path variable
//        usuario.Direcciones.get(0).IdDireccion = IdDireccion;
//        
//        result = direccionJPADAOImplementation.Update(usuario);
//        return ResponseEntity.status(result.Status).body(result);
//        
//    } catch (Exception ex) {
//        result.Status = 500;
//        result.correct = false;
//        result.errorMessage = "Error interno: " + ex.getMessage();
//        return ResponseEntity.status(result.Status).body(result);
//    }
//}

    
    //GETBYID
    @GetMapping("/{IdDireccion}")
    @Operation(description="Metodo para sacar una direccion especifica")
    public ResponseEntity<Result>GetById(@PathVariable int IdDireccion){
        Result result = new Result();
        
        try{
            result = direccionJPADAOImplementation.GetById(IdDireccion);
            result.correct=true;
            return ResponseEntity.status(200).body(result);
            
        }catch(Exception ex){
            result.correct=false;
            result.errorMessage=ex.getLocalizedMessage();
            result.ex=ex;
            return ResponseEntity.status(500).body(result);
        }
    }
    
    
}
