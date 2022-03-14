package com.tutorial.oauth2.keycloak.controller;

import com.tutorial.oauth2.keycloak.dto.ResponseMessage;
import com.tutorial.oauth2.keycloak.model.Foo;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;


@RestController
@RequestMapping("/foo")
@CrossOrigin
public class FooController {

    private List<Foo> foos = Stream.of(new Foo(1,"foo 1"),new Foo(2,"foo 2"), new Foo(3,"foo 3")).collect(Collectors.toList());


    @GetMapping("/list")
    //@RolesAllowed("backend-user")
    public ResponseEntity<List<Foo>> list(){
        return  ResponseEntity.ok(foos);
    }

    @GetMapping("/detail/{id}")
    @RolesAllowed("backend-user")
    public ResponseEntity<Foo> detail(@PathVariable int id){
        Foo find = foos.stream().filter(foo->foo.getId()==id).findFirst().orElse(null);

        if(find==null){
            return new ResponseEntity<>(find, HttpStatus.NOT_FOUND);
        }else{
            return ResponseEntity.ok(find);
        }

    }



    @PostMapping(value = "/create",produces = MediaType.APPLICATION_PROBLEM_JSON_VALUE)
    //@RolesAllowed("backend-admin")
    public ResponseEntity<?> create(@RequestBody Foo foo){

        int maxIndex=foos.stream().max(Comparator.comparing(m->m.getId())).get().getId();

        foo.setId(maxIndex+1);
        foos.add(foo);
        return new ResponseEntity(new ResponseMessage("it was added"), HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    @RolesAllowed("backend-admin")
    public ResponseEntity<?> update(@RequestBody Foo foo, @PathVariable int id){
        Foo find = foos.stream().filter(f->f.getId()==id).findFirst().orElse(null);

        find.setName(foo.getName());
        return new ResponseEntity<>(new ResponseMessage("Updated sussefully"),HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{id}")
    @RolesAllowed("backend-admin")
    public ResponseEntity<?> delete(@PathVariable int id){

        Foo find = foos.stream().filter(f->f.getId()==id).findFirst().orElse(null);
        foos.remove(find);
        return new ResponseEntity<>(new ResponseMessage("It was deleted seccessfully"),HttpStatus.OK);
    }




}
