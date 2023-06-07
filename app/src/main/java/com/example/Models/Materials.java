package com.example.Models;

public class Materials {
    private String idMaterial;
    private String materialName;
    private String materialDescription;
    private int primaryQuantity;
    private boolean materialStatus;
    private String materialIMG;
    private String idType;
    private String idPrimaryUnit;
    private String idProduct;

    public Materials() {
    }

    public Materials(String idMaterial, String materialName, String materialDescription, int primaryQuantity, boolean materialStatus, String materialIMG, String idType, String idPrimaryUnit, String idProduct) {
        this.idMaterial = idMaterial;
        this.materialName = materialName;
        this.materialDescription = materialDescription;
        this.primaryQuantity = primaryQuantity;
        this.materialStatus = materialStatus;
        this.materialIMG = materialIMG;
        this.idType = idType;
        this.idPrimaryUnit = idPrimaryUnit;
        this.idProduct = idProduct;
    }


    public String getIdMaterial() {
        return idMaterial;
    }

    public void setIdMaterial(String idMaterial) {
        this.idMaterial = idMaterial;
    }

    public String getMaterialName() {
        return materialName;
    }

    public void setMaterialName(String materialName) {
        this.materialName = materialName;
    }

    public String getMaterialDescription() {
        return materialDescription;
    }

    public void setMaterialDescription(String materialDescription) {
        this.materialDescription = materialDescription;
    }

    public int getPrimaryQuantity() {
        return primaryQuantity;
    }

    public void setPrimaryQuantity(int primaryQuantity) {
        this.primaryQuantity = primaryQuantity;
    }

    public boolean isMaterialStatus() {
        return materialStatus;
    }

    public void setMaterialStatus(boolean materialStatus) {
        this.materialStatus = materialStatus;
    }

    public String getMaterialIMG() {
        return materialIMG;
    }

    public void setMaterialIMG(String materialIMG) {
        this.materialIMG = materialIMG;
    }

    public String getIdType() {
        return idType;
    }

    public void setIdType(String idType) {
        this.idType = idType;
    }

    public String getIdPrimaryUnit() {
        return idPrimaryUnit;
    }

    public void setIdPrimaryUnit(String idPrimaryUnit) {
        this.idPrimaryUnit = idPrimaryUnit;
    }

    public String getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(String idProduct) {
        this.idProduct = idProduct;
    }
}
