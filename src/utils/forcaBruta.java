package utils;

import java.math.BigInteger;

public class forcaBruta {

	private BigInteger chave;
	private BigInteger p;
	private BigInteger q;
	
	public forcaBruta(BigInteger chave) {
		this.chave = chave;	
		this.quebrarChave();
	}
		
	public void quebrarChave() {
        
		BigInteger p = new BigInteger("2");                
        BigInteger q = new BigInteger("2");        
        
        boolean stopCondition = true;
        do {
            do {
                q = q.add(new BigInteger("1"));       
                
                BigInteger r = p.multiply(q);
                
                if (r.compareTo(chave) > 0) {
                    break;
                }
                if (r.compareTo(chave) == 0) {                    
                	stopCondition = !stopCondition;
                    this.p = p;
                    this.q = q;                    
                }
            } while (stopCondition);
            p = p.add(new BigInteger("1"));
            q = BigInteger.ONE;
        } while (stopCondition);        
    }

	public BigInteger getP() {
		return p;
	}
	
	public BigInteger getQ() {
		return q;
	}
}