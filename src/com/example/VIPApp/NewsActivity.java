package com.example.VIPApp;

import android.app.ActionBar;
import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ListView;
import com.com.vip.control.NewsAdapter;
import com.com.vip.control.ProductAdapter;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.vip.model.News;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;

/**
 * Created by hp on 03/08/2014.
 */
public class NewsActivity extends Activity {

    ArrayList<News> news = new ArrayList<News>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_news);

        ActionBar actionBar = getActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        new LoadNews().execute();
    }

    private class LoadNews extends AsyncTask<String, Long, Void> {
        protected Void doInBackground(String... urls) {

            HttpClient httpClient = new DefaultHttpClient();
            HttpGet get = new HttpGet("http://192.168.43.55:8080/VIPBackEnd/news/rest");
            get.setHeader("content-type", "application/json");

            try{

                HttpResponse resp = httpClient.execute(get);
                String respStr = EntityUtils.toString(resp.getEntity());
                Gson gson = new Gson();
                Type type = new TypeToken<ArrayList<News>>(){}.getType();
                news = gson.fromJson(respStr, type);

            }
            catch (ClientProtocolException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return null;
        }

        protected void onPostExecute(Void response) {
            MostrarProductos();
        }
    }

    private void MostrarProductos(){

        NewsAdapter ne = new NewsAdapter(news, this);
        ListView ls = (ListView) findViewById(R.id.ls_news);
        ls.setAdapter(ne);

    }
}
