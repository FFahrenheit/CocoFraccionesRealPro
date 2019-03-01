package cocofraccionesrealpro;

import java.util.Scanner;

/**
 * Este programa lee ecuaciones y devuelve
 * el resultado en string
 * También se roba mi salud mental y genera
 * deseos de suicidio.
 * @author ivxn
 */
public class CocoFraccionesRealPro {

    /**
     * @param args the command line arguments
     */
    
    public static Scanner input;
    
    public static void main(String[] args) 
    {
        input = new Scanner(System.in);
        System.out.print("Ingrese su operacion: ");
        String string = input.nextLine();
        Fraction fraction = new Fraction(string);
        System.out.print("El resultado es: ");
        System.out.println(fraction.calculate());
    }
    
}
