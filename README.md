google-calendar-gadget
======================

A Google calendar sidebar gadget in Angular.js with a Java Jersey RESTful back end.

The Back End
======================
I used Apache Maven 3.0.4 (local), Java 1.7.0_55 x64 (local), and Tomcat 8.0.9 (docker container)

<h3>Spinning up Tomcat in a Docker Container</h3>
If you wish to run tomcat inside docker like I did, run the following:

```docker run -d -i -t -v=/localpath/khs-google-calendar-gadget/attendance-api/deploy:/opt/tomcat/webapps -v=/localpath/khs-google-calendar-gadget/attendance-api/logs:/opt/tomcat/logs -p 8080:8080 imageID```

Where imageID is the ID of the docker image with tomcat installed.  I used "consol/tomcat-8.0" from dockerhub: https://registry.hub.docker.com/u/consol/tomcat-8.0/

This should create a container running tomcat with bound volumes between its webapps and logs directories and those on your local machine, respectively.  The Maven command in the next section can then be used to deploy the web service (WAR file) to the webapps directory.

<h3>Build and deploy the web service WAR</h3>
In the attendance-api directory (where the pom resides), run the following:

```sudo mvn clean compile package```

This should place a attendance-api-x.war in your deploy folder

<h3>Test it out</h3>
Using Postman or some other HTTP client tool, you should now be able to perform a successful GET request to a URL such as:

http://localhost:8080/attendance-api-0.0.1-SNAPSHOT/event/7203/info

All requests/responses are in JSON, and supports CORS by allowing ALL origins ("*") via the response header access-control-allow-origin.  The web service's state is not persisted to a DB and is only preserved in memory.  Change the mock data (such as hard-coded event ID's, names, etc) as needed.

Supported calls are:
* GET http://localhost:8080/attendance-api-0.0.1-SNAPSHOT/event/{eventId}/roster
* GET http://localhost:8080/attendance-api-0.0.1-SNAPSHOT/event/{eventId}/info
* GET http://localhost:8080/attendance-api-0.0.1-SNAPSHOT/event/{eventId}/attendees
* PUT http://localhost:8080/attendance-api-0.0.1-SNAPSHOT/event/{eventId}/attendees

The GET and PUT attendees calls give/take only the ID's of the attendees, like so:

[
    "6327",
    "1234"
]

The Front
======================
Transfer the contents of "gadget" to a publicly available web site.  An intranet or local web URL will not suffice.

After logging in to Google, load the gadget like so:

https://www.google.com/calendar/render?gadgeturl=http://206.190.137.149/gadgets/eventGadget.xml

Google gadgets are cache-happy.  In order to see changes to the gadget after you publish them to your web server, you must remove the gadget (via the X in the upper right) and reload it, each time with a unique query param to invalidate the cache.  Like so:

https://www.google.com/calendar/render?gadgeturl=http://206.190.137.149/gadgets/eventGadget.xml&foo=bar

