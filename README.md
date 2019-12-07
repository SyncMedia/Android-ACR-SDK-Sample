# Sync Android Demo

### Prerequisites

__Access Keys__

Get `ACCESS_KEY` and `ACCESS_SECRET` from dashboard.

__App Permissions__

This is used to process audio via microphone for event recognition.

```
* Manifest.permission.RECORD_AUDIO
```

These store events in local database till they are synced to our server.

```
* Manifest.permission.READ_EXTERNAL_STORAGE
* Manifest.permission.WRITE_EXTERNAL_STORAGE
```


### Installation

__Version__

VERSION :: `1.0.1`

__Download lib__


```
wget https://dev-sync-media.s3-ap-southeast-1.amazonaws.com/libs/android_sync_sdk_$VERSION.aar
```

Add `android_sync_sdk_$VERSION.aar` to `libs` folder

```
dependencies {
    implementation fileTree(dir: 'libs', include: ['*.jar', '*.aar'])
	//... other dependencies
}
```

__Create Listener__

```
SMEventsListener listener = new SMEventsListener() {
    @Override
    public void onSMReady(@NonNull SMClient client) {
         Log.d(TAG, "onSMReady: client: start: " + client.getState());
    }
};
```

__Create config__

```
SMClient smClient = new SMConfig.Builder()
	.setCredentials("ACCESS_KEY", "ACCESS_SECRET")
	.setIdentifier("DEVICE_IDENTIFIER")
	.setContext(this)
	.setListener(SMEventsListener)
	.setAutoStart(true)
	.makeClient();
```

__Release Client__

```
smClient.release();
```

### Contributors

* Pankaj Soni <pankajsoni@syncmedia.io>

