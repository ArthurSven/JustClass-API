package com.devapps.justclass_api.utils.security;

import lombok.RequiredArgsConstructor;
import org.springframework.web.filter.CorsFilter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.firewall.HttpFirewall;
import org.springframework.security.web.firewall.StrictHttpFirewall;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;
import jakarta.servlet.Filter;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final Filter jwtAuthFilter;
    private final AuthenticationProvider authenticationProvider;

    public SecurityConfig(@Qualifier("jwtAuthenticationFilter") Filter jwtAuthFilter,
                          AuthenticationProvider authenticationProvider) {
        this.jwtAuthFilter = jwtAuthFilter;
        this.authenticationProvider = authenticationProvider;
    }

    @Bean
    public HttpFirewall allowUrlEncodedSlashHttpFirewall() {
        StrictHttpFirewall firewall = new StrictHttpFirewall();
        firewall.setAllowUrlEncodedSlash(true); // Allow encoded slashes
        firewall.setAllowUrlEncodedPercent(true); // Allow encoded percent
        firewall.setAllowSemicolon(true); // Allow semicolons
        firewall.setAllowBackSlash(true); // Allow backslashes
        firewall.setAllowUrlEncodedDoubleSlash(true); // Allow double slashes
        firewall.setAllowUrlEncodedPeriod(true); // Allow periods
        return firewall;
    }

    @Bean
    public HttpFirewall allowSemicolonHttpFirewall() {
        StrictHttpFirewall firewall = new StrictHttpFirewall();
        firewall.setAllowSemicolon(true);
        return firewall;
    }

    @Bean
    public UrlSanitizationFilter urlSanitizationFilter() {
        return new UrlSanitizationFilter();
    }


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {

        httpSecurity
                .csrf(csrf -> csrf.disable())
                .cors(cors -> cors.configurationSource(corsConfigurationSource()))
                .authorizeHttpRequests(auth -> auth
                                .requestMatchers("/", "/auth/user/**", "/auth/user/register")
                                .permitAll()
                                .anyRequest()
                                .authenticated()
                ).sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )
                .authenticationProvider(this.authenticationProvider)
                .addFilterBefore(this.jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
                .addFilterBefore(urlSanitizationFilter(), UsernamePasswordAuthenticationFilter.class);

        return httpSecurity.build();

    }

//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
//
//        httpSecurity
//                .csrf(csrf -> csrf.ignoringRequestMatchers("/auth/user/register")) // CSRF only disabled for registration
//                .cors(withDefaults()) // Use default CORS configuration or configure as needed
//                .authorizeHttpRequests(auth -> auth
//                        .requestMatchers("/auth/user/register").permitAll() // Registration permitted
//                        .requestMatchers("/auth/user/**").permitAll() // Other auth endpoints permitted if needed
//                        .anyRequest().authenticated() // All other requests require authentication
//                )
//                .sessionManagement(session -> session
//                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS) // Important for JWT authentication
//                )
//                .authenticationProvider(authenticationProvider) // Your authentication provider (important!)
//                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class); // JWT filter BEFORE username/password filter
//
//        return httpSecurity.build();
//    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowCredentials(true);
        configuration.setAllowedOrigins(List.of("http://localhost:4200"));
        configuration.addAllowedOriginPattern("*");
        configuration.addAllowedHeader("*");
        configuration.addAllowedMethod("*");
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

    @Bean
    public CorsFilter corsFilter() {
        return new CorsFilter(corsConfigurationSource());
    }

}
