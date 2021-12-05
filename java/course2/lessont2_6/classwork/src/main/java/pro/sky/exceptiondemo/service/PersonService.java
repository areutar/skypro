package pro.sky.exceptiondemo.service;

public interface PersonService {
    String getPerson(Integer id);

    String getPerson(Integer id, Integer picCode);

    String updatePerson(String name, boolean block, Integer id);

    String addPerson(String name, boolean block);

    String removePerson(Integer id);
}
