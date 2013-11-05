import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import utils.*;

public class PGCCRSA {
	
	public static void bruteForceTest(Integer maxBits) {
		System.out.printf("Bits;Tempo Criação;Tempo Criação (ms);P,Q Original;P,Q Quebrado\n");
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
			System.out.printf("%d;%s;%d;(%s,%s,%s)\n", bits, result.getFormatedDuration(), result.getMiliseconds(), rsaAlg.getChaveRSA().getD().toString(16), rsaAlg.getChaveRSA().getE().toString(16),rsaAlg.getChaveRSA().getN().toString(16));
			testResults.add(result);
		}
	}
	
	public static void encriptFile(String inputFile, String outputFile, BigInteger p, BigInteger n) {
		BufferedReader br = null;
		BufferedWriter wr = null;
		try {
			int currentChar;
			String strToCript = "";
			ChaveRSA r = new ChaveRSA();
			r.setN(n);
			r.setE(p);
			br = new BufferedReader(new FileReader(inputFile));
			wr = new BufferedWriter(new FileWriter(outputFile));
			int intBuffer=(int) (p.bitLength()/8);
			while ((currentChar = br.read()) != -1) {
				strToCript = strToCript + (char) currentChar;
				if(strToCript.length() >= intBuffer) {
					String strCript = r.encriptWithKey(p, n, strToCript);
					wr.write(strCript + "\n");
					strToCript="";
				}
			}
		} 
		catch (IOException e) {
			e.printStackTrace();
		} 
		finally {
			try {
				if (br != null)
					br.close();
				if (wr != null)
					wr.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
	}
	
	public static void decriptFile(String inputFile, String outputFile, BigInteger p, BigInteger n) {
		BufferedReader br = null;
		BufferedWriter wr = null;
		try {
			String currentLine;
			ChaveRSA r = new ChaveRSA();
			r.setN(n);
			r.setE(p);
			br = new BufferedReader(new FileReader(inputFile));
			wr = new BufferedWriter(new FileWriter(outputFile));
			while ((currentLine = br.readLine()) != null) {
				String strDeCript = r.decriptWithKey(p, n, currentLine);
				wr.write(strDeCript);
			}
		} 
		catch (IOException e) {
			e.printStackTrace();
		} 
		finally {
			try {
				if (br != null)
					br.close();
				if (wr != null)
					wr.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}	
	}

	public static void main(String[] args) {
		if(args.length > 0) {
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
			else if(args[0].toLowerCase().equals("--encript")) {
				if((args[1] != null) && (args[2] != null) && (args[3] != null) ) {
					try {
						BigInteger k,n;
						k = new BigInteger(args[1].split(",")[0], 16);
						n = new BigInteger(args[1].split(",")[1], 16);
						System.out.println("Lendo o arquivo " + args[2] + " e criptografando em " + args[3]);
						encriptFile(args[2], args[3], k, n);
					} catch (Exception e) {
						System.err.println(e.getMessage());
					}
	
				}
			}
			else if(args[0].toLowerCase().equals("--decript")) {
				if((args[1] != null) && (args[2] != null) && (args[3] != null) ) {
					try {
						BigInteger k,n;
						k = new BigInteger(args[1].split(",")[0], 16);
						n = new BigInteger(args[1].split(",")[1], 16);
						System.out.println("Lendo o arquivo " + args[2] + " e descriptografando em " + args[3]);
						decriptFile(args[2], args[3], k, n);
					} catch (Exception e) {
						System.err.println(e.getMessage());
					}
				}			
			}
			else {
				printUsage();
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
		System.out.println("        --encript <Key> <filein> <fileout>");
		System.out.println("        --decript <Key> <filein> <fileout>");
		System.out.println("A chave <Key> deve estar em base 16 (hex) e no formato:");
		System.out.println("[d|e],n");
		System.out.println("Exemplo:");
		System.out.println("java -jar PGCCRSA.jar --encript 51047299e49c4101,7623febd2d28a55b \"arqentrada\" \"arqsaida\"");
	}
}