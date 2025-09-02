
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
    public Result UpDate(com.TGarciaProgramacionNCapas25.Proyect.JPA.Usuario usuarioJPA) {
        
//        Result result = new Result();
//        
//        try{
//            Direccion direccionJPA = new Direccion(usuarioML);
//            entityManager.merge(direccionJPA);
//            result.correct = true;
//            
//        }catch(Exception ex){
//            result.correct=false;
//            result.errorMessage=ex.getLocalizedMessage();
//            result.ex= ex;
//        }
        return null;
        
    }
    
//    @Transactional
//    @Override
//    public Result Delete(int IdDireccion) {
//        
//        Result result = new Result();
//        
//        try{
//            Direccion direccionJPA = entityManager.find(Direccion.class, IdDireccion);
//            entityManager.remove(direccionJPA);
//            
//            result.correct=true;
//            
//        }catch(Exception ex){
//            result.correct=false;
//            result.errorMessage=ex.getLocalizedMessage();
//            result.ex=ex;
//        }
//        
//        return result;
//    }
//
//    @Override
//    public Result GetById(int IdDireccion) {
//        Result result = new Result();
//        
//        try{
//            Direccion direccionJPA = entityManager.find(Direccion.class, IdDireccion);
//            com.TGarciaProgramacionNCapas25.Proyect.ML.Direccion direccionML = new com.TGarciaProgramacionNCapas25.Proyect.ML.Direccion(direccionJPA);
//            com.TGarciaProgramacionNCapas25.Proyect.ML.Usuario usuarioML = new com.TGarciaProgramacionNCapas25.Proyect.ML.Usuario();
//            
//            usuarioML.Direcciones.add(direccionML);
//            
//            result.object=direccionML;
//            result.correct=true;
//            
//        }catch(Exception ex){
//            result.correct=false;
//            result.errorMessage=ex.getLocalizedMessage();
//            result.ex=ex;
//        }
//        return result;
//    }
    
    
}
