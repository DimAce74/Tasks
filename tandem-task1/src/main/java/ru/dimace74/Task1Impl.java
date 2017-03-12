package main.java.ru.dimace74;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * <h1>Задание №1</h1>
 * Реализуйте интерфейс {@link IStringRowsListSorter}.
 *
 * <p>Мы будем обращать внимание в первую очередь на структуру кода и владение стандартными средствами java.</p>
 */
public class Task1Impl implements IStringRowsListSorter {

    // ваша реализация должна работать, как singleton. даже при использовании из нескольких потоков.
    public static final IStringRowsListSorter INSTANCE = new Task1Impl();

    @Override
    public void sort(final List<String[]> rows, final int columnIndex) {
        for (String[] row : rows) {

        }
        class columnComparator implements Comparator<String>{

            @Override
            public int compare(String a, String b) {
                if (a == null) {
                    return (b == null) ? 0:-1;
                } else if (b == null) {
                    return 1;
                } else if (a.equals("")) {
                    return b.equals("") ? 0:-1;
                } else if (b.equals("")) {
                    return 1;
                }
            }
        private String[] stringTokenizer (String string) {
            Pattern pattern = Pattern.compile("(\\d+)");
            Matcher matcher = pattern.matcher(string);
            List<String> digitSubstrings = new ArrayList<>();
            while (matcher.find()) {
                digitSubstrings.add(matcher.group());
            }
            if (digitSubstrings.isEmpty()) {
                return null;
            }else {
                int maxLength = digitSubstrings.stream().
            }
        }

        }
    }

}

