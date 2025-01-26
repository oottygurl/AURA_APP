import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

public class RegisterServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String fullName = request.getParameter("full_name");
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        try (Connection con = DBconnection.getConnection()) {
            String query = "INSERT INTO users (full_name, email, password_hash) VALUES (?, ?, ?)";
            try (PreparedStatement stmt = con.prepareStatement(query)) {
                stmt.setString(1, fullName);
                stmt.setString(2, email);
                stmt.setString(3, password); // Again, hash the password for better security
                int result = stmt.executeUpdate();

                if (result > 0) {
                    response.sendRedirect("login.html"); // Redirect to login page on success
                } else {
                    response.sendRedirect("register.html"); // If registration failed
                }
            }
        } catch (SQLException e) {
            throw new ServletException("Database connection error", e);
        }
    }
}