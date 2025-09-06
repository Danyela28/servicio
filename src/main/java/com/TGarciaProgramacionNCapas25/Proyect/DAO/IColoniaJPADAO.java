
package com.TGarciaProgramacionNCapas25.Proyect.DAO;

import com.TGarciaProgramacionNCapas25.Proyect.JPA.Result;



public interface IColoniaJPADAO {
    
    Result GetAll();
    Result GetByIdMunicipio(int IdMunicipio);
    
    
}
