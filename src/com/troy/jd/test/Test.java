package com.troy.jd.test;

import java.io.File;

import com.troy.jd.attr.AttrInfo;
import com.troy.jd.attr.Attrs;
import com.troy.jd.decompiler.Decomplier;
import com.troy.jd.decompiler.FieldInfo;
import com.troy.jd.decompiler.MethodInfo;
import com.troy.jd.decompiler.TClass;
import com.troy.jd.pool.CPInfo;

public class Test {
	public static void main(String[] args) throws Exception {
		File classFile = new File("C:/troy/java_workspace/troy_jd/bin/com/troy/jd/test/TestCase.class");

		Decomplier de = new Decomplier(classFile);
		TClass tc = de.parse();
		de.close();

		System.out.println(tc.getMagic());
		System.out.println("version:" + tc.getMajorVersion() + "." + tc.getMinorVersion());

		System.out.println("-----------------");
		int i = 0;
		for (CPInfo cpInfo : tc.getConstantPool().getConstants()) {
			if (!cpInfo.isNull()) {
				System.out
						.println("#" + i + " " + cpInfo.getClass().getSimpleName() + " -> " + cpInfo.getDescription());
			}
			i++;
		}

		System.out.println("-----------------");
		System.out.println("access flags:" + tc.getAccessFlags());
		System.out.println("class Name:" + tc.getClassName());
		System.out.println("super class:" + tc.getSuperClass());
		System.out.println("interface count:" + tc.getInterfaceIndexes().size());
		System.out.println("interfaces:" + tc.getInterfaces());

		System.out.println("-----------------");
		System.out.println("field count:" + tc.getFieldInfos().size());
		for (FieldInfo field : tc.getFieldInfos()) {
			System.out.println("field name:" + field.getName() + " -> " + field.getDescriptor() + " -> "
					+ field.getAccessFlags().getAccessFlags());
			Attrs attrs = field.getAttrs();
			printAttrs(attrs);
		}

		System.out.println("-----------------");
		System.out.println("method count:" + tc.getMethodInfos().size());
		for (MethodInfo method : tc.getMethodInfos()) {
			System.out.println("method name:" + method.getName() + " -> " + method.getDescriptor() + " -> "
					+ method.getAccessFlags().getAccessFlags());
			printAttrs(method.getAttrs());
			System.out.println("-------");
		}

		System.out.println("-----------------");
		System.out.println("class attr count:" + tc.getAttrs().size());
		printAttrs(tc.getAttrs());
	}

	private static void printAttrs(Attrs attrs) {
		for (AttrInfo attrInfo : attrs.getAttrInfos()) {
			System.out.println(attrInfo.getName());
			System.out.println(attrInfo.toString());
		}
	}
}
