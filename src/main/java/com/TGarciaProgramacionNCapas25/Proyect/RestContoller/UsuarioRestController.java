
package com.TGarciaProgramacionNCapas25.Proyect.RestContoller;


import com.TGarciaProgramacionNCapas25.Proyect.DAO.DireccionJPADAOImplementation;
import com.TGarciaProgramacionNCapas25.Proyect.DAO.UsuarioJPADAOImplementation;
import com.TGarciaProgramacionNCapas25.Proyect.JPA.Result;
import com.TGarciaProgramacionNCapas25.Proyect.JPA.Usuario;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name="Controller de Usuario", description="Controla los metodos del Usuario")
@RestController
@RequestMapping("usuarioapi")
public class UsuarioRestController {
    
    @Autowired
    private UsuarioJPADAOImplementation usuarioJPADAOImplementation;
    
    
    
    //MOSTRAR TODOS
    @GetMapping
    @Operation(description="Metodo para retornar a todos los usuarios")
    @ApiResponse(responseCode = "200", description = "Usuarios encontrados")
    @ApiResponse(responseCode = "500", description = "proceso no exitoso")
    public ResponseEntity GetAll(){
        
        Result result;

        try{
            result= usuarioJPADAOImplementation.GetAll();
            result.correct= true;
            return ResponseEntity.status(200).body(result);
            
        }catch(Exception ex){
            result = new Result();
            result.ex = ex;
            result.errorMessage = ex.getLocalizedMessage();
            result.correct = false;
            return ResponseEntity.status(500).body(result);

        }
        
    }
    
    //AGREGAR USUARIO
    @PostMapping
    @Operation(description="Metodo para agregar a un usuario completo")
    @ApiResponse(responseCode = "200", description = "Usuario agregado ")
    @ApiResponse(responseCode = "500", description = "Error al agregar usuario")
    public ResponseEntity Add(@RequestBody Usuario usuario){
        
        Result result = new Result();
        try{
            usuario.getIdUsuario();
            result = usuarioJPADAOImplementation.Add(usuario);
            return ResponseEntity.status(201).body(result);
            
        }catch(Exception ex){
            result.ex= ex;
            result.correct=false;
            result.errorMessage=ex.getLocalizedMessage();
            return ResponseEntity.status(500).body(result);
        }
        
    }
    
    //MODIFICAR USUARIO
    @PutMapping("/{IdUsuario}")
    @Operation(description="Metodo para modificar al usuario")
    @ApiResponse(responseCode = "200", description = "Usuario modificado exitosamente")
    @ApiResponse(responseCode = "500", description = "Proceso no exitoso")
    public ResponseEntity<Result>Update(@PathVariable int IdUsuario, @RequestBody Usuario usuario){
        
        usuario.setIdUsuario(IdUsuario);
        Result result = usuarioJPADAOImplementation.Update(usuario);
        
        return ResponseEntity.status(result.Status).body(result);
        
    }
    
    
    
    //ELIMINAR USUARIO
    @DeleteMapping("/{IdUsuario}")
    @Operation(description="Metodo para eliminar al usuario del index")
    @ApiResponse(responseCode = "200", description = "Usuario Eliminado")
    @ApiResponse(responseCode = "404", description = "Usuario NO Eliminado")
    public ResponseEntity<Result> Delete(@PathVariable int IdUsuario){
        Result result = new Result();
        try{
            result = usuarioJPADAOImplementation.Delete(IdUsuario);
            result.correct= true;
            return ResponseEntity.status(200).body(result);
            
        }catch (Exception ex){
            result.ex = ex;
            result.correct = false;
            result.errorMessage = ex.getLocalizedMessage();
            return ResponseEntity.status(500).body(result);
        }
    }
    
    //USUARIO BUSQUEDA POR ID
    @GetMapping("/{IdUsuario}")
    @Operation(description="Metodo para obtener la informacion de un uduario en especial")
    @ApiResponse(responseCode = "200", description = "Usuario encontrado")
    @ApiResponse(responseCode = "500", description = "Usuario no encontrado")
    public ResponseEntity<Result>GetById(@PathVariable int IdUsuario){
        Result result = new Result();
        
        try{
            result = usuarioJPADAOImplementation.GetById(IdUsuario);
            result.correct=true;
            return ResponseEntity.status(200).body(result);
        }catch(Exception ex){
            result.correct=false;
            result.errorMessage=ex.getLocalizedMessage();
            result.ex=ex;
            return ResponseEntity.status(500).body(result);
        }
    }
   @PatchMapping("/estatus/{IdUsuario}")
   @Operation(description="Metodo para hacer una baja logica")
    public ResponseEntity BajaLogica(@PathVariable int IdUsuario) {
        Result result = usuarioJPADAOImplementation.BajaLogica(IdUsuario);
        return ResponseEntity.status(result.Status).body(result);
    }

}
