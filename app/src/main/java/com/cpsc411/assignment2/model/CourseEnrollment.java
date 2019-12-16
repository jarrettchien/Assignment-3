package com.cpsc411.assignment2.model;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class CourseEnrollment extends PersistentObject{
    protected String CourseID;
    protected String Grade;
    protected String Student;

    public CourseEnrollment(String courseID, String grade, String student) {
        CourseID = courseID;
        Grade = grade;
        Student = student;
    }

    public CourseEnrollment() {}

    @Override
    public void insert(SQLiteDatabase db) {
        ContentValues content = new ContentValues();
        content.put("CourseID", CourseID);
        content.put("Grade", Grade);
        content.put("Student", Student);
        db.insert("CourseEnrollment", null, content);
    }

    @Override
    public void initFrom(SQLiteDatabase db, Cursor cursor) {
        CourseID = cursor.getString(cursor.getColumnIndex("CourseID"));
        Grade = cursor.getString(cursor.getColumnIndex("Grade"));
        Student = cursor.getString(cursor.getColumnIndex("Student"));
    }
}
