package com.local.socialbooks.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.local.socialbooks.domain.Autor;
import com.local.socialbooks.repository.AutoresRepository;
import com.local.socialbooks.services.exceptions.AutorExistenteException;
import com.local.socialbooks.services.exceptions.AutorNaoEncontradoException;

@Service
public class AutoresService {
	
	@Autowired
	private AutoresRepository autoresRepository;	
	
	public List<Autor> listar(){
		return autoresRepository.findAll();
	}	
	
	public Autor salvar(Autor autor){
		if(autor.getId() != null){
			Autor a = this.autoresRepository.findOne(autor.getId());
			
			if(a != null){
				throw new AutorExistenteException("o autor já existe.");
			}
		}
		return this.autoresRepository.save(autor);
	}	
	
	public Autor buscar(Long id){
		Autor autor = this.autoresRepository.findOne(id);
		
		if(autor == null){
			throw new AutorNaoEncontradoException("o autor não pôde ser encontrado.");
		}
		return autor;
	}	
}