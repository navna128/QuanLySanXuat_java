package com.example.Models;

public class MaterialTypes {
    private String idType;
    private String typeName;
    private String typelDescription;

    public MaterialTypes() {
    }

    public MaterialTypes(String idType, String typeName, String typelDescription) {
        this.idType = idType;
        this.typeName = typeName;
        this.typelDescription = typelDescription;
    }


    public String getIdType() {
        return idType;
    }

    public void setIdType(String idType) {
        this.idType = idType;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getTypelDescription() {
        return typelDescription;
    }

    public void setTypelDescription(String typelDescription) {
        this.typelDescription = typelDescription;
    }
}
