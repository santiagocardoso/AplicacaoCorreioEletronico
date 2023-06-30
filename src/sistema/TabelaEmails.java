package sistema;

import java.util.LinkedList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import dados.*;
import exceptions.SelectException;
import negocio.Sistema;

public class TabelaEmails extends AbstractTableModel {
    private String[] colunas = {"Emails"};
    private Sistema sistema;
    private Usuario usuario;
    private List<Email> emails = new LinkedList<>();

    public TabelaEmails(Usuario u) {
        try {
            sistema = new Sistema("postgres");
            this.usuario = u;
            for (Email e : sistema.getEmails()) {
                if (e.getIdDestinatario() == usuario.getId())
                    emails.add(e);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
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
        return emails.size();
    }
    @Override
    public Object getValueAt(int linha, int coluna) {
        return emails.get(linha);
    };
    public int getRowAt() throws SelectException {
        return emails.size();
    }
    public void atualizar() {
        fireTableStructureChanged();
    }
}

