package model.file;

import model.Account;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

import static java.nio.file.StandardOpenOption.APPEND;
import static java.nio.file.StandardOpenOption.CREATE;

/**
 * Klasse für das Lesen und Schreiben von Dateien.
 */
public class FileHandler {
    private static FileHandler instance;
    private final String ACCOUNTS = "accounts.bin";
    private final String BOOK_LOG = "book.log";

    private FileHandler(){}

    /**
     * Gibt die Instanz von FileHandler zurück (Singleton)
     * @return Instanz
     */
    public static FileHandler getInstance() {
        if(instance == null){instance = new FileHandler();}
        return instance;
    }

    /**
     * Liest alle Kontodaten und gibt sie zurück
     * @return Kontodaten
     */

    public ArrayList<Account> readAccounts(){
        checkFile(ACCOUNTS);
        try(ObjectInputStream oi = new ObjectInputStream(new FileInputStream(ACCOUNTS))){
            return (ArrayList<Account>) oi.readObject();
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    /**
     *  Schreibt alle Kontodaten in eine Datei
     * @param data Kontodaten
     */
    public void writeAccounts(ArrayList<Account> data){
       checkFile(ACCOUNTS);
        try(ObjectOutputStream oo = new ObjectOutputStream(new FileOutputStream(ACCOUNTS))){
            oo.writeObject(data);
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * Schreibt Daten in die Log Datei
     * @param id Sachbearbeiter-Kürzel
     * @param accountNumber Kontonummer
     * @param amount Betrag
     * @param balanceBefore Kontostand bevor
     * @param balanceAfter Kontostand danach
     * @param date Datum
     */
    public void writeLog(String id, int accountNumber, double amount, double balanceBefore, double balanceAfter, String date){
        checkFile(BOOK_LOG);
        try {
            String s = (Files.lines(Path.of(BOOK_LOG)).count()+1)+",";
            s+=id+",";
            s+=accountNumber+",";
            s+=amount+",";
            s+=balanceBefore+",";
            s+=balanceAfter+",";
            s+=date+System.lineSeparator();
            Files.writeString(Path.of(BOOK_LOG),s,CREATE, APPEND);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Prüft, ob die Datei existiert. Falls nicht, wird sie erstellt
     * @param file Datei-URL
     */

    private void checkFile(String file){
        if(!new File(file).exists()){
            try {
                new File(file).createNewFile();
            } catch (IOException ignored) {
            }
        }
    }

}
