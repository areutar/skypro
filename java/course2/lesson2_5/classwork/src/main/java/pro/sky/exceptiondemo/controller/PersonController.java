package pro.sky.exceptiondemo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pro.sky.exceptiondemo.service.PersonService;

@RestController
public class PersonController {
    private final PersonService personService;


    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping(path = "/get/person/{id}")
    public String getPerson(@PathVariable("id") Integer id) {
        return personService.getPerson(id);
    }

    @GetMapping(path = "/get/fsb/person/{id}")
    public String getPerson(@PathVariable("id") Integer id,
                            @RequestParam("pincode") Integer pincode) {
        return personService.getPerson(id, pincode);
    }

    @GetMapping(path = "/update/{id}")
    public String updatePerson(@RequestParam("name") String name,
                               @RequestParam("block") boolean block,
                               @PathVariable("id") Integer id) {
        return personService.updatePerson(name, block, id);
    }

}
