package com.projeto.crud.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.projeto.crud.model.Cliente;
import com.projeto.crud.model.Email;
import com.projeto.crud.model.Telefone;
import com.projeto.crud.repository.ClienteRepository;
import com.projeto.crud.repository.EmailRepository;
import com.projeto.crud.repository.TelefoneRepository;

@Service
public class ClienteService {
 
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private TelefoneService telefoneService;
	
	@Autowired
	private EmailService emailService;
	
	@Autowired
	private TelefoneRepository telefoneRepository;
	
	@Autowired
	private EmailRepository emailRepository;
	
	public Cliente atualizar(Long codigo, Cliente cliente) {
		 Cliente clienteSalvo = this.clienteRepository.findById(codigo)
			      .orElseThrow(() -> new EmptyResultDataAccessException(1));
		 if(clienteSalvo.getTelefones().isEmpty()) {
			 for (Telefone telefone : cliente.getTelefones()) {
					telefone.setCliente(clienteSalvo);
					telefoneRepository.save(telefone);
					
				}
			 
		 }else {
			 for (Telefone telefone : cliente.getTelefones()) {
				 if(telefone.getCodigo() == null || telefone.getCodigo() == 0) {
					 telefone.setCodigo(null);
					 telefone.setCliente(clienteSalvo);
					 telefoneRepository.save(telefone);
				 }else {					 
					 //System.out.println(" atualizar cod tel - " +telefone.getCodigo());
					 telefoneService.atualizar(telefone.getCodigo(), telefone);
				 }
					
			}
		 }
		 
		 if(clienteSalvo.getEmails().isEmpty()) {
			 for (Email email : cliente.getEmails()) {
					email.setCliente(clienteSalvo);
					emailRepository.save(email);
					
				}
			 
		 }else {
			 for (Email email : cliente.getEmails()) {
				 if(email.getCodigo() == null || email.getCodigo() == 0) {
					 email.setCodigo(null);
					 email.setCliente(clienteSalvo);
					 emailRepository.save(email);
				 }else {					 
					 //System.out.println(" atualizar cod email - " +email.getCodigo());
					 emailService.atualizar(email.getCodigo(), email);
				 }
					
			}
		 }
		BeanUtils.copyProperties(cliente, clienteSalvo, "codigo");
		return this.clienteRepository.save(clienteSalvo);
	}
 
}
