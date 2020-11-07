package com.netviet.weathernew.app;

import android.os.Looper;

public class ContextUtils {
    public static Boolean isOnMainThread(){
        return Looper.myLooper() == Looper.getMainLooper();
    }

    public static void ensureBackgroundThread(CallBackThread callBackThread){

        Runnable runnable = callBackThread::callBack;

        if (isOnMainThread()){
            new Thread(runnable).start();
        }else {
            callBackThread.callBack();
        }
    }
}


public interface CallBackThread{
    void callBack();
}