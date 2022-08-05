package com.projeto.crud.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.projeto.crud.model.Email;
import com.projeto.crud.repository.EmailRepository;

@Service
public class EmailService {

	@Autowired
	private EmailRepository emailRepository;
	
	public Email atualizar(Long codigo, Email email) {
		 Email emailSalvo = this.emailRepository.findById(codigo)
			      .orElseThrow(() -> new EmptyResultDataAccessException(1));

		BeanUtils.copyProperties(email, emailSalvo, "codigo");
		return this.emailRepository.save(emailSalvo);
	}
}
