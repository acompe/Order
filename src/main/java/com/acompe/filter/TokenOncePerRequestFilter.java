package com.acompe.filter;

import com.acompe.dto.UserAuthentication;
import com.acompe.pojo.User;
import com.acompe.service.UserService;
import com.acompe.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class TokenOncePerRequestFilter extends OncePerRequestFilter {
    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private UserService userService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = request.getHeader(jwtUtil.getHeader());
        if (!StringUtils.isEmpty(token)) {
            int id = jwtUtil.getIdFromToken(token);
            if (id != 0 && SecurityContextHolder.getContext().getAuthentication() == null){
                User user = userService.findById(id);
                if (user != null){
                    UserAuthentication userAuthentication = new UserAuthentication(true,user);
                    SecurityContextHolder.getContext().setAuthentication(userAuthentication);
                }
            }
        }
        filterChain.doFilter(request, response);
    }
}
