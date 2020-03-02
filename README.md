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
     
    implementation 'com.getkeepsafe.relinker:relinker:1.3.1'
    implementation "androidx.room:room-runtime:2.2.4"
    annotationProcessor "androidx.room:room-compiler:2.2.4"
    
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

### Versions

#### Current

* [SDK v1.4.1](https://dev-sync-media.s3-ap-southeast-1.amazonaws.com/libs/android-sdk/sdk_1.4.1.aar)

#### Deprecated

* [SDK v1.3.4](https://dev-sync-media.s3-ap-southeast-1.amazonaws.com/libs/android_sync_sdk_1.3.4.aar)
* [SDK v1.3.0](https://dev-sync-media.s3-ap-southeast-1.amazonaws.com/libs/android_sync_sdk_1.3.0.aar)
* [SDK v1.2.0](https://dev-sync-media.s3-ap-southeast-1.amazonaws.com/libs/android_sync_sdk_1.2.0.aar)
* [SDK v1.0.0](https://dev-sync-media.s3-ap-southeast-1.amazonaws.com/libs/android_sync_sdk_1.0.0.aar)

### Contributors

* Pankaj Soni <pankaj.soni@syncmedia.io>
* Vikas Saxena <vikas.saxena@syncmedia.io>