package com.cpsc411.assignment2;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.cpsc411.assignment2.model.CourseEnrollment;
import com.cpsc411.assignment2.model.Student;
import com.cpsc411.assignment2.model.StudentDB;

import java.util.ArrayList;

public class StudentAddActivity extends AppCompatActivity {
    ArrayList<EditText> courseViewList = new ArrayList<EditText>();
    StudentDB newStudentDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_student);
        newStudentDB = new StudentDB(this);
        courseViewList.add((EditText) findViewById(R.id.courseid_0_id));
        courseViewList.add((EditText) findViewById(R.id.grade_0_id));
        Button addCourseButton = findViewById(R.id.add_course_button_id);
        addCourseButton.setOnClickListener(
                new View.OnClickListener(){
                    @Override
                    public void onClick(View v) {
                        LinearLayout courseid_ll = findViewById(R.id.courseid_ll_id);
                        LinearLayout grade_ll = findViewById(R.id.grade_ll_id);
                        LayoutInflater inflater = LayoutInflater.from(v.getContext());
                        EditText courseid = (EditText) inflater.inflate(R.layout.course_edit_text,
                                courseid_ll, false);
                        EditText grade = (EditText) inflater.inflate(R.layout.course_edit_text,
                                grade_ll, false);
                        courseViewList.add(courseid);
                        courseViewList.add(grade);
                        courseid_ll.addView(courseid);
                        grade_ll.addView(grade);
                    }
                }
        );
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.add_screen, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.action_done){
            ArrayList<CourseEnrollment> courseList = new ArrayList<CourseEnrollment>();
            for(int i = 0; i < courseViewList.size(); i += 2){
                courseList.add(new CourseEnrollment(courseViewList.get(i).getText().toString(),
                        courseViewList.get(i+1).getText().toString(),
                        (((EditText) findViewById(R.id.edit_cwid_id)).getText().toString())));
            }
            Student s = new Student(((EditText) findViewById(R.id.edit_first_name_id)).getText().toString(),
                    ((EditText) findViewById(R.id.edit_last_name_id)).getText().toString(),
                    (((EditText) findViewById(R.id.edit_cwid_id)).getText().toString()));
            s.setCourses(courseList);
            newStudentDB.addStudent(s);
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
