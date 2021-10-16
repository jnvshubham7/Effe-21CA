package com.example.effe_21ca;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.renderscript.ScriptGroup;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.effe_21ca.databinding.ActivityAdminPageBinding;
import com.example.effe_21ca.models.TASKS;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.FirebaseDatabase;

public class Admin_Page extends AppCompatActivity {
    ActivityAdminPageBinding binding;
    FirebaseDatabase database;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        binding =ActivityAdminPageBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        database = FirebaseDatabase.getInstance();

binding.AdminButton.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        String TaskId=binding.TaskId.getText().toString();
        String TaskTitle=binding.Title.getText().toString();
        String TaskLink=binding.TaskLink.getText().toString();
        int Taskpoint=Integer.parseInt(binding.Points.getText().toString());
        binding.TaskId.setText("");
        binding.TaskLink.setText("");
        binding.Title.setText("");
        binding.Points.setText("");

        TASKS Task = new TASKS(TaskTitle,TaskLink,TaskId,Taskpoint);

        database.getReference().child("TASKS").child(TaskId).setValue(Task).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                Toast.makeText(Admin_Page.this, "Task Added", Toast.LENGTH_SHORT).show();



            }
        });





    }
});


    }
}