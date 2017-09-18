package javacard.security.impl;

import javacard.security.DESKey;

public class DESKeyImpl extends SymmtricKey implements DESKey {

	public DESKeyImpl(byte keyType, short keySize) {
		super(keyType, keySize);
	}
}
