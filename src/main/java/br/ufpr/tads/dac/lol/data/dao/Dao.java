package br.ufpr.tads.dac.lol.data.dao;

import br.ufpr.tads.dac.ds.data.db.ConnectionFactory;
import br.ufpr.tads.dac.lol.model.Model;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author Lucas
 * @param <T extends Model>
 */
public abstract class Dao<T extends Model> {

    public static Connection con;
    PreparedStatement stmt;
    ResultSet rs;
    CallableStatement call;

    public void open(boolean mustClose) throws Exception {
        if (mustClose) {
            close();
        }
        if (con == null || con.isClosed()) {
            con = ConnectionFactory.getConnection();
        }
    }

    public void open() throws Exception {
        open(false);
    }

    protected void close() {
        try {
            rs.close();
        } catch (Exception e) {
        }

        try {
            stmt.close();
        } catch (Exception e) {
        }

        try {
            con.close();
        } catch (Exception e) {
        }
    }

    public Integer getNextId() throws Exception {
        open();
        String sql = "SELECT value FROM Sequence WHERE id = ?";
        String id = String.format("seq_%s", this.getClass().getSimpleName().toLowerCase());
        stmt = con.prepareStatement(sql);
        stmt.setString(1, id);
        rs = stmt.executeQuery();
        Integer value = null;
        if (rs.next()) {
            value = rs.getInt("value");
            sql = "UPDATE Sequence set value = ? WHERE id = ?";
            stmt = con.prepareStatement(sql);
            stmt.setInt(1, value + 1);
            stmt.setString(2, id);
            stmt.execute();
        }
        close();
        return value;
    }

    @Override
    public void finalize() throws Throwable {
        try {
            close();
        } finally {
            super.finalize();
        }
    }
}
