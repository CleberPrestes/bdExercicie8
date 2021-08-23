package posutfpr.banco.ativcinco.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import posutfpr.banco.ativcinco.entity.DepartamentEntity;


//UTFPR — Universidade Tecnológica Federal do Paraná UTFPR
//XXI Curso de Especialização em Tecnologia Java (2021_01)
//Banco de Dados
//Atividade 8
//Cleber dos Santos Prestes de Oliveira


public interface DepartamentRepository extends JpaRepository<DepartamentEntity, Long> {
	
		
	DepartamentEntity findFirstBy();
	DepartamentEntity findByName(String name);
	
}