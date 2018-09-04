package com.example.yjw.KillProsser;

import android.app.ActivityManager;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.os.Debug;

import java.util.ArrayList;
import java.util.List;

/**
 * @author YJW
 * @create 2018/7/27
 * @Describe
 */

public class ProcessInfoProviderUtil {
    public  static List<ProcessInfo> getProcessInfos(Context context){
        //创造要返回的集合
        List<ProcessInfo> list = new ArrayList<ProcessInfo>();
        PackageManager pm = (PackageManager)context.getPackageManager();

        //拿到手机里的所有进程信息
        ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningAppProcessInfo> runningAppProcesses = am.getRunningAppProcesses();
        for(ActivityManager.RunningAppProcessInfo processInfo : runningAppProcesses) {

            //要添加的对象
            ProcessInfo info = new ProcessInfo();
            //包名即为进程名字
            String packageName = processInfo.processName;
            info.setPackageName(packageName);
            //以进程号为参数，获得进程的信息(内存占用的大小)
            //使用时通过Formatter.formatFileSize(this,SystemProcessUitl.getAvaMemory(this))转换为MB即可
            Debug.MemoryInfo[] processMemoryInfo = am.getProcessMemoryInfo(new int[]{processInfo.pid});
            long totalPrivateDirty = processMemoryInfo[0].getTotalPrivateDirty()*1024l;
            info.setSize(totalPrivateDirty);
            //应用名字和图标
            ApplicationInfo applicationInfo = null;
            try {
                applicationInfo = pm.getApplicationInfo(packageName, 0);
                String name = applicationInfo.loadLabel(pm).toString();
                info.setName(name);
                Drawable icon = applicationInfo.loadIcon(pm);
                info.setIcon(icon);
                if ( (applicationInfo.flags & ApplicationInfo.FLAG_SYSTEM)== 0 ){
                    //用户进程
                    info.setUesrProcess(true);
                }else {
                    //系统进程
                    info.setUesrProcess(false);
                }
            } catch (PackageManager.NameNotFoundException e) {
                e.printStackTrace();
            }
            if(info.getName()!=null) {
                list.add(info);
            }
        }
        return list;
    }
}