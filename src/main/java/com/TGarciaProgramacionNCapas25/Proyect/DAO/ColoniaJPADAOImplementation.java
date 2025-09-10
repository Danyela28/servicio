
package com.TGarciaProgramacionNCapas25.Proyect.DAO;


import com.TGarciaProgramacionNCapas25.Proyect.JPA.Colonia;
import com.TGarciaProgramacionNCapas25.Proyect.JPA.Result;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public class ColoniaJPADAOImplementation implements IColoniaJPADAO {
        


    @PersistenceContext
    private EntityManager entityManager;

     @Override
    public Result GetAll() {
        Result result = new Result();

        try {
            TypedQuery<Colonia> queryColonia
                    = entityManager.createQuery("FROM colonia", Colonia.class);
            List<Colonia> colonias = queryColonia.getResultList();
            result.objects = new ArrayList<>();
            for (Colonia colonia : colonias) {
                result.objects.add(colonia);
            }
            result.correct = true;
            result.Status = 200;

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
    public Result GetByIdMunicipio(int IdMunicipio) {
        Result result = new Result();

        try {
            TypedQuery<Colonia>queryMunicipio=entityManager.createQuery("FROM Colonia WHERE Municipio.IdMunicipio = :idMunicipio",
                    Colonia.class);
            queryMunicipio.setParameter("idMunicipio", IdMunicipio);

                            result.object = queryMunicipio.getResultList();

            result.correct = true;

        } catch (Exception ex) {
            result.correct = false;
            result.errorMessage = ex.getLocalizedMessage();
            result.ex = ex;
        }
        return result;
    }
}

        

