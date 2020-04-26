package wesal.app.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

import wesal.app.R;
import wesal.app.utils.AppConstants;

public class SplashActivity extends AppCompatActivity {

    private static final int SPLASH_TIME_OUT = 3000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        new Handler().postDelayed(this::setUp, SPLASH_TIME_OUT);


    }

    protected void setUp() {

        SharedPreferences prefs = getSharedPreferences(AppConstants.PREF_NAME, MODE_PRIVATE);
        String email = prefs.getString("email", null);

        if (email == null) {

            startActivity(new Intent(SplashActivity.this, StartActivity.class));
            finish();

        } else {

            startActivity(new Intent(SplashActivity.this, MainActivity.class));
            finish();

        }

    }
}
