package it.unical.inf.ea.exe;

import java.net.URL;
import java.net.URLClassLoader;

public class ExtensibleClassLoader extends URLClassLoader
{
    public ExtensibleClassLoader(final ClassLoader parent)
    {
        super(new URL[0], parent);
    }

    @Override
    public void addURL(final URL url)
    {
        super.addURL(url);
    }
}
