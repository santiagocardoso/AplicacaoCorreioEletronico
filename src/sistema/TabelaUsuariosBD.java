package sistema;

import java.sql.SQLException;

import javax.swing.table.AbstractTableModel;

import dados.*;
import negocio.*;
import exceptions.*;

public class TabelaUsuariosBD extends AbstractTableModel {
    private String[] colunas = {"Usuários"};
    private SistemaBD sistema;

    public TabelaUsuariosBD() throws ClassNotFoundException, SQLException, SelectException {
        sistema = new SistemaBD("postgres");
    }

    public String getColumnName(int column) {
        return colunas[column];
    }

    @Override
    public int getColumnCount() {
        return 1;
    }

    @Override
    public int getRowCount() {
        try {
            return sistema.selectAll().size();
        } catch (SelectException e) {
            System.err.println("Não foi possível pegar o RowCount!");
        }
        return 0;
    }

    @Override
    public Object getValueAt(int linha, int coluna) {
        try {
            return sistema.selectAll().get(linha);
        } catch (SelectException e) {
            System.err.println("Não foi possível pegar o ValueAt!");
        }
        return null;
    }

    public int getRowAt() throws SelectException {
        return sistema.selectAll().size();
    }

    public void adicionarValor(Usuario u) throws InsertException, SelectException {
        sistema.inserirUsuario(u);
        fireTableStructureChanged();
    }
}
