
package com.TGarciaProgramacionNCapas25.Proyect.DAO;

import com.TGarciaProgramacionNCapas25.Proyect.JPA.Direccion;
import com.TGarciaProgramacionNCapas25.Proyect.JPA.Result;



public interface IDireccionJPADAO {
    
    Result UpDate(Direccion direccion);
    Result Delete(Long idDireccion);
    Result GetById(Long idDireccion);
    Result Add(Direccion direccion);
}
