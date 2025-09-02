
package com.TGarciaProgramacionNCapas25.Proyect.DAO;

import com.TGarciaProgramacionNCapas25.Proyect.JPA.Result;
import com.TGarciaProgramacionNCapas25.Proyect.JPA.Usuario;

public interface IUsuarioJPADAO {
    
    Result GetAll();
    Result Add(Usuario usuario);
//    Result Delete(int IdUsuario);
//    Result UpDate(com.TGarciaProgramacionNCapas25.Proyect.ML.Usuario usuarioML);
//    Result GetById(int IdUsuario);
}
