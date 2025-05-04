package wms;

import view.Login;

public class Main {

    public static void main(String[] args) {
        System.out.println("Application running well");
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Login().setVisible(true);
            }
        });
    }
    
}
