package view;

import javax.swing.*;

public class StacKOfBooks extends JFrame {
    private JPanel window;
    private JScrollPane scrollPane;
    private JTable table;
    private JPanel form;
    private JTextField pagesText;
    private JTextField idText;
    private JTextField titleText;
    private JButton addBtn;
    private JButton dropBtn;
    private JButton updateBtn;
    private JPanel buttons;
    private JLabel idLabel;
    private JLabel titleLabel;
    private JLabel pagesLabel;


    public StacKOfBooks() {
        super();
        setContentPane(window);
    }

    public JTextField getPagesText() {
        return this.pagesText;
    }

    public JTextField getIdText() {
        return this.idText;
    }

    public JTextField getTitleText() {
        return this.titleText;
    }

    public JButton getAddBtn() {
        return this.addBtn;
    }

    public JButton getDropBtn() {
        return this.dropBtn;
    }

    public JButton getUpdateBtn() {
        return this.updateBtn;
    }

    public JTable getTable() {
        return this.table;
    }

}
