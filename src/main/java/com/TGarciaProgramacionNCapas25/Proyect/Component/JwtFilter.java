
package com.TGarciaProgramacionNCapas25.Proyect.Component;

import com.TGarciaProgramacionNCapas25.Proyect.Service.CustomerDetailsService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import jakarta.servlet.FilterChain;
import jakarta.servlet.GenericFilter;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@Component
public class JwtFilter  extends GenericFilter{
    
    private final JwtUtil jwtUtil;
    private final CustomerDetailsService userDetailsService;
    
    public JwtFilter(JwtUtil jwtUtil, CustomerDetailsService uds){
        this.jwtUtil = jwtUtil;
        this.userDetailsService = uds; 
    }
    
    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException{
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response= (HttpServletResponse) res;
        String header = request.getHeader("Authorization");
        
        if (header !=null && header.startsWith("Bearer")){
            String token = header.substring(7);
            try{
                Jws<Claims> claims = jwtUtil.validateToken(token);
                
                if(!jwtUtil.checkTokenUsage(claims)){
                    response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                    response.getWriter().write("Token excedio el numero de usos permitidos");
                    return;
                }
                
                UserDetails userDetails = userDetailsService.loadUserByUsername(claims.getBody().getSubject());
                UsernamePasswordAuthenticationToken auth
                        =new UsernamePasswordAuthenticationToken(userDetails,null, userDetails.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(auth);
            }catch(Exception ignored){
                
            }
        }
        chain.doFilter(req, res);
    }

    
    
}
