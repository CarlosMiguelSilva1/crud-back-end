package com.projeto.crud.model;

import java.util.List;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;


@Entity
@Table(name = "cliente")
public class Cliente {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long codigo;

	@NotNull
	private String nome;
	
	@NotNull
	private Long cpf;

	@Embedded
	@NotNull
	private Endereco endereco;

	
	@OneToMany(mappedBy = "cliente")//nome da variavel de relacionamento da classe de telefone
	private List<Email> emails;
	
	
	//Telefones
	@OneToMany(mappedBy = "cliente")//nome da variavel de relacionamento da classe de telefone
	private List<Telefone> telefones;
	
	
	
	
	//Telefone
	
	public List<Telefone> getTelefones() {
		return telefones;
	}



	public void setTelefones(List<Telefone> telefones) {
		this.telefones = telefones;
	}



	public Long getCodigo() {
		return codigo;
	}	



	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}


	public String getNome() {
		return nome;
	}


	public void setNome(String nome) {
		this.nome = nome;
	}


	public Endereco getEndereco() {
		return endereco;
	}


	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}


	

	public List<Email> getEmails() {
		return emails;
	}



	public void setEmails(List<Email> emails) {
		this.emails = emails;
	}



	public Long getCpf() {
		return cpf;
	}


	public void setCpf(Long cpf) {
		this.cpf = cpf;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codigo == null) ? 0 : codigo.hashCode());
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cliente other = (Cliente) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		return true;
	}
	
	
}
