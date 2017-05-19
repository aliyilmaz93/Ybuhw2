package com.example.ali.ybuhw2;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class tab1 extends Fragment {
    private ListView lv,listView;

    public ArrayList liste= new ArrayList();
    private ArrayAdapter<String> adapter;
    private static String URL="http://ybu.edu.tr/sks/";
    private ProgressDialog progressDialog;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.tab1, container, false);
        lv=(ListView)rootView.findViewById(R.id.list);

        adapter=new ArrayAdapter<String>(getActivity(),android.R.layout.simple_list_item_1,liste);
        new Takeinfo().execute();
        return rootView;


    }

    private class Takeinfo extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            progressDialog= new ProgressDialog(getActivity());
             progressDialog.setMessage("Lütfen bekleyiniz..");
            progressDialog.setIndeterminate(false);
            progressDialog.show();

        }



        @Override
        protected Void doInBackground(Void... voids) {

            try {


                    Document doc = Jsoup.connect(URL).timeout(30 * 1000).get();
                liste.clear();
                    Elements oyunadi = doc.select("table h5");

                    for (int i = 0; i < oyunadi.size(); i++) {

                        liste.add(oyunadi.get(i).text());

                }

            } catch (IOException e) {
                e.printStackTrace();
            }


            return null;
        }


        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

            lv.setAdapter(adapter);
            progressDialog.dismiss();

        }
    }
}
