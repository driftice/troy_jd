package com.troy.jd.decompiler;

import java.io.File;
import java.io.FileNotFoundException;

import com.troy.jd.test.Test;

/**
 * 需要先调用parse
 * 
 * @author WuCh
 * 
 */
public class Decomplier extends Helper {
	private TClass tc;

	public Decomplier(File sourceFile) throws FileNotFoundException {
		super(sourceFile);
		tc = new TClass();
	}

	public TClass parse() {
		try {
			tc.fill(this);
			return tc;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public TClass getTClass() {
		return tc;
	}

	public static void main(String[] args) {
		try {
			Test.main(null);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
