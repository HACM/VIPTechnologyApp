package com.com.vip.control;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.VIPApp.R;
import com.vip.model.ItemNavigation;

import java.util.ArrayList;

/**
 * Created by hp on 04/08/2014.
 */
public class NavigationAdapter extends BaseAdapter {

    private Context ctx;
    private ArrayList<ItemNavigation> items;

    public NavigationAdapter(Context _cx, ArrayList<ItemNavigation> _items){
        ctx =_cx;
        items =_items;
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Object getItem(int i) {
        return items.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    public static class Fila{
        TextView tv;
        ImageView img;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        Fila fila;

        if(view == null){
            fila = new Fila();
            ItemNavigation itm = items.get(i);
            view = LayoutInflater.from(ctx).inflate(R.layout.item_navigation,null);

            fila.tv = (TextView) view.findViewById(R.id.item);
            fila.tv.setText(itm.getTitulo());

            fila.img = (ImageView) view.findViewById(R.id.ico);
            fila.img.setImageResource(itm.getIco());

            view.setTag(fila);
        }
        else {

            fila = (Fila) view.getTag();

        }


        return view;
    }
}
