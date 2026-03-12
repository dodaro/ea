package it.unical.inf.ea.reflection.ex2;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import it.unical.inf.ea.reflection.ex2.bean.Genre;
import it.unical.inf.ea.reflection.ex2.bean.Cd;
import it.unical.inf.ea.reflection.ex2.bean.MusicCd;

public class Instantiation
{
    public static void main(final String[] args)
    {
        try
        {
            instanceDefaultConstructor();
            instanceDefaultConstructorIsAssignableFrom();
            instanceConstructor();
            invokeMethod();
            changeValues();
        }
        catch (final Exception e)
        {
            e.printStackTrace();
        }
    }

    static void changeValues() throws SecurityException, NoSuchFieldException, IllegalArgumentException, IllegalAccessException
    {
        System.out.println("CHANGE VALUES");
        final MusicCd musicCd = new MusicCd("Queen", "The wall", "code_01", Genre.ROCK, 26);

        final Class<? extends MusicCd> clazz = musicCd.getClass();

        final Field[] fields = clazz.getFields();
        for (final Field field : fields)
        {
            System.out.println(field.getType() + " -> " + field.getName());
        }

        Field field = clazz.getField("author");
        field.set(musicCd, "Pink Floyd");
        System.out.println(musicCd);

        field = clazz.getField("CONST");
        System.out.println("CONST: " + field.getInt(null));

        System.out.println();
    }

    static void instanceConstructor() throws ClassNotFoundException, SecurityException, NoSuchMethodException,
            IllegalArgumentException, InstantiationException, IllegalAccessException, InvocationTargetException
    {
        System.out.println("CONSTRUCTOR WITH PARAMETER");
        @SuppressWarnings("unchecked")
        final Class<MusicCd> clazz = (Class<MusicCd>) Class.forName("it.unical.inf.ea.reflection.ex2.bean.MusicCd"); // recupero l'oggetto class di MusicCd
        final Class<? >[] parametersType = new Class<? >[] { String.class, String.class, String.class, Genre.class, int.class }; // mi instanzio un array di classe che rappresentano i tipi input
        // del costruttore
        final Constructor<MusicCd> constructor = clazz.getConstructor(parametersType); // mi creo un oggetto Constructor a partire dall'array dei tipi

        final Object[] parameter = new Object[] { "Pink Floyd", "The wall", "code_01", Genre.ROCK, 26 }; // mi instanzio un array di Object che sono i valori con cui costruire  l'oggetto
        final MusicCd musicCd = constructor.newInstance(parameter); // instanzio un nuove oggetto di MusicCd
        System.out.println(musicCd);
        System.out.println();
    }

    static void instanceDefaultConstructor() throws ClassNotFoundException, InstantiationException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        System.out.println("DEFAULT CONSTRUCTOR");
        @SuppressWarnings("unchecked")
        final Class<MusicCd> clazz = (Class<MusicCd>) Class.forName("it.unical.inf.ea.reflection.ex2.bean.MusicCd"); // recupero una certa clase
        final MusicCd musicCd = clazz.getDeclaredConstructor().newInstance(); // a partire dall'oggetto della classe MusicCd instanzio un oggetto a partire dal costruttore vuoto
        System.out.println(musicCd);
        System.out.println();
    }

    static void instanceDefaultConstructorIsAssignableFrom() throws ClassNotFoundException, InstantiationException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        System.out.println("DEFAULT CONSTRUCTOR isAssignableFrom");
        final Class<? > clazz = Thread.currentThread().getContextClassLoader()
                .loadClass("it.unical.inf.ea.reflection.ex2.bean.MusicCd"); //recupero la classe a partire dal classloader

        if (Cd.class.isAssignableFrom(clazz)) // se la classe è assegnabile alla classe Cd proseguo e assegno
        {
            final Cd instance = (Cd) clazz.getDeclaredConstructor().newInstance();
            System.out.println(instance);
        }
        System.out.println();
    }

    static void invokeMethod() throws SecurityException, NoSuchMethodException, IllegalArgumentException,
            IllegalAccessException, InvocationTargetException
    {
        System.out.println("INVOKE METHODS");
        final MusicCd musicCd = new MusicCd("Pink Floyd", "The wall", "code_01", Genre.ROCK, 26);

        @SuppressWarnings("unchecked")
        final Class<MusicCd> clazz = (Class<MusicCd>) musicCd.getClass();
        Class<? >[] parameterTypes = new Class<? >[] { int.class, String.class }; // mi instanzio un array di Class che rappresentano i parametri del metodo
        Method method = clazz.getMethod("record", parameterTypes);

        Object[] parameters = new Object[] { 1, "blablablabla bla blablabla" }; // mi instanzio un array di Object che rappresentano i valori dei parametri del metodo
        final boolean r = (Boolean) method.invoke(musicCd, parameters);  // invoco il metodo record per l'oggetto musicCd
        System.out.println(r);

        parameterTypes = new Class<? >[] { int.class };
        method = clazz.getMethod("getSong", parameterTypes);

        parameters = new Object[] { 1 };
        final String song = (String) method.invoke(musicCd, parameters);  // invoco il metodo getSong per l'oggetto musicCd
        System.out.println(song);
        System.out.println();
    }
}
