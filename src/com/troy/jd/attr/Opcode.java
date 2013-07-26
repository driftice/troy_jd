package com.troy.jd.attr;

import java.io.DataInputStream;

import com.troy.jd.de.Constants;
import com.troy.jd.de.TClass;

/**
 * 字节码的参数来源有
 * - 栈顶
 * - 局部变量
 * - 常量池
 * - 字节码偏移量:用于跳转指令
 * - 数字常量。 如sipush指令
 * 
 * 对于class解析时,opts只可能是后面三个,并且
 * - 局部变量: byte
 * - 常量池: short
 * - 字节码偏移量:用于跳转指令:short 或 int ,跳转指令都是以if或goto开头,可以确定
 * - 数字常量:push指令。可能是short或者byte
 * 
 * 因而只要确定参数基本类型,就可以确定参数的来源
 * 
 * @author WuCh
 * 
 */
public class Opcode {
	public static final short OPT_SOURCE_LOCAL_VAR = 1;
	public static final short OPT_SOURCE_CONSTANT_POOL = 2;
	public static final short OPT_SOURCE_OPCODE_OFFSET = 3;
	public static final short OPT_SOURCE_CONSTANT = 4;

	private int code;
	private int offset;
	private int[] opts;
	private short[] sourceTypeOfOpts;
	private TClass tclass;

	public Opcode(TClass tclass) {
		super();
		this.tclass = tclass;
	}

	public int getCode() {
		return code;
	}

	public void fill(DataInputStream in, int offset) throws Exception {
		this.offset = offset;
		code = in.readUnsignedByte();
		String codeName = Constants.OPCODE_NAMES[code];

		short[] typesOfOpt = Constants.TYPE_OF_OPERANDS[code];
		int numOfOpts = typesOfOpt.length;
		opts = new int[numOfOpts];
		sourceTypeOfOpts = new short[numOfOpts];

		for (int j = 0; j < numOfOpts; j++) {
			short typeOfOpt = typesOfOpt[j];

			if (typeOfOpt == Constants.T_BYTE) {
				opts[j] = in.readUnsignedByte();
				sourceTypeOfOpts[j] = OPT_SOURCE_LOCAL_VAR;
			} else if (typeOfOpt == Constants.T_SHORT) {
				opts[j] = in.readUnsignedShort();
				if (codeName.startsWith("goto") || codeName.startsWith("if")) {
					sourceTypeOfOpts[j] = OPT_SOURCE_OPCODE_OFFSET;
				} else if (codeName.endsWith("push")) {
					sourceTypeOfOpts[j] = OPT_SOURCE_CONSTANT;
				} else {
					sourceTypeOfOpts[j] = OPT_SOURCE_CONSTANT_POOL;
				}
			} else if (typeOfOpt == Constants.T_INT) {
				opts[j] = in.readInt();
				sourceTypeOfOpts[j] = OPT_SOURCE_OPCODE_OFFSET;
			} else {
				throw new RuntimeException("unknown type of opt for code:" + code);
			}
		}
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		String codeName = getCodeName();
		sb.append(offset).append(":").append(codeName).append(" ");

		for (int j = 0; j < sourceTypeOfOpts.length; j++) {
			if (j > 0) {
				sb.append(",");
			}

			short typeOfOpt = sourceTypeOfOpts[j];
			if (typeOfOpt == OPT_SOURCE_CONSTANT_POOL) {
				sb.append("#").append(String.valueOf(opts[j])).append(" // ")
						.append(tclass.getFullDescriptionOf(opts[j]));
			} else {
				sb.append(String.valueOf(opts[j]));
			}
		}

		return sb.toString();
	}

	public int[] getOpts() {
		return opts;
	}

	public String getCodeName() {
		return Constants.OPCODE_NAMES[code];
	}

	public short[] getTypesOfOpt() {
		return Constants.TYPE_OF_OPERANDS[code];
	}

	public int getOffset() {
		return offset;
	}

	public short[] getSourceTypeOfOpts() {
		return sourceTypeOfOpts;
	}
}
