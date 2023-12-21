package com.example.homefitness.databases;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.NonNull;

import com.example.homefitness.models.Account;
import com.example.homefitness.models.Category;
import com.example.homefitness.models.Exercise;

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

    public final static String LIST_ID_RECENT_EXERCISE = "list_id_recent_exercise";

    // Cac column cua table exercise
    public final static String EXERCISE_TABLE_NAME = "exercises";

    public final static String EXERCISE_ID = "exercise_id";

    public final static String GIF_NAME = "exercise_name";

    public final static String TIME = "time";

    public final static String CALORIE = "calorie";

    public final static String INDEX_GIF_IN_DRAWABLE = "index_gif_in_drawable";

    public final static String CATEGORY_ID = "category_id";
    public final static String FAVORITE = "favorite";

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
                    + TARGET + " VARCHAR (255) ," + LIST_ID_RECENT_EXERCISE + " VARCHAR (255) ); ";
            // Create table account
            db.execSQL(sql);

            // SQL EXERCISE
            sql = "CREATE TABLE " + EXERCISE_TABLE_NAME + "(" + EXERCISE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + GIF_NAME + " VARCHAR (255) , " + TIME + " INTEGER , "
                    + CALORIE + " INTEGER ," + INDEX_GIF_IN_DRAWABLE + " INTEGER , " + CATEGORY_ID + " VARCHAR (255), " + FAVORITE + " INTEGER);" ;
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
            contentValues.put(LIST_ID_RECENT_EXERCISE, account.getListIdRecentExercise());
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
                    int iRecent = cursor.getColumnIndex(LIST_ID_RECENT_EXERCISE);


                    ac.setId(cursor.getInt(iId));
                    ac.setName(cursor.getString(iName));
                    ac.setGender(cursor.getString(iGender));
                    ac.setHeight(cursor.getDouble(iHeight));
                    ac.setWeight(cursor.getDouble(iWeight));
                    ac.setTarget(cursor.getString(iTarget));
                    ac.setListIdRecentExercise(cursor.getString(iRecent));

                    listAccount.add(ac);
                }while (cursor.moveToNext());
            }
        }
        return listAccount;
    }
    // Update account
    public boolean updateAccount(int id, String name, double height, double weight) {
        boolean result = true;
        SQLiteDatabase sqlite = getWritableDatabase();
        if (sqlite != null) {
            String sql = "UPDATE " + ACCOUNT_TABLE_NAME + " SET " +ACCOUNT_NAME +" = '" + name + "' , "  + HEIGHT + " = " + height + " , " + WEIGHT + " = " + weight + " WHERE " + ACCOUNT_ID + " = " + id;
            try {
                sqlite.execSQL(sql);

            } catch (Exception ex) {

                result = false;
            }

        }
        return result;
    }
    // Delete account
    public int deleteAccount(Account account){
        int result = 0;
        SQLiteDatabase sqlite = getWritableDatabase();
        if(sqlite != null){
            String sql = "DELETE FROM " + ACCOUNT_TABLE_NAME + " WHERE " + ACCOUNT_ID + " LIKE " + account.getId();
            ContentValues contentValues = new ContentValues();
            result = (int) sqlite.delete(ACCOUNT_TABLE_NAME, ACCOUNT_ID + " = ? ", new String[] {account.getId() +""});
            sqlite.close();
        }
        return result;
    }
    // ------ ACCOUNT END -------



    // ------ EXERCISE START ------

    // Update bai tap vao danh sach yeu thich
    public boolean updateExercisesIntoFavorite(ArrayList<Exercise> listExercisesChoose){
        boolean success = true;
        SQLiteDatabase sqlite = getWritableDatabase();
        String sql ="";
        String exercisesID ="";

        for (Exercise exercise:
             listExercisesChoose) {
            exercisesID += exercise.getId()+",";
        }
        exercisesID = exercisesID.substring(0, exercisesID.length() - 1 ) ;

        if (sqlite != null) {
            sql = "Update "+EXERCISE_TABLE_NAME+" Set "+ FAVORITE +" = 1 Where "+ EXERCISE_ID +" IN ("+exercisesID+")";
            try {
                sqlite.execSQL(sql);
            }catch (Exception ex){
                success = false;
            }
        }
        return success;

    }

    public int createExercise(Exercise exercise){
        int result = 0;
        SQLiteDatabase sqlite = getWritableDatabase();
        if(sqlite != null){
            ContentValues contentValues = new ContentValues();
            contentValues.put(GIF_NAME, exercise.getGifName());
            contentValues.put(TIME, exercise.getTime());
            contentValues.put(CALORIE, exercise.getCalorie());
            contentValues.put(INDEX_GIF_IN_DRAWABLE, exercise.getIndexGifInDrawable());
            contentValues.put(CATEGORY_ID, exercise.getCategoryId());
            contentValues.put(FAVORITE, exercise.getFavorite());
            result = (int) sqlite.insert(EXERCISE_TABLE_NAME, null, contentValues);
            sqlite.close();
        }
        return result;
    }
    // lay danh sach tat ca san pham
    public ArrayList<Exercise> getAllExercise() {
        ArrayList<Exercise> lisExercise = new ArrayList<Exercise>();
        SQLiteDatabase sqlite = getWritableDatabase();
        String sql = null;
        if (sqlite != null) {
            sql = "SELECT * FROM " + EXERCISE_TABLE_NAME;


            Cursor cursor = sqlite.rawQuery(sql, null);
            if (cursor != null && cursor.moveToFirst()) {
                do {
                    Exercise ex = new Exercise();
                    int iId = cursor.getColumnIndex(EXERCISE_ID);
                    int iName = cursor.getColumnIndex(GIF_NAME);
                    int iTime = cursor.getColumnIndex(TIME);
                    int iCalorie = cursor.getColumnIndex(CALORIE);
                    int iIndexGifInDrawable = cursor.getColumnIndex(INDEX_GIF_IN_DRAWABLE);
                    int iCategoryId = cursor.getColumnIndex(CATEGORY_ID);
                    int iFavorite = cursor.getColumnIndex(FAVORITE);

                    ex.setId(cursor.getInt(iId));
                    ex.setGifName(cursor.getString(iName));
                    ex.setTime(cursor.getInt(iTime));
                    ex.setCalorie(cursor.getInt(iCalorie));
                    ex.setIndexGifInDrawable(cursor.getInt(iIndexGifInDrawable));
                    ex.setCategoryId(cursor.getString(iCategoryId));
                    ex.setFavorite(cursor.getInt(iFavorite));

                    lisExercise.add(ex);
                } while (cursor.moveToNext());
            }
        }

        return lisExercise;
    }

    // Lay san pham theo danh muc
    public ArrayList<Exercise> getExerciseByCategoryId(String categoryId) {
        ArrayList<Exercise> lisExercise = new ArrayList<Exercise>();
        SQLiteDatabase sqlite = getWritableDatabase();

        if (sqlite != null) {
            String sql = "SELECT * FROM " + EXERCISE_TABLE_NAME + " WHERE " + CATEGORY_ID + " LIKE '" + categoryId + "'";
            Cursor cursor = sqlite.rawQuery(sql, null);

            if (cursor != null && cursor.moveToFirst()) {
                do {
                    Exercise ex = new Exercise();
                    int iId = cursor.getColumnIndex(EXERCISE_ID);
                    int iName = cursor.getColumnIndex(GIF_NAME);
                    int iTime = cursor.getColumnIndex(TIME);
                    int iCalorie = cursor.getColumnIndex(CALORIE);
                    int iIndexGifInDrawable = cursor.getColumnIndex(INDEX_GIF_IN_DRAWABLE);
                    int iCategoryId = cursor.getColumnIndex(CATEGORY_ID);
                    int iFavorite = cursor.getColumnIndex(FAVORITE);

                    ex.setId(cursor.getInt(iId));
                    ex.setGifName(cursor.getString(iName));
                    ex.setTime(cursor.getInt(iTime));
                    ex.setCalorie(cursor.getInt(iCalorie));
                    ex.setIndexGifInDrawable(cursor.getInt(iIndexGifInDrawable));
                    ex.setCategoryId(cursor.getString(iCategoryId));
                    ex.setFavorite(cursor.getInt(iFavorite));

                    lisExercise.add(ex);
                } while (cursor.moveToNext());
            }
        }
        return lisExercise;
    }

    // Lay danh sach yeu thich
    public ArrayList<Exercise> getExerciseFavorite() {
        ArrayList<Exercise> lisExercise = new ArrayList<Exercise>();
        SQLiteDatabase sqlite = getWritableDatabase();

        if (sqlite != null) {
            String sql = "SELECT * FROM " + EXERCISE_TABLE_NAME + " WHERE " + FAVORITE + " LIKE 1 ";

            Cursor cursor = sqlite.rawQuery(sql, null);
            if (cursor != null && cursor.moveToFirst()) {
                do {
                    Exercise ex = new Exercise();
                    int iId = cursor.getColumnIndex(EXERCISE_ID);
                    int iName = cursor.getColumnIndex(GIF_NAME);
                    int iTime = cursor.getColumnIndex(TIME);
                    int iCalorie = cursor.getColumnIndex(CALORIE);
                    int iIndexGifInDrawable = cursor.getColumnIndex(INDEX_GIF_IN_DRAWABLE);
                    int iCategoryId = cursor.getColumnIndex(CATEGORY_ID);
                    int iFavorite = cursor.getColumnIndex(FAVORITE);

                    ex.setId(cursor.getInt(iId));
                    ex.setGifName(cursor.getString(iName));
                    ex.setTime(cursor.getInt(iTime));
                    ex.setCalorie(cursor.getInt(iCalorie));
                    ex.setIndexGifInDrawable(cursor.getInt(iIndexGifInDrawable));
                    ex.setCategoryId(cursor.getString(iCategoryId));
                    ex.setFavorite(cursor.getInt(iFavorite));

                    lisExercise.add(ex);
                } while (cursor.moveToNext());
            }
        }
        return lisExercise;
    }

    // Lay danh sach bai tap cuoi cung
    public ArrayList<Exercise> getListRecentExercise(String strRecentExerciseId) {
        ArrayList<Exercise> lisExercise = new ArrayList<Exercise>();
        if (!strRecentExerciseId.isEmpty()){
        String[] arrStr =  strRecentExerciseId.split(",");
        int[] arrRecentExerciseId = new int[arrStr.length];
        String temp ="";
        for (int i = 0; i < arrStr.length; i++){
            arrRecentExerciseId[i] = Integer.parseInt(arrStr[i]);
            temp += arrRecentExerciseId[i] + " , ";
        }
        temp = temp.trim();
        temp = temp.substring(0, temp.length() - 1);
        SQLiteDatabase sqlite = getWritableDatabase();
        if (sqlite != null) {
            String sql = "SELECT * FROM " + EXERCISE_TABLE_NAME + " WHERE " + EXERCISE_ID + " IN ( " + temp + " )";
            Cursor cursor = sqlite.rawQuery(sql, null);
            if (cursor != null && cursor.moveToFirst()) {
                do {
                    Exercise ex = new Exercise();
                    int iId = cursor.getColumnIndex(EXERCISE_ID);
                    int iName = cursor.getColumnIndex(GIF_NAME);
                    int iTime = cursor.getColumnIndex(TIME);
                    int iCalorie = cursor.getColumnIndex(CALORIE);
                    int iIndexGifInDrawable = cursor.getColumnIndex(INDEX_GIF_IN_DRAWABLE);
                    int iCategoryId = cursor.getColumnIndex(CATEGORY_ID);
                    int iFavorite = cursor.getColumnIndex(FAVORITE);

                    ex.setId(cursor.getInt(iId));
                    ex.setGifName(cursor.getString(iName));
                    ex.setTime(cursor.getInt(iTime));
                    ex.setCalorie(cursor.getInt(iCalorie));
                    ex.setIndexGifInDrawable(cursor.getInt(iIndexGifInDrawable));
                    ex.setCategoryId(cursor.getString(iCategoryId));
                    ex.setFavorite(cursor.getInt(iFavorite));

                    lisExercise.add(ex);
                } while (cursor.moveToNext());
            }
        }}
        return lisExercise;
    }
        // ------ EXERCISE END -------



        // ------ CATEGORY START -------
        public int createCategory(Category category){
            int result = 0;
            SQLiteDatabase sqlite = getWritableDatabase();
            if(sqlite != null){
                ContentValues contentValues = new ContentValues();
                contentValues.put(CATEGORY_ID, category.getCategoryId());
                contentValues.put(CATEGORY_NAME, category.getCategoryName());
                contentValues.put(INDEX_CATEGORY_IN_DRAWABLE, category.getIndexCategoryInDrawable());

                result = (int) sqlite.insert(CATEGORY_TABLE_NAME, null, contentValues);
                sqlite.close();
            }
            return result;
        }

        // ------ CATEGORY END -------
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
