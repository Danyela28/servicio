
package com.TGarciaProgramacionNCapas25.Proyect.DAO;

import com.TGarciaProgramacionNCapas25.Proyect.JPA.Municipio;
import com.TGarciaProgramacionNCapas25.Proyect.JPA.Result;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class MunicipioJPADAOImplementation implements IMunicipioJPADAO {
    
    @Autowired
    private EntityManager entityManager;

    @Override
    public Result MunicipioByEstado(int IdEstado) {
        Result result = new Result();
        
        try{
            TypedQuery<Municipio> query = entityManager.createQuery(
                    "FROM Municipio WHERE Municipio.Estado.IdEstado = :IdEstado", Municipio.class);
           query.setParameter("IdEstado", IdEstado);
            
            List<Municipio> municipios = query.getResultList();
            result.objects = new ArrayList<>();
            
            for(Municipio municipio : municipios){
                result.objects.add(municipio);
            }
            System.out.println(result.objects.size());
            result.correct=true;
            
        }catch(Exception ex){
            result.correct= false;
            result.errorMessage=ex.getLocalizedMessage();
            result.ex= ex;
        }
        return result;
    
    }
    
}
