
package com.TGarciaProgramacionNCapas25.Proyect.RestControllerRespository;

import com.TGarciaProgramacionNCapas25.Proyect.JPA.Result;
import com.TGarciaProgramacionNCapas25.Proyect.Service.ServiceColonia;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/colonia")
public class ColoniaControllerRepository {
    
    
    @Autowired
    private ServiceColonia serviceColonia;
    
    @GetMapping("/{IdMunicipio}")
    public ResponseEntity GetByIdMunicipio(@PathVariable("IdMunicipio")int IdMunicipio){
        
        Result result = serviceColonia.GetByIdMunicipio(IdMunicipio);
        return ResponseEntity.status(result.Status).body(result);
    }
    
  
}
