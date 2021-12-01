package pro.sky.exceptiondemo;

import org.springframework.stereotype.Service;

@Service
public class PersonServiceImpl implements PersonService {
    Person[] persons = {
            new Person("Василий Лановой", true),
            new Person("Герберт Уэлс", false),
            new Person("Елизавета Рачкова", true)
    };

    @Override
    public String getPerson(Integer id) {
        if (persons.length > id) {
            try {
                return getPersonWithoutPincode(id);
            } catch (NoAccessPersonException e) {
                return "Вы не имеете доступа к этому пользователю";

            }
        } else {
            return "Пользователь с id " + id + " не найден";
        }

//        try {
//            return persons[id];
//        } catch (ArrayIndexOutOfBoundsException e) {
//            return "Пользователь с id " + id + " не найден";
//        } catch (NullPointerException nullPointerException) {
//            return "Persons is null";
//        } finally {
//            return "Finally block";
//        }
    }

    private static final Integer code = 1234;

    @Override
    public String getPerson(Integer id, Integer pincode) {
        if (!pincode.equals(code)) {
            throw new BadPincodeException();
        }
        return persons[id].getName();
    }

    private String getPersonWithoutPincode(Integer id) throws NoAccessPersonException {
        if (persons[id].isBlock()) {
            throw new NoAccessPersonException();
        }
        return persons[id].getName();
    }
}
