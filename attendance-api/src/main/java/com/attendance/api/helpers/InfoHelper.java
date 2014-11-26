package com.attendance.api.helpers;

import javax.ws.rs.WebApplicationException;

import com.attendance.api.pojos.Info;

public class InfoHelper {

	public static Info getInfo(String eventId) {
		switch (eventId) {
		case "7203":
			return getKnittingClass();
		case "9333":
			return getWoodworkingClass();
		case "0061":
			return getQuiltingClass();
		case "2298":
			return getMomAndMeClass();
		case "5736":
			return getEmbroideryClass();
		case "2301":
			return getHolidayClass();
		default:
			throw new WebApplicationException(404);
		}
	}

	private static Info getKnittingClass() {
		Info classInfo = new Info();
		classInfo.name = "Knitting & Crochet Group";
		classInfo.instructor = "Jaime Rollins";
		return classInfo;
	}

	private static Info getWoodworkingClass() {
		Info classInfo = new Info();
		classInfo.name = "Woodworking 201";
		classInfo.instructor = "Jason Salisbury";
		return classInfo;
	}

	private static Info getQuiltingClass() {
		Info classInfo = new Info();
		classInfo.name = "Quilting for Beginners";
		classInfo.instructor = "Susie Watkins";
		return classInfo;
	}

	private static Info getMomAndMeClass() {
		Info classInfo = new Info();
		classInfo.name = "Mom & Me Craft - Birdfeeder";
		classInfo.instructor = "Jason Salisbury";
		return classInfo;
	}

	private static Info getEmbroideryClass() {
		Info classInfo = new Info();
		classInfo.name = "Advanced Embroidery";
		classInfo.instructor = "Susie Watkins";
		return classInfo;
	}

	private static Info getHolidayClass() {
		Info classInfo = new Info();
		classInfo.name = "Holiday Craft - Burlap Wreath";
		classInfo.instructor = "Mya Grisham";
		return classInfo;
	}
}
