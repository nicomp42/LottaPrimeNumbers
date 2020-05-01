/*
 * Bill Nicholson
 * nicholdw@ucmail.uc.edu
 * 
 * There are about 5_761_455 primes under 100_000_000
 */
package lottaPrimeNumbers;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Arrays;

public class LottaPrimeNumbers {
	public static void main(String[] args) {
		long limit = (long) Math.pow(10, 8);
		final long startTime = System.currentTimeMillis();
//		Long[] primes = CalculatePrimes(limit);
		ArrayList<Long> primes = CalculatePrimesWithArrayList(limit);
		final long endTime = System.currentTimeMillis();
		System.out.println("Total execution time to compute and serialize primes up to " + limit + ": " + (endTime - startTime)/1000. + " seconds.");
		System.out.println("First = " + primes.get(0) + ", last = " + primes.get(primes.size()-1) + ", " + primes.size() + " total primes.");
//		System.out.println("First = " + primes[0] + ", last = " + primes[primes.length] + ", " + primes.length + " total primes.");
	}
	private static Long[] CalculatePrimes(long limit) {
		Long[] primes = new Long[5_800_800];	// This is an informed guess. 
		primes[0] = (long) 2;
		int count = 1;
		for (long i = 3; i < limit; i+=2) {
			if (IsPrime(i)) {
				primes[count] = i;
				count++;
				if (count % 100_000 == 0) {System.out.println(i);} // Just to see it's still running
			}
		} try {
            FileOutputStream fos = new FileOutputStream("primes.ser");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(primes);
            oos.close();
            fos.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }	
        return primes;
	}
	private static ArrayList<Long> CalculatePrimesWithArrayList(long limit) {
		ArrayList<Long> primes = new ArrayList<Long>(5_800_800);	// This is an informed guess
		primes.add((long) 2);
		int count = 0;
		for (long i = 3; i < limit; i+=2) {
			if (IsPrime(i)) {
				primes.add(i);
				count++;
				if (count % 100_000 == 0) {System.out.println(i);} // Just to see it's still running
			}
		} try {
            FileOutputStream fos = new FileOutputStream("primes.ser");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(primes);
            oos.close();
            fos.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }	
        return primes;
	}

	private static boolean IsPrime(long num) {
		if (num == 2 || num == 3) return true;
		if (num %   2 == 0) return false;
		if (num %   3 == 0) return false;
		if (num %   5 == 0) return false;
		if (num %   7 == 0) return false;
		if (num %  11 == 0) return false;
		if (num %  13 == 0) return false;
		if (num %  17 == 0) return false;
		long limit = (long) Math.sqrt(num);
		for (long i = 19; i <= limit; i += 2) {
			if (num % i == 0) {return false;}
		}
		return true;
	}
	@SuppressWarnings("unchecked")
	private static java.util.List<Long> ReadPrimes() {
		//ArrayList<Long> primes = new ArrayList<Long>();
		ArrayList<Long> primes = new ArrayList<Long>(5_800_800);	// This is an informed guess
        try {
            FileInputStream fis = new FileInputStream("primes.ser");
            ObjectInputStream ois = new ObjectInputStream(fis);
            primes = (ArrayList<Long>) ois.readObject();
            ois.close();
            fis.close();
        } catch(Exception ex) {
            ex.printStackTrace();
        }
        return primes;
    }
}
