package ru.senla.school.hotel.project.senlaprojecthotel.senla_project_home.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import ru.senla.school.hotel.project.senlaprojecthotel.senla_project_home.entity.Permission;
import ru.senla.school.hotel.project.senlaprojecthotel.senla_project_home.security.JwtConfigurer;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final JwtConfigurer jwtConfigurer;
    private final UserDetailsService userDetailsService;

    public SecurityConfig(JwtConfigurer jwtConfigurer, @Qualifier("userDetailsServiceImpl") UserDetailsService userDetailsService) {
        this.jwtConfigurer = jwtConfigurer;
        this.userDetailsService = userDetailsService;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .antMatchers("/").permitAll()
                .antMatchers("/auth/login").permitAll()
                .antMatchers(HttpMethod.GET, "/**").hasAuthority(Permission.READ.getPermission())
                .antMatchers(HttpMethod.POST, "/**").hasAuthority(Permission.WRITE.getPermission())
                .antMatchers(HttpMethod.DELETE, "/**").hasAuthority(Permission.WRITE.getPermission())
                .antMatchers(HttpMethod.PUT, "/**").hasAuthority(Permission.WRITE.getPermission())
                .antMatchers(HttpMethod.PATCH, "/**").hasAuthority(Permission.WRITE.getPermission())
                .anyRequest()
                .authenticated()
                .and()
                .apply(jwtConfigurer);
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        // configure AuthenticationManager so that it knows from where to load
        // user for matching credentials
        // Use BCryptPasswordEncoder
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    protected PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(12);
    }
}