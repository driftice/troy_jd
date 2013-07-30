package com.troy.jd.test;

import java.io.Serializable;

public class TestCase implements Serializable {
	private int privateIntField = 0;
	public long publicLongField = 1l;
	public static boolean publicStaticBoolField = true;
	public String publicStringRef = new String("yeah");
	public final static int finalValue = 0;

	public static void publicStaticMethod() {
		publicStaticBoolField = false;
	}

	public void withNullAndReturnVoid() {
		privateIntField++;
	}

	public int withIntAndReturnInt(int n) throws RuntimeException {
		privateIntField += n;
		return privateIntField;
	}

	public void withCatch() {
		try {
			System.out.println("yes");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void testAll() {
		int minor = 10; // assign minor number
		int major = 10000; // assign major number

		int i1 = 1;
		int i2 = 2;
		int i3 = i1 + i2;

		int k = 0;
		k++;

		double d1 = 324234d;
		double d2 = 2;
		double d3 = d1 + d2;

		String s1 = "a";
		String s2 = "c";
		String s3 = s1 + s2;

		TestCase test = new TestCase(); // new Object
		test.withIntAndReturnInt(10); // invoke and don't assign
		int result1 = test.withIntAndReturnInt(10); // invoke and assign
		int result2 = test.withIntAndReturnInt(10) + 100; // multi opt

		StringBuilder sb = new StringBuilder();
		for (int j = 0; j < 10; j++) {
			sb.append(j);
		}

		if (i3 > 10) {
			System.out.println("c > 10");
		} else if (i3 > 0) {
			System.out.println("c > 0");
		} else {
			System.out.println("c <= 0");
		}

		privateIntField++;
	}
}
