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
        return personService.getPersonName(id);
    }

    @GetMapping(path = "/get/fsb/person/{id}")
    public String getPerson(@PathVariable("id") Integer id,
                            @RequestParam("picCode") Integer picCode) {
        return personService.getPersonName(id, picCode);
    }

    @GetMapping(path = "/update/{id}")
    public String updatePerson(@RequestParam("name") String name,
                               @RequestParam("block") boolean block,
                               @PathVariable("id") Integer id) {
        return personService.updatePerson(name, block, id);
    }

    @GetMapping(path = "/post/person")
    public String addPerson(@RequestParam("name") String name,
                            @RequestParam("block") boolean block) {
        return personService.addPerson(name, block);
    }

    @GetMapping(path = "/delete/person/{id}")
    public String removePerson(@PathVariable("id") Integer id) {
        return personService.removePerson(id);
    }
}
