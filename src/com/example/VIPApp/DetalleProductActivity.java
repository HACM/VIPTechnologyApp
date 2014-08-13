package com.example.VIPApp;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import com.vip.model.Product;

/**
 * Created by hp on 04/08/2014.
 */
public class DetalleProductActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detalle_product);
        init();
    }

    private void init(){
        Intent i = getIntent();
        int pos = i.getIntExtra("pos", 0);
        Product pd = MyActivity.products.get(pos);

        TextView txNombre = (TextView) findViewById(R.id.name);
        TextView txPrecio = (TextView) findViewById(R.id.price);
        TextView txDesc = (TextView) findViewById(R.id.desc);
        ImageView img = (ImageView) findViewById(R.id.img);

        txNombre.setText(pd.getNombre());
        txPrecio.setText(String.valueOf(pd.getPrecio()));
        txDesc.setText(pd.getDescripcion());

        Bitmap bmp = BitmapFactory.decodeByteArray(pd.getImg(), 0, pd.getImg().length);
        img.setImageBitmap(bmp);

    }
}
