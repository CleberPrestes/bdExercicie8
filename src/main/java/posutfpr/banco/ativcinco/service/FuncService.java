package posutfpr.banco.ativcinco.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import posutfpr.banco.ativcinco.entity.DepartamentEntity;
import posutfpr.banco.ativcinco.entity.FuncEntity;
import posutfpr.banco.ativcinco.repository.FuncRepository;


//UTFPR — Universidade Tecnológica Federal do Paraná UTFPR
//XXI Curso de Especialização em Tecnologia Java (2021_01)
//Banco de Dados
//Atividade 8
//Cleber dos Santos Prestes de Oliveira


@Service
public class FuncService {

	@Autowired
	private FuncRepository funcRepository;

	public FuncEntity saveFunc(FuncEntity funcEntity) {
		return funcRepository.save(funcEntity);
	}
	
	
	//Atividade 6
			
	public Optional<FuncEntity> findFuncById(Long id) {
		return funcRepository.findById(id);
	}

	public List<FuncEntity> findAllFunc() {
		return funcRepository.findAll();
	}

	public List<FuncEntity> findByName(String name) {

		FuncEntity funct = new FuncEntity();
		funct.setName(name);

		ExampleMatcher matcher = ExampleMatcher.matching().withIgnoreCase()
				.withStringMatcher(ExampleMatcher.StringMatcher.ENDING);

		Example<FuncEntity> example = Example.of(funct, matcher);

		return funcRepository.findAll(example);
	}

	public List<FuncEntity> findByDependents(Integer depend) {

		FuncEntity funct = new FuncEntity();
		funct.setDependents(depend);
		Example<FuncEntity> example = Example.of(funct);

		return funcRepository.findAll(example);
	}

	public void deleteFuncById(Long idUsuario) {
		System.out.println("Deletando usuario com id " + idUsuario);
		funcRepository.deleteById(idUsuario);
	}

	public void deleteAllFunc() {
		System.out.println("Deletando todos os usuarios");
		funcRepository.deleteAll();
	}

	public FuncEntity findByNameAndDep(String name, Integer dependents) {
		
		return funcRepository.findByNameAndDependents(name, dependents);
		
	}
	
	public List<FuncEntity> findFuncByDep(String name) {
		return funcRepository.findByDepartName(name);
	}
	
	public FuncEntity highestSalary() {
		
		return funcRepository.findTopByOrderByPayDesc();
		
	}
	
	public List<FuncEntity> threeHighestSalary() {
		return funcRepository.findFirst3ByOrderByPayDesc();
	}
	
	public List<FuncEntity> funcNoDependents() {
		return funcRepository.findByFuncNoDependents();
	}
	
	public List<FuncEntity> funcBiggerPay(Long valor) {
		return funcRepository.findByPayBigger(valor);
	}
	
	public List<FuncEntity> funcBiggerPayNative(Long valor) {
		return funcRepository.findByPay(valor);
	}
	
	public List<FuncEntity> findByDepNamedQuery(Integer valor) {
		return funcRepository.findByDepNamedQuery(valor);
	}
	
	
	public List<FuncEntity> findByPartNameNativeQuery(String nome) {
		
		nome = "%" + nome +"%";
		System.out.println("O que foi recebido no service "+ nome);
		return funcRepository.findByPartOfNameNativeQuery(nome);
	}
	
		
	
	// Atividade 7.1
	public void salaryReadjustment(Integer reajuste) {
		
		funcRepository.procedureReajuste(reajuste);
	}
	
	
	// Atividade 7.2
	public List<FuncEntity> findByNamedParam(Integer dependents, String depart) {
		return funcRepository.findByNamedParam(dependents, depart);
	}

	
	// Atividade 7.3
	public int updateFuncDepart(DepartamentEntity niw, DepartamentEntity old) {
				
			return funcRepository.updateDepartById(niw, old);
			
	}
		
	// Atividade 7.4
	public int deleteFuncByDepart(Long idDepart) {
					
			return funcRepository.deleteDepartById(idDepart);
	}

}
