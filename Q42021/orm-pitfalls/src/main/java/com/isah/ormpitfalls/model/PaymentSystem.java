package com.isah.ormpitfalls.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity(name = "PaymentSystem")
public class PaymentSystem {
	@Id
	private String id;
	private String name;
	@ManyToOne(fetch = FetchType.LAZY)
	private Merchant merchant;

	public PaymentSystem() {
		super();
	}

	public PaymentSystem(String name, Merchant merchant) {
		super();
		this.name = name;
		this.merchant = merchant;
	}

	public PaymentSystem withId(String id){
		this.id = id;
		return this;
	}

	public String getName() {
		return name;
	}

	public Merchant getMerchant() {
		return merchant;
	}

	@Override
	public String toString() {
		return "Payment System [name=" + name + ", merchant=" + merchant + "]";
	}
}
