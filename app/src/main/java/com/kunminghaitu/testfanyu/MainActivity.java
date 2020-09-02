package com.kunminghaitu.testfanyu;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.uniubi.uface.ether.outdevice.devicemanager.UfaceDevice;
import com.uniubi.uface.ether.outdevice.serialport.EtherMcuManager;
import com.uniubi.uface.ether.outdevice.serialport.controller.DoorController;
import com.uniubi.uface.ether.outdevice.serialport.controller.LightController;

public class MainActivity extends AppCompatActivity {

    private UfaceDevice device;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        EtherMcuManager.getInstance().globalAutoInitBySN();

        setContentView(R.layout.activity_main);

        Spinner spinner = findViewById(R.id.spinner);
        spinner.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.spinneritem)));
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                device = UfaceDevice.values()[i];
                Log.d("MainActivity", device.name() + " == " + i);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        Button button2 = findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean b = LightController.openWhiteLED(UfaceDevice.uface2);
                if (b) {
                    Toast.makeText(MainActivity.this, "开灯成功！", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(MainActivity.this, "开灯失败！", Toast.LENGTH_LONG).show();
                }

            }
        });
        Button button3 = findViewById(R.id.button3);
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean b = DoorController.openDoorRelay(UfaceDevice.uface2, 500);
                if (b) {
                    Toast.makeText(MainActivity.this, "开门成功！", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(MainActivity.this, "开门失败！", Toast.LENGTH_LONG).show();
                }
            }
        });
        Button button4 = findViewById(R.id.button4);
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean b = DoorController.openDoorSerialport(5, "", 0);
                if (b) {
                    Toast.makeText(MainActivity.this, "开门成功！", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(MainActivity.this, "开门失败！", Toast.LENGTH_LONG).show();
                }
            }
        });
        Button button5 = findViewById(R.id.button5);
        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean b = LightController.closeGreenLED(UfaceDevice.uface2);
                if (b) {
                    Toast.makeText(MainActivity.this, "关灯成功！", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(MainActivity.this, "关灯失败！", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}