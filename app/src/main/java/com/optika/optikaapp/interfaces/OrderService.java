package com.optika.optikaapp.interfaces;

import com.optika.optikaapp.model.Angle;
import com.optika.optikaapp.model.Diopter;
import com.optika.optikaapp.model.Order;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface OrderService {

    @GET("getOrder/getOrderById")
    Call<HashMap<String, Order>> getOrdersById(@Query("id") int id);

    @POST("/AddOrder")
    Call<String> addOrder(
            @Query("id") int id,
            @Query("date") String date,
            @Query("od_sph") Double od_sph,
            @Query("os_sph") Double os_sph,
            @Query("od_cyl") Double od_cyl,
            @Query("os_cyl") Double os_cyl,
            @Query("od_angle") Integer od_angle,
            @Query("os_angle") Integer os_angle,
            @Query("pd") Double pd,
            @Query("type") String type,
            @Query("frame") String frame,
            @Query("comment") String comment,
            @Query("hasAddition") Boolean hasAddition,
            @Query("addition") Double addition);

    @POST("DeleteOrder")
    Call<String> deleteOrder(
            @Query("id") int id
    );
}
