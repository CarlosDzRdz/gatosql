package DAO.factory;

public class JSqlite implements Conn{

    private static JSqlite instance = new JSqlite();

    @Override
    public void conectar() {

    }

    @Override
    public void Desconectar() {

    }

    @Override
    public void setErrMsg(String msg) {

    }

    @Override
    public String getErrMsg() {
        return "";
    }

    @Override
    public void query(String sql) {

    }

    @Override
    public void add(String estado, String json) {

    }

    @Override
    public void delete() {

    }

    @Override
    public void update() {

    }

    private JSqlite() {
        System.out.println("TEST JSqlite okay");
    }

    public static JSqlite getInstance() {
        return instance;
    }
}
