package com.optika.optikaapp.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;

import com.optika.optikaapp.R;
import com.optika.optikaapp.adapters.BuyerAdapter;
import com.optika.optikaapp.factories.RetrofitFactory;
import com.optika.optikaapp.interfaces.BuyerService;
import com.optika.optikaapp.interfaces.UtilService;
import com.optika.optikaapp.model.Buyer;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class SearchActivity extends AppCompatActivity{

    public ListView listView;
    private static BuyerAdapter adapter;
    List<Buyer> foundBuyers;
    public SearchView searchView;
    Call<List<Buyer>> call;
    Context context;
    public static String optikaapp_message;
    static String origin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        listView = findViewById(R.id.list_view);
        searchView = findViewById(R.id.simpleSearchView);
        context = this;



        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {

                Retrofit retrofit = RetrofitFactory.getRetrofit();

                BuyerService buyerService = retrofit.create(BuyerService.class);

                String[] split = query.split(" ");
                if(split.length > 1) {
                    String name = split[0];
                    String lastname = split[1];
                    call = buyerService.getBuyersByName(name, lastname);
                } else {
                    call = buyerService.getBuyersByName(query, null);
                }

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

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Buyer buyer = foundBuyers.get(i);
                int userId = buyer.getId();
                Intent intent = new Intent(context, DisplayBuyer.class);
                intent.putExtra("origin", "SearchActivity");
                intent.putExtra(optikaapp_message, userId);
                startActivity(intent);
            }
        });


    }


}
