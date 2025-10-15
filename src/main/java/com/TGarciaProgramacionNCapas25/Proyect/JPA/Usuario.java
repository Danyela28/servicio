
package com.TGarciaProgramacionNCapas25.Proyect.JPA;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.ArrayList;
import java.util.Date;  
import java.util.List;


@Entity
public class Usuario {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="idusuario")
    private int IdUsuario;
    @Column(name="nombre")
    private String Nombre;
    @Column(name="apellidomaterno")
    private String ApellidoMaterno;
    @Column(name="apellidopaterno")
    private String ApellidoPaterno;
    @Column(name="fechanacimiento")
    @JsonFormat(pattern = "dd-MM-yyyy")
    private Date FechaNacimiento;
    @Column(name="celular")
    private String Celular;
    @Column(name="username", nullable = false, unique = true)
    private String userName;
    @Column(name="email")
    private String Email;
    @Column(name="password")
    private String Password;
    @Column(name="sexo")
    private String Sexo;
    @Column(name="telefono")
    private String Telefono;
    @Column(name="curp")
    private String CURP;
    @Lob
    @Column( name="imagen")
    private String Imagen;
    @Column(name = "status")
    private int Status = 1;
    @ManyToOne
    @JoinColumn (name="idrol")
    public Rol Rol;
    
    
    @OneToMany(mappedBy="Usuario", cascade = CascadeType.ALL, orphanRemoval = true)
    public List<Direccion>Direcciones = new ArrayList<>();
   
            
    public Usuario(){}
    
    public Usuario(String Nombre, String ApellidoPaterno, String ApellidoMaterno, Date FechaNacimiento, String Celular, String UserName, 
            String Email, String Password, String Sexo, String Telefono, String CURP, String Imagen, int Status, Rol rol){
        this.Nombre=Nombre;
        this.ApellidoPaterno=ApellidoPaterno;
        this.ApellidoMaterno=ApellidoMaterno;
        this.FechaNacimiento=FechaNacimiento;
        this.Celular=Celular;
        this.userName=UserName;
        this.Email=Email;
        this.Sexo=Sexo;
        this.Password=Password;
        this.Telefono=Telefono;
        this.CURP=CURP;
        this.Imagen=Imagen;
        this.Status= Status;
        this.Rol= rol;
    }
    
    
    public void setIdUsuario(int idUsuario){
        this.IdUsuario=idUsuario;   
    } 
    public int getIdUsuario(){
        return this.IdUsuario;
    }

    public void setNombre(String nombre){
        this.Nombre=nombre;
    }
    public String getNombre(){
        return Nombre;
    }
    public void setApellidoMaterno(String apellidoMaterno){
        this.ApellidoMaterno=apellidoMaterno;
    }
    public String getApellidoMaterno(){
        return ApellidoMaterno;
    }
    public void setApellidoPaterno( String apellidoPaterno){
        this.ApellidoPaterno=apellidoPaterno;
    }
    public String getApellidoPaterno(){
        return ApellidoPaterno;
    }
    public void setFechaNacimiento(Date fechaNacimiento){
        this.FechaNacimiento = fechaNacimiento;  
    }

    public Date getFechaNacimiento(){
        return FechaNacimiento;
    }
    

    public String getCelular() {
        return Celular;
    }

    public void setCelular(String Celular) {
        this.Celular = Celular;
    }
    public String getUserName(){
        return userName;
    }
    public void setUserName(String UserName){
        this.userName = UserName;
    }
    public String getEmail(){
        return Email;
    }
    public void setEmail(String email){
        this.Email = email;
    }
    public String getPassword(){
        return Password;
    }
    public void setPassword(String password){
        this.Password = password;
    }
    public String getSexo(){
        return Sexo;
    }
    public void setSexo(String sexo){
        this.Sexo = sexo;
    }
    public String getTelefono(){
        return Telefono;
    }
    public void setTelefono(String telefono){
        this.Telefono = telefono;          
    }
    public String getCURP(){
        return CURP;
    }
    public void setCURP(String curp){
        this.CURP = curp;
    }


    public String getImagen() {
        return Imagen;
    }

    public void setImagen(String Imagen) {
        this.Imagen = Imagen;
    }

    public int getStatus() {
        return Status;
    }

    public void setStatus(int Status) {
        this.Status = Status;
    }

//    public List<Direccion> getDirecciones() {
//        return Direcciones;
//    }
//
//    public void setDirecciones(List<Direccion> Direcciones) {
//        this.Direcciones = Direcciones;
//    }
}
