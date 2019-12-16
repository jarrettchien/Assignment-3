package com.cpsc411.assignment2.model;

import java.util.ArrayList;
import java.io.File;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;


public class StudentDB {

    protected ArrayList<Student> Students;

    protected SQLiteDatabase newSQLiteDatabase;

    public StudentDB(Context context) {
        File db = context.getDatabasePath("studentdb");
        newSQLiteDatabase = SQLiteDatabase.openOrCreateDatabase(db, null);
        newSQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS Student (FirstName Text, LastName Text, CWID Text)");
        newSQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS CourseEnrollment (CourseID Text, Grade Text, Student Text)");
    }

    public ArrayList<Student> getStudents() {
        return Students;
    }

    public void setStudents(ArrayList<Student> students) {
        Students = students;
    }

    public void addStudent(Student student){
        student.insert(newSQLiteDatabase);
    }

    public ArrayList<Student> retrieveStudentObjects(){
        Students = new ArrayList<Student>();
        Cursor c = newSQLiteDatabase.query("Student", null, null, null, null, null, null);
        if (c.getCount() > 0){
            while(c.moveToNext()){
                Student studentObj = new Student();
                studentObj.initFrom(newSQLiteDatabase, c);
                Students.add(studentObj);
            }
        }
        return Students;
    }
}
