package sistema;

import javax.swing.table.AbstractTableModel;

import dados.*;
import negocio.*;

public class TabelaEmailsBD extends AbstractTableModel {
    private String[] colunas = {"Emails"};
    private Usuario usuario;

    Sistema sistema = new Sistema();

    public TabelaEmailsBD(Usuario u) {
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
        return usuario.getEmails().get(linha);
    };
    public int getRowAt() {
		return usuario.getEmails().size();
	}
	public void adicionarValor() {
        for (Email e : usuario.getEmails()) {
		    usuario.adicionarEmail(e);
            sistema.descriptarEmail(usuario, e);
        }
		fireTableStructureChanged();
	}
    public void atualizar() {
		fireTableStructureChanged();
	}
}

