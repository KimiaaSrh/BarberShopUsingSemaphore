import java.util.concurrent.Semaphore;

public class Barber extends Thread {

	public static Semaphore chair = new Semaphore(1);
	static int status;
	//status=0 sleep 
	//status=1 working
	public static int getStatus() {
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
				if( BarberShop.q.size()!=0 && Barber.chair.tryAcquire()) {
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
						Barber.chair.release();
						System.out.println("barber completed Cuting hair of Customer : "+object);
//					else
//						object=BarberShop.hairCuttingCustomer;
					
				}
			}
			
	}
	
}
