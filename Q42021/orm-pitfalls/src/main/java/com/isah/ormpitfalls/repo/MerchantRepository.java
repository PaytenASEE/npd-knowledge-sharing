package com.isah.ormpitfalls.repo;

import java.util.List;

import com.isah.ormpitfalls.repo.spec.AbstractRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.isah.ormpitfalls.model.Merchant;

@Repository
public class MerchantRepository extends AbstractRepository<Merchant, String> {
	public MerchantRepository() {
		super(Merchant.class);
	}

	@Transactional
	public void add(List<Merchant> merchants) {
		merchants.forEach(this::add);
	}

	@Transactional
	public void remove(List<Merchant> merchants) {
		merchants.forEach(merchant -> delete(merge(merchant)));
	}
}
