/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication14;

/**
 *
 * @author josem
 */
public class JavaApplication14 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here190, 105, 89, 90, 121, 170, 15, 48, 91, 22, 73, 132, 90 y 80,
        ArbolB a = new ArbolB(4);
        a.insertar(190);
        a.insertar(105);
        a.insertar(89);
        a.insertar(90);
        a.insertar(121);
        a.insertar(170);
        a.insertar(15);
        a.insertar(48);
        a.insertar(91);
        a.insertar(22);
        a.insertar(73);
        a.insertar(132);
        a.insertar(80);
        System.out.println(a.llamarRecorrerInverso());
    }
    
}
