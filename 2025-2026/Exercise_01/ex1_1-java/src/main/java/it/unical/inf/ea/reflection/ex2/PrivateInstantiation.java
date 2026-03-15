package it.unical.inf.ea.reflection.ex2;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import it.unical.inf.ea.reflection.ex2.bean.Genre;
import it.unical.inf.ea.reflection.ex2.bean.MusicCd;

public class PrivateInstantiation
{
    public static void main(final String[] args)
    {
        try
        {
            instancePrivateConstructor();
            invokePrivateMethod();
            changePrivateValues();
        }
        catch (final Exception e)
        {
            e.printStackTrace();
        }
    }

    private static void changePrivateValues()
            throws SecurityException, NoSuchFieldException, IllegalArgumentException, IllegalAccessException
    {
        System.out.println("CHANGE PRIVATE VALUES");
        final MusicCd musicCd = new MusicCd("Pink Floyd", "The wall", "code_01", Genre.ROCK, 26);

        final Class<? extends MusicCd> clazz = musicCd.getClass();

        final Field field = clazz.getDeclaredField("codeSiae");
        field.setAccessible(true);
        field.set(musicCd, "000");

        System.out.println(musicCd);
        System.out.println();
    }

    private static void instancePrivateConstructor() throws ClassNotFoundException, SecurityException, NoSuchMethodException,
            IllegalArgumentException, InstantiationException, IllegalAccessException, InvocationTargetException
    {
        System.out.println("PRIVATE CONSTRUCTOR WITH PARAMETER");
        @SuppressWarnings("unchecked")
        final Class<MusicCd> clazz = (Class<MusicCd>) Class.forName("it.unical.inf.ea.reflection.ex2.bean.MusicCd");
        final Class<? >[] parametersType = new Class<? >[] { String.class, String.class, int.class };
        final Constructor<MusicCd> constructor = clazz.getDeclaredConstructor(parametersType);

        final Object[] parameter = new Object[] { "Vasco Rossi", "Canzoni per me", 8 };
        constructor.setAccessible(true); // rendo accessibile il costruttore anche se privato
        final MusicCd musicCd = constructor.newInstance(parameter);
        System.out.println(musicCd);
        System.out.println();
    }

    private static void invokePrivateMethod() throws  SecurityException, NoSuchMethodException,
            IllegalArgumentException, IllegalAccessException, InvocationTargetException
    {
        System.out.println("INVOKE PRIVATE METHODS");
        final MusicCd musicCd = new MusicCd("Pink Floyd", "The wall", "code_01", Genre.ROCK, 26);

        final Class<? extends MusicCd> clazz = musicCd.getClass();

        final Class<? >[] parameterTypes = new Class<? >[] { int.class };
        final Method method = clazz.getDeclaredMethod("initializeTracks", parameterTypes);
        method.setAccessible(true);  // rendo accessibile il metodo anche se privato

        final Object[] parameters = new Object[] { 0 };
        method.invoke(musicCd, parameters);
        System.out.println(musicCd.getNumTracks());
        System.out.println();
    }

}
