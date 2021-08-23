package posutfpr.banco.ativcinco.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import posutfpr.banco.ativcinco.entity.DepartamentEntity;
import posutfpr.banco.ativcinco.entity.FuncEntity;
import posutfpr.banco.ativcinco.repository.DepartamentRepository;
import posutfpr.banco.ativcinco.repository.FuncRepository;

//UTFPR — Universidade Tecnológica Federal do Paraná UTFPR
//XXI Curso de Especialização em Tecnologia Java (2021_01)
//Banco de Dados
//Atividade 8
//Cleber dos Santos Prestes de Oliveira


@Service
public class DepartamentService {

	@Autowired
	private DepartamentRepository departamentRepository;

	public DepartamentEntity saveDepartament(DepartamentEntity departament) {
		return departamentRepository.save(departament);
	}

	public Optional<DepartamentEntity> findDepartById(Long id) {
		return departamentRepository.findById(id);
	}

	public List<DepartamentEntity> findAllDepart() {
		return departamentRepository.findAll();
	}

	public List<DepartamentEntity> findByName(String name) {

		DepartamentEntity depart = new DepartamentEntity();
		depart.setName(name);

		ExampleMatcher matcher = ExampleMatcher.matching().withIgnoreCase()
				.withStringMatcher(ExampleMatcher.StringMatcher.ENDING);

		Example<DepartamentEntity> example = Example.of(depart, matcher);

		return departamentRepository.findAll(example);
	}

	public List<DepartamentEntity> findAllByName() {

		Sort sort = Sort.by(Sort.Direction.ASC, "name");

		return departamentRepository.findAll(sort);
	}

	public Page<DepartamentEntity> paginResults() {

		Pageable pageable = PageRequest.of(0, 3, Sort.by(Sort.Direction.ASC, "name"));

		return departamentRepository.findAll(pageable);
	}

	public void delDepartament(Long id) {

		departamentRepository.deleteById(id);

		System.out.println("Delete departament with id :" + id);
	}

	public void deleteAllDep() {
		System.out.println("Deletando todos os departamentos");
		departamentRepository.deleteAll();
	}
	
	public DepartamentEntity findFirstDep() {
		return departamentRepository.findFirstBy();
	}
	
	public DepartamentEntity findDepartByName(String name) {

		return departamentRepository.findByName(name);
	}
	
	
	//Atividade 8
	@Autowired
	   private DepartamentRepository departamentoRepositoyr;
	   @Autowired
	   private FuncRepository funcRepository;
	
	
	@Transactional()
	public void saveNewDepAndFunc(DepartamentEntity departamento, FuncEntity funcionario){
	  departamentoRepositoyr.save(departamento);
	  funcionario.setDepart(departamento);
	  funcRepository.save(funcionario);
	}
	
	
}
