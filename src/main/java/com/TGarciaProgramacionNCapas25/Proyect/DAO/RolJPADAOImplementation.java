
package com.TGarciaProgramacionNCapas25.Proyect.DAO;


import com.TGarciaProgramacionNCapas25.Proyect.JPA.Rol;
import com.TGarciaProgramacionNCapas25.Proyect.JPA.Result;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class RolJPADAOImplementation implements IRolJPADAO {
    
    @Autowired
    private EntityManager entityManager;
    
    

    @Override
    public Result GetAll() {
        
        Result result = new Result();
        
        try {
            TypedQuery<Rol> queryUsuario = entityManager.createQuery("FROM Rol ORDER BY IdRol", Rol.class);
            List<Rol> roles = queryUsuario.getResultList();

            result.objects = new ArrayList<>();

            for (Rol rol : roles) {
                result.objects.add(rol);
            }

            System.out.println(result.objects.size());
            result.correct = true;
        } catch (Exception ex) {
            System.out.println(ex.getLocalizedMessage());
            result.correct = false;
            result.errorMessage = ex.getLocalizedMessage();
            result.ex = ex;
        }
        return result;

    }
    
    
    
}
