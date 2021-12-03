package pro.sky.exceptiondemo.service;

public interface PersonService {
    String getPerson(Integer id);

    String getPerson(Integer id, Integer pincode);

    String updatePerson(String name, boolean block, Integer id);
}
