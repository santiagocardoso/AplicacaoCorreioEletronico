package sistema;

import javax.swing.table.AbstractTableModel;

import dados.*;
import exceptions.SelectException;

public class TabelaEmails extends AbstractTableModel {
    private String[] colunas = {"Emails"};
    private Usuario usuario;

    public TabelaEmails(Usuario u) {
        this.usuario = u;
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
        return usuario.getQuantEmails();
    }
    @Override
    public Object getValueAt(int linha, int coluna) {
        try {
            return usuario.getEmails().get(linha);
        } catch (SelectException e) {
            e.printStackTrace();
        }
        return null;
    };
    public int getRowAt() throws SelectException {
		return usuario.getEmails().size();
	}
	public void adicionarValor() {
		fireTableStructureChanged();
	}
    public void atualizar() {
		fireTableStructureChanged();
	}
}

