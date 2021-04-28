package com.hieptran.denpin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.hardware.Camera;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Button btnControl;
    FlashLightDevice flashLightDevice;
    boolean isTurnOnFlashlight = false;
    FlashlightProvider flashlightProvider;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mappingView();

        btnControl.setOnClickListener(v -> {
            if (flashLightDevice.isFlashlight) {
                if (isTurnOnFlashlight) {
                    flashlightProvider.turnFlashlightOff();
                    isTurnOnFlashlight = false;
                } else {
                    flashlightProvider.turnFlashlightOn();
                    isTurnOnFlashlight = true;
                }
            } else {
                Toast.makeText(getApplicationContext(), "Không hỡ trợ đèn pin", Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void mappingView() {
        btnControl = findViewById(R.id.btnControl);
        flashLightDevice = new FlashLightDevice(getApplicationContext());

        flashlightProvider = new FlashlightProvider(getApplicationContext());
    }
}