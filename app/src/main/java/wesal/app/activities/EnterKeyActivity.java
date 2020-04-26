package wesal.app.activities;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import wesal.app.R;
import wesal.app.databinding.ActivityEnterKeyBinding;

public class EnterKeyActivity extends AppCompatActivity {


    ActivityEnterKeyBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityEnterKeyBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        setUp();
    }

    private void setUp() {

        jump(binding.etDigit1, binding.etDigit2, null);
        jump(binding.etDigit2, binding.etDigit3, binding.etDigit1);
        jump(binding.etDigit3, binding.etDigit4, binding.etDigit2);
        jump(binding.etDigit4, binding.etDigit5, binding.etDigit3);
        jump(binding.etDigit5, null, binding.etDigit4);

        binding.btnNext.setOnClickListener(v -> {
            startActivity(new Intent(EnterKeyActivity.this, HomeActivity.class).putExtra("familyCode", "12345"));
            finish();
        });
    }

    private void jump(TextInputEditText current, TextInputEditText next, TextInputEditText back) {

        current.addTextChangedListener(new TextWatcher() {
            @Override
            public void afterTextChanged(Editable s) {

            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (current.getText().toString().trim().length() == 1) {

                    if (next != null)
                        next.requestFocus();

                } else if (back != null) {

                    back.requestFocus();

                }
            }
        });

    }

}
