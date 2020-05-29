package com.mohfajar.gantara;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.BitmapFactory;
import android.media.RingtoneManager;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.example.mohfajar.gantara.R;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.mohfajar.gantara.Chat.ChatActivity;
import com.mohfajar.gantara.Chat.DbChat;
import com.mohfajar.gantara.Chat.IsiChat;

import org.json.JSONException;
import org.json.JSONObject;

public class MyFirebaseMessageService extends FirebaseMessagingService {

    private String TAG="Firebase Message";
    DbChat dbhelper;

    private NotificationManager managerCompat;
    private SharedPrefManager sharedPrefManager;

    SQLiteDatabase database;

    @Override
    public void onNewToken(String s) {
        super.onNewToken(s);
        Log.d(TAG,"New token : " + s);
    }

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        // ...

        // TODO(developer): Handle FCM messages here.
        // Not getting messages here? See why this may be: https://goo.gl/39bRNJ
        dbhelper = new DbChat(this);
        database = dbhelper.getWritableDatabase();
        sharedPrefManager = SharedPrefManager.getInstance(this);
        Log.d(TAG, "From: " + remoteMessage.getFrom());
        managerCompat = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        // Check if message contains a data payload.
        if (remoteMessage.getData().size() > 0) {
            Log.d(TAG, "Message data payload: " + remoteMessage.getData());
            try {
                JSONObject data = new JSONObject(remoteMessage.getData().get("message"));
                if(data.getString("id_pengirim").equals(sharedPrefManager.getAtletID()) || !data.getString("id_penerima")
                        .equals(sharedPrefManager.getAtletID()))
                    return;
                IsiChat chat = new IsiChat(data);

                Intent intent = null;
                Intent intentMessage = new Intent("message_intent");
                PendingIntent pendingIntent = null;

                if(data.getString("id_pengirim").equals(sharedPrefManager.getAtletID())) {
                    Log.d("Pmon-Firebase","self chat");
                    dbhelper.save(chat);
                    intentMessage.putExtra("id_penerima",data.getString("id_penerima"));
                    intentMessage.putExtra("id_pengirim",data.getString("id_pengirim"));
                    intentMessage.putExtra("waktu",data.getString("waktu"));
                    intentMessage.putExtra("message",data.getString("message"));
                    sendBroadcast(intentMessage);
                    return;
                }

                NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(this);
                Notification notification;

//                if(ChatActivity.isRunning())
//                    intent = new Intent();
//                else
                    intent = new Intent(this,ChatActivity.class);

                pendingIntent = PendingIntent.getActivity(this,101,intent,0);

                mBuilder
                        .setAutoCancel(true)
                        .setSmallIcon(R.drawable.notification_pmon)
                        .setContentTitle("Pesan baru")
                        .setLargeIcon(BitmapFactory.decodeResource(this.getResources(), R.drawable.notification_pmon))
                        .setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION))
                        .setContentText(data.getString("message"));
                mBuilder.setContentIntent(pendingIntent);

                notification = mBuilder.build();

                managerCompat.notify(10,notification);
//                startForeground(10,notification);
                dbhelper.save(chat);
                intentMessage.putExtra("id_penerima",data.getString("id_penerima"));
                intentMessage.putExtra("id_pengirim",data.getString("id_pengirim"));
                intentMessage.putExtra("waktu",data.getString("waktu"));
                intentMessage.putExtra("message",data.getString("message"));
                sendBroadcast(intentMessage);

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        // Check if message contains a notification payload.
        if (remoteMessage.getNotification() != null) {
            Log.d(TAG, "Message Notification Body: " + remoteMessage.getNotification().getBody());
        }

        // Also if you intend on generating your own notifications as a result of a received FCM
        // message, here is where that should be initiated. See sendNotification method below.


    }
}
