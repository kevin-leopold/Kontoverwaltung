package view;

import controller.ActionCommand;
import controller.Controller;

import javax.swing.*;
import java.awt.*;

public class View extends JFrame {
    private final int TEXTFIELD_COLUMNS = 17;
    private JPanel mainpanel;
    private JPanel inputpanel;
    private JPanel buttonpanel;
    private JLabel header;
    private JLabel accountNumberL;
    private JLabel idL;
    private JLabel balanceL;
    private JLabel bookAmountL;
    private JTextField accountNumber;
    private JTextField id;
    private JTextField balance;
    private JTextField bookAmount;
    private JButton ok;
    private JButton cancel;
    private JLabel status;
    private JButton savingMode;

    public View() {
        super("Kontoverwaltung");
        init();
        setVisible(true);
    }

    /**
     * Initialisiert die View
     */

    private void init() {
        setMinimumSize(new Dimension(450, 550));
        setLocationRelativeTo(null);
        setResizable(false);
        mainpanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 40, 40));
        header = new JLabel("Kontoverwaltung");
        header.setFont(header.getFont().deriveFont(Font.BOLD, 25));
        mainpanel.add(header);
        inputpanel = new JPanel(new GridLayout(0, 2, 10, 20));
        accountNumber = new JTextField(TEXTFIELD_COLUMNS);
        accountNumberL = new JLabel("Kontonummer: ");
        id = new JTextField(TEXTFIELD_COLUMNS);
        id.setEditable(false);
        idL = new JLabel("Kürzel: ");
        balance = new JTextField(TEXTFIELD_COLUMNS);
        balance.setEditable(false);
        balanceL = new JLabel("Kontostand: ");
        bookAmount = new JTextField(TEXTFIELD_COLUMNS);
        bookAmount.setEditable(false);
        bookAmountL = new JLabel("Buchungsbetrag: ");
        inputpanel.add(accountNumberL);
        inputpanel.add(accountNumber);
        inputpanel.add(idL);
        inputpanel.add(id);
        inputpanel.add(balanceL);
        inputpanel.add(balance);
        inputpanel.add(bookAmountL);
        inputpanel.add(bookAmount);
        mainpanel.add(inputpanel);
        buttonpanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 0));
        ok = new JButton("Ok");
        cancel = new JButton("Abbrechen");
        buttonpanel.add(ok);
        buttonpanel.add(cancel);
        buttonpanel.setPreferredSize(new Dimension(450, buttonpanel.getPreferredSize().height));
        mainpanel.add(buttonpanel);
        JPanel p = new JPanel();
        savingMode = new JButton("In Datenbank Speichern");
        p.add(savingMode);
        p.setPreferredSize(new Dimension(450, p.getPreferredSize().height));
        mainpanel.add(p);
        status = new JLabel();
        status.setForeground(Color.RED);
        mainpanel.add(status);
        add(mainpanel);
    }

    /**
     * Setzt die Textfelder zurück und leert sie
     */

    public void reset() {
        accountNumber.setText("");
        id.setText("");
        bookAmount.setText("");
        balance.setText("");
        setSearchingMode();

    }

    /**
     * Leert die Statuszeile
     */
    public void clearStatus() {
        status.setText("");
    }

    /**
     * Verändert die View zum Buchungsmodus
     */
    public void setBookingMode() {
        accountNumber.setEditable(false);
        bookAmount.setEditable(true);
        ok.setActionCommand(ActionCommand.BOOKING);
        ok.setText("Buchen");
    }

    /**
     * Verändert die View zum Suchmodus
     */
    public void setSearchingMode() {
        accountNumber.setEditable(true);
        bookAmount.setEditable(false);
        ok.setActionCommand(ActionCommand.SEARCH);
        ok.setText("Ok");
    }

    /**
     * Setzt die Listener vom Controller fest.
     *
     * @param c Controller
     */


    public void setListeners(Controller c) {
        ok.setActionCommand(ActionCommand.SEARCH);
        cancel.setActionCommand(ActionCommand.CANCEL);
        ok.addActionListener(c);
        cancel.addActionListener(c);
        savingMode.setActionCommand(ActionCommand.CHANGE_SAVING_MODE);
        savingMode.addActionListener(c);
        accountNumber.addMouseListener(c);
    }

    public JTextField getAccountNumber() {
        return accountNumber;
    }

    public JTextField getBalance() {
        return balance;
    }

    public JTextField getId() {
        return id;
    }

    public JTextField getBookAmount() {
        return bookAmount;
    }

    public JLabel getStatus() {
        return status;
    }

    public JButton getSavingMode() {
        return savingMode;
    }
}
