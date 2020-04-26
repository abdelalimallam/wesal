package wesal.app.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.esafirm.imagepicker.features.ImagePicker;
import com.esafirm.imagepicker.model.Image;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import petrov.kristiyan.colorpicker.ColorPicker;
import wesal.app.R;
import wesal.app.databinding.ActivityPersonalInfoBinding;
import wesal.app.databinding.ActivitySignUpBinding;

public class SignUpActivity extends AppCompatActivity {

    private ActivitySignUpBinding binding;

    private Image image = null;
    private String color = "#5ccca5";

    String family = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySignUpBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        if (getIntent().hasExtra("family")) {
            family = getIntent().getExtras().getString("family");
        }

        setUp();

    }


    private void setUp() {


        binding.ccp.registerCarrierNumberEditText(binding.etPhone);


        binding.ivProfileImage.setOnClickListener(v -> {
            if (image == null)
                ImagePicker.create(this).single().includeVideo(false).limit(1).start(0);
        });

        binding.ivDeleteImage.setOnClickListener(v -> {
            Glide.with(this)
                    .load("")
                    .placeholder(R.drawable.ic_pick_image)
                    .error(R.drawable.ic_pick_image)
                    .into(binding.ivProfileImage);

            image = null;

            binding.ivDeleteImage.setVisibility(View.GONE);
        });

        binding.flChooseColor.setOnClickListener(v -> {

            ColorPicker colorPicker = new ColorPicker(this);
            colorPicker.show();
            colorPicker.setOnChooseColorListener(new ColorPicker.OnChooseColorListener() {
                @Override
                public void onChooseColor(int position, int color) {
                    // put code
                    SignUpActivity.this.color = "#" + Integer.toHexString(color);
                    binding.ivPickedColor.setColorFilter(color);

                }

                @Override
                public void onCancel() {
                    // put code
                }
            });

        });


        binding.btnNext.setOnClickListener(v -> {


            if (binding.etEmail.getText().length() != 0
                    && binding.etPassword.getText().length() != 0
                    && binding.etFullName.getText().length() != 0
                    && binding.etPhone.getText().length() != 0) {


                if (validatePhoneNumber()) {
                    doSignUp(binding.etEmail.getText().toString(), binding.etPassword.getText().toString(), binding.etFullName.getText().toString(), binding.ccp.getFullNumberWithPlus());
                }
            } else {
                Toast.makeText(SignUpActivity.this, "Error Please check the values", Toast.LENGTH_LONG).show();

            }

        });

    }


    void doSignUp(String email, String password, String name, String phone) {


//        private String phone,name,email,color,family;

        startActivity(new Intent(SignUpActivity.this, ConfirmPhoneActivity.class)
                .putExtra("phone", binding.ccp.getFullNumberWithPlus().trim())
                .putExtra("name", name)
                .putExtra("email", email)
                .putExtra("color", color)
                .putExtra("family", family)

        );

//        mAuth.createUserWithEmailAndPassword(email, password)
//                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
//                    @Override
//                    public void onComplete(@NonNull Task<AuthResult> task) {
//                        if (task.isSuccessful()) {
//
//
//
//                        } else {
//
//                            Toast.makeText(SignUpActivity.this, getString(R.string.exist_email_error), Toast.LENGTH_LONG).show();
//
//                        }
//                    }
//
//                });

    }

    private boolean validatePhoneNumber() {

        if (TextUtils.isEmpty(binding.etPhone.getText().toString())) {
            Toast.makeText(SignUpActivity.this, "Invalid phone number.", Toast.LENGTH_LONG).show();

            return false;
        }

        return true;
    }


    @Override
    public void onActivityResult(int reqCode, int resultCode, Intent data) {
        super.onActivityResult(reqCode, resultCode, data);
        if (resultCode == RESULT_OK) {

            if (reqCode == 0 && data != null) {

                image = ImagePicker.getFirstImageOrNull(data);

                Glide.with(this)
                        .load(image.getPath())
                        .circleCrop()
                        .placeholder(R.drawable.ic_pick_image)
                        .error(R.drawable.ic_pick_image)
                        .into(binding.ivProfileImage);

                binding.ivDeleteImage.setVisibility(View.VISIBLE);

            }

        } else {

            Toast.makeText(this, "Image not picked", Toast.LENGTH_LONG).show();
        }
    }

}
