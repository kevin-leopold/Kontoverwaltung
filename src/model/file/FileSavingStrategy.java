package model.file;

import model.Account;
import model.Formatter;
import model.SavingStrategy;

import java.util.ArrayList;
import java.util.Date;

/**
 * Strategy für das Speichern in einer Datei. Siehe {@link model.SavingStrategy}
 */
public class FileSavingStrategy implements SavingStrategy {

    @Override
    public Account search(int number) {
        ArrayList<Account> accounts = FileHandler.getInstance().readAccounts();
        for (Account account : accounts) {
            if (account.getAccountNumber() == number) {
                return account;
            }
        }
        return null;
    }

    @Override
    public void save(Account account) {
        ArrayList<Account> accounts = FileHandler.getInstance().readAccounts();
        for(int i =0;i< accounts.size();i++){
            if(accounts.get(i).getAccountNumber() == account.getAccountNumber()){
                accounts.set(i,account);
                FileHandler.getInstance().writeAccounts(accounts);
                return;
            }
        }
        accounts.add(account);
        FileHandler.getInstance().writeAccounts(accounts);
    }

    @Override
    public void log(Account account, double amount) {
        FileHandler.getInstance().writeLog(account.getId(),account.getAccountNumber(),
                amount,account.getBalance()-amount,account.getBalance(),
                Formatter.dateFormatter.format(new Date()));
    }
}
