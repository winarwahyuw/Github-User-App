package winarwahyuw.winw.githubuser.activity;


import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import winarwahyuw.winw.githubuser.R;
import winarwahyuw.winw.githubuser.adapter.UsersAdapter;
import winarwahyuw.winw.githubuser.model.Item;
import winarwahyuw.winw.githubuser.view_model.MyViewModel;


public class MainActivity extends AppCompatActivity {
    public final static String EXTRA_DATA = "extra_data";

    private MyViewModel myViewModel;
    private UsersAdapter mAdapter;
    private ArrayList<Item> itemArray = new ArrayList<>();
    private TextView tv_not_found;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ProgressBar progressBar = findViewById(R.id.progressBar);
        progressBar.setVisibility(View.VISIBLE);

        tv_not_found = findViewById(R.id.tv_user_not_found);

        mAdapter = new UsersAdapter(getApplicationContext(), itemArray);

        RecyclerView recyclerView = findViewById(R.id.rv_users);
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        recyclerView.setAdapter(mAdapter);
        recyclerView.setHasFixedSize(true);

        Log.d("RECYCLER", "SET" + recyclerView);

        myViewModel = new ViewModelProvider(this, new ViewModelProvider.NewInstanceFactory()).get(MyViewModel.class);
        setSearchView();
        progressBar.setVisibility(View.GONE);
        setMyViewModelObserver();

    }

    private void setMyViewModelObserver() {
        Log.d("CEK", "View Model Observer CREATED");

        myViewModel.getAllUsers().observe(this, new Observer<List<Item>>() {
            @Override
            public void onChanged(final List<Item> items) {
                if (items.isEmpty()) {
                    isNotFound();
                } else {
                    mAdapter.setUserData(items);
                }

                Log.d("CEK", "userData" + items);

                mAdapter.OnItemClickListener(new UsersAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(Item item) {
                        Bundle bundle = new Bundle();
                        bundle.putString(EXTRA_DATA, item.getUsername());

                        Log.d("DATA", "CEK BUNDLE : " + item.getUsername());

                        Intent intent = new Intent(MainActivity.this, DetailActivity.class);
                        intent.putExtras(bundle);
                        startActivity(intent);

                    }
                });
                Log.d("OBSERVER", "CEK DATA" + items.size());
            }
        });


    }

    //Untuk mencari User
    private void setSearchView() {
        final SearchView searchView = findViewById(R.id.search_user);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                myViewModel.setAllUsers(query);
                Toast.makeText(getApplicationContext(), "Submit text", Toast.LENGTH_SHORT).show();

                Log.d("Submit", "DATA" + query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.btn_fav) {
            Intent intent = new Intent(MainActivity.this, FavoriteActivity.class);
            startActivity(intent);
        } else if (item.getItemId() == R.id.btn_setting) {
            Intent intent = new Intent(MainActivity.this, SettingActivity.class);
            startActivity(intent);
        }
        return true;
    }

    //user not found
    private void isNotFound() {
        tv_not_found.setText(R.string.user_not_found);
    }
}
