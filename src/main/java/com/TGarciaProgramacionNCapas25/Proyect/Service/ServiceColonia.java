
package com.TGarciaProgramacionNCapas25.Proyect.Service;

import com.TGarciaProgramacionNCapas25.Proyect.DAO.IRepositoryColonia;
import com.TGarciaProgramacionNCapas25.Proyect.JPA.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServiceColonia {
    
    @Autowired
    private IRepositoryColonia iRepositoryColonia;
    
    public Result GetByIdMunicipio(int IdMunicipio){
        Result result = new Result();
        try{
            result.correct=true;
            result.object=iRepositoryColonia.findById(IdMunicipio);
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
