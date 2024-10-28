package dal.assignment;

import entity.accesscontrol.User;
import entity.accesscontrol.Role;
import entity.accesscontrol.Feature;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UserDBContext {
    protected Connection connection;

    public UserDBContext() {
        try {
            String user = "hieunguyen";
            String pass = "12345";
            String url = "jdbc:sqlserver://localhost\\SQLEXPRESS:1433;databaseName=CreatedByPA;trustServerCertificate=true;";
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            connection = DriverManager.getConnection(url, user, pass);
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(UserDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public User get(String username, String password) {
        try {
            String sql = "SELECT [UserName], [password] FROM [User] WHERE [UserName] = ? AND [password] = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, username);
            stm.setString(2, password);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                User user = new User();
                user.setUsername(rs.getString("UserName"));
                user.setPassword(rs.getString("password"));
                return user;
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public ArrayList<Role> getRoles(String username) {
        ArrayList<Role> roles = new ArrayList<>();
        try {
            String sql = "SELECT r.RoleID, r.RoleName FROM [UserRole] ur JOIN [Role] r ON ur.RoleID = r.RoleID WHERE ur.UserName = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, username);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Role role = new Role();
                role.setRoleId(rs.getInt("RoleID"));
                role.setRoleName(rs.getString("RoleName"));
                role.setFeatures(getFeatures(role.getRoleId()));
                roles.add(role);
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return roles;
    }

    public ArrayList<Feature> getFeatures(int roleId) {
        ArrayList<Feature> features = new ArrayList<>();
        try {
            String sql = "SELECT f.FeatureID, f.FeatureName, f.url FROM [RoleFeature] rf JOIN [Feature] f ON rf.FeatureID = f.FeatureID WHERE rf.RoleID = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, roleId);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Feature feature = new Feature();
                feature.setFeatureId(rs.getInt("FeatureID"));
                feature.setFeatureName(rs.getString("FeatureName"));
                feature.setUrl(rs.getString("url"));
                features.add(feature);
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return features;
    }
}


