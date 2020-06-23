package winarwahyuw.winw.consumerapp.adapter;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import winarwahyuw.winw.consumerapp.R;
import winarwahyuw.winw.consumerapp.fragment.FollowersFragment;
import winarwahyuw.winw.consumerapp.fragment.FollowingFragment;

public class ViewPagerAdapter extends FragmentPagerAdapter {
    private Context context;
    private Bundle bundle;


    public ViewPagerAdapter(FragmentManager fm, Bundle bundle, Context context) {
        super(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        this.bundle = bundle;
        this.context = context;
    }

    @StringRes
    private final int[] TAB_TITLES = new int[]{
            R.string.followers,
            R.string.following,
    };

    @NonNull
    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;
        switch (position) {
            case 0:
                fragment = new FollowersFragment();
                fragment.setArguments(bundle);
                Log.d("CEK", "BUNDLE VIEWPAGER FOLLOWERS" + bundle.toString());
                break;
            case 1:
                fragment = new FollowingFragment();
                fragment.setArguments(bundle);
                Log.d("CEK", "BUNDLE VIEWPAGER FOLLOWING" + bundle.toString());
                break;
        }
        return fragment;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return context.getResources().getString(TAB_TITLES[position]);

    }


    @Override
    public int getCount() {
        return 2;
    }
}
