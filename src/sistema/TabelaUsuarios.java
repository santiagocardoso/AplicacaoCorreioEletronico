package sistema;

import java.sql.SQLException;

import javax.swing.table.AbstractTableModel;

import dados.Usuario;
import negocio.*;
import exceptions.*;

public class TabelaUsuarios extends AbstractTableModel {
    private String[] colunas = {"Usuários"};
    private Sistema sistema;

    public TabelaUsuarios() throws ClassNotFoundException, SQLException, SelectException {
        sistema = new Sistema("postgres");
    }

    public String getColumnName(int column) {
        return colunas[column];
    }

    @Override
    public int getRowCount() {
        try {
            return sistema.getUsuarios().size();
        } catch (SelectException e) {
            System.err.println("Não foi possível obter a contagem de usuários: " + e.getMessage());
        }
        return 0;
    }

    @Override
    public int getColumnCount() {
        return colunas.length;
    }

    @Override
    public Object getValueAt(int linha, int coluna) {
        Usuario usuario;
        try {
            usuario = sistema.getUsuarios().get(linha);
            if (coluna == 0) {
                return usuario.getUsuario() + " | " + usuario.getEnderecoEmail();
            }
        } catch (SelectException e) {
            System.err.println("Não foi possível obter o usuário: " + e.getMessage());
        }
        return null;
    }

    public int getRowAt() throws SelectException {
        return sistema.getUsuarios().size();
    }

    public void adicionarValor() {
        fireTableStructureChanged();
    }
}
