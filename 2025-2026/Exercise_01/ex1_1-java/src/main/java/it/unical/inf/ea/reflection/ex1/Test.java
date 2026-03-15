package it.unical.inf.ea.reflection.ex1;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

public class Test
{
    public static void main(final String[] args)
    {
        try
        {
            final Class sonClass = Class.forName("it.unical.inf.ea.reflection.ex1.Son");

            final Constructor constructor = sonClass.getDeclaredConstructor(new Class<? >[] {});
            constructor.setAccessible(true);
            final Father father = (Father) constructor.newInstance(new Object[] {});

            final Method method = sonClass.getDeclaredMethod("method", new Class<? >[] {});
            method.setAccessible(true);
            method.invoke(father, new Object[] {});
        }
        catch (final Exception e)
        {
            e.printStackTrace();
        }
    }
}
