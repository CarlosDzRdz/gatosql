package Presentacion.utilis;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Utilis {
    public static int leerEntero(String msg){
        int numero = 0;
        InputStreamReader in = new InputStreamReader(System.in);
        BufferedReader leer = new BufferedReader(in);

        do {
            try{
                System.out.printf("Ingrese el numero de %s:\n", msg);
                numero = Integer.parseInt(leer.readLine());
                if (numero <= 0 || numero > 3){
                    System.out.printf("El numero esta fuera de rango");
                }
            } catch (Exception e){
                System.out.printf("Error: la entrada no es valida");
            }
        }while(numero < 0 || numero > 3);
        return numero-1;
    }
}
