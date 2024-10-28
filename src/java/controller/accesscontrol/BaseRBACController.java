package controller.accesscontrol;

import dal.assignment.UserDBContext;
import entity.accesscontrol.Feature;
import entity.accesscontrol.Role;
import entity.accesscontrol.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

public abstract class BaseRBACController extends BaseRequiredAuthenticationController {
    
    private boolean isAuthorized(HttpServletRequest req, User account) {
        String currentUrl = req.getServletPath();
        UserDBContext db = new UserDBContext();
        ArrayList<Role> roles = db.getRoles(account.getUsername());
        account.setRoles(roles);
        
        for (Role role : account.getRoles()) {
            for (Feature feature : role.getFeatures()) {
                if (feature.getUrl().equals(currentUrl)) {
                    return true;
                }
            }
        }
        
        return false;
    }
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp, User account) throws ServletException, IOException {
        if (isAuthorized(req, account)) {
            doAuthorizedGet(req, resp, account);
        } else {
            resp.sendError(403, "You do not have the right to access this feature!");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp, User account) throws ServletException, IOException {
        if (isAuthorized(req, account)) {
            doAuthorizedPost(req, resp, account);
        } else {
            resp.sendError(403, "You do not have the right to access this feature!");
        }
    }

    protected abstract void doAuthorizedGet(HttpServletRequest req, HttpServletResponse resp, User account) throws ServletException, IOException;
    protected abstract void doAuthorizedPost(HttpServletRequest req, HttpServletResponse resp, User account) throws ServletException, IOException;
}


