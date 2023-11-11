package org.example.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.model.User;
import org.example.model.enums.UserRole;

import java.io.IOException;

@WebFilter(urlPatterns = "/admin/*")
public class AdminFilter extends HttpFilter {
    @Override
    protected void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws IOException, ServletException {
        User user = (User) req.getSession().getAttribute("user");
        if (user != null && UserRole.ADMIN.equals(user.getRole())) {
            chain.doFilter(req, res);
        } else {
            res.sendRedirect("/home");
        }
    }
}
