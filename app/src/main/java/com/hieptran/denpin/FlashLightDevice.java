package com.hieptran.denpin;

import android.content.Context;
import android.content.pm.PackageManager;

public class FlashLightDevice {
    boolean isFlashlight;

    public FlashLightDevice(boolean isFlashlight) {
        this.isFlashlight = isFlashlight;
    }

    public FlashLightDevice(Context context) {
        this.isFlashlight = context.getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA_FLASH);
    }

    public boolean isFlashlight() {
        return isFlashlight;
    }

    public void setFlashlight(boolean flashlight) {
        isFlashlight = flashlight;
    }
}
