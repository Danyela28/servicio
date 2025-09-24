
package com.TGarciaProgramacionNCapas25.Proyect.Service;

import com.TGarciaProgramacionNCapas25.Proyect.DAO.IRepositoryUsuario;
import com.TGarciaProgramacionNCapas25.Proyect.JPA.Result;
import com.TGarciaProgramacionNCapas25.Proyect.JPA.Usuario;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class ServiceUsuario {
    
    @Autowired
    private IRepositoryUsuario iRepositoryUsuario;
    
    
    
    public Result GetAllRepository(){
        
        Result result = new Result();
        try{
            result.correct = true;
            result.object = iRepositoryUsuario.findAll();
            result.Status=200;
            
        }catch(Exception ex){          
            result.ex = ex;
            result.errorMessage = ex.getLocalizedMessage();
            result.correct = false;
            result.Status=500;
        }
        return result;
    }
    
    public Result GetByIdUsuario(int IdUsuario){
        
        Result result = new Result();
        try{
            result.correct=true;
            result.object= iRepositoryUsuario.findById(IdUsuario);
            result.Status=200;
            
        }catch(Exception ex){
            result.ex=ex;
            result.correct=false;
            result.errorMessage=ex.getLocalizedMessage();
            result.Status=500;
        }
        return result;
    }
//    public Result AddUsuario(Usuario usuario){
//        
//        if (usuario.getDirecciones() != null) {
//            usuario.getDirecciones().forEach(d -> d.setUsuario(usuario));
//        }
//       
//        Result result = new Result();
//        try{
//            result.correct=true;
//            result.object= iRepositoryUsuario.save(usuario);
//            result.Status=200;
//            
//        }catch(Exception ex){
//            result.ex=ex;
//            result.correct=false;
//            result.errorMessage=ex.getLocalizedMessage();
//            result.Status=500;
//        }
//        return result;  
//    }
    public Result DeleteUsuario(int IdUsuario){
        Result result = new Result();
        try{
            result.correct=true;
            iRepositoryUsuario.deleteById( IdUsuario);
            result.Status=200;
            
        }catch(Exception ex){
            result.ex=ex;
            result.correct=false;
            result.errorMessage=ex.getLocalizedMessage();
            result.Status=500;
        }
        return result;      
    }
    public Result UpDate(Usuario usuario){
        Result result = new Result();
        try {
           
            Optional<Usuario> optionalUsuario = iRepositoryUsuario.findById(usuario.getIdUsuario());

            if (optionalUsuario.isPresent()) {
                
                iRepositoryUsuario.save(usuario);
                
                result.correct = true;
                result.object = usuario;
                result.Status = 200;
                result.errorMessage = "El usuario se actualizó correctamente.";

            } else {
                
                result.correct = false;
                result.Status = 404; 
                result.errorMessage = "Usuario no encontrado, ID incorrecto: " + usuario.getIdUsuario();
            }

        } catch (Exception ex) {
            
            result.correct = false;
            result.errorMessage = ex.getLocalizedMessage();
            result.ex = ex;
            result.Status = 500;
        }
        return result;
    }
    
        
    
    
//    private Result BajaLogica(int idStatus){
//        
//        Optional<Alumno> alumno = iRepositoryAlumno.findById(idStatus);
//        
//        if (alumno.isPresent()) {
//            // alumno.setStatus(alumno.getStatus() == 1 ? 0 : 1)
//        }
//        
//        
//        return null; // aquí va el result
//    }
    
    
}
