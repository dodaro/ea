package it.unical.inf.ea.annotations.ex5;

public class InheritanceApp {

    class BaseClass {
        @MethodInfo(date = "2024-03-04", comments = "Esempio di metodo in BaseClass")
        public void metodoBase() {
            System.out.println("Metodo in BaseClass");
        }
    }

    class SubClass extends BaseClass {
        @Override
        public void metodoBase() {
            System.out.println("Metodo sovrascritto in SubClass");
        }
    }

    public static void main(String[] args) throws NoSuchMethodException{

        MethodInfo metodoInfoSubClass = SubClass.class.getMethod("metodoBase").getAnnotation(MethodInfo.class);
        if (metodoInfoSubClass != null) {
            System.out.println("Autore: " + metodoInfoSubClass.author());
            System.out.println("Data: " + metodoInfoSubClass.date());
            System.out.println("Revisione: " + metodoInfoSubClass.revision());
            System.out.println("Commenti: " + metodoInfoSubClass.comments());
        } else {
            System.out.println("Nessuna annotazione trovata nella sottoclasse.");
        }


        MethodInfo metodoInfoBaseClass = BaseClass.class.getMethod("metodoBase").getAnnotation(MethodInfo.class);
        if (metodoInfoBaseClass != null) {
            System.out.println("Autore: " + metodoInfoBaseClass.author());
            System.out.println("Data: " + metodoInfoBaseClass.date());
            System.out.println("Revisione: " + metodoInfoBaseClass.revision());
            System.out.println("Commenti: " + metodoInfoBaseClass.comments());
        } else {
            System.out.println("Nessuna annotazione trovata nella superclasse.");
        }
    }
}
