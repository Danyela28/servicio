
package com.TGarciaProgramacionNCapas25.Proyect.RestControllerRespository;

import com.TGarciaProgramacionNCapas25.Proyect.JPA.Result;
import com.TGarciaProgramacionNCapas25.Proyect.Service.ServiceEstado;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/Estado")
public class EstadoControllerRepository {
    
    @Autowired
    private ServiceEstado serviceEstado;
    
    @GetMapping("/{IdPais}")
    public ResponseEntity GetByIdEstado(@PathVariable("IdPais")int IdPais){
        
        Result result = serviceEstado.GetByIdPais(IdPais);
        return ResponseEntity.status(result.Status).body(result);
    }
    
}
