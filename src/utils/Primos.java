package utils;

import java.math.*;
import java.util.Random;

public class Primos {

	private final static Random rand = new Random();
	
    private static BigInteger baseAleatoria(BigInteger n)
    {
        while (true)
        {
            final BigInteger a = new BigInteger (n.bitLength(), rand);
            // regra 1 <= a < n
            if (BigInteger.ONE.compareTo(a) <= 0 && a.compareTo(n) < 0)
            {
                return a;
            }
        }
    }
	
	public static boolean validaPrimo(BigInteger n, int maxInteracoes)
    {
        if (n.equals(BigInteger.ONE))
            return false;

        for (int i = 0; i < maxInteracoes; i++)
        {
            BigInteger a = baseAleatoria(n);
            a = a.modPow(n.subtract(BigInteger.ONE), n); // <<<----- Complexidade 

            if (!a.equals(BigInteger.ONE))
                return false;
        }

        return true;
    }
}
