package winarwahyuw.winw.githubuser.activity;


import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager.widget.ViewPager;

import com.bumptech.glide.Glide;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;

import java.util.Objects;

import de.hdodenhof.circleimageview.CircleImageView;
import winarwahyuw.winw.githubuser.R;
import winarwahyuw.winw.githubuser.adapter.ViewPagerAdapter;
import winarwahyuw.winw.githubuser.fragment.FollowersFragment;
import winarwahyuw.winw.githubuser.fragment.FollowingFragment;
import winarwahyuw.winw.githubuser.model.User;
import winarwahyuw.winw.githubuser.view_model.MyViewModel;

import static winarwahyuw.winw.githubuser.database.DatabaseContract.FavoriteColumns.AVATAR;
import static winarwahyuw.winw.githubuser.database.DatabaseContract.FavoriteColumns.CONTENT_URI;
import static winarwahyuw.winw.githubuser.database.DatabaseContract.FavoriteColumns.ITEM_ID;
import static winarwahyuw.winw.githubuser.database.DatabaseContract.FavoriteColumns.LOCATION;
import static winarwahyuw.winw.githubuser.database.DatabaseContract.FavoriteColumns.USERNAME;


public class DetailActivity extends AppCompatActivity {
    public final static String EXTRA_DATA = "extra_data";

    private TextView tv_username, tv_location;
    private CircleImageView iv_avatar;
    private Bundle bundle = new Bundle();
    private FloatingActionButton fb_favorite;
    public boolean state = false;
    private ProgressBar progressBar;
    private Uri uriWithId;
    private Cursor cursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        tv_username = findViewById(R.id.tv_dt_username);
        tv_location = findViewById(R.id.tv_dt_location);
        iv_avatar = findViewById(R.id.iv_dt_avatar);
        progressBar = findViewById(R.id.progressBar);
        fb_favorite = findViewById(R.id.fb_favorite);

        //get from MainActivity
        final String username = Objects.requireNonNull(getIntent().getExtras()).getString(EXTRA_DATA);

        //send Bundle
        bundle.putString(EXTRA_DATA, username);
        Log.d("CEK", "BUNDLE DETAIL" + bundle);
        //send to FollowersFragment
        FollowersFragment fragment = new FollowersFragment();
        fragment.setArguments(bundle);
        Log.d("CEK", "BUNDLE TO FOLLOWERS FRAGMENT" + bundle.toString());
        //send to FollowingFragment
        FollowingFragment mfragment = new FollowingFragment();
        mfragment.setArguments(bundle);
        Log.d("CEK", "BUNDLE TO FOLLOWING FRAGMENT" + bundle.toString());

        progressBar.setVisibility(View.VISIBLE);

        final MyViewModel mViewModel = new ViewModelProvider(this, new ViewModelProvider.NewInstanceFactory()).get(MyViewModel.class);
        mViewModel.getUserDetail().observe(this, new Observer<User>() {
            @Override
            public void onChanged(final User user) {
                String avatar_url = "https://avatars1.githubusercontent.com/u/" + user.getItem_id() + "?v=4";

                tv_username.setText(user.getUsername());

                if (user.getLocation() != null) {
                    tv_location.setText(user.getLocation());
                } else {
                    tv_location.setText(R.string.location_unknown);
                }
                Glide.with(getApplicationContext())
                        .load(avatar_url)
                        .into(iv_avatar);
                if (getSupportActionBar() != null) {
                    getSupportActionBar().setTitle(user.getUsername());
                }
                progressBar.setVisibility(View.GONE);
                Log.d("CEK", "KOSONGKAH" + user.getItem_id());

                final String[] mSelectionArgs = {user.getItem_id().toString()};
                uriWithId = Uri.parse(CONTENT_URI + "/" + user.getItem_id());
                cursor = getApplicationContext().getContentResolver().query(uriWithId, null, ITEM_ID, mSelectionArgs, null);

                Log.d("CEK", "CURSOR : " + Objects.requireNonNull(cursor).getCount());
                if (Objects.requireNonNull(cursor).getCount() > 0) {
                    fb_favorite.setImageResource(R.drawable.ic_favorite_white_24dp);
                }

                //Add to favorite
                fb_favorite.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Log.d("FB", "Clicked");

                        if (cursor.getCount() < 1) {
                            if (state) {
                                getContentResolver().delete(uriWithId, ITEM_ID, mSelectionArgs); //dihapus dari database
                                Toast.makeText(DetailActivity.this, "Deleted from favorite", Toast.LENGTH_SHORT).show();

                                Log.d("CEK", "DATA DIHAPUS" + user.getItem_id().toString());
                            } else {
                                ContentValues values = new ContentValues();

                                values.put(ITEM_ID, user.getItem_id());
                                values.put(USERNAME, user.getUsername());
                                values.put(AVATAR, user.getAvatar_url());
                                if (user.getLocation() == null) {
                                    values.put(LOCATION, R.string.location_unknown);
                                } else {
                                    values.put(LOCATION, user.getLocation());
                                }
                                getContentResolver().insert(uriWithId, values); //masuk ke database
                                Toast.makeText(DetailActivity.this, "Added to Favorite", Toast.LENGTH_SHORT).show();
                            }
                            state = !state;
                            setIcon();
                        } else {
                            getContentResolver().delete(uriWithId, ITEM_ID, mSelectionArgs); //dihapus dari database
                            fb_favorite.setImageResource(R.drawable.ic_favorite_border_white_24dp);
                            Toast.makeText(DetailActivity.this, "Deleted from Favorite", Toast.LENGTH_SHORT).show();

                            Log.d("CEK", "DATA DIHAPUS ELSE" + user.getItem_id().toString());
                        }

                    }
                });
            }
        });

        mViewModel.setUserDetail(username);

        setTabs();

        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(R.string.detail_user);

    }

    //setTabLayout&viewpager
    public void setTabs() {
        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager(), bundle, this);
        ViewPager viewPager = findViewById(R.id.view_pager);
        viewPager.setAdapter(viewPagerAdapter);

        TabLayout tabLayout = findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);

        Objects.requireNonNull(getSupportActionBar()).setElevation(0);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    private void setIcon() {
        if (state) {
            fb_favorite.setImageResource(R.drawable.ic_favorite_white_24dp);
        } else {
            fb_favorite.setImageResource(R.drawable.ic_favorite_border_white_24dp);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
