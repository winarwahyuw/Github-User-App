package winarwahyuw.winw.consumerapp.api;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import winarwahyuw.winw.consumerapp.model.Item;
import winarwahyuw.winw.consumerapp.model.User;
import winarwahyuw.winw.consumerapp.response.WrappedListResponse;

public interface ApiService {

    //using WrappedListResponse to get user
    @GET("/search/users")
    Call<WrappedListResponse<Item>> getItemList(@Query("q") String username);

    //get user detail
    @GET("/users/{username}")
    Call<User> getDetail(@Path("username") String username);

    //get followers
    @GET("/users/{username}/followers")
    Call<List<User>> getFollowers(@Path("username") String username);

    //get following
    @GET("/users/{username}/following")
    Call<List<User>> getFollowing(@Path("username") String username);

}
