package de.cokuss.chrisbee.fileandio;

import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.io.File;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    File appDir;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        appDir = new File(getApplicationInfo().dataDir);
        System.out.println("Paht: " + appDir.getAbsolutePath());
        System.out.println("Write: " + appDir.canWrite());

        File file = new File(getApplicationInfo().dataDir+"/testemal.txt");

        if (!file.exists()) {
            try {
                    //new File(file.getParent()).mkdirs();
                    file.createNewFile();
            } catch (IOException e) {
                System.out.println(e.getMessage().toString());
            }
        } else System.out.println("File already exists! ");

        dir();
    }

    private void dir() {
        File[] results = appDir.listFiles();
        System.out.println("''''''''''''''''''''''''''''''''''''''");
        System.out.println("File list:");
        for(File entry : results) {
            System.out.println(entry);
        }
        System.out.println("''''''''''''''''''''''''''''''''''''''");
    }


}

