package br.ufpr.tads.dac.lol.model;

/**
 *
 * @author Lucas
 */
public class Admin implements Authenticable {

    private static Admin instance;

    private final String userName;
    private final String password;

    public static Admin getInstance() {
        if (Admin.instance == null) {
            Admin.instance = new Admin("admin", "admin");
        }
        return Admin.instance;
    }

    private Admin(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    @Override
    public String getUsername() {
        return userName;
    }

    @Override
    public void setUsername(String userName) {
        throw new UnsupportedOperationException("Not supported");
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public void setPassword(String password) {
        throw new UnsupportedOperationException("Not supported");
    }
}
