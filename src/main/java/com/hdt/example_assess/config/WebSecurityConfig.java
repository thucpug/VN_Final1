package com.hdt.example_assess.config;

import com.hdt.example_assess.jwt.JwtAuthenticationFilter;
import com.hdt.example_assess.service.Impl.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;


import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.Collections;

@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    UserService userService;
    @Bean
    public JwtAuthenticationFilter jwtAuthenticationFilter(){
        return new JwtAuthenticationFilter();
    }
    @Bean(BeanIds.AUTHENTICATION_MANAGER)
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        // Get AuthenticationManager bean
        return super.authenticationManagerBean();
    }
    @Bean
    public PasswordEncoder passwordEncoder() {
        // Password encoder, để Spring Security sử dụng mã hóa mật khẩu người dùng
        return new BCryptPasswordEncoder();
    }
    private static final String[] CLASSPATH_RESOURCE_LOCATIONS = {
            "classpath:/META-INF/resources/", "classpath:/resources/",
            "classpath:/static/", "classpath:/public/" };


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService) // Cung cáp userservice cho spring security
                .passwordEncoder(passwordEncoder()); // cung cấp password encoder
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.cors().configurationSource(new CorsConfigurationSource() {
            @Override
            public CorsConfiguration getCorsConfiguration(HttpServletRequest request) {
                CorsConfiguration config = new CorsConfiguration();
                config.setAllowedHeaders(Collections.singletonList("*"));
                config.setAllowedMethods(Collections.singletonList("*"));
                config.addAllowedOrigin("*");
                config.setExposedHeaders(Arrays.asList("Authorization"));
                config.setAllowCredentials(true);
                return config;
            }
        });

        http.authorizeRequests()
                // No need authentication./image/upload
                .antMatchers(HttpMethod.POST, "/api/image/upload").permitAll()
                .antMatchers(HttpMethod.GET, "/api/images/{url}").permitAll()
                .antMatchers(HttpMethod.GET, "/api/image-response-entity").permitAll()
                .antMatchers(HttpMethod.GET, "/api/reportv2/**").permitAll()
                .antMatchers(HttpMethod.POST, "/api/auth/login").permitAll() //privatead
                .antMatchers(HttpMethod.POST,"/api/auth/register").permitAll()
                .antMatchers(HttpMethod.GET,"/api/auth/create-role").permitAll()
                .antMatchers(HttpMethod.GET,"/api/auth/privatead").hasRole("ADMIN")
                .antMatchers(HttpMethod.GET,"/api/auth/privateuser").hasRole("USER")
                .antMatchers("/individual", "/application", "/upload").hasRole("USER")
                //book
                .antMatchers(HttpMethod.POST,"/api/book").hasRole("ADMIN")
                .antMatchers(HttpMethod.POST,"/api/book/{id}").hasRole("ADMIN")
                .antMatchers(HttpMethod.PUT,"/api/book/{id}").hasRole("ADMIN")
                .antMatchers(HttpMethod.DELETE,"/api/book/{id}").hasRole("ADMIN")
                //bill
                .antMatchers(HttpMethod.POST,"/api/bill").hasRole("ADMIN")
                .antMatchers(HttpMethod.POST,"/api/bill/{id}").hasRole("ADMIN")
                .antMatchers(HttpMethod.PUT,"/api/bill/{id}").hasRole("ADMIN")
                .antMatchers(HttpMethod.DELETE,"/api/bill/{id}").hasRole("ADMIN")

                .anyRequest().authenticated()
                .and()
                .addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
    }
}
