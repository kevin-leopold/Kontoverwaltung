package controller;

import model.Account;
import model.Formatter;
import model.Model;
import model.codes.AccountNumberStatusCode;
import model.codes.BookingStatusCode;
import model.db.DatabaseSavingStrategy;
import model.file.FileSavingStrategy;
import view.View;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;

public class Controller extends MouseOverListener implements ActionListener {
    private final View view;
    private final Model model;
    public Controller(){
        model = new Model();
        view = new View();
        view.setListeners(this);
    }

    /**
     * Verwaltet alle Events von der View
     * @param e ActionEvent
     */

    @Override
    public void actionPerformed(ActionEvent e) {
        view.clearStatus();
        switch (e.getActionCommand()){
            case ActionCommand.SEARCH:
                    AccountNumberStatusCode code = model.search(view.getAccountNumber().getText());
                    switch (code){
                        case INVALID_ACCOUNT_NUMBER:
                            view.getStatus().setText("Ungültige Kontonummer");
                            break;
                        case ACCOUNT_NUMBER_NOT_FOUND:
                            view.getStatus().setText("Kontonummer nicht gefunden");
                            break;
                        case OK:
                            view.setBookingMode();
                            Account account = model.getAccount();
                            view.getId().setText(account.getId());
                            view.getBalance().setText(Formatter.currencyFormatter.format(account.getBalance()));
                    }
                break;
            case ActionCommand.CANCEL:
                view.reset();
                break;
            case ActionCommand.BOOKING:
                    BookingStatusCode bcode = model.getAccount().book(view.getBookAmount().getText());
                    switch (bcode) {
                        case INVALID_BOOKING_VALUE:
                            view.getStatus().setText("ungültiger Buchungsbetrag");
                            break;
                        case EMPTY_BOOKING_VALUE:
                            view.getStatus().setText("Buchungsbetrag leer");
                            break;
                        case OK:
                            view.getStatus().setForeground(Color.black);
                            view.getStatus().setText("Buchung erfolgreich");
                            view.getStatus().setForeground(Color.RED);
                            model.save();
                            model.log(view.getBookAmount().getText());
                            view.reset();
                    }
                    break;
            case ActionCommand.CHANGE_SAVING_MODE:
                    view.reset();
                    if(model.getSavingStrategy() instanceof DatabaseSavingStrategy){
                        view.getSavingMode().setText("In Datenbank speichern");
                        model.setSavingStrategy(new FileSavingStrategy());
                    }else {
                        view.getSavingMode().setText("In Datei speichern");
                        model.setSavingStrategy(new DatabaseSavingStrategy());
                    }


        }
    }

    /**
     *  Wird nur genutzt falls der Benutzer über das Kontonummern Textfeld hovert.
     * @param e MouseEvent
     */
    @Override
    public void mouseEntered(MouseEvent e) {
        view.getStatus().setText("");
    }
}
