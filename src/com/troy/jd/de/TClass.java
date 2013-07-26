package com.troy.jd.de;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.troy.jd.attr.Attrs;
import com.troy.jd.cp.CPInfo;
import com.troy.jd.cp.ConstantPool;

/**
 * ClassFile {
 * u4 magic;
 * u2 minor_version;
 * u2 major_version;
 * u2 constant_pool_count;
 * cp_info constant_pool[constant_pool_count-1];
 * u2 access_flags;
 * u2 this_class;
 * u2 super_class;
 * u2 interfaces_count;
 * u2 interfaces[interfaces_count];
 * u2 fields_count;
 * field_info fields[fields_count];
 * u2 methods_count;
 * method_info methods[methods_count];
 * u2 attributes_count;
 * attribute_info attributes[attributes_count];
 * }
 * 
 * @author WuCh
 * 
 */
public class TClass implements Fillable {
	// class version
	private String magic;
	private int minorVersion;
	private int majorVersion;

	// constant pool
	private ConstantPool constantPool;

	// class Info
	private AccessFlags accessFlags;
	private int thisClassIndex;
	private int superClassIndex;
	private List<Integer> interfaceIndexes = new ArrayList<>();

	// fields
	private List<FieldInfo> fieldInfos = new ArrayList<>();

	// methods
	private List<MethodInfo> methodInfos = new ArrayList<>();

	// attrss
	private Attrs attrs;

	public TClass() {
		super();
	}

	@Override
	public void fill(Decomplier de) throws Exception {
		readMagic(de);
		readVersion(de);
		readConstantPool(de);
		readClassInfo(de);
		readFields(de);
		readMethods(de);
		readClassAttributes(de);
	}

	private void readClassAttributes(Decomplier de) throws Exception {
		attrs = new Attrs(this);
		attrs.fill(de);
	}

	private void readMethods(Decomplier de) throws Exception {
		int len = de.readU2();
		for (int i = 0; i < len; i++) {
			MethodInfo methodInfo = new MethodInfo(this);
			methodInfo.fill(de);
			methodInfos.add(methodInfo);
		}
	}

	private void readFields(Decomplier de) throws Exception {
		int len = de.readU2();
		for (int i = 0; i < len; i++) {
			FieldInfo fieldInfo = new FieldInfo(this);
			fieldInfo.fill(de);
			fieldInfos.add(fieldInfo);
		}
	}

	private void readClassInfo(Decomplier de) {
		accessFlags = new AccessFlags(de.readU2());
		thisClassIndex = de.readU2();
		superClassIndex = de.readU2();

		int interfaceLen = de.readU2();
		for (int i = 0; i < interfaceLen; i++) {
			interfaceIndexes.add(de.readU2());
		}
	}

	private void readConstantPool(Decomplier de) throws Exception {
		constantPool = new ConstantPool(this);
		constantPool.fill(de);
	}

	private void readVersion(Decomplier de) {
		minorVersion = de.readU2();
		majorVersion = de.readU2();
	}

	private void readMagic(Decomplier de) {
		magic = de.toHex(de.readU4());
	}

	public CPInfo getCPInfoOf(int index) {
		return constantPool.getCPInfoOf(index);
	}

	public String getFullDescriptionOf(int index) {
		return constantPool.getFullDescriptionOf(index);
	}

	public int getMinorVersion() {
		return minorVersion;
	}

	public int getMajorVersion() {
		return majorVersion;
	}

	public String getMagic() {
		return magic;
	}

	public List<FieldInfo> getFieldInfos() {
		return Collections.unmodifiableList(fieldInfos);
	}

	public List<MethodInfo> getMethodInfos() {
		return Collections.unmodifiableList(methodInfos);
	}

	public int getThisClassIndex() {
		return thisClassIndex;
	}

	public int getSuperClassIndex() {
		return superClassIndex;
	}

	public List<Integer> getInterfaceIndexes() {
		return Collections.unmodifiableList(interfaceIndexes);
	}

	public String getClassName() {
		return getFullDescriptionOf(thisClassIndex);
	}

	public String getSuperClass() {
		return getFullDescriptionOf(superClassIndex);
	}

	public Attrs getAttrs() {
		return attrs;
	}

	public ConstantPool getConstantPool() {
		return constantPool;
	}

	public List<String> getInterfaces() {
		List<String> interfaces = new ArrayList<>();
		for (int index : interfaceIndexes) {
			interfaces.add(getFullDescriptionOf(index));
		}
		return interfaces;
	}

	public AccessFlags getAccessFlags() {
		return accessFlags;
	}
}
