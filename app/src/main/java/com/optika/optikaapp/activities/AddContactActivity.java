package com.optika.optikaapp.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.LinearLayout;

import com.optika.optikaapp.R;
import com.optika.optikaapp.factories.RetrofitFactory;
import com.optika.optikaapp.fragments.ContactFragment;
import com.optika.optikaapp.fragments.SaveFragment;
import com.optika.optikaapp.helpers.Saveable;
import com.optika.optikaapp.interfaces.ContactService;
import com.optika.optikaapp.model.Contact;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class AddContactActivity extends AppCompatActivity implements Saveable {

    private int userId;
    ContactFragment contactFragment;
    SaveFragment saveFragment;
    FragmentManager fragmentManager;
    Context context;
    static String optikaapp_userId;
    String origin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_contact);

        Intent intent = getIntent();
        userId = intent.getIntExtra(DisplayBuyer.optikaapp_userid, -1);

        context = this;

        LinearLayout root = findViewById(R.id.new_order_root);

        contactFragment = new ContactFragment();
        saveFragment = new SaveFragment(this);

        fragmentManager = this.getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.add_contact_root, contactFragment);
        fragmentTransaction.add(R.id.add_contact_root, saveFragment);
        fragmentTransaction.commit();
    }

    @Override
    public void save() {
        Retrofit retrofit = RetrofitFactory.getRetrofit();
        ContactService contactService = retrofit.create(ContactService.class);
        Call<String> call = contactService.addContact(userId, contactFragment.getPhone());
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                Intent intent = new Intent(context, DisplayBuyer.class);
                intent.putExtra("origin", "AddContactActivity");
                intent.putExtra(optikaapp_userId, userId);
                startActivity(intent);
                finish();
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {

            }
        });
    }
}
