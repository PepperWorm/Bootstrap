package web.springBootSecurityProject.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserDetailsService service;
    @Autowired
    public SecurityConfig(UserDetailsService service) {
        this.service = service;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf()
                .disable()
                .authorizeRequests()
                .antMatchers("/auth/home","/auth/login", "/auth/registration", "/error").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/auth/home")
                .loginProcessingUrl("/process_login")
                .defaultSuccessUrl("/index", true)
                .failureUrl("/auth/login?error")
                .and()
                .logout().logoutUrl("/logout").logoutSuccessUrl("/auth/home");
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) {
        try {
            auth.userDetailsService(service);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Bean
    public PasswordEncoder getPasswordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }
}
