package com.example.ali.ybuhw2;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

public class tab2 extends Fragment {
    private ListView lv,listView;
    public ArrayList linklist= new ArrayList();
    public ArrayList liste= new ArrayList();
    private ArrayAdapter<String> adapter;
    private static String URL="http://www.ybu.edu.tr/muhendislik/bilgisayar/";
    private ProgressDialog progressDialog;


    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.tab2, container, false);
        lv=(ListView)rootView.findViewById(R.id.list);
        TextView text= (TextView)rootView.findViewById(R.id.text);

        adapter=new ArrayAdapter<String>(getActivity(),android.R.layout.simple_list_item_1,liste);
        new Takeinfo().execute();
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i = new Intent(getActivity(),content.class);
                i.putExtra("alibaba" ,linklist.get(position).toString());
                getActivity().startActivity(i);

            }
        });
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
                Document doc= Jsoup.connect(URL).timeout(30*1000).get();
                liste.clear();


                Elements name=doc.select("div.caContent > div[class=cncItem]");
                Elements linkname=doc.select("div.caContent > div[class=cncItem] a[href]");

                for (int i=0;i<name.size();i++){


                    liste.add(name.get(i).text());
                    linklist.add(linkname.get(i).attr("abs:href"));


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


    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Toast.makeText(getActivity(),"sdsadsadas",Toast.LENGTH_LONG).show();
        String itemChosen = (String) parent.getItemAtPosition(position);
        Intent intent = new Intent(getActivity(), tab2.class);
        intent.putExtra("groceryItem", itemChosen);
        startActivity(intent);

    }


}
