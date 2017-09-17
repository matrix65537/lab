package org.crypto;

import java.math.BigInteger;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.Security;
import java.security.interfaces.RSAPrivateCrtKey;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.RSAPrivateCrtKeySpec;
import java.security.spec.RSAPrivateKeySpec;
import java.security.spec.RSAPublicKeySpec;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

public class RsaApp {

	public static RSAPublicKey publicKey;
	public static RSAPrivateKey privateKey;
	public static RSAPrivateCrtKey privateCrtKey;

	public static void dumpBigInteger(BigInteger num) {
		Util.dump(num.toByteArray());
	}

	public static void initKey() {
		KeyPairGenerator keyPairGen;
		try {
			keyPairGen = KeyPairGenerator.getInstance("RSA", "BC");
			System.out.println(keyPairGen.getProvider());

			keyPairGen.initialize(1024);
			KeyPair keyPair = keyPairGen.generateKeyPair();

			publicKey = (RSAPublicKey) keyPair.getPublic();
			privateKey = (RSAPrivateKey) keyPair.getPrivate();

			BigInteger N = publicKey.getModulus();
			BigInteger E = publicKey.getPublicExponent();

			BigInteger N2 = privateKey.getModulus();
			BigInteger D = privateKey.getPrivateExponent();

			dumpBigInteger(N);
			dumpBigInteger(E);
			dumpBigInteger(N2);
			dumpBigInteger(D);
			
			System.out.println("=======================================");
			privateCrtKey = (RSAPrivateCrtKey)keyPair.getPrivate();
			
			BigInteger P, Q,DP, DQ, QINV;
			
			N = privateCrtKey.getModulus();
			E = privateCrtKey.getPublicExponent();
			D = privateCrtKey.getPrivateExponent();
			
			P = privateCrtKey.getPrimeP();
			Q = privateCrtKey.getPrimeQ();
			DP = privateCrtKey.getPrimeExponentP();
			DQ = privateCrtKey.getPrimeExponentQ();
			QINV = privateCrtKey.getCrtCoefficient();
			
			dumpBigInteger(N);
			dumpBigInteger(E);
			dumpBigInteger(D);

			dumpBigInteger(P);
			dumpBigInteger(Q);
			dumpBigInteger(DP);
			dumpBigInteger(DQ);
			dumpBigInteger(QINV);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static RSAPublicKey getPublicKey(String N, String E) {
		BigInteger n = new BigInteger(N, 16);
		BigInteger e = new BigInteger(E, 16);
		try {
			KeyFactory keyFactory = KeyFactory.getInstance("RSA");
			RSAPublicKeySpec keySpec = new RSAPublicKeySpec(n, e);
			try {
				return (RSAPublicKey) keyFactory.generatePublic(keySpec);
			} catch (InvalidKeySpecException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		} catch (NoSuchAlgorithmException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return null;
	}

	public static RSAPrivateKey getPrivateKey(String N, String E) {
		BigInteger n = new BigInteger(N, 16);
		BigInteger e = new BigInteger(E, 16);
		try {
			KeyFactory keyFactory = KeyFactory.getInstance("RSA");
			RSAPrivateKeySpec keySpec = new RSAPrivateKeySpec(n, e);
			try {
				return (RSAPrivateKey) keyFactory.generatePrivate(keySpec);
			} catch (InvalidKeySpecException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		} catch (NoSuchAlgorithmException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return null;
	}
	
	public static RSAPrivateCrtKey getPrivateCrtKey(String N, String E, String D, String P, String Q, String DP, String DQ, String QINV) {
		BigInteger n = new BigInteger(N, 16);
		BigInteger e = new BigInteger(E, 16);
		BigInteger d = new BigInteger(D, 16);

		BigInteger p = new BigInteger(P, 16);
		BigInteger q = new BigInteger(Q, 16);
		BigInteger dp = new BigInteger(DP, 16);
		BigInteger dq = new BigInteger(DQ, 16);
		BigInteger qinv = new BigInteger(QINV, 16);
		try {
			KeyFactory keyFactory = KeyFactory.getInstance("RSA");
			RSAPrivateCrtKeySpec keySpec = new RSAPrivateCrtKeySpec(n, null, null, p, q, dp, dq, qinv);
			try {
				return (RSAPrivateCrtKey) keyFactory.generatePrivate(keySpec);
			} catch (InvalidKeySpecException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		} catch (NoSuchAlgorithmException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return null;
	}

	public static void enc() {
		try {
			Cipher cipher = Cipher.getInstance("RSA/NONE/NoPadding");
			try {
				byte[] input = new byte[128];
				input[0] = 0x10;
				byte[] output;
				try {
					cipher.init(Cipher.ENCRYPT_MODE, privateKey);
					output = cipher.doFinal(input);
					Util.dump(output);

					cipher.init(Cipher.ENCRYPT_MODE, privateCrtKey);
					output = cipher.doFinal(input);
					Util.dump(output);
				} catch (IllegalBlockSizeException | BadPaddingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} catch (InvalidKeyException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (NoSuchAlgorithmException | NoSuchPaddingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider());
		//initKey();

		String N = "0094D90DCCC5926A9B776B54164F3DA8CCBBF890C0295B8695CDE40071A7A87B3A13A4A687EB4115D0EFA93AD83C348940D5BDBB670B2494E8FD5C32221B7E48503C744AC5D4B1418A32091E3C482653AF0773A4D1DC2BE5F667AA473D873F1BD24C39921D962BD905D10ADE7DCA5F927BAE8F96FF740155CE05821DBA406E17B5";
		String E = "010001";
		String D = "082912399CC75732E8CC2893048B23DAFD9F360862FC1297D9EEDB1B600EBB9AC682EB625E427638BB3AF2EFF1F50B6553929058A8E2BB8B3E80B47AEC38F32BE58AB817A7D1C23953FA711B99155299218C8E6BB25C66DE04900AAE40C70F7395A166564D410A0B17CFE47EC43758E6C47A6D11E71D8C044DDC622560659401";

		String P = "C91176B37AA651B460C52A49290025C415647FF1EA95B26F758B956DE2DE95308057DE52CDF7FA5D4A94B2A9F485121101DBE890977298D6F7A1DD8ADF5FD2B5";
		String Q = "BD835824501C089FB97C0530743A14A9BDDAFDCFDB598D3356DCD7B1E5F9C48084591E795F70FB13D6679D47FE2C3772E99B466297E98033205A164EB0B75101";
		String DP = "1629CEA3CA093B485EC25CFEE37E18AB9C900F6A63294D19EC230EBDA61C52E59DC011C99EDA8EC669CA2A4781A48971467B2F0F812228A6C975162E90A0C761";
		String DQ = "0ABA6ABCF38402C0F8011B5218E248D52B18409209BAD2117BF3588A8B984E157068931F0FD6178091FC9F9CB9B8CB12C842B00358D2311DB3D125BE22A6DE01";
		String QINV = "A724669A7C9199756621934CE033D8F29F4FC8DAC53D65348F0089F46965C31DF544D4E671B0AB24037ECB98FF04C9BF31E75FE9B539C07CCACB1A88E98F0BDA";


		privateKey = getPrivateKey(N, D);
		publicKey = getPublicKey(N, E);
		privateCrtKey = getPrivateCrtKey(N, E, D, P, Q, DP, DQ, QINV);

		enc();
	}
}
