import java.math.BigInteger;

import utils.*;

public class PGCCRSA {

	public static void main(String[] args) {
		RSA rsaAlg = new RSA(BigInteger.valueOf(13),BigInteger.valueOf(11));
		ChaveRSA chave = rsaAlg.getChaveRSA();
		System.out.printf("Chave privada (d,n) = (%s,%s):\n", chave.getD().toString(), chave.getN().toString());
		System.out.printf("Chave publica (e,n) = (%s,%s):\n", chave.getE().toString(), chave.getN().toString());
		
		BigInteger mensagem = new BigInteger("5");
		BigInteger cripto = new BigInteger("0");
		
		cripto = mensagem.modPow(chave.getE(), chave.getN());
		
		System.out.printf("Mensagem %s,  criptografa fica: %s\n", mensagem.toString(), cripto.toString());
		System.out.printf("\n");
		
		BigInteger decript = new BigInteger("0");
		
		decript = cripto.modPow(chave.getD(), chave.getN());
		
		System.out.printf("Mensagem Cifrada %s,  decriptograda fica: %s\n", cripto.toString(), decript.toString());
		System.out.printf("\n");
		
		System.out.println(args[0]);
		System.out.println(args[1]);
	}
}