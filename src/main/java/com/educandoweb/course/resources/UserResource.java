package com.educandoweb.course.resources;

import com.educandoweb.course.entities.User;
import com.educandoweb.course.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/users")
public class UserResource {

    @Autowired
    private UserService service;

    @GetMapping
    public ResponseEntity<List<User>> findAll(){
        List<User> list = service.findAll();
        return ResponseEntity.ok().body(list);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<User> findById(@PathVariable Long id){
        User obj = service.findById(id);
        return ResponseEntity.ok().body(obj);
    }

    @PostMapping
    public  ResponseEntity<User> insert(@RequestBody User obj){
        obj = service.insert(obj); // Salva o objeto no banco e retorna com ID preenchido

        URI uri = ServletUriComponentsBuilder   // Monta a URI do recurso criado: ex. http://localhost:8080/users/5
                .fromCurrentRequest()          // Pega a URL atual da requisição (ex. /users)
                .path("/{id}")                // Adiciona /{id} no final da URL
                .buildAndExpand(obj.getId()) // Substitui {id} pelo valor real do ID
                .toUri();                   // Converte para objeto URI
        return ResponseEntity.created(uri).body(obj); // Retorna 201 Created com Location no cabeçalho
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
