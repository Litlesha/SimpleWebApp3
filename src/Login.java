import model.UserEntity;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.*;

@WebServlet(name = "/Login", urlPatterns = "/Login")
public class Login extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        String pass = request.getParameter("pass");
        UserEntity user = new UserEntity();
        user.setName(name);
        user.setPassword(pass);

            if (validate(name, pass)) {
                response.sendRedirect("welcome.jsp");
            } else {
                response.sendRedirect("index.jsp");
            }
        }
private boolean validate(String username, String password) {

    try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/users", "root", "123456")) {
        try (PreparedStatement statement = conn.prepareStatement("SELECT * FROM user1 WHERE name = ? AND password = ?")) {
            statement.setString(1, username);
            statement.setString(2, password);
            ResultSet result = statement.executeQuery();
            return result.next();
        }
    } catch (SQLException e) {
        e.printStackTrace();
        return false;
    }
}

}
