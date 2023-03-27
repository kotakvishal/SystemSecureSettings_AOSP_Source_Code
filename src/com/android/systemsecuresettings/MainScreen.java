package com.example.systemsecuresettings;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.Toast;


import java.lang.reflect.Method;

public class MainScreen extends AppCompatActivity {

    Switch wifiAlwaysOn, swtchAUP, swtchSI,pwrBtn,usbDbgng;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_screen);
        initViews();
        /**
         * Change Wifi Always On : Property on user preference
         */
        wifiAlwaysOn.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean wifi_always_on) {
                wifiAlwaysOn(wifi_always_on);
                Toast.makeText(MainScreen.this,"WIFI Always On:"+wifi_always_on,Toast.LENGTH_SHORT).show();
            }
        });

        /**
         * Change Usb Debugging : Property on user preference
         */
        usbDbgng.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean usb_debugging) {
                usbDebugging(usb_debugging);
                Toast.makeText(MainScreen.this,"USB Debugging:"+usb_debugging,Toast.LENGTH_SHORT).show();
            }
        });

        /**
         * Change Power Button Property On: Property on user preference
         */
        pwrBtn.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean power_btn) {
                powerButton(power_btn);
                Toast.makeText(MainScreen.this,"Power Button On:"+power_btn,Toast.LENGTH_SHORT).show();
            }
        });

    }

    /**
     * Takes a boolean value from user.
     * Sets power button property
     * in framework base through reflection api.
     * @param power_btn
     */
    private void powerButton(boolean power_btn) {
        try {
            Class<?> c = Class.forName("android.os.SystemProperties");
            Method set = c.getMethod("set", String.class);
            set.invoke(c, "persist.sys.sss.power_btn");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Takes a boolean value from user.
     * Sets usb debugging property
     * in framework base through reflection api.
     * @param usb_debugging
     */
    private void usbDebugging(boolean usb_debugging) {
        try {
            Class<?> c = Class.forName("android.os.SystemProperties");
            Method set = c.getMethod("set", String.class);
            set.invoke(c, "persist.sys.sss.usb_debugging");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Takes a boolean value from user.
     * Sets wifi always on property
     * in framework base through reflection api.
     * @param wifi_always_on
     */
    private void wifiAlwaysOn(boolean wifi_always_on) {
            try {
                Class<?> c = Class.forName("android.os.SystemProperties");
                Method set = c.getMethod("set", String.class);
                set.invoke(c, "persist.sys.sss.wifi_always_on");

            } catch (Exception e) {
                e.printStackTrace();
            }
    }


    /**
     * Initialize views and attach the XML views to the Java file.
     */
    public void initViews(){
        swtchAUP=findViewById(R.id.auto_grant_all_permission_toggle);
        swtchSI=findViewById(R.id.silent_installer_toggle);
        wifiAlwaysOn=findViewById(R.id.wifi_toggle);
        pwrBtn=findViewById(R.id.power_button_toggle);
        usbDbgng=findViewById(R.id.usb_debugging_toggle);
    }

}