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
	
	public static BigInteger geraPrimoAleatorio(BigInteger max) {
		BigInteger num;
		do {
			num = Aleatorio.numeroAleatorio(max); 
			if(Primos.validaPrimo(num, 60)) {
				break;
			}
		} while ( true );
		return num;
	}
	
	public static BigInteger geraPrimoAleatorio(Integer bitLenght) {
		BigInteger num;
		do {
			num = Aleatorio.numeroAleatorio(bitLenght); 
			if(Primos.validaPrimo(num, 60)) {
				break;
			}
		} while ( true );
		return num;
	}
}
