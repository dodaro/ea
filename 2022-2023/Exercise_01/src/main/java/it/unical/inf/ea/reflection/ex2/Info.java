package it.unical.inf.ea.reflection.ex2;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Set;
import java.util.stream.Collectors;

public class Info
{

    public static void main(final String[] args)
    {
        try
        {
            //            final Class<? > clazz = Class.forName("java.util.List");
            //            final Class<? > clazz = Class.forName("java.util.ArrayList");
            //            final Class<? > clazz = Class.forName("java.lang.String");
            final Class<? > clazz = Class.forName("it.unical.inf.ea.reflection.ex2.bean.MusicCd");
            printInterfaces(clazz);
            printSuperclass(clazz);
            printSuperclasses(clazz);
            printConstructors(clazz);
            printMethods(clazz);

            printDeclaredFields(clazz);
            printDeclaredFields(clazz.getSuperclass());


            findAllClassesUsingClassLoader("it.unical.inf.ea.reflection.ex2.bean").forEach(x->System.out.println(x));

        }
        catch (final ClassNotFoundException e)
        {
            e.printStackTrace();
        }
    }



    private static void printConstructors(final Class<? > clazz)
    {
        System.out.println(String.format("THE CONSTRUCTORS OF %s ARE:", clazz.getName()));
        final Constructor<? >[] constructors = clazz.getConstructors(); // recuper tutti i costruttori pubblici della classe clazz
        //        final Constructor<? >[] constructors = clazz.getDeclaredConstructors();
        for (final Constructor<? > constructor : constructors)
        {
            final String modifier = Modifier.toString(constructor.getModifiers()); // per ogni costruttore recupero il suo modificatore
            System.out.print("\t- " + modifier + " " + clazz.getName() + "( ");
            final Class<? >[] parameterTypes = constructor.getParameterTypes(); // per ogni costruttore recupero i tipi dei parametri
            for (final Class<? > class1 : parameterTypes)
            {
                System.out.print(class1.getName() + " ");
            }
            System.out.println(")");
        }
        System.out.println();
    }

    private static void printDeclaredFields(final Class<? > clazz)
    {
        System.out.println();
        System.out.println("DECLARED FIELDS OF " + clazz);
        final Field[] fields = clazz.getDeclaredFields(); // recupero tutt i campi della classe clazz
        for (final Field field : fields)
        {
            System.out.print(field.getType() + " -> " + field.getName());
            final Annotation[] annotations = field.getAnnotations(); // per ogni campo recupero le annotazioni
            for (final Annotation annotation : annotations)
            {
                System.out.print(" @" + annotation.annotationType().getSimpleName());
            }
            System.out.println();
        }
    }

    private static void printMethods(final Class<? > clazz)
    {
        System.out.println(String.format("THE METHODS OF %s ARE:", clazz.getName()));
        final Method[] methods = clazz.getMethods(); // recupero tutti i metodi di clazz
        //        final Method[] methods = clazz.getDeclaredMethods();
        for (final Method method : methods)
        {
            final String methodModifier = Modifier.toString(method.getModifiers());
            final String methodName = method.getName();
            System.out.print("\t- " + methodModifier + " " + methodName + "( ");
            final Class<? >[] parameterTypes = method.getParameterTypes();
            for (final Class<? > class1 : parameterTypes)
            {
                System.out.print(class1.getName() + " ");
            }
            System.out.println(")");
        }
        System.out.println();
    }

    private static void printSuperclass(final Class<? > clazz)
    {
        System.out.println(String.format("THE SUPERCLASS OF %s IS:", clazz.getName()));
        final Class<? > superclass = clazz.getSuperclass(); // recupero la super classe di clazz
        if (superclass != null)
        {
            System.out.println("\t- " + superclass.getName());
        }
        else
        {
            System.out.println("The class is Object or a Interface or a Primitive Type or Void");
        }
        System.out.println();
    }

    private static void printSuperclasses(final Class<? > clazz)
    {
        System.out.println(String.format("THE SUPERCLASSES OF %s ARE:", clazz.getName()));
        Class<? > superclass = clazz.getSuperclass(); // recupero la super classe di clazz e itero fino alla classe Object
        while (superclass != null)
        {
            System.out.println("\t- " + superclass.getName());
            superclass = superclass.getSuperclass();
        }
        System.out.println();
    }

    static void printInterfaces(final Class<? > clazz)
    {
        System.out.println(String.format("THE INTERFACES OF %s ARE:", clazz.getName()));
        final Class<? >[] interfaces = clazz.getInterfaces(); // recupero tutte le interfacce della classe clazz
        for (final Class<? > class1 : interfaces)
        {
            System.out.println("\t- " + class1.getName());
        }
        System.out.println();
    }

    public static Set<Class> findAllClassesUsingClassLoader(String packageName) {
        InputStream stream = ClassLoader.getSystemClassLoader()
            .getResourceAsStream(packageName.replaceAll("[.]", "/"));
        BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
        return reader.lines()
            .filter(line -> line.endsWith(".class"))
            .map(line -> getClass(line, packageName))
            .collect(Collectors.toSet());
    }

    private static Class getClass(String className, String packageName) {
        try {
            return Class.forName(packageName + "."
                + className.substring(0, className.lastIndexOf('.')));
        } catch (ClassNotFoundException e) {
            // handle the exception
        }
        return null;
    }
}
