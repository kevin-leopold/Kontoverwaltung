package model.booking;

import model.Account;
import model.codes.BookingStatusCode;

import java.io.Serializable;

/**
 * Strategy für den Buchungsvorgang
 */
public interface BookStrategy extends Serializable {
    BookingStatusCode book(Account account, double amount);
}
