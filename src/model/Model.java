package model;

import model.codes.AccountNumberStatusCode;
import model.file.FileSavingStrategy;
// Testdaten
//231243
//231993
public class Model {
    private SavingStrategy savingStrategy;
    private Account account;
    public Model(){
        savingStrategy = new FileSavingStrategy();
    }

    /**
     * Sucht nach einem Konto mit der Kontonummer
     * @param number Kontonummer
     * @return Gibt den Status zurück {@link model.codes.AccountNumberStatusCode}
     */

    public AccountNumberStatusCode search(String number){
        try{
            account = savingStrategy.search(Integer.parseInt(number));
            return (account == null) ? AccountNumberStatusCode.ACCOUNT_NUMBER_NOT_FOUND : AccountNumberStatusCode.OK;
        }catch (Exception e){
            return AccountNumberStatusCode.INVALID_ACCOUNT_NUMBER;
        }
    }

    /**
     * Speichert das aktuelle Konto
     */
    public void save(){
        savingStrategy.save(account);
    }

    /**
     * Gibt das aktuelle Konto zurück
     * @return Konto
     */
    public Account getAccount() {
        return account;
    }

    public void log(String amount){
        try {
            savingStrategy.log(account, Double.parseDouble(amount));
        }catch (Exception e){
            //not reachable
        }
    }

    /**
     * Ändert die Art zu Speichern
     * @param savingStrategy Strategy
     */

    public void setSavingStrategy(SavingStrategy savingStrategy) {
        this.savingStrategy = savingStrategy;
    }

    /**
     *  Gibt die Art zu Speichern zurück
     * @return Strategy
     */

    public SavingStrategy getSavingStrategy() {
        return savingStrategy;
    }
}
