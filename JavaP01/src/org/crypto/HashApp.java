package org.crypto;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.Security;

public class HashApp {
	
	public static void hash(String algorithm, byte[] data){
		System.out.println("=======================================");
		System.out.println(algorithm);
        MessageDigest digest;
		try {
			digest = MessageDigest.getInstance(algorithm, "BC");
			System.out.println(digest.getProvider());
            digest.update(data);
            byte[] r = digest.digest();
            Util.dump(r);
		} catch (NoSuchAlgorithmException e) {
			System.out.println(e);
			//e.printStackTrace();
		} catch(NoSuchProviderException e){
			System.out.println(e);
		}
	}

	public static void main(String[] args) {
		//Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider());  
		String[] algorithms = {"MD5", "SHA", "SHA-224", "SHA-256", "SHA-384", "SHA-512"};
		byte[] data = {0x01, 0x02, 0x03, 0x04};
		for(String algorithm : algorithms){
			hash(algorithm, data);
		}
	}
}



