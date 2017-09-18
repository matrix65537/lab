package javacard.security;

/**
 * The <code>Key</code> interface is the base interface for all keys.
 * <p>
 * <p>
 * A <code>Key</code> object sets its initialized state to true only when all
 * the associated <code>Key</code> object parameters have been set at least
 * once since the time the initialized state was set to false.
 * <p>
 * A newly created <code>Key</code> object sets its initialized state to
 * false. Invocation of the <code>clearKey()</code> method sets the
 * initialized state to false. A key with transient key data sets its
 * initialized state to false on the associated clear events.
 *
 * @see KeyBuilder KeyBuilder
 */
public interface Key
{

    /**
     * Reports the initialized state of the key. Keys must be initialized before
     * being used.
     * <p>
     * A <code>Key</code> object sets its initialized state to true only when
     * all the associated <code>Key</code> object parameters have been set at
     * least once since the time the initialized state was set to false.
     * <p>
     * A newly created <code>Key</code> object sets its initialized state to
     * false. Invocation of the <code>clearKey()</code> method sets the
     * initialized state to false. A key with transient key data sets its
     * initialized state to false on the associated clear events.
     *
     * @return <code>true</code> if the key has been initialized
     */
    boolean isInitialized();

    /**
     * Clears the key and sets its initialized state to false.
     */
    void clearKey();

    /**
     * Returns the key interface type.
     *
     * @return the key interface type. Valid codes listed in <code>TYPE_*</code>
     *         constants See
     *         {@link KeyBuilder#TYPE_DES_TRANSIENT_RESET TYPE_DES_TRANSIENT_RESET}.
     *         <p>
     * @see KeyBuilder KeyBuilder
     */
    byte getType();

    /**
     * Returns the key size in number of bits.
     *
     * @return the key size in number of bits
     */
    short getSize();

}
