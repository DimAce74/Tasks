package ru.dimace;


import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.*;

public class UtilClass {
    private Set<List<Byte>> listElements = new HashSet<>();
    private SimpleDateFormat format = new SimpleDateFormat("dd-MM-yy");

    public Set<List<Byte>> getListElements() {
        return this.listElements;
    }

    public void setListElements(Set<List<Byte>> newListElements) {
        this.listElements = newListElements;
    }

    public void addElement(List<Byte> element) {
            listElements.add(element);
    }

    public <T> boolean compareNumberWithZero(T obj) {

        try {
            if (Double.valueOf(obj.toString()).equals(0.00)) {
                return true;
            }
        } catch (NumberFormatException e) {
            return false;
        }
        return false;
    }

    /**
     * Метод логирует данные (предполагается использование из внешних модулей).
     * Формат: [текущая дата в виде dd-MM-yy]: stroka; dataElements как строка (через сепаратор)
     * @param stroka строка из инпута
     * @param dataElements коллеция с данными для логирования
     * @param separator символ, который используется для разделения элементов из коллеции dataElements для создания строкового представления.
     */
    public void logData(String stroka, Collection dataElements, char separator) {
        StringBuilder result = new StringBuilder("[").append(format.format(new Date())).append("]: ").append(stroka.trim()).append("; ");

        for (Object obj : dataElements)
            result.append(obj).append(separator);

        System.out.println(result.toString());
    }

    /**
     * [startDate или текущая дата, если startDate = null или меньше текущей даты] + 5 рабочих дней + 1 календарный день + 1 год.
     * classifier - справочник содержащий даты выходных и праздничных дней
     */
    public static Date getDatePlus5WorkDaysPlus1DayPlus1Year(Date startDate, List<InsuranceHoliday> classifier) {
        Calendar calendar = Calendar.getInstance();
        if (startDate != null & startDate.compareTo(calendar.getTime()) < 0) {
            calendar.setTime(startDate);
        }
        int workingDays = 0;
        while (workingDays < 5) {
            calendar.add(Calendar.DAY_OF_YEAR, 1);
            boolean weekend = false;
            for (InsuranceHoliday holiday : classifier) {
                Calendar holydayCalendar = Calendar.getInstance();
                holydayCalendar.setTime(holiday.getTheDate());
                if (holydayCalendar.get(Calendar.DAY_OF_MONTH) == calendar.get(Calendar.DAY_OF_MONTH)
                        && holydayCalendar.get(Calendar.MONTH) == calendar.get(Calendar.MONTH)
                        && holydayCalendar.get(Calendar.YEAR) == calendar.get(Calendar.YEAR)) {
                    weekend = true;
                    break;
                }
            }
            if (!weekend)
                workingDays++;
        }

        calendar.add(Calendar.DAY_OF_YEAR, 1);
        calendar.add(Calendar.YEAR, 1);
        return calendar.getTime();
    }

}

