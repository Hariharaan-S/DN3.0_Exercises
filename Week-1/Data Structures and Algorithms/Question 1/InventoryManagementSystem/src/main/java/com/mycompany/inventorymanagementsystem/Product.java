/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.inventorymanagementsystem;

/**
 *
 * @author shari
 */
public class Product implements Comparable<Product> {
    private int productID;
    private String productName;
    private int quantity;
    private int unitPrice;

    /**
     * @return the productID
     */
    public int getProductID() {
        return productID;
    }

    /**
     * @param productID the productID to set
     */
    public void setProductID(int productID) {
        this.productID = productID;
    }

    /**
     * @return the productName
     */
    public String getProductName() {
        return productName;
    }

    /**
     * @param productName the productName to set
     */
    public void setProductName(String productName) {
        this.productName = productName;
    }

    /**
     * @return the quantity
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * @param quantity the quantity to set
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    /**
     * @return the price
     */
    public int getUnitPrice() {
        return unitPrice;
    }

    /**
     * @param price the price to set
     */
    public void setUnitPrice(int price) {
        this.unitPrice = price;
    }

    @Override
    public int compareTo(Product o) {
        if(o.getProductID() > this.getProductID()){
            return 1;
        }
        return 0;
    }

}
