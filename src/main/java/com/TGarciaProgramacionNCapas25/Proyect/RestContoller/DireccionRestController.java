
package com.TGarciaProgramacionNCapas25.Proyect.RestContoller;

import com.TGarciaProgramacionNCapas25.Proyect.DAO.DireccionJPADAOImplementation;
import com.TGarciaProgramacionNCapas25.Proyect.JPA.Direccion;
import com.TGarciaProgramacionNCapas25.Proyect.JPA.Result;
import com.TGarciaProgramacionNCapas25.Proyect.JPA.Usuario;
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

@RestController
@RequestMapping("direccionapi")
public class DireccionRestController {
    
    @Autowired
    private DireccionJPADAOImplementation direccionJPADAOImplementation;
    
        
    //ELIMINAR DIRECCION
    @DeleteMapping("/{idUsuario}/direcciones/{idDireccion}")
    public ResponseEntity<Result>DeleteDireccion(@PathVariable Long idUsuario, @PathVariable Long idDireccion){
        Result result = new Result();
        try{
            result = direccionJPADAOImplementation.Delete(idDireccion);
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
    @PutMapping("{IdDireccion}")
    public ResponseEntity Update(@PathVariable int IdDireccion,
            @RequestBody Usuario usuario) {
        usuario.Direcciones.get(0).setIdDireccion(IdDireccion);
        Result result = direccionJPADAOImplementation.Update(usuario);
        return ResponseEntity.status(result.Status).body(result);
    }

    
    //GETBYID
    @GetMapping("/{IdDireccion}")
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
