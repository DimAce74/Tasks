package ru.dimace74;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.dimace74.config.SpringConfig;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Application {
    private static BufferedReader reader;

    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);
        reader = new BufferedReader(new InputStreamReader(System.in));

        menuTop();
    }

    private static void menuTop() {
        System.out.println("Введите номер документа:");
        System.out.println("1) Пасспорт;");
        System.out.println("2) Свидетельство о регистрации брака.");
        String choice = "";
        try {
            choice = reader.readLine();
        } catch (IOException e) {
            throw new IllegalArgumentException();
        }
        if (choice.equals("1")){
            menuPassport();
        } else if (choice.equals("2")){
            menuCertificateOfMarriage();
        } else {
            System.out.println("Такого варианта не существует!");
            menuTop();
        }
    }

    private static void menuCertificateOfMarriage() {

    }

    private static void menuPassport() {
    }
}
