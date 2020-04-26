package wesal.app.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import wesal.app.R;
import wesal.app.databinding.ActivityAllDoneBinding;
import wesal.app.databinding.ActivityCouncilNameBinding;

public class AllDoneActivity extends AppCompatActivity {
    private ActivityAllDoneBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAllDoneBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        binding.btnFirstTime.setOnClickListener(v -> {
            startActivity(new Intent(AllDoneActivity.this, HomeActivity.class));


        });

    }
}
