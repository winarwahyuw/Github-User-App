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
import winarwahyuw.winw.consumerapp.adapter.FollowingAdapter;
import winarwahyuw.winw.consumerapp.model.User;
import winarwahyuw.winw.consumerapp.view_model.MyViewModel;

import static winarwahyuw.winw.consumerapp.activity.MainActivity.EXTRA_DATA;

/**
 * A simple {@link Fragment} subclass.
 */
public class FollowingFragment extends Fragment {
    private TextView tv_following;
    private FollowingAdapter mAdapter;
    private ArrayList<User> itemArray = new ArrayList<>();

    public FollowingFragment() {
        // Required empty public constructor
        Log.d("CEK", "FOLLOWING FRAGMENT CREATED");
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_following, container, false);

        tv_following = view.findViewById(R.id.tv_have_no_following);

        //get data from DetailActivity
        String username = null;
        if (getArguments() != null) {
            username = getArguments().getString(EXTRA_DATA);
        }

        Log.d("CEK", "BUNDLE FRAGMENT" + Objects.requireNonNull(getArguments()).getString(EXTRA_DATA));
        mAdapter = new FollowingAdapter(itemArray, getContext());

        RecyclerView recyclerView = view.findViewById(R.id.rv_following);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(mAdapter);

        final MyViewModel myViewModel = new ViewModelProvider(this, new ViewModelProvider.NewInstanceFactory()).get(MyViewModel.class);
        myViewModel.getFollowingList().observe(this, new Observer<List<User>>() {
            @Override
            public void onChanged(List<User> items) {
                if (items == null) {
                    tv_following.setText(R.string.have_no_following);
                } else {
                    mAdapter.setFollowingData(items);
                }

            }
        });

        myViewModel.setFollowingList(username);

        return view;
    }

}
