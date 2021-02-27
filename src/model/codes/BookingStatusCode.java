package model.codes;

/**
 * Rückgabewerte für die Buchung
 *
 * <table border="1">
 *   <tr>
 *     <td> OK </td> <td> Buchung erfolgreich</td>
 *   </tr>
 *   <tr>
 *     <td> EMPTY_BOOKING_VALUE </td> <td> Buchungsbetrag leer</td>
 *   </tr>
 *   <tr>
 *     <td> INVALID_BOOKING_VALUE </td> <td> Buchungsbetrag ist ungültig</td>
 *  </tr>
 * </table>
 */

public enum BookingStatusCode {
    OK,
    EMPTY_BOOKING_VALUE,
    INVALID_BOOKING_VALUE,
}
