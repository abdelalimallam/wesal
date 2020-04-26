package wesal.app.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.esafirm.imagepicker.features.ImagePicker;
import com.esafirm.imagepicker.model.Image;

import petrov.kristiyan.colorpicker.ColorPicker;
import wesal.app.R;
import wesal.app.databinding.ActivityPersonalInfoBinding;

public class PersonalInfoActivity extends AppCompatActivity {

    private ActivityPersonalInfoBinding binding;

    private Image image = null;
    private String color = "#5ccca5";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityPersonalInfoBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

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
                    PersonalInfoActivity.this.color = "#" + Integer.toHexString(color);
                    binding.ivPickedColor.setColorFilter(color);

                }

                @Override
                public void onCancel() {
                    // put code
                }
            });

        });


        binding.btnNext.setOnClickListener(v -> {
            startActivity(new Intent(PersonalInfoActivity.this, ConfirmPhoneActivity.class));
        });

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
