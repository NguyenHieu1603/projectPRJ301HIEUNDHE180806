/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity.assignment;

/**
 *
 * @author sonnt-local
 */
public class PlanCampain {
    private int id;
    private Plan plan;
    private Product product;
    private int quantity;
    private float cost;
    private int quantity_per_shift_1;
    private int quantity_per_shift_2;
    private int quantity_per_shift_3;

    public PlanCampain() {
        this.quantity_per_shift_1 = 0;
        this.quantity_per_shift_2 = 0;
        this.quantity_per_shift_3 = 0;
    }

    public int getQuantity_per_shift_1() {
        return quantity_per_shift_1;
    }

    public void setQuantity_per_shift_1(int quantity_per_shift_1) {
        this.quantity_per_shift_1 = quantity_per_shift_1;
    }

    public int getQuantity_per_shift_2() {
        return quantity_per_shift_2;
    }

    public void setQuantity_per_shift_2(int quantity_per_shift_2) {
        this.quantity_per_shift_2 = quantity_per_shift_2;
    }

    public int getQuantity_per_shift_3() {
        return quantity_per_shift_3;
    }

    public void setQuantity_per_shift_3(int quantity_per_shift_3) {
        this.quantity_per_shift_3 = quantity_per_shift_3;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Plan getPlan() {
        return plan;
    }

    public void setPlan(Plan plan) {
        this.plan = plan;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public float getCost() {
        return cost;
    }

    public void setCost(float cost) {
        this.cost = cost;
    }
}