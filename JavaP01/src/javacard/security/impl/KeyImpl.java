package javacard.security.impl;

public class KeyImpl {

	private byte keyType;
	private short keySize;
	private short keyLen;
	protected boolean inited;

	public KeyImpl(byte keyType, short keySize) {
		this.keyType = keyType;
		this.keySize = keySize;
		this.keyLen = (byte) ((keySize + 7) >> 3);
	}

	public byte getType() {
		return keyType;
	}

	public short getSize() {
		return keySize;
	}
	
	public short getKeyLen(){
		return keyLen;
	}

	public boolean isInitialized() {
		return inited;
	}
}
