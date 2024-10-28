
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal.assignment;

import dal.assignment.DBContext;
import entity.assignment.Department;
import entity.assignment.Plan;
import entity.assignment.PlanCampain;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.*;

/**
 *
 * @author sonnt-local
 */
public class PlanDBContext extends DBContext<Plan> {
    
    @Override
    public void insert(Plan entity) {
        try {
            connection.setAutoCommit(false);
            
            String sql_insert_plan = "INSERT INTO [Plan]\n"
                    + "           ([PlanName]\n"
                    + "           ,[StartDate]\n"
                    + "           ,[EndDate]\n"
                    + "           ,[DepartmentID])\n"
                    + "     VALUES\n"
                    + "           (?\n"
                    + "           ,?\n"
                    + "           ,?\n"
                    + "           ,?)";
            PreparedStatement stm_insert_plan = connection.prepareStatement(sql_insert_plan);
            stm_insert_plan.setString(1, entity.getName());
            stm_insert_plan.setDate(2, entity.getStart());
            stm_insert_plan.setDate(3, entity.getEnd());
            stm_insert_plan.setInt(4, entity.getDept().getId());
            stm_insert_plan.executeUpdate();
            
            String sql_select_plan = "SELECT @@IDENTITY as PlanID";
            PreparedStatement stm_select_plan = connection.prepareStatement(sql_select_plan);
            ResultSet rs = stm_select_plan.executeQuery();
            if (rs.next()) {
                entity.setId(rs.getInt("PlanID"));
            }
            
            for (PlanCampain campain : entity.getCampains()) {
                String sql_insert_campain = "INSERT INTO [PlanCampain]\n"
                        + "           ([PlanID]\n"
                        + "           ,[ProductID]\n"
                        + "           ,[Quantity]\n"
                        + "           ,[Estimate]\n"
                        + "           ,[quantity_per_shift_1]\n"
                        + "           ,[quantity_per_shift_2]\n"
                        + "           ,[quantity_per_shift_3])\n"

                        + "     VALUES\n"
                        + "           (?\n"
                        + "           ,?\n"
                        + "           ,?\n"
                        + "           ,?\n"
                        + "           ,?\n"
                        + "           ,?\n"
                        + "           ,?)";
                
                PreparedStatement stm_insert_campain = connection.prepareStatement(sql_insert_campain);
                stm_insert_campain.setInt(1, entity.getId());
                stm_insert_campain.setInt(2, campain.getProduct().getId());
                stm_insert_campain.setInt(3, campain.getQuantity());
                stm_insert_campain.setFloat(4, campain.getCost());
                stm_insert_campain.setInt(5, campain.getQuantity_per_shift_1());
                stm_insert_campain.setInt(6, campain.getQuantity_per_shift_2());
                stm_insert_campain.setInt(7, campain.getQuantity_per_shift_3());

                stm_insert_campain.executeUpdate();
            }
            
            connection.commit();
        } catch (SQLException ex) {
            Logger.getLogger(PlanDBContext.class.getName()).log(Level.SEVERE, null, ex);
            try {
                connection.rollback();
            } catch (SQLException ex1) {
                Logger.getLogger(PlanDBContext.class.getName()).log(Level.SEVERE, null, ex1);
            }
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException ex) {
                Logger.getLogger(PlanDBContext.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(PlanDBContext.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
    }
    
    @Override
    public void update(Plan entity) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    @Override
    public void delete(Plan entity) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    @Override
    public Plan get(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public ArrayList<Plan> list() {
        ArrayList<Plan> plans = new ArrayList<>();
        PreparedStatement command = null;
        try {
            String sql_select_plan = "select PlanID, PlanName, StartDate, EndDate, d.DepartmentName, p.DepartmentID from [Plan] p\n" +
"		   join Department d\n" +
"		   on d.DepartmentID = p.DepartmentID";
            
            
            command = connection.prepareStatement(sql_select_plan);
            ResultSet rs = command.executeQuery();
            while (rs.next()) {
                Plan p = new Plan();
                p.setId(rs.getInt("PlanID"));
                p.setName(rs.getNString("PlanName"));
                p.setStart(rs.getDate("StartDate"));
                p.setEnd(rs.getDate("EndDate"));

                Department d = new Department();
                d.setId(rs.getInt("DepartmentID"));
                d.setName(rs.getString("DepartmentName"));
                p.setDept(d);

                plans.add(p);
            }
        } catch (SQLException ex) {
            Logger.getLogger(PlanDBContext.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                command.close();
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(PlanDBContext.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
           
        return plans;
    }
}
    
