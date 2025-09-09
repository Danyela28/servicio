
package com.TGarciaProgramacionNCapas25.Proyect.RestContoller;

import com.TGarciaProgramacionNCapas25.Proyect.DAO.MunicipioJPADAOImplementation;
import com.TGarciaProgramacionNCapas25.Proyect.JPA.Result;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name="Controller de Municipio", description="Controla los metodos de los municipios")
@RestController
@RequestMapping("municipioapi")
public class MunicipioRestController {
    
    @Autowired
    private MunicipioJPADAOImplementation municipioJPADAOImplementation;
    
    @GetMapping
    @Operation(description="Metodo para retornar todos los municipios")
    @ApiResponse(responseCode = "200", description = "Municipios encontrados")
    @ApiResponse(responseCode = "500", description = "Proceso fallido")
    public ResponseEntity GetAll() {
        Result result = municipioJPADAOImplementation.GetAll();
        return ResponseEntity.status(result.Status).body(result);
    }

    @GetMapping("/{IdEstado}")
    @Operation(description="Metodo para retornar al estado")
    
    public ResponseEntity GetByEstado(@PathVariable int IdEstado) {
        Result result = municipioJPADAOImplementation.GetByEstado(IdEstado);
        return ResponseEntity.status(result.Status).body(result);
    }
}
    

