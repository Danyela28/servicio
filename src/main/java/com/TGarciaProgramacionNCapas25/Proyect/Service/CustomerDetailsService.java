
package com.TGarciaProgramacionNCapas25.Proyect.Service;

import com.TGarciaProgramacionNCapas25.Proyect.DAO.IRepositoryUsuario;
import com.TGarciaProgramacionNCapas25.Proyect.JPA.Usuario;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomerDetailsService implements UserDetailsService{
    
    private final IRepositoryUsuario iRepositoryUsuario;
    
    public CustomerDetailsService(IRepositoryUsuario iRepositoryUsuario){
        this.iRepositoryUsuario = iRepositoryUsuario;
    }
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
        Usuario usuario = iRepositoryUsuario.findByUserName(username);
        
        return User.withUsername(usuario.getUserName())
                .password(usuario.getPassword())
                .roles(usuario.Rol.getNombre())
                .accountLocked(!(usuario.getStatus() == 1))
                .disabled(!(usuario.getStatus() == 1))
                .build();
    }
    
}
