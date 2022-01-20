package com.isah.ormpitfalls;

import org.junit.runner.notification.RunNotifier;
import org.junit.runners.model.InitializationError;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

public class SpringTestClassRunner extends SpringJUnit4ClassRunner {
	private TestListener testListener;

	public SpringTestClassRunner(Class<?> clazz) throws InitializationError {
		super(clazz);
	}

	@Override
	protected Object createTest() throws Exception {
		Object test = super.createTest();
		if (test instanceof TestListener && testListener == null) {
			testListener = (TestListener) test;
			testListener.beforeClass();
		}
		return test;
	}

	@Override
	public void run(RunNotifier notifier) {
		super.run(notifier);
		if (testListener != null) {
			testListener.afterClass();
		}
	}
}