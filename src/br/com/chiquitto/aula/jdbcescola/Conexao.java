package br.com.chiquitto.aula.jdbcescola;

import org.sqlite.JDBC;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author chiquitto
 */
public class Conexao {

    private static Connection conexao;
    private static String url;

    public static Connection getConexao() {
        if (conexao == null) {
            try {
                DriverManager.registerDriver(new JDBC());
                conexao = DriverManager.getConnection(url);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

        return conexao;
    }

    public static void setUrl(String url2) {
        url = url2;
    }
}
