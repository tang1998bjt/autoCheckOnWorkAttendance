package com.ojama.myapplication.service;

import android.app.ActivityManager;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.IBinder;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import com.ojama.myapplication.Contrast;
import com.ojama.myapplication.Main2Activity;
import com.ojama.myapplication.ShellUtil;

import static com.ojama.myapplication.Main2Activity.sleep;

import java.util.Calendar;
import java.util.Collections;
import java.util.Random;

public class HonestService extends Service {
    private Random random = new Random();
    private static final int INTERVAL_TIME = 10;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        // 获取AlarmManager对象
        AlarmManager aManager = (AlarmManager) getSystemService(Service.ALARM_SERVICE);
        Intent myIntent = new Intent(this, JusticeReceiver.class);
        intent.setAction("ojama");
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 0, myIntent, PendingIntent.FLAG_CANCEL_CURRENT);
        int apiLevel = getApiLevel();
        java.util.Calendar mCalendar;
        mCalendar = Calendar.getInstance();
        mCalendar.setTimeInMillis(System.currentTimeMillis());
        mCalendar.add(Calendar.SECOND, INTERVAL_TIME);
/*        if (apiLevel < Build.VERSION_CODES.KITKAT) {
            aManager.setRepeating(AlarmManager.RTC_WAKEUP, mCalendar.getTimeInMillis(), INTERVAL_TIME, pendingIntent);
        } else if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                aManager.setExact(AlarmManager.RTC_WAKEUP, mCalendar.getTimeInMillis(), pendingIntent);
            }
        } else {
            aManager.setExactAndAllowWhileIdle(AlarmManager.RTC_WAKEUP, mCalendar.getTimeInMillis(), pendingIntent);
        }
*/
        aManager.setRepeating(AlarmManager.RTC_WAKEUP, SystemClock.elapsedRealtime(), 5000, pendingIntent);
        new JusticeThread().start();
        return super.onStartCommand(intent, flags, startId);
    }

    public static int getApiLevel() {
        try {
            java.lang.reflect.Field f = android.os.Build.VERSION.class.getField("SDK_INT");
            f.setAccessible(true);
            return f.getInt(null);
        } catch (Throwable e) {
            return 3;
        }
    }

    private void doShellCmdInputTap(int x, int y) {
        ShellUtil.execCmd(Collections.singletonList("input tap " + x + " " + y));
    }

    public void hoest(boolean work) {
        PackageManager packageManager = getPackageManager();
        String packageName = "com.alibaba.android.rimet";
        Intent launchIntentForPackage = packageManager.getLaunchIntentForPackage(packageName);
        if (launchIntentForPackage != null) {
            startActivity(launchIntentForPackage);
        }
        sleep(20000);
        doShellCmdInputTap(500, 1793);
        sleep(3000);
        doShellCmdInputTap(164, 1077);
        sleep(10000);
        if (work) {
            doShellCmdInputTap(582, 777);
        } else {
            doShellCmdInputTap(555, 1290);
        }
        sleep(10000);
        doShellCmdInputTap(105, 160);
    }

    class JusticeThread extends Thread {
        @Override
        public void run() {
            while (true) {
                Calendar now = Calendar.getInstance();
                int day = now.get(Calendar.DAY_OF_MONTH);
                int hour = now.get(Calendar.HOUR_OF_DAY);
                int minute = now.get(Calendar.MINUTE);
                int week = now.get(Calendar.DAY_OF_WEEK);
                doShellCmdInputTap(162, 1873);
                Main2Activity.sleep(1000);
                doShellCmdInputTap(566, 1859);
                if (week == Calendar.SUNDAY || week == Calendar.SATURDAY) {
                    Main2Activity.sleep(60000);
                    continue;
                }
                if (Contrast.date != day) {
                    Contrast.date = day;
                    Contrast.onDuty = false;
                    Contrast.offDuty = false;
                    Contrast.random = random.nextInt(10);
                }
                if (hour == 9 && !Contrast.onDuty) {
                    if (minute >= Contrast.random) {
                        //打卡
                        hoest(true);
                        Contrast.onDuty = true;
                    }
                }
                if (hour == 20 && !Contrast.offDuty) {
                    //打卡
                    hoest(false);
                    Contrast.offDuty = true;
                }
                Main2Activity.sleep(3000);
            }
        }
    }
}
