package winarwahyuw.winw.githubuser.helper;

import android.database.Cursor;

import java.util.ArrayList;

import winarwahyuw.winw.githubuser.model.User;

import static winarwahyuw.winw.githubuser.database.DatabaseContract.FavoriteColumns.AVATAR;
import static winarwahyuw.winw.githubuser.database.DatabaseContract.FavoriteColumns.ITEM_ID;
import static winarwahyuw.winw.githubuser.database.DatabaseContract.FavoriteColumns.LOCATION;
import static winarwahyuw.winw.githubuser.database.DatabaseContract.FavoriteColumns.USERNAME;

public class MappingHelper {
    public static ArrayList<User> mapCursorToArrayList(Cursor userCursor){
        ArrayList<User> userList=new ArrayList<>();

        while(userCursor.moveToNext()){
            Integer item_id =userCursor.getInt(userCursor.getColumnIndexOrThrow(ITEM_ID));
            String username =userCursor.getString(userCursor.getColumnIndexOrThrow(USERNAME));
            String avatar   =userCursor.getString(userCursor.getColumnIndexOrThrow(AVATAR));
            String location =userCursor.getString(userCursor.getColumnIndexOrThrow(LOCATION));

            userList.add(new User(item_id, username, avatar, location));
        }
        return userList;
    }

    public static User mapCursorToObject(Cursor userCursor){
        userCursor.moveToFirst();
        Integer item_id=userCursor.getInt(userCursor.getColumnIndexOrThrow(ITEM_ID));
        String username=userCursor.getString(userCursor.getColumnIndexOrThrow(USERNAME));
        String avatar=userCursor.getString(userCursor.getColumnIndexOrThrow(AVATAR));
        String location=userCursor.getString(userCursor.getColumnIndexOrThrow(LOCATION));

        return new User(item_id, username, avatar, location);
    }
}
