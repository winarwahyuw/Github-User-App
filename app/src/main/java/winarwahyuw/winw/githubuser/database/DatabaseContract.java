package winarwahyuw.winw.githubuser.database;

import android.net.Uri;
import android.provider.BaseColumns;

public class DatabaseContract {
    public static String TABLE_FAVORITE = "fav";

    //content provider
    public static final String AUTHORITY = "winarwahyuw.winw.githubuser";
    private static final String SCHEME = "content";

    private DatabaseContract() {

    }

    public static final class FavoriteColumns implements BaseColumns {
        public static String ITEM_ID = "id";
        public static String USERNAME = "login";
        public static String AVATAR = "avatar";
        public static String LOCATION = "location";

        //membuat URI content://winarwahyuw.winw.githubuser/fav
        //parameter dalam Uri.parse(COntent Uri)
        public static final Uri CONTENT_URI = new Uri.Builder().scheme(SCHEME)
                .authority(AUTHORITY)
                .appendPath(TABLE_FAVORITE)
                .build();
    }
}
