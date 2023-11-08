package africa.semicolon.gemstube.config.security;


import africa.semicolon.gemstube.config.security.filters.GemsTubeAuthenticationFilter;
import africa.semicolon.gemstube.config.security.filters.GemsTubeAuthorizationFilter;
import africa.semicolon.gemstube.config.security.services.JwtService;
import africa.semicolon.gemstube.models.Authority;
import africa.semicolon.gemstube.repositories.UserRepository;
import africa.semicolon.gemstube.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;

import java.util.List;

import static org.hibernate.cfg.JdbcSettings.USER;

@Configuration
@AllArgsConstructor
public class SecurityConfig {

    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final GemsTubeAuthorizationFilter authorizationFilter;



    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http.csrf(c->c.disable())
                .sessionManagement(c->c.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                   .cors(httpSecurityCorsConfigurer -> {
                        CorsConfiguration corsConfiguration = new CorsConfiguration();
                        corsConfiguration.setAllowedMethods(List.of("POST", "PUT", "GET"));
                        corsConfiguration.setAllowedOrigins(List.of("*"));
                   })
                   .addFilterAt(new GemsTubeAuthenticationFilter(authenticationManager, jwtService), UsernamePasswordAuthenticationFilter.class)
                   .addFilterBefore(authorizationFilter, GemsTubeAuthenticationFilter.class)
                   .authorizeHttpRequests(c->c.requestMatchers(HttpMethod.POST, "/api/v1/user", "/login").permitAll()
                           .requestMatchers(HttpMethod.GET, "/api/v1/user").hasAnyAuthority(Authority.USER.name()))
                   .build();
    }
}
