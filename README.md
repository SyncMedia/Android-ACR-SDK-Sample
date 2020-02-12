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

__Download lib__


Check [`Versions`](https://code.syncmedia.io/external/android_offline_creative_demo#versions) for current version.

```
wget https://dev-sync-media.s3-ap-southeast-1.amazonaws.com/libs/android_sync_sdk_$VERSION.aar
```

Add `android_sync_sdk_$VERSION.aar` as a `module dependency` named: `android_sync_sdk`

Add app dependencies to your main module

```
dependencies {
     //sdk dependencies
    implementation project(':android_sync_sdk') //sub module
     
    implementation 'com.getkeepsafe.relinker:relinker:1.3.1'
    implementation "androidx.room:room-runtime:2.2.3"
    annotationProcessor "androidx.room:room-compiler:2.2.3"
    
	//... other dependencies
}
```

__Start Listener__

```
SMClient smClient = new SMConfig.Builder()
	.setCredentials("ACCESS_KEY", "ACCESS_SECRET")
	.setIdentifier("DEVICE_IDENTIFIER")
	.setContext(this)
	.build();
```

__Stop Listener__

```
smClient.release();
```

### Versions

#### Current

* [android\_sync\_sdk\_1.3.0.aar](https://dev-sync-media.s3-ap-southeast-1.amazonaws.com/libs/android_sync_sdk_1.3.0.aar)

#### Previous

* [android\_sync\_sdk\_1.2.0.aar](https://dev-sync-media.s3-ap-southeast-1.amazonaws.com/libs/android_sync_sdk_1.2.0.aar)
* [android\_sync\_sdk\_1.0.0.aar](https://dev-sync-media.s3-ap-southeast-1.amazonaws.com/libs/android_sync_sdk_1.0.0.aar)

### Contributors

* Pankaj Soni <pankaj.soni@syncmedia.io>
* Vikas Saxena <vikas.saxena@syncmedia.io>