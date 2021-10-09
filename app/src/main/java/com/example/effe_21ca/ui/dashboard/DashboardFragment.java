package com.example.effe_21ca.ui.dashboard;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.effe_21ca.Adaptors.TaskAdaptor;
import com.example.effe_21ca.R;
import com.example.effe_21ca.databinding.FragmentDashboardBinding;
import com.example.effe_21ca.models.TASKS;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class DashboardFragment extends Fragment {


    private DashboardViewModel dashboardViewModel;
    private FragmentDashboardBinding binding;
//    FirebaseStorage storage;
//    FirebaseAuth auth;
//    FirebaseDatabase database;
    private TaskAdaptor adapter;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        dashboardViewModel =
                new ViewModelProvider(this).get(DashboardViewModel.class);

        binding = FragmentDashboardBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

//        storage=FirebaseStorage.getInstance();
//        auth=FirebaseAuth.getInstance();
//        database=FirebaseDatabase.getInstance();

        LinearLayoutManager layoutManager=new LinearLayoutManager(getContext());
        binding.dasaboardRecycleview.setLayoutManager(layoutManager);


        FirebaseRecyclerOptions<TASKS> options =
                new FirebaseRecyclerOptions.Builder<TASKS>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("TASKS"), TASKS.class)
                        .build();

        adapter=new TaskAdaptor(options);
        binding.dasaboardRecycleview.setAdapter(adapter);




//        final TextView textView = binding.textDashboard;
//        dashboardViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
//            @Override
//            public void onChanged(@Nullable String s) {
//                textView.setText(s);
//            }
//        });

//        binding.uploadButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent=new Intent();
//                intent.setAction(Intent.ACTION_GET_CONTENT);
//                intent.setType("image/*");
//                startActivityForResult(intent,33);
//            }
//        });



        return root;
    }

//    @Override
//    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//
//        if(data.getData()!=null){
//
//            Uri sFile=data.getData();
//
//            final StorageReference reference=storage.getReference().child("profile picture")
//                    .child(FirebaseAuth.getInstance().getUid());
//            reference.putFile(sFile).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
//                @Override
//                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
//
//
//                    Toast.makeText(getContext(), "Image is Uploaded", Toast.LENGTH_SHORT).show();
//                }
//            });
//
//        }
//    }

    @Override
    public void onStart() {
        super.onStart();
        adapter.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();
        adapter.stopListening();
    }

//    @Override
//    public void onDestroyView() {
//        super.onDestroyView();
//        binding = null;
//    }

}