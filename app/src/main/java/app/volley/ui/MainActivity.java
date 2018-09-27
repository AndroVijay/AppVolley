package app.volley.ui;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import com.android.volley.Request;

import java.util.ArrayList;

import app.volley.R;
import app.volley.adapters.DataAadpter;
import app.volley.constants.ApiConstants;
import app.volley.listener.onUpdateViewListener;
import app.volley.network.NetworkEngine;
import app.volley.response.Hero;
import app.volley.response.ListHero;

public class MainActivity extends AppCompatActivity implements onUpdateViewListener {

    private RecyclerView recyclerView;
    private ArrayList<Hero> heroArrayList;
    private DataAadpter dataAadpter;
    private ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recyclerview);
        final LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        dialog = new ProgressDialog(this);
        heroArrayList = new ArrayList<>();
        hitApiRequest(ApiConstants.REQUEST_TYPE.MY_URL);


    }

    private void hitApiRequest(int reqType) {

        try {

            Class clasz = null;
            String url = "";
            dialog.setMessage("Loading...");
            dialog.show();

            switch (reqType) {
                case ApiConstants.REQUEST_TYPE.MY_URL:

                    clasz = ListHero.class;

                    // api request
                    url = ApiConstants.Urls.MY_URL;
                    url = url.replace(" ", "%20");
                    Log.v("url-->> ", url);
                    break;
                default:
                    break;

            }
            NetworkEngine.with(this).setClassType(clasz).setUrl(url).setRequestType(reqType).setHttpMethodType(Request.Method.GET).setUpdateViewListener(this).build();


        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    @Override
    public void updateView(Object responseObject, boolean isSuccess, int reqType) {

        try {
            dialog.hide();
            switch (reqType) {
                case ApiConstants.REQUEST_TYPE.MY_URL:
                    ListHero gameListResponse = (ListHero) responseObject;
                    try {

                        heroArrayList = gameListResponse.getHeroes();
                        //dataAadpter.setData(this, heroArrayList);
                        senddata(heroArrayList);

                    } catch (Exception e) {
                        dialog.hide();
                    }
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void senddata(ArrayList<Hero> heroArrayList) {

        dataAadpter = new DataAadpter(this, heroArrayList);
        recyclerView.setAdapter(dataAadpter);
        dataAadpter.setData(this, heroArrayList);
    }
}
