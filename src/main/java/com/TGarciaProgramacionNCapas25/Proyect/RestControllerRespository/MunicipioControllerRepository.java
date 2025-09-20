
package com.TGarciaProgramacionNCapas25.Proyect.RestControllerRespository;

import com.TGarciaProgramacionNCapas25.Proyect.JPA.Result;
import com.TGarciaProgramacionNCapas25.Proyect.Service.ServiceMunicipio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/Municipio")
public class MunicipioControllerRepository {
    
    @Autowired
    private ServiceMunicipio serviceMunicipio;
    
    @GetMapping("/{IdEstado}")
    public ResponseEntity GetByIdEstado(@PathVariable("IdEstado")int IdEstado){
        
        Result result = serviceMunicipio.GetByIdEstado(IdEstado);
        return ResponseEntity.status(result.Status).body(result);
    }
    
}
