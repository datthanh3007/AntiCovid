package com.example.anticovid.Fragment;

import android.app.Activity;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.util.Log;
import android.widget.Toast;

import com.example.anticovid.R;
import com.google.android.gms.location.LocationResult;

import androidx.core.app.NotificationCompat;
import androidx.fragment.app.Fragment;

public class LocationUpdatesBroadcastReceiver extends BroadcastReceiver {
    private static final String CHANNEL_ID ="ChancelHN" ;
    private String strMesage = "Bạn vừa di chuyển gần khu vực có dịch bệnh";
    private String strTitle="ENDCOVI";
    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals("abc")) {
            Log.d("aaa", "OK");

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                if (LocationResult.extractResult(intent) != null) {
                    LocationResult.extractResult(intent).getLocations().forEach( (location) ->
                          //  Log.d("aaa", "" + location.getLatitude() + location.getLongitude());

//                    for(int i =0;i<fragmentHome.locationArr.size();i++){
//                        double kd = Double.parseDouble(fragmentHome.locationArr.get(i).getLongitude());
//                        double vd = Double.parseDouble(fragmentHome.locationArr.get(i).getLatitude());
//
//                    }

                            {
                                for (int i = 0; i < fragmentHome.locationArr.size(); i++) {
                               //     Log.d("abc", "KQ"+calculator(105.836914062500000f,20.987602233886720f,20.985224f,105.815475f));
                                    float longd = Float.parseFloat(fragmentHome.locationArr.get(i).getLongitude());
                                    float latd= Float.parseFloat(fragmentHome.locationArr.get(i).getLatitude());
                                    float latMe = (float)(location.getLatitude());
                                    float longMe = (float)(location.getLongitude());
//                                    Log.d("abc", "OK+KC:"+longd+","+latd);
//                                    Log.d("abc", "OK+KC2:"+longMe+","+latMe);
                                      if(calculator(latMe,longMe,latd,longd)<=10000){
                                      Log.d("abc", "OK+KC:"+calculator(latMe,longMe,latd,longd));
                                        Intent notification  = new Intent(context,fragmentDialogInfected.class);
                                        notification.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                        notification.putExtra("message",strMesage);
                                        notification.putExtra("title",strTitle);
                                        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, notification, PendingIntent.FLAG_UPDATE_CURRENT);
                                        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, CHANNEL_ID)
                                                .setSmallIcon(R.drawable.avatarapp)
                                                .setContentTitle(strTitle)
                                                .setContentText(strMesage)
                                                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                                                .setContentIntent(pendingIntent)
                                                .setAutoCancel(true);
                                        NotificationManager manager = (NotificationManager)context.getSystemService(Context.NOTIFICATION_SERVICE);
                                        manager.notify(0,builder.build());
                                    }
                                }
                            }

                    );
                }
            } else {
                Log.d("aaa", "NOTOK");

//                if (listCaseInformation.isNotEmpty()) {
//                    listCaseInformation.forEach {
//                        if (getValueSharePreferences(it.id) != it.id) {
//                            NotificationUtils.create(
//                                    context,
//                                    context.getString(R.string.title_notification_location_update),
//                                    context.getString(R.string.msg_notification_location_update, listCaseInformation.size),
//                                    R.drawable.ic_warning,
//                                    getPendingIntent(context)
//                            )
//                            putValueSharePreferences(it.id)
//                        }
//                    }
//                    sharedPreferences = null
//                }
            }
        }
    }

//    private void createNotificationChannel() {
//        // Create the NotificationChannel, but only on API 26+ because
//        // the NotificationChannel class is new and not in the support library
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//            CharSequence name = getString(R.string.channel_name);
//            String description = getString(R.string.channel_description);
//            int importance = NotificationManager.IMPORTANCE_DEFAULT;
//            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
//            channel.setDescription(description);
//            // Register the channel with the system; you can't change the importance
//            // or other notification behaviors after this
//            NotificationManager notificationManager = getActivity().getSystemService(NotificationManager.class);
//            notificationManager.createNotificationChannel(channel);
//        }
//    }



    private Double calculator(float lat1, float lon1, float lat2, float lon2) {
        float r = 6371;
        double phi1 = deg2rad(lat1);
        double phi2 = deg2rad(lat2);
        double deltaPhi = deg2rad(lat2 - lat1);
        double deltaLambda = deg2rad(lon2 - lon1);
        double a = Math.sin(deltaPhi / 2) * Math.sin(deltaPhi / 2) + Math.cos(phi1) * Math.cos(phi2) * Math.sin(deltaLambda / 2) * Math.sin(deltaLambda / 2);

        double c = Math.atan2(Math.sqrt(a), Math.sqrt(1 - a)) * 2;

        return r * c;
    }

    private Double deg2rad(float deg) {
        return deg * (Math.PI / 180);
    }


    private Double calculator2(double lat1, double lon1, double lat2, double lon2) {
        float r = 6371;
        double phi1 = deg2rad2(lat1);
        double phi2 = deg2rad2(lat2);
        double deltaPhi = deg2rad2(lat2 - lat1);
        double deltaLambda = deg2rad2(lon2 - lon1);
        double a = Math.sin(deltaPhi / 2) * Math.sin(deltaPhi / 2) + Math.cos(phi1) * Math.cos(phi2) * Math.sin(deltaLambda / 2) * Math.sin(deltaLambda / 2);

        double c = Math.atan2(Math.sqrt(a), Math.sqrt(1 - a)) * 2;

        return r * c;
    }

    private Double deg2rad2(double deg) {
        return deg * (Math.PI / 180);
    }
}
