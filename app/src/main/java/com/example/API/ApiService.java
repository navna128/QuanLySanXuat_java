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
import retrofit2.http.DELETE;
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
    Call<Users> Login(@Query("username") String username, @Query("password") String password);

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

    @POST("api/Materials/PostMaterial")
    Call<Materials> PostMaterial(@Body Materials materials);

    @POST("api/MaterialTypes/PostMaterialType")
    Call<MaterialTypes> PostMaterialType(@Body MaterialTypes materialTypes);

    @POST("api/PrimaryUnits/PostPrimaryUnit")
    Call<PrimaryUnits> PostPrimaryUnit(@Body PrimaryUnits primaryUnits);

    @POST("api/Products/PostProduct")
    Call<Products> PostProduct(@Body Products products);

    @POST("api/Resources/PostResource")
    Call<Resources> PostResource(@Body Resources resources);

    @DELETE("api/Materials/DeleteMaterial")
    Call<Materials> DeleteMaterial(@Query("id") String id);

    @DELETE("api/MaterialTypes/DeleteMaterialType")
    Call<MaterialTypes> DeleteMaterialType(@Query("id") String id);

    @DELETE("api/PrimaryUnits/DeletePrimaryUnit")
    Call<PrimaryUnits> DeletePrimaryUnit(@Query("id") String id);

    @DELETE("api/Products/DeleteProduct")
    Call<Products> DeleteProduct(@Query("id") String id);

    @DELETE("api/Resources/DeleteResource")
    Call<Resources> DeleteResource(@Query("id") String id);

}
