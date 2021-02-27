package model.db;

import model.Account;
import model.Formatter;
import model.SavingStrategy;
import model.booking.DefaultBookStrategy;
import model.booking.SavingsBookStrategy;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

/**
 * Strategy für das Speichern in einer Datenbank. Siehe {@link model.SavingStrategy}
 */
public class DatabaseSavingStrategy implements SavingStrategy {
    @Override
    public Account search(int number) {
        try {
            PreparedStatement preparedStatement = DatabaseConnection.getInstance().prepareStatement(
                    "select * from account where account_number = ?");
            preparedStatement.setInt(1, number);
            ResultSet rs = preparedStatement.executeQuery();

            if (!rs.next()) {
                return null;
            }
            return new Account(rs.getString(2), rs.getInt(1), rs.getDouble(3),
                    rs.getInt(4) == 1 ? new SavingsBookStrategy() : new DefaultBookStrategy());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    @Override
    public void save(Account account) {
        try {
            PreparedStatement preparedStatement = DatabaseConnection.getInstance().prepareStatement(
                    "update account set balance = ?, id = ?, type = ? where account_number = ?");
            preparedStatement.setDouble(1, account.getBalance());
            preparedStatement.setString(2, account.getId());
            preparedStatement.setInt(3, (account.getBookStrategy() instanceof SavingStrategy) ? 1 : 0);
            preparedStatement.setInt(4, account.getAccountNumber());
            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public void log(Account account, double amount) {
        try {
            PreparedStatement preparedStatement = DatabaseConnection.getInstance().prepareStatement(
                    "insert into log (account_number, id, amount, balance_before, balance_after, date) values(?,?,?,?,?,?)");
            preparedStatement.setInt(1, account.getAccountNumber());
            preparedStatement.setString(2, account.getId());
            preparedStatement.setDouble(3, amount);
            preparedStatement.setDouble(4, account.getBalance() - amount);
            preparedStatement.setDouble(5, account.getBalance());
            preparedStatement.setString(6, Formatter.dateFormatter.format(new Date()));
            preparedStatement.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }
}
