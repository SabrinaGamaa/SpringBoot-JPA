package com.educandoweb.course.repositories;

import com.educandoweb.course.entities.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

// Pordeira colocar, mas a minha interface está herdando do JPArepository que já está registrado como oponente do string
//@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {
}
