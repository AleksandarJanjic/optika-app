package com.optika.optikaapp.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.optika.optikaapp.R;
import com.optika.optikaapp.activities.DisplayBuyer;
import com.optika.optikaapp.factories.RetrofitFactory;
import com.optika.optikaapp.interfaces.ContactService;
import com.optika.optikaapp.model.Contact;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class ContactsAdapter extends ArrayAdapter<Contact> implements View.OnClickListener {

    List<Contact> contacts;
    Context context;
    public static String optikaapp_userId;
    private AppCompatActivity activity;

    public ContactsAdapter(Context context, int resource) {
        super(context, resource);
    }

    @Override
    public void onClick(View view) {

    }

    private static class ViewHolder {
        TextView phoneNumber;
        ImageButton deleteIcon;
    }

    public ContactsAdapter(List<Contact> data, Context context, AppCompatActivity activity) {
        super(context, R.layout.row_contact , data);
        this.contacts = data;
        this.context = context;
    }

    private int lastPosition = -1;

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final Contact contact = getItem(position);
        ViewHolder viewHolder;

        if(convertView == null) {
            viewHolder = new ViewHolder();
            LayoutInflater layoutInflater = LayoutInflater.from(getContext());
            convertView = layoutInflater.inflate(R.layout.row_contact, parent, false);
            viewHolder.phoneNumber = (TextView) convertView.findViewById(R.id.phone_number);
            viewHolder.deleteIcon = (ImageButton) convertView.findViewById(R.id.delete_icon);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ContactsAdapter.ViewHolder) convertView.getTag();
        }

        lastPosition = position;
        viewHolder.phoneNumber.setText(contact.getPhoneNum());
        viewHolder.deleteIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Retrofit retrofit = RetrofitFactory.getRetrofit();
                ContactService contactService = retrofit.create(ContactService.class);
                Call<String> call = contactService.deleteContact(contact.getId());
                call.enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(Call<String> call, Response<String> response) {
                        Intent intent = new Intent(context, DisplayBuyer.class);
                        intent.putExtra("origin", "ContactsAdapter");
                        intent.putExtra(optikaapp_userId, contact.getBuyer());
                        intent.addFlags(intent.FLAG_ACTIVITY_NEW_TASK);
                        context.startActivity(intent);
                        activity.finish();
                    }

                    @Override
                    public void onFailure(Call<String> call, Throwable t) {

                    }
                });
            }
        });
        return convertView;
    }
}
