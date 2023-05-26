package ru.mirea.lukashev_ni.notebook;

import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

public class MainActivity extends AppCompatActivity {

    private EditText editTextFileName;
    private EditText editTextQuote;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextFileName = findViewById(R.id.editTextFileName);
        editTextQuote = findViewById(R.id.editTextQuote);

        Button buttonSave = findViewById(R.id.buttonSave);
        buttonSave.setOnClickListener(v -> saveDataToFile());

        Button buttonLoad = findViewById(R.id.buttonLoad);
        buttonLoad.setOnClickListener(v -> loadDataFromFile());
    }

    private void saveDataToFile() {
        String fileName = editTextFileName.getText().toString();
        String quote = editTextQuote.getText().toString();

        File path = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS), "Directory_Documents");
        path.mkdirs(); // Создание директории, если она не существует

        File file = new File(path, fileName);
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            fileOutputStream.write(quote.getBytes());
            fileOutputStream.close();
            Log.i("ExternalStorage", "Data saved to file: " + file.getAbsolutePath());
        } catch (IOException e) {
            Log.e("ExternalStorage", "Failed to save data to file: " + e.getMessage());
        }
    }

    private void loadDataFromFile() {
        String fileName = editTextFileName.getText().toString();

        File path = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS), "Directory_Documents");
        File file = new File(path, fileName);

        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream, StandardCharsets.UTF_8);
            BufferedReader reader = new BufferedReader(inputStreamReader);

            StringBuilder stringBuilder = new StringBuilder();
            String line = reader.readLine();
            while (line != null) {
                stringBuilder.append(line);
                line = reader.readLine();
            }

            String quote = stringBuilder.toString();
            editTextQuote.setText(quote);
            Log.i("ExternalStorage", "Data loaded from file: " + file.getAbsolutePath());
        } catch (IOException e) {
            Log.e("ExternalStorage", "Failed to load data from file: " + e.getMessage());
        }
    }
}
