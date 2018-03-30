package com.septinary.common.util;

import java.util.Locale;
import java.util.TimeZone;

/**
 * 本地化操作
 * @Filename: com.septinary.common.util.LocaleUtil.java of the project [com.septinary.common]
 *     @Type: LocaleUtil
 *     @Desc: TODO
 *   @Author: macbook[weide<weide001@gmail.com>]
 *  @Created: 2016年4月13日下午12:26:03
 *
 */
public abstract class LocaleUtil {
	
	/**
	 * 解析本地化字符串
	 @method LocaleUtil: ParseLocaleString()
	 @memo TODO
	 @param localeString
	 @return Locale
	 */
	public static Locale ParseLocaleString(String localeString) {
		String[] parts = StringUtil.TokenizeToStringArray(localeString, "_ ", false, false);
		String language = (parts.length > 0 ? parts[0] : "");
		String country = (parts.length > 1 ? parts[1] : "");
		validateLocalePart(language);
		validateLocalePart(country);
		String variant = "";
		if (parts.length > 2) {
			// There is definitely a variant, and it is everything after the country
			// code sans the separator between the country code and the variant.
			int endIndexOfCountryCode = localeString.indexOf(country, language.length()) + country.length();
			// Strip off any leading '_' and whitespace, what's left is the variant.
			variant = StringUtil.TrimLeadingWhitespace(localeString.substring(endIndexOfCountryCode));
			if (variant.startsWith("_")) {
				variant = StringUtil.TrimLeadingCharacter(variant, '_');
			}
		}
		return (language.length() > 0 ? new Locale(language, country, variant) : null);
	}

	private static void validateLocalePart(String localePart) {
		for (int i = 0; i < localePart.length(); i++) {
			char ch = localePart.charAt(i);
			if (ch != '_' && ch != ' ' && !Character.isLetterOrDigit(ch)) {
				throw new IllegalArgumentException("Locale part \"" + localePart + "\" contains invalid characters");
			}
		}
	}

	/**
	 * 转换为语言标识
	 @method LocaleUtil: ToLanguageTag()
	 @memo TODO
	 @param locale
	 @return String
	 */
	public static String ToLanguageTag(Locale locale) {
		return locale.getLanguage() + (StringUtil.HasText(locale.getCountry()) ? "-" + locale.getCountry() : "");
	}

	/**
	 * 解析时区字符串
	 @method LocaleUtil: ParseTimeZoneString()
	 @memo TODO
	 @param timeZoneString
	 @return TimeZone
	 */
	public static TimeZone ParseTimeZoneString(String timeZoneString) {
		TimeZone timeZone = TimeZone.getTimeZone(timeZoneString);
		if ("GMT".equals(timeZone.getID()) && !timeZoneString.startsWith("GMT")) {
			// We don't want that GMT fallback...
			throw new IllegalArgumentException("Invalid time zone specification '" + timeZoneString + "'");
		}
		return timeZone;
	}
}
