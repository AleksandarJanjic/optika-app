package com.optika.optikaapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.optika.optikaapp.R;
import com.optika.optikaapp.model.Buyer;

import java.util.List;

public class BuyerAdapter extends ArrayAdapter<Buyer> implements View.OnClickListener {

    private List<Buyer> buyers;
    Context context;

    private static class ViewHolder {
        TextView name;
        TextView lastname;
        TextView phoneNum;
        int id;

        public void setId(int id) {
            this.id = id;
        }

        public int getId() {
            return this.id;
        }
    }

    public BuyerAdapter(List<Buyer> buyers, Context context) {
        super(context, R.layout.row_item, buyers);
        this.buyers = buyers;
        this.context = context;
    }

    public BuyerAdapter(Context context, int resource) {
        super(context, resource);
    }

    @Override
    public void onClick(View view) {

    }

    private int lastPosition = -1;

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Buyer buyer = getItem(position);
        ViewHolder viewHolder;

        if(convertView == null) {
            viewHolder = new ViewHolder();
            LayoutInflater layoutInflater = LayoutInflater.from(getContext());
            convertView = layoutInflater.inflate(R.layout.row_item, parent, false);
            viewHolder.name = (TextView) convertView.findViewById(R.id.name);
            viewHolder.lastname = (TextView) convertView.findViewById(R.id.lastname);
            viewHolder.phoneNum = (TextView) convertView.findViewById(R.id.phoneNum);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        lastPosition = position;

        viewHolder.name.setText(buyer.getName());
        viewHolder.lastname.setText(buyer.getLastname());
        StringBuilder sb = new StringBuilder();
        for (String p: buyer.getPhoneNums()
             ) {
            sb.append(p);
            sb.append(" - ");
        }
        viewHolder.phoneNum.setText(sb.toString());
        viewHolder.setId(buyer.getId());
        System.out.println("Made View with following data: " + buyer.getName() + " " + buyer.getLastname() + " " + buyer.getPhoneNums());

        return convertView;
    }
}
