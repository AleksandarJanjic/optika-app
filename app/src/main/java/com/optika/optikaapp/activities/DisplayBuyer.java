package com.optika.optikaapp.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.view.ViewCompat;

import android.content.Intent;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.optika.optikaapp.R;
import com.optika.optikaapp.adapters.BuyerDetailsAdapter;
import com.optika.optikaapp.interfaces.BuyerService;
import com.optika.optikaapp.interfaces.OrderService;
import com.optika.optikaapp.model.Buyer;
import com.optika.optikaapp.model.Order;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class DisplayBuyer extends AppCompatActivity {

    public int userId;
    public Buyer resultBuyer;
    public ConstraintLayout constraintLayout;
    public HashMap<String, Order> orders;
    public List<String> orderTitles;
    public BuyerDetailsAdapter buyerDetailsAdapter;
    public LinearLayout linearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_buyer);

        Intent intent = getIntent();
        userId = intent.getIntExtra(SearchActivity.optikaapp_message, -1);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.1.26:8080/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();

        BuyerService buyerService = retrofit.create(BuyerService.class);

        Call<Buyer> call = buyerService.getBuyerById(userId);

        call.enqueue(new Callback<Buyer>() {
            @Override
            public void onResponse(Call<Buyer> call, Response<Buyer> response) {
                System.out.println("REST Call getBuyerById ON RESPONSE");
                resultBuyer = response.body();
                System.out.println(resultBuyer.getName());
                System.out.println(resultBuyer.getLastname());
                constraintLayout = (ConstraintLayout) findViewById(R.id.constraint_layout_buyer_display);
                linearLayout = (LinearLayout) findViewById(R.id.buyer_linear_layout);
                // Add Name
                TextView name = new TextView(getApplicationContext());
                name.setLayoutParams(new ConstraintLayout.LayoutParams(
                        ViewGroup.LayoutParams.WRAP_CONTENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT
                ));

                name.setText(resultBuyer.getName());
                linearLayout.addView(name);
                // Add Lastname if available
                if(!(resultBuyer.getLastname() == null)) {
                    TextView lastname = new TextView(getApplicationContext());
                    lastname.setLayoutParams(new ConstraintLayout.LayoutParams(
                            ViewGroup.LayoutParams.WRAP_CONTENT,
                            ViewGroup.LayoutParams.WRAP_CONTENT
                    ));
                    lastname.setText(resultBuyer.getLastname());
                    linearLayout.addView(lastname);
                }
                // Add Phone numbers if available
                if(!(resultBuyer.getPhoneNums() == null)) {
                    TextView phonenums = new TextView(getApplicationContext());
                    phonenums.setLayoutParams(new ConstraintLayout.LayoutParams(
                            ViewGroup.LayoutParams.WRAP_CONTENT,
                            ViewGroup.LayoutParams.WRAP_CONTENT
                    ));
                    phonenums.setText(resultBuyer.getPhoneNums());
                    linearLayout.addView(phonenums);
                }

//                constraintLayout.addView(linearLayout);

                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl("http://192.168.1.26:8080/")
                        .addConverterFactory(GsonConverterFactory.create())
                        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                        .build();

                OrderService orderService = retrofit.create(OrderService.class);
                final Call<HashMap<String, Order>> callOrder = orderService.getOrdersById(resultBuyer.getId());

                callOrder.enqueue(new Callback<HashMap<String, Order>>() {
                    @Override
                    public void onResponse(Call<HashMap<String, Order>> call, Response<HashMap<String, Order>> response) {
                        orders = response.body();
                        if(!(orders == null)) {
                            ExpandableListView expandableListView = (ExpandableListView) findViewById(R.id.expandable_list);
                            orderTitles = new ArrayList<String>(orders.keySet()) {
                            };
                            buyerDetailsAdapter = new BuyerDetailsAdapter(getApplicationContext(), orderTitles, orders);
                            expandableListView.setAdapter(buyerDetailsAdapter);
                        }
                    }

                    @Override
                    public void onFailure(Call<HashMap<String, Order>> call, Throwable t) {
                        System.out.println("Rest Call to getOrderById in DisplayBuyer FAILED");
                        t.printStackTrace();
                    }
                });

            }

            @Override
            public void onFailure(Call<Buyer> call, Throwable t) {
                System.out.println("REST Call getBuyerById FAILED");
                t.printStackTrace();
            }
        });


    }
}
