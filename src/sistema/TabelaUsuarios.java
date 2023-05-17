package sistema;

import javax.swing.table.AbstractTableModel;

import dados.*;
import negocio.*;

public class TabelaUsuarios extends AbstractTableModel {
    private String[] colunas = {"Usuários"};

    private Sistema sistema = new Sistema();

    public String getColumnName(int column) {
		return colunas[column];
    }
    @Override
    public int getColumnCount() {
        return 1;
    }
    @Override
    public int getRowCount() {
        return sistema.getUsuarios().size();
    }
    @Override
    public Object getValueAt(int linha, int coluna) {
        return sistema.getUsuarios().get(linha);
    };
    public int getRowAt() {
		return sistema.getUsuarios().size();
	}
	public void adicionarValor(Usuario u) {
		sistema.cadastrarUsuario(u);
		fireTableStructureChanged();
	}
}

