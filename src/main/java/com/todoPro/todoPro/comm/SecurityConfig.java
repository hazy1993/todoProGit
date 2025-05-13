package com.todoPro.todoPro.comm;

import com.todoPro.todoPro.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.servlet.http.HttpServletResponse;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private JwtAuthenticationFilter jwtAuthenticationFilter;

    private final JwtUtil jwtUtil;

    private final UsersService usersService;

    public SecurityConfig(JwtUtil jwtUtil, JwtAuthenticationFilter jwtAuthenticationFilter ,UsersService usersService) {
        this.jwtUtil = jwtUtil;
        this.jwtAuthenticationFilter = jwtAuthenticationFilter;
        this.usersService  = usersService;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http
                .csrf(AbstractHttpConfigurer::disable)
                .httpBasic(AbstractHttpConfigurer::disable)
                .authorizeRequests(authz -> authz
                        .antMatchers("/users/signup","/users/login").permitAll()  // /login 경로는 누구나 접근 가능
                        .anyRequest().authenticated()  // 그 외에는 인증된 사용자만 접근 가능
                ) .addFilterBefore(new JwtAuthenticationFilter(jwtUtil,usersService),
                        UsernamePasswordAuthenticationFilter.class)


                /*.exceptionHandling(ex -> ex
                         .authenticationEntryPoint((request, response, authException) -> {
                         response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized"); // 토큰없을경우 401 응답을 명시적으로 처리
                     })
                )*/
                // Form 로그인을 활용하는경우 (JWT에는 필요없음)
          /*      .formLogin(form -> form
                       // .loginPage("/loginform")
                        .loginProcessingUrl("users/login")
                        .defaultSuccessUrl("/")
                        .permitAll()
                )*/
                .logout((logout) -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/")
                        .invalidateHttpSession(true)
                )
                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED) // 또는 생략
                        .maximumSessions(1)
                        .maxSessionsPreventsLogin(true)
                );
        return http.build();
    }

}