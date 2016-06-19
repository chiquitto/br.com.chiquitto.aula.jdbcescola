package br.com.chiquitto.escola.dao;

import br.com.chiquitto.escola.Conexao;
import java.sql.Connection;

/**
 *
 * @author chiquitto
 */
public class AbstractDao {

    protected Connection conexao;

    public AbstractDao() {
        conexao = Conexao.getConexao();
    }
}
