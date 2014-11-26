package com.attendance.api.helpers;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.WebApplicationException;

import com.attendance.api.pojos.Attendee;

public class AttendeeHelper {

	private static Map<String, Collection<String>> eventAttendees = new HashMap<String, Collection<String>>();
	
	public static Collection<String> getAttendees(String eventId)
	{
		Collection<String> retVal = eventAttendees.get(eventId);
		
		if(retVal == null)
		{
			return Collections.<String> emptyList();
		}
		
		return retVal;
	}
	
	public static void setAttendees(String eventId, Collection<String> attendeeIds)
	{
		eventAttendees.put(eventId, attendeeIds == null ? Collections.<String> emptyList() : attendeeIds);
	}
}
