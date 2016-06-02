package com.redis.ex;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

public class Application {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("spring-config.xml");
        PersonRepo personRepo = (PersonRepo)context.getBean("personRepo");

        Person person = new Person();
        person.setId("1");
        person.setAge(55);
        person.setGender(Person.Gender.Female);
        person.setName("Oracle");
        try {
            person.setDob(new SimpleDateFormat("dd-MM-yyyy").parse("1991-09-14"));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        personRepo.save(person);

        Person person2 = new Person();
        person2.setId("2");
        person2.setAge(60);
        person2.setGender(Person.Gender.Male);
        person2.setName("TheArchitect");
        try {
            person2.setDob(new SimpleDateFormat("dd-MM-yyyy").parse("1991-09-14"));
        } catch (ParseException e) {
            e.printStackTrace();
        }


        personRepo.save(person2);

        Person person3 = new Person();
        person3.setId("5");
        person3.setAge(24);
        person3.setGender(Person.Gender.Male);
        person3.setName("Nakul");
        try {
            person3.setDob(new SimpleDateFormat("dd-MM-yyyy").parse("1991-09-14"));
        } catch (ParseException e) {
            e.printStackTrace();
        }


        personRepo.save(person3);

        System.out.println("Finding the One : "+personRepo.find("2"));

        Map<Object,Object> personMatrixMap = personRepo.findAll();

        System.out.println("Currently in the Redis Matrix");

        System.out.println(personMatrixMap);

        System.out.println("Deleting The Architect ");

        personRepo.delete("2");

        personMatrixMap = personRepo.findAll();

        System.out.println("Remnants .. : ");

        System.out.println(personMatrixMap);
    }
}
