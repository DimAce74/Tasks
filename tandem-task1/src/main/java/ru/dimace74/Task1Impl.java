package main.java.ru.dimace74;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

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

        class ColumnComparator implements Comparator<String[]>{
            @Override
            public int compare(String[] arr1, String[] arr2) {
                String a = arr1[columnIndex];
                String b = arr2[columnIndex];
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
        private List<String> stringTokenizer (String string) {
            Pattern pattern = Pattern.compile("(\\d+)");
            Matcher matcher = pattern.matcher(string);
            List<String> digitSubstrings = new ArrayList<>();
            while (matcher.find()) {
                digitSubstrings.add(matcher.group());
            }
            if (digitSubstrings.isEmpty()) {
                throw new IllegalArgumentException();
            }else {
                int maxLength = digitSubstrings.stream()
                        .map(String::length)
                        .max(Integer::compareTo)
                        .get();
                List<String> maxDigitSubstrings = digitSubstrings.stream()
                        .filter(str-> str.length()==maxLength)
                        .collect(Collectors.toList());
                List<Integer> listOfIndexes = maxDigitSubstrings.stream()
                        .map(string::indexOf)
                        .collect(Collectors.toList());
                List<String> result = new ArrayList<>();
                result.add(string.substring(0, listOfIndexes.get(0)));
                if (listOfIndexes.size()==1){
                    result.add(string.substring(listOfIndexes.get(0)))
                }
                for (int i=0; i<listOfIndexes.size()-1; i++){
                    while (string.length()>(listOfIndexes.get(i)+maxLength)) {
                        result.add(string.substring(listOfIndexes.get(i), (listOfIndexes.get(i) + maxLength)));

                    }
                }

        }

        rows.sort(new ColumnComparator());

    }

}

