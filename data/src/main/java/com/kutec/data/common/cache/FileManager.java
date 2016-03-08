package com.kutec.data.common.cache;

import android.content.Context;
import android.content.SharedPreferences;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Author: wangrenxing(wangrenxing87@gmail.com)
 * Date: 2016/3/3.
 */
@Singleton
public class FileManager {

    @Inject
    public FileManager() {
    }

    public void writeToFile(File file, final String content) {
        if( !file.exists() ) {
            try {
                FileWriter fileWriter = new FileWriter(file);
                fileWriter.write(content);
                fileWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public String readFileContent(File file) {
        StringBuffer stringBuffer = new StringBuffer();
        if( file.exists() ) {
            try {
                FileReader fileReader = new FileReader(file);
                BufferedReader bufferedReader = new BufferedReader(fileReader);
                String line;
                while( (line = bufferedReader.readLine()) != null ) {
                    stringBuffer.append(line).append("\n");
                }
                bufferedReader.close();
                fileReader.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch ( IOException e) {
                e.printStackTrace();
            }
        }
        return stringBuffer.toString();
    }

    public boolean exists(File file) {
        return file.exists();
    }

    public void clearDirectory(File directory) {
        if( directory.exists() ) {
            for( File file : directory.listFiles() ) {
                file.delete();
            }
        }
    }

    public void writeToPreference(Context context, String preferenceFileName, String key, long value) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(preferenceFileName, Context.MODE_PRIVATE);
        sharedPreferences.edit()
                .putLong(key, value)
                .commit();
    }

    public long readFromPreference(Context context, String preferenceFileName, String key) {
        return context.getSharedPreferences(preferenceFileName, Context.MODE_PRIVATE)
                .getLong(key, 0);

    }
}
