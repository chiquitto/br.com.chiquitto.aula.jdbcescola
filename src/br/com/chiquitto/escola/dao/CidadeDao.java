package br.com.chiquitto.escola.dao;

import br.com.chiquitto.escola.vo.Cidade;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author chiquitto
 */
public class CidadeDao extends AbstractDao {
    
    public List<Cidade> getAll() {
        List<Cidade> cidades = new ArrayList<>();

        try {
            Statement st = conexao.createStatement();
            ResultSet rs = st.executeQuery("Select idcidade, cidade From cidade");
            
            while (rs.next()) {
                Cidade cidade = new Cidade();
                cidade.setIdcidade(rs.getInt("idcidade"));
                cidade.setCidade(rs.getString("cidade"));

                cidades.add(cidade);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return cidades;
    }
}
