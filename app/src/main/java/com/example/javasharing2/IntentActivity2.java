package com.example.javasharing2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class IntentActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intent2);

        //Toast.makeText(getApplicationContext(), "We are moved to second Activity",Toast.LENGTH_LONG).show();

        //Toast toast = Toast.makeText(getApplicationContext(),"Hiiiii",Toast.LENGTH_LONG);
        //toast.setGravity(Gravity.BOTTOM| Gravity.CENTER,0,0);
        //toast.setDuration(Toast.LENGTH_LONG);
        //toast.show();

        LayoutInflater inflater = getLayoutInflater();
        View layout = inflater.inflate(R.layout.custom_toast,
                (ViewGroup) findViewById(R.id.toast_layout_root));
        // get the reference of TextView and ImageVIew from inflated layout
        TextView toastTextView = (TextView) layout.findViewById(R.id.toastTextView);
        ImageView toastImageView = (ImageView) layout.findViewById(R.id.toastImageView);
        // set the text in the TextView
        toastTextView.setText("Hello World");
        // set the Image in the ImageView
        toastImageView.setImageResource(R.drawable.ic_launcher_foreground);
        // create a new Toast using context
        Toast toast = new Toast(getApplicationContext());
        toast.setDuration(Toast.LENGTH_LONG); // set the duration for the Toast
        toast.setView(layout); // set the inflated layout
        toast.show(); // display the custom Toast

    }
}