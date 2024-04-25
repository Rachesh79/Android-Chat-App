package com.example.FreeChat;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.FreeChat.model.UserModel;
import com.example.FreeChat.utils.AndroidUtil;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_splash);

        if(   getIntent().getExtras() != null){
//            from notification
            String userId = getIntent().getExtras().getString("userId");
            FirebaseUtil.allUserCollectionReference().document(userId).get()
                    .addOnCompleteListener(task -> {
                       if(task.isSuccessful()){
                           UserModel model = task.getResult().toObject(UserModel.class);

                           Intent mainIntent = new Intent(this, MainActivity.class);
                           mainIntent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                           startActivity(mainIntent);

                           Intent intent = new Intent(this, ChatActivity.class);
                           AndroidUtil.passUserModelAsIntent(intent, model);
                           intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                           startActivity(intent);
                           finish();
                       }
                    });

        }else{
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    // If user is authenticated, start MainActivity
                    if(FirebaseUtil.isLoggedIN()){
                        startActivity(new Intent(SplashActivity.this, MainActivity.class));
                    }else{
                        startActivity(new Intent(SplashActivity.this, LoginPhoneNumActivity.class));
                    }
                    finish();
                }
            }, 1000);
        }



        // Check user authentication status or any necessary setup
        // For demonstration purposes, assume the user is authenticated

        // Simulate authentication check delay using Handler

    }
}
