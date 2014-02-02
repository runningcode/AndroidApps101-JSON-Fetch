package edu.illinois.coursera.android.json;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import edu.illinois.coursera.android.json.models.Item;

public class ItemListAdapter extends ArrayAdapter<Item> {

    private LayoutInflater mLayoutInflater;

    public ItemListAdapter(Context context) {
        super(context, R.layout.row_item);
        mLayoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;

        if (convertView == null) {
            convertView = mLayoutInflater.inflate(R.layout.row_item, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.name = (TextView) convertView.findViewById(R.id.name);
            viewHolder.quantity = (TextView) convertView.findViewById(R.id.quantity);
            viewHolder.price = (TextView) convertView.findViewById(R.id.price);
            viewHolder.weight = (TextView) convertView.findViewById(R.id.weight);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        Item item = getItem(position);
        if (item != null) {
            viewHolder.name.setText(item.getName());
            viewHolder.quantity.setText(String.valueOf(item.getQuantity()));
            viewHolder.price.setText(item.getPrice());
            viewHolder.weight.setText(item.getWeight());
        }
        return convertView;
    }

    public void setData(List<Item> data) {
        if (data != null) {
            clear();
            // we add each item individually instead of using addAll() for backward compatibility
            for (Item item : data) {
                add(item);
            }
        }
    }

    private class ViewHolder {
        private TextView name;
        private TextView quantity;
        private TextView price;
        private TextView weight;
    }
}
