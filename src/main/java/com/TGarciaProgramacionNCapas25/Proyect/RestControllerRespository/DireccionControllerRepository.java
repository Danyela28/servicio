
package com.TGarciaProgramacionNCapas25.Proyect.RestControllerRespository;


import com.TGarciaProgramacionNCapas25.Proyect.JPA.Result;
import com.TGarciaProgramacionNCapas25.Proyect.JPA.Usuario;
import com.TGarciaProgramacionNCapas25.Proyect.Service.ServicieDireccion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/Direccion")
public class DireccionControllerRepository {
    
    @Autowired
    private ServicieDireccion serviceDireccion;
    
    @GetMapping("/{idDireccion}")
    public ResponseEntity GetById(@PathVariable("idDireccion")int IdDireccion){
        
        Result result = serviceDireccion.GetByIdDireccion(IdDireccion);
        return ResponseEntity.status(result.Status).body(result);
    }
    //Add @RequestBody Usuario   @Post
    @PostMapping
    public ResponseEntity ADD(@RequestBody Usuario usuario){
        
        Result result = serviceDireccion.AddDireccion(usuario);
        return ResponseEntity.status(result.Status).body(result);
    }
    //Delete  @PathVariable int IdUsuario, int IdDireccion
    @DeleteMapping("/{IdDireccion}")
    public ResponseEntity Delete(@PathVariable int IdUsuario, @PathVariable int IdDireccion){
        
        Result result = serviceDireccion.DeleteDireccion(IdUsuario, IdDireccion);
        return ResponseEntity.status(result.Status).body(result);
    }
    
    //UpDate int idDireccion @put
}
