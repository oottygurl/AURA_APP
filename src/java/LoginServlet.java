import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

public class LoginServlet extends HttpServlet {

    /**
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        try (Connection con = DBconnection.getConnection()) {
            String query = "SELECT * FROM users WHERE email = ? AND password_hash = ?";
            try (PreparedStatement stmt = con.prepareStatement(query)) {
                stmt.setString(1, email);
                stmt.setString(2, password); // Ideally, hash password comparison should be used
                ResultSet rs = stmt.executeQuery();

                if (rs.next()) {
                    HttpSession session = request.getSession();
                    session.setAttribute("user_id", rs.getInt("id"));
                    response.sendRedirect("dashboard.jsp"); // Redirect to the user dashboard
                } else {
                    response.sendRedirect("login.html"); // Invalid login, redirect to login page
                }
            }
        } catch (SQLException e) {
            throw new ServletException("Database connection error", e);
        }
    }
}
