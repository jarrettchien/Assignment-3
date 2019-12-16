package com.cpsc411.assignment2.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.cpsc411.assignment2.R;
import com.cpsc411.assignment2.model.Student;
import com.cpsc411.assignment2.model.StudentDB;

public class SummaryLVAdapter extends BaseAdapter {

    StudentDB newStudentDB;
    public SummaryLVAdapter(StudentDB DB) {
        newStudentDB = DB;
    }

    @Override
    public int getCount() {
        return newStudentDB.getStudents().size();
    }

    @Override
    public Object getItem(int position) {
        return newStudentDB.getStudents().get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View rowview;

        if (convertView == null){
            LayoutInflater inflater = LayoutInflater.from(parent.getContext());
            rowview = inflater.inflate(R.layout.student_row, parent, false);
        } else rowview = convertView;

        Student s = newStudentDB.getStudents().get(position);

        TextView fNameView =  rowview.findViewById(R.id.first_name_id);
        TextView lNameView = rowview.findViewById(R.id.last_name_id);
        fNameView.setText(s.getFirstName());
        lNameView.setText(s.getLastName());
        rowview.setTag(new Integer(position));

        return rowview;
    }
}
