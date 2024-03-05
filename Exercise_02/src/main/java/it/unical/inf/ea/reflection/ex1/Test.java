package it.unical.inf.ea.reflection.ex1;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

public class Test
{
    public static void main(final String[] args)
    {
        try
        {
            final Class<? extends Father> sonClass = (Class<? extends Father>) Class
                    .forName("it.unical.inf.ea.reflection.ex1.Son");

            final Constructor<? extends Father> constructor = sonClass.getDeclaredConstructor(new Class<? >[] {});
            constructor.setAccessible(true);
            final Father son = constructor.newInstance(new Object[] {});

            final Method method = sonClass.getDeclaredMethod("method", new Class<? >[] {});
            method.setAccessible(true);
            method.invoke(son, new Object[] {});
        }
        catch (final Exception e)
        {
            e.printStackTrace();
        }
    }
}
