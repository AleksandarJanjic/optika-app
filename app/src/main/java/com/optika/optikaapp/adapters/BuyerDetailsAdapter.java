package com.optika.optikaapp.adapters;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.optika.optikaapp.R;
import com.optika.optikaapp.activities.DisplayBuyer;
import com.optika.optikaapp.factories.RetrofitFactory;
import com.optika.optikaapp.helpers.CheckForNull;
import com.optika.optikaapp.interfaces.OrderService;
import com.optika.optikaapp.model.Order;

import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class BuyerDetailsAdapter extends BaseExpandableListAdapter {

    private HashMap <String, Order> orders;
    private List<String> titles;
    Context context;
    public static String optikaapp_userId;
    private int userId;
    private AppCompatActivity activity;

    public BuyerDetailsAdapter(Context context, List<String> titles, HashMap<String, Order> orders, int userId, AppCompatActivity activity) {
        super();
        this.context = context;
        this.titles = titles;
        this.orders = orders;
        this.userId = userId;
        this.activity = activity;
    }

    @Override
    public int getGroupCount() {
        return this.orders.size();
    }

    @Override
    public int getChildrenCount(int i) {
        return 1;
    }

    @Override
    public Object getGroup(int i) {
        return this.titles.get(i);
    }

    @Override
    public Object getChild(int i, int i1) {
        return this.orders.get(this.titles.get(i));
    }

    @Override
    public long getGroupId(int i) {
        return 0;
    }

    @Override
    public long getChildId(int i, int i1) {
        return i1;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int i, boolean b, View view, ViewGroup viewGroup) {
        String listTitle = (String) getGroup(i);
        if(view == null) {
            LayoutInflater layoutInflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = layoutInflater.inflate(R.layout.row_expandable_parent, null);
        }
        TextView title = (TextView) view.findViewById(R.id.comment_date);
        title.setText(listTitle);
        return view;
    }

    @Override
    public View getChildView(int i, int i1, boolean b, View view, ViewGroup viewGroup) {
        final Order order = (Order) getChild(i, i1);
        if(view == null) {
            LayoutInflater layoutInflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = layoutInflater.inflate(R.layout.row_expandable_child_constraint, null);
        }
        TextView purpose = (TextView) view.findViewById(R.id.naocare);
        purpose.setText(order.getHasAddition() ? R.string.naocare_za_stalno : R.string.naocare_za_rad);
        TextView od_sph = (TextView) view.findViewById(R.id.od_sph_placeholder);
        od_sph.setText(CheckForNull.checkIfNull(order.getOd_sph()));
        od_sph.setTypeface(Typeface.DEFAULT_BOLD);
        TextView od_cyl = (TextView) view.findViewById(R.id.od_cyl_placeholder);
        od_cyl.setText(CheckForNull.checkIfNull(order.getOd_cyl()));
        od_cyl.setTypeface(Typeface.DEFAULT_BOLD);
        TextView od_angle = (TextView) view.findViewById(R.id.od_angle_placeholder);
        od_angle.setText(CheckForNull.checkIfNull(order.getOd_angle()));
        TextView os_sph = (TextView) view.findViewById(R.id.os_sph_placeholder);
        os_sph.setText(CheckForNull.checkIfNull(order.getOs_sph()));
        os_sph.setTypeface(Typeface.DEFAULT_BOLD);
        TextView os_cyl = (TextView) view.findViewById(R.id.os_cyl_placeholder);
        os_cyl.setText(CheckForNull.checkIfNull(order.getOs_cyl()));
        os_cyl.setTypeface(Typeface.DEFAULT_BOLD);
        TextView os_angle = (TextView) view.findViewById(R.id.os_angle_placeholder);
        os_angle.setText(CheckForNull.checkIfNull(order.getOs_angle()));
        TextView additionText = (TextView) view.findViewById(R.id.addition_text);
        TextView additionPlaceholder = (TextView) view.findViewById(R.id.addition_placeholder);
        if(order.getHasAddition()) {
            additionPlaceholder.setText(CheckForNull.checkIfNull(order.getAddition()));
            additionPlaceholder.setTypeface(Typeface.DEFAULT_BOLD);
        } else {
            additionText.setVisibility(View.GONE);
            additionPlaceholder.setVisibility(View.GONE);
        }
        TextView type_placeholder = (TextView) view.findViewById(R.id.type_placeholder);
        if(!(order.getType() == null)) {
            type_placeholder.setText(order.getType().getType());
        }
        TextView frame_placeholder = (TextView) view.findViewById(R.id.frame_placeholder);
        frame_placeholder.setText(order.getFrame());
        TextView pd_placeholder = (TextView) view.findViewById(R.id.pd_placeholder);
        pd_placeholder.setText(order.getPd());
        ImageButton delete = (ImageButton) view.findViewById(R.id.delete_order_icon);
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Retrofit retrofit = RetrofitFactory.getRetrofit();
                OrderService orderService = retrofit.create(OrderService.class);
                Call<String> call = orderService.deleteOrder(order.getId());
                call.enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(Call<String> call, Response<String> response) {
                        Intent intent = new Intent(context, DisplayBuyer.class);
                        intent.putExtra("origin", "BuyerDetailsAdapter");
                        intent.putExtra(optikaapp_userId, userId);
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
        return view;
    }

    @Override
    public boolean isChildSelectable(int i, int i1) {
        return false;
    }
}
