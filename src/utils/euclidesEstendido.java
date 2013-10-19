package utils;

import java.math.*;

public class euclidesEstendido {

	BigInteger r;
	BigInteger u;
	BigInteger v;
	
	public euclidesEstendido(BigInteger a, BigInteger b) {
		BigInteger[] valores = this.getResult(a, b);
		
		this.r = valores[0];
		this.u = valores[1];
		this.v = valores[2];
		this.ajustV(a);
	}
	
	
	public BigInteger[] getResult(BigInteger a, BigInteger b){
		
		BigInteger[] retorno = new BigInteger[3];
		
	    if (b.compareTo(BigInteger.ZERO) == 0) {
	        retorno[0] = a;					/*r*/
	        retorno[1] = BigInteger.ONE;	/*u*/
	        retorno[2] = BigInteger.ZERO;	/*v*/
	    }
	    else {
	        
	        BigInteger[] tmp = new BigInteger[3];

	        tmp = this.getResult(b, a.mod(b));
	        retorno[0] = tmp[0];
	        retorno[1] = tmp[2];
	        retorno[2] = tmp[1].subtract(((a.divide(b)).multiply(tmp[2])));
	    }
		return retorno;		
	}
	
	public BigInteger getR() {
		return r;
	}
	public BigInteger getU() {
		return u;
	}
	public BigInteger getV() {
		return v;
	}
	
	public void ajustV(BigInteger a) {
		
		while (this.v.compareTo(BigInteger.ZERO) < 0) {
			this.v = this.v.add(a);
			
		}
		
	}		
}
