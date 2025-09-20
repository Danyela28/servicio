
package com.TGarciaProgramacionNCapas25.Proyect.Service;

import com.TGarciaProgramacionNCapas25.Proyect.DAO.IRepositoryEstado;
import com.TGarciaProgramacionNCapas25.Proyect.JPA.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServiceEstado {
    
    @Autowired
    private IRepositoryEstado iRepositoryEstado;
    
    public Result GetByIdPais(int IdPais){
        Result result = new Result();
        try{
            result.correct=true;
            result.object=iRepositoryEstado.findById(IdPais);
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
