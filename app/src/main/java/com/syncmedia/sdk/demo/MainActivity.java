package com.syncmedia.sdk.demo;

import android.Manifest;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.acr.syncmedia.SMClient;
import com.acr.syncmedia.SMConfig;
import com.acr.utils.SMException;
import com.acr.utils.SMLogger;

import java.util.UUID;

import static android.content.pm.PackageManager.PERMISSION_GRANTED;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    private SMClient mClient;

    private static final int REQUEST_EXTERNAL_STORAGE = 1;

    private static final String ACCESS_KEY = "test_project";
    private static final String ACCESS_SECRET = "e2c0e8e2-23b1-4477-8a2b-7d7e16fd95d8";

    private static final boolean AUTO_START = Boolean.parseBoolean("false");

    private static String[] PERMISSIONS_STORAGE = {
            Manifest.permission.RECORD_AUDIO
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        findViewById(R.id.start).setOnClickListener(v -> checkAndStart());
        findViewById(R.id.cancel).setOnClickListener(v -> cancel());
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        for (int result : grantResults) {
            if (result != PERMISSION_GRANTED) {
                return;
            }
        }

        startClient();
    }

    private void checkAndStart() {
        ActivityCompat.requestPermissions(this, PERMISSIONS_STORAGE,
                REQUEST_EXTERNAL_STORAGE);

    }

    private void startClient() {
        if (this.mClient != null) {
            return;
        }

        try {
            this.mClient =
                    new SMConfig.Builder()
                            .setCredentials(ACCESS_KEY, ACCESS_SECRET)
                            .setIdentifier(UUID.randomUUID().toString())
                            .setContext(this)
                            .build();
        } catch (SMException e) {
            SMLogger.e(TAG, "startClient", e);
        }
    }

    protected void cancel() {
        if (this.mClient != null) {
            this.mClient.release();
            this.mClient = null;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.e("MainActivity", "release");
        cancel();
    }
}