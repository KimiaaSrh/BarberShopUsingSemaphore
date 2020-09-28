import java.util.LinkedList;
import java.util.concurrent.Semaphore;

import java.util.Queue;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;


public class BarberShop {

	public static Queue<Integer> q = new LinkedList<>(); 
	int numberOfCostomers=10;
	public static int hairCuttingCustomer=-1;
	public static Barber barber=new Barber();
    public static void main(String[] args) {
    	
    	
    	barber.start();
//    	System.out.println("ajsbdka");
    	
        int i=0;
        while(true) {
    		Customer p=new Customer(i);
    		p.start();
    		try
            {
                TimeUnit.SECONDS.sleep((long)(Math.random()*10));
            }
            catch(InterruptedException iex)
            {
                iex.printStackTrace();
            }
    		i++;
        }
	}
    static class Barber extends Thread {

    	 Semaphore chair = new Semaphore(1);
    	int status;
    	//status=0 sleep 
    	//status=1 working
    	public int getStatus() {
    		return status;
    	}
    	@Override
        public void run() {

    			while(true) {
    				try
    		        {
    		            Thread.sleep((long)50);
    		        }
    		        catch(InterruptedException iex)
    		        {
    		            iex.printStackTrace();
    		        }
    				if( BarberShop.q.size()!=0 && chair.tryAcquire()) {
    					int  object=9876;

    					object=BarberShop.q.poll();
    					 System.out.println("barber is starting to cut thiscustomer's hair "+object);
    						try
    				        {
    				            this.sleep((long)25);
    				        }
    				        catch(InterruptedException iex)
    				        {
    				            iex.printStackTrace();
    				        }
    						chair.release();
    						System.out.println("barber completed Cuting hair of Customer : "+object);
//    					else
//    						object=BarberShop.hairCuttingCustomer;
    					
    				}
    			}
    			
    	}
    	
    }
    
    static class Customer extends Thread {
        private int id;
        
        public Customer(int id) {
        	this.id=id;
        }

        @Override
        public void run() {
//    		System.out.println("hello customer");

            if(BarberShop.q.size()<4) {
            	if(BarberShop.q.size()==0  )
            	{
            		if(barber.chair.tryAcquire()) {

            			System.out.println(" customer "+this.id+" awakes the barber");
                		System.out.println("cutting of this customer started "+this.id);
                		try {
                			this.sleep((long)500);
                		} catch (InterruptedException e) {
        					// TODO Auto-generated catch block
        					e.printStackTrace();
        				 }
                		barber.chair.release();
                		System.out.println("cutting of this customer finished "+this.id);

                		barber.status=0;
            		}
            		else { 
                		BarberShop.q.add(this.id);
//                		try {
//        					Barber.chair.acquire();
//        				} catch (InterruptedException e) {
//        					// TODO Auto-generated catch block
//        					e.printStackTrace();
//        				}
                		System.out.println("i'm waiting on seats message from customer  "+this.id);

            		}
            	}
            	else {
            		BarberShop.q.add(this.id);
//            		try {
//    					Barber.chair.acquire();
//    				} catch (InterruptedException e) {
//    					// TODO Auto-generated catch block
//    					e.printStackTrace();
//    				}
            		System.out.println("i'm waiting on seats message from customer  "+this.id);
            	}
            }
            	else {
//            		BarberShop.q.add(this.id);
            		System.out.println(" i'm leaving message from customer "+this.id);
//            		
            	}

            }
    }


    
}
