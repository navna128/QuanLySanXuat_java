package com.example.Models;

public class PrimaryUnits {
    private String idPrimaryUnit;
    private String primaryUnitName;
    private String primaryUnitlDescription;

    public PrimaryUnits() {
    }

    public PrimaryUnits(String idPrimaryUnit, String primaryUnitName, String primaryUnitlDescription) {
        this.idPrimaryUnit = idPrimaryUnit;
        this.primaryUnitName = primaryUnitName;
        this.primaryUnitlDescription = primaryUnitlDescription;
    }


    public String getIdPrimaryUnit() {
        return idPrimaryUnit;
    }

    public void setIdPrimaryUnit(String idPrimaryUnit) {
        this.idPrimaryUnit = idPrimaryUnit;
    }

    public String getPrimaryUnitName() {
        return primaryUnitName;
    }

    public void setPrimaryUnitName(String primaryUnitName) {
        this.primaryUnitName = primaryUnitName;
    }

    public String getPrimaryUnitlDescription() {
        return primaryUnitlDescription;
    }

    public void setPrimaryUnitlDescription(String primaryUnitlDescription) {
        this.primaryUnitlDescription = primaryUnitlDescription;
    }
}
