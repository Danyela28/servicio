
package com.TGarciaProgramacionNCapas25.Proyect.RestContoller;

import com.TGarciaProgramacionNCapas25.Proyect.DAO.ColoniaJPADAOImplementation;
import com.TGarciaProgramacionNCapas25.Proyect.JPA.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("coloniaapi")
public class ColoniaRestController {
    
    @Autowired
    private ColoniaJPADAOImplementation coloniaJPADAOImplementation;
    
    @GetMapping()
    public ResponseEntity GetAll() {
        Result result = coloniaJPADAOImplementation.GetAll();
        return ResponseEntity.status(result.Status).body(result);
    }

    @GetMapping("/{IdMunicipio}")
    public ResponseEntity GetByMunicipio(@PathVariable int IdMunicipio) {
        Result result = coloniaJPADAOImplementation.GetByIdMunicipio(IdMunicipio);
        return ResponseEntity.status(result.Status).body(result);
    }
    
    
    
}
