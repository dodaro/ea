package it.unical.inf.ea.reflection.ex1;

import java.util.ArrayList;
import java.util.List;

class Son extends Father
{
    @Override
    void method()
    {
        List<Integer> l = new ArrayList<Integer>();

        List<? extends Father> ll = new ArrayList();

        System.out.println("Hello");
    }
}
