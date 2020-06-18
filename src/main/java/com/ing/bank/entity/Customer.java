package com.ing.bank.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import org.springframework.data.annotation.Id;

/**
 *
 * @author HM
 */
@Entity
@Table(name = "CLIENT")
public class Customer implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID_CLIENT")
	@javax.persistence.Id
	private long id;

	@Column(name = "REFERENCE")
	private String reference;

	@Column(name = "NOM_CLIENT")
	private String name;

	@Column(name = "PRENOM_CLIENT")
	private String surname;

	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinTable(name = "CLIENT_COMPTE_CLIENT", joinColumns = { @JoinColumn(name = "ID_CLIENT") }, inverseJoinColumns = {
			@JoinColumn(name = "ID_COMPTE_CLIENT") })
	private List<CustomerAccount> customerAccounts = new ArrayList<>();

	public Customer() {
		super();
	}

	public Customer(String reference, String name, String surname, List<CustomerAccount> customerAccounts) {
		super();
		this.reference = reference;
		this.name = name;
		this.surname = surname;
		this.customerAccounts = customerAccounts;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public List<CustomerAccount> getCustomerAccounts() {
		return customerAccounts;
	}

	public void setCustomerAccounts(List<CustomerAccount> customerAccounts) {
		this.customerAccounts = customerAccounts;
	}

	public String getReference() {
		return reference;
	}

	public void setReference(String reference) {
		this.reference = reference;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((customerAccounts == null) ? 0 : customerAccounts.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((reference == null) ? 0 : reference.hashCode());
		result = prime * result + ((surname == null) ? 0 : surname.hashCode());
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
		Customer other = (Customer) obj;
		if (customerAccounts == null) {
			if (other.customerAccounts != null)
				return false;
		} else if (!customerAccounts.equals(other.customerAccounts))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (reference == null) {
			if (other.reference != null)
				return false;
		} else if (!reference.equals(other.reference))
			return false;
		if (surname == null) {
			if (other.surname != null)
				return false;
		} else if (!surname.equals(other.surname))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Customer [id=" + id + ", reference=" + reference + ", name=" + name + ", surname=" + surname
				+ ", customerAccounts=" + customerAccounts + "]";
	}

}
