package wat.f.bridgedu.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
            .authorizeRequests(auth -> auth.antMatchers("/login**").permitAll())
            .authorizeRequests(auth -> auth.antMatchers("/students").hasAnyRole("ADMIN", "TEACHER"))
            .authorizeRequests(auth -> auth.antMatchers("/h2-console/**").hasRole("ADMIN"))
            .csrf(auth -> auth.ignoringAntMatchers("/h2-console/**"))
            .headers(auth -> auth.frameOptions(frame -> frame.sameOrigin()))
            .authorizeRequests(auth -> auth.anyRequest().hasAnyRole("ADMIN", "TEACHER", "STUDENT"))
            .formLogin(auth -> auth.loginPage("/login")
            .usernameParameter("username").passwordParameter("password"))
            .logout(auth -> auth.logoutUrl("/logout").logoutSuccessUrl("/login"))
            .build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
