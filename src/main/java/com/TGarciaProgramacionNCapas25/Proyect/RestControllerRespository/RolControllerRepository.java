
package com.TGarciaProgramacionNCapas25.Proyect.RestControllerRespository;

import com.TGarciaProgramacionNCapas25.Proyect.JPA.Result;
import com.TGarciaProgramacionNCapas25.Proyect.Service.ServiceRol;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/Rol")
public class RolControllerRepository {
    
    @Autowired
    private ServiceRol serviceRol;
    
    @GetMapping
    public ResponseEntity GetAll(){
        
        Result result = serviceRol.GetAllRol();
        return ResponseEntity.status(result.Status).body(result);
 
    }
   
    
}
