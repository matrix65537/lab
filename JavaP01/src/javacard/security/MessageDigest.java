package javacard.security;

/**
 * The <code>MessageDigest</code> class is the base class for hashing
 * algorithms. Implementations of <code>MessageDigest</code> algorithms must
 * extend this class and implement all the abstract methods.
 * <p>
 * A tear or card reset event resets a <code>MessageDigest</code> object to
 * the initial state (state upon construction).
 * <p>
 * Even if a transaction is in progress, update of intermediate result state in
 * the implementation instance shall not participate in the transaction.
 */

abstract public class MessageDigest
{

    // algorithm options

    /**
     * Message Digest algorithm SHA. The block size used by this algorithm is 64
     * bytes.
     */
    public static final byte ALG_SHA = 1;

    /**
     * Message Digest algorithm MD5. The block size used by this algorithm is 64
     * bytes.
     */
    public static final byte ALG_MD5 = 2;

    /**
     * Message Digest algorithm RIPE MD-160. The block size used by this
     * algorithm is 64 bytes.
     */
    public static final byte ALG_RIPEMD160 = 3;

    /**
     * Message Digest algorithm SHA-256. The block size used by this algorithm
     * is 64 bytes.
     */
    public static final byte ALG_SHA_256 = 4;

    /**
     * Message Digest algorithm SHA-384. The block size used by this algorithm
     * is 128 bytes.
     */
    public static final byte ALG_SHA_384 = 5;

    /**
     * Message Digest algorithm SHA-512. The block size used by this algorithm
     * is 128 bytes.
     */
    public static final byte ALG_SHA_512 = 6;

    /**
     * Length of digest in bytes for SHA
     */
    public static final byte LENGTH_MD5 = (byte) 16;

    /**
     * Length of digest in bytes for RIPE MD-160
     */
    public static final byte LENGTH_RIPEMD160 = (byte) 20;

    /**
     * Length of digest in bytes for SHA-256
     */
    public static final byte LENGTH_SHA = (byte) 20;

    /**
     * Length of digest in bytes for MD5
     */
    public static final byte LENGTH_SHA_256 = (byte) 32;

    /**
     * Length of digest in bytes for SHA-384
     */
    public static final byte LENGTH_SHA_384 = (byte) 48;

    /**
     * Length of digest in bytes for SHA-512
     */
    public static final byte LENGTH_SHA_512 = (byte) 64;

    /**
     * Creates a <code>MessageDigest</code> object instance of the selected
     * algorithm.
     *
     * @param algorithm
     *            the desired message digest algorithm. Valid codes listed in
     *            <code>ALG_*</code> constants above, for example,
     *            {@link #ALG_SHA ALG_SHA}.
     * @param externalAccess
     *            <code>true</code> indicates that the instance will be shared
     *            among multiple applet instances and that the
     *            <code>MessageDigest</code> instance will also be accessed
     *            (via a <code>Shareable</code>. interface) when the owner of
     *            the <code>MessageDigest</code> instance is not the currently
     *            selected applet. If <code>true</code> the implementation
     *            must not allocate CLEAR_ON_DESELECT transient space for
     *            internal data.
     * @return the <code>MessageDigest</code> object instance of the requested
     *         algorithm
     * @exception CryptoException
     *                with the following reason codes:
     *                <ul>
     *                <li><code>CryptoException.NO_SUCH_ALGORITHM</code> if
     *                the requested algorithm or shared access mode is not
     *                supported.
     *                </ul>
     */
    public static final MessageDigest getInstance(byte algorithm,
            boolean externalAccess) throws CryptoException
    {
        CryptoException.throwIt(CryptoException.NO_SUCH_ALGORITHM);
        return null;
    }

    /**
     * Creates a <code>InitializedMessageDigest</code> object instance of the
     * selected algorithm.
     *
     * @param algorithm
     *            the desired message digest algorithm. Valid codes listed in
     *            <code>ALG_*</code> constants above, for example,
     *            {@link #ALG_SHA ALG_SHA}.
     * @param externalAccess
     *            <code>true</code> indicates that the instance will be shared
     *            among multiple applet instances and that the
     *            <code>InitializedMessageDigest</code> instance will also be
     *            accessed (via a <code>Shareable</code>. interface) when the
     *            owner of the <code>InitializedMessageDigest</code> instance
     *            is not the currently selected applet. If <code>true</code>
     *            the implementation must not allocate CLEAR_ON_DESELECT
     *            transient space for internal data.
     * @return the <code>InitializedMessageDigest</code> object instance of
     *         the requested algorithm
     * @exception CryptoException
     *                with the following reason codes:
     *                <ul>
     *                <li><code>CryptoException.NO_SUCH_ALGORITHM</code> if
     *                the requested algorithm or shared access mode is not
     *                supported.
     *                </ul>
     * @since 2.2.2
     */
    public static final InitializedMessageDigest getInitializedMessageDigestInstance(
        byte algorithm, boolean externalAccess) throws CryptoException
    {
        CryptoException.throwIt(CryptoException.NO_SUCH_ALGORITHM);
        return null;
    }

    /**
     * Protected Constructor
     */
    protected MessageDigest()
    {
    }

    /**
     * Gets the Message digest algorithm.
     *
     * @return the algorithm code defined above
     */
    abstract public byte getAlgorithm();

    /**
     * Returns the byte length of the hash.
     *
     * @return hash length
     */
    abstract public byte getLength();

    /**
     * Generates a hash of all/last input data. Completes and returns the hash
     * computation after performing final operations such as padding. The
     * <code>MessageDigest</code> object is reset to the initial state after
     * this call is made.
     * <p>
     * The input and output buffer data may overlap.
     *
     * @param inBuff
     *            the input buffer of data to be hashed
     * @param inOffset
     *            the offset into the input buffer at which to begin hash
     *            generation
     * @param inLength
     *            the byte length to hash
     * @param outBuff
     *            the output buffer, may be the same as the input buffer
     * @param outOffset
     *            the offset into the output buffer where the resulting hash
     *            value begins
     * @return number of bytes of hash output in <code>outBuff</code>
     * @exception CryptoException
     *                with the following reason codes:
     *                <ul>
     *                <li><code>CryptoException.ILLEGAL_USE</code> if the
     *                accumulated message length is greater than the maximum
     *                length supported by the algorithm.
     *                </ul>
     */
    abstract public short doFinal(byte[] inBuff, short inOffset,
                                  short inLength, byte[] outBuff, short outOffset)
    throws CryptoException;

    /**
     * Accumulates a hash of the input data. This method requires temporary
     * storage of intermediate results. In addition, if the input data length is
     * not block aligned (multiple of block size) then additional internal
     * storage may be allocated at this time to store a partial input data
     * block. This may result in additional resource consumption and/or slow
     * performance. This method should only be used if all the input data
     * required for the hash is not available in one byte array. If all of the
     * input data required for the hash is located in a single byte array, use
     * of the <code>doFinal()</code> method is recommended. The
     * <code>doFinal()</code> method must be called to complete processing of
     * input data accumulated by one or more calls to the <code>update()</code>
     * method.
     * <p>
     * Note:
     * <ul>
     * <li><em>If </em><code>inLength</code><em> is 0 this method does nothing.</em>
     * </ul>
     *
     * @param inBuff
     *            the input buffer of data to be hashed
     * @param inOffset
     *            the offset into the input buffer at which to begin hash
     *            generation
     * @param inLength
     *            the byte length to hash
     * @exception CryptoException
     *                with the following reason codes:
     *                <ul>
     *                <li><code>CryptoException.ILLEGAL_USE</code> if the
     *                accumulated message length is greater than the maximum
     *                length supported by the algorithm.
     *                </ul>
     * @see #doFinal doFinal
     */
    abstract public void update(byte[] inBuff, short inOffset, short inLength)
    throws CryptoException;

    /**
     * Resets the <code>MessageDigest</code> object to the initial state for
     * further use.
     */
    abstract public void reset();
}
