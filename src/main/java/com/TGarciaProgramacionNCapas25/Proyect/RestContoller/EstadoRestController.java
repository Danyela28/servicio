
package com.TGarciaProgramacionNCapas25.Proyect.RestContoller;

import com.TGarciaProgramacionNCapas25.Proyect.DAO.EstadoJPADAOImplementation;
import com.TGarciaProgramacionNCapas25.Proyect.JPA.Result;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@CrossOrigin(origins = "*")
@Tag(name="Controller de Estados", description="Controla los metodos de los estados")
@RestController
@RequestMapping("estadoapi")
public class EstadoRestController {
    
    @Autowired
    private EstadoJPADAOImplementation estadoJPADAOImplementation;
    
    
    @GetMapping
    @Operation(description="Metodo para retornar todos los estados")
    public ResponseEntity GetAll() {
        Result result = estadoJPADAOImplementation.GetAll();
        return ResponseEntity.status(result.Status).body(result);
    }

    @GetMapping("/{IdPais}")
    @Operation(description="Metodo para retornar al pais")
    public ResponseEntity GetByIdPais(@PathVariable int IdPais) {
        Result result = new Result();
        try{
            result = estadoJPADAOImplementation.GetByIdPais(IdPais);
             return ResponseEntity.status(200).body(result);
        }catch(Exception ex){
            result.correct=false;
            result.errorMessage=ex.getLocalizedMessage();
            result.ex=ex;
            return ResponseEntity.status(500).body(result);
        }
        
       
    }
}

    

