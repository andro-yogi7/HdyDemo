package com.heady.headydemoapp.view.activity;

import android.annotation.SuppressLint;
import android.app.Activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.heady.headydemoapp.Utility;
import com.heady.headydemoapp.dao.Dao;
import com.heady.headydemoapp.dao.DatabaseHelper;
import com.heady.headydemoapp.R;
import com.heady.headydemoapp.adapter.CategoryAdapter;
import com.heady.headydemoapp.model.ApiResponse;
import com.heady.headydemoapp.rest.ApiClient;
import com.heady.headydemoapp.rest.ApiInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends Activity implements AdapterView.OnItemClickListener {

   // @Bind(R.id.list_cat)
    ListView list_cat;
    private Cursor c;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //ButterKnife.bind(this);
        initDb();
        callApi();
        populateList();
     }

    private void initDb() {
         list_cat = findViewById(R.id.list_cat);
         //progressBar = findViewById(R.id.progress_bar);
    }

    private void callApi() {
       if(Utility.isNetworkAvailable(this)) {
           ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
           Call<ApiResponse> call = apiService.getData();
           showProgress();
           call.enqueue(new Callback<ApiResponse>() {
               @Override
               public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                   if (response.isSuccessful()) {
                       Log.e("String response:", "" + response.body().getCategories().size());
                       //categoryDao.insertAll(response.body().getCategories().toArray(new Category[0]));
                       Dao dao = new Dao(MainActivity.this);
                       dao.insertCat(response.body().getCategories());
                       dao.insertRanking(response.body().getRankings());

                       c = dao.getListCursor();
                       list_cat.setAdapter(new CategoryAdapter(MainActivity.this, c, true));
                       if(progressDialog!=null) progressDialog.dismiss();
                   }
               }//success

               @Override
               public void onFailure(Call<ApiResponse> call, Throwable t) {
                   Log.e("String response:", "Failure" + t.getMessage());
                   if(progressDialog!=null)progressDialog.dismiss();
               }//onFailure
           });
       }else{
           Toast.makeText(this, "No connection.", Toast.LENGTH_LONG).show();
       }
    }
    private void populateList(){
                           //Log.e("Count", "" + categoryDao.countCategory());
      /*  categoryDao = AppDataBase.
        directorsLiveData = directorDao.getAllDirectors();*/
       // list_cat.setAdapter(new CategoryDao().getAllCategories());
        list_cat.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
         c.moveToPosition(i);
         int item_cat = c.getInt(c.getColumnIndex(DatabaseHelper.COLOUMN_CID));
         Log.e("Clicked","" + item_cat);
         startActivity(new Intent(MainActivity.this, ProductListActivity.class).putExtra("prod", item_cat));
    }

    public void showProgress(){
        progressDialog = new ProgressDialog(MainActivity.this);
        //progressDialog.setMax(100);
        progressDialog.setMessage("Connecting....");
        //progressDialog.setTitle("ProgressDialog bar example");
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.create();
        progressDialog.show();
    }
}
