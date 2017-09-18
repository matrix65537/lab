package javacard.security.impl;

import javacard.framework.Util;
import javacard.security.CryptoException;
import javacard.security.Key;
import javacardx.crypto.Cipher;

public class CipherSymm extends Cipher {
	
	private byte algorithm;
	private Key theKey;
	private byte theMode;
	private byte[] iv;
	
	public CipherSymm(byte algorithm){
		this.algorithm = algorithm;
	}

	@Override
	public void init(Key theKey, byte theMode) throws CryptoException {
		this.theKey = theKey;
		this.theMode = theMode;
	}

	@Override
	public void init(Key theKey, byte theMode, byte[] bArray, short bOff,
			short bLen) throws CryptoException {
		this.theKey = theKey;
		this.theMode = theMode;
		iv = new byte[bLen];
		Util.arrayCopy(bArray, bOff, iv, (short)0, bLen);
	}

	@Override
	public byte getAlgorithm() {
		return 0;
	}

	@Override
	public short doFinal(byte[] inBuff, short inOffset, short inLength,
			byte[] outBuff, short outOffset) throws CryptoException {
		return 0;
	}

	@Override
	public short update(byte[] inBuff, short inOffset, short inLength,
			byte[] outBuff, short outOffset) throws CryptoException {
		return 0;
	}

}
