package model.booking;

import model.Account;
import model.codes.BookingStatusCode;

public class DefaultBookStrategy implements BookStrategy {
    /**
     * Standart Buchungsvorgang
     *
     * @param account Konto
     * @param amount  Betrag
     * @return Gibt den Status zurück. Siehe {@link model.codes.BookingStatusCode}
     */
    @Override
    public BookingStatusCode book(Account account, double amount) {
        account.setBalance(account.getBalance() + amount);
        return BookingStatusCode.OK;
    }
}
