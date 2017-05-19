package com.example.ali.ybuhw2;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;

import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;

/**
 * Created by Ali on 13.5.2017.
 */

public class content extends Activity {
    String Title;
    StringBuilder sb;
    private static String URL = "";
    private ProgressDialog progressDialog;
    private ListView lv,listView;
    TextView tx,txtitle;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.content);
        Intent intent = getIntent();
        String message = intent.getStringExtra("alibaba");
        tx = (TextView) findViewById(R.id.text1);

        txtitle = (TextView) findViewById(R.id.title1);
        URL = message;
        new TakeContent().execute();

    }


    private class TakeContent extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

          /*  progressDialog = new ProgressDialog(getApplicationContext());
            progressDialog.setTitle("HABERLER");
            progressDialog.setMessage("Lütfen bekleyiniz..");
            progressDialog.setIndeterminate(false);
            progressDialog.show();*/

        }


        @Override
        protected Void doInBackground(Void... voids) {

            try {
                Document doc = Jsoup.connect(URL).timeout(30 * 1000).get();

                Elements title = doc.select("title");
                 Title = title.get(0).text();
                Elements content= doc.select("div[id=content_left]  p");
                sb = new StringBuilder(content.size());
                for (int i=0;i<content.size();i++){


                    sb.append(content.get(i).text());


                }




            } catch (IOException e) {
                e.printStackTrace();
            }


            return null;
        }


        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            txtitle.setText(Title);
            tx.setText(sb.toString());
        }
    }
}