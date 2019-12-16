package com.cpsc411.assignment2.model;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import java.util.ArrayList;

public class Student extends PersistentObject {
    protected String FirstName;
    protected String LastName;
    protected String _CWID;
    protected ArrayList<CourseEnrollment> Courses;

    public Student(String firstName, String lastName, String CWID) {
        FirstName = firstName;
        LastName = lastName;
        _CWID = CWID;
    }

    public Student() {}

    public String getFirstName() {
        return FirstName;
    }

    public String getLastName() {
        return LastName;
    }
    
    public void setCourses(ArrayList<CourseEnrollment> courses) {
        Courses = courses;
    }

    @Override
    public void insert(SQLiteDatabase db){
        ContentValues content = new ContentValues();
        content.put("FirstName", FirstName);
        content.put("LastName", LastName);
        content.put("CWID", _CWID);
        db.insert("Student",null, content);
        for(int i = 0; i < Courses.size(); i++){
            Courses.get(i).insert(db);
        }
    }
    public void initFrom(SQLiteDatabase db, Cursor cursor) {
        FirstName = cursor.getString(cursor.getColumnIndex("FirstName"));
        LastName = cursor.getString(cursor.getColumnIndex("LastName"));
        _CWID = cursor.getString(cursor.getColumnIndex("CWID"));

        Courses = new ArrayList<CourseEnrollment>();
        Cursor c = db.query("CourseEnrollment", null, "Student=?", new String[]{_CWID}, null, null, null);
        if(c.getCount() > 0){
            while(c.moveToNext()){
                CourseEnrollment cObj = new CourseEnrollment();
                cObj.initFrom(db, c);
                Courses.add(cObj);
            }
        }
    }
}
