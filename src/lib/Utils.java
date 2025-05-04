package lib;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;

/**
 *
 * @author Agung
 */
public class Utils {

    public static String formatNumberToRupiah(Number amount) {
        BigInteger value;

        if (amount instanceof BigDecimal) {
            value = ((BigDecimal) amount).toBigInteger(); // buang pecahan
        } else if (amount instanceof BigInteger) {
            value = (BigInteger) amount;
        } else {
            value = BigInteger.valueOf(amount.longValue()); // aman buat int, long, double
        }

        DecimalFormatSymbols symbols = new DecimalFormatSymbols();
        symbols.setGroupingSeparator('.');
        symbols.setDecimalSeparator(','); // default Indonesia, walau nggak dipakai di BigInteger

        DecimalFormat formatter = new DecimalFormat("Rp #,###", symbols);
        return formatter.format(value);
    }

    public static String formatNumberWithDotSeparator(int number) {
        DecimalFormatSymbols symbols = new DecimalFormatSymbols();
        symbols.setGroupingSeparator('.');

        DecimalFormat formatter = new DecimalFormat("#,###", symbols);
        return formatter.format(number);
    }
}
