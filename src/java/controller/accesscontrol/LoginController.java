package controller.accesscontrol;

import dal.assignment.UserDBContext;
import entity.accesscontrol.User;
import entity.accesscontrol.Role;
import entity.accesscontrol.Feature;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet("/login")
public class LoginController extends HttpServlet {
    
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String user = req.getParameter("username");
        String pass = req.getParameter("password");
        
        UserDBContext db = new UserDBContext();
        User account = db.get(user, pass);
        
        if (account != null) {
            ArrayList<Role> roles = db.getRoles(account.getUsername());
            account.setRoles(roles);
            HttpSession session = req.getSession(true);
            session.setAttribute("account", account) ;   
            resp.sendRedirect(req.getContextPath() + "/plan/list"); // Redirect to homepage after successful login
        } else {
            resp.getWriter().println("login failed!");
        }
    }
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("./login/login.jsp").forward(req, resp);
    }
    
}

