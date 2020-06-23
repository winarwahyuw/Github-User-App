package winarwahyuw.winw.githubuser.view_model;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import winarwahyuw.winw.githubuser.api.ApiClientInstance;
import winarwahyuw.winw.githubuser.api.ApiService;
import winarwahyuw.winw.githubuser.model.Item;
import winarwahyuw.winw.githubuser.model.User;
import winarwahyuw.winw.githubuser.response.WrappedListResponse;

public class MyViewModel extends androidx.lifecycle.ViewModel {
    private MutableLiveData<List<Item>> itemList = new MutableLiveData<>(); //for items
    private MutableLiveData<User> mutableItem = new MutableLiveData<>(); //for detail
    private MutableLiveData<List<User>> followersList = new MutableLiveData<>(); //for followers
    private MutableLiveData<List<User>> followingList = new MutableLiveData<>(); //for following

    //get items
    public LiveData<List<Item>> getAllUsers() {
        Log.d("CEK", "LIVEDATA" + itemList.toString());
        return itemList;

    }

    //get detail
    public LiveData<User> getUserDetail() {
        return mutableItem;
    }

    //get followers list
    public LiveData<List<User>> getFollowersList() {
        Log.d("CEK", "LIVEDATA FOLLOWERS" + followersList.toString());
        return followersList;
    }

    //get following list
    public LiveData<List<User>> getFollowingList() {
        Log.d("CEK", "LIVEDATA FOLLOWING" + followingList.toString());
        return followingList;
    }

    //search user
    public void setAllUsers(String username) {
        ApiService apiService = ApiClientInstance.getNetwork().create(ApiService.class);

        Call<WrappedListResponse<Item>> call = apiService.getItemList(username);
        call.enqueue(new Callback<WrappedListResponse<Item>>() {
            @Override
            public void onResponse(Call<WrappedListResponse<Item>> call, Response<WrappedListResponse<Item>> response) {
                WrappedListResponse<Item> items;

                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        items = response.body();
                        itemList.postValue(items.getItems());
                        //cek
                        Log.d("CEK", "onRESPONSE :" + items.getItems().size());
                    }
                }

                Log.i("TAG", "GET MUTABLE ITEMS");
            }

            @Override
            public void onFailure(Call<WrappedListResponse<Item>> call, Throwable t) {
                Log.e("TAG", "GAGAL MEMUAT" + t);
            }
        });
    }

    //get User Detail
    public void setUserDetail(String username) {
        ApiService apiService = ApiClientInstance.getNetwork().create(ApiService.class);

        Call<User> call = apiService.getDetail(username);
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (response.isSuccessful()) {
                    User user = response.body();
                    mutableItem.postValue(user);

                    //cek
                    Log.d("CEK", "DETAIL onRESPONSE: " + mutableItem);
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Log.e("TAG", "GAGAL MEMUAT DETAIL" + t);
            }
        });
    }

    //followers list
    public void setFollowersList(String username) {
        ApiService apiService = ApiClientInstance.getNetwork().create(ApiService.class);

        Call<List<User>> call = apiService.getFollowers(username);
        call.enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                List<User> user = response.body();
                followersList.postValue(user);
            }

            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {
                Log.d("CEK", "FOLLOWERS GAGAL DIMUAT", t);
            }
        });
    }

    //following list
    public void setFollowingList(String username) {
        ApiService apiService = ApiClientInstance.getNetwork().create(ApiService.class);

        Call<List<User>> call = apiService.getFollowing(username);
        call.enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                List<User> item = response.body();
                followingList.postValue(item);

                //cek
                Log.d("CEK", "FOLLOWING RESPONSE: " + followingList);
            }

            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {
                Log.d("CEK", "FOLLOWING GAGAL DIMUAT ", t);
            }
        });
    }

}

