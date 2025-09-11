
package com.TGarciaProgramacionNCapas25.Proyect.JPA;

public enum StatusArchivo {
    Error(0, "Error"),
    PROCESAR(1, "Procesar"),
    PROCESADO(2, "Procesado");
    
    private final int code;
    private final String descripcion;
    
    StatusArchivo(int code, String descripcion){
        this.code = code;
        this.descripcion = descripcion;
    }

    public int getCode() {
        return code;
    }

    public String getDescripcion() {
        return descripcion;
    }
    
    public static StatusArchivo fromCode(int code){
        for(StatusArchivo SArchivo: values())if(SArchivo.code==code)return SArchivo;
        throw new IllegalArgumentException("Codigo invalid: o" + code);
    }
    
}
