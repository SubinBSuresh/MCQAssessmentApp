package com.example.assessmentapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.HashMap;

public class MCQInputDatabaseHelper extends SQLiteOpenHelper {

    public static final String KEY_ASSESSMENTNAME = "assessment_name";
    public static final String KEY_QUESTION_NO = "mcq_question_no";
    public static final String KEY_QUESTION = "mcq_question";
    public static final String KEY_OPTION1 = "option1";
    public static final String KEY_OPTION2 = "option2";
    public static final String KEY_OPTION3 = "option3";
    public static final String KEY_OPTION4 = "option4";
    public static final String KEY_CORRECTANSWER = "correct_answer";

    private static final String DATABASE_NAME = "DB_ASSESSMENT";
    private static final String TABLE_MCQ_QUESTIONS_INPUT = "MCQINPUT";
    private static final int DATABASE_VERSION = 1;

    //CREATE TABLE
    private static final String CREATE_MCQ_TABLE = "CREATE TABLE " + TABLE_MCQ_QUESTIONS_INPUT + "(assessment_name text,mcq_question_no text,mcq_question text, option1 text, option2 text, option3 text, option4 text, correct_answer text)";
    //DROP TABLE
    private static final String DROP_MCQ_TABLE = "DROP TABLE IF EXISTS " + TABLE_MCQ_QUESTIONS_INPUT;



    private static final String TABLE_NAME = "ASSESSMENT_DETAILS";


    public static final String KEY_ASSESSMENT_NAME = "assessment_name_input";
    public static final String KEY_DUE_DATE = "due_date";
    public static final String KEY_DURATION = "duration";

    //CREATE TABLE
    private static final String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + "(assessment_name_input text, due_date text, duration text)";
    //DROP TABLE
    private static final String DROP_TABLE = "DROP TABLE IF EXISTS " + TABLE_NAME;






    //CONSTRUCTOR
    public MCQInputDatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_MCQ_TABLE);


    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL(DROP_MCQ_TABLE);
        onCreate(sqLiteDatabase);
    }





    //SAVE MCQ
    public void insertMCQData(String assessment_name, String mcq_question_no, String mcq_question, String option1, String option2, String option3, String option4, String correct_answer) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(KEY_ASSESSMENTNAME, assessment_name);
        cv.put(KEY_QUESTION_NO, mcq_question_no);
        cv.put(KEY_QUESTION, mcq_question);
        cv.put(KEY_OPTION1, option1);
        cv.put(KEY_OPTION2, option2);
        cv.put(KEY_OPTION3, option3);
        cv.put(KEY_OPTION4, option4);
        cv.put(KEY_CORRECTANSWER, correct_answer);

        //Insert new row in database
        db.insert(TABLE_MCQ_QUESTIONS_INPUT, null, cv);
    }


    //SAVE MCQ
    public ArrayList<HashMap<String, String>> getMCQData() {
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        ArrayList<HashMap<String, String>> mcqQuestions = new ArrayList<>();


        String query = "select mcq_question, option1,option2,option3,option4, correct_answer from " + TABLE_MCQ_QUESTIONS_INPUT;
        Cursor cursor = sqLiteDatabase.rawQuery(query, null);

        while ((cursor.moveToNext())) {
            HashMap<String, String> mcqData = new HashMap<>();

            mcqData.put(KEY_QUESTION_NO, cursor.getString(0));
            mcqData.put(KEY_QUESTION, cursor.getString(1));
            mcqData.put(KEY_OPTION1, cursor.getString(2));
            mcqData.put(KEY_OPTION2, cursor.getString(3));
            mcqData.put(KEY_OPTION3, cursor.getString(4));
            mcqData.put(KEY_OPTION4, cursor.getString(5));
            mcqData.put(KEY_CORRECTANSWER, cursor.getString(6));

            mcqQuestions.add(mcqData);
        }
        return mcqQuestions;
    }

}
