package com.optika.optikaapp.interfaces;

import retrofit2.Call;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ContactService {

    @POST("/AddContact")
    Call<String> addContact(
            @Query("id") int id,
            @Query("phoneNum") String phoneNum
    );

    @POST("/DeleteContact")
    Call<String> deleteContact(
            @Query("id") int id
    );
}
