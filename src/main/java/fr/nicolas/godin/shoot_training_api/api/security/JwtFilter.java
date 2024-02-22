package fr.nicolas.godin.shoot_training_api.api.security;

import fr.nicolas.godin.shoot_training_api.api.enums.CustomExceptionMessage;
import fr.nicolas.godin.shoot_training_api.configuration.CustomException;
import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.CredentialsExpiredException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.web.filter.OncePerRequestFilter;
import java.io.IOException;
@Service
public class JwtFilter extends OncePerRequestFilter {

    private final JwtService jwtService;

    public JwtFilter(JwtService jwtService) {
        this.jwtService = jwtService;
    }

    /**
     * Filtre la requete entrante pour recuperer le token passer dans le header
     * @param request requete
     * @param response response
     * @param filterChain filterChain
     * @throws ServletException ServletException
     * @throws IOException IOException
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {


            final String authorization = request.getHeader("Authorization");
            String token = null;
            UserDetails user = null;
            boolean isTokenExpired = true;
            //Si le header est vide ne laisse pas continuer la methode
            if (authorization != null && authorization.startsWith("Bearer ")){
                token = authorization.substring(7);
                isTokenExpired = this.jwtService.verifyTokenValidity(token);
                user = this.jwtService.getUserWithTokenPayload(token);

            }
            //Si le token est exprirer ne continue pas le filtre
            if (!isTokenExpired && user != null && SecurityContextHolder.getContext().getAuthentication() == null){

                UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(user,null,user.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);

            }


        filterChain.doFilter(request,response);

    }
}
