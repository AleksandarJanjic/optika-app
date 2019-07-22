package com.optika.optikaapp.activities;

import android.os.Bundle;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;

import com.optika.optikaapp.R;
import com.optika.optikaapp.adapters.BuyerAdapter;
import com.optika.optikaapp.interfaces.BuyerService;
import com.optika.optikaapp.model.Buyer;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class SearchActivity extends AppCompatActivity{

    public ListView listView;
    private static BuyerAdapter adapter;
    List<Buyer> foundBuyers;
    public SearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        listView = findViewById(R.id.list_view);
        searchView = findViewById(R.id.simpleSearchView);


        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {

                System.out.println("inside OnQueryTextSubmit");
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl("")
                        .addConverterFactory(GsonConverterFactory.create())
                        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                        .build();

                BuyerService buyerService = retrofit.create(BuyerService.class);
                Call<List<Buyer>> call = buyerService.getBuyers();

                call.enqueue(new Callback<List<Buyer>>() {
                    @Override
                    public void onResponse(Call<List<Buyer>> call, Response<List<Buyer>> response) {
                        System.out.println("Called REST");
                        foundBuyers = response.body();
                        for (Buyer b: foundBuyers
                             ) {
                            System.out.println(b.getName());
                        }
                        adapter = new BuyerAdapter(foundBuyers, getApplicationContext());
                        System.out.println("Made the adapter");
                        listView.setAdapter(adapter);
                        System.out.println("Set the adapter");
                    }

                    @Override
                    public void onFailure(Call<List<Buyer>> call, Throwable t) {
                        System.out.println("Failed to call Rest");
                        t.printStackTrace();
                    }
                });


                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
    }


}
