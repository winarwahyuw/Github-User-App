package winarwahyuw.winw.consumerapp.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;


public class User implements Parcelable {
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

    public User() {
    }

    public User(Integer item_id, String username, String avatar_url, String location) {
        this.item_id = item_id;
        this.username = username;
        this.avatar_url = avatar_url;
        this.location = location;
    }

    public User(Parcel in) {
        item_id = (Integer) in.readValue(Integer.class.getClassLoader());
        username = ((String) in.readValue((String.class.getClassLoader())));
        avatar_url = ((String) in.readValue((String.class.getClassLoader())));
        followers_url = ((String) in.readValue((String.class.getClassLoader())));
        following_url = ((String) in.readValue((String.class.getClassLoader())));
        followers = ((String) in.readValue((String.class.getClassLoader())));
        following = ((String) in.readValue((String.class.getClassLoader())));
        location = ((String) in.readValue((String.class.getClassLoader())));
    }

    public static final Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };

    public Integer getItem_id() {
        return item_id;
    }

    public void setItem_id(Integer item_id) {
        this.item_id = item_id;
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

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    @SerializedName("followers")
    private String followers;
    @SerializedName("following")
    private String following;
    @SerializedName("location")
    private String location;

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
        dest.writeValue(followers);
        dest.writeValue(following);
        dest.writeValue(location);
    }


}
