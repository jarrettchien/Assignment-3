package com.cpsc411.assignment2;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.cpsc411.assignment2.adapter.SummaryLVAdapter;
import com.cpsc411.assignment2.model.CourseEnrollment;
import com.cpsc411.assignment2.model.Student;
import com.cpsc411.assignment2.model.StudentDB;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    protected ListView SummaryView;
    protected SummaryLVAdapter ad;
    protected StudentDB newStudentDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        newStudentDB = new StudentDB(this);
        newStudentDB.retrieveStudentObjects();

        setContentView(R.layout.summary_listview);
        SummaryView = findViewById(R.id.summary_list_view_id);
        ad = new SummaryLVAdapter(newStudentDB);
        SummaryView.setAdapter(ad);
    }

    /*protected void createStudentDB(){
        ArrayList<Student> studentList = new ArrayList<Student>();

        Student s = new Student("Jarrett", "Chien", "123456");
        ArrayList<CourseEnrollment> courses = new ArrayList<CourseEnrollment>();
        courses.add(new CourseEnrollment("0", "A"));
        courses.add(new CourseEnrollment("1", "A"));
        s.setCourses(courses);
        studentList.add(s);

        s = new Student("Eugene", "Lee", "234567");
        courses = new ArrayList<>();
        courses.add(new CourseEnrollment("0", "A"));
        courses.add(new CourseEnrollment("1", "A"));
        s.setCourses(courses);
        studentList.add(s);

        StudentDB.getInstance().setStudents(studentList);
    }*/

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.summary, menu);
        menu.findItem(R.id.action_add).setVisible(true);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        Intent intent = new Intent(this, StudentAddActivity.class);
        startActivity(intent);
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onStart() {
        super.onStart();
        newStudentDB.retrieveStudentObjects();
        ad.notifyDataSetChanged();
    }
}
