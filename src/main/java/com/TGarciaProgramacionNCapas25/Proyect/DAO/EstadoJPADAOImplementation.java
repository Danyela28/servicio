
package com.TGarciaProgramacionNCapas25.Proyect.DAO;


import com.TGarciaProgramacionNCapas25.Proyect.JPA.Estado;
import com.TGarciaProgramacionNCapas25.Proyect.JPA.Result;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class EstadoJPADAOImplementation implements IEstadoJPADAO {
    
    @Autowired
    private EntityManager entityManager;

    @Override
    public Result EstadoByPais(int IdPais) {
        
        Result result = new Result();
        
        try{
            TypedQuery<Estado> query = entityManager.createQuery(
                    "FROM Estado WHERE Estado.Pais.IdPais = :IdPais", Estado.class);
            query.setParameter("IdPais", IdPais);
            
            List<Estado> estados = query.getResultList();
            result.objects = new ArrayList<>();
            
            for(Estado estado : estados){
                result.objects.add(estado);
            }
            System.out.println(result.objects.size());
            result.correct=true;
            
            
        }catch(Exception ex){
            result.correct=false;
            result.errorMessage=ex.getLocalizedMessage();
            result.ex=ex;
        }
        return result;
    }
    
}
