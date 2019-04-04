package com.ojama.myapplication;

import java.io.Closeable;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.List;

public class ShellUtil {

    /**
     * 执行adb 批命令
     *
     * @param list 传入的命令list
     * @return CommandResult
     */
    public static void execCmd(List<String> list) {
        Process process = null;
        Main2Activity.sleep(500);
        DataOutputStream os = null;
        try {
            //必须要 su
            Main2Activity.sleep(500);
            process = Runtime.getRuntime().exec("su");
            Main2Activity.sleep(500);
            os = new DataOutputStream(process.getOutputStream());
            for (String command : list) {
                //逐行执行 adb 指令
                if (command == null) continue;
                Main2Activity.sleep(500);
                os.write(command.getBytes());
                Main2Activity.sleep(500);
                os.writeBytes("\n");
                Main2Activity.sleep(500);
                os.flush();
            }
            os.writeBytes("exit\n");
            os.flush();
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            closeIO(os);
            if (process != null) {
                process.destroy();
            }
        }
    }

    /**
     * 关闭IO
     *
     * @param closeables closeable
     */
    public static void closeIO(Closeable... closeables) {
        if (closeables == null) return;
        for (Closeable closeable : closeables) {
            if (closeable != null) {
                try {
                    closeable.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
