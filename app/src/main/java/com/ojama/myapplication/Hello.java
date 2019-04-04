package com.ojama.myapplication;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.os.RemoteException;
import android.support.test.InstrumentationRegistry;
import android.support.test.uiautomator.UiDevice;

public class Hello extends Application {

    public static void main(String[] args) throws RemoteException {
        UiDevice device = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation());//获取设备用例
        if (device.isScreenOn()) {
            device.wakeUp();
        }
        startAPP("com.alibaba.android.rimet");
        device.waitForWindowUpdate("com.alibaba.android.rimet", 5 * 2000);//等待app
    }

    public static void startAPP(String packageName) {
        Context context = InstrumentationRegistry.getContext();
        Intent intent = context.getPackageManager().getLaunchIntentForPackage(packageName);
        context.startActivity(intent);
    }
}
