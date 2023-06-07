package com.example;

import android.util.Log;

import com.example.API.ApiService;
import com.example.Models.MaterialTypes;
import com.example.Models.Materials;
import com.example.Models.PrimaryUnits;
import com.example.Models.Products;
import com.example.Models.Resources;
import com.example.Models.Users;
import com.example.quanlysanxuat.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DataManager {
    public static Users currentUser = new Users();
    public static Materials selectedMaterial = new Materials();
    public static MaterialTypes selectedMaterialType = new MaterialTypes();
    public static PrimaryUnits selectedPrimaryUnit = new PrimaryUnits();
    public static Products selectedProduct = new Products();
    public static Resources selectedResource = new Resources();

    public static  List<MaterialTypes> materialTypesList = new ArrayList<>();
    public static  List<PrimaryUnits> primaryUnitsList = new ArrayList<>();
    public static  List<Products> productsList = new ArrayList<>();
}
