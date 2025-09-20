
package com.TGarciaProgramacionNCapas25.Proyect.Service;

import com.TGarciaProgramacionNCapas25.Proyect.DAO.IRepositoryDireccion;
import com.TGarciaProgramacionNCapas25.Proyect.JPA.Direccion;
import com.TGarciaProgramacionNCapas25.Proyect.JPA.Result;
import com.TGarciaProgramacionNCapas25.Proyect.JPA.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServicieDireccion {
    
    @Autowired
    private IRepositoryDireccion iRepositoryDireccion;
    
    
    public Result GetByIdDireccion(int IdDireccion){
        Result result = new Result();
        try{
            result.correct=true;
            result.object=iRepositoryDireccion.findById(IdDireccion);
            result.Status=200;
            
        }catch(Exception ex){
            result.ex=ex;
            result.correct=false;
            result.errorMessage=ex.getLocalizedMessage();
            result.Status=500;
        }
        return result;
    }
    public Result AddDireccion(Usuario usuario){
        Result result = new Result();
        try{
            result.correct=true;
            result.object=iRepositoryDireccion.save(usuario);
            result.Status=200;
            
        }catch(Exception ex){
            result.ex=ex;
            result.correct=false;
            result.errorMessage=ex.getLocalizedMessage();
            result.Status=500;
        }
        return result;
    }
        public Result DeleteDireccion(int IdUsuario, int IdDireccion){
        Result result = new Result();
        try{
            result.correct=true;
            iRepositoryDireccion.deleteById( IdDireccion);
            result.Status=200;
            
        }catch(Exception ex){
            result.ex=ex;
            result.correct=false;
            result.errorMessage=ex.getLocalizedMessage();
            result.Status=500;
        }
        return result;      
    }
    
}
