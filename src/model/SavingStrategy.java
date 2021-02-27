package model;

/**
 * Strategy für das Speichern der Daten. Siehe  {@link model.db.DatabaseSavingStrategy} und {@link model.file.FileSavingStrategy}
 */
public interface SavingStrategy {
    /**
     * Sucht nach einem Konto mit der Kontonummer
     *
     * @param number Kontonummer
     * @return Konto
     */
    Account search(int number);

    /**
     * Speichert das übergebene Konto
     *
     * @param account Konto
     */
    void save(Account account);

    /**
     * Speichert nach einer Buchung die Transaktion in ein Log
     *
     * @param account Konto
     * @param amount  Betrag
     */
    void log(Account account, double amount);
}
