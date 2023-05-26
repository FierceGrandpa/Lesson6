package ru.mirea.lukashev_ni.lesson6;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    private static final String PREFERENCES_NAME = "mirea_settings";
    private static final String KEY_GROUP_NUMBER = "GROUP";
    private static final String KEY_LIST_NUMBER = "NUMBER";
    private static final String KEY_FAVORITE_MOVIE = "MOVIE";

    private SharedPreferences sharedPreferences;
    private EditText groupNumberEditText;
    private EditText listNumberEditText;
    private EditText favoriteMovieEditText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sharedPreferences = getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE);

        groupNumberEditText = findViewById(R.id.groupNumberEditText);
        listNumberEditText = findViewById(R.id.listNumberEditText);
        favoriteMovieEditText = findViewById(R.id.favoriteMovieEditText);
        Button saveButton = findViewById(R.id.saveButton);

        saveButton.setOnClickListener(view -> saveDataToSharedPreferences());

        // Восстановление сохраненных значений при загрузке приложения
        String groupNumber = sharedPreferences.getString(KEY_GROUP_NUMBER, "");
        String listNumber = sharedPreferences.getString(KEY_LIST_NUMBER, "");
        String favoriteMovie = sharedPreferences.getString(KEY_FAVORITE_MOVIE, "");

        groupNumberEditText.setText(groupNumber);
        listNumberEditText.setText(listNumber);
        favoriteMovieEditText.setText(favoriteMovie);
    }

    private void saveDataToSharedPreferences() {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        String groupNumber = groupNumberEditText.getText().toString();
        String listNumber = listNumberEditText.getText().toString();
        String favoriteMovie = favoriteMovieEditText.getText().toString();

        editor.putString(KEY_GROUP_NUMBER, groupNumber);
        editor.putString(KEY_LIST_NUMBER, listNumber);
        editor.putString(KEY_FAVORITE_MOVIE, favoriteMovie);

        editor.apply();
    }
}