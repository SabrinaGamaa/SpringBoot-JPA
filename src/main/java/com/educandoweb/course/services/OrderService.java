package com.educandoweb.course.services;

import com.educandoweb.course.entities.Order;
import com.educandoweb.course.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

// Indica que essa classe é um serviço do Spring. Isso significa que ela contém regras de negócio
// e pode ser injetada em outras classes com @Autowired.
@Service
public class OrderService {

    // Injeta automaticamente uma instância de OrderRepository.
    // Isso permite acessar o banco de dados com operações pré-definidas como findAll(), findById(), save(), etc.
    @Autowired
    private OrderRepository repository;

    // Método para buscar todos os pedidos no banco de dados.
    // Usa o método findAll() do repositório JPA
    public List<Order> findAll(){
        return repository.findAll();
    }

    // Método para buscar um pedido específico pelo id.
    public Order findById(Long id){
        // O método findById retorna um Optional, que é uma forma segura de lidar com valores que podem ser nulos.
        Optional<Order> obj = repository.findById(id);

        // Usa o get() para obter o objeto de dentro do Optional.
        // Atenção: se o pedido não existir, isso pode lançar uma exceção NoSuchElementException.
        // Em aplicações reais, é comum tratar isso com try/catch ou lançar uma exceção personalizada.
        return obj.get();
    }
}
