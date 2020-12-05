package com.yng.appmascotas;

//Librería de Notificación que se usa tanto para Android Wear como la normal
import androidx.core.app.NotificationCompat;

//Librerías para Android Wear
import androidx.core.app.NotificationManagerCompat;
import androidx.core.app.NotificationCompat.WearableExtender;
//Librerías para Notificaciones Normales
/*import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;*/
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

public class MyFirebaseMessagingService extends FirebaseMessagingService {
    private static final String TAG = "NotifTag";
    public String mensajeNotif="";

    @Override
    public void onMessageReceived(@NonNull RemoteMessage remoteMessage) {
        //super.onMessageReceived(remoteMessage);
        Log.i(TAG, "From: " + remoteMessage.getFrom());
        Log.i(TAG, "Notification Message Body: " + remoteMessage.getNotification().getBody());
        Log.i(TAG, "Notification Message Data: " + remoteMessage.getData());
        Log.i(TAG, "Notification Message Id: " + remoteMessage.getMessageId());

        mensajeNotif=remoteMessage.getNotification().getBody();

        despliegaNotificacion(mensajeNotif);
    }

    public void despliegaNotificacion(String mensajeNotif1)
    {
        //Acción 1
        Intent i = new Intent();
        i.setAction("VER_FOTOS_CUENTA");
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 0, i, PendingIntent.FLAG_UPDATE_CURRENT);
        //Acción 2
        Intent i2 = new Intent();
        i2.setAction("CAMBIAR_USUARIO");
        PendingIntent pendingIntent2 = PendingIntent.getBroadcast(this, 0, i2, PendingIntent.FLAG_UPDATE_CURRENT);
        //Acción 3
        Intent i3 = new Intent();
        i3.setAction("DAR_LIKE_OTRA");
        PendingIntent pendingIntent3 = PendingIntent.getBroadcast(this, 0, i3, PendingIntent.FLAG_UPDATE_CURRENT);

        Uri sonido = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);


        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);
        String NOTIFICATION_CHANNEL_ID = "my_channel_id_01";

        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this, NOTIFICATION_CHANNEL_ID);

        NotificationCompat.Action action =
                new NotificationCompat.Action.Builder(R.drawable.ic_full_poke_web, getString(R.string.ver_fotos_cuenta), pendingIntent)
                        .build();

        NotificationCompat.Action action2 =
                new NotificationCompat.Action.Builder(R.drawable.ic_full_poke_web, getString(R.string.cambiar_cuenta), pendingIntent2)
                        .build();

        NotificationCompat.Action action3 =
                new NotificationCompat.Action.Builder(R.drawable.ic_full_poke_web, getString(R.string.dar_like_otra_cuenta), pendingIntent3)
                        .build();

        NotificationCompat.WearableExtender wearableExtender =
                new NotificationCompat.WearableExtender();


        notificationBuilder.setAutoCancel(true)
                //.setDefaults(Notification.DEFAULT_ALL)
                .setWhen(System.currentTimeMillis())
                .setSmallIcon(R.drawable.hueso_blanco)
                .setTicker("Hearty365")
                .setContentTitle("Notificaciones")
                .setContentText(mensajeNotif1)
                .setContentIntent(pendingIntent)
                .extend(wearableExtender.addAction(action))
                .extend(wearableExtender.addAction(action2))
                .extend(wearableExtender.addAction(action3));


        notificationManager.notify(001, notificationBuilder.build());
    }

    //Esta funciona sin android wear
    /*public void despliegaNotificacion(String mensajeNotif1)
    {
        Intent i = new Intent(this, MainActivity.class);
        i.putExtra("frgToLoad", 1);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, i, PendingIntent.FLAG_ONE_SHOT);

        Uri sonido = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);



        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        String NOTIFICATION_CHANNEL_ID = "my_channel_id_01";

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel notificationChannel = new NotificationChannel(NOTIFICATION_CHANNEL_ID, "My Notifications", NotificationManager.IMPORTANCE_HIGH);

            // Configure the notification channel.
            notificationChannel.setDescription("Channel description");
            notificationChannel.enableLights(true);
            notificationChannel.setLightColor(Color.RED);
            notificationChannel.setVibrationPattern(new long[]{0, 1000, 500, 1000});
            notificationChannel.enableVibration(true);
            notificationManager.createNotificationChannel(notificationChannel);
        }


        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this, NOTIFICATION_CHANNEL_ID);

        notificationBuilder.setAutoCancel(true)
                .setDefaults(Notification.DEFAULT_ALL)
                .setWhen(System.currentTimeMillis())
                .setSmallIcon(R.drawable.hueso_blanco)
                .setTicker("Hearty365")
                //     .setPriority(Notification.PRIORITY_MAX)
                .setContentTitle("Notificaciones")
                .setContentText(mensajeNotif1)
                .setContentIntent(pendingIntent);

        notificationManager.notify(1, notificationBuilder.build());
    }*/

    public void onTokenRefresh()
    {

    }

    public void onNewToken(String token) {
        Log.d(TAG, "Refreshed token: " + token);

        // If you want to send messages to this application instance or
        // manage this apps subscriptions on the server side, send the
        // FCM registration token to your app server.
        sendRegistrationToServer(token);
    }

    private void sendRegistrationToServer(String token) {
        // TODO: Implement this method to send token to your app server.


    }

}
