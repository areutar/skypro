package pro.sky.exceptiondemo.service.impl;

import org.springframework.stereotype.Service;
import pro.sky.exceptiondemo.data.Person;
import pro.sky.exceptiondemo.service.PersonService;
import pro.sky.exceptiondemo.exception.BadPincodeException;
import pro.sky.exceptiondemo.exception.NoAccessPersonException;
import pro.sky.exceptiondemo.service.list.MyList;

@Service
public class PersonServiceImpl implements PersonService {
    private MyList persons;

    public PersonServiceImpl() {
        this.persons = new MyList();
        persons.add(new Person("Василий Лановой", true));
        persons.add(new Person("Герберт Уэллс", false));
        persons.add(new Person("Елизавета Рачкова", true));
    }

    private static final Integer code = 1234;

    @Override
    public String getPerson(Integer id) {
        if (persons.size() > id) {
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
    public String getPerson(Integer id, Integer picCode) {
        if (!picCode.equals(code)) {
            throw new BadPincodeException();
        }
        return persons.get(id).getName();
    }

    @Override
    public String updatePerson(String name, boolean block, Integer id) {
        if (persons.size() > id) {
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
        persons.add(new Person(name, block));
        return name;
    }

    @Override
    public String removePerson(Integer id) {
        if (persons.size() > id) {
            return persons.remove(id).getName();
        } else {
            return getNotFoundMessage(id);
        }
    }

    private String getPersonWithoutPincode(Integer id) throws NoAccessPersonException {
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
