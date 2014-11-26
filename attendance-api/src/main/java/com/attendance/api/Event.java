package com.attendance.api;

import java.util.Collection;

import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.attendance.api.helpers.AttendeeHelper;
import com.attendance.api.helpers.InfoHelper;
import com.attendance.api.helpers.RosterHelper;
import com.attendance.api.pojos.Attendee;
import com.attendance.api.pojos.Info;

@Path("/event")
public class Event {

	@GET
	@Path("{eventId}/roster")
	@Produces(MediaType.APPLICATION_JSON)
	public Collection<Attendee> retrieveRoster(@PathParam("eventId") String eventId, @Context HttpServletResponse response) {
		validateEventId(eventId);	
	    return RosterHelper.getRoster(eventId);
	}

	@GET
	@Path("{eventId}/info")
	@Produces(MediaType.APPLICATION_JSON)	
	public Info retrieveInfo(@PathParam("eventId") String eventId, @Context HttpServletResponse response) {
		validateEventId(eventId);
		return InfoHelper.getInfo(eventId);
	}

	@GET
	@Path("{eventId}/attendees")
	@Produces(MediaType.APPLICATION_JSON)
	public Collection<String> retrieveAttendees(@PathParam("eventId") String eventId, @Context HttpServletResponse response) {
		validateEventId(eventId);
		return AttendeeHelper.getAttendees(eventId);
	}

	@PUT
	@Path("{eventId}/attendees")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response updateAttendees(Collection<String> attendees, @PathParam("eventId") String eventId, @Context HttpServletResponse response) {
		validateEventId(eventId);
		AttendeeHelper.setAttendees(eventId, attendees);
		return Response.ok().build();
	}

	private void validateEventId(String eventId)
	{
		if (eventId == null || eventId.length() == 0) {
			throw new WebApplicationException(400);
		}
	}
}