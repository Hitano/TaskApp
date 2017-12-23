package jp.techacademy.tanooka.hiroshi.taskapp;

import android.app.Application;

import io.realm.Realm;

/**
 * Created by Tanooka on 2017/12/22.
 */

public class TaskApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Realm.init(this);
    }
}
