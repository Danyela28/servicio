package com.TGarciaProgramacionNCapas25.Proyect.DAO;

import com.TGarciaProgramacionNCapas25.Proyect.JPA.Usuario;
import com.TGarciaProgramacionNCapas25.Proyect.JPA.Result;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UsuarioJPADAOImplementation implements IUsuarioJPADAO {

    @Autowired
    private EntityManager entityManager;

    @Override
    public Result GetAll() {

        Result result = new Result();

        try {
            TypedQuery<Usuario> queryUsuario = entityManager.createQuery("FROM Usuario ORDER BY IdUsuario", Usuario.class);
            result.object = queryUsuario.getResultList();

            result.correct = true;

        } catch (Exception ex) {
            System.out.println(ex.getLocalizedMessage());
            result.correct = false;
            result.errorMessage = ex.getLocalizedMessage();
            result.ex = ex;
        }
        return result;

    }
    @Transactional
    @Override
    public Result Add(Usuario usuario) {
        Result result = new Result();
        
        try{
            entityManager.persist(usuario);
            result.object= usuario;
            result.correct=true;
            
        }catch (Exception ex){
            result.correct = false;
            result.errorMessage=ex.getLocalizedMessage();
            result.ex=ex;
        }
        return result;
        
    }
    
//    @Transactional
//    @Override
//    public Result Delete(int IdUsuario){
//        Result result = new Result();
//        
//        try{
//            Usuario usuarioJPA = entityManager.find(Usuario.class, IdUsuario);
//            entityManager.remove(usuarioJPA);
//            
//            result.correct=true;
//            
//        }catch(Exception ex){
//            result.correct=false;
//            result.errorMessage=ex.getLocalizedMessage();
//            result.ex=ex;
//        }
//        return result;
//    }
//    @Transactional
//    @Override
//    public Result UpDate(com.TGarciaProgramacionNCapas25.Proyect.ML.Usuario usuarioML) {
//        
//        Result result = new Result();
//        
//        try{
//            Usuario usuarioJPA = new Usuario(usuarioML);
//            Usuario usuarioBD = entityManager.find(Usuario.class, usuarioML.getIdUsuario());
//            
//            usuarioJPA.Direcciones = usuarioBD.Direcciones;
//            
//            entityManager.merge(usuarioJPA);
//            
//            result.correct=true;
//            
//        }catch(Exception ex){
//            result.correct=false;
//            result.errorMessage= ex.getLocalizedMessage();
//            result.ex = ex;
//        }
//        return result;
//    }
//    
//    @Override
//    public Result GetById(int IdUsuario){
//        Result result = new Result();
//        
//        try{
//            
//            Usuario usuarioJPA = entityManager.find(Usuario.class, IdUsuario);
//            com.TGarciaProgramacionNCapas25.Proyect.ML.Usuario usuarioML = new com.TGarciaProgramacionNCapas25.Proyect.ML.Usuario(usuarioJPA);
//            result.object = usuarioML;
//            result.correct=true;
//            
//        }catch(Exception ex){
//            result.correct=false;
//            result.errorMessage=ex.getLocalizedMessage();
//            result.ex=ex;
//        }
//        return result;
//  }
    
}
