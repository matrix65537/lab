package org.crypto;

import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.Security;

import javax.crypto.KeyGenerator;
import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

public class MacApp {
	
	public static void mac(String algorithm, byte[] key, byte[] data){
		System.out.println("=======================================");
		System.out.println(algorithm);
		try {
			SecretKey secretKey = new SecretKeySpec(key, algorithm);
            Mac mac = Mac.getInstance(algorithm);
            try {
				mac.init(secretKey);
				byte[] r = mac.doFinal(data);
                Util.dump(r);
			} catch (InvalidKeyException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (NoSuchAlgorithmException e) {
			System.out.println(e);
			//e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		//Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider());  
		String[] algorithms = {"HmacMD5", "HmacSHA1", "HmacSha224", "HmacSha256", "HmacSha384", "HmacSha512"};
		byte[] key =  {0x01, 0x02, 0x03, 0x04};
		byte[] data = {0x01, 0x02, 0x03, 0x04};
		for(String algorithm : algorithms){
			mac(algorithm, key, data);
		}
	}
}



