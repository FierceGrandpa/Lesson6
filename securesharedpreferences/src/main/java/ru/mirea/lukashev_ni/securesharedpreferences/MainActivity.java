package ru.mirea.lukashev_ni.securesharedpreferences;

import androidx.appcompat.app.AppCompatActivity;
import androidx.security.crypto.EncryptedSharedPreferences;
import androidx.security.crypto.MasterKeys;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.security.keystore.KeyGenParameterSpec;
import android.widget.TextView;

import java.io.IOException;
import java.security.GeneralSecurityException;

public class MainActivity extends AppCompatActivity {
    private TextView tvPoetName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvPoetName = findViewById(R.id.tvPoetName);

        try {
            KeyGenParameterSpec keyGenParameterSpec = MasterKeys.AES256_GCM_SPEC;
            String mainKeyAlias = MasterKeys.getOrCreate(keyGenParameterSpec);
            SharedPreferences secureSharedPreferences = EncryptedSharedPreferences.create(
                    "secret_shared_prefs",
                    mainKeyAlias,
                    getBaseContext(),
                    EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
                    EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
            );

            String poetName = secureSharedPreferences.getString("secure", "");
            if (poetName.equals("")) {
                SharedPreferences.Editor editor = secureSharedPreferences.edit();
                editor.putString("secure", "Пушкин");
                editor.apply();

                poetName = secureSharedPreferences.getString("secure", "");
            }

            tvPoetName.setText(poetName);

        } catch (GeneralSecurityException | IOException e) {
            throw new RuntimeException(e);
        }
    }
}