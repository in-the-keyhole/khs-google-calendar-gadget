eventApp.controller('EventController', ['$scope', '$http', '$timeout', 'eventFactory', function($scope, $http, $timeout, eventFactory) {

 initGoogleCalendar = function() {
   //You can also use this to clear the title after the gadget is loaded.  Setting it to "" from the get-go makes it titleless to the user, which is bad.
   gadgets.window.setTitle('Check-in Service');
   
   //this is the event magic
   google.calendar.read.subscribeToEvents($scope.subscribeEventCallback);

   //because 200px isn't enough
   gadgets.window.adjustHeight(4000);
 }

 initEventApp = function() {
   //reset, hide everything
   $scope.errorMessage = "";
   $scope.successMessage = "";
   $scope.attendeesClean = true;
   $scope.showEvent = false;
   $scope.eventId = null;
 }

 function getEventInformation() {
   eventFactory.getEventInformation($scope.eventId).then(function(eventInfo) {
     $scope.eventName = eventInfo.eventName;
     $scope.eventInstructor = eventInfo.eventInstructor;
     $scope.attendees = eventInfo.roster;

     $scope.attendees.forEach(function(attendee) {
       attendee.isChecked = (eventInfo.attendees.indexOf(attendee.id) > -1) ? true : false;
     });

     $scope.showEvent = true;
     $scope.attendeesClean = true;
   }).catch(function(error) { // the error object probably has useful debugging info
     $scope.errorMessage = "The event information could not be loaded";
     $scope.successMessage = "";
   });
 }

 $scope.markAttendeesDirty = function() {
   $scope.attendeesClean = false;
 }


$scope.checkInToEvent = function() {

   //only gather checked attendees
   var checkedAttendees = $scope.attendees.filter(
     function (element) {
       return element.isChecked;
     }
     );
   
   //create a new JSON array with just id's
   //delete will not work because it will remove those properties from the original objects (which are NOT deep cloned)
   var checkedAttendeeIds = [];
   for (var i in checkedAttendees) {
     checkedAttendeeIds[i] = checkedAttendees[i].id;
   }

   $http.put('http://localhost:8080/attendance-api-0.0.1-SNAPSHOT/event/'+$scope.eventId+'/attendees', checkedAttendeeIds).
   success(function(data, status, headers, config) {

     $scope.errorMessage = "";
     $scope.successMessage = "Attendees checked in!";

     $timeout(function() {
       $scope.successMessage = "";
     }, 2000);
   }).
   error(function(data, status, headers, config) {
     $scope.errorMessage = "An error occurred while checking in attendees";
   });
 }

 $scope.subscribeEventCallback = function(calendarEvent) {
   initEventApp();

   if (calendarEvent && calendarEvent['id']) {
     
     console.log("EVENT ID: "+calendarEvent['id']);
     
     //TODO use the real ID, mocked for now
     $scope.eventId = 7203;

     getEventInformation($scope.eventId);
   }
   else
   {
     $timeout(function () {
       $scope.showEvent = false;
     }, 100);
   }
 }

 initGoogleCalendar();
 initEventApp();

}]);
