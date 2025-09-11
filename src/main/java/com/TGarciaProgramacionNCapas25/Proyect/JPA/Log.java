
package com.TGarciaProgramacionNCapas25.Proyect.JPA;

import java.io.ObjectInputFilter.Status;


public class Log {
    
    private String rutaCifrada;
    private String archivo;
    private Status status;
    private String fecha;
    private String observacion;
    
    public Log(String rutaCifrada, String archivo, Status status, String fecha, String observacion ){
        this.rutaCifrada = rutaCifrada;
        this.archivo = archivo;
        this.fecha = fecha;
        this.observacion= observacion;
        this.status = status;
    }

    public String getRutaCifrada() {
        return rutaCifrada;
    }

    public void setRutaCifrada(String rutaCifrada) {
        this.rutaCifrada = rutaCifrada;
    }

    public String getArchivo() {
        return archivo;
    }

    public void setArchivo(String archivo) {
        this.archivo = archivo;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }
    
}
