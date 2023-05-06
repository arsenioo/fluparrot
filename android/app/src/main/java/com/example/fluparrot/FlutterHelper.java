package com.example.fluparrot;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.util.Log;

import androidx.annotation.NonNull;

import io.flutter.embedding.android.FlutterActivity;
import io.flutter.embedding.android.FlutterActivityLaunchConfigs;
import io.flutter.embedding.engine.FlutterEngine;
import io.flutter.embedding.engine.FlutterEngineCache;
import io.flutter.plugin.common.BinaryMessenger;

import static com.example.fluparrot.BPChannel.IBPSdkApi;
import static com.example.fluparrot.FlutterHelper.BPSdkApi.bpSdkApi;
import static com.example.fluparrot.BPSdkInstance.bpSdkInstance;

public enum FlutterHelper {
    flutterHelper;
    private BPChannel.IBPSdkListener bpSdkListener;

    void initBPChannel(BinaryMessenger binaryMessenger) {
        bpSdkListener = new BPChannel.IBPSdkListener(binaryMessenger);
        IBPSdkApi.setup(binaryMessenger, bpSdkApi);
    }

    BPChannel.IBPSdkListener getBPSdkListener() {
        return bpSdkListener;
    }

    enum BPSdkApi implements IBPSdkApi {
        bpSdkApi;
        static final String TAG = "::BPSdkApi::";

        @Override
        public void connect(@NonNull Long mode) {
            try {
                bpSdkInstance.headset.connect(mode.intValue());
            } catch (Throwable e) {
                Log.e(TAG, e.toString());
            }
        }
    }
}
