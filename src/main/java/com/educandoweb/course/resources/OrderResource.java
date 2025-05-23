package com.educandoweb.course.resources;

import com.educandoweb.course.entities.Order;
import com.educandoweb.course.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

// Define que essa classe é um controlador REST, ou seja, ela irá responder a requisições HTTP (GET, POST, etc.)
@RestController

// Define o caminho base para os endpoints desta classe. Exemplo: http://localhost:8080/orders
@RequestMapping(value = "/orders")
public class OrderResource {

    // Injeta automaticamente uma instância da classe OrderService.
    // Isso permite separar a lógica de negócios da camada de controle.
    @Autowired
    private OrderService service;

    // Mapeia o método abaixo para requisições HTTP GET em "/orders".
    // Este método serve para retornar todos os pedidos (orders) do sistema.
    @GetMapping
    public ResponseEntity<List<Order>> findAll(){
        // Busca todos os pedidos utilizando o service.
        List<Order> list = service.findAll();
        // Retorna uma resposta HTTP 200 (OK) com a lista de pedidos no corpo.
        return ResponseEntity.ok().body(list);
    }

    // Mapeia o método abaixo para requisições HTTP GET em "/orders/{id}".
    // O "{id}" é um parâmetro de caminho, usado para buscar um pedido específico.
    @GetMapping(value = "/{id}")
    public ResponseEntity<Order> findById(@PathVariable Long id){

        // Utiliza o service para buscar o pedido pelo id fornecido na URL.
        Order obj = service.findById(id);
        return ResponseEntity.ok().body(obj);
    }
}
