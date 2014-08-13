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
import com.vip.model.News;

import java.util.ArrayList;

/**
 * Created by Frander Mejía on 03/08/2014.
 */
public class NewsAdapter extends BaseAdapter {

    private Context ctx;
    private ArrayList<News> news = new ArrayList<News>();

    public NewsAdapter(ArrayList<News> _news, Context _ctx){

        news = _news;
        ctx = _ctx;
    }

    @Override
    public int getCount() {
        return news.size();
    }

    @Override
    public Object getItem(int i) {
        return news.get(i);
    }

    @Override
    public long getItemId(int i) {
        return news.get(i).getId();
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (view == null){
            view = LayoutInflater.from(ctx).inflate(R.layout.news_row,null);
        }
        TextView txNombre = (TextView) view.findViewById(R.id.name);
        ImageView img = (ImageView) view.findViewById(R.id.img);

        News n = this.news.get(i);

        txNombre.setText(n.getTitulo());
        Bitmap bmp = BitmapFactory.decodeByteArray(n.getImg(), 0, n.getImg().length);
        img.setImageBitmap(bmp);

        return view;
    }
}
