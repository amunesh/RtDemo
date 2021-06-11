package com.nx.demo.ApiUtil;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DBhelper extends SQLiteOpenHelper {

    public static final String Datadase_Name = "Post.db";
    public static final String Table_Name = "FavPost";
    public static final String COLUMN_ID = "ID";
    public static final String Title = "COLUMN_2";
    public static final String Message = "COLUMN_3";
    public static final String LikePost = "LikePost";


    private ContentValues cValues;
    private SQLiteDatabase dataBase = null;

    public DBhelper(Context context) {
        super(context, Datadase_Name, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        sqLiteDatabase.execSQL("create table " + Table_Name + " (ID INTEGER PRIMARY KEY , " + Title + " TEXT ," + Message + " TEXT ," + LikePost + " TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + Table_Name);
        onCreate(sqLiteDatabase);

    }

    public boolean inserRecordDebit(int id, String Title1, String Message1, String like) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_ID, id);
        contentValues.put(Title, Title1);
        contentValues.put(Message, Message1);
        contentValues.put(LikePost, like);


        db.insert(Table_Name, null, contentValues);
        return true;
    }


    public boolean Delete_Record(int intid) {
        dataBase = getWritableDatabase();
        String deletedata = "Delete  from " + Table_Name + " where " + COLUMN_ID + " = " + intid + "";
        Log.e("deletedata ", "" + deletedata);
        Cursor cursor = dataBase.rawQuery(deletedata, null);


        if (cursor.getCount() == -1)
            return false;
        else
            return true;
    }

    //All Data Filtring
    public Cursor getAll() {

        dataBase = this.getWritableDatabase();
        Cursor res = dataBase.rawQuery("select * from " + Table_Name + " " + COLUMN_ID + "  ORDER BY id DESC", null);
        return res;
    }

    public void queryData(String sql) {
        SQLiteDatabase database = getWritableDatabase();
        database.execSQL(sql);
    }

    public boolean CheckData(int id) {

        try {
            dataBase = this.getReadableDatabase();
            Cursor cursor = dataBase.rawQuery("SELECT " + COLUMN_ID + " FROM " + Table_Name + " WHERE " + COLUMN_ID + "=" + id + "", null);
            if (cursor.moveToFirst()) {
                dataBase.close();

                return true;//record Exists

            }

            dataBase.close();
        } catch (Exception errorException) {
            Log.d("Exception occured", "Exception occured " + errorException);
            // db.close();
        }
        return false;
//
//        dataBase = getWritableDatabase();
//
//        Cursor cursor = dataBase.rawQuery("Select *  from " + Table_Name + " where " + COLUMN_ID + " = " + id + "", null);
//
//        if (cursor.moveToFirst())
//        {
//            dataBase.close();
//
//            return true;//record Exists
//
//        }
//        if (cursor.getCount() == -1)
//            return false;
//        else
//            return true;
    }


}
