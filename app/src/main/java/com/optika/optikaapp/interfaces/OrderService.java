package com.optika.optikaapp.interfaces;

import com.optika.optikaapp.model.Order;

import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface OrderService {

    @GET("getOrder/getOrderById")
    Call<HashMap<String, Order>> getOrdersById(@Query("id") int id);
}
