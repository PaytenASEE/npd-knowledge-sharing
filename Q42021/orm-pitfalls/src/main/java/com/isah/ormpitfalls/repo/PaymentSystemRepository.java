package com.isah.ormpitfalls.repo;

import java.util.List;

import javax.persistence.Query;

import com.isah.ormpitfalls.repo.spec.AbstractRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.isah.ormpitfalls.model.PaymentSystem;
import com.isah.ormpitfalls.model.Merchant;

@Repository
public class PaymentSystemRepository extends AbstractRepository<PaymentSystem, String> {
	public PaymentSystemRepository() {
		super(PaymentSystem.class);
	}

	/**
	 * Pitfall nr. 1 with non-lazy references
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
	public List<PaymentSystem> getAllNaive() {
		String jpql = "FROM " + PaymentSystem.class.getName();
		Query query = entityManager.createQuery(jpql);
		return query.getResultList();
	}

	/**
	 * Alleviation to avoid pitfall nr. 1 by fetching merchant
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
	public List<PaymentSystem> getAllWithFetches() {
		Query query = entityManager.createQuery(
				"FROM " + PaymentSystem.class.getName() + " ps JOIN FETCH ps.merchant");
		return query.getResultList();
	}

	/**
	 * Pitfall nr. 2 with LAZY references. Make merchant reference LAZY in PaymentSystem
	 * entity to test behavior of this method
	 * 
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "unused" })
	@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
	public void doSomethingWithPaymentSystemsAndTheirMerchant() {
		String jpql = "FROM " + PaymentSystem.class.getName();
		Query query = entityManager.createQuery(jpql);
		List<PaymentSystem> paymentSystems = query.getResultList(); // 1 query to list paymentSystems
		paymentSystems.forEach(paymentSystem -> {
			String paymentSystemName = paymentSystem.getMerchant().getName();// paymentSystem.getMerchant() triggers query to fetch merchant, n queries.
			// do something
		});
	}

	/**
	 * Pitfall nr. 3 with LAZY collections
	 */
	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
	public void doSomethingWithMerchantsAndTheirPaymentSystems() {
		String jpql = "FROM " + Merchant.class.getName();
		Query query = entityManager.createQuery(jpql);
		List<Merchant> merchants = query.getResultList(); // 1 query to list merchants
		merchants.forEach(merchant -> {
			merchant.getPaymentSystems().toString(); // merchant.getPaymentSystems() triggers a query to fetch paymentSystems of that merchant, n queries
			// do something
		});
	}

	/**
	 * Avoiding pitfall nr. 3 by fetching the LAZY collection right away
	 */
	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
	public void doSomethingWithMerchantsAndPaymentSystemsWithFetch() {
		String jpql = "FROM " + Merchant.class.getName() + " m JOIN FETCH m.paymentSystems";
		Query query = entityManager.createQuery(jpql);
		List<Merchant> merchants = query.getResultList(); // 1 query to list merchants
		merchants.forEach(merchant -> {
			merchant.getPaymentSystems().toString(); // merchant.getPaymentSystems() triggers a
											// query to fetch paymentSystems of that
											// merchant, n queries
			// do something
		});
	}

}
