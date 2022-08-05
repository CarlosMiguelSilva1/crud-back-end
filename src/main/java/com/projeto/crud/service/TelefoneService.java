package com.projeto.crud.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.projeto.crud.model.Telefone;
import com.projeto.crud.repository.TelefoneRepository;

@Service
public class TelefoneService {

	@Autowired
	private TelefoneRepository telefoneRepository;
	
	public Telefone atualizar(Long codigo, Telefone telefone) {
		 Telefone telefoneSalvo = this.telefoneRepository.findById(codigo)
			      .orElseThrow(() -> new EmptyResultDataAccessException(1));

		BeanUtils.copyProperties(telefone, telefoneSalvo, "codigo");
		return this.telefoneRepository.save(telefoneSalvo);
	}
}
