package winarwahyuw.winw.githubuser.view_model;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import winarwahyuw.winw.githubuser.helper.MappingHelper;
import winarwahyuw.winw.githubuser.model.User;

import static winarwahyuw.winw.githubuser.database.DatabaseContract.FavoriteColumns.CONTENT_URI;

public class FavoriteViewModel extends ViewModel {
    private MutableLiveData<List<User>> favoriteUser = new MutableLiveData<>();//untuk hal Favorite

    //untuk hal favorite
    public LiveData<List<User>> getFavoriteUser() {
        return favoriteUser;
    }

    //untuk hal favorite
    public void setFavoriteUser(Context context) {

        Uri uri = Uri.parse(CONTENT_URI.toString()); //ambil semua
        Cursor cursor = context.getContentResolver().query(uri, null, null, null, null);

        Log.d("CEK", "ISI CURSOR" + cursor);
        if (cursor != null) {
            List<User> users = MappingHelper.mapCursorToArrayList(cursor);

            cursor.moveToNext();
            favoriteUser.postValue(users);

            cursor.close();
            Log.d("GET", "FAVORITE BERHASIL QUERYALL" + users);
            Log.d("GET", "FAVORITE BERHASIL QUERYALL" + favoriteUser);
        } else {
            Log.d("WARNING", "FAVORITE GAGAL DIMUAT");
        }
    }
}
