package posutfpr.banco.ativcinco.entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedNativeQuery;
import javax.persistence.NamedQuery;
import javax.persistence.NamedStoredProcedureQuery;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureParameter;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.AbstractPersistable;

//UTFPR — Universidade Tecnológica Federal do Paraná UTFPR
//XXI Curso de Especialização em Tecnologia Java (2021_01)
//Banco de Dados
//Atividade 8
//Cleber dos Santos Prestes de Oliveira


@Entity
@Table(name = "Funcionario")
// Atividade 6
@NamedQuery(name = "FuncEntity.byDependents",
query = "from FuncEntity f where f.dependents = ?1")

@NamedNativeQuery(name = "FuncEntity.byPartOfName",
query = "select * from  funcionario where nome like ?1", 
resultClass = FuncEntity.class)

// Atividade 7.1
@NamedStoredProcedureQuery( 
		   name = "Funcionario.Reajuste",
		   procedureName = "REAJUSTE_SALARIO",
		   parameters = { 
		      @StoredProcedureParameter( 
		         mode = ParameterMode.IN, 
		         name = "percentual",
		         type = Integer.class),
		     })

public class FuncEntity extends AbstractPersistable<Long> {

	@Column(name = "nome", length = 64, nullable = true)
	private String name;

	@Column(name = "qtdDependente", length = 64, nullable = true)
	private int dependents;

	@Column(name = "salario", length = 64, nullable = true)
	private Long pay;

	@Column(name = "cargo", length = 64, nullable = true)
	private String responsibility;

	
	
	@ManyToOne
	@JoinColumn(name = "dep_id")
	private DepartamentEntity depart;

	public FuncEntity() {

	}

	public FuncEntity(String name, int dependents, Long pay, String responsibility, DepartamentEntity depart) {
		super();
		this.name = name;
		this.dependents = dependents;
		this.pay = pay;
		this.responsibility = responsibility;
		this.depart = depart;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getDependents() {
		return dependents;
	}

	public void setDependents(int dependents) {
		this.dependents = dependents;
	}

	public Long getPay() {
		return pay;
	}

	public void setPay(Long pay) {
		this.pay = pay;
	}

	public String getResponsibility() {
		return responsibility;
	}

	public void setResponsibility(String responsibility) {
		this.responsibility = responsibility;
	}

	public DepartamentEntity getDepart() {
		return depart;
	}

	public void setDepart(DepartamentEntity depart) {
		this.depart = depart;
	}

}