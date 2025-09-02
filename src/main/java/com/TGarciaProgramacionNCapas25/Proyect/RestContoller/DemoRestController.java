
package com.TGarciaProgramacionNCapas25.Proyect.RestContoller;

import com.TGarciaProgramacionNCapas25.Proyect.JPA.Usuario;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("demo")
public class DemoRestController {
    
    
    
    @GetMapping("/suma")
    public String suma(@RequestParam int numeroA, @RequestParam int numeroB){
        return "La suma es " +(numeroA + numeroB);
    }
    
    @PostMapping("/sumaArreglo")
    public String sumaArreglo(@RequestBody List<Integer> numeros) {
    
        int sumaTotal = 0;
   
        for (Integer numero : numeros) {
            sumaTotal += numero;
        }
        
        return "La suma de los elementos del arreglo es: " + sumaTotal;
    }
    
    @PostMapping("/saludoInfo")
    public String saludoInfo(@RequestBody Usuario usuario) {
    
    
    
   
    String infoUsuario = "Apellido Paterno: " + usuario.getApellidoPaterno() + "\n "
                       + "Apellido Materno: " + usuario.getApellidoMaterno() + "\n "
                       + "CURP: " + usuario.getCURP() + "\n "
                       + "Celular: " + usuario.getCelular() + "\n  "
                       + "Teléfono: " + usuario.getTelefono() + "\n "
                       + "Email: " + usuario.getEmail() + "\n"
                       + "Sexo: " + usuario.getSexo() + "\n  "
                       + "Usuario: " + usuario.getUserName();
                       
    

    return "Hola " + usuario.getNombre() + "\n" + " tus datos son los siguientes:" +"\n" +  infoUsuario;
}
    
    @PostMapping("/Saludo")
    public String Saludo(@RequestBody Usuario usuario){
        return "Hola " + usuario.getNombre();
    }
    
    
    @PatchMapping("/actualizaNombre/{posicion}/{nuevoNombre}")
    public String actualizaNombre(
            @RequestBody List<String> nombres,
            @PathVariable int posicion,
            @PathVariable String nuevoNombre) {
        
        
        nombres.set(posicion, nuevoNombre);
        
      
        return nombres.toString();
    }
}
