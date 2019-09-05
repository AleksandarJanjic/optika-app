package com.optika.optikaapp.activities;

import android.os.Bundle;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.optika.optikaapp.R;
import com.optika.optikaapp.factories.RetrofitFactory;
import com.optika.optikaapp.fragments.AdditionFragment;
import com.optika.optikaapp.fragments.AngleFragment;
import com.optika.optikaapp.fragments.BuyerFragment;
import com.optika.optikaapp.fragments.DetailsFragment;
import com.optika.optikaapp.fragments.DiopterFragment;
import com.optika.optikaapp.fragments.SaveFragment;
import com.optika.optikaapp.fragments.TypeFragment;
import com.optika.optikaapp.helpers.Saveable;
import com.optika.optikaapp.helpers.SaveableWithAddition;
import com.optika.optikaapp.interfaces.BuyerService;
import com.optika.optikaapp.interfaces.ContactService;
import com.optika.optikaapp.interfaces.OrderService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class NewBuyerOrder extends AppCompatActivity implements Saveable, SaveableWithAddition {

    BuyerFragment buyerDetails;
    TypeFragment type;
    DiopterFragment od_sph_view;
    DiopterFragment od_cyl_view;
    AngleFragment od_angle_view;
    DiopterFragment os_sph_view;
    DiopterFragment os_cyl_view;
    AngleFragment os_angle_view;
    AdditionFragment addition_view;
    DetailsFragment details;
    FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.order_root);
        LinearLayout root = findViewById(R.id.root);

        buyerDetails = new BuyerFragment();
        type = new TypeFragment(this);
        od_sph_view = new DiopterFragment(getResources().getString(R.string.od_sph));
        od_cyl_view = new DiopterFragment(getResources().getString(R.string.od_cyl));
        od_angle_view = new AngleFragment();
        os_sph_view = new DiopterFragment(getResources().getString(R.string.os_sph));
        os_cyl_view = new DiopterFragment(getResources().getString(R.string.os_cyl));
        os_angle_view = new AngleFragment();
        addition_view = new AdditionFragment();
        details = new DetailsFragment();
        SaveFragment save = new SaveFragment(this);

        fragmentManager = this.getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.root, buyerDetails);
        fragmentTransaction.add(R.id.root, type);
        fragmentTransaction.add(R.id.root, od_sph_view);
        fragmentTransaction.add(R.id.root, od_cyl_view);
        fragmentTransaction.add(R.id.root, od_angle_view);
        fragmentTransaction.add(R.id.root, os_sph_view);
        fragmentTransaction.add(R.id.root, os_cyl_view);
        fragmentTransaction.add(R.id.root, os_angle_view);
        fragmentTransaction.add(R.id.root, addition_view);
        fragmentTransaction.add(R.id.root, details);
        fragmentTransaction.add(R.id.root, save);
        fragmentTransaction.commit();

    }

    public void additionOff() {
        fragmentManager.beginTransaction().hide(addition_view).commit();
    }

    public void additionOn() {
        fragmentManager.beginTransaction().show(addition_view).commit();
    }

    public void save() {
        Retrofit retrofit = RetrofitFactory.getRetrofit();
        BuyerService buyerService = retrofit.create(BuyerService.class);
        Call<String> call = buyerService.addBuyer(buyerDetails.getName(), buyerDetails.getLastname());
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                final int id = Integer.valueOf(response.body());

                Retrofit retrofitContact = RetrofitFactory.getRetrofit();
                ContactService contactService = retrofitContact.create(ContactService.class);
                Call<String> callContact = contactService.addContact(id, buyerDetails.getPhoneNum());
                String test = response.body();
                callContact.enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(Call<String> call, Response<String> response) {
                        Retrofit retrofitOrder = RetrofitFactory.getRetrofit();
                        OrderService orderService = retrofitOrder.create(OrderService.class);
                        Call<String> callOrder = orderService.addOrder(
                                id,
                                details.getDate(),
                                od_sph_view.getDiopter(),
                                os_sph_view.getDiopter(),
                                od_cyl_view.getDiopter(),
                                os_cyl_view.getDiopter(),
                                od_angle_view.getAngle(),
                                os_angle_view.getAngle(),
                                details.getPD(),
                                details.getLensType(),
                                details.getFrame(),
                                details.getComment(),
                                type.getType(),
                                addition_view.getAddition()
                        );
                        callOrder.enqueue(new Callback<String>() {
                            @Override
                            public void onResponse(Call<String> call, Response<String> response) {
                                System.out.println(response.body());
                            }

                            @Override
                            public void onFailure(Call<String> call, Throwable t) {

                            }
                        });
                    }

                    @Override
                    public void onFailure(Call<String> call, Throwable t) {
                        t.printStackTrace();
                        System.out.println("Order failed");
                    }
                });
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {

            }
        });
    }
}
