//package com.example.alphacar;
//
//import android.app.Activity;
//import android.content.Context;
//import android.os.Build;
//
//import androidx.annotation.NonNull;
//import androidx.core.app.ActivityCompat;
//
//public class PermissionUtil {
//
//    public static int checkSelfPermission(Context context, String permission){
//        if(permission == null){
//            throw new IllegalArgumentException("permission is null");
//        }
//        return context.checkPermission(permission, android.os.Process.myPid(), Process.myUid());
//    }
//
//    public static boolean checkPermission(Activity activity, String permission){
//        int permissionResult = ActivityCompat.checkSelfPermission(activity, permission);
//    }
//
//    public static void requestPermission(final @NonNull Activity activity,
//                                         final @NonNull String[] permission, final int requestCode){
//        if (Build.VERSION.SDK_INT >= 23){
//            ActivityCompatApi23.requestPermissions(activity, permission, requestCode);
//        }
//    }
//
//
//}
//
//
