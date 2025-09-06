
package com.TGarciaProgramacionNCapas25.Proyect.DAO;

import com.TGarciaProgramacionNCapas25.Proyect.JPA.Direccion;
import com.TGarciaProgramacionNCapas25.Proyect.JPA.Result;
import com.TGarciaProgramacionNCapas25.Proyect.JPA.Usuario;



public interface IDireccionJPADAO {
    
    Result Add(Usuario usuario);
    Result Update(Usuario usuario);
    Result Delete(Long idDireccion);
    Result GetById(int IdDireccion);

}
