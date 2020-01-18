package com.example.smsproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText num,msg;
    Button send;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        num=(EditText)findViewById(R.id.editText);
        msg=(EditText)findViewById(R.id.editText2);

    }
    public void btn_click(View v)
    {
        int permissioncheck= ContextCompat.checkSelfPermission(this, Manifest.permission.SEND_SMS);
        if(permissioncheck== PackageManager.PERMISSION_GRANTED){
            myMessage();
        }
        else{
            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.SEND_SMS},0);

        }
    }
    public void myMessage()
    {
        String ph=num.getText().toString();
        String message=msg.getText().toString();
        SmsManager smsManager=SmsManager.getDefault();
        smsManager.sendTextMessage(ph,null,message,null,null);
        Toast.makeText(this,"Message Sent", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        switch(requestCode){
            case 0:
                if(grantResults.length>=0 && grantResults[0]==PackageManager.PERMISSION_GRANTED)
                {
                    myMessage();
                }
                else
                {
                    Toast.makeText(this,"You don't have required permission..",Toast.LENGTH_LONG).show();
                }
        }
    }
}
