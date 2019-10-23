package com.optika.optikaapp.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.optika.optikaapp.R;
import com.optika.optikaapp.adapters.BuyerDetailsAdapter;
import com.optika.optikaapp.adapters.ContactsAdapter;
import com.optika.optikaapp.factories.RetrofitFactory;
import com.optika.optikaapp.interfaces.BuyerService;
import com.optika.optikaapp.interfaces.OrderService;
import com.optika.optikaapp.model.Buyer;
import com.optika.optikaapp.model.Contact;
import com.optika.optikaapp.model.Order;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class DisplayBuyer extends AppCompatActivity {

    public int userId;
    public Buyer resultBuyer;
    public ConstraintLayout constraintLayout;
    public HashMap<String, Order> orders;
    public List<String> orderTitles;
    public BuyerDetailsAdapter buyerDetailsAdapter;
    public ContactsAdapter contactsAdapter;
    private Context context;
    private ImageButton addContact;
    private ImageButton addOrder;
    public static String optikaapp_userid;
    private AppCompatActivity activity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_buyer);
        activity = this;
        context = this;

        Intent intent = getIntent();
        String origin = intent.getStringExtra("origin");
        if(origin.equals("AddContactActivity")) {
            userId = intent.getIntExtra(AddContactActivity.optikaapp_userId, -1);
        } else if(origin.equals("AddOrderActivity")) {
            userId = intent.getIntExtra(AddOrderActivity.optikaapp_userId, -1);
        } else if(origin.equals("BuyerDetailsAdapter")) {
            userId = intent.getIntExtra(BuyerDetailsAdapter.optikaapp_userId, -1);
        } else if (origin.equals("ContactsAdapter")) {
            userId = intent.getIntExtra(ContactsAdapter.optikaapp_userId, -1);
        } else {
            userId = intent.getIntExtra(SearchActivity.optikaapp_message, -1);
        }

        addContact = findViewById(R.id.add_contact_existing);
        addOrder = findViewById(R.id.add_order_existing);

        Retrofit retrofit = RetrofitFactory.getRetrofit();
        BuyerService buyerService = retrofit.create(BuyerService.class);
        Call<Buyer> call = buyerService.getBuyerById(userId);

        call.enqueue(new Callback<Buyer>() {
            @Override
            public void onResponse(Call<Buyer> call, Response<Buyer> response) {
                System.out.println("REST Call getBuyerById ON RESPONSE");
                resultBuyer = response.body();
                System.out.println(resultBuyer.getName());
                System.out.println(resultBuyer.getLastname());
                constraintLayout = (ConstraintLayout) findViewById(R.id.buyer_details_constraint);

                // Add Name
                TextView name = (TextView) findViewById(R.id.name_placeholder);
                name.setText(resultBuyer.getName());

                // Add Lastname if available
                if(!(resultBuyer.getLastname() == null)) {
                    TextView lastname = (TextView) findViewById(R.id.lastname_placeholder);
                    lastname.setText(resultBuyer.getLastname());
                }
                // Add Phone numbers if available
                if(!(resultBuyer.getPhoneNums() == null)) {
                    ListView phonenums = (ListView) findViewById(R.id.contacts_placeholder);
                    contactsAdapter = new ContactsAdapter(resultBuyer.getContacts(), context, activity);
                    phonenums.setAdapter(contactsAdapter);
                }

                Retrofit retrofit = RetrofitFactory.getRetrofit();

                OrderService orderService = retrofit.create(OrderService.class);
                final Call<HashMap<String, Order>> callOrder = orderService.getOrdersById(resultBuyer.getId());

                callOrder.enqueue(new Callback<HashMap<String, Order>>() {
                    @Override
                    public void onResponse(Call<HashMap<String, Order>> call, Response<HashMap<String, Order>> response) {
                        HashMap<String, Order> temp = response.body();
                        orders = new HashMap<String, Order>();
                        for (Map.Entry<String, Order> e: temp.entrySet()
                             ) {
                            if(e.getValue().getIsDeleted() == false) {
                                orders.put(e.getKey(), e.getValue());
                            }
                        }
                        if(!(orders == null)) {
                            ExpandableListView expandableListView = (ExpandableListView) findViewById(R.id.expandable_list);
                            orderTitles = new ArrayList<String>(orders.keySet()) {
                            };
                            buyerDetailsAdapter = new BuyerDetailsAdapter(getApplicationContext(), orderTitles, orders, resultBuyer.getId(), activity);
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

    public void addContact(View view) {
        Intent intent = new Intent(context, AddContactActivity.class);
        intent.putExtra(optikaapp_userid, userId);
        startActivity(intent);
        finish();
    }

    public void addOrder(View view) {
        Intent intent = new Intent(context, AddOrderActivity.class);
        intent.putExtra(optikaapp_userid, userId);
        startActivity(intent);
        finish();
    }

    public void deleteBuyer(View view) {
        Retrofit retrofit = RetrofitFactory.getRetrofit();
        BuyerService buyerService = retrofit.create(BuyerService.class);
        Call<String> call = buyerService.deleteBuyer(userId);
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                String res = response.body();
                System.out.println(res);
                finish();
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                System.out.println("Failed to delete Buyer");
            }
        });
    }

}
