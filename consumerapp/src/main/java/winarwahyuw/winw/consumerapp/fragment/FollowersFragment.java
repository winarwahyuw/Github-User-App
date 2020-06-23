package winarwahyuw.winw.consumerapp.fragment;


import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import winarwahyuw.winw.consumerapp.R;
import winarwahyuw.winw.consumerapp.adapter.FollowersAdapter;
import winarwahyuw.winw.consumerapp.model.User;
import winarwahyuw.winw.consumerapp.view_model.MyViewModel;

import static winarwahyuw.winw.consumerapp.activity.MainActivity.EXTRA_DATA;

/**
 * A simple {@link Fragment} bclass.
 */
public class FollowersFragment extends Fragment {
    private TextView tv_followers;
    private FollowersAdapter mAdapter;
    private ArrayList<User> itemArray = new ArrayList<>();

    public FollowersFragment() {
        // Required empty public constructor
        Log.d("CEK", "FOLLOWERS FRAGMENT CREATED");
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_followers, container, false);

        tv_followers = view.findViewById(R.id.tv_have_no_followers);

        //get data from DetailActivity
        String username = null;
        if (getArguments() != null) {
            username = getArguments().getString(EXTRA_DATA);
        }

        Log.d("CEK", "BUNDLE FRAGMENT" + Objects.requireNonNull(getArguments()).getString(EXTRA_DATA));

        mAdapter = new FollowersAdapter(itemArray, getContext());

        RecyclerView recyclerView = view.findViewById(R.id.rv_followers);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(mAdapter);

        final MyViewModel myViewModel = new ViewModelProvider(this, new ViewModelProvider.NewInstanceFactory()).get(MyViewModel.class);
        myViewModel.getFollowersList().observe(this, new Observer<List<User>>() {
            @Override
            public void onChanged(List<User> users) {
                if (users == null) {
                    tv_followers.setText(R.string.have_no_followers);
                } else {
                    mAdapter.setFollowersData(users);
                }

            }
        });

        myViewModel.setFollowersList(username);

        return view;

    }
}
