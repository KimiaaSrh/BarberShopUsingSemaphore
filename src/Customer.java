import java.util.concurrent.ThreadLocalRandom;

class Customer extends Thread {
    private int id;
    
    public Customer(int id) {
    	this.id=id;
    }

    @Override
    public void run() {
//		System.out.println("hello customer");

        if(BarberShop.q.size()<4) {
        	if(BarberShop.q.size()==0  )
        	{
        		if(Barber.chair.tryAcquire()) {

        			System.out.println(" customer "+this.id+" awakes the barber");
            		System.out.println("cutting of this customer started "+this.id);
            		try {
            			this.sleep((long)500);
            		} catch (InterruptedException e) {
    					// TODO Auto-generated catch block
    					e.printStackTrace();
    				 }
            		Barber.chair.release();
            		System.out.println("cutting of this customer finished "+this.id);

            		Barber.status=0;
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
        		BarberShop.q.add(this.id);
//        		try {
//					Barber.chair.acquire();
//				} catch (InterruptedException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
        		System.out.println("i'm waiting on seats message from customer  "+this.id);
        	}
        }
        	else {
//        		BarberShop.q.add(this.id);
        		System.out.println(" i'm leaving message from customer "+this.id);
//        		
        	}

        }
}
