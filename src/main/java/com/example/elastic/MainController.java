package com.example.elastic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.query.*;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/")
public class MainController {

    private ElasticsearchOperations elasticsearchOperations;
    @Autowired
    PersonRepository personRepository;

    public MainController(ElasticsearchOperations elasticsearchOperations) {
        this.elasticsearchOperations = elasticsearchOperations;
    }

    @PostMapping("/person")
    public String save(@RequestBody Person person) {

        Person p = new Person();
        p.setAddress("g");
        p.setAge(1);
        p.setId(2);
        p.setName("ewrwer");

        Person p2 = personRepository.save(p);

        return String.valueOf(p2);
    }



    @GetMapping("/person/{id}")
    public Person findById(@PathVariable("id")  Long id) {
        Person person = elasticsearchOperations
                .queryForObject(GetQuery.getById(id.toString()), Person.class);
        return person;
    }

    @DeleteMapping("person/{id}")
    public HttpEntity del(@PathVariable("id")  Integer id) {
        DeleteQuery deleteQuery = new DeleteQuery();
        boolean ok = false;

        Person p = new Person();
        p.setId(id);

        personRepository.delete(p);

        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("MyResponseHeader", String.valueOf(ok));
        HttpEntity<String> entity = new HttpEntity<String>("header", responseHeaders);
        return entity;
    }
}