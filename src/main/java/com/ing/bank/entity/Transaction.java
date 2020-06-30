package com.ing.bank.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;
import org.springframework.data.annotation.Id;

import com.ing.bank.model.TransactionType;

/**
 *
 * @author HM
 */
@Entity
@Table(name = "TRANSACTION")
public class Transaction implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID_TRANSACTION")
	@javax.persistence.Id
	private long id;

	@Enumerated(EnumType.STRING)
	@Column(name = "TYPE_TRANSACTION")
	private TransactionType transactionType;

	@Column(name = "VALEUR_TRANSACTION")
	private BigDecimal transactionValue;

	@Column(name = "DATE_TRANSACTION")
	private LocalDate transactionDate;

	@Column(name = "REFERENCE")
	private String reference;

	public Transaction() {
		super();
	}

	public Transaction(TransactionType transactionType, BigDecimal transactionValue, LocalDate transactionDate,
			String reference) {
		super();
		this.transactionType = transactionType;
		this.transactionValue = transactionValue;
		this.transactionDate = transactionDate;
		this.reference = reference;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public TransactionType getTransactionType() {
		return transactionType;
	}

	public void setTransactionType(TransactionType transactionType) {
		this.transactionType = transactionType;
	}

	public BigDecimal getTransactionValue() {
		return transactionValue;
	}

	public void setTransactionValue(BigDecimal transactionValue) {
		this.transactionValue = transactionValue;
	}

	public LocalDate getTransactionDate() {
		return transactionDate;
	}

	public void setTransactionDate(LocalDate transactionDate) {
		this.transactionDate = transactionDate;
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
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + ((reference == null) ? 0 : reference.hashCode());
		result = prime * result + ((transactionDate == null) ? 0 : transactionDate.hashCode());
		result = prime * result + ((transactionType == null) ? 0 : transactionType.hashCode());
		result = prime * result + ((transactionValue == null) ? 0 : transactionValue.hashCode());
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
		Transaction other = (Transaction) obj;
		if (id != other.id)
			return false;
		if (reference == null) {
			if (other.reference != null)
				return false;
		} else if (!reference.equals(other.reference))
			return false;
		if (transactionDate == null) {
			if (other.transactionDate != null)
				return false;
		} else if (!transactionDate.equals(other.transactionDate))
			return false;
		if (transactionType != other.transactionType)
			return false;
		if (transactionValue == null) {
			if (other.transactionValue != null)
				return false;
		} else if (!transactionValue.equals(other.transactionValue))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Transaction [id=" + id + ", transactionType=" + transactionType + ", transactionValue="
				+ transactionValue + ", transactionDate=" + transactionDate + ", reference=" + reference + "]";
	}

}
