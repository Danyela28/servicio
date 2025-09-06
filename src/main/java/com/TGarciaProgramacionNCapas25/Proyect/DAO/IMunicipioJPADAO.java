
package com.TGarciaProgramacionNCapas25.Proyect.DAO;

import com.TGarciaProgramacionNCapas25.Proyect.JPA.Result;


public interface IMunicipioJPADAO {
    
    Result GetAll();
    Result GetByEstado(int IdEstado);
    
}
