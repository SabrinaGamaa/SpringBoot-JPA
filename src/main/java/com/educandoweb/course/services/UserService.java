package com.educandoweb.course.services;

import com.educandoweb.course.entities.User;
import com.educandoweb.course.repositories.UserRepository;
import com.educandoweb.course.services.exceptions.DatabaseException;
import com.educandoweb.course.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    public List<User> findAll(){
        return repository.findAll();
    }

    public User findById(Long id){
        Optional<User> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ResourceNotFoundException(id));
    }

    public User insert(User obj){
        return repository.save(obj);
    }

    public void delete(Long id){
        boolean exists = repository.existsById(id);
        if (!exists) {
            System.out.println("ID " + id + " não existe");
            throw new ResourceNotFoundException(id);
        }

        try {
            repository.deleteById(id);
        }catch (DataIntegrityViolationException e){
            throw new DatabaseException(e.getMessage());
        }
    }

    // Método responsável por atualizar um usuário com base no ID fornecido.
    // Primeiro, obtém uma referência ao usuário existente no banco de dados.
    // Em seguida, atualiza os dados desse usuário com as novas informações recebidas (obj).
    // Por fim, salva e retorna o usuário atualizado.
    public User update(Long id, User obj) {
        User entity = repository.getReferenceById(id);
        updateData(entity, obj);
        return repository.save(entity);
    }

    // Método responsável por atualizar os dados de um usuário existente
    // com as informações fornecidas em outro objeto User (obj).
    // Apenas os campos nome, e-mail e telefone são atualizados.
    public void updateData(User entity, User obj) {
        entity.setName(obj.getName());
        entity.setEmail(obj.getEmail());
        entity.setPhone(obj.getPhone());
    }
}
