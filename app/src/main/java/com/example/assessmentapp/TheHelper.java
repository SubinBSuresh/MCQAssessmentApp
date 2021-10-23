package com.example.assessmentapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.HashMap;

public class TheHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "FINAL_DATABASE";
    public static final int DATABASE_VERSION = 1;


    //MCQ QUESTION INPUT
    private static final String TABLE_MCQ_QUESTIONS_INPUT = "MCQINPUT";
    public static final String KEY_ASSESSMENTNAME = "assessment_name";
    public static final String KEY_QUESTION_NO = "mcq_question_no";
    public static final String KEY_QUESTION = "mcq_question";
    public static final String KEY_OPTION1 = "option1";
    public static final String KEY_OPTION2 = "option2";
    public static final String KEY_OPTION3 = "option3";
    public static final String KEY_OPTION4 = "option4";
    public static final String KEY_CORRECTANSWER = "correct_answer";

    //CREATE TABLE
    private static final String CREATE_MCQ_TABLE = "CREATE TABLE " + TABLE_MCQ_QUESTIONS_INPUT + "(assessment_name text,mcq_question_no text,mcq_question text, option1 text, option2 text, option3 text, option4 text, correct_answer text)";
    //DROP TABLE
    private static final String DROP_MCQ_TABLE = "DROP TABLE IF EXISTS " + TABLE_MCQ_QUESTIONS_INPUT;


    //ASSESSMENT DETAILS
    private static final String TABLE_NAME = "ASSESSMENT_DETAILS";
    public static final String KEY_ASSESSMENT_NAME = "assessment_name_input";
    public static final String KEY_DUE_DATE = "due_date";
    public static final String KEY_DURATION = "duration";

    //CREATE TABLE
    private static final String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + "(assessment_name_input text, due_date text, duration text)";
    //DROP TABLE
    private static final String DROP_TABLE = "DROP TABLE IF EXISTS " + TABLE_NAME;


    //STUDENT DETAILS
    private static final String STUDENT_TABLE_NAME = "studentinfo";
    public static final String KEY_STUDENT_USERNAME = "username";
    public static final String KEY_STUDENT_EMAIL = "email";
    public static final String KEY_STUDENT_PASSWORD = "password";


    //Create Table
    private static final String CREATE_TABLE_STUDENT = "CREATE TABLE " + STUDENT_TABLE_NAME + "(username text,email text,password text)";
    //Drop Table
    private static final String DROP_TABLE_STUDENT = "DROP TABLE IF EXISTS " + STUDENT_TABLE_NAME;


    //FACULTY DETAILS
    private static final String FACULTY_TABLE_NAME = "facultyinfo";
    public static final String KEY_USERNAME = "username";
    public static final String KEY_EMAIL = "email";
    public static final String KEY_PASSWORD = "password";

    //Create Table
    private static final String CREATE_FACULTY_TABLE = "CREATE TABLE " + FACULTY_TABLE_NAME + "(username text,email text,password text)";
    //Drop Table
    private static final String DROP_FACULTY_TABLE = "DROP TABLE IF EXISTS " + FACULTY_TABLE_NAME;
    private Object String;


    //Student mark details
    private static final String MARK_LIST_TABLE = "MARKLIST";
    public static final String STUDENT_NAME = "student_name";
    public static final String STUDENT_ASSESSMENT = "student_assessment";
    public static final String STUDENT_MARKS = "student_marks";
    //CREATE TABLE
    private static final String CREATE_STUDENT_MARKLIST = "CREATE TABLE "+MARK_LIST_TABLE+"( student_name ,student_assessment ,student_marks )";
    //DROP TABLE
    private static final String DROP_STUDENT_MARKLIST = "DROP TABLE IF EXISTS "+MARK_LIST_TABLE;


    //CONSTRUCTOR
    public TheHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // ON CREATE
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_MCQ_TABLE);
        sqLiteDatabase.execSQL(CREATE_TABLE);
        sqLiteDatabase.execSQL(CREATE_TABLE_STUDENT);
        sqLiteDatabase.execSQL(CREATE_FACULTY_TABLE);
        sqLiteDatabase.execSQL(CREATE_STUDENT_MARKLIST);
    }

    // ON UPGRADE
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL(DROP_MCQ_TABLE);
        sqLiteDatabase.execSQL(DROP_TABLE);
        sqLiteDatabase.execSQL(DROP_TABLE_STUDENT);
        sqLiteDatabase.execSQL(DROP_FACULTY_TABLE);
        sqLiteDatabase.execSQL(DROP_STUDENT_MARKLIST);

        onCreate(sqLiteDatabase);
    }


//<-----------------------------------------------------------------Subin--------------------------------------------------->

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
    public ArrayList<HashMap<String, String>> getMCQData(String assessmentName) {
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        ArrayList<HashMap<String, String>> mcqQuestions = new ArrayList<>();

//        String query = "select mcq_question,option1,option2, option3,option4, correct_answer from " + TABLE_MCQ_QUESTIONS_INPUT+ " where "+KEY_ASSESSMENTNAME+ " = " + assessmentName;
//        Cursor cursor = sqLiteDatabase.rawQuery(query, null);

        Cursor cursor = sqLiteDatabase.query(TABLE_MCQ_QUESTIONS_INPUT,new String[]{KEY_QUESTION,KEY_OPTION1,KEY_OPTION2,KEY_OPTION3,KEY_OPTION4,KEY_CORRECTANSWER}, KEY_ASSESSMENTNAME+"=?",new String[]{assessmentName},null,null,null,null);


        while ((cursor.moveToNext())) {
            HashMap<String, String> mcqData = new HashMap<>();

            mcqData.put(KEY_QUESTION, cursor.getString(0));
            mcqData.put(KEY_OPTION1, cursor.getString(1));
            mcqData.put(KEY_OPTION2, cursor.getString(2));
            mcqData.put(KEY_OPTION3, cursor.getString(3));
            mcqData.put(KEY_OPTION4, cursor.getString(4));
            mcqData.put(KEY_CORRECTANSWER, cursor.getString(5));
            mcqQuestions.add(mcqData);
        }
        return mcqQuestions;
    }


    public Integer getQuestionCount(String assessmentName){
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();

//        long numRows = DatabaseUtils.longForQuery(sqLiteDatabase, "SELECT COUNT(*) FROM "+TABLE_MCQ_QUESTIONS_INPUT+ " where assessment_name = "+assessmentName, null);
        Cursor cursor = sqLiteDatabase.query(TABLE_MCQ_QUESTIONS_INPUT,new String[]{KEY_QUESTION}, KEY_ASSESSMENTNAME+"=?",new String[]{assessmentName},null,null,null,null);
        return cursor.getCount();
    }

    //<-----------------------------------------------------------------Tineesha--------------------------------------------------->
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

    //Retrieve Assessment details
    public ArrayList<HashMap<String, String>> getAssessmentList() {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ArrayList<HashMap<String, String>> assessmentList = new ArrayList<>();

        String query = "select assessment_name_input,due_date,duration from " + TABLE_NAME;
        Cursor cursor = sqLiteDatabase.rawQuery(query, null);
        while (cursor.moveToNext()) {
            HashMap<String, String> assessment = new HashMap<>();
            assessment.put(KEY_ASSESSMENT_NAME, cursor.getString(0));
            assessment.put(KEY_DUE_DATE, cursor.getString(1));
            assessment.put(KEY_DURATION, cursor.getString(2));
            assessmentList.add(assessment);
        }
        return assessmentList;
    }

//<-----------------------------------------------------------------Akash--------------------------------------------------->

    public Boolean insertStudentData(String username, String email, String password) {
        SQLiteDatabase myDB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(KEY_STUDENT_USERNAME, username);
        contentValues.put(KEY_STUDENT_EMAIL, email);
        contentValues.put(KEY_STUDENT_PASSWORD, password);
        long result = myDB.insert(STUDENT_TABLE_NAME, null, contentValues);
        return result != -1;
    }

    public Boolean checkStudentUsername(String emailCheck) {
        SQLiteDatabase myDB = this.getReadableDatabase();
        Cursor cursor = myDB.rawQuery("SELECT email FROM " + STUDENT_TABLE_NAME + " WHERE email = ? ", new String[]{emailCheck});
        return cursor.getCount() > 0;
    }

    public Boolean checkStudentUsernamePassword(String email, String password) {
        SQLiteDatabase myDB = this.getReadableDatabase();
        Cursor cursor = myDB.rawQuery("SELECT email,password FROM " + STUDENT_TABLE_NAME + " WHERE email = ? AND password = ?", new String[]{email, password});
        return cursor.getCount() > 0;
    }

    public String getStudentName(String email) {
        String name = "";
        SQLiteDatabase myDB = this.getReadableDatabase();
        Cursor cursor = myDB.rawQuery("SELECT username FROM " + STUDENT_TABLE_NAME + " WHERE email = ?", new String[]{email});
        while (cursor.moveToNext()) {
            name = cursor.getString(0);
        }
        return name;
    }


//<-----------------------------------------------------------------kEVIN--------------------------------------------------->

    public Boolean insertFacultyData(String username, String email, String password) {
        SQLiteDatabase myDB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(KEY_USERNAME, username);
        contentValues.put(KEY_EMAIL, email);
        contentValues.put(KEY_PASSWORD, password);
        long result = myDB.insert(FACULTY_TABLE_NAME, null, contentValues);
        return result != -1;
    }

    public Boolean checkFacultyUsername(String emailCheck) {
        SQLiteDatabase myDB = this.getReadableDatabase();
        Cursor cursor = myDB.rawQuery("SELECT email FROM " + FACULTY_TABLE_NAME + " WHERE email = ? ", new String[]{emailCheck});
        return cursor.getCount() > 0;
    }

    public Boolean checkFacultyUsernamePassword(String email, String password) {
        SQLiteDatabase myDB = this.getReadableDatabase();
        Cursor cursor = myDB.rawQuery("SELECT email,password FROM " + FACULTY_TABLE_NAME + " WHERE email = ? AND password = ?", new String[]{email, password});

        return cursor.getCount() > 0;
    }
    //<-----------------------------------------------------------------Subin--------------------------------------------------->

    public void insertStudentMarklist(String name, String assessmentName, String marks) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(STUDENT_NAME, name);
        cv.put(STUDENT_ASSESSMENT, assessmentName);
        cv.put(STUDENT_MARKS, marks);

        //Insert new row in database
        db.insert(MARK_LIST_TABLE, null, cv);
    }

    //Retrieve Assessment details
    public ArrayList<HashMap<String, String>> getStudentMarklist(String assessmentName) {
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        ArrayList<HashMap<String, String>> marksList = new ArrayList<>();

/*        String query = "select student_name,student_marks from " + MARK_LIST_TABLE+" where student_assessment = "+ assessmentName;
        Cursor cursor = sqLiteDatabase.rawQuery(query, null);*/
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT student_name,student_marks FROM " + MARK_LIST_TABLE + " WHERE student_assessment = ?", new String[]{assessmentName});

        while (cursor.moveToNext()) {
            HashMap<String, String> marks = new HashMap<>();
            marks.put(STUDENT_NAME, cursor.getString(0));
            marks.put(STUDENT_MARKS, cursor.getString(1));
            marksList.add(marks);
        }
        return marksList;
    }

    //Eligibility checker
    public String eligibility(String studentName, String assessmentName){
        SQLiteDatabase myDB = this.getReadableDatabase();
        String mark = "";
        Cursor cursor = myDB.rawQuery("SELECT  student_marks FROM " + MARK_LIST_TABLE + " WHERE student_name = ? AND student_assessment = ?", new String[]{studentName, assessmentName});
        while (cursor.moveToNext()) {
             mark = cursor.getString(0);
        }
        return mark;
    }
}
