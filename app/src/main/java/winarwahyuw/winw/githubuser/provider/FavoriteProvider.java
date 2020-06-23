package winarwahyuw.winw.githubuser.provider;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.net.Uri;
import android.util.Log;

import java.util.Objects;

import winarwahyuw.winw.githubuser.database.FavoriteHelper;

import static winarwahyuw.winw.githubuser.database.DatabaseContract.AUTHORITY;
import static winarwahyuw.winw.githubuser.database.DatabaseContract.FavoriteColumns.CONTENT_URI;
import static winarwahyuw.winw.githubuser.database.DatabaseContract.TABLE_FAVORITE;

public class FavoriteProvider extends ContentProvider {
    private static final int USER = 1;
    private static final int USER_ID = 2;
    private FavoriteHelper favoriteHelper;

    private static final UriMatcher sUriMatcher = new UriMatcher(UriMatcher.NO_MATCH);

    static {
        //content://winarwahyuw.winw.githubuser.fav
        sUriMatcher.addURI(AUTHORITY, TABLE_FAVORITE, USER);

        //content://winarwahyuw.winw.githubuser.fav/id
        sUriMatcher.addURI(AUTHORITY, TABLE_FAVORITE + "/#", USER_ID);
    }

    public FavoriteProvider() {
    }


    @Override
    public boolean onCreate() {
        favoriteHelper = FavoriteHelper.getInstance(getContext());
        favoriteHelper.open();
        return true;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection,
                        String[] selectionArgs, String sortOrder) {
        Cursor cursor;
        favoriteHelper.read();
        switch (sUriMatcher.match(uri)) {
            case USER:
                cursor = favoriteHelper.queryAll();
                break;
            case USER_ID:
                cursor = favoriteHelper.queryById(uri.getLastPathSegment());
                break;
            default:
                cursor = null;
                break;
        }
        return cursor;
    }

    @Override
    public String getType(Uri uri) {
        return null;
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        long added;
        if (sUriMatcher.match(uri) == USER_ID) {
            added = favoriteHelper.insert(values);
        } else {
            added = 0;
        }

        Objects.requireNonNull(getContext()).getContentResolver().notifyChange(CONTENT_URI, null);

        Log.d("CEK", "BERHASIL DITAMBAHKAN" + values);

        return Uri.parse(CONTENT_URI + "/" + added);
    }


    @Override
    public int update(Uri uri, ContentValues values, String selection,
                      String[] selectionArgs) {
        return 0;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        int deleted;
        if (sUriMatcher.match(uri) == USER_ID) {
            deleted = favoriteHelper.delete(uri.getLastPathSegment());
        } else {
            deleted = 0;
        }

        Objects.requireNonNull(getContext()).getContentResolver().notifyChange(CONTENT_URI, null);

        Log.d("CEK", "BERHASIL DIHAPUS" + uri.getLastPathSegment());
        return deleted;
    }

}
