
package com.TGarciaProgramacionNCapas25.Proyect.RestContoller;

import com.TGarciaProgramacionNCapas25.Proyect.DAO.PaisJPADAOImplementation;
import com.TGarciaProgramacionNCapas25.Proyect.JPA.Result;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*")
@Tag(name="Controller de Usuario", description="Controla el metodo de pais")
@RestController
@RequestMapping("paisapi")
public class PaisRestController {
    
    @Autowired
    private PaisJPADAOImplementation paisJPADAOImplementation;
    
    
    //TODOS
    @GetMapping
    @Operation(description="Metodo para retornar todos los paises")
    @ApiResponse(responseCode = "200", description = "Paises encontrados")
    @ApiResponse(responseCode = "500", description = "Proceso no exitoso")
    public ResponseEntity GetAll(){
        
        Result result = new Result();
        
        try{
            result = paisJPADAOImplementation.GetAll();
            result.correct=true;
            return ResponseEntity.status(200).body(result);
            
        }catch(Exception ex){
            result.correct=false;
            result.errorMessage=ex.getLocalizedMessage();
            result.ex=ex;
            return ResponseEntity.status(500).body(result);
        }
        
    }
}
