package com.optika.optikaapp.interfaces;

import com.optika.optikaapp.model.Buyer;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface BuyerService {

    @GET("getBuyer/getAll")
    Call<List<Buyer>> getBuyers();
}
