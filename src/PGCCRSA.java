import java.math.BigInteger;

import utils.*;

@SuppressWarnings("unused")
public class PGCCRSA {

	public static void main(String[] args) {
		
		for(int i=0;i<30;i++){
			BigInteger test;
			do {
				test = Aleatorio.numeroAleatorio(512); 
				if(Primos.validaPrimo(test, 60)) {
					break;
				}
			} while ( true );
			System.out.printf("checkprime(%s) is %b%n", test.toString(), Primos.validaPrimo(test, 60));			
		}
	}
}
