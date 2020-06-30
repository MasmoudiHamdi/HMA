package com.ing.bank.entity;

import java.io.Serializable;
import java.math.BigDecimal;
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
@Table(name = "COMPTE_CLIENT")
public class CustomerAccount implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID_COMPTE_CLIENT")
	@javax.persistence.Id
	private long id;

	@Column(name = "SOLDE")
	private BigDecimal balance;

	@Column(name = "DECOUVERT")
	private BigDecimal overdraft;

	@Column(name = "REFERENCE")
	private String reference;

	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinTable(name = "COMPTE_CLIENT_TRANSACTION", joinColumns = {
			@JoinColumn(name = "ID_COMPTE_CLIENT") }, inverseJoinColumns = { @JoinColumn(name = "ID_TRANSACTION") })
	private List<Transaction> transactions = new ArrayList<>();

	public CustomerAccount() {
		super();
	}

	public CustomerAccount(BigDecimal balance, BigDecimal overdraft, String reference, List<Transaction> transactions) {
		super();
		this.balance = balance;
		this.overdraft = overdraft;
		this.reference = reference;
		this.transactions = transactions;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public BigDecimal getBalance() {
		return balance;
	}

	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}

	public BigDecimal getOverdraft() {
		return overdraft;
	}

	public void setOverdraft(BigDecimal overdraft) {
		this.overdraft = overdraft;
	}

	public List<Transaction> getTransactions() {
		return transactions;
	}

	public void setTransactions(List<Transaction> transactions) {
		this.transactions = transactions;
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
		result = prime * result + ((balance == null) ? 0 : balance.hashCode());
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + ((overdraft == null) ? 0 : overdraft.hashCode());
		result = prime * result + ((reference == null) ? 0 : reference.hashCode());
		result = prime * result + ((transactions == null) ? 0 : transactions.hashCode());
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
		CustomerAccount other = (CustomerAccount) obj;
		if (balance == null) {
			if (other.balance != null)
				return false;
		} else if (!balance.equals(other.balance))
			return false;
		if (id != other.id)
			return false;
		if (overdraft == null) {
			if (other.overdraft != null)
				return false;
		} else if (!overdraft.equals(other.overdraft))
			return false;
		if (reference == null) {
			if (other.reference != null)
				return false;
		} else if (!reference.equals(other.reference))
			return false;
		if (transactions == null) {
			if (other.transactions != null)
				return false;
		} else if (!transactions.equals(other.transactions))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "CustomerAccount [id=" + id + ", balance=" + balance + ", overdraft=" + overdraft + ", reference="
				+ reference + ", transactions=" + transactions + "]";
	}

}
