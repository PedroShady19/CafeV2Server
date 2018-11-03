package com.example.shady.cafev2server;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.shady.cafev2server.Model.User;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class SignIn extends AppCompatActivity {
    Button btnSignIn;
    EditText edtPhone,edtPassword;

    FirebaseDatabase database;
    DatabaseReference users;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        //Botoes e outros
        edtPassword=findViewById(R.id.edtPassword);
        edtPhone=findViewById(R.id.edtPhone);
        btnSignIn=findViewById(R.id.btnSignIn);
        //Inicializar a Firebase
        database=FirebaseDatabase.getInstance();
        users=database.getReference("User");

        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signInUser(edtPhone.getText().toString(),edtPassword.getText().toString());
            }
        });

    }

    private void signInUser(final String phone, String password) {
    final ProgressDialog mDialog = new ProgressDialog(SignIn.this);
    mDialog.setMessage("Please wait ");
    mDialog.show();

    final String localPhone= phone;
    final String localPassword=password;

    users.addValueEventListener(new ValueEventListener() {
        @Override
        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
            if(dataSnapshot.child(localPhone).exists())
            {
                mDialog.dismiss();
                User user = dataSnapshot.child(localPhone).getValue(User.class);
                user.setPhone(localPhone);
                if(Boolean.parseBoolean(user.getIsStaff())) // se IsStaff ==True
                {
                    if(user.getPassword().equals(localPassword))
                    {
                        //Login Ok
                    }
                    else
                    {
                        Toast.makeText(SignIn.this,"Wrong Password!",Toast.LENGTH_SHORT).show();
                    }
                }
                else
                    {
                        Toast.makeText(SignIn.this,"Please login with Staff account!!",Toast.LENGTH_SHORT).show();
                    }
            }
            else
                {
                    mDialog.dismiss();
                    Toast.makeText(SignIn.this,"User doesn't exist!",Toast.LENGTH_SHORT).show();
                }
        }

        @Override
        public void onCancelled(@NonNull DatabaseError databaseError) {

        }
    });
    }

}
