package com.example.API;

import com.example.Models.MaterialTypes;
import com.example.Models.Materials;
import com.example.Models.PrimaryUnits;
import com.example.Models.Products;
import com.example.Models.Resources;
import com.example.Models.Users;
import com.example.Utils.Common;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiService {
    Gson gson = new GsonBuilder().setDateFormat("dd/MM/yyyy").create();
    OkHttpClient okHttpClient = new OkHttpClient.Builder()
            .readTimeout(60, TimeUnit.SECONDS)
            .connectTimeout(60, TimeUnit.SECONDS)
            .build();
    ApiService api = new Retrofit.Builder()
            .baseUrl(Common.baseUrl)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(okHttpClient)
            .build()
            .create(ApiService.class);


    //tài khoản
    @GET("api/Users/Login")
    Call<Boolean> Login(@Query("username") String username, @Query("password") String password);

    @GET("api/Users/checkUsernameExist")
    Call<Boolean> checkUsernameExist(@Query("username") String username);

    @POST("api/Users/PostUser")
    Call<Users> PostUser(@Body Users users);

    @GET("api/Materials/GetMaterials")
    Call<List<Materials>> GetMaterials();

    @GET("api/MaterialTypes/GetMaterialTypes")
    Call<List<MaterialTypes>> GetMaterialTypes();

    @GET("api/MaterialTypes/GetIdByName")
    Call<String> GetIdByName(@Query("name") String materialTypeName);

    @GET("api/PrimaryUnits/GetPrimaryUnits")
    Call<List<PrimaryUnits>> GetPrimaryUnits();


    @GET("api/Resources/GetResources")
    Call<List<Resources>> GetResources();

    @GET("api/Products/GetProducts")
    Call<List<Products>> GetProducts();



}
