import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import utils.*;

public class PGCCRSA {
	
	public static void bruteForceTest(Integer maxBits) {
		System.out.printf("Bits;Tempo Criação;Tempo Criação (ms);P,Q Original, P,Q Quebrado\n");
		for (int bits=8; bits <= maxBits; bits++) {
			KeyGenTestResult result = new KeyGenTestResult();
			RSA rsaAlg = new RSA(bits);
			ChaveRSA chave = rsaAlg.getChaveRSA();
			result.setStart(new Date());
			forcaBruta forte = new forcaBruta(chave.getN());
			result.setEnd(new Date());
			System.out.println(bits + ";" + result.getFormatedDuration() + ";" + result.getMiliseconds() + ";(" +  
							   rsaAlg.getP().toString(16) + "," + rsaAlg.getQ().toString(16) + ");(" + 
							   forte.getP().toString(16) + "," + forte.getQ().toString(16) + ")");
		}		
	}
	
	public static void createKeysTest(Integer maxBits){
		List<KeyGenTestResult> testResults = new ArrayList<KeyGenTestResult>();

		System.out.printf("Bits;Tempo Criação(s);Chave\n");
		for (int bits=8; bits <= maxBits; bits++) {
			KeyGenTestResult result = new KeyGenTestResult();				
			result.setStart(new Date());
			RSA rsaAlg = new RSA(bits);
			result.setEnd(new Date());
			result.setBitLenght(bits);
			System.out.printf("%d;%s;%d;(%s,%s)\n", bits, result.getFormatedDuration(), result.getMiliseconds(), rsaAlg.getChaveRSA().getD().toString(16),rsaAlg.getChaveRSA().getN().toString(16));
			testResults.add(result);
		}
	}
	
	public static void main(String[] args) {
		if(args[0].toLowerCase().equals("--keygentest")) {
			if(!args[1].isEmpty()) {
				try {
					int bits;
					bits = Integer.parseInt(args[1]);
					createKeysTest(bits);
				} catch (Exception e) {
					System.err.println(e.getMessage());
				}
			}
		}
		else if(args[0].toLowerCase().equals("--bruteforcetest")) {
			if(!args[1].isEmpty()) {
				try {
					int bits;
					bits = Integer.parseInt(args[1]);
					bruteForceTest(bits);
				} catch (Exception e) {
					System.err.println(e.getMessage());
				}
			}
		}
		else {
			printUsage();
		}
	}

	private static void printUsage() {
		System.out.println("PGCCRSA [Opções]");
		System.out.println("        --keygentest <bits>");
		System.out.println("        --bruteforcetest <bits>");
		System.out.println("        --encript <PrivateKey> <filein> <fileout>");
		System.out.println("        --decript <PublicKey> <filein> <fileout>");
		System.out.println("Tanto a <PrivateKey> e a <PublicKey> deverão estar no formato:");
		System.out.println("([d|e],n)");
		
		RSA t = new RSA(1024);
		ChaveRSA c = t.getChaveRSA();
		String m = "Teste";
		String mepk = c.encriptWithPrivateKey(m);
		String decer = c.decriptWithPublicKey(mepk);
		String mecer = c.encriptWithPublicKey(m);
		String depk = c.decriptWithPrivateKey(mecer);
		
		System.out.println(m);
		System.out.println(mepk);
		System.out.println(depk);
		System.out.println(mecer);
		System.out.println(decer);
	}
}