package itis.parsing;

import itis.parsing.annotations.FieldName;
import itis.parsing.annotations.MaxLength;
import itis.parsing.annotations.NotBlank;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.Annotation;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ParkParsingServiceImpl implements ParkParsingService {

    //Парсит файл в обьект класса "Park", либо бросает исключение с информацией об ошибках обработки
    @Override
    public Park parseParkData(String parkDatafilePath) throws ParkParsingException {
        Object o=null;

        List<String> fileLines = new ArrayList<>();
        Class klass= null;
        try {
            klass = Class.forName("itis.parsing.Park");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        Field field=null;

        try {
            fileLines = Files.readAllLines(Paths.get(parkDatafilePath));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Constructor constructor=null;
        try {
            constructor= klass.getConstructor();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        try {
             o=constructor.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

        Field[] fields=klass.getDeclaredFields();

//        for (Field field:fields) {
//            field.setAccessible(true);
//            Annotation[] annotations= (Annotation[]) field.getDeclaredAnnotations();
//            //java.lang.annotation.Annotation[] annotations1=field.getDeclaredAnnotations();
//
//        }




        for (String line :fileLines) {
            LocalDate foundationYear;
            String legalName="";
            String ownerOrganizationInn="";

           String[] parts= line.split(":");
            for (int i = 0; i <parts.length ; i++) {
                if (parts[i].equals("\"foundationYear\"")){
                    if (parts[i+1].equals("null")){
                        foundationYear=null;
                    } else {
                        foundationYear= LocalDate.parse(parts[i+1]);
                    }

                    try {
                        klass=Class.forName("itis.parsing.Park");
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    }

                    try {
                      field=  klass.getDeclaredField("foundationYear");
                    } catch (NoSuchFieldException e) {
                        e.printStackTrace();
                    }
                    field.setAccessible(true);

                    Annotation[] annotations= (Annotation[]) field.getDeclaredAnnotations();
                    for (Annotation annotation:annotations) {
                        if (annotation.equals(FieldName.class)){
                            boolean fieldNameFlag=false;

                        for (String line2 :fileLines) {
                             if (line2.equals(annotation.getValue())){
                                 foundationYear=LocalDate.parse(line2);
                                 fieldNameFlag=true;
                             }
                        }

                        if (fieldNameFlag==false){
                            System.out.println("Ошибка обработки fieldName");
                        }

                        }
                         if (annotation.equals(MaxLength.class)){
                             int value = (int)annotation.getValue();
                            String l= foundationYear.toString();

                            char[] array=l.toCharArray();

                            if (array.length>value){
                                System.out.println("Ошибка! Недопустимая величина значения. ");
                            }


                         }
                         if (annotation.equals(NotBlank.class)){
                             if (foundationYear==null){
                                 System.out.println("Ошибка! Данное поле не может быть null");
                             }

                         }



                    }


                    try {
                        Field field1=klass.getDeclaredField("foundationYear");
                        field1.setAccessible(true);
                        try {
                            field1.set(o,foundationYear);
                        } catch (IllegalAccessException e) {
                            e.printStackTrace();
                        }
                    } catch (NoSuchFieldException e) {
                        e.printStackTrace();
                    }


                }


                if (parts[i].equals("\"legalName\"")){
                    if (parts[i].equals("null")){
                        legalName=null;
                    }else {
                        legalName=parts[i+1];
                    }
                    try {
                        klass=Class.forName("itis.parsing.Park");
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    }

                    try {
                        field=  klass.getDeclaredField("legalName");
                    } catch (NoSuchFieldException e) {
                        e.printStackTrace();
                    }
                    field.setAccessible(true);

                    Annotation[] annotations= (Annotation[]) field.getDeclaredAnnotations();
                    for (Annotation annotation:annotations) {
                        if (annotation.equals(FieldName.class)){
                            boolean legalNameFlag=false;

                            for (String line2 :fileLines) {
                                if (line2.equals(annotation.getValue())){
                                    legalName=line2;
                                    legalNameFlag=true;
                                }
                            }

                            if (legalNameFlag==false){
                                System.out.println("Ошибка обработки fieldName");
                            }

                        }
                        if (annotation.equals(MaxLength.class)){
                            int value = (int)annotation.getValue();


                            char[] array=legalName.toCharArray();

                            if (array.length>value){
                                System.out.println("Ошибка! Недопустимая величина значения. ");
                            }


                        }
                        if (annotation.equals(NotBlank.class)){
                            if (legalName==null){
                                System.out.println("Ошибка! Данное поле не может быть null");
                            }

                        }



                    }

                    try {
                        Field field1=klass.getDeclaredField("legalName");
                        field1.setAccessible(true);
                        try {
                            field1.set(o,legalName);
                        } catch (IllegalAccessException e) {
                            e.printStackTrace();
                        }
                    } catch (NoSuchFieldException e) {
                        e.printStackTrace();
                    }



                }





                if (parts[i].equals("\"ownerOrganizationInn\"")){
                    ownerOrganizationInn=parts[i+1];
                    if (parts[i+1].equals("null")){
                        ownerOrganizationInn=null;
                    } else {
                        ownerOrganizationInn= parts[i+1];
                    }

                    try {
                        klass=Class.forName("itis.parsing.Park");
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    }

                    try {
                        field=  klass.getDeclaredField("ownerOrganizationInn");
                    } catch (NoSuchFieldException e) {
                        e.printStackTrace();
                    }
                    field.setAccessible(true);

                    Annotation[] annotations= (Annotation[]) field.getDeclaredAnnotations();
                    for (Annotation annotation:annotations) {
                        if (annotation.equals(FieldName.class)){
                            boolean ownerFlag=false;

                            for (String line2 :fileLines) {
                                if (line2.equals(annotation.getValue())){
                                    foundationYear=LocalDate.parse(line2);
                                    ownerFlag=true;
                                }
                            }

                            if (ownerFlag==false){
                                System.out.println("Ошибка обработки fieldName");
                            }

                        }
                        if (annotation.equals(MaxLength.class)){
                            int value = (int)annotation.getValue();

                            char[] array=ownerOrganizationInn.toCharArray();

                            if (array.length>value){
                                System.out.println("Ошибка! Недопустимая величина значения. ");
                            }


                        }
                        if (annotation.equals(NotBlank.class)){
                            if (ownerOrganizationInn==null){
                                System.out.println("Ошибка! Данное поле не может быть null");
                            }

                        }



                    }
                    try {
                        Field field1=klass.getDeclaredField("ownerOrganizationInn");
                        field1.setAccessible(true);
                        try {
                            field1.set(o,ownerOrganizationInn);
                        } catch (IllegalAccessException e) {
                            e.printStackTrace();
                        }
                    } catch (NoSuchFieldException e) {
                        e.printStackTrace();
                    }
                }

            }
        }



        //write your code here
        return (Park) o;
    }
}
