package com.example.Models;

public class Resources {
    private int idResource;
    private String resourceName;
    private String resourcelDescription;

    public Resources(int idResource, String resourceName, String resourcelDescription) {
        this.idResource = idResource;
        this.resourceName = resourceName;
        this.resourcelDescription = resourcelDescription;
    }

    public Resources() {
    }

    public int getIdResource() {
        return idResource;
    }

    public void setIdResource(int idResource) {
        this.idResource = idResource;
    }

    public String getResourceName() {
        return resourceName;
    }

    public void setResourceName(String resourceName) {
        this.resourceName = resourceName;
    }

    public String getResourcelDescription() {
        return resourcelDescription;
    }

    public void setResourcelDescription(String resourcelDescription) {
        this.resourcelDescription = resourcelDescription;
    }
}
