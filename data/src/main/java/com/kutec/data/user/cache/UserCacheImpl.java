package com.kutec.data.user.cache;

import android.content.Context;

import com.kutec.data.common.cache.FileManager;
import com.kutec.data.common.cache.serializer.JsonSerializer;
import com.kutec.data.user.entity.UserEntity;
import com.kutec.domain.common.excutor.ThreadExecutor;

import java.io.File;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;

import rx.Observable;

/**
 * Author: wangrenxing(wangrenxing87@gmail.com)
 * Date: 2016/3/4.
 */
@Singleton
public class UserCacheImpl implements UserCache {

    private static final String SETTINGS_FILE_NAME = "com.kutec.cleanarchitecture.SETTINGS";
    private static final String SETTINGS_KEY_LAST_CACHE_UPDATE = "last_cache_update";

    private static final String DEFAULT_FILE_NAME = "user_";
    private static final long EXPIRATION_TIME = 60 * 10 * 1000;

    private final Context context;
    private final File cacheDir;
    private final JsonSerializer<UserEntity> serializer;
    private final FileManager fileManager;
    private final ThreadExecutor threadExecutor;

    @Inject
    public UserCacheImpl(Context context, FileManager fileManager, @Named("userEntity") JsonSerializer<UserEntity> serializer, ThreadExecutor threadExecutor) {
        if( context == null || fileManager == null || serializer == null || threadExecutor == null ) {
            throw new IllegalArgumentException("UserCacheImpl args can not be null");
        }
        this.context = context;
        this.cacheDir = context.getCacheDir();
        this.fileManager = fileManager;
        this.serializer = serializer;
        this.threadExecutor = threadExecutor;
    }

    @Override
    public Observable<UserEntity> get(int userId) {
        return Observable.create( subscriber -> {
            File file = UserCacheImpl.this.buildFile(userId);
            UserEntity userEntity = null;
            if( file.exists() ) {
                userEntity = serializer.deSerialize(fileManager.readFileContent(file), UserEntity.class);
            }
           if( !subscriber.isUnsubscribed() ) {
                subscriber.onNext(userEntity);
               subscriber.onCompleted();
           }
        });
    }

    @Override
    public void put(UserEntity userEntity) {
        if( userEntity != null ) {
            if( !isCached(userEntity.getUserId()) ) {
                File fileToWrite = buildFile(userEntity.getUserId());
                String fileContent = serializer.serialize(userEntity);
                executeAsynchronously(new CacheWriter(fileContent, fileManager, fileToWrite));
                setLastCacheUpdateTimeMillis();
            }
        }
    }

    @Override
    public boolean isCached(int userid) {
        return buildFile(userid).exists();
    }

    @Override
    public boolean isExpired() {
        boolean expired = false;
        long lastCacheUpdateTime = fileManager.readFromPreference(context, SETTINGS_FILE_NAME, SETTINGS_KEY_LAST_CACHE_UPDATE);
        if( System.currentTimeMillis() - lastCacheUpdateTime > EXPIRATION_TIME ) {
            expired = true;
        }
        if( expired ) {
            evictAll();
        }
        return expired;
    }

    @Override
    public void evictAll() {
        executeAsynchronously(new CacheEvict(cacheDir, fileManager));
    }

    private void setLastCacheUpdateTimeMillis() {
        long currentMillis = System.currentTimeMillis();
        fileManager.writeToPreference(context, SETTINGS_FILE_NAME, SETTINGS_KEY_LAST_CACHE_UPDATE, currentMillis);
    }

    private File buildFile(int userId) {
        StringBuilder sb = new StringBuilder();
        sb.append(cacheDir.getPath())
          .append(File.separator)
          .append(DEFAULT_FILE_NAME)
          .append(userId);
        return new File(sb.toString());
    }

    private void executeAsynchronously(Runnable runnable) {
        threadExecutor.execute(runnable);
    }

    private static class CacheWriter implements Runnable {
        private FileManager fileManager;
        private File fileToWrite;
        private String contentToWrite;

        public CacheWriter(String contentToWrite, FileManager fileManager, File fileToWrite) {
            this.contentToWrite = contentToWrite;
            this.fileManager = fileManager;
            this.fileToWrite = fileToWrite;
        }

        @Override
        public void run() {
            fileManager.writeToFile(fileToWrite, contentToWrite);
        }
    }

    private static class CacheEvict implements Runnable {
        private FileManager fileManager;
        private File cacheDir;

        public CacheEvict(File cacheDir, FileManager fileManager) {
            this.cacheDir = cacheDir;
            this.fileManager = fileManager;
        }

        @Override
        public void run() {
            fileManager.clearDirectory(cacheDir);
        }
    }
}
