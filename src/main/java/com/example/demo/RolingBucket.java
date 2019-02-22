package com.example.demo;

public class RolingBucket {

private int TotalSentToQR=0;
	
	boolean ProbabilityGenerator() {
		boolean b=Math.random() < 0.5;
		return b;
	}
	
	public void RollingBucketlogic() {
		
		int BucketSize=10;
		
		int QRPercent=20;
		int counter=0;
		int array[]=new int[50];
		int loopcount=1;
		int j=0;
		System.out.println("BucketSize is: "+BucketSize);
//		int j=0;
		
		for(int i=0;i<50;i++) {
			
			j++;
			String wr="WR";	
			wr=wr.concat(Integer.toString(i));
		
			
			int diff=BucketSize-i;
			if(i>BucketSize) {
				
				diff=i-BucketSize;
			   if(array[i-BucketSize]==1) {
				   
				   counter--;
				   
				   System.out.println("Decremented counter value for i=: "+i);
			   }
		   }
			
			if(j==BucketSize) {
				loopcount++;
				j=0;
			}

			
			   System.out.println("\nFor request "+i+" counter value is: "+counter);
			   System.out.println("\nTotal processed wrs: "+i);

			   int min_value=Math.min(BucketSize, i);
			   int QRsendcount=(QRPercent*min_value)/100;
			   if(min_value%2!=0 && QRPercent>50) {
				   QRsendcount++;
			   }
			   int QRsendcountforcurrentbucket=(QRPercent*BucketSize)/100;
			   
			   
			   System.out.println("QRSendcount: "+QRsendcount);

				
			   if((QRsendcountforcurrentbucket-counter)<=(diff)) {

			   
			   boolean b;
			
			   if(counter<=QRsendcount) {
				
				   if((b=ProbabilityGenerator())==true) {
				//Send to QR for review by updating wrs_sentto_qr
					counter++;
					System.out.println(wr+" Sent to QR: Updated counter: "+counter+"\n");
					array[i]=1;
					TotalSentToQR++;
				   }else {
				
					System.out.println(wr +" Not Sent: Counter:"+counter);
					array[i]=0;
				   }
			
			   }else {
				
				System.out.println(wr +" Not Sent: Counter:"+counter);
				array[i]=0;
				
				}
		
		}else {
			   
			   counter++;
				System.out.println(wr+" Sent to forcefully QR: Updated counter: "+counter+"\n");
				array[i]=1;
				TotalSentToQR++;
		}
		}
			

		System.out.println(" Total Sent: "+TotalSentToQR);


	
	
}
	
	
}
