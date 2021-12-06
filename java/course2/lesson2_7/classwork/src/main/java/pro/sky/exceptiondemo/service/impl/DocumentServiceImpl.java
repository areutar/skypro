package pro.sky.exceptiondemo.service.impl;

import org.springframework.stereotype.Service;
import pro.sky.exceptiondemo.data.Document;
import pro.sky.exceptiondemo.data.Person;
import pro.sky.exceptiondemo.service.DocumentService;
import pro.sky.exceptiondemo.service.PersonService;

import java.util.HashMap;
import java.util.Map;

@Service
public class DocumentServiceImpl implements DocumentService {
    private final PersonService personService;
    private Map<Person, Document> personDocumentMap;

    public DocumentServiceImpl(PersonService personService) {
        this.personService = personService;
        personDocumentMap = new HashMap<>();
    }

    @Override
    public Document addDocumentToPerson(Integer id, String passportNum, String inn) {
        Document document = new Document();
        document.setPassportNum(passportNum);
        document.setInn(inn);
        Person person = personService.getPerson(id);
        if (person != null) {
            return personDocumentMap.put(person, document);
        } else {
            throw new IllegalArgumentException("Id неверный.");
        }
    }

    @Override
    public Document getDocumentByPerson(Integer id) {
        Person person = personService.getPerson(id);
        return personDocumentMap.get(person);
    }
}
