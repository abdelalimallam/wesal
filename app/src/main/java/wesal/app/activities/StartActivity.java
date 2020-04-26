package wesal.app.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

import wesal.app.databinding.ActivityStartBinding;

public class StartActivity extends AppCompatActivity {

    private ActivityStartBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityStartBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        FirebaseAuth.getInstance().signOut();
        binding.btnInvited.setOnClickListener(v -> {

            startActivity(new Intent(StartActivity.this, EnterKeyActivity.class));

        });

        binding.btnFirstTime.setOnClickListener(v -> {

            startActivity(new Intent(StartActivity.this, CreateNewCouncilActivity.class));

        });

    }

}
