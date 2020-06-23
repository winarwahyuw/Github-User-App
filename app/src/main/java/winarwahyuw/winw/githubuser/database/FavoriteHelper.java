package winarwahyuw.winw.githubuser.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import static winarwahyuw.winw.githubuser.database.DatabaseContract.FavoriteColumns.ITEM_ID;
import static winarwahyuw.winw.githubuser.database.DatabaseContract.TABLE_FAVORITE;

public class FavoriteHelper {
    private static final String DATABASE_TABLE = TABLE_FAVORITE;
    private static DatabaseHelper databaseHelper;
    private static FavoriteHelper INSTANCE;
    private static SQLiteDatabase database;

    private FavoriteHelper(Context context) {
        databaseHelper = new DatabaseHelper(context);
    }

    public static FavoriteHelper getInstance(Context context) {
        if (INSTANCE == null) {
            synchronized (SQLiteOpenHelper.class) {
                if (INSTANCE == null) {
                    INSTANCE = new FavoriteHelper(context);
                }
            }
        }
        return INSTANCE;
    }

    public void open() throws SQLException {
        database = databaseHelper.getWritableDatabase();
    }

    public void read() {
        database = databaseHelper.getReadableDatabase();
    }

//    public void close() {
//        databaseHelper.close();
//        if (database.isOpen()) {
//            database.close();
//        }
//    }

    //berhasil
    public Cursor queryAll() {
        return database.query(DATABASE_TABLE,
                null,
                null,
                null,
                null,
                null,
                ITEM_ID + " ASC",
                null);
    }

    public Cursor queryById(String item_id) {
        return database.query(DATABASE_TABLE,
                null,
                ITEM_ID + " = ?",
                new String[]{item_id},
                null,
                null,
                null,
                null);
    }

    //baru
    public long insert(ContentValues values) {
        return database.insert(DATABASE_TABLE, null, values);
    }

    //baru
    public int delete(String item_id) {
        return database.delete(DATABASE_TABLE, ITEM_ID + " = ?", new String[]{item_id});
    }

}
