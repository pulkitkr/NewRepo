package com.crm.comcast.SDET32.practise;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class RunTestScriptUsingTripleData {

	@DataProvider
	public Object[][] dataProviderForTicketBooking() {
		
		Object[][] objArr=new Object[5][3];
		
		objArr[0][0]="Bangalore";
		objArr[0][1]="Goa";
		objArr[0][2]=2000;
		
		objArr[1][0]="Bangalore";
		objArr[1][1]="Andaman";
		objArr[1][2]=4000;

		objArr[2][0]="Bangalore";
		objArr[2][1]="Ladakh";
		objArr[2][2]=10000;
		
		objArr[3][0]="Bangalore";
		objArr[3][1]="Kolkata";
		objArr[3][2]=8000;
		
		objArr[4][0]="Bangalore";
		objArr[4][1]="Kashmir";
		objArr[4][2]=14000;
		
		return objArr;
	}
	
	@Test(dataProvider = "dataProviderForTicketBooking")
		public void ticketBookingTest(String from, String to,int price) {
		System.out.println("Travelling from "+from+" to "+to+" and the price is "+price);
	}
}
