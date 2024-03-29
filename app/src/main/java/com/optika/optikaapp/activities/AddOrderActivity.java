package com.optika.optikaapp.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import com.optika.optikaapp.R;
import com.optika.optikaapp.factories.RetrofitFactory;
import com.optika.optikaapp.fragments.AdditionFragment;
import com.optika.optikaapp.fragments.AngleFragment;
import com.optika.optikaapp.fragments.DetailsFragment;
import com.optika.optikaapp.fragments.DiopterFragment;
import com.optika.optikaapp.fragments.SaveFragment;
import com.optika.optikaapp.fragments.TypeFragment;
import com.optika.optikaapp.helpers.Saveable;
import com.optika.optikaapp.helpers.SaveableWithAddition;
import com.optika.optikaapp.interfaces.OrderService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class AddOrderActivity extends AppCompatActivity implements Saveable, SaveableWithAddition {

    private int userId;
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
    Context context;
    static String optikaapp_userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_order);

        Intent intent = getIntent();
        userId = intent.getIntExtra(DisplayBuyer.optikaapp_userid, -1);

        context = this;

        LinearLayout root = findViewById(R.id.new_order_root);

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
        fragmentTransaction.add(R.id.new_order_root, type);
        fragmentTransaction.add(R.id.new_order_root, od_sph_view);
        fragmentTransaction.add(R.id.new_order_root, od_cyl_view);
        fragmentTransaction.add(R.id.new_order_root, od_angle_view);
        fragmentTransaction.add(R.id.new_order_root, os_sph_view);
        fragmentTransaction.add(R.id.new_order_root, os_cyl_view);
        fragmentTransaction.add(R.id.new_order_root, os_angle_view);
        fragmentTransaction.add(R.id.new_order_root, addition_view);
        fragmentTransaction.add(R.id.new_order_root, details);
        fragmentTransaction.add(R.id.new_order_root, save);
        fragmentTransaction.commit();

    }

    @Override
    public void save() {
        Retrofit retrofit = RetrofitFactory.getRetrofit();
        OrderService orderService = retrofit.create(OrderService.class);
        Call<String> call = orderService.addOrder(
                userId,
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
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                Intent intent = new Intent(context, DisplayBuyer.class);
                intent.putExtra("origin", "AddOrderActivity");
                intent.putExtra(optikaapp_userId, userId);
                startActivity(intent);
                finish();
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {

            }
        });
    }

    @Override
    public void additionOff() {
        fragmentManager.beginTransaction().hide(addition_view).commit();
    }

    @Override
    public void additionOn() {
        fragmentManager.beginTransaction().show(addition_view).commit();
    }
}
