package com.example.assessmentapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class AssessmentDetailsDatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "DB_ASSESSMENT";
    private static final String TABLE_NAME = "ASSESSMENT_DETAILS";
    private static final int DATABASE_VERSION = 1;

    public static final String KEY_ASSESSMENT_NAME = "assessment_name";
    public static final String KEY_DUE_DATE = "due_date";
    public static final String KEY_DURATION = "duration";

    //CREATE TABLE
    private static final String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + "(assessment_name text, due_date text, duration text)";
    //DROP TABLE
    private static final String DROP_TABLE = "DROP TABLE IF EXISTS " + TABLE_NAME;

    //CONSTRUCTOR
    public AssessmentDetailsDatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL(DROP_TABLE);
        onCreate(sqLiteDatabase);
    }


    //save assessment details
    public void insertAssessmentDetails(String assessment_name, String due_date, String duration) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(KEY_ASSESSMENT_NAME, assessment_name);
        cv.put(KEY_DUE_DATE, due_date);
        cv.put(KEY_DURATION, duration);

        //Insert new row in database
        db.insert(TABLE_NAME, null, cv);
    }
}
