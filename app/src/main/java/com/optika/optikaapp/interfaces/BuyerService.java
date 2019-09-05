package com.optika.optikaapp.interfaces;

import com.optika.optikaapp.model.Buyer;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface BuyerService {

    @GET("getBuyer/getAll")
    Call<List<Buyer>> getBuyers();

    @GET("getBuyer/getBuyerByName")
    Call<List<Buyer>> getBuyersByName(@Query("name") String name, @Query("lastname") String lastname);

    @GET("getBuyer/getBuyerById")
    Call<Buyer> getBuyerById(@Query("id") int id);

    @POST("/AddBuyer")
    Call<String> addBuyer(
            @Query("name") String name,
            @Query("lastname") String lastname
    );
}
