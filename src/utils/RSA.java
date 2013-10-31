package utils;

import java.math.*;

public class RSA {
	private BigInteger p;
	private BigInteger q;

	private BigInteger n;
	private BigInteger e;
	private BigInteger d;

	public BigInteger getQ() {
		return q;
	}

	public BigInteger getP() {
		return p;
	}

	public BigInteger getN() {
		return n;
	}

	public BigInteger getE() {
		return e;
	}

	public BigInteger getD() {
		return d;
	}
	
	public ChaveRSA getChaveRSA() {
		ChaveRSA ret = new ChaveRSA();
		ret.setD(d);
		ret.setE(e);
		ret.setN(n);
		
		return ret;
	}

	private void init() {
		BigInteger qq, e;
		
		this.n = this.p.multiply(this.q);
		
		qq = p.add(BigInteger.valueOf(-1)).multiply(q.add(BigInteger.valueOf(-1)));
		
		do {
			e = Aleatorio.geraPrimoAleatorio(qq);
		} while ( e.gcd(qq).intValue() != 1 );

		this.e = e;
		
		euclidesEstendido euclides = new euclidesEstendido(qq, e);
		
		this.d = euclides.getV();
	}
	
	public RSA(Integer bitLenght) {
		this.p = Aleatorio.geraPrimoAleatorio(bitLenght/2);
		this.q = Aleatorio.geraPrimoAleatorio(bitLenght/2);
		init();
	}

	public RSA(BigInteger p, BigInteger q) {
		this.p = p;
		this.q = q;
		init();
	}
}
