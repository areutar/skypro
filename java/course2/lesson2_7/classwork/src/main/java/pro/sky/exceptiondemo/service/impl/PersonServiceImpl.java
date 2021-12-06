package pro.sky.exceptiondemo.service.impl;

import org.springframework.stereotype.Service;
import pro.sky.exceptiondemo.data.Person;
import pro.sky.exceptiondemo.exception.BadPincodeException;
import pro.sky.exceptiondemo.exception.NoAccessPersonException;
import pro.sky.exceptiondemo.service.PersonService;

import java.util.HashMap;
import java.util.Map;

@Service
public class PersonServiceImpl implements PersonService {
    private Map<Integer, Person> persons;
    private static final Integer code = 1234;
    private Integer nextId = 0;

    public PersonServiceImpl() {
        this.persons = new HashMap<>();
        persons.put(getNextId(), new Person("Василий Лановой", false));
        persons.put(getNextId(), new Person("Герберт Уэллс", false));
        persons.put(getNextId(), new Person("Елизавета Рачкова", true));
    }

    private Integer getNextId() {
        Integer result = nextId;
        nextId = nextId + 1;
        return result;
    }

    @Override
    public String getPersonName(Integer id) {
        if (persons.containsKey(id)) {
            try {
                return getPersonWithoutPinCode(id);
            } catch (NoAccessPersonException e) {
                return getNoAccessMessage();
            }
        } else {
            return getNotFoundMessage(id);
        }
    }


    @Override
    public String getPersonName(Integer id, Integer pinCode) {
        if (!pinCode.equals(code)) {
            throw new BadPincodeException();
        }
        return persons.get(id).getName();
    }

    @Override
    public String updatePerson(String name, boolean block, Integer id) {
        if (persons.containsKey(id)) {
            Person person = persons.get(id);
            if (person.isBlock()) {
                return getNoAccessMessage();
            } else {
                person.setBlock(block);
                person.setName(name);
                return name;
            }
        } else {
            return getNotFoundMessage(id);
        }
    }

    @Override
    public String addPerson(String name, boolean block) {
        persons.put(getNextId(), new Person(name, block));
        return name;
    }

    @Override
    public String removePerson(Integer id) {
        if (persons.containsKey(id)) {
            return persons.remove(id).getName();
        } else {
            return getNotFoundMessage(id);
        }
    }

    @Override
    public Person getPerson(Integer id) {
        return persons.get(id);
    }

    private String getPersonWithoutPinCode(Integer id) throws NoAccessPersonException {
        if (persons.get(id).isBlock()) {
            throw new NoAccessPersonException();
        }
        return persons.get(id).getName();
    }

    private String getNoAccessMessage() {
        return "Вы не имеете доступа к этому пользователю";
    }

    private String getNotFoundMessage(Integer id) {
        return "Пользователь с id " + id + " не найден";
    }
}
