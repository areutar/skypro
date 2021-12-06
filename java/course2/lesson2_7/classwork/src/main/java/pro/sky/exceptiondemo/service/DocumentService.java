package pro.sky.exceptiondemo.service;

import pro.sky.exceptiondemo.data.Document;

public interface DocumentService {
    Document addDocumentToPerson(Integer id, String passportNum, String inn);

    Document getDocumentByPerson(Integer id);
}
