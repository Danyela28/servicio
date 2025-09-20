
package com.TGarciaProgramacionNCapas25.Proyect.RestControllerRespository;

import com.TGarciaProgramacionNCapas25.Proyect.JPA.Result;
import com.TGarciaProgramacionNCapas25.Proyect.Service.ServicePais;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/pais")
public class PaisControllerRepository {
    
    @Autowired
    private ServicePais servicePais;
    
    @GetMapping
    public ResponseEntity GetAllRepository(){
        
        Result result = servicePais.GetAllPais();
        return ResponseEntity.status(result.Status).body(result);
 
    }
    
}
