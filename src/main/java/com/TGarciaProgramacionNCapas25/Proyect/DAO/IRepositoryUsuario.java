
package com.TGarciaProgramacionNCapas25.Proyect.DAO;

import com.TGarciaProgramacionNCapas25.Proyect.JPA.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;


public interface IRepositoryUsuario extends JpaRepository<Usuario, Integer>{
    
    Usuario findByUserName(String UserName);
}
