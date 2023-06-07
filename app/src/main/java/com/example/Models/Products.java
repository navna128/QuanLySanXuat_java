package com.example.Models;

public class Products {
    private String idProduct;
    private String productName;
    private String productlDescription;
    private String productIMG;

    public Products() {
    }

    public Products(String idProduct, String productName, String productlDescription, String productIMG) {
        this.idProduct = idProduct;
        this.productName = productName;
        this.productlDescription = productlDescription;
        this.productIMG = productIMG;
    }

    public String getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(String idProduct) {
        this.idProduct = idProduct;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductlDescription() {
        return productlDescription;
    }

    public void setProductlDescription(String productlDescription) {
        this.productlDescription = productlDescription;
    }

    public String getProductIMG() {
        return productIMG;
    }

    public void setProductIMG(String productIMG) {
        this.productIMG = productIMG;
    }
}
