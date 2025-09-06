
package com.TGarciaProgramacionNCapas25.Proyect.RestContoller;


import com.TGarciaProgramacionNCapas25.Proyect.DAO.DireccionJPADAOImplementation;
import com.TGarciaProgramacionNCapas25.Proyect.DAO.UsuarioJPADAOImplementation;
import com.TGarciaProgramacionNCapas25.Proyect.JPA.Result;
import com.TGarciaProgramacionNCapas25.Proyect.JPA.Usuario;

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
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("usuarioapi")
public class UsuarioRestController {
    
    @Autowired
    private UsuarioJPADAOImplementation usuarioJPADAOImplementation;
    
    
    
    //MOSTRAR TODOS
    @GetMapping
    public ResponseEntity GetAll(){
        
        Result result;

        try{
            result= usuarioJPADAOImplementation.GetAll();
            result.correct= true;
            return ResponseEntity.status(200).body(result);
            
        }catch(Exception ex){
            result = new Result();
            result.ex = ex;
            result.errorMessage = ex.getLocalizedMessage();
            result.correct = false;
            return ResponseEntity.status(500).body(result);

        }
        
    }
    
    //AGREGAR USUARIO
    @PostMapping
    public ResponseEntity Add(@RequestBody Usuario usuario){
        
        Result result = new Result();
        try{
            result = usuarioJPADAOImplementation.Add(usuario);
            return ResponseEntity.status(201).body(result);
            
        }catch(Exception ex){
            result.ex= ex;
            result.correct=false;
            result.errorMessage=ex.getLocalizedMessage();
            return ResponseEntity.status(500).body(result);
        }
        
    }
    
    //MODIFICAR USUARIO
    @PutMapping("/{IdUsuario}")
    public ResponseEntity<Result>Update(@PathVariable int IdUsuario, @RequestBody Usuario usuario){
        
        usuario.setIdUsuario(IdUsuario);
        Result result = usuarioJPADAOImplementation.Update(usuario);
        
        return ResponseEntity.status(result.Status).body(result);
        
    }
    
    
    
    //ELIMINAR USUARIO
    @DeleteMapping("/{IdUsuario}")
    public ResponseEntity<Result> Delete(@PathVariable int IdUsuario){
        Result result = new Result();
        try{
            result = usuarioJPADAOImplementation.Delete(IdUsuario);
            result.correct= true;
            return ResponseEntity.status(200).body(result);
            
        }catch (Exception ex){
            result.ex = ex;
            result.correct = false;
            result.errorMessage = ex.getLocalizedMessage();
            return ResponseEntity.status(500).body(result);
        }
    }
    
    //USUARIO BUSQUEDA POR ID
    @GetMapping("/{IdUsuario}")
    public ResponseEntity<Result>GetById(@PathVariable int IdUsuario){
        Result result = new Result();
        
        try{
            result = usuarioJPADAOImplementation.GetById(IdUsuario);
            result.correct=true;
            return ResponseEntity.status(200).body(result);
        }catch(Exception ex){
            result.correct=false;
            result.errorMessage=ex.getLocalizedMessage();
            result.ex=ex;
            return ResponseEntity.status(500).body(result);
        }
    }
   @PatchMapping("/estatus/{IdUsuario}")
    public ResponseEntity LogicalDelete(@PathVariable int IdUsuario) {
        Result result = usuarioJPADAOImplementation.LogicalDelete(IdUsuario);
        return ResponseEntity.status(result.Status).body(result);
    }

}
