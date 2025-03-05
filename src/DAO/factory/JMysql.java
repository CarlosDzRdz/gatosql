package DAO.factory;

import java.sql.*;

public class JMysql implements Conn {

    private static JMysql instance = new JMysql();
    private String errMsg;
    private Connection connection;
    int id = 0;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public void conectar() {
        try{
            //Tenemos que registrar si la class existe
            Class.forName("com.mysql.cj.jdbc.Driver");
            //Conectar sisi existe
            connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:8989/tictactoe", "root", "root");
            boolean valid = connection.isValid(5000);
            System.out.println(valid?"Conexion mysql ok":"Conexion mysql Error");

        } catch (java.sql.SQLException sql) {
            setErrMsg(sql.getMessage());
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void Desconectar() {
        try{
            if (!connection.equals(null)) {
                if (!connection.isClosed()) {
                    connection.close();
                    System.out.println("Conexion mysql desconectada");
                }
            }
        }catch (SQLException err){
            setErrMsg(err.getMessage());
        }
    }

    @Override
    public void setErrMsg(String msg) {
        errMsg = msg;
    }

    private JMysql() {
        System.out.println("TEST Mysql okay");
        id = -1;
    }

    public static JMysql getInstance() {
        return instance;
    }

    public String getErrMsg(){
        return errMsg;
    }

    @Override
    public void query(String sql) {
        sql = "SELECT id, marcador, tablero FROM tablero WHERE id = ?";

        try(PreparedStatement smtp = connection.prepareStatement(sql)) {
            if (id != -1){
                smtp.setInt(1, getId());
                ResultSet rs = smtp.executeQuery();

                while (rs.next()) {
                    int _id = rs.getInt("id");
                    String marcador = rs.getString("marcador");
                    String json  = rs.getString("tablero");
                    System.out.printf("id= %d Marcador=%s Tablero %s", id, marcador, json);
                }
            }
        } catch (Exception e) {
            setErrMsg(e.getMessage());
        }

    }

    @Override
    public void add(String estado, String json) {
        String sql = "INSERT INTO tablero (fecha, marcador, tablero) VALUES (now(), ?, ?)";
        try(PreparedStatement smtp = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);){
            smtp.setString(1, estado);
            smtp.setString(2, json);
            smtp.executeUpdate();

            ResultSet rs = smtp.getGeneratedKeys();

            if (rs.next()) {
                id = rs.getInt(1);
            }

        } catch (SQLException err) {
            System.out.println(err.getMessage());
            setErrMsg(err.getMessage());
        }

    }

    @Override
    public void delete() {

    }

    @Override
    public void update() {

    }
}