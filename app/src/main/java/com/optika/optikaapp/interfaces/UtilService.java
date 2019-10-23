package com.optika.optikaapp.interfaces;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface UtilService {

    @POST("utilService/countSearch")
    Call<String> countSearch(@Query("counter") int counter);

    @POST("utilService/countAdd")
    Call<String> countAdd(@Query("counter") int counter);
}
