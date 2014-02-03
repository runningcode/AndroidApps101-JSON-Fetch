/*
 * Copyright (c) 2014 Nelson Osacky
 *
 * Dual licensed under Apache2.0 and MIT Open Source License (included below):
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package apps101.example.json;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import edu.illinois.coursera.android.json.R;

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
            // inflate the view if it is null
            convertView = mLayoutInflater.inflate(R.layout.row_item, parent, false);

            // store our views in a viewholder because findviewbyid is expensive
            viewHolder = new ViewHolder();
            viewHolder.name = (TextView) convertView.findViewById(R.id.name);
            viewHolder.quantity = (TextView) convertView.findViewById(R.id.quantity);
            viewHolder.price = (TextView) convertView.findViewById(R.id.price);
            viewHolder.weight = (TextView) convertView.findViewById(R.id.weight);
            convertView.setTag(viewHolder);
        } else {
            // we just saved having to do all our lookups :)
            viewHolder = (ViewHolder) convertView.getTag();
        }

        Item item = getItem(position);
        if (item != null) {
            // set the text in our views
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

    // this class holds our lookups
    private class ViewHolder {
        private TextView name;
        private TextView quantity;
        private TextView price;
        private TextView weight;
    }
}
