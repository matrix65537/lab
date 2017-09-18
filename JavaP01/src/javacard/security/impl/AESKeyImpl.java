package javacard.security.impl;

import javacard.security.AESKey;

public class AESKeyImpl extends SymmtricKey implements AESKey {

	public AESKeyImpl(byte keyType, short keySize) {
		super(keyType, keySize);
	}
}
