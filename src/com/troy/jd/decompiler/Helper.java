package com.troy.jd.decompiler;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Helper extends DataInputStream {
	protected File sourceFile;

	public Helper(File sourceFile) throws FileNotFoundException {
		super(new BufferedInputStream(new FileInputStream(sourceFile)));
		this.sourceFile = sourceFile;
	}

	public String toHex(int n) {
		return "0x" + Integer.toHexString(n);
	}

	public String toHex(float n) {
		return "0x" + Float.toHexString(n);
	}

	public String toHex(double n) {
		return "0x" + Double.toHexString(n);
	}

	public String toHex(long n) {
		return "0x" + Long.toHexString(n);
	}

	public int readU1() {
		try {
			return readUnsignedByte();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	public int readU2() {
		try {
			return readUnsignedShort();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}

	}

	public int readU4() {
		try {
			return readInt();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}

	}

	public long readU8() {
		try {
			return readLong();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}

	}

	public byte[] readArray(int length) {
		try {
			byte[] buf = new byte[length];
			read(buf);
			return buf;
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
}
