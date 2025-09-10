
package com.TGarciaProgramacionNCapas25.Proyect.DAO;


import com.TGarciaProgramacionNCapas25.Proyect.JPA.Municipio;
import com.TGarciaProgramacionNCapas25.Proyect.JPA.Result;
import com.TGarciaProgramacionNCapas25.Proyect.JPA.Usuario;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class MunicipioJPADAOImplementation implements IMunicipioJPADAO {
    
    @Autowired
    private EntityManager entityManager;

    @Override
    public Result GetAll() {
        Result result = new Result();

        try {
            TypedQuery<Municipio> queryMunicipio
                    = entityManager.createQuery("FROM municipio", Municipio.class);
            List<Municipio> municipios = queryMunicipio.getResultList();
            result.objects = new ArrayList<>();
            for (Municipio municipio : municipios) {
                result.objects.add(municipio);
            }
            result.correct = true;
            result.Status = 200;

        } catch (Exception ex) {
            result.Status = 500;
            result.ex = ex;
            result.errorMessage = ex.getLocalizedMessage();
            result.correct = false;
        }
        return result;
    }

    @Transactional
    @Override
    public Result GetByEstado(int IdEstado) {
        Result result = new Result();

        try {
            TypedQuery<Municipio>queryMunicipio=entityManager.createQuery("FROM Municipio WHERE Estado.IdEstado = :idEstado",
                    Municipio.class);
            queryMunicipio.setParameter("idEstado", IdEstado);

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
    

