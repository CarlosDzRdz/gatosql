package DAO;

import DAO.factory.*;
import com.google.gson.Gson;
import com.google.gson.JsonArray;

public class Dao {

    private  ConnFactory connFactory; // objeeto patter demo
    private  Conn conexion;
    private String ErrMsg;

    public String getErrMsg() {
        return ErrMsg;
    }

    public void setErrMsg(String errMsg) {
        ErrMsg = errMsg;
    }

    private ConnFactory getConnectionFactory(Database db) {
        switch (db){
            case JMysql -> {
                return new MysqlConnectionFactory();
            }
            case JSqlite -> {
                return new SqliteConnectionFactory();
            }
            default ->  {
                return new FirebaseConnectionFactory();
            }
        }
    } // End connFactory

    private void star(){
        try{
            conexion = connFactory.getConnection();
            conexion.conectar();
            //conexion.Desconectar();
        } catch (Exception e) {
            setErrMsg(conexion.getErrMsg());
        }
    }

    private void close(){
        try{
            conexion.Desconectar();
        } catch (Exception e) {
            setErrMsg(conexion.getErrMsg());
        }
    }

    public void add(String estado, char[][]tablero) {
        try {
            estado = "Turno: " + estado;
            Gson gson = new Gson();
            JsonArray json = gson.toJsonTree(tablero).getAsJsonArray();
            conexion.add(estado, gson.toJson(json).toString());
        } catch (Exception e) {
            setErrMsg(e.getMessage());
        }
    }

    public Dao(Database db) {
        connFactory = getConnectionFactory(db);
        star();
    }

    public Dao() {
        this(Database.JMysql);
    }

    public void showData(){
        try{
            conexion.query("");
        } catch (Exception e) {
            setErrMsg(conexion.getErrMsg());
        }
    }

}
