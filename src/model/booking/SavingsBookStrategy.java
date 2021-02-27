package model.booking;

import model.Account;
import model.codes.BookingStatusCode;

public class SavingsBookStrategy implements BookStrategy{

    /**
     * Sparbuch Buchungsvorang
     * Negative Werte sind nicht erlaubt
     * @param account Konto
     * @param amount Betrag
     * @return Gibt den Status zurück. Siehe {@link model.codes.BookingStatusCode}
     */
    @Override
    public BookingStatusCode book(Account account, double amount) {
        if(amount < 0){return BookingStatusCode.INVALID_BOOKING_VALUE;}
        account.setBalance(account.getBalance() + amount);
        return BookingStatusCode.OK;
    }
}
