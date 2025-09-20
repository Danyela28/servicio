
package com.TGarciaProgramacionNCapas25.Proyect.JPA;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.List;


public class Result {
    public boolean correct;
    public String errorMessage;
    public Exception ex;
    public Object object;
    public List<Object> objects;
    @JsonIgnore
    public int Status;
    
    public List<ErrorCM> errores;
    public Boolean archivoCorrecto;
    public String path;
    
}
