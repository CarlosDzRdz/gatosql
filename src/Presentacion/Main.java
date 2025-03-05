package Presentacion;

import DAO.Dao;
import DAO.factory.Database;
import Negocio.LogDb;
import Presentacion.Obeserver.Jugador;
import Presentacion.Obeserver.Tablero;

public class Main {
    public static void main(String[] args) {

        Tablero tablero = new Tablero();
        Jugador playerone = new Jugador();
        LogDb negocio = new LogDb();

        tablero.attach(playerone);
        tablero.attach(negocio);
        tablero.play();

        negocio.showRun();

    }//fin main
}