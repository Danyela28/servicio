
package com.TGarciaProgramacionNCapas25.Proyect.RestContoller;


import com.TGarciaProgramacionNCapas25.Proyect.DAO.UsuarioJPADAOImplementation;
import com.TGarciaProgramacionNCapas25.Proyect.JPA.Result;
import com.TGarciaProgramacionNCapas25.Proyect.JPA.Usuario;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("usuarioapi")
public class UsuarioRestController {
    
    @Autowired
    private UsuarioJPADAOImplementation usuarioJPADAOImplementation;
    
    
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
    
    @PostMapping
    public ResponseEntity Add(@RequestBody Usuario usuario){
        
        Result result = new Result();
        try{
            result = usuarioJPADAOImplementation.Add(usuario);
            result.correct = true;
            return ResponseEntity.status(201).body(result);
            
        }catch(Exception ex){
            result.ex= ex;
            result.correct=false;
            result.errorMessage=ex.getLocalizedMessage();
            return ResponseEntity.status(500).body(result);
        }
        
    }
    

}
