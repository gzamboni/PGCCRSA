package utils;

import java.math.*;

public class ChaveRSA {
	private BigInteger e;
	private BigInteger d;
	private BigInteger n;
	
	public BigInteger getE() {
		return e;
	}
	public void setE(BigInteger e) {
		this.e = e;
	}
	public BigInteger getD() {
		return d;
	}
	public void setD(BigInteger d) {
		this.d = d;
	}
	public BigInteger getN() {
		return n;
	}
	public void setN(BigInteger n) {
		this.n = n;
	}
	
	public BigInteger[] getPublicKey() {
		BigInteger [] ret = new BigInteger[2];
		ret[0] = this.e;
		ret[1] = this.n;
		return ret;
	}
	
	public BigInteger[] getPrivateKey() {
		BigInteger [] ret = new BigInteger[2];
		ret[0] = this.d;
		ret[1] = this.n;
		return ret;
	}
	
	public String encriptWithPrivateKey(String message) {
		return (new BigInteger(message.getBytes())).modPow(this.getE(), this.getN()).toString(16);
	}
	
	public String encriptWithPublicKey(String message) {
		return (new BigInteger(message.getBytes())).modPow(this.getD(), this.getN()).toString(16);
	}
	
	public String encriptWithKey(BigInteger p, BigInteger n, String message) {
		return (new BigInteger(message.getBytes())).modPow(p, n).toString(16);
	}

	public String decriptWithPrivateKey(String message) {
		return new String((new BigInteger(message,16)).modPow(this.getE(), this.getN()).toByteArray());
	}
	
	public String decriptWithPublicKey(String message) {
		return new String((new BigInteger(message,16)).modPow(this.getD(), this.getN()).toByteArray());
	}
	
	public String decriptWithKey(BigInteger p, BigInteger n, String message) {
		return new String((new BigInteger(message,16)).modPow(p, n).toByteArray());
	}
}
