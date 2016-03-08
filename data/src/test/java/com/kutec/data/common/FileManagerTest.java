package com.kutec.data.common;

import com.kutec.data.ApplicationCaseTest;
import com.kutec.data.common.cache.FileManager;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.robolectric.RuntimeEnvironment;

import java.io.File;

/**
 * Author: wangrenxing(wangrenxing87@gmail.com)
 * Date: 2016/3/3.
 */
public class FileManagerTest extends ApplicationCaseTest {
    private FileManager fileManager;
    private File cacheDir;


    @Before
    public void setUp() {
        fileManager = new FileManager();
        cacheDir = RuntimeEnvironment.application.getCacheDir();
    }

    @After
    public void tearDown() {
        if( cacheDir != null && cacheDir.exists() ) {
            fileManager.clearDirectory(cacheDir);
        }
    }

    @Test
    public void testWriteToFile() {
        File file = createDummyFile();
        String content = "content";
        fileManager.writeToFile(file, content);
        Assert.assertTrue(file.exists());
    }

    @Test
    public void testFileContent() {
        File file = createDummyFile();
        String content = "content\n";
        fileManager.writeToFile(file, content);
        Assert.assertEquals(content, fileManager.readFileContent(file));
    }

    private File createDummyFile() {
        return new File(cacheDir+File.separator+"dummyFile");
    }
}
