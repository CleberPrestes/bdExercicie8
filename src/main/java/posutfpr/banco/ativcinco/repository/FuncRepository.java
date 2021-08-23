package posutfpr.banco.ativcinco.repository;

import java.util.List;

import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;

import posutfpr.banco.ativcinco.entity.DepartamentEntity;
import posutfpr.banco.ativcinco.entity.FuncEntity;

//UTFPR — Universidade Tecnológica Federal do Paraná UTFPR
//XXI Curso de Especialização em Tecnologia Java (2021_01)
//Banco de Dados
//Atividade 8
//Cleber dos Santos Prestes de Oliveira


public interface FuncRepository extends JpaRepository<FuncEntity, Long> {
		
	
	FuncEntity findByNameAndDependents(String name, Integer dependents);
	
	 
	List<FuncEntity> findByDepartName(String name);
			
	
	FuncEntity findTopByOrderByPayDesc();
	
	List<FuncEntity> findFirst3ByOrderByPayDesc();
	
	
	@Query("select f from FuncEntity f where dependents = 0 order by name asc")
	List<FuncEntity>findByFuncNoDependents();
	
    @Query(" select f from FuncEntity f  where pay >= ?1 order by name asc") 
    List<FuncEntity> findByPayBigger(Long pay);
    
    @Query(value = "select * from funcionario where salario > ?1", nativeQuery = true)
    List<FuncEntity> findByPay(Long pay);
    
    
    @Query(name = "FuncEntity.byDependents")
    List<FuncEntity> findByDepNamedQuery(Integer qtdDepend);
    
    @Query(name = "FuncEntity.byPartOfName")
    List<FuncEntity> findByPartOfNameNativeQuery(String nome);
    
	
	
    //Atividade 7.1
    @Procedure(name =   "Funcionario.Reajuste") 
    void procedureReajuste(Integer percentual);
    
  //Atividade 7.2
    @Query(" select f from FuncEntity f  where dependents = :depend and depart.name = :depart") 
    List<FuncEntity> findByNamedParam(@Param("depend") Integer depend, @Param("depart") String depart);
    
  //Atividade 7.3
    @Transactional
    @Modifying
    @Query(" update FuncEntity f set f.depart = :newDep where f.depart = :oldDep") 
    int updateDepartById(@Param("newDep") DepartamentEntity newDep, @Param("oldDep") DepartamentEntity oldDep);
       
    
  //Atividade 7.4
    @Transactional
    @Modifying
    @Query(" delete from FuncEntity f where depart.id = ?1 ")
    int deleteDepartById(Long id);
    
       

}