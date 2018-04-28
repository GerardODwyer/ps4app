package com.example.gerardodwyer.ps4collection;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.concurrent.ExecutionException;
//designed based on vidio https://www.youtube.com/watch?v=CNoj9vzAYiQ
public class MoreDetailsActivity extends AppCompatActivity {

    EditText editText;
    Button write, read;
    TextView textView;

    String fileName = "file.txt";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_more_details);

        editText = (EditText) findViewById(R.id.editText);
        write = (Button) findViewById(R.id.write);
        read = (Button) findViewById(R.id.read);
        textView = (TextView) findViewById(R.id.textViewer);

        read.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                textView.setText(readFile(fileName));


            }
        });

        write.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                saveFile(fileName, editText.getText().toString());
                Toast.makeText(MoreDetailsActivity.this, "Text Saved. " +
                        " " +
                        "", Toast.LENGTH_SHORT).show();
            }
        });

    }

    public void saveFile(String file, String text) {

        try{
            FileOutputStream fos = openFileOutput(file, Context.MODE_PRIVATE);
            fos.write(text.getBytes());
            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(MoreDetailsActivity.this, "Error Saving File.", Toast.LENGTH_SHORT).show();
        }
    }

    public String readFile(String file) {
        String text = "";

        try {
            FileInputStream fis = openFileInput(file);
            int size = fis.available();
            byte[] buffer = new byte[size];
            fis.read(buffer);
            fis.close();
            text = new String(buffer);
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(MoreDetailsActivity.this, "Error Reading File.", Toast.LENGTH_SHORT).show();
        }

        return text;
    }
}
