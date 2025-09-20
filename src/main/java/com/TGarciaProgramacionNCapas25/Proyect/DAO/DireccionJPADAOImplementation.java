
package com.TGarciaProgramacionNCapas25.Proyect.DAO;

import com.TGarciaProgramacionNCapas25.Proyect.JPA.Colonia;
import com.TGarciaProgramacionNCapas25.Proyect.JPA.Direccion;
import com.TGarciaProgramacionNCapas25.Proyect.JPA.Result;
import com.TGarciaProgramacionNCapas25.Proyect.JPA.Usuario;
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
    public Result Update(Usuario usuario) {
        Result result = new Result();

        try {
            Direccion direccionBD = entityManager.find(Direccion.class, usuario.Direcciones.get(0).getIdDireccion());
            if (direccionBD != null) {
                Direccion direccion = usuario.Direcciones.get(0);

                Usuario usuarioRef = entityManager.getReference(Usuario.class, usuario.getIdUsuario());
                Colonia coloniaRef = entityManager.getReference(Colonia.class, direccion.colonia.getIdColonia());

                direccionBD.setCalle(direccion.getCalle());
                direccionBD.setNumeroInterior(direccion.getNumeroInterior());
                direccionBD.setNumeroExterior(direccion.getNumeroExterior());
                direccionBD.Usuario = usuarioRef;
                direccionBD.colonia = coloniaRef;
                entityManager.merge(direccionBD);
                result.correct = true;
                result.Status = 200;
            } else {
                result.Status = 400;
                result.errorMessage = "Direccion no existe";
            }

        } catch (Exception ex) {
            result.correct = false;
            result.errorMessage = ex.getLocalizedMessage();
            result.ex = ex;
        }
        return result;

    }
    
    @Transactional
    @Override
    public Result Delete(int IdDireccion) {
        
        Result result = new Result();
        
        try{
            Direccion direccion = entityManager.find(Direccion.class, IdDireccion);
           
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
    public Result GetById(int IdDireccion) {
        Result result = new Result();
        
        try{
            Direccion direccion = entityManager.find(Direccion.class, IdDireccion);

            result.object=direccion;
            result.correct=true;
            
        }catch(Exception ex){
            result.correct=false;
            result.errorMessage=ex.getLocalizedMessage();
            result.ex=ex;
        }
        return result;
    }

    @Transactional
    @Override
    public Result Add(Usuario usuario) {

        Result result = new Result();
        try {
            Direccion direccion = usuario.Direcciones.get(0);

            Usuario usuarioRef = entityManager.getReference(Usuario.class, usuario.getIdUsuario());
            Colonia coloniaRef = entityManager.getReference(Colonia.class, direccion.colonia.getIdColonia());

            Direccion direccionBD = new Direccion();
            direccionBD.setCalle(direccion.getCalle());
            direccionBD.setNumeroInterior(direccion.getNumeroInterior());
            direccionBD.setNumeroExterior(direccion.getNumeroExterior());
            direccionBD.Usuario = usuarioRef;
            direccionBD.colonia = coloniaRef;

            entityManager.persist(direccionBD);
            result.correct = true;
            result.Status = 200;
        } catch (Exception ex) {
            result.Status = 500;
            result.correct = false;
            result.ex = ex;
            result.errorMessage = ex.getLocalizedMessage();
        }
        return result;
    }
}
