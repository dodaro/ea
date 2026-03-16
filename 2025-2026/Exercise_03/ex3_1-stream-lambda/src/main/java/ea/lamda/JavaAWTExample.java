package ea.lamda;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class JavaAWTExample {

    public static void main(String[] args) {
        Button button  = new Button();
        button.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Button pressed");
            }
        });

        button.addActionListener( ( ActionEvent e) -> {System.out.println("Button pressed");});

        button.addActionListener( e -> System.out.println("Button pressed"));

    }

}
