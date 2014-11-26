package com.attendance.api.helpers;

import java.util.ArrayList;
import java.util.Collection;

import javax.ws.rs.WebApplicationException;

import com.attendance.api.pojos.Attendee;

public class RosterHelper {

	public static Collection<Attendee> getRoster(String eventId) {
		
		Collection<Attendee> retVal = new ArrayList<Attendee>();
		
		switch (eventId) {
		case "7203":
			retVal.add(getKenWilliams());
			retVal.add(getJohnSmith());
			retVal.add(getBeckyMalone());
			break;
		case "9333":
			retVal.add(getJohnSmith());
			retVal.add(getJoAnnDavis());
			retVal.add(getSadieMcDonald());
			retVal.add(getRobertClarkson());
			retVal.add(getWandaJackson());
			break;
		case "0061":
			// intentionally empty
			break;
		case "2298":
			retVal.add(getBethWilliams());
			retVal.add(getKenWilliams());
			retVal.add(getSusanJones());
			break;
		case "5736":
			retVal.add(getJohnSmith());
			retVal.add(getBeckyMalone());
			break;
		case "2301":
			retVal.add(getSadieMcDonald());
			break;
		default:
			throw new WebApplicationException(404);
		}
		
		return retVal;
	}

	private static Attendee getJohnSmith() {
		Attendee retVal = new Attendee();
		retVal.id = "1234";
		retVal.lastName = "Smith";
		retVal.firstName = "John";
		return retVal;
	}

	private static Attendee getBeckyMalone() {
		Attendee retVal = new Attendee();
		retVal.id = "5678";
		retVal.lastName = "Malone";
		retVal.firstName = "Becky";
		return retVal;
	}

	private static Attendee getWandaJackson() {
		Attendee retVal = new Attendee();
		retVal.id = "9253";
		retVal.lastName = "Jackson";
		retVal.firstName = "Wanda";
		return retVal;
	}

	private static Attendee getKenWilliams() {
		Attendee retVal = new Attendee();
		retVal.id = "6327";
		retVal.lastName = "Williams";
		retVal.firstName = "Ken";
		return retVal;
	}

	private static Attendee getBethWilliams() {
		Attendee retVal = new Attendee();
		retVal.id = "0623";
		retVal.lastName = "Williams";
		retVal.firstName = "Beth";
		return retVal;
	}

	private static Attendee getSadieMcDonald() {
		Attendee retVal = new Attendee();
		retVal.id = "0481";
		retVal.lastName = "McDonald";
		retVal.firstName = "Sadie";
		return retVal;
	}

	private static Attendee getSusanJones() {
		Attendee retVal = new Attendee();
		retVal.id = "1111";
		retVal.lastName = "Jones";
		retVal.firstName = "Susan";
		return retVal;
	}

	private static Attendee getRobertClarkson() {
		Attendee retVal = new Attendee();
		retVal.id = "2222";
		retVal.lastName = "Clarkson";
		retVal.firstName = "Robert";
		return retVal;
	}

	private static Attendee getJoAnnDavis() {
		Attendee retVal = new Attendee();
		retVal.id = "4444";
		retVal.lastName = "Davis";
		retVal.firstName = "Jo Ann";
		return retVal;
	}
}
