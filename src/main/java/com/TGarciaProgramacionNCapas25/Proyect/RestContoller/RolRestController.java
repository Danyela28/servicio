
package com.TGarciaProgramacionNCapas25.Proyect.RestContoller;

import com.TGarciaProgramacionNCapas25.Proyect.DAO.RolJPADAOImplementation;
import com.TGarciaProgramacionNCapas25.Proyect.JPA.Result;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name="Controller de Roles", description="Controla el metodo del los roles")
@RestController
@RequestMapping("rolapi")
public class RolRestController {
    
    @Autowired
    private RolJPADAOImplementation rolJPADAOImplementation;
    
    //TODOS
    @GetMapping
    @Operation(description="Metodo para retornar a todos los roles")
    @ApiResponse(responseCode = "200", description = "Roles correctos")
    @ApiResponse(responseCode = "500", description = "Proceso no exitoso")
    public ResponseEntity GetAll(){
        Result result;
        
        try{
            result=rolJPADAOImplementation.GetAll();
            result.correct=true;
            return ResponseEntity.status(200).body(result);
            
        }catch(Exception ex){
            result= new Result();
            result.correct=false;
            result.errorMessage=ex.getLocalizedMessage();
            result.ex=ex;
            return ResponseEntity.status(500).body(result);
        }
    }
    
    
}
