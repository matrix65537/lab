package org.laoguo.chapter2;

public final class CastUtil {
	public static String castString(Object obj) {
		return castString(obj, "");
	}

	public static String castString(Object obj, String defaultValue) {
		return obj != null ? String.valueOf(obj) : defaultValue;
	}

	public static double castDouble(Object obj) {
		return castDouble(obj, 0);
	}

	public static double castDouble(Object obj, double defaultValue) {
		double doubleValue = defaultValue;
		if (obj != null) {
			String strValue = castString(obj);
			try {
				doubleValue = Double.parseDouble(strValue);
			} catch (NumberFormatException e) {
				doubleValue = defaultValue;
			}
		}
		return doubleValue;
	}

	public static long castLong(Object obj) {
		return castLong(obj, 0);
	}

	public static long castLong(Object obj, long defaultValue) {
		long longValue = defaultValue;
		if (obj != null) {
			String strValue = castString(obj);
			try {
				longValue = Long.parseLong(strValue);
			} catch (NumberFormatException e) {
				longValue = defaultValue;
			}
		}
		return longValue;
	}

	public static int castInt(Object obj) {
		return castInt(obj, 0);
	}

	public static int castInt(Object obj, int defaultValue) {
		int intValue = defaultValue;
		if (obj != null) {
			String strValue = castString(obj);
			try {
				intValue = Integer.parseInt(strValue);
			} catch (NumberFormatException e) {
				intValue = defaultValue;
			}
		}
		return intValue;
	}

	public static boolean castBoolean(Object obj) {
		return castBoolean(obj, false);
	}

	public static boolean castBoolean(Object obj, boolean defaultValue) {
		boolean booleanValue = defaultValue;
		if (obj != null) {
			String strValue = castString(obj);
			try {
				booleanValue = Boolean.parseBoolean(strValue);
			} catch (NumberFormatException e) {
				booleanValue = defaultValue;
			}
		}
		return booleanValue;
	}
}
