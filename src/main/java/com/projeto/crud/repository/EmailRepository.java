package com.projeto.crud.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.projeto.crud.model.Email;

@Repository
public interface EmailRepository extends JpaRepository<Email, Long> {

}
