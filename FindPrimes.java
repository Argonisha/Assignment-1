import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

public class FindPrimes {
	public static void main(String[] args) { 
		
		
		long timeStart = System.currentTimeMillis();
		PrimeThreading[] threads = new PrimeThreading[8];
		for(int i = 0;i<8;i++)
		{
			threads[i] = new PrimeThreading();
			threads[i].start();
		}
		for(int i = 0;i<8;i++)
		{
			try {
				threads[i].join();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		long timeFinish = System.currentTimeMillis();
		Arrays.sort(PrimeThreading.biggestPrimes);
		try {
			File fileOut = new File("primes.txt");
			FileWriter writer = new FileWriter("primes.txt");
			String writeString = (timeFinish-timeStart) + "ms " + PrimeThreading.count + " " + PrimeThreading.total;
			writer.write(writeString+"\n");
			for(int i = 0;i<10;i++) {
				writer.write(PrimeThreading.biggestPrimes[i]+" ");
			}
			writer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//System.out.println((timeFinish-timeStart) + "ms " + PrimeThreading.total + " " + PrimeThreading.count);
		//PrimeThreading.printBiggest();

	}

}

class PrimeThreading extends Thread {
	
	public static AtomicLong total = new AtomicLong(2); //the number 2 is automatically accounted for
	public static AtomicInteger count = new AtomicInteger(1); //the number 2 is automatically accounted for
	public static int testNum = 99999999; //starting top to bottom to fill the biggest primes easily, also not including even numbers
	public static int biggestPrimes[] = new int[10];
	public static int unfilledBiggest = 9;
	
	public void run() {
		//System.out.println("Currently in Thread: "+ this.getId());
		//System.out.println()
		int curNum;
		while((curNum = nextTest()) > 0) {
			if(isPrime(curNum)) {
				addBiggest(curNum);
				total.addAndGet(curNum);
				count.incrementAndGet();
			}
			//nextTest();
		}
	}
	public static synchronized void addBiggest(int val) {
		if(unfilledBiggest >= 0) {
			biggestPrimes[unfilledBiggest] = val;
			unfilledBiggest--;
		}
		return;
	}

	public static void printBiggest() {
		for(int i = 0;i<5;i++) {
			System.out.print(biggestPrimes[i]+" ");
		}
	}
	
	public static synchronized int nextTest() {
		int nextVal = testNum;
	    testNum-=2;
		return nextVal;
	}
	
	boolean isPrime(long n) {
	    if(n < 2) return false;
	    if(n == 2 || n == 3) return true;
	    if(n%2 == 0 || n%3 == 0) return false;
	    long sqrtN = (long)Math.sqrt(n)+1;
	    for(long i = 6L; i <= sqrtN; i += 6) {
	        if(n%(i-1) == 0 || n%(i+1) == 0) return false;
	    }
	   //System.out.println(n+" is prime");
	    return true;
	}


}
