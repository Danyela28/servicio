package com.TGarciaProgramacionNCapas25.Proyect.RestControllerRespository;

import com.TGarciaProgramacionNCapas25.Proyect.Component.JwtUtil;
import com.TGarciaProgramacionNCapas25.Proyect.DAO.IRepositoryUsuario;
import com.TGarciaProgramacionNCapas25.Proyect.JPA.Rol;
import com.TGarciaProgramacionNCapas25.Proyect.JPA.Usuario;
import java.util.HashMap;
import java.util.Map;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("auth")
public class AuthRestController {

    private final IRepositoryUsuario iRepositoryUsuario;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public AuthRestController(IRepositoryUsuario iRepositoryUsuario, PasswordEncoder passwordEncoder, JwtUtil jwtUtil) {
        this.iRepositoryUsuario = iRepositoryUsuario;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/signup")
    public Usuario signup(@RequestBody Usuario usuario) {
        usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
        usuario.Rol = new Rol();
        return iRepositoryUsuario.save(usuario);

    }

    @PostMapping("/login")
    public Map<String, Object> login(@RequestBody Usuario usuario) {
        System.out.println("Login con usuario: " + usuario.getUserName());
        
        Usuario dbUser = iRepositoryUsuario.findByUserName(usuario.getUserName());
        if(dbUser == null){
            System.out.println("Usuario no encontrado en la BD");
            throw new RuntimeException("Usuario no encontrado");
        }
        System.out.println("Usuario encontrado:" + dbUser.getUserName());
        System.out.println("Contraseña enviada:" + usuario.getPassword());
        System.out.println("Contraseña enviada: "+ dbUser.getPassword());
        System.out.println("Resultado: " + passwordEncoder.matches(usuario.getPassword(), dbUser.getPassword()));
        
        if(!passwordEncoder.matches(usuario.getPassword(), dbUser.getPassword())){
            throw new RuntimeException("Contraseña incorrectas");
        }
        String token = jwtUtil.generateToken(dbUser.getUserName(), dbUser.Rol.getNombre());
        
        Map<String, Object>response = new HashMap<>();
        response.put("token", token);
        response.put("idUsuario", dbUser.getIdUsuario());
        response.put("rol", dbUser.Rol.getNombre());
        return response;
        
//        if (passwordEncoder.matches(usuario.getPassword(), dbUser.getPassword())) {
//            String token = jwtUtil.generateToken(dbUser.getUserName(), dbUser.Rol.getNombre());
//            
//            Map<String, Object> response = new HashMap<>();
//            response.put("token", token);
//            response.put("idUsuario", dbUser.getIdUsuario());
//            response.put("rol", dbUser.Rol.getNombre());
//            return response;
//        }
//        throw new RuntimeException("Credenciales invalidas");
    }

}
