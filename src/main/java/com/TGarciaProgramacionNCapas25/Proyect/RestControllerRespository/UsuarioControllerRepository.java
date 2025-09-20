
package com.TGarciaProgramacionNCapas25.Proyect.RestControllerRespository;

import com.TGarciaProgramacionNCapas25.Proyect.DAO.IRepositoryUsuario;
import com.TGarciaProgramacionNCapas25.Proyect.JPA.Result;
import com.TGarciaProgramacionNCapas25.Proyect.JPA.Usuario;
import com.TGarciaProgramacionNCapas25.Proyect.Service.ServiceUsuario;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping("api/usuario")
public class UsuarioControllerRepository {
    

@Autowired
private ServiceUsuario serviceUsuario;
    
    @GetMapping
    public ResponseEntity GetAllRepository(){
        
        Result result = serviceUsuario.GetAllRepository();
        return ResponseEntity.status(result.Status).body(result);
 
    }
    @GetMapping("/{idUsuario}")
    public ResponseEntity GetByIdUsuario(@PathVariable("idUsuario")int IdUsuario){
        
        Result result = serviceUsuario.GetByIdUsuario(IdUsuario);
        return ResponseEntity.status(result.Status).body(result);
        
    }

    @PostMapping
    public ResponseEntity ADD(@RequestBody Usuario usuario){
        
        Result result = serviceUsuario.AddUsuario(usuario);
        return ResponseEntity.status(result.Status).body(result);
        
    }
    //Update @PutMapping("/{IdUsuario}")  Update(@PathVariable int IdUsuario, @RequestBody Usuario usuario
//    @PutMapping("/{IdUsuario}")
    @PutMapping
    public ResponseEntity<Result> UpDate(@RequestBody Usuario usuario) {
        Result result = serviceUsuario.UpDate(usuario);
        return ResponseEntity.status(result.Status).body(result);
    }
    
    //Delete    
    @DeleteMapping("/{IdUsuario}")
    public ResponseEntity Delete(@PathVariable int IdUsuario){
        
        Result result = serviceUsuario.DeleteUsuario(IdUsuario);
        return ResponseEntity.status(result.Status).body(result);
    }
    
    
    
  
//    @GetMapping("{username}")
//    public ResponseEntity GetByUserName(@PathVariable("username") String UserName){
//        
//        Result result = new Result();
//        result.correct = true;
//        result.object = iRepositoryUsuario.findByUserName(UserName);
//        
//        return ResponseEntity.ok(result);
//    }
    

}