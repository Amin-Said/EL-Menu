package com.example.amin.myhttprest;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.amin.myhttprest.model.Recipes;
import com.example.amin.myhttprest.parser.RecipesParser;
import com.facebook.shimmer.ShimmerFrameLayout;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    List<Recipes> recipesList;
    List<MyTask> tasks = new ArrayList<>();
    TextView output = null;
    private ShimmerFrameLayout mShimmerViewContainer;

    private final int num_list_Items = 100;
    private RecyclerAdapter mAdapter;
    private RecyclerView mNumberLists;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mShimmerViewContainer = findViewById(R.id.shimmer_view_container);
        mShimmerViewContainer.setVisibility(View.INVISIBLE);


        mNumberLists =  findViewById(R.id.recycler_view);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_get_data) {
            mShimmerViewContainer.setVisibility(View.VISIBLE);
            if (isOnline()) {
                if (tasks.size() == 0) {
                    requestData("https://api.androidhive.info/json/shimmer/menu.php");
                }

            } else {
                mShimmerViewContainer.setVisibility(View.INVISIBLE);
                Toast.makeText(MainActivity.this, "Network Unavailable", Toast.LENGTH_LONG).show();
            }
        }
        return false;
    }


    private void requestData(String uri) {
        MyTask task = new MyTask();
        task.execute(uri);

    }

    protected void updateDisplay() {


        if (recipesList != null) {

            for (Recipes recipe : recipesList) {

                // refreshing recycler view
                mAdapter = new RecyclerAdapter(this, recipesList);
                LinearLayoutManager layoutManager = new LinearLayoutManager(this);
                mNumberLists.setLayoutManager(layoutManager);
                mNumberLists.setHasFixedSize(true);
                mNumberLists.setAdapter(mAdapter);
                mAdapter.notifyDataSetChanged();
            }
        }

    }

    public boolean isOnline() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        if (netInfo != null && netInfo.isConnectedOrConnecting()) {
            return true;
        } else {
            return false;
        }
    }

    private class MyTask extends AsyncTask<String, String, String> {

        @Override
        protected void onPreExecute() {
            mShimmerViewContainer.startShimmerAnimation();
            updateDisplay();
            tasks.add(this);

        }

        @Override
        protected String doInBackground(String... strings) {
            String content = HttpManager.getData(strings[0]);
            return content;
        }

        @Override
        protected void onPostExecute(String result) {
            if (result == null) {
                Toast.makeText(MainActivity.this, "can't connect to webservice", Toast.LENGTH_LONG).show();
                return;
            }
            recipesList = RecipesParser.parseFeed(result);

            // stop animating Shimmer and hide the layout
            mShimmerViewContainer.stopShimmerAnimation();
            mShimmerViewContainer.setVisibility(View.GONE);
            updateDisplay();


        }
    }
}
