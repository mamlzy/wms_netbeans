package lib;

import java.lang.reflect.Field;
import java.util.List;
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

    public static void printList(List<?> list) {
        if (list == null || list.isEmpty()) {
            System.out.println("List is empty.");
            return;
        }

        for (Object obj : list) {
            printObject(obj);
        }
    }

    private static void printObject(Object obj) {
        System.out.println("==== " + obj.getClass().getSimpleName() + " ====");
        Field[] fields = obj.getClass().getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            try {
                Object value = field.get(obj);
                System.out.printf("%s : %s%n", field.getName(), value);
            } catch (IllegalAccessException e) {
                System.out.printf("%s : [access error]%n", field.getName());
            }
        }
        System.out.println("============================\n");
    }

}
