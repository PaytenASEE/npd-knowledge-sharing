package com.isah.ormpitfalls;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import com.isah.ormpitfalls.config.PersistenceConfig;
import com.isah.ormpitfalls.repo.PaymentSystemRepository;
import com.isah.ormpitfalls.repo.MerchantRepository;

@RunWith(SpringTestClassRunner.class)
// ApplicationContext will be loaded from PersistenceConfig class
@ContextConfiguration(classes = PersistenceConfig.class, loader = AnnotationConfigContextLoader.class)
public class OrmPitfallsTest implements TestListener {
	static Logger logger = LoggerFactory.getLogger(OrmPitfallsTest.class);

	@Autowired
	private MerchantRepository merchantRepository;
	@Autowired
	private PaymentSystemRepository paymentSystemRepository;

	@Override
	public void beforeClass() {
		logNote("Populating database with some merchants and payment systems...");
		merchantRepository.add(InitialData.MERCHANTS);
	}

	@Override
	public void afterClass() {
		logNote("Cleaning up database from test data...");
		merchantRepository.remove(InitialData.MERCHANTS);
	}

	@Test
	public void test_pitfall1_with_non_lazy_ref() {
		logNote("ORM Pitfall nr. 1: non-lazy merchant in PaymentSystem...");
		paymentSystemRepository.getAllNaive();
	}

	@Test
	public void test_pitfall1_with_non_lazy_ref_improved() {
		logNote("Pitfall nr. 1 workaround with merchant fetch on first query...");
		paymentSystemRepository.getAllWithFetches();
	}

	@Test
	public void test_pitfall2_with_lazy_ref() {
		logNote("ORM Pitfall nr. 2 if merchant is set to LAZY in PaymentSystem...");
		paymentSystemRepository.doSomethingWithPaymentSystemsAndTheirMerchant();
	}

	@Test
	public void test_pitfall2_with_lazy_ref_improved() {
		logNote("ORM Pitfall nr. 3 workaround with fetch of paymentSystems on first query...");
		paymentSystemRepository.getAllWithFetches();
	}

	@Test
	public void test_pitfall3_with_lazy_collections() {
		logNote("ORM Pitfall nr. 3 lazy paymentSystems in Merchant(default fetch mode)...");
		paymentSystemRepository.doSomethingWithMerchantsAndTheirPaymentSystems();
	}

	@Test
	public void test_pitfall3_with_lazy_collections_improved() {
		logNote("ORM Pitfall nr. 3 lazy paymentSystems in Merchant(default fetch mode)...");
		paymentSystemRepository.doSomethingWithMerchantsAndPaymentSystemsWithFetch();
	}



	private void logNote(String note) {
		logger.trace("----------------------------------------------------------------------");
		logger.trace(note);
		logger.trace("----------------------------------------------------------------------");
	}
}
