
package com.TGarciaProgramacionNCapas25.Proyect.DAO;

import com.TGarciaProgramacionNCapas25.Proyect.JPA.Direccion;
import com.TGarciaProgramacionNCapas25.Proyect.JPA.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;


public interface IRepositoryDireccion extends JpaRepository<Direccion, Integer>{

    public Object save(Usuario usuario);
    
}
