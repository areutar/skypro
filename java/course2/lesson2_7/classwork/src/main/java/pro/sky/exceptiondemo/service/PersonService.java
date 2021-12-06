package pro.sky.exceptiondemo.service;

import pro.sky.exceptiondemo.data.Person;

public interface PersonService {
    String getPersonName(Integer id);

    String getPersonName(Integer id, Integer picCode);

    String updatePerson(String name, boolean block, Integer id);

    String addPerson(String name, boolean block);

    String removePerson(Integer id);

    Person getPerson(Integer id);
}
