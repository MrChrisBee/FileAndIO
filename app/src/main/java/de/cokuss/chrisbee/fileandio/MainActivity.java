package de.cokuss.chrisbee.fileandio;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class MainActivity extends AppCompatActivity {
    File appDir;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        appDir = getFilesDir();
        System.out.println("Paht: " + appDir.getAbsolutePath());
        System.out.println("Write: " + appDir.canWrite());

        File file = new File(getApplicationInfo().dataDir + "/testemal.txt");

        if (!file.exists()) {
            try {
                //new File(file.getParent()).mkdirs();
                file.createNewFile();
            } catch (IOException e) {
                System.out.println(e.getMessage().toString());
            }
        } else System.out.println("File already exists! ");

        dir();
        File toWrite = new File(appDir + "/demo.txt");
        System.out.println(toWrite.length());
        try (FileOutputStream fos = new FileOutputStream(toWrite, true);
             OutputStreamWriter ow = new OutputStreamWriter(fos);
             BufferedWriter bw = new BufferedWriter(ow)) {
            bw.write("Erste Zeile");
            bw.newLine();
            bw.write("Zweite Zeile");
            bw.newLine();
            bw.flush();
        } catch (IOException e) {
            System.out.println(e.getMessage().toString());
        }
        System.out.println(toWrite.length());

        try (FileInputStream fis = new FileInputStream(toWrite);
             InputStreamReader isr = new InputStreamReader(fis);
             BufferedReader br = new BufferedReader(isr)) {
            String str;
            while ((str = br.readLine()) != null) {
                System.out.println(str);
            }
        } catch (IOException e) {
            System.out.println(e.getMessage().toString());
        }
        //temporäre Datei erstellen !!
        try {
            File tmpFile = File.createTempFile("Dateiname",".extension",getCacheDir());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void dir() {
        File[] results = appDir.listFiles();
        System.out.println("''''''''''''''''''''''''''''''''''''''");
        System.out.println("File list:");
        for (File entry : results) {
            System.out.println(entry);
        }
        System.out.println("''''''''''''''''''''''''''''''''''''''");
    }
}