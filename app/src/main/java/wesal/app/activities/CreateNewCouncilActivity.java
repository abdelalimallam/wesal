package wesal.app.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import wesal.app.databinding.ActivityCreateNewCouncilBinding;

public class CreateNewCouncilActivity extends AppCompatActivity {

    private ActivityCreateNewCouncilBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCreateNewCouncilBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        binding.btnCreate.setOnClickListener(v -> {

            startActivity(new Intent(CreateNewCouncilActivity.this, CouncilNameActivity.class));

        });
    }


}
