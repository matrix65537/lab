package javacard.security.impl;

import javacard.framework.Util;
import javacard.security.CryptoException;
import javacard.security.RSAPrivateCrtKey;

public class RSAPrivateCrtKeyImpl extends KeyImpl implements RSAPrivateCrtKey
{

    private byte[] p;
    private byte[] q;
    private byte[] dp;
    private byte[] dq;
    private byte[] qinv;

    private short componentLen;

    private boolean pInited;
    private boolean qInited;
    private boolean dpInited;
    private boolean dqInited;
    private boolean qinvInited;

    public RSAPrivateCrtKeyImpl(byte keyType, short keySize)
    {
        super(keyType, keySize);
        componentLen = (short) (getKeyLen() >> 1);
    }

    @Override
    public void clearKey()
    {
        inited = false;
        Util.arrayFillNonAtomic(p, (short)0, componentLen, (byte)0);
        Util.arrayFillNonAtomic(q, (short)0, componentLen, (byte)0);
        Util.arrayFillNonAtomic(dp, (short)0, componentLen, (byte)0);
        Util.arrayFillNonAtomic(dq, (short)0, componentLen, (byte)0);
        Util.arrayFillNonAtomic(qinv, (short)0, componentLen, (byte)0);
    }

    private void checkLen(short length)
    {
        if ((length * 2) != getKeyLen())
        {
            CryptoException.throwIt(CryptoException.ILLEGAL_VALUE);
        }
    }

    private void checkInited()
    {
        if (pInited && qInited && dpInited && dqInited && qinvInited)
        {
            inited = true;
        }
    }

    @Override
    public void setP(byte[] buffer, short offset, short length)
    throws CryptoException
    {
        checkLen(length);
        Util.arrayCopy(buffer, offset, p, (short) 0, length);
        pInited = true;
        checkInited();
    }

    @Override
    public void setQ(byte[] buffer, short offset, short length)
    throws CryptoException
    {
        checkLen(length);
        Util.arrayCopy(buffer, offset, q, (short) 0, length);
        qInited = true;
        checkInited();
    }

    @Override
    public void setDP1(byte[] buffer, short offset, short length)
    throws CryptoException
    {
        checkLen(length);
        Util.arrayCopy(buffer, offset, dp, (short) 0, length);
        dpInited = true;
        checkInited();

    }

    @Override
    public void setDQ1(byte[] buffer, short offset, short length)
    throws CryptoException
    {
        checkLen(length);
        Util.arrayCopy(buffer, offset, dq, (short) 0, length);
        dqInited = true;
        checkInited();

    }

    @Override
    public void setPQ(byte[] buffer, short offset, short length)
    throws CryptoException
    {
        checkLen(length);
        Util.arrayCopy(buffer, offset, qinv, (short) 0, length);
        qinvInited = true;
        checkInited();
    }

    @Override
    public short getP(byte[] buffer, short offset)
    {
        Util.arrayCopy(p, (short) 0, buffer, offset, componentLen);
        return componentLen;
    }

    @Override
    public short getQ(byte[] buffer, short offset)
    {
        Util.arrayCopy(q, (short) 0, buffer, offset, componentLen);
        return componentLen;
    }

    @Override
    public short getDP1(byte[] buffer, short offset)
    {
        Util.arrayCopy(dp, (short) 0, buffer, offset, componentLen);
        return componentLen;
    }

    @Override
    public short getDQ1(byte[] buffer, short offset)
    {
        Util.arrayCopy(dq, (short) 0, buffer, offset, componentLen);
        return componentLen;
    }

    @Override
    public short getPQ(byte[] buffer, short offset)
    {
        Util.arrayCopy(qinv, (short) 0, buffer, offset, componentLen);
        return componentLen;
    }

}
