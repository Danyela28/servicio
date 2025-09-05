
package com.TGarciaProgramacionNCapas25.Proyect.DAO;

import com.TGarciaProgramacionNCapas25.Proyect.JPA.Direccion;
import com.TGarciaProgramacionNCapas25.Proyect.JPA.Result;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;



@Repository
public class DireccionJPADAOImplementation implements IDireccionJPADAO{
    
    @Autowired
    private EntityManager entityManager;

    @Transactional
    @Override
    public Result UpDate(Direccion direccion) {
        Result result = new Result();
        
        try{
            Direccion direccionBD = entityManager.find(Direccion.class, direccion.getIdDireccion()); //direccion de la base de datos
            
            // asignando datos de direccion sobre la misma direccion, en que momento actualizas direccionBD??
            if(direccionBD !=null){
                direccionBD.setCalle(direccion.getCalle()); 
                direccionBD.setNumeroExterior(direccion.getNumeroExterior());
                direccionBD.setNumeroInterior(direccion.getNumeroInterior());
                
                entityManager.merge(direccionBD); // merge a direccion traida de la base de datos (sin ningun dato nuevo)
                result.correct = true;
                result.object = direccionBD;
                result.Status=200;
            }else{
                result.correct=false;
                result.errorMessage="Direccion no encontrada, id incorrecto";
                result.Status = 400;
            }
        }catch(Exception ex){
            result.correct=false;
            result.errorMessage=ex.getLocalizedMessage();
            result.ex=ex;
            result.Status=500;
        }
        return result;
        
    }
    
    @Transactional
    @Override
    public Result Delete(Long idDireccion) {
        
        Result result = new Result();
        
        try{
            Direccion direccion = entityManager.find(Direccion.class, idDireccion);
           
            entityManager.remove(direccion);
            result.object = direccion;
            result.correct=true;
            
        }catch(Exception ex){
            result.correct=false;
            result.errorMessage=ex.getLocalizedMessage();
            result.ex=ex;
        }
        
        return result;
    }

    @Override
    public Result GetById(Long idDireccion) {
        Result result = new Result();
        
        try{
            Direccion direccion = entityManager.find(Direccion.class, idDireccion);

            result.object=direccion;
            result.correct=true;
            
        }catch(Exception ex){
            result.correct=false;
            result.errorMessage=ex.getLocalizedMessage();
            result.ex=ex;
        }
        return result;
    }

    @Override
    public Result Add(Direccion direccion) {
        
        return null;
        
    }
    
    

}
