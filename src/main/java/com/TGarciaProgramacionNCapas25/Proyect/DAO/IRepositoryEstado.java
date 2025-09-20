
package com.TGarciaProgramacionNCapas25.Proyect.DAO;

import com.TGarciaProgramacionNCapas25.Proyect.JPA.Estado;
import org.springframework.data.jpa.repository.JpaRepository;


public interface IRepositoryEstado extends JpaRepository<Estado, Integer> {
    
}
