package winarwahyuw.winw.githubuser.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;


public class Item implements Parcelable {
    @SerializedName("id")
    private Integer item_id;
    @SerializedName("login")
    private String username;
    @SerializedName("avatar_url")
    private String avatar_url;
    @SerializedName("followers_url")
    private String followers_url;
    @SerializedName("following_url")
    private String following_url;
    @SerializedName("repos_url")
    private String repos_url;
    @SerializedName("starred_url")
    private String starred_url;
    @SerializedName("subscriptions_url")
    private String subscriptions_url;
    @SerializedName("organizations_url")
    private String organizations_url;

//    public Item(Integer item_id, String username, String avatar_url, String followers_url, String following_url, String repos_url, String starred_url, String subscriptions_url, String organizations_url) {
//        this.item_id = item_id;
//        this.username = username;
//        this.avatar_url = avatar_url;
//        this.followers_url = followers_url;
//        this.following_url = following_url;
//        this.repos_url = repos_url;
//        this.starred_url = starred_url;
//        this.subscriptions_url = subscriptions_url;
//        this.organizations_url = organizations_url;
//    }

    private Item(Parcel in) {
        item_id = ((Integer) in.readValue((Integer.class.getClassLoader())));
        username = ((String) in.readValue((String.class.getClassLoader())));
        avatar_url = ((String) in.readValue((String.class.getClassLoader())));
        followers_url = ((String) in.readValue((String.class.getClassLoader())));
        following_url = ((String) in.readValue((String.class.getClassLoader())));
        repos_url = ((String) in.readValue((String.class.getClassLoader())));
        starred_url = ((String) in.readValue((String.class.getClassLoader())));
        subscriptions_url = ((String) in.readValue((String.class.getClassLoader())));
        organizations_url = ((String) in.readValue((String.class.getClassLoader())));
    }

    public static final Creator<Item> CREATOR = new Creator<Item>() {
        @Override
        public Item createFromParcel(Parcel in) {
            return new Item(in);
        }

        @Override
        public Item[] newArray(int size) {
            return new Item[size];
        }
    };

    public Integer getItem_id() {
        return item_id;
    }

    public void setItem_id(Integer id) {
        this.item_id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAvatar_url() {
        return avatar_url;
    }

    public void setAvatar_url(String avatar_url) {
        this.avatar_url = avatar_url;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(item_id);
        dest.writeValue(username);
        dest.writeValue(avatar_url);
        dest.writeValue(followers_url);
        dest.writeValue(following_url);
        dest.writeValue(repos_url);
        dest.writeValue(starred_url);
        dest.writeValue(subscriptions_url);
        dest.writeValue(organizations_url);
    }

}
