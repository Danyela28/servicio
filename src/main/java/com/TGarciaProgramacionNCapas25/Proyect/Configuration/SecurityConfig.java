
package com.TGarciaProgramacionNCapas25.Proyect.Configuration;

import com.TGarciaProgramacionNCapas25.Proyect.Component.JwtFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    
    private final JwtFilter jwtFilter;

    public SecurityConfig(JwtFilter jwtFilter) {
        this.jwtFilter = jwtFilter;
    }
    
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity  http)throws Exception{
        return http.csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                .requestMatchers("/auth/**").permitAll()
                .requestMatchers("/api/publico").permitAll()
                        
                //Carga Masiva
                .requestMatchers("/usuario/cargamasiva").hasAnyRole("Administrador", "Editador")
                        
                //USUARIO
                .requestMatchers(HttpMethod.POST, "/api/usuario/**").hasRole("Administrador")
                .requestMatchers(HttpMethod.PUT, "/api/usuario/**").hasAnyRole("Administrador", "Editador")
                .requestMatchers(HttpMethod.DELETE, "/api/usuario/**").hasRole("Administrador")
                .requestMatchers(HttpMethod.GET, "/api/usuario/**").hasAnyRole("Administrador", "Editador", "Estandar", "Lector")
                
                //Roles
                .requestMatchers(HttpMethod.GET, "/api/Rol/**").hasAnyRole("Administrador", "Editador", "Estandar", "Lector")
                
                //Pais
                .requestMatchers(HttpMethod.GET, "/api/pais/**").hasAnyRole("Administrador", "Editador", "Estandar", "Lector")
                //Estado
                .requestMatchers(HttpMethod.GET, "/api/Estado/**").hasAnyRole("Administrador", "Editador", "Estandar", "Lector")
                        //DIREC
                .requestMatchers(HttpMethod.GET, "/api/Direccion/**").hasAnyRole("Asministrador", "Editador", "Estandar", "Lector")
                .requestMatchers(HttpMethod.DELETE, "/api/Direccion/**").hasRole("Administrador")
                .requestMatchers(HttpMethod.POST, "/api/Direccion/**").hasAnyRole("Administrador", "Editador")
                .anyRequest().authenticated()
                )
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }
    
    @Bean
    AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception{
        return config.getAuthenticationManager(); 
    }
    
    
        
    
}
