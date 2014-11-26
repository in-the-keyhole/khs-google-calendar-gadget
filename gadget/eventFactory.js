eventApp.factory('eventFactory', ['$q', '$http', function($q, $http) {
    return {
        getEventInformation: function(eventId) {
            var baseUrl = 'http://localhost:8080/attendance-api-0.0.1-SNAPSHOT/event/' + eventId;

            // Typically, when using promises you see something like promise1().then(promise2).then(promise3)
            // But, then() is "synchronous" and since the three REST calls below don't depend on data from the others,
            // they can all be run asynchronously to reduce overall latency.  The all() function lets us do that.
            return $q.all([
                $http.get(baseUrl + '/info'),
                $http.get(baseUrl + '/roster'),
                $http.get(baseUrl + '/attendees')
            ]).then(function(responses) {
                var infoResponse = responses[0];
                var rosterResponse = responses[1];
                var attendeesResponse = responses[2];

                // When an $http promise is resolved, it's parameter is an object with data, status, headers, and config properties
                return {
                    eventName: infoResponse.data.name,
                    eventInstructor: infoResponse.data.instructor,
                    roster: rosterResponse.data,
                    attendees: attendeesResponse.data
                };
            });
        }
    };
}]);