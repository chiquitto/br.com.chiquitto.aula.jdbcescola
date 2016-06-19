package br.com.chiquitto.escola;

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

    public static Connection getConexao() {
        if (conexao == null) {
            try {
                DriverManager.registerDriver(new JDBC());
                conexao = DriverManager.getConnection("jdbc:sqlite:/Users/chiquitto/work/aula/java-caso-uso-escola/data/banco.db");
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

        return conexao;
    }
}
