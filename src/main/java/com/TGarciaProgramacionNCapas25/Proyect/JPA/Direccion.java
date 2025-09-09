
package com.TGarciaProgramacionNCapas25.Proyect.JPA;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;


@Entity
public class Direccion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="iddireccion")
    private int IdDireccion;
    @Column(name="calle")
    private String Calle;
    @Column(name="numerointerior")
    private String NumeroInterior;
    @Column(name="numeroexterior")
    private String NumeroExterior;
    @ManyToOne
    @JoinColumn(name="idcolonia")
    public Colonia colonia;
    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="idusuario", nullable = false)
    public Usuario Usuario;

   
    public Direccion(){}
    public Direccion(Usuario usuarioJPA){}
   
  
    public void actualizarDatos(String calle, String numeroInterior, String numeroExterior, Usuario usuario, Colonia colonia) {
        this.Calle = calle;
        this.NumeroInterior = numeroInterior;
        this.NumeroExterior = numeroExterior;
        this.Usuario = usuario; 
        this.colonia = colonia; 
    }
    public Direccion(int IdDireccion) {
        this.IdDireccion = IdDireccion;
    }
    
    public int getIdDireccion() {
        return IdDireccion;
    }

    public void setIdDireccion(int IdDireccion) {
        this.IdDireccion = IdDireccion;
    }

    public String getCalle() {
        return Calle;
    }

    public void setCalle(String Calle) {
        this.Calle = Calle;
    }

    public String getNumeroInterior() {
        return NumeroInterior;
    }

    public void setNumeroInterior(String NumeroInterior) {
        this.NumeroInterior = NumeroInterior;
    }

    public String getNumeroExterior() {
        return NumeroExterior;
    }

    public void setNumeroExterior(String NumeroExterior) {
        this.NumeroExterior = NumeroExterior;
    }

    

  
}
