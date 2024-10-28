/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controller.productionplan;

import dal.assignment.DepartmentDBContext;
import dal.assignment.PlanDBContext;
import dal.assignment.ProductDBContext;
import entity.assignment.Department;
import entity.assignment.Plan;
import entity.assignment.PlanCampain;
import entity.assignment.Product;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.Date;
import java.util.ArrayList;

@WebServlet("/plan/create")
public class ProductionPlanCreateController extends HttpServlet {
   
    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
     * Handles the HTTP <code>GET</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        ProductDBContext dbProduct = new ProductDBContext();
        DepartmentDBContext dbDepts = new DepartmentDBContext();
        
        request.setAttribute("products", dbProduct.list());
        request.setAttribute("depts", dbDepts.get("WS"));
        
        request.getRequestDispatcher("/productionplan/create.jsp").forward(request, response);
    } 

    /** 
     * Handles the HTTP <code>POST</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        Plan plan = new Plan();
        plan.setName(request.getParameter("name"));
        plan.setStart(Date.valueOf(request.getParameter("from")));
        plan.setEnd(Date.valueOf(request.getParameter("to")));
        Department d = new Department();
        d.setId(Integer.parseInt(request.getParameter("did")));
        plan.setDept(d);
        
        String[] pids = request.getParameterValues("pid");
        if (pids != null) {
            for (String pid : pids) {
                PlanCampain c = new PlanCampain();
                Product p = new Product();
                p.setId(Integer.parseInt(pid));
                c.setProduct(p);
                c.setPlan(plan);
                 String raw_quantity = request.getParameter("quantity" + pid);
                String raw_cost = request.getParameter("cost" + pid);
                
                long differenceInMillis = plan.getEnd().getTime() - plan.getStart().getTime();
                int differenceInDays = (int) (differenceInMillis / (1000 * 60 * 60 * 24));
               
                int qps1 = Integer.parseInt(raw_quantity)/ differenceInDays / 3;
                c.setQuantity_per_shift_1(qps1);
                c.setQuantity_per_shift_2(qps1);
                c.setQuantity_per_shift_3(qps1);

                
                
                
                c.setQuantity(raw_quantity != null && raw_quantity.length() > 0 ? Integer.parseInt(raw_quantity) : 0);
                c.setCost(raw_cost != null && raw_cost.length() > 0 ? Float.parseFloat(raw_cost) : 0);
                
                if (c.getQuantity() > 0 && c.getCost() > 0) {
                    plan.getCampains().add(c);
                }
            }
        }
        
        if (plan.getCampains().size() > 0) {
            // Insert plan
            PlanDBContext db = new PlanDBContext();
            db.insert(plan);
            response.getWriter().println("Your plan has been added!");
        } else {
            response.getWriter().println("Your plan does not have any valid products/campaigns.");
        }
    }
}

