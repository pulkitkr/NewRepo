package com.crm.comcast.SDET32.practise;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class RunTestScriptUsingMultipleData {

	@DataProvider
	public Object[][] dataProviderForTicketBooking() {
		
		Object[][] objArr=new Object[5][2];
		
		objArr[0][0]="Bangalore";
		objArr[0][1]="Goa";
		
		objArr[1][0]="Bangalore";
		objArr[1][1]="Andaman";

		objArr[2][0]="Bangalore";
		objArr[2][1]="Ladakh";
		
		objArr[3][0]="Bangalore";
		objArr[3][1]="Kolkata";
		
		objArr[4][0]="Bangalore";
		objArr[4][1]="Kashmir";
		
		return objArr;
	}
	
	@Test(dataProvider = "dataProviderForTicketBooking")
		public void ticketBookingTest(String from, String to) {
		System.out.println("Travelling from "+from+" to "+to);
	}
}
