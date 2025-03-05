package Presentacion.tictactoe;

import java.util.Arrays;
import java.util.Random;

import static Presentacion.utilis.Utilis.leerEntero;

public class Gato {

    private char[][] tablero;
    private Random random;

    public Gato() {
        tablero = new char[3][3];

        Arrays.fill(tablero[0],'*');
        Arrays.fill(tablero[1],'*');
        Arrays.fill(tablero[2],'*');

    }

    public void play (){
        Random random = new Random();
        boolean jugando_cpu = random.nextBoolean();
        int ren = 0, col = 0, turnos = 1;
        char ganador = ' ';

        imprimirTablero(tablero, turnos);

        do {
            if (jugando_cpu){
                do {
                    ren = random.nextInt(0,3);
                    col = random.nextInt(0,3);
                }while(tablero[ren][col] == 'X' || tablero[ren][col] == '0');
                tablero[ren][col] = '0';//CPU jugando
            }else {
                do {
                    ren = leerEntero("Renglon");
                    col = leerEntero("Colon");
                    if (tablero[ren][col] != '*'){
                        System.out.printf("El espacio ya esta ocupado\n");
                        imprimirTablero(tablero, turnos);
                    }else{
                        tablero[ren][col] = 'X';
                        break;
                    }
                    //tablero[ren][col] != 'X' && tablero[ren][col] != '0'
                }while (true);
            }

            imprimirTablero(tablero, turnos);

            if (turnos++ >= 5){
                ganador = obtenerGanador(tablero);
            }

            jugando_cpu = !jugando_cpu;

        }while (turnos != 9 && ganador == ' ');

        if (ganador == ' '){
            System.out.println("Empate!");
        }else{
            System.out.println("Gano: "+ganador);
        }
    }

    private char obtenerGanador(char [][] tablero){
        boolean rst = false;
        char ficha = ' ';
        for (int i = 0; i < 3; i++){
            if (sonIguales(tablero [i][0], tablero [i][1], tablero [i][2])){
                ficha = tablero[i][0];
                return ficha;
            }
            if (sonIguales(tablero [0][i], tablero [1][i], tablero [2][i])){
                return tablero[0][i];
            }
        }

        if (sonIguales(tablero [0][0], tablero [0][1], tablero [0][2])){
            ficha = tablero[0][0];
        }

        if (sonIguales(tablero [0][2], tablero [1][1], tablero [2][0])){
            ficha = tablero[0][2];
        }

        return ficha;
    }

    private boolean sonIguales(char a, char b, char c){
        if (a == '*' || b == '*' || c == '*'){
            return false;
        }

        return a == b && b == c;
    }

    public static void imprimirTablero(char[][] tablero, int turno){
        System.out.println("Tturno: "+turno);
        System.out.printf("      1    2    3\n");
        for (int i = 0; i < tablero.length; i++){
            System.out.printf("%d ", (i+1));
            for (int j = 0; j < tablero[i].length; j++){
                System.out.printf("    %c",tablero[i][j]);
            }
            System.out.println("");
        }
    }
}
