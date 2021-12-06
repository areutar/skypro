package pro.sky.exceptiondemo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pro.sky.exceptiondemo.data.Document;
import pro.sky.exceptiondemo.service.DocumentService;

@RestController
public class DocumentController {
    private final DocumentService documentService;

    public DocumentController(DocumentService documentService) {
        this.documentService = documentService;
    }

    @GetMapping(path = "/post/person/{id}/document")
    public Document getPerson(@PathVariable("id") Integer id,
                              @RequestParam("passportNum") String passportNum,
                              @RequestParam("inn") String inn) {
        return documentService.addDocumentToPerson(id, passportNum, inn);
    }

    @GetMapping(path = "/get/person/{id}/document")
    public Document getPerson(@PathVariable("id") Integer id) {
        return documentService.getDocumentByPerson(id);
    }
}
