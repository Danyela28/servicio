package com.TGarciaProgramacionNCapas25.Proyect.DAO;

import com.TGarciaProgramacionNCapas25.Proyect.JPA.Direccion;
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

        try {
            entityManager.persist(usuario);
            result.object = usuario;

            Direccion dire = usuario.Direcciones.get(0);
            dire.Usuario = new Usuario();
            dire.Usuario.setIdUsuario(usuario.getIdUsuario());
            entityManager.persist(dire);

            result.correct = true;

        } catch (Exception ex) {
            result.correct = false;
            result.errorMessage = ex.getLocalizedMessage();
            result.ex = ex;
        }
        return result;

    }

    @Transactional
    @Override
    public Result Delete(Long id) {
        Result result = new Result();

        try {
            Usuario usuario = entityManager.find(Usuario.class, id);

            entityManager.remove(usuario);
            result.object = usuario;

            result.correct = true;

        } catch (Exception ex) {
            result.correct = false;
            result.errorMessage = ex.getLocalizedMessage();
            result.ex = ex;
        }
        return result;
    }

    @Transactional
    @Override
    public Result Update(Usuario usuario) {

        Result result = new Result();

        try {

            Usuario usuarioBD = entityManager.find(Usuario.class, usuario.getIdUsuario());

            if (usuarioBD != null) {
                    usuarioBD.setNombre(usuario.getNombre());
                    usuarioBD.setApellidoPaterno(usuario.getApellidoPaterno());
                    usuarioBD.setApellidoMaterno(usuario.getApellidoMaterno());
                    usuarioBD.setCURP(usuario.getCURP());
                    usuarioBD.setCelular(usuario.getCelular());
                    usuarioBD.setTelefono(usuario.getTelefono());
                    usuarioBD.setEmail(usuario.getEmail());
                    usuarioBD.setFechaNacimiento(usuario.getFechaNacimiento());
                    usuarioBD.setSexo(usuario.getSexo());
                    usuarioBD.setUserName(usuario.getUserName());
                    usuarioBD.setImagen(usuario.getImagen());
                    
                    entityManager.merge(usuarioBD);
                    result.correct = true;
                    result.object = usuarioBD;
                    result.Status=200;
                    
                }else{
                    result.errorMessage="Usuario no encontrado, id incorrecto";
                    result.Status =400;
                }
            }catch(Exception ex){
                result.correct=false;
                result.errorMessage= ex.getLocalizedMessage();
                result.ex = ex;
                result.Status=500;
            }
            return result;
        }
    

    @Override
    public Result GetById(int id) {
        Result result = new Result();

        try {

            Usuario usuario = entityManager.find(Usuario.class, id);
            result.object = usuario;
            result.correct = true;

        } catch (Exception ex) {
            result.correct = false;
            result.errorMessage = ex.getLocalizedMessage();
            result.ex = ex;
        }
        return result;
    }

}
