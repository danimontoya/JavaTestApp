JavaTestApp
===========

Build a native app that shows Jake Wharton Github repositories as list.
 

Clean architecture Modules
--------------------------
* Presentation: MVP.
* Domain
* Data


Assignment - Android Developer
------------------------------
Build a native app that shows Jake Wharton Github repositories as list. Please use pagination (request 15 items per request). When the user reaches the third item from the bottom, request another batch. During the request execution you can show a progress bar as last item in the list. If it's clear that there is no more items available, you should stop doing requests and showing the progress bar.
Please pay attention to code quality, testable and maintainable app architecture. Make sure that the app will be usable (at least partially) without an internet connection. Notify the user if a request was failed, but make sure that the user will be able to see locally stored data.

The data You can take required url like: 
https://api.github.com/users/JakeWharton/repos?page=1&per_page=15

The Constraints
---------------
* Include unit and instrumentation testing.
* Support API 19 and above. Please use target SDK 26.
* Use Material Design principles.
* Use local database (Realm preferred) for storing data and make that data available offline.
* Use your Github account during developing. When you are done, please give us the link to that repo.
