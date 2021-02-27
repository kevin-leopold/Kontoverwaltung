package model;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;

/**
 * Wird für das Formatieren von Geld und Daten benutzt.
 */

public class Formatter {
    public static final SimpleDateFormat dateFormatter = new SimpleDateFormat("dd-MM-yyy HH:mm:ss");
    public static final DecimalFormat currencyFormatter = new DecimalFormat("0.00 €");
}
