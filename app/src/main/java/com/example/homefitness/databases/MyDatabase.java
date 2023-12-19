package com.example.homefitness.databases;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.NonNull;

import com.example.homefitness.models.Account;

import java.util.ArrayList;

public class MyDatabase extends SQLiteOpenHelper {
    // Dinh nghia cac thuoc tinh cho Database
    public final static String DB_NAME = "home_fitness_sqlite";
    public final static int DB_VERSION = 1;
    public static Activity activity;

    // Cac column cua table account
    private static final String ACCOUNT_TABLE_NAME = "accounts";
    public final static String ACCOUNT_ID = "account_id";
    public final static String ACCOUNT_NAME = "account_name";
    public final static String GENDER = "gender";
    public final static String HEIGHT = "height";

    public final static String WEIGHT = "weight";

    public final static String TARGET = "target";
    public final static String FAVORITE = "favorite";
    public final static String ID_RECENT_EXERCISE = "id_recent_execise";

    // Cac column cua table exercise
    public final static String EXERCISE_TABLE_NAME = "exercises";

    public final static String EXERCISE_ID = "exercise_id";

    public final static String GIF_NAME = "exercise_name";

    public final static String TIME = "time";

    public final static String CALORIE = "calorie";

    public final static String INDEX_GIF_IN_DRAWABLE = "index_gif_in_drawable";

    public final static String CATEGORY_ID = "category_id";

    // Cac column cua table category
    public final static String CATEGORY_TABLE_NAME = "categories";

    public final static String CATEGORY_NAME = "category_name";

    public final static String INDEX_CATEGORY_IN_DRAWABLE = "index_category_in_drawable";

    //CONSTRUCTOR
    public MyDatabase(@NonNull Activity activity){
        super(activity, DB_NAME, null,DB_VERSION);
        MyDatabase.activity = activity;
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        if(db != null){
            // SQL ACCOUNT
            String sql = "CREATE TABLE " + ACCOUNT_TABLE_NAME + "(" + ACCOUNT_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + ACCOUNT_NAME + " VARCHAR (255) , " + GENDER + " VARCHAR (255) , " + HEIGHT + " DOUBLE , " + WEIGHT + " DOUBLE , "
                    + TARGET + " VARCHAR (255) ," + FAVORITE + " VARCHAR (255), "+ ID_RECENT_EXERCISE + " VARCHAR (255)); " ;
            // Create table account
            db.execSQL(sql);

            // SQL EXERCISE
            sql = "CREATE TABLE " + EXERCISE_TABLE_NAME + "(" + EXERCISE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + GIF_NAME + " VARCHAR (255) , " + TIME + " INTEGER , "
                    + CALORIE + " INTEGER ," + INDEX_GIF_IN_DRAWABLE + " INTEGER , " + CATEGORY_ID + " VARCHAR (255) );" ;
            // Create table EXERCISE
            db.execSQL(sql);


            // SQL CATEGORY
            sql = "CREATE TABLE " + CATEGORY_TABLE_NAME + "(" + CATEGORY_ID + " VARCHAR (255) PRIMARY KEY , "
                    + CATEGORY_NAME + " VARCHAR (255) , " + INDEX_CATEGORY_IN_DRAWABLE + " INTEGER); ";
            // Create table Category
            db.execSQL(sql);
        }
    }
    // Methods
    // Add account
    public int createAccount(Account account){
        int result = 0;
        SQLiteDatabase sqlite = getWritableDatabase();
        if(sqlite != null){
            ContentValues contentValues = new ContentValues();
            contentValues.put(ACCOUNT_NAME, account.getName());
            contentValues.put(GENDER, account.getGender());
            contentValues.put(HEIGHT, account.getHeight());
            contentValues.put(WEIGHT, account.getWeight());
            contentValues.put(TARGET, account.getTarget());
            result = (int) sqlite.insert(ACCOUNT_TABLE_NAME, null, contentValues);
            sqlite.close();
        }
        return result;
    }
    // Get account
    @SuppressLint("Range")
    public ArrayList<Account> getAccount(){
        ArrayList<Account> listAccount = new ArrayList<Account>();
        SQLiteDatabase sqlite = getWritableDatabase();
        if (sqlite != null){
            Cursor cursor = sqlite.query(ACCOUNT_TABLE_NAME, null, null, null, null, null,null);
            if (cursor != null && cursor.moveToFirst()){
                do{
                    Account ac = new Account();
                    int iId = cursor.getColumnIndex(ACCOUNT_ID);
                    int iName = cursor.getColumnIndex(ACCOUNT_NAME);
                    int iGender = cursor.getColumnIndex(GENDER);
                    int iHeight = cursor.getColumnIndex(HEIGHT);
                    int iWeight = cursor.getColumnIndex(WEIGHT);
                    int iTarget = cursor.getColumnIndex(TARGET);
                    int iFavorite = cursor.getColumnIndex(FAVORITE);
                    int iRecent = cursor.getColumnIndex(ID_RECENT_EXERCISE);


                    ac.setId(cursor.getInt(iId));
                    ac.setName(cursor.getString(iName));
                    ac.setGender(cursor.getString(iGender));
                    ac.setHeight(cursor.getDouble(iHeight));
                    ac.setWeight(cursor.getDouble(iWeight));
                    ac.setTarget(cursor.getString(iTarget));
                    ac.setFavoriteList(cursor.getString(iFavorite));
                    ac.setIdRecentExercise(cursor.getString(iRecent));

                    listAccount.add(ac);
                }while (cursor.moveToNext());
            }
        }
        return listAccount;
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
