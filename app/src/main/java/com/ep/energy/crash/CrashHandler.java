package com.ep.energy.crash;

import android.os.Environment;
import android.support.v4.BuildConfig;
import android.util.Log;

import com.ep.energy.SharePrefrenceUtil;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.lang.Thread.UncaughtExceptionHandler;
import java.util.LinkedList;

/**
 * UncaughtException处理类,当程序发生Uncaught异常的时候,有该类来接管程序,并记录发送错误报告.
 *
 * @author user
 */
public class CrashHandler implements UncaughtExceptionHandler {
    private static final String TAG = "MS_CrashHandler---->";
    private UncaughtExceptionHandler defaultUEH;
    private static CrashHandler INSTANCE = new CrashHandler();
    private boolean upload, writeSD;

    // 构造函数，获取默认的处理方法
    public CrashHandler() {

    }

    public static CrashHandler getInstance() {
        return INSTANCE;
    }

    public void init(boolean upload, boolean writeSD) {
        this.upload = upload;
        this.writeSD = writeSD;
        // 获取系统默认的UncaughtException处理器
        this.defaultUEH = Thread.getDefaultUncaughtExceptionHandler();
        // 设置该CrashHandler为程序的默认处理器
        Thread.setDefaultUncaughtExceptionHandler(this);
    }

    // 这个接口必须重写，用来处理我们的异常信息
    @Override
    public void uncaughtException(Thread thread, Throwable ex) {
        final Writer result = new StringWriter();
        final PrintWriter printWriter = new PrintWriter(result);
        StackTraceElement[] trace = ex.getStackTrace();
        StackTraceElement[] trace2 = new StackTraceElement[trace.length + 3];
        System.arraycopy(trace, 0, trace2, 0, trace.length);
        trace2[trace.length + 0] = new StackTraceElement("APP", "VERSION",
                BuildConfig.VERSION_NAME + " " + BuildConfig.VERSION_CODE, -1);
        trace2[trace.length + 1] = new StackTraceElement("Android", "MODEL",
                android.os.Build.MODEL + " " + android.os.Build.VERSION.RELEASE, -1);
        trace2[trace.length + 2] = new StackTraceElement("Android",
                "FINGERPRINT", android.os.Build.FINGERPRINT, -1);
        ex.setStackTrace(trace2);
        ex.printStackTrace(printWriter);
        final String stacktrace = result.toString();
        printWriter.close();
        Log.e(TAG, stacktrace);

        // 这里把刚才异常堆栈信息写入SD卡的Log日志里面
//        if (writeSD && Environment.getExternalStorageState().equals(
//                Environment.MEDIA_MOUNTED)) {
//            writeLog(stacktrace, file.getAbsolutePath());
//        }

        if (upload)
            postLog(stacktrace);

        defaultUEH.uncaughtException(thread, ex);
    }

//    private void writeLog(String log, String name) {
//        CharSequence timestamp = TimeUtil.getTimeStr(System.currentTimeMillis()
//                + "");
//        String filename = name + File.separator + timestamp + ".log";
//
//        try {
//            FileOutputStream stream = new FileOutputStream(filename);
//            OutputStreamWriter output = new OutputStreamWriter(stream);
//            BufferedWriter bw = new BufferedWriter(output);
//            // 写入相关Log到文件
//            bw.write(log);
//            bw.newLine();
//            bw.close();
//            output.close();
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }

    private void postLog(final String log) {
        SharePrefrenceUtil.setOOMLog(log);
    }

}
