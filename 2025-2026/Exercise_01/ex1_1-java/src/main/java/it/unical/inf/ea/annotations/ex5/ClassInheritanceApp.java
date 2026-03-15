package it.unical.inf.ea.annotations.ex5;

public class ClassInheritanceApp {

    @ClassInfo(date = "2024-03-04", comments = "Esempio di classe BaseClass")
    static class BaseClass {
        public void metodoBase() {
            System.out.println("Metodo in BaseClass");
        }
    }

    static class SubClass extends BaseClass {
        @Override
        public void metodoBase() {
            System.out.println("Metodo sovrascritto in SubClass");
        }
    }

    public static void main(String[] args) {

        // Annotazione sulla classe base
        ClassInfo classInfoBaseClass = BaseClass.class.getAnnotation(ClassInfo.class);
        if (classInfoBaseClass != null) {
            System.out.println("=== BaseClass ===");
            System.out.println("Autore: "    + classInfoBaseClass.author());
            System.out.println("Data: "      + classInfoBaseClass.date());
            System.out.println("Revisione: " + classInfoBaseClass.revision());
            System.out.println("Commenti: "  + classInfoBaseClass.comments());
        } else {
            System.out.println("Nessuna annotazione trovata in BaseClass.");
        }

        System.out.println("---");

        // @Inherited: l'annotazione viene ereditata dalla sottoclasse a livello di CLASSE
        ClassInfo classInfoSubClass = SubClass.class.getAnnotation(ClassInfo.class);
        if (classInfoSubClass != null) {
            System.out.println("=== SubClass (ereditata via @Inherited) ===");
            System.out.println("Autore: "    + classInfoSubClass.author());
            System.out.println("Data: "      + classInfoSubClass.date());
            System.out.println("Revisione: " + classInfoSubClass.revision());
            System.out.println("Commenti: "  + classInfoSubClass.comments());
        } else {
            System.out.println("Nessuna annotazione trovata in SubClass.");
        }

        System.out.println("---");

        // @Inherited NON funziona sui metodi: questa lookup restituisce sempre null
        try {
            ClassInfo metodoInfoSubClass = SubClass.class
                    .getMethod("metodoBase")
                    .getAnnotation(ClassInfo.class);

            if (metodoInfoSubClass != null) {
                System.out.println("Annotazione trovata sul metodo (inatteso).");
            } else {
                System.out.println("=== metodoBase() in SubClass ===");
                System.out.println("Nessuna annotazione sul metodo: @Inherited");
                System.out.println("funziona solo a livello di classe, non di metodo.");
            }
        } catch (NoSuchMethodException e) {
            System.out.println("Metodo non trovato: " + e.getMessage());
        }
    }
}