package org.crypto;

import java.security.Security;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.DESedeKeySpec;
import javax.crypto.spec.IvParameterSpec;

public class DesApp {
	
	public static void des(String algorithm, byte[] key, byte[] data, byte[] iv){
		System.out.println("=======================================");
		System.out.println(algorithm);
		try {
			DESKeySpec dks = new DESKeySpec(key);
			SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
			SecretKey secretKey = keyFactory.generateSecret(dks);
            Cipher cipher = Cipher.getInstance(algorithm, "BC");
			System.out.println(cipher.getProvider());
			if(iv != null){
				IvParameterSpec ivParam = new IvParameterSpec(iv);
				cipher.init(Cipher.ENCRYPT_MODE, secretKey, ivParam);
			}else{
				cipher.init(Cipher.ENCRYPT_MODE, secretKey);
			}
            byte[] r = cipher.doFinal(data);
            Util.dump(r);
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public static void des3(String algorithm, byte[] key, byte[] data, byte[] iv){
		System.out.println("=======================================");
		System.out.println(algorithm);
		try {
			DESedeKeySpec dks = new DESedeKeySpec(key);
			SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DESede");
			SecretKey secretKey = keyFactory.generateSecret(dks);
            Cipher cipher = Cipher.getInstance(algorithm, "BC");
			System.out.println(cipher.getProvider());
			if(iv != null){
				IvParameterSpec ivParam = new IvParameterSpec(iv);
				cipher.init(Cipher.ENCRYPT_MODE, secretKey, ivParam);
			}else{
				cipher.init(Cipher.ENCRYPT_MODE, secretKey);
			}
            byte[] r = cipher.doFinal(data);
            Util.dump(r);
		} catch (Exception e) {
			System.out.println(e);
			e.printStackTrace();
		}
	}
	public static void main(String[] args) {
		Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider());  
		byte[] key =   {0x01, 0x02, 0x03, 0x04, 0x05, 0x06, 0x07, 0x08};
		byte[] data =  {0x01, 0x02, 0x03, 0x04, 0x05, 0x06, 0x07, 0x08};
		byte[] iv =  {0x01, 0x02, 0x03, 0x04, 0x05, 0x06, 0x07, 0x08};
        des("DES/ECB/NOPadding", key, data, null);
        des("DES/CBC/NOPadding", key, data, iv);
		byte[] key2 =   {0x01, 0x02, 0x03, 0x04, 0x05, 0x06, 0x07, 0x08, 0x09, 0x0A, 0x0B, 0x0C, 0x0D, 0x0E, 0x0F, 0x10, 0x11, 0x12, 0x13, 0x14, 0x15, 0x16, 0x17, 0x18};
        des3("DESede/ECB/NOPadding", key2, data, null);
        des3("DESede/CBC/NOPadding", key2, data, iv);
	}
}



