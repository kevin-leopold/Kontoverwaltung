package model.codes;

/**
 * Rückgabewerte für das Suchen der Kontonummer
 * <table border="1">
 *   <tr>
 *     <td> OK </td> <td> Konto gefunden</td>
 *   </tr>
 *   <tr>
 *     <td> INVALID_ACCOUNT_NUMBER </td> <td> keine gültige Kontonummer</td>
 *   </tr>
 *   <tr>
 *     <td> ACCOUNT_NUMBER_NOT_FOUND </td> <td> Kontonummer wurde nicht gefunden</td>
 *  </tr>
 * </table>
 */
public enum AccountNumberStatusCode {
    OK,
    INVALID_ACCOUNT_NUMBER,
    ACCOUNT_NUMBER_NOT_FOUND,
}
