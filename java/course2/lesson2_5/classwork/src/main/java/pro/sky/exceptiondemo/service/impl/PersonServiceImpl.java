package pro.sky.exceptiondemo.service.impl;

import org.springframework.stereotype.Service;
import pro.sky.exceptiondemo.data.Person;
import pro.sky.exceptiondemo.service.PersonService;
import pro.sky.exceptiondemo.exception.BadPincodeException;
import pro.sky.exceptiondemo.exception.NoAccessPersonException;

@Service
public class PersonServiceImpl implements PersonService {
    Person[] persons = {
            new Person("Василий Лановой", true),
            new Person("Герберт Уэлс", false),
            new Person("Елизавета Рачкова", true)
    };
    private static final Integer code = 1234;

    @Override
    public String getPerson(Integer id) {
        if (persons.length > id) {
            try {
                return getPersonWithoutPincode(id);
            } catch (NoAccessPersonException e) {
                return getNoAccessMessage();

            }
        } else {
            return getNotFoundMessage(id);
        }
    }


    @Override
    public String getPerson(Integer id, Integer pincode) {
        if (!pincode.equals(code)) {
            throw new BadPincodeException();
        }
        return persons[id].getName();
    }

    @Override
    public String updatePerson(String name, boolean block, Integer id) {
        if (persons.length > id) {
            Person person = persons[id];
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

    private String getPersonWithoutPincode(Integer id) throws NoAccessPersonException {
        if (persons[id].isBlock()) {
            throw new NoAccessPersonException();
        }
        return persons[id].getName();
    }

    private String getNoAccessMessage() {
        return "Вы не имеете доступа к этому пользователю";
    }

    private String getNotFoundMessage(Integer id) {
        return "Пользователь с id " + id + " не найден";
    }
}
