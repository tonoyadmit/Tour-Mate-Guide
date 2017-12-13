package in.digitechlab.travelmatepro;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/**
 * Created by DELL on 11/4/2017.
 */


public class TravelProvider extends ContentProvider {

    public static final String AUTHORITY_URI = "in.digitechlab.travelmatepro.travelprovider";
    public static final String CONTENT_STRING = "content://"+AUTHORITY_URI;
    public static final Uri CONTENT_URI= Uri.parse(CONTENT_STRING);

    public static final UriMatcher sURI_MATCHER;

    static {
        sURI_MATCHER = new UriMatcher(UriMatcher.NO_MATCH);
        sURI_MATCHER.addURI(AUTHORITY_URI,TravelDatabase.TABLE_USER,1);
        sURI_MATCHER.addURI(AUTHORITY_URI,TravelDatabase.TABLE_EVENT,2);
        sURI_MATCHER.addURI(AUTHORITY_URI,TravelDatabase.TABLE_EXPENSE,3);
        sURI_MATCHER.addURI(AUTHORITY_URI,TravelDatabase.TABLE_MOMENT,4);
    }


    private TravelDatabase travelDatabase;
    private SQLiteDatabase sqLiteDatabase;

    public TravelProvider() {
    }


    @Override
    public boolean onCreate() {
        travelDatabase = new TravelDatabase(getContext());
        sqLiteDatabase = travelDatabase.getWritableDatabase();
        return false;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] strings, @Nullable String s, @Nullable String[] strings1, @Nullable String s1) {
        switch (sURI_MATCHER.match(uri)){
            case 1:
                Cursor cursor = sqLiteDatabase.query(TravelDatabase.TABLE_USER,strings,s,null,null,null,null);
                return cursor;
//            case 2:
//                String id = uri.getLastPathSegment();
//                Cursor specificCursor = sqLiteDatabase.query(TodoDatabase.TABLE_TODO,projection,TodoDatabase.COL_ID+" = "+id,null,null,null,null);
//                return specificCursor;
            default:
                throw new IllegalArgumentException("invalid uri for query");
        }
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        // TODO: Implement this to handle requests to update one or more rows.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues contentValues) {

        switch (sURI_MATCHER.match(uri)){
            case 1:
                long id1 = sqLiteDatabase.insert(TravelDatabase.TABLE_USER,null,contentValues);
                Uri insertedUri = Uri.parse(CONTENT_URI+"/"+TravelDatabase.TABLE_USER+"/"+id1);
                return insertedUri;
            case 2:
                long id2 = sqLiteDatabase.insert(TravelDatabase.TABLE_EVENT,null,contentValues);
                Uri insertedEventUri = Uri.parse(CONTENT_URI+"/"+TravelDatabase.TABLE_EVENT+"/"+id2);
                return insertedEventUri;
            case 3:
                long id3 = sqLiteDatabase.insert(TravelDatabase.TABLE_EXPENSE,null,contentValues);
                Uri insertedExpenseUri = Uri.parse(CONTENT_URI+"/"+TravelDatabase.TABLE_EXPENSE+"/"+id3);
                return insertedExpenseUri;
            case 4:
                long id4 = sqLiteDatabase.insert(TravelDatabase.TABLE_MOMENT,null,contentValues);
                Uri insertedMomentUri = Uri.parse(CONTENT_URI+"/"+TravelDatabase.TABLE_MOMENT+"/"+id4);
                return insertedMomentUri;
            default:
                throw new IllegalArgumentException("invalid uri for insert");
        }
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String s, @Nullable String[] strings) {
        // TODO: Implement this to handle requests to update one or more rows.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues contentValues, @Nullable String s, @Nullable String[] strings) {
        // TODO: Implement this to handle requests to update one or more rows.
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
