package com.com.vip.control;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.VIPApp.R;
import com.vip.model.Product;

import java.util.ArrayList;

/**
 * Created by hp on 03/08/2014.
 */
public class ProductAdapter extends BaseAdapter {

    private Context ctx;
    //private ArrayList<Product> products = new ArrayList<Product>();
    private ArrayList<Product> products;

    public ProductAdapter(ArrayList<Product> _products, Context _ctx){

        products = _products;
        ctx = _ctx;
    }

    @Override
    public int getCount() {
        return products.size();
    }

    @Override
    public Object getItem(int i) {
        return products.get(i);
    }

    @Override
    public long getItemId(int i) {
        return products.get(i).getId();
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        if (view == null){
            view = LayoutInflater.from(ctx).inflate(R.layout.product_row,null);
        }
        TextView txNombre = (TextView) view.findViewById(R.id.name);
        //TextView txPrecio = (TextView) view.findViewById(R.id.price);
        ImageView img = (ImageView) view.findViewById(R.id.img);

        Product pro = this.products.get(i);

        txNombre.setText(pro.getNombre());
        //txPrecio.setText(String.valueOf(pro.getPrecio()));

        Bitmap bmp = BitmapFactory.decodeByteArray(pro.getImg(), 0, pro.getImg().length);
        img.setImageBitmap(bmp);

        return view;
    }
}
