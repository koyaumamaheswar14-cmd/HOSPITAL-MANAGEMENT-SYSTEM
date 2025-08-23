package com.hms.servlets;

import com.hms.util.DBConnection;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String username = request.getParameter("username"); // entered username
        String password = request.getParameter("password"); // entered password

        try (Connection conn = DBConnection.getConnection()) {
            String sql = "SELECT * FROM users WHERE username=? AND password=?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, username);
            stmt.setString(2, password);

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                // ✅ details match signup entry
                HttpSession session = request.getSession();
                session.setAttribute("username", rs.getString("username"));
                response.sendRedirect("dashboard.jsp");
            } else {
                // ❌ wrong details
                response.sendRedirect("login.html?msg=InvalidCredentials");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendRedirect("login.html?msg=Error");
        }
    }
}
