package com.ivcrypto;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.apache.commons.io.FilenameUtils;

import com.nbsp.materialfilepicker.MaterialFilePicker;
import com.nbsp.materialfilepicker.ui.FilePickerActivity;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    Button browseButton;
    Button encryptButton;
    Button decryptButton;
    EditText passwordTextView;
    static String password;

    static String file_Path;
    static String file_Extension;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        browseButton = (Button) findViewById(R.id.browse_button);
        encryptButton = (Button) findViewById(R.id.encrypt_button);
        decryptButton = (Button) findViewById(R.id.decrypt_button);
        passwordTextView = (EditText) findViewById(R.id.password_view);


        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.M && checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1001);
        }

        browseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new MaterialFilePicker()
                        .withActivity(MainActivity.this)
                        .withRequestCode(1000)
                        .start();
            }
        });
        
        encryptButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // To check if the password is not empty
                password = passwordTextView.getText().toString().trim();
                if (password.equals("")) {
                    Toast.makeText(MainActivity.this, "Password Cannot Be Empty", Toast.LENGTH_SHORT).show();
                } else {
                    //should call the encrypt method
                    try {
                        byte[] bytesEncrypted = Encryption.Encrypt(file_Path, password);

                        FileOutputStream fileOutputStream = null;

                        try {
                            fileOutputStream = new FileOutputStream(FilenameUtils.removeExtension(file_Path).toString()+".encrypted");
                            file_Extension = FilenameUtils.getExtension(file_Path).toString();
                            fileOutputStream.write(bytesEncrypted);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    Toast.makeText(MainActivity.this, "Completed Encryption", Toast.LENGTH_SHORT).show();
                }
            }
        });
        
        decryptButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Check if the password is not empty
                password = passwordTextView.getText().toString().trim();
                if (password.equals("")) {
                    Toast.makeText(MainActivity.this, "Password Cannot Be Empty", Toast.LENGTH_SHORT).show();
                } else {
                    //call the decrypt method
                    try {
                        byte[] bytesDecrypted = Encryption.Decrypt(file_Path, password);

                        FileOutputStream fileOutputStream = null;

                        try {
                            fileOutputStream = new FileOutputStream(FilenameUtils.removeExtension(file_Path).toString()+ "restored." + file_Extension);
                            fileOutputStream.write(bytesDecrypted);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    Toast.makeText(MainActivity.this, "Completed Decryption", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1000 && resultCode == RESULT_OK) {
            String getfilePath = data.getStringExtra(FilePickerActivity.RESULT_FILE_PATH);
            file_Path = getfilePath;
            Toast.makeText(this, file_Path, Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case 1001: {
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(this, "Permission Granted", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, "Permission Denied", Toast.LENGTH_SHORT).show();
                    finish();
                }
            }
        }
    }
}
