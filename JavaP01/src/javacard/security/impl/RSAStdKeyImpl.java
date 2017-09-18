package javacard.security.impl;

import javacard.framework.Util;
import javacard.security.CryptoException;
import javacard.security.RSAPrivateKey;
import javacard.security.RSAPublicKey;

public class RSAStdKeyImpl extends KeyImpl implements RSAPublicKey, RSAPrivateKey{
	
	private byte[] e;
	private byte[] n;
	
	private short eLen;
	private short nLen;
	
	private boolean eInited;
	private boolean nInited;


	public RSAStdKeyImpl(byte keyType, short keySize) {
		super(keyType, keySize);
		short keyLen = getKeyLen();
		e = new byte[keyLen];
		n = new byte[keyLen];
	}

	@Override
	public void clearKey() {
		inited = false;
		short keyLen = getKeyLen();
		Util.arrayFillNonAtomic(e, (short)0, keyLen, (byte)0);
		Util.arrayFillNonAtomic(n, (short)0, keyLen, (byte)0);
	}

	@Override
	public void setModulus(byte[] buffer, short offset, short length)
			throws CryptoException {
		Util.arrayCopy(buffer, offset, n, (short) 0, length);
		nLen = length;
		nInited = true;	
		inited = (nInited && eInited);
	}

	@Override
	public void setExponent(byte[] buffer, short offset, short length)
			throws CryptoException {
		Util.arrayCopy(buffer, offset, e, (short) 0, length);
		eLen = length;
		eInited = true;	
		inited = (nInited && eInited);
	}

	@Override
	public short getModulus(byte[] buffer, short offset) {
		Util.arrayCopy(n, (short) 0, buffer, offset, nLen);
		return nLen;
	}

	@Override
	public short getExponent(byte[] buffer, short offset) {
		Util.arrayCopy(e, (short) 0, buffer, offset, eLen);
		return eLen;
	}

}
