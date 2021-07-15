package com.example.javasharing2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.app.AlarmManager;
import android.app.AlertDialog;
import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    public static final String NOTIFICATION_CHANNEL_ID = "10001" ;
    private final static String default_notification_channel_id = "default" ;

    Button explicit_btn, implicit_btn, exit_btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        explicit_btn = findViewById(R.id.button2);
        implicit_btn = findViewById(R.id.button1);
        exit_btn = findViewById(R.id.exit);

        implicit_btn.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(),IntentActivity2.class);
            startActivity(intent);
        });

        explicit_btn.setOnClickListener(v -> {

            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(Uri.parse("http://airost.epizy.com/airost-team-website/tutorial/article.php?i=1"));
            startActivity(intent);

            //Uri gmmIntentUri = Uri.parse("geo:37.7749,-122.4194");
            //Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
            //mapIntent.setPackage("com.google.android.apps.maps");
            //startActivity(mapIntent);
        });

        exit_btn.setOnClickListener(v -> {

            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
            // Setting Alert Dialog Title
            alertDialogBuilder.setTitle("Confirm Exit..!!!");
            // Icon Of Alert Dialog
            alertDialogBuilder.setIcon(R.drawable.ic_launcher_foreground);
            // Setting Alert Dialog Message
            alertDialogBuilder.setMessage("Are you sure,You want to exit");
            alertDialogBuilder.setCancelable(false);

            alertDialogBuilder.setPositiveButton("Yes", (arg0, arg1) -> {
                finish();
            });

            alertDialogBuilder.setNegativeButton("No", (dialog, which) -> {
                Toast.makeText(MainActivity.this, "You clicked over No", Toast.LENGTH_SHORT).show();
            });
            alertDialogBuilder.setNeutralButton("Cancel", (dialog, which) -> {
                Toast.makeText(getApplicationContext(), "You clicked on Cancel", Toast.LENGTH_SHORT).show();
            });

            AlertDialog alertDialog = alertDialogBuilder.create();
            alertDialog.show();

        });

    }
    @Override
    public boolean onCreateOptionsMenu (Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_menu , menu) ;
        return true;
    }
    @Override
    public boolean onOptionsItemSelected (MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_5 :
                scheduleNotification(getNotification( "after 5sec" ) , 5000 ) ;
                return true;
            case R.id.action_10 :
                scheduleNotification(getNotification( "after 10sec" ) , 10000 ) ;
                return true;
            case R.id.action_30 :
                scheduleNotification(getNotification( "after 30sec" ) , 30000 ) ;
                return true;
            default :
                return super .onOptionsItemSelected(item) ;
        }
    }
    private void scheduleNotification (Notification notification , int delay) {
        Intent notificationIntent = new Intent( this, MyNotificationPublisher.class ) ;
        notificationIntent.putExtra(MyNotificationPublisher. NOTIFICATION_ID , 1 ) ;
        notificationIntent.putExtra(MyNotificationPublisher. NOTIFICATION , notification) ;
        PendingIntent pendingIntent = PendingIntent. getBroadcast ( this, 0 , notificationIntent , PendingIntent. FLAG_UPDATE_CURRENT ) ;
        long futureInMillis = SystemClock. elapsedRealtime () + delay ;
        AlarmManager alarmManager = (AlarmManager) getSystemService(Context. ALARM_SERVICE ) ;
        assert alarmManager != null;
        alarmManager.set(AlarmManager. ELAPSED_REALTIME_WAKEUP , futureInMillis , pendingIntent) ;
    }
    private Notification getNotification (String content) {
        NotificationCompat.Builder builder = new NotificationCompat.Builder( this, default_notification_channel_id ) ;
        builder.setContentTitle( "This is a notification" ) ;
        builder.setContentText(content) ;
        builder.setSmallIcon(R.drawable. ic_launcher_foreground ) ;
        builder.setAutoCancel( true ) ;
        builder.setChannelId( NOTIFICATION_CHANNEL_ID ) ;
        return builder.build() ;
    }

}