package com.devapps.justclass_api.utils;

import com.devapps.justclass_api.repositories.UserRepository;
import com.devapps.justclass_api.utils.jwt.JwtAuthenticationFilter;
import com.devapps.justclass_api.utils.jwt.JwtService;
import jakarta.servlet.Filter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class ApplicationConfig {

    private final UserRepository userRepository;

    public ApplicationConfig(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

@Qualifier("jwtFilter")
    public Filter jwtAuthFilter() {
    return new JwtAuthenticationFilter(jwtService(), userDetailsService());
}

@Bean
public JwtService jwtService() {
    return new JwtService();
}

@Bean
    public AuthenticationProvider authenticationProvider() {
    DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
    authProvider.setUserDetailsService(userDetailsService());
    authProvider.setPasswordEncoder(passwordEncoder());
    return authProvider;
}

@Bean
public UserDetailsService userDetailsService() {
    return new UserDetailsService() {
        @Override
        public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
            return userRepository.findByUsername(username)
                    .orElseThrow(() -> new UsernameNotFoundException("No user with the name: " + username + " was found"));
        }
    };
}

@Bean
    public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
}

@Bean
@Primary
    AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
    return authenticationConfiguration.getAuthenticationManager();
}


}
