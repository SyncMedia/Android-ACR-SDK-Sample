# Sync Android Demo

### Prerequisites

__Access Keys__

Get `ACCESS_KEY` and `ACCESS_SECRET` from dashboard [https://cms.syncmedia.io/cms/sdk/setup](https://cms.syncmedia.io/cms/sdk/setup)

__App Permissions__

This is used to process audio via microphone for event recognition.

```
* Manifest.permission.RECORD_AUDIO
```

### Installation

__Integration__

* Download SDK Version from [`SDK Versions`](https://code.syncmedia.io/external/android_offline_creative_demo#versions)

* Add downloaded aar file as a `module dependency` named: `android_sync_sdk`

* Add app dependencies to your main module

```
dependencies {
     //sdk dependencies
    implementation project(':android_sync_sdk') //sub module
     
    implementation 'com.getkeepsafe.relinker:relinker:1.4.3'    // allows loading of sdk so lib
    implementation "androidx.room:room-runtime:2.2.6"           // used for storing data offline
    annotationProcessor "androidx.room:room-compiler:2.2.6"
    
	//... other dependencies
}
```

__Start Listener__

```
SMClient smClient = new SMConfig.Builder()
	.setCredentials("ACCESS_KEY", "ACCESS_SECRET")
	.setIdentifier("DEVICE_IDENTIFIER")
	.setContext(ApplicationContext.this)
	.setListener(new SMEventsListener() {
        @Override
        public void onSMStateChanged(@NonNull SMClient client, @SMState String state) {
            Log.d(TAG, "onSMStateChanged: " + state);
        }
    })
    .setLogger(new SMLogger(true))
	.build();
```

__Stop Listener__

```
smClient.release();
```

#### Metadata

`smclient` supports optional metadata. This is a json encoded key-value pair dictionary.
This value can be set anytime as needed by client application.

Below is a sample code to set it.

```
mClient.setMetaData(new SMMetaData.Builder()
                .putNestedMeta("location",
                        new SMMetaData.Builder()
                                .putVal("latitude", "latitude")
                                .putVal("longitude", "longitude"))
                .putNestedMeta("device",
                        new SMMetaData.Builder()
                                .putVal("brand", android.os.Build.MANUFACTURER)
                                .putVal("model", android.os.Build.MODEL)
                                .putVal("product", android.os.Build.PRODUCT)
                                .putVal("os", android.os.Build.VERSION.SDK_INT))
                .build());
```

### Proguard

```
-keep class com.acr.rec.engine.** { *; }
-keepclassmembers class com.acr.*
```

### Versions

#### Current

* [SDK v1.5.1](https://dev-sync-media.s3-ap-southeast-1.amazonaws.com/libs/android-sdk/sdk_v1.5.1.aar)

#### Deprecated

* [SDK v1.4.1](https://dev-sync-media.s3-ap-southeast-1.amazonaws.com/libs/android-sdk/sdk_v1.4.1.aar)
* [SDK v1.3.4](https://dev-sync-media.s3-ap-southeast-1.amazonaws.com/libs/android-sdk/android_sync_sdk_1.3.4.aar)
* [SDK v1.3.0](https://dev-sync-media.s3-ap-southeast-1.amazonaws.com/libs/android-sdk/android_sync_sdk_1.3.0.aar)
* [SDK v1.2.0](https://dev-sync-media.s3-ap-southeast-1.amazonaws.com/libs/android-sdk/android_sync_sdk_1.2.0.aar)
* [SDK v1.0.0](https://dev-sync-media.s3-ap-southeast-1.amazonaws.com/libs/android-sdk/android_sync_sdk_1.0.0.aar)

### Contributors

* Pankaj Soni <pankaj.soni@syncmedia.io>
* Vikas Saxena <vikas.saxena@syncmedia.io>