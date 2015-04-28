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

### TGUtil
Utility class for TG library. Gives utility to parse and format date including Time Zone effects.

```
Date parsedDate = TGUtil.parseDate("2005-03-16 7:30:45", "yyyy-MM-dd HH:mm:ss");
Date date = TGUtil.parseDate("2005-03-16 7:30:45", "yyyy-MM-dd HH:mm:ss", TimeZone.getTimeZone("EST"));

String formattedDate = TGUtil.formatDate(date, "MM/dd/yy")
```

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

### Example App
Code base already has example "app" in parallel to library, which shows how to use all TG components.
@TGFramework > app (folder)

### Upcoming...
- TGModel : Application data models which can bridge service output to UI elements.
- TGService : Common gateway to call outer services
- TGRequest : Http Service Request creation
- TGResponse : Http Service Response creation
- TGError & TGException : Error handling & Exception handling

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
