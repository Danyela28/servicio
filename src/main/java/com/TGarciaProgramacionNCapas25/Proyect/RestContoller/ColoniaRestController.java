
package com.TGarciaProgramacionNCapas25.Proyect.RestContoller;

import com.TGarciaProgramacionNCapas25.Proyect.DAO.ColoniaJPADAOImplementation;
import com.TGarciaProgramacionNCapas25.Proyect.JPA.Result;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name="Controller de Colonia", description="Controla los metodos de la colonia")
@RestController
@RequestMapping("coloniaapi")
public class ColoniaRestController {
    
    @Autowired
    private ColoniaJPADAOImplementation coloniaJPADAOImplementation;
    
    @GetMapping()
    @Operation(description="Metodo para retornar todas las colonias")
    public ResponseEntity GetAll() {
        Result result = coloniaJPADAOImplementation.GetAll();
        return ResponseEntity.status(result.Status).body(result);
    }

    @GetMapping("/{IdMunicipio}")
    @Operation(description="Metodo para retornar al municipio")
    public ResponseEntity GetByMunicipio(@PathVariable int IdMunicipio) {
        Result result = coloniaJPADAOImplementation.GetByIdMunicipio(IdMunicipio);
        return ResponseEntity.status(result.Status).body(result);
    }
    
    
    
}
