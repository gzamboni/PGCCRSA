package utils;

import java.math.BigInteger;
import java.util.Random;

public class Aleatorio {
	public static BigInteger numeroAleatorio(BigInteger max) {
	    Random rnd = new Random();
	    do {
	        BigInteger i = new BigInteger(max.bitLength(), rnd);
	        if (i.compareTo(max) <= 0)
	            return i;
	    } while (true);
	}
	
	public static BigInteger numeroAleatorio(Integer bitLenght) {
	    Random rnd = new Random();
	    do {
	        BigInteger i = new BigInteger(bitLenght, rnd);
	        if (i.bitLength() == bitLenght)
	            return i;
	    } while (true);
	}
}
