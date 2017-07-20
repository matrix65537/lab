import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.lang.reflect.UndeclaredThrowableException;
import org.laoguo.dproxy.Subject;

public final class $Proxy_01 extends Proxy
    implements Subject
{
    private static Method m1;
    private static Method m3;
    private static Method m0;
    private static Method m2;

    public $Proxy_01(InvocationHandler paramInvocationHandler)
    throws
    {
        super(paramInvocationHandler);
    }

    public final boolean equals(Object paramObject)
    throws
    {
        try
        {
            return ((Boolean)this.h.invoke(this, m1, new Object[] { paramObject })).booleanValue();
        }
        catch (RuntimeException localRuntimeException)
        {
            throw localRuntimeException;
        }
        catch (Throwable localThrowable)
        {
        }
        throw new UndeclaredThrowableException(localThrowable);
    }

    public final void request()
    throws
    {
        try
        {
            this.h.invoke(this, m3, null);
            return;
        }
        catch (RuntimeException localRuntimeException)
        {
            throw localRuntimeException;
        }
        catch (Throwable localThrowable)
        {
        }
        throw new UndeclaredThrowableException(localThrowable);
    }

    public final int hashCode()
    throws
    {
        try
        {
            return ((Integer)this.h.invoke(this, m0, null)).intValue();
        }
        catch (RuntimeException localRuntimeException)
        {
            throw localRuntimeException;
        }
        catch (Throwable localThrowable)
        {
        }
        throw new UndeclaredThrowableException(localThrowable);
    }

    public final String toString()
    throws
    {
        try
        {
            return (String)this.h.invoke(this, m2, null);
        }
        catch (RuntimeException localRuntimeException)
        {
            throw localRuntimeException;
        }
        catch (Throwable localThrowable)
        {
        }
        throw new UndeclaredThrowableException(localThrowable);
    }

    static
    {
        try
        {
            m1 = Class.forName("java.lang.Object").getMethod("equals", new Class[] { Class.forName("java.lang.Object") });
            m3 = Class.forName("org.laoguo.dproxy.Subject").getMethod("request", new Class[0]);
            m0 = Class.forName("java.lang.Object").getMethod("hashCode", new Class[0]);
            m2 = Class.forName("java.lang.Object").getMethod("toString", new Class[0]);
            return;
        }
        catch (NoSuchMethodException localNoSuchMethodException)
        {
            throw new NoSuchMethodError(localNoSuchMethodException.getMessage());
        }
        catch (ClassNotFoundException localClassNotFoundException)
        {
        }
        throw new NoClassDefFoundError(localClassNotFoundException.getMessage());
    }
}
