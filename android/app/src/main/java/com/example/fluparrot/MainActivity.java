package com.example.fluparrot;

import static com.example.fluparrot.FlutterHelper.flutterHelper;
import static com.example.fluparrot.BPSdkInstance.bpSdkInstance;

import io.flutter.embedding.android.FlutterActivity;
import io.flutter.embedding.engine.FlutterEngine;
import android.os.Bundle;
import android.util.Log;
import com.blueparrott.blueparrottsdk.BPHeadset;
import com.blueparrott.blueparrottsdk.BPHeadsetListener;
import com.blueparrott.blueparrottsdk.BPSdk;

public class MainActivity extends FlutterActivity {

    static final String TAG = "::fluparrotActivity::";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bpSdkInstance.headset = BPSdk.getBPHeadset(this);
        bpSdkInstance.headset.addListener(bpSdkInstance);
        Log.i(TAG, "BPHeadset initialized");
    }

    @Override
    public void configureFlutterEngine(FlutterEngine flutterEngine) {
        super.configureFlutterEngine(flutterEngine);
        flutterHelper.initBPChannel(flutterEngine.getDartExecutor().getBinaryMessenger());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (bpSdkInstance.headset != null) {
            bpSdkInstance.headset.disconnect();
            Log.i(TAG, "BPHeadset disconnected");
        }
    }
}
