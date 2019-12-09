package com.syncmedia.acr.demo;

import android.Manifest;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.acr.syncmedia.SMClient;
import com.acr.syncmedia.SMConfig;
import com.acr.syncmedia.SMEventsListener;
import com.acr.utils.SMException;
import com.syncmedia.audiodemo.R;
import com.testfairy.TestFairy;

import java.util.UUID;

import static android.content.pm.PackageManager.PERMISSION_GRANTED;

public class MainActivity extends AppCompatActivity implements SMEventsListener {

    private static final String TAG = "MainActivity";

    private SMClient mClient;

    private static final int REQUEST_EXTERNAL_STORAGE = 1;

    private static final String ACCESS_KEY = "test_project";
    private static final String ACCESS_SECRET = "1a4f7ad7-0f79-4544-bcd7-d3409ca1f3b3";

    private static String[] PERMISSIONS_STORAGE = {
            Manifest.permission.RECORD_AUDIO,
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
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
                            .setListener(this)
                            .setAutoStart(true)
                            .makeClient();
        } catch (SMException e) {
            TestFairy.logThrowable(e);
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

    @Override
    public void onSMReady(@NonNull SMClient client) {
        Log.d(TAG, "onSMReady: client: start: " + client.getState());
    }
}