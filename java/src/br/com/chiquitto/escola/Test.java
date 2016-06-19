package br.com.chiquitto.escola;

import java.sql.Connection;

/**
 *
 * @author chiquitto
 */
public class Test {

    public static void main(String[] args) {
        Connection c = Conexao.getConexao();
    }
}
