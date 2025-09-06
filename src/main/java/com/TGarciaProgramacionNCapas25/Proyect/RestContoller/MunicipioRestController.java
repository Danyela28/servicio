
package com.TGarciaProgramacionNCapas25.Proyect.RestContoller;

import com.TGarciaProgramacionNCapas25.Proyect.DAO.MunicipioJPADAOImplementation;
import com.TGarciaProgramacionNCapas25.Proyect.JPA.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("municipioapi")
public class MunicipioRestController {
    
    @Autowired
    private MunicipioJPADAOImplementation municipioJPADAOImplementation;
    
    @GetMapping()
    public ResponseEntity GetAll() {
        Result result = municipioJPADAOImplementation.GetAll();
        return ResponseEntity.status(result.Status).body(result);
    }

    @GetMapping("/{IdEstado}")
    public ResponseEntity GetByEstado(@PathVariable int IdEstado) {
        Result result = municipioJPADAOImplementation.GetByEstado(IdEstado);
        return ResponseEntity.status(result.Status).body(result);
    }
}
    

