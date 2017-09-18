package javacard.security;

/**
 * The <CODE>KeyAgreement</CODE> class is the base class for key agreement
 * algorithms such as Diffie-Hellman and EC Diffie-Hellman [IEEE P1363].
 * Implementations of <CODE>KeyAgreement</CODE> algorithms must extend this
 * class and implement all the abstract methods.
 *
 * A tear or card reset event resets an initialized <CODE>KeyAgreement</CODE>
 * object to the state it was in when previously initialized via a call to
 * <code>init()</code>.
 *
 * @version 1.0
 */
public abstract class KeyAgreement extends Object
{

    /**
     * Elliptic curve secret value derivation primitive, Diffie-Hellman version,
     * as per [IEEE P1363].
     */
    public static final byte ALG_EC_SVDP_DH = 1;

    /**
     * Elliptic curve secret value derivation primitive, Diffie-Hellman version,
     * with cofactor multiplication, as per [IEEE P1363]. (output value is to be
     * equal to that from <CODE>ALG_EC_SVDP_DH</CODE>)
     */
    public static final byte ALG_EC_SVDP_DHC = 2;

    /**
     * Protected constructor.
     */
    protected KeyAgreement()
    {
    }

    // ---- Methods ----

    /**
     * Creates a <CODE>KeyAgreement</CODE> object instance of the selected
     * algorithm.
     *
     * @param algorithm
     *            the desired key agreement algorithm Valid codes listed in
     *            <CODE>ALG_*</CODE> constants above, for example,
     *            {@link #ALG_EC_SVDP_DH ALG_EC_SVDP_DH}.
     * @param externalAccess
     *            if <code>true</code> indicates that the instance will be
     *            shared among multiple applet instances and that the
     *            <code>KeyAgreement</code> instance will also be accessed
     *            (via a <code>Shareable</code> interface) when the owner of
     *            the <code>KeyAgreement</code> instance is not the currently
     *            selected applet. If <code>true</code> the implementation
     *            must not allocate <code>CLEAR_ON_DESELECT</code> transient
     *            space for internal data.
     * @return the KeyAgreement object instance of the requested algorithm
     * @exception CryptoException
     *                with the following reason codes:
     *                <ul>
     *                <li><code>CryptoException.NO_SUCH_ALGORITHM</code> if
     *                the requested algorithm or shared access mode is not
     *                supported.
     *                </ul>
     */
    public static final KeyAgreement getInstance(byte algorithm,
            boolean externalAccess) throws CryptoException
    {
        switch (algorithm)
        {
        default:
            CryptoException.throwIt(CryptoException.NO_SUCH_ALGORITHM);
        }
        return null;
    }

    /**
     * Initializes the object with the given private key.
     *
     * @param privKey
     *            the private key
     * @exception CryptoException
     *                with the following reason codes:
     *                <ul>
     *                <li><code>CryptoException.ILLEGAL_VALUE</code> if the
     *                input key type is inconsistent with the
     *                <code>KeyAgreement</code> algorithm, for example, if the
     *                <code>KeyAgreement</code> algorithm is
     *                <code>ALG_EC_SVDP_DH</code> and the key type is
     *                <code>TYPE_RSA_PRIVATE</code>, or if
     *                <code>privKey</code> is inconsistent with the
     *                implementation.</li>
     *                <li><code>CryptoException.UNINITIALIZED_KEY</code> if
     *                <code>privKey</code> is uninitialized, or if the
     *                <code>KeyAgreement</code> algorithm is set to
     *                <CODE>ALG_EC_SVDP_DHC</CODE> and the cofactor, K, has
     *                not been successfully initialized since the time the
     *                initialized state of the key was set to false.</li>
     *                </ul>
     */
    public abstract void init(PrivateKey privKey) throws CryptoException;

    /**
     * Gets the KeyAgreement algorithm.
     *
     * @return the algorithm code defined above
     */
    public abstract byte getAlgorithm();

    /**
     * Generates the secret data as per the requested algorithm using the
     * <code>PrivateKey</code> specified during initialization and the public
     * key data provided.
     *
     * Note that in the case of the algorithms <CODE>ALG_EC_SVDP_DH</CODE> and
     * <CODE>ALG_EC_SVDP_DHC</CODE> the public key data provided should be the
     * public elliptic curve point of the second party in the protocol,
     * specified as per ANSI X9.62. A specific implementation need not support
     * the compressed form, but must support the uncompressed form of the point.
     *
     * @param publicData
     *            buffer holding the public data of the second party
     * @param publicOffset
     *            offset into the publicData buffer at which the data begins
     * @param publicLength
     *            byte length of the public data
     * @param secret
     *            buffer to hold the secret output
     * @param secretOffset
     *            offset into the secret array at which to start writing the
     *            secret
     * @return byte length of the secret
     * @exception CryptoException
     *                with the following reason codes:
     *                <ul>
     *                <li><code>CryptoException.ILLEGAL_VALUE</code> if the
     *                <code>publicData</code> data format is incorrect, or if
     *                the <code>publicData</code> data is inconsistent with
     *                the <code>PrivateKey</code> specified during
     *                initialization.
     *                <li><code>CryptoException.INVALID_INIT</code> if this
     *                <code>KeyAgreement</code> object is not initialized.
     *                </ul>
     */
    public abstract short generateSecret(byte[] publicData, short publicOffset,
                                         short publicLength, byte[] secret, short secretOffset)
    throws CryptoException;

}
