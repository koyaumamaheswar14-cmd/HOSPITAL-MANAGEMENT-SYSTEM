package com.hms.servlets;

import com.hms.util.DBConnection;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

public class SignupServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String username = request.getParameter("username");
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        try (Connection conn = DBConnection.getConnection()) {
            String sql = "INSERT INTO users (username, email, password) VALUES (?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, username);
            stmt.setString(2, email);
            stmt.setString(3, password);  // ⚠ stored as plain text for now

            int rows = stmt.executeUpdate();
            if (rows > 0) {
                response.sendRedirect("login.html?msg=SignupSuccess");
            } else {
                response.sendRedirect("signup.html?msg=SignupFailed");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendRedirect("signup.html?msg=Error");
        }
    }
}
