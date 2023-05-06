package com.example.fluparrot;

import static com.example.fluparrot.FlutterHelper.flutterHelper;

import com.blueparrott.blueparrottsdk.BPHeadset;
import com.blueparrott.blueparrottsdk.BPHeadsetListener;
import com.blueparrott.blueparrottsdk.BPSdk;
import com.blueparrott.blueparrottsdk.IBPHeadsetListener;
import android.util.Log;

public enum BPSdkInstance implements IBPHeadsetListener {

    bpSdkInstance;
    final String TAG = "::BPSdkInstance::";

    BPHeadset headset;

    @Override
    public void onConnect() {
        Log.d(TAG, "Connected");
        flutterHelper.getBPSdkListener().onConnect(
                (Void) -> Log.i(TAG, "onConnect() listener returned"));
    }

    @Override
    public void onConnectProgress(int progressCode) {
        Log.d(TAG, "onConnectProgress: " + progressCode);
        flutterHelper.getBPSdkListener().onConnectProgress(new Long(progressCode),
                (Void) -> Log.i(TAG, "onConnectProgress() listener returned"));
    }

    @Override
    public void onConnectFailure(int errorCode) {
        flutterHelper.getBPSdkListener().onConnectFailure(new Long(errorCode),
                (Void) -> Log.i(TAG, "onConnectFailure() listener returned"));
        Log.d(TAG, "Connection Failed");
        Log.d(TAG, "Error: " + errorCode);

        if (errorCode == BPHeadsetListener.CONNECT_ERROR_WORK_PROFILE_COULD_NOT_CONNECT) {
            Log.d(TAG, "Android Work Profile bug found - receiver only mode activated");
            Log.d(TAG, "Events will be sent but headset values are not readable.");
        } else {
            Log.d(TAG, "Retry or turn headset off then on");
        }
    }

    @Override
    public void onDisconnect() {
        Log.d(TAG, "Disconnected");
    }

    @Override
    public void onModeUpdate() {
        Log.d(TAG, "Mode Updated: " + headset.getMode());
    }

    @Override
    public void onModeUpdateFailure (int reasonCode) {
        Log.d(TAG, "Mode Update Failed. Reason: " + reasonCode);
    }

    @Override
    public void onButtonDown(int buttonId) {
        Log.d(TAG, "Button Down: " + buttonId);
    }

    @Override
    public void onButtonUp(int buttonId) {
        Log.d(TAG, "Button Up: " + buttonId);
    }

    @Override
    public void onTap(int buttonId) {
        Log.d(TAG, "Tap: " + buttonId);
    }

    @Override
    public void onDoubleTap(int buttonId) {
        Log.d(TAG, "DoubleTap: " + buttonId);
    }

    @Override
    public void onLongPress(int buttonId) {
        Log.d(TAG, "LongPress: " + buttonId);
    }

    @Override
    public void onProximityChange(int status) {
        Log.d(TAG, "Proximity state: " + status);
    }

    @Override
    public void onValuesRead() {
        Log.d(TAG, "onValuesRead");
    }

    @Override
    public void onEnterpriseValuesRead() {
        Log.d(TAG, "onEnterpriseValuesRead");
    }
}
