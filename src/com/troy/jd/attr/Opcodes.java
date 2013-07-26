package com.troy.jd.attr;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.troy.jd.de.Decomplier;
import com.troy.jd.de.Fillable;
import com.troy.jd.de.TClass;

public class Opcodes implements Fillable {
	private byte[] bytes;
	private List<Opcode> opcodes = new ArrayList<>();
	private TClass tclass;

	public Opcodes(TClass tclass) {
		this.tclass = tclass;
	}

	@Override
	public void fill(Decomplier de) throws Exception {
		int codeLength = de.readU4();
		bytes = de.readArray(codeLength);
		DataInputStream in = new DataInputStream(new ByteArrayInputStream(bytes));

		while (in.available() > 0) {
			Opcode optcode = new Opcode(tclass);
			optcode.fill(in, codeLength - in.available());
			opcodes.add(optcode);
		}
	}

	public List<Opcode> getOpcodes() {
		return Collections.unmodifiableList(opcodes);
	}

	public int getCodeLength() {
		return bytes.length;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for (Opcode opcode : opcodes) {
			sb.append(opcode.toString());
			sb.append("\n");
		}
		return sb.toString();
	}
}
