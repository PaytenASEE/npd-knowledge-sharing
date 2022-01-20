package com.isah.ormpitfalls;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import com.isah.ormpitfalls.model.PaymentSystem;
import com.isah.ormpitfalls.model.Merchant;

public class InitialData {

	public static final List<Merchant> MERCHANTS = new ArrayList<Merchant>() {
		/**
		* 
		*/
		private static final long serialVersionUID = -2176905740174063781L;

		{
			Merchant brightStarMerchant = new Merchant("Brightstar", "brightstar01");
			PaymentSystem brightStarFinans = new PaymentSystem("Finans", brightStarMerchant).withId(UUID.randomUUID().toString());
			PaymentSystem brightStarIsbank = new PaymentSystem("Isbank", brightStarMerchant).withId(UUID.randomUUID().toString());
			brightStarMerchant.getPaymentSystems().addAll(Arrays.asList(brightStarFinans, brightStarIsbank));
			add(brightStarMerchant);

			Merchant sahibindenMerchant = new Merchant("Sahibinden", "sahibinden01");
			PaymentSystem sahibindenGaranti = new PaymentSystem("Garanti", sahibindenMerchant).withId(UUID.randomUUID().toString());
			PaymentSystem sahibindenVakifbank = new PaymentSystem("Vakifbank", sahibindenMerchant).withId(UUID.randomUUID().toString());
			sahibindenMerchant.getPaymentSystems().addAll(Arrays.asList(sahibindenGaranti, sahibindenVakifbank));
			add(sahibindenMerchant);

			Merchant macfitMerchant = new Merchant("Macfit", "macfit01");
			PaymentSystem macfitIngBank = new PaymentSystem("IngBank", macfitMerchant).withId(UUID.randomUUID().toString());
			PaymentSystem macfitDenizbank = new PaymentSystem("Vakifbank", macfitMerchant).withId(UUID.randomUUID().toString());
			sahibindenMerchant.getPaymentSystems().addAll(Arrays.asList(macfitIngBank, macfitDenizbank));
			add(macfitMerchant);
		}
	};
}
