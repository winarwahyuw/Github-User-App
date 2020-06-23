package winarwahyuw.winw.consumerapp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import winarwahyuw.winw.consumerapp.R;
import winarwahyuw.winw.consumerapp.adapter.FavoriteAdapter;
import winarwahyuw.winw.consumerapp.model.User;
import winarwahyuw.winw.consumerapp.view_model.FavoriteViewModel;

import static winarwahyuw.winw.consumerapp.activity.MainActivity.EXTRA_DATA;

public class FavoriteActivity extends AppCompatActivity {

    private FavoriteAdapter mAdapter;
    private ArrayList<User> userArray = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite);

        mAdapter = new FavoriteAdapter(getApplicationContext(), userArray);
        RecyclerView recyclerView = findViewById(R.id.rv_favorite);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(FavoriteActivity.this));
        recyclerView.setAdapter(mAdapter);

        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(R.string.favorite_user);

        FavoriteViewModel mViewModel = new ViewModelProvider(this, new ViewModelProvider.NewInstanceFactory()).get(FavoriteViewModel.class);
        mViewModel.getFavoriteUser().observe(this, new Observer<List<User>>() {
            @Override
            public void onChanged(List<User> users) {
                mAdapter.setUserData(users);
                mAdapter.OnItemClickListener(new FavoriteAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(User user) {
                        Bundle bundle = new Bundle();
                        bundle.putString(EXTRA_DATA, user.getUsername());

                        Log.d("DATA", "CEK BUNDLE FAVORITE: " + user.getUsername());

                        Intent intent = new Intent(FavoriteActivity.this, DetailActivity.class);
                        intent.putExtras(bundle);
                        startActivity(intent);
                    }
                });
            }
        });

        mViewModel.setFavoriteUser(getApplicationContext());

    }


    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
