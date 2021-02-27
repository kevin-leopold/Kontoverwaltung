package model;

import model.booking.BookStrategy;
import model.codes.BookingStatusCode;


import java.io.Serializable;

/**
 * Speichert die Daten eines einzelnen Accounts
 */

public class Account implements Serializable {
    private String id;
    private int accountNumber;
    private double balance;
    private BookStrategy bookStrategy;

    public Account(String id, int accountNumber, double balance, BookStrategy bookStrategy) {
        this.id = id;
        this.accountNumber = accountNumber;
        this.balance = balance;
        this.bookStrategy = bookStrategy;
    }

    /**
     * Gibt die Kontonummer zurück
     * @return Kontonummer
     */
    public int getAccountNumber() {
        return accountNumber;
    }

    /**
     * Gibt den Kontostand zurück
     * @return Kontostand
     */
    public double getBalance() {
        return balance;
    }

    /**
     * Gibt den Sachbearbeiter-Kürzel zurück.
     * @return Sachbearbeiter-Kürzel
     */

    public String getId() {
        return id;
    }

    /**
     * Gibt die Art des Kontos zurück
     * @return Kontoart
     */

    public BookStrategy getBookStrategy() {
        return bookStrategy;
    }

    /**
     * Setzt den Kontostand
     * @param balance neuer Kontostand
     */

    public void setBalance(double balance) {
        this.balance = balance;
    }

    /**
     * Betrag als String von Textfeld wird überprüft und der Strategy übergeben
     * @param amount Betrag
     * @return Gibt Rückgabewert zurück, ob die Transaktion erfolgreich war
     */

    public BookingStatusCode book(String amount){
        if(amount.trim().isEmpty()){ return BookingStatusCode.EMPTY_BOOKING_VALUE;}
        try{
            return bookStrategy.book(this,Double.parseDouble(amount));
        }catch (Exception e){
            return BookingStatusCode.INVALID_BOOKING_VALUE;
        }
    }

    @Override
    public String toString() {
        return "Account{" +
                "id='" + id + '\'' +
                ", accountNumber=" + accountNumber +
                ", balance=" + balance +
                '}';
    }
}
