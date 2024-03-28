package utils;



import java.util.Date;

import reports.HtmlReporter;
import wrapper.GenericWrappers;

public class Reporter extends GenericWrappers{
	
	protected static long startTime, endTime;
	
	public static void reportStep(String desc, String status) {
		
		System.out.println(desc);
		endTime = new Date().getTime();
		System.out.println("time taken to perform this step is :"+ (endTime-startTime)/1000+" seconds");
		
		desc = desc.replace("[","");
		desc = desc.replace("]","");
		desc = "Step : "+sStepNumber.get()+ " - "+ desc;
		
		System.out.println(desc);
		reportStep(desc, status,true);
		
	}
	
	public static void reportStep(String desc, String ststus, boolean bSnapshot) {
		if(bSnapshot) {
			new HtmlReporter().report(desc, ststus, bSnapshot);
		}
	}
	
}
