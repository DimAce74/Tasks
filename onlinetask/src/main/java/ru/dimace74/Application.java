package ru.dimace74;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.core.type.filter.RegexPatternTypeFilter;
import ru.dimace74.config.SpringConfig;
import ru.dimace74.documents.Document;
import ru.dimace74.services.DocService;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;
import java.util.regex.Pattern;

public class Application {
    private static ApplicationContext context;
    private static BufferedReader reader;
    private static DocService service;
    private static Map<Integer, String> namesOfDocuments = new HashMap<>();
    private static Class docType;

    public static void main(String[] args) {
        context = new AnnotationConfigApplicationContext(SpringConfig.class);
        ClassPathScanningCandidateComponentProvider provider = new ClassPathScanningCandidateComponentProvider(false);
        provider.addIncludeFilter(new RegexPatternTypeFilter(Pattern.compile(".*")));
        Set<BeanDefinition> typesOfDocuments = provider.findCandidateComponents("ru.dimace74.documents");

        Integer key = 0;
        for (BeanDefinition bean : typesOfDocuments) {
            String name = bean.getBeanClassName();
            key++;
            namesOfDocuments.put(key, name);
        }

        reader = new BufferedReader(new InputStreamReader(System.in));
        menuTop();

    }

    private static void menuTop() {
        System.out.println("Введите номер типа документа или 0 для выхода:");
        for (int i=1; i<namesOfDocuments.size()+1; i++){
            System.out.println(i+") " + getNameFromFullQualName(namesOfDocuments.get(i)) + ";");
        }

        String choice = "";
        try {
            choice = reader.readLine();
        } catch (IOException e) {
            throw new IllegalArgumentException();
        }
        if (Integer.parseInt(choice)==0){
            System.out.println("До свидания!");
            System.exit(0);
        }
        String fullDocTypeName = namesOfDocuments.get(Integer.parseInt(choice));
        String nameOfDocType = getNameFromFullQualName(fullDocTypeName);
        Class<DocService> serviceType = null;
        try {
            docType = Class.forName(fullDocTypeName);
            serviceType= (Class<DocService>) Class.forName("ru.dimace74.services." +nameOfDocType+"Service");

        } catch (ClassNotFoundException e) {
            System.out.println("Такого варианта не существует!");
            menuTop();
        }
        service = context.getBean(serviceType);

        menuAction();


    }

    private static void menuAction() {
        System.out.println("Выбран тип документа: " + docType.getName());
        System.out.println("Что вы хотите сделать?");
        System.out.println("1) Изменить существующий документ;");
        System.out.println("2) Создать новый документ");
        int choice = 0;
        try{
            choice = Integer.parseInt(reader.readLine());
        } catch (IOException e){
            throw new IllegalArgumentException();
        }
        if (choice==1){
            menuEditDoc();
        } else if (choice==2) {
            menuCreateDoc();
        } else {
            System.out.println("Такого варианта не существует!");
            menuAction();
        }
    }

    private static void menuCreateDoc() {
        Object document;
        try {
            document = docType.cast(docType.newInstance());
    } catch (InstantiationException | IllegalAccessException e) {
            throw new IllegalArgumentException();
        }
        BeanInfo beanInfo;
        try {
            beanInfo = Introspector.getBeanInfo(docType);
        } catch (IntrospectionException e) {
            throw new IllegalArgumentException();
        }
        for (PropertyDescriptor propertyDescriptor : beanInfo.getPropertyDescriptors()){
            if (!propertyDescriptor.getName().equals("class") && !propertyDescriptor.getName().equals("id")) {
                editAttribute(propertyDescriptor, document);
            }
        }
        service.save(document);
        System.out.println("Документ успешно сохранен!");
        menuTop();
    }

    private static void menuEditDoc() {
        System.out.println("Введите ID документа, который Вы хотите изменить:");
        int id = 0;
        try {
            id = Integer.parseInt(reader.readLine());
        } catch (IOException e) {
            System.out.println("Это не число!");
            menuEditDoc();
        }
        Object document = service.findById(id);
        if (document == null) {
            System.out.println("Такого документа не существует!");
            menuEditDoc();
        } else {
            menuEditAttribute(document);
        }
    }

    private static void menuEditAttribute(Object document){
        BeanInfo beanInfo;
        try {
            beanInfo = Introspector.getBeanInfo(docType);
        } catch (IntrospectionException e) {
            throw new IllegalArgumentException();
        }
        Map<Integer, PropertyDescriptor> fieldsMap = new HashMap<>();
        int key = 0;
        for (PropertyDescriptor propertyDescriptor : beanInfo.getPropertyDescriptors()) {
            String propertyName = propertyDescriptor.getName();
            if (!propertyName.equals("class") && !propertyName.equals("id")){
                key++;
                fieldsMap.put(key, propertyDescriptor);
            }
        }
        System.out.println("Какое поле Вы хотите изменить?");
        for (int i =1; i<fieldsMap.size()+1; i++){
            System.out.println(i + ") "+ fieldsMap.get(i).getName()+";");
        }
        int choice = 0;
        try {
            choice = Integer.parseInt(reader.readLine());
        } catch (IOException e) {
            System.out.println("Это не число!");
            menuEditAttribute(document);
        }
        PropertyDescriptor propertyDescriptor = fieldsMap.get(choice);
        Method readMethod = propertyDescriptor.getReadMethod();
        try {
            String actualValue = readMethod.invoke(document).toString();
            System.out.println("Текущее значение: " + actualValue);
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
        editAttribute(propertyDescriptor, document);
        service.save(document);
        System.out.println("Документ успешно изменен!");
        menuTop();
    }

    private static void editAttribute (PropertyDescriptor propertyDescriptor, Object document){
        String propertyName = propertyDescriptor.getName();
        Class propertyType = propertyDescriptor.getPropertyType();
        Method method = propertyDescriptor.getWriteMethod();

        if (propertyType.equals(Date.class)){
            System.out.println("Введите значение " + propertyName);
            int year=0;
            int day=0;
            int month=0;
            System.out.println("День:");
            try {
                day = Integer.parseInt(reader.readLine());
                System.out.println("Порядковый номер месяца:");
                month = Integer.parseInt(reader.readLine())-1;
                System.out.println("Год:");
                year = Integer.parseInt(reader.readLine());
            } catch (IOException e){
                System.out.println("Неверные данные!");
                menuCreateDoc();
            }
            Calendar calendar = new Calendar.Builder().setDate(year, month, day).build();
            try {
                method.invoke(document, calendar.getTime());
            } catch (IllegalAccessException | InvocationTargetException e) {
                e.printStackTrace();
            }
        } else if (propertyType.equals(Integer.class)) {
            System.out.println("Введите значение " + propertyName);
            try {
                method.invoke(document, Integer.parseInt(reader.readLine()));
            } catch (IllegalAccessException | InvocationTargetException | IOException e) {
                e.printStackTrace();
            }
        } else if (propertyType.equals(String.class)) {
            System.out.println("Введите значение " + propertyName);
            try {
                method.invoke(document, reader.readLine());
            } catch (IllegalAccessException | InvocationTargetException | IOException e) {
                e.printStackTrace();
            }
        } else if (Arrays.stream(propertyType.getInterfaces()).filter(aClass -> aClass.equals(Document.class)).count()==1){
            System.out.println("Введите значение " + propertyName);
            System.out.println("Введите ID документа:");
            Class<DocService> serviceType;
            try {
                serviceType= (Class<DocService>) Class.forName("ru.dimace74.services." +getNameFromFullQualName(propertyType.getName())+"Service");

            } catch (ClassNotFoundException e) {
                throw new IllegalArgumentException();
            }
            DocService innerService = context.getBean(serviceType);
            try {
                method.invoke(document, innerService.findById(Integer.parseInt(reader.readLine())));
            } catch (IllegalAccessException | InvocationTargetException | IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Такой тип поля: "+ propertyType.getName()+" не предусмотрен, обратитесь к разработчику!");
            menuAction();
        }

    }

    private static String getNameFromFullQualName (String fullName){
        String[] partsOfPath = fullName.split("\\.");
        return partsOfPath[partsOfPath.length-1];
    }

}
