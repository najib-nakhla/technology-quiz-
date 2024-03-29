package com.example.projetandroid;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ProfileActivity extends AppCompatActivity {
    private FirebaseUser user ;
    private DatabaseReference reference;

    private String userID;
    private TextView greetingTextView;
    private TextView fullNameTextView;
    private TextView emailTextView;
    private TextView ageTextView;

    private Button Play;



    private Button logout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        logout = (Button) findViewById(R.id.signOut);



        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(ProfileActivity.this,MainActivity.class));
            }
        });

        Play = (Button) findViewById(R.id.QuizzStart);

        Play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ProfileActivity.this,StartQuizz.class));


            }
        });




        user = FirebaseAuth.getInstance().getCurrentUser();
        reference = FirebaseDatabase.getInstance().getReference("Users");
        userID=user.getUid();
        final TextView greetingTextView = (TextView) findViewById(R.id.greeting);

        final TextView fullNameTextView = (TextView) findViewById(R.id.fullName);
        final TextView emailTextView = (TextView) findViewById(R.id.emailAdress);
        final TextView ageTextView = (TextView) findViewById(R.id.age);


        reference.child(userID).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
               User userProfile =snapshot.getValue(User.class);
               if(userProfile != null) {
                   String fullName =userProfile.fullName;
                   String email =userProfile.email;
                   String age =userProfile.age;

                   greetingTextView.setText("Welcome,"+fullName+"!");
                   fullNameTextView.setText(fullName);
                   emailTextView.setText(email);
                   ageTextView.setText(age);

               }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(ProfileActivity.this,"Something wrong happened!",Toast.LENGTH_LONG).show();


            }
        });








    }
}