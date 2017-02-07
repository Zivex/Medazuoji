package com.capinfo.sonic;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.Map;

public class ConstantsMap extends HashMap {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6245740238177532512L;

	private static final Class CLZ = Constants.class;

	private static ConstantsMap constantsMap;

	public static ConstantsMap getInstance() {
		if (null == constantsMap) {
			constantsMap = new ConstantsMap();
		}

		return constantsMap;
	}

	private ConstantsMap() {
		Class c = CLZ;
		Field[] fields = c.getDeclaredFields();
		for (int i = 0; i < fields.length; i++) {
			Field field = fields[i];
			int modifier = field.getModifiers();
			if (Modifier.isFinal(modifier) && !Modifier.isPrivate(modifier)) {
				try {
					this.put(field.getName(), field.get(this));
				} catch (IllegalAccessException e) {
				}
			}

		}
	}

	@Override
	public void clear() {
		super.clear();
	}

	@Override
	public Object put(Object key, Object value) {
		return super.put(key, value);
	}

	@Override
	public void putAll(Map m) {
		super.putAll(m);
	}

	@Override
	public Object remove(Object key) {
		return super.remove(key);
	}
}
