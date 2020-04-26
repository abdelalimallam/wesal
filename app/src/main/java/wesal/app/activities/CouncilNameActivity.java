package wesal.app.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import wesal.app.databinding.ActivityCouncilNameBinding;

public class CouncilNameActivity extends AppCompatActivity {

    private ActivityCouncilNameBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCouncilNameBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        binding.btnNext.setOnClickListener(v -> {

            if (binding.etFamilyName.getText().toString().isEmpty()) {
                Toast.makeText(CouncilNameActivity.this, "Invalid Family name..", Toast.LENGTH_SHORT).show();

            } else {
                startActivity(new Intent(CouncilNameActivity.this, SignUpActivity.class).putExtra("family", binding.etFamilyName.getText().toString()));

            }

        });
    }

}
