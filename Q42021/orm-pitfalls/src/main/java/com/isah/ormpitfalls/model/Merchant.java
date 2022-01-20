package com.isah.ormpitfalls.model;

import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity(name = "Merchant")
public class Merchant {
	private String name;
	@Id
	private String businessId;

	@OneToMany(mappedBy = "merchant", cascade = CascadeType.ALL)
	private Set<PaymentSystem> paymentSystems = new LinkedHashSet<>();
	
	public Merchant() {
		super();
	}

	public Merchant(String name, String code) {
		super();
		this.name = name;
		this.businessId = code;
	}

	public Merchant(String name, String merchantBusinessId, Set<PaymentSystem> paymentSystems) {
		super();
		this.name = name;
		this.businessId = merchantBusinessId;
		this.paymentSystems = paymentSystems;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBusinessId() {
		return businessId;
	}

	public void setBusinessId(String code) {
		this.businessId = code;
	}

	public Set<PaymentSystem> getPaymentSystems() {
		return paymentSystems;
	}

	@Override
	public String toString() {
		return "Merchant [name=" + name + ", businessId=" + businessId + "]";
	}
}
