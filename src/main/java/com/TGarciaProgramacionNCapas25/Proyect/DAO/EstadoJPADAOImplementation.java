
package com.TGarciaProgramacionNCapas25.Proyect.DAO;


import com.TGarciaProgramacionNCapas25.Proyect.JPA.Estado;
import com.TGarciaProgramacionNCapas25.Proyect.JPA.Result;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class EstadoJPADAOImplementation implements IEstadoJPADAO {
    
    @Autowired
    private EntityManager entityManager;
    
    @Override
    public Result GetAll() {
        Result result = new Result();

        try {
            TypedQuery<Estado> queryEstado
                    = entityManager.createQuery("FROM estado", Estado.class);
            List<Estado> estados = queryEstado.getResultList();
            result.objects = new ArrayList<>();
            for (Estado estado : estados) {
                result.objects.add(estado);
            }
            result.Status = 200;
            result.correct = true;
        } catch (Exception ex) {
            result.ex = ex;
            result.errorMessage = ex.getLocalizedMessage();
            result.correct = false;
            result.Status = 500;

        }
        return result;
    }
    @Transactional
    @Override
    public Result GetByIdPais(int IdPais) {
        Result result = new Result();

        try {
            TypedQuery<Estado>queryEstado=entityManager.createQuery("FROM Estado WHERE Pais.IdPais = :idPais",
                    Estado.class);
            queryEstado.setParameter("idPais", IdPais);

                            result.object = queryEstado.getResultList();

            result.correct = true;

        } catch (Exception ex) {
            result.correct = false;
            result.errorMessage = ex.getLocalizedMessage();
            result.ex = ex;
        }
        return result;
    }

}


