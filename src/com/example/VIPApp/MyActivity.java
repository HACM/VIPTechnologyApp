package com.example.VIPApp;

import android.app.ActionBar;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.widget.DrawerLayout;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.*;
import com.com.vip.control.NavigationAdapter;
import com.com.vip.control.ProductAdapter;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.vip.model.ItemNavigation;
import com.vip.model.Product;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;


public class MyActivity extends Activity {


    public static ArrayList<Product> products = new ArrayList<Product>();
    private ListView navList;

    private ActionBarDrawerToggle mDrawerToggle;
    private DrawerLayout mDrawerLayout;


    private String[] ti;
    private ArrayList<ItemNavigation> items;
    private TypedArray icons;
    private NavigationAdapter na;
    private ProgressDialog progDailog;
    Toast toast;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        this.navList = (ListView) findViewById(R.id.left_drawer);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);

        progDailog = new ProgressDialog(MyActivity.this);


        initNavigationDrawer();
        initToogleButom();
        cargarDatos();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_action, menu);
        return super.onCreateOptionsMenu(menu);
    }

    private class DrawerItemClickListener implements ListView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView parent, View view, int position, long id) {
            selectItem(position, view);
        }
    }

    //Navigation Drawer opciones: opciones del menú de navegación
    private void selectItem(int position, View v) {
        if(position == 2){
            Intent myIntent = new Intent(v.getContext(),
                    NewsActivity.class);
            startActivity(myIntent.putExtra(Intent.EXTRA_INTENT, MyActivity.class.getCanonicalName()));
        }
        if(position == 3){
            Intent myIntent = new Intent(v.getContext(),
                    MensajeActivity.class);
            startActivity(myIntent.putExtra(Intent.EXTRA_INTENT, MyActivity.class.getCanonicalName()));
        }
        if(position == 4){
            Intent myIntent = new Intent(v.getContext(),
                    ConfigurationActivity.class);
            startActivity(myIntent.putExtra(Intent.EXTRA_INTENT, MyActivity.class.getCanonicalName()));
        }
    }

        public void initNavigationDrawer(){
            View header = getLayoutInflater().inflate(R.layout.header_navigation,null);
            navList.addHeaderView(header);
            icons = getResources().obtainTypedArray(R.array.navigation_icon);
            ti = getResources().getStringArray(R.array.nav_options);
            items = new ArrayList<ItemNavigation>();
            items.add(new ItemNavigation(ti[0],icons.getResourceId(0,-1)));
            items.add(new ItemNavigation(ti[1],icons.getResourceId(1,-1)));
            items.add(new ItemNavigation(ti[2],icons.getResourceId(2,-1)));
            items.add(new ItemNavigation(ti[3],icons.getResourceId(3,-1)));
            na = new NavigationAdapter(this, items);
            navList.setAdapter(na);
            navList.setOnItemClickListener(new DrawerItemClickListener());

        }

        public void initToogleButom(){

            mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.drawable.ico, R.string.drawer_open,R.string.drawer_close){
                public void onDrawerClosed(View view) {
                    super.onDrawerClosed(view);
                    getActionBar().setTitle(" Technology");
                }

                public void onDrawerOpened(View drawerView) {
                    super.onDrawerOpened(drawerView);
                    getActionBar().setTitle(" Technology");
                }
            };

            mDrawerLayout.setDrawerListener(mDrawerToggle);
            getActionBar().setDisplayHomeAsUpEnabled(true);
            getActionBar().setHomeButtonEnabled(true);

    }

    public void cargarDatos(){
        new LoadProducts().execute();
    }


    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        mDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mDrawerToggle.onConfigurationChanged(newConfig);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private class LoadProducts extends AsyncTask<String, Long, Void> {
        protected Void doInBackground(String... urls) {

            HttpClient httpClient = new DefaultHttpClient();
            HttpGet get = new HttpGet("http://192.168.43.55:8080/VIPBackEnd/product/rest");
            get.setHeader("content-type", "application/json");

            try{

                HttpResponse resp = httpClient.execute(get);
                String respStr = EntityUtils.toString(resp.getEntity());
                Gson gson = new Gson();
                Type type = new TypeToken<ArrayList<Product>>(){}.getType();

                products = gson.fromJson(respStr, type);

            }
            catch (ClientProtocolException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPreExecute() {
            progDailog.setMessage("Cargando Algo Asombroso...");
            progDailog.show();
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(Void response) {
            progDailog.dismiss();

            if(products.size()==0){
                ImageView img  = (ImageView) findViewById(R.id.nose);
                img.setImageResource(R.drawable.nose);
                img.setVisibility(View.VISIBLE);
                Context context = getApplicationContext();

                toast = Toast.makeText(context, "No se pudo conectar a Internet", Toast.LENGTH_LONG);
                toast.show();

            }
            else
                MostrarProductos();
        }
    }

    private void MostrarProductos(){

        ProductAdapter pa = new ProductAdapter(products, this);
        ListView ls = (ListView) findViewById(R.id.ls_products);
        ls.setAdapter(pa);

        ls.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                LlamarProduct(i);
            }
        });
    }

    private void LlamarProduct(int i){
        Intent detalle = new Intent(this,DetalleProductActivity.class);
        detalle.putExtra("pos",i);
        startActivity(detalle);
    }


}
