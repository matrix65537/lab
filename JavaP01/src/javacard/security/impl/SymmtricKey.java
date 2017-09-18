package javacard.security.impl;

import javacard.framework.*;
import javacard.security.SecretKey;

public class SymmtricKey extends KeyImpl implements SecretKey
{

    private byte[] keyData;
    

    public SymmtricKey(byte keyType, short keySize)
    {
    	super(keyType, keySize);
        this.keyData = new byte[getKeyLen()];
        inited = false;
    }

    @Override
    public void clearKey()
    {
    	inited = false;
    	Util.arrayFillNonAtomic(keyData, (short)0, (short)keyData.length, (byte)0);
    }

    public void setKey(byte[] keyData, short kOff) throws 
           NullPointerException, ArrayIndexOutOfBoundsException
    {
		Util.arrayCopy(keyData, kOff, keyData, (short) 0, getKeyLen());
		inited = true;
    }

    public byte getKey(byte[] keyData, short kOff)
    {
    	short keyLen = getKeyLen();
		Util.arrayCopy(this.keyData, (short) 0, keyData, kOff, keyLen);
        return (byte)keyLen;
    }

}
