# TGFramework-Android
Purpose of TGFramework is to provide developer friendly library, that can help to improve feature development rather focusing on common structure creation during every mobile app development. 

## How to use library?
- Copy [TGFramework.jar](Archives/Library-Jar/TGFramework.jar) into your project's lib folder and start using it !!!
- For documentation of library classes, please refer [Java Doc](Archives/Java-Doc).

## Areas where TGFramework may help

### TGObject
Base class for TGFramework library, having basic supportive methods.

Make sure to have current class inheriting any of the TG Class. (TGObject, TGActivity, TGService based on which layer you are in.) -OR- Use TGUtil instance directly without changing your existing framework.

Avoid redudant null checks
```
Instead of : if(name!=null && name.size()>0) {...}
Just use   : if(hasValue(name)){...}
```
Easy to become null safe
```
Instead of : text.setText(name!=null ? name : "");
Just use   : text.setText(nullSafeString(name));
```

### TGSession
Single Session instance in memory to hold values in memory as Key-Value pair. No need to inherit or initialize anything.

##### Where to use:
- Whenever data is common accross multiple activities.
- Managing data in local storage option (like in local database, shared preference or in disk), when actually data become invalid after app close.

* Between activities: 
Just straight put things in one activity and use it in another one.
```
Activity 1  : TGSession.instance().put("Designation","Developer");
Activity 2  : String designation = TGSession.instance().get("Designation");
```
Thinking to manage piled up objects in session?
```
TGSession.instance().remove("Designation");
```
* Between service and activity layer: 
Just put in service and have it in Activity. (Easy to put any object.)
```
Service  : TGSession.instance().put("Person",Person);
Activity : Person person = TGSession.instance().get("Person");
``` 

* Common UI rendering place:
Create all the UI pages by binding TGSession attribtues with it. And behind the scene use your own way of using various data sources or services to fill data synchronously or asynchronously in TGSession.

##### TGSessionListener:
Just make any class as Session listener by implementing TGSessionListener. And get the ability to perfrom any post action when any key has been put in session, removed from session or even session become invalidated.

##### Where NOT to use TGSession:
TGSession holds data in app memory (RAM) so whenever it required data to be stored beyond user's app usage. Please consider to store it in local database or shared preference. One can reload in TGSession again when app starts again.

### TGApplication
Application class which holds light weight Application Context reference. Android framework manages Application as Singleton Instance internally. Just set this class or your own Application class which extends TGApplication.

Just provide TGApplication (or your own custom extended class) as Application class in AndroidManifest.xml
```
<application 
     android:name="com.techgrains.application.TGApplication"
```

### TGActivity
Base Activity of TGFramework which gives handy implementation like Alert Dialog, Progress Dialog etc.

Extend TGActivity from all android activity classes.
```
public MyLoginActivity extends TGActivity
```

###### TGIAlertDialog
Alert dialog interface to manage custom Alert Dialog generation from any activity which extends TGActivity. It has option to implement custom callback methods based on positive, negative and neutral user's button click actions.

```
showAlertDialog(new TGAlertDialog("Title", "Hello World!", "Okay"));
```

###### TGIProgressDialog
Progress dialog interface to manage custom Progress Dialog generation from any activity which extends TGActivity. 

```
showProgressDialog(new TGProgressDialog("Title", "Message", R.drawable.spinner));
...
isShowingProgressDialog();
...
dismissProgressDialog();
```

###### Toast Message
Show Toast message with boolean selection of short or long duration

```
showToast("Email has been sent!");       // For short lived toast message
showToast("Email has been sent!",false); // For long lived toast message
```

### TGRequest & TGResponse
TGFramework gives mechanism to use HTTP Request & Response like an ORM which facilitates various response conversion inbuilt. (Like, JSon response to object or Image response to Bitmap). Internally it uses Google’s Volley framework.

#### TGStringRequest
Provides http response as is as String along with other params in TGResponse. (Like Status Code, Headers, Response, Headers, Network Time etc.)
It uses TGIResponseListener for callback methods.

#### TGJsonRequest
Encapsulates network calls and conversion of json response to custom T (provided) object. It internally uses GSon library for converting string into Java objects. It supports SerializedName expression to map Json element name to java object's attribute name. It uses TGIResponseListener for callback methods.

Reference from Example app
```
public class CityListRequest extends TGJsonRequest<ApiResponse<CityList>>
``` 

#### TGImageRequest
Uses separate request queue to avoid network call sharing with TGJsonRequest and TGStringRequest. Response of the image is provided as common TGResponse object having attribute name “bitmap”. It uses same TGIResponseListener for callback methods.
```
public class HomeBackgroundImageRequest<T extends TGResponse> extends TGImageRequest
``` 

#### TGImageLoader (Alternate of TGImageRequest)
Uses ImageLoader with Lru Cache to give option to keep images in memory. Recommended approach when it requires to keep images in memory. (Like, scrollable list of results, list of image views as slides)
```
TGService.loadImage(IMAGE_URL, imageView);
TGService.loadImage(IMAGE_URL, imageView. R.drawable.default_image, R.drawable.error_image);
```

#### TGIResponseListener
Callback methods either on Main thread or on Background thread upon successful/error response.
```
void onSuccessMainThread(T response);
void onSuccessBackgroundThread(T response);
void onError(TGResponse response);
```

### TGService
Service wrapper class to facilitate all the service/network related calls including String request, Json request, Image request and image loader.
```
public static void performJsonRequest (TGJsonRequest<?> request)
public static void performStringRequest (TGStringRequest<?> request)
public static void performImageRequest (TGImageRequest request)
public static void cancelRequest (TGRequest request)
public static void cancelRequestByTag (Object tag)
public static void startRequests()
public static void stopRequests()
public static void loadImage (String imageUrl, ImageView imageView)
public static void loadImage (String imageUrl, ImageView imageView, int defaultImageId, int errorImageId)
```

### TGUtil
Utility methods which are independent of any Android specific API.
```
// Parse Date from String
Date date = TGUtil.parseDate("2005-03-16 7:30:45", "yyyy-MM-dd HH:mm:ss");
Date date = TGUtil.parseDate("2005-03-16 7:30:45", "yyyy-MM-dd HH:mm:ss", TimeZone.getTimeZone("EST"));

// Format Date into String
String dateString = TGUtil.formatDate(date, "yyyy-MM-dd");
String dateString = TGUtil.formatDate(date, "yyyy-MM-dd HH:mm:ss.SSSZ", TimeZone.getTimeZone("GMT"));

// Current Date and/or Time in String format
String currentDateTime = TGUtil.currentDateTime(); // In format of "yyyy-MM-dd'T'HH:mm:ss"
String currentDate = TGUtil.currentDate(); // In format of "yyyy-MM-dd"
String currentTime = TGUtil.currentTime(); // In format of "HH:mm:ss"

// Convert JSon string into Object
Employee employee = (Employee) TGUtil.fromJson(jsonString, new TypeToken<Employee>(){}.getType());

// Convert Object into JSon string or even Map object of key value pair by attributes
Employee employee = new Employee(1, "Matt");
Sting employeeString = TGUtil.toJson(employee); // JSon representation of Employee object
Map<String, Object> employeeMap = TGUtil.convertToMap(employee); // Key-Value pair of each attribute of employee object

// Read File
String fileString = TGUtil.readFile(String filename);

// Random UUID
String uuid = TGUtil.getRandomUUID();
```

### TGAndroidUtil
Utility methods which are depending upon Android API.
```
// Check Internet network available
boolean isNetworkAvailable = TGAndroidUtil.isNetworkAvailable();

// Read file from asset
String fileString = TGAndroidUtil.readFileFromAssets(String file);

// Serialize & Deserialize any object to & from String
String serialized = TGAndroidUtil.serialize(new Employee("Vishal","Developer"));
Employee deserialized = (Employee) TGAndroidUtil.deserialize(serialized);

// Gets display metric to decide scheme : sdpi, mdpi, hdpi, xhdpi, xxhdpi, xxxhdpi
int scheme = TGAndroidUtil.getDisplayMetric();

// Android logs : Supports VERBOSE, DEBUG, INFO, WARN, ERROR
TGAndroidUtil.log("Started Main Activity"); // Defaults to Log.DEBUG, Tag is "TGFramework"
TGAndroidUtil.log(Log.ERROR, "Failed to start Main Activity");
TGAndroidUtil.log(Log.INFO, "My Own Log Tag", "Something in Main Activity");

// Conversion between Bitmap to-from Byte array
byte[] bytes = TGAndroidUtil.convertBitmapToByte(bitmap);
Bitmap bitmap = TGAndroidUtil.convertByteArrayToBitmap(bytes);

// IMEI Code
String imei = TGAndroidUtil.getIMEI();

// Check for tablet
boolean isTablet = TGAndroidUtil.isTablet();
```

### TGSharedPreferences
Wrapper class of android.content.SharedPreferences interface.
```
TGSharedPreferences.editor().putString("username","Vishal");
String username = TGSharedPreferences.instance().getString("username");
```
Extended Shared Preference to accomodate to put Object into the shared preference and get it back as is.
```
TGSharedPreferences.putObject("employee",new User("Vishal"));
User user = TGSharedPreferences.getObject("username");
```

### TGLocationManager
Custom location manager class which uses android.location.LocationManager to capture current location with accuracy and timeout settings.
```
TGLocationListener listener = new TGLocationListener() {
    @Override
    public void onAccurateLocation(Location location) {...}

    @Override
    public void onStart() {...}
    
    @Override
    public void onTimeOut() {...}

    @Override
    public void onLocationChanged(Location location) {...}

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {...}

    @Override
    public void onProviderEnabled(String provider) {...}

    @Override
    public void onProviderDisabled(String provider) {...}
};

float targetAccuracy = 5.0f;
int timeout = 60000; // 60 secs
TGLocationManager tgLocationManager = new TGLocationManager(listener, targetAccuracy, timeout);
```
### TGError & TGException
Entire TGFrameworks handles and throws exception in common TGException. Also uses TGError object to transfer error information across the board. Every TGException holds TGError object as exception code and message.

### Example App
Code base already has example "app" in parallel to library, which shows how to use all TG components.
@TGFramework > app (folder)

### Anything to say?
Any suggestions or recommendations are most welcome.
> Please email me on vishal@techgrains.com with subject "TGFramework" for easier reference and to have in proper mailbox folder. Thanks in advance!

## License
#### Copyright 2015 Techgrains Technologies

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
