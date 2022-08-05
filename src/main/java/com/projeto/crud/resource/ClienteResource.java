package com.projeto.crud.resource;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.projeto.crud.event.RecursoCriadoEvent;
import com.projeto.crud.model.Cliente;
import com.projeto.crud.model.Email;
import com.projeto.crud.model.Telefone;
import com.projeto.crud.repository.ClienteRepository;
import com.projeto.crud.repository.EmailRepository;
import com.projeto.crud.repository.TelefoneRepository;
import com.projeto.crud.service.ClienteService;

@RestController
@RequestMapping("/clientes")
public class ClienteResource {
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private TelefoneRepository telefoneRepository;
	
	@Autowired
	private EmailRepository emailRepository;
	
	@Autowired
	private ClienteService clienteService;
	
	
	
	@Autowired
	private ApplicationEventPublisher publisher;
	
	@GetMapping
	@PreAuthorize("hasAuthority('ROLE_PESQUISAR_CLIENTE')")
	public Page<Cliente> pesquisar(@RequestParam(required = false, defaultValue = "") String nome, Pageable pageable) {
		return clienteRepository.findByNomeContaining(nome, pageable);
	}
	
	@PostMapping
	@PreAuthorize("hasAuthority('ROLE_CADASTRAR_CLIENTE')")
	public ResponseEntity<Cliente> criar(@Valid @RequestBody Cliente cliente, HttpServletResponse response){
		Cliente clienteSalvo = clienteRepository.save(cliente);
		
		for (Telefone telefone : cliente.getTelefones()) {
			telefone.setCliente(clienteSalvo);
			telefoneRepository.save(telefone);
			
		}
		for (Email email : cliente.getEmails()) {
			email.setCliente(clienteSalvo);
			emailRepository.save(email);
			
		}
		
		publisher.publishEvent(new RecursoCriadoEvent(this, response, clienteSalvo.getCodigo()));
		
		return ResponseEntity.status(HttpStatus.CREATED).body(clienteSalvo);		
	}
	
	@GetMapping("/{codigo}")
	@PreAuthorize("hasAuthority('ROLE_PESQUISAR_CLIENTE')")
	public ResponseEntity<Cliente> buscarPeloCodigo(@PathVariable Long codigo) {
		Cliente cliente = this.clienteRepository.findById(codigo).orElse(null);
		return cliente != null ? ResponseEntity.ok(cliente) : ResponseEntity.notFound().build();
	    
	}
	
	@DeleteMapping("/{codigo}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@PreAuthorize("hasAuthority('ROLE_REMOVER_CLIENTE')")
	public void removerCliente (@PathVariable Long codigo) {
		Cliente cliente =  this.clienteRepository.findById(codigo).orElse(null);
	    
	    for (Telefone telefone : cliente.getTelefones()) {			
			this.telefoneRepository.delete(telefone);
		}	   
	    for (Email email : cliente.getEmails()) {			
			this.emailRepository.delete(email);
		}
	    
	    this.clienteRepository.delete(cliente);
	}
	
	@DeleteMapping("/telefone/{codigo}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@PreAuthorize("hasAuthority('ROLE_REMOVER_CLIENTE')")
	public void removerTelefone (@PathVariable Long codigo) {
		Telefone telefone =  this.telefoneRepository.findById(codigo).orElse(null);
	    
	    this.telefoneRepository.delete(telefone);
	}
	
	@DeleteMapping("/email/{codigo}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@PreAuthorize("hasAuthority('ROLE_REMOVER_CLIENTE')")
	public void removerEmail (@PathVariable Long codigo) {
		Email email =  this.emailRepository.findById(codigo).orElse(null);
	    
	    this.emailRepository.delete(email);
	}
	
	@PutMapping("/{codigo}")
	@PreAuthorize("hasAuthority('ROLE_CADASTRAR_CLIENTE')")
	public ResponseEntity<Cliente> atualizar(@PathVariable Long codigo, @Valid @RequestBody Cliente cliente) {
		Cliente clienteSalva = clienteService.atualizar(codigo, cliente);
		
		return ResponseEntity.ok(clienteSalva);
	}
	
	
	
}
