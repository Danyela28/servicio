
package com.TGarciaProgramacionNCapas25.Proyect.Service;

import com.TGarciaProgramacionNCapas25.Proyect.DAO.IRepositoryRol;
import com.TGarciaProgramacionNCapas25.Proyect.JPA.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServiceRol {
    
    @Autowired
    private IRepositoryRol iRepositoryRol;
    
    
     public Result GetAllRol(){
        Result result = new Result();
        try{
            result.correct = true;
            result.object = iRepositoryRol.findAll();
            result.Status=200;
            
        }catch(Exception ex){          
            result.ex = ex;
            result.errorMessage = ex.getLocalizedMessage();
            result.correct = false;
            result.Status=500;
        }
        return result;
    }
        
    
    
}
