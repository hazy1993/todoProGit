package com.todoPro.todoPro.comm;

import com.todoPro.todoPro.service.UsersService;
import com.todoPro.todoPro.vo.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;


import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;
import java.util.List;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private JwtUtil jwtUtil;

    private UsersService usersService;

    public JwtAuthenticationFilter(JwtUtil jwtUtil,UsersService usersService) {
        this.jwtUtil = jwtUtil;
        this.usersService = usersService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        String uri = request.getRequestURI();
        // ✅ 로그인 관련 URL은 JWT 인증 없이 통과
        if (uri.startsWith("/users/login") || uri.startsWith("/users/signup") ) {
            filterChain.doFilter(request, response);
            return;
        }

        String header = request.getHeader("Authorization");
        //header != null && header.startsWith("Bearer ")
        try {

            String token = header.substring(7);

            if (!jwtUtil.isTokenValid(token)) {
                response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Invalid or expired JWT");
                return;
            }

            String userId = jwtUtil.extractUserId(token);

            // 사용자 DB 존재 여부 확인
            Users users = usersService.findUsers(userId); // userId 또는 username

            UsernamePasswordAuthenticationToken authentication =
                new UsernamePasswordAuthenticationToken(
                users, null, List.of());

                SecurityContextHolder.getContext().setAuthentication(authentication);

            } catch (org.springframework.security.core.userdetails.UsernameNotFoundException e) {
                response.setStatus(HttpServletResponse.SC_NOT_FOUND);
                response.getWriter().write("User not found in database");
                return;
            } catch (Exception e) {
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                response.getWriter().write("Authentication failed");
                return;
             }
        filterChain.doFilter(request, response);
    }
}