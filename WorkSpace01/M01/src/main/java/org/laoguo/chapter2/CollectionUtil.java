package org.laoguo.chapter2;

import java.util.Collection;
import java.util.Map;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.MapUtils;

public final class CollectionUtil {
	public static boolean isEmpty(Collection<?> collection) {
		return CollectionUtils.isEmpty(collection);
	}

	public static boolean isNotEmpty(Collection<?> collection) {
		return CollectionUtils.isNotEmpty(collection);
	}

	public static boolean isEmpty(Map<?, ?> map) {
		return MapUtils.isEmpty(map);
	}

	public static boolean isNotEmpty(Map<?, ?> map) {
		return MapUtils.isNotEmpty(map);
	}
}
