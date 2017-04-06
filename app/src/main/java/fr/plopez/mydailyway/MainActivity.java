package fr.plopez.mydailyway;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.LinearLayoutManager;

import android.content.Context;
import android.os.AsyncTask;

import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;
import org.w3c.dom.Text;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    public TextView textFlux;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textFlux = (TextView)findViewById(R.id.rss_feed);

        final RecyclerView rv = (RecyclerView) findViewById(R.id.list);
        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.setAdapter(new RecyclerViewAdapter());
    }

    @Override
    protected void onStart() {
        super.onStart();
        new GetAndroidPitRssFeedTask().execute();
    }

    private String getAndroidPitRssFeed(){
        InputStream input = null;
        URL url = null;
        HttpURLConnection connexion = null;
        String rssFeed = "";
        try {
            url = new URL("https://api.jcdecaux.com/vls/v1/stations?contract=Nantes&apiKey=5b45ad73afd612e6553401c6ac3d33f276da8dfa");
            connexion = (HttpURLConnection)url.openConnection();
            input = connexion.getInputStream();
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
            for (int count; (count = input.read(buffer)) != -1; ) {
                out.write(buffer, 0, count);
            }
            byte[] response = out.toByteArray();
            rssFeed = new String(response, "UTF-8");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            connexion.disconnect();
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return rssFeed;
        }
    }


    private class GetAndroidPitRssFeedTask extends AsyncTask<Void, Void, String> {
        @Override
        protected String doInBackground(Void... voids) {
            String result = "";
            result = getAndroidPitRssFeed();
            return result;
        }
        @Override
        protected void onPostExecute(String rssFeed) {
            textFlux.setText(rssFeed);
        }
    }
}
