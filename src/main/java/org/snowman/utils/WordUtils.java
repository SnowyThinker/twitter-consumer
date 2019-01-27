package org.snowman.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class WordUtils {
	
	/**
	 * <p> 保留英文和汉字中的文字(A-Z, a-z, 所有汉字文字，不包括标点符号)
	 * \\u0061-\\u007A  小写字母
	 * \\u0041-\\u005A	大写字母
	 * \\u4E00-\\u9FEF  所有汉字
	 * reference
	 * https://en.wikipedia.org/wiki/Basic_Latin_(Unicode_block)
	 * https://en.wikipedia.org/wiki/CJK_Unified_Ideographs
	 */
	private static final String LETTER_PATTERN = "[\\u0061-\\u007A\\u0041-\\u005A\\u4E00-\\u9FEF]+";
	
	private static final Pattern pattern = Pattern.compile(LETTER_PATTERN);
	
	/**
	 * <p>是否包含常规字符(A-Z, 汉字)
	 * @param word
	 * @return
	 */
	public static boolean containsNormalWords(String word) {
		Matcher matcher = pattern.matcher(word);
		if(matcher.matches()) {
			return true;
		}
		
		return false;
	}

	/**
	 * <p>根据UnicodeBlock方法判断中文标点符号
	 * <p>因为中文的标点符号主要存在于以下5个UnicodeBlock中：
	 * U2000-General Punctuation (百分号，千分号，单引号，双引号等)
	 * U3000-CJK Symbols and Punctuation ( 顿号，句号，书名号，〸，〹，〺 等；PS: 后面三个字符你知道什么意思吗？ : )    )
	 * UFF00-Halfwidth and Fullwidth Forms ( 大于，小于，等于，括号，感叹号，加，减，冒号，分号等等)
	 * UFE30-CJK Compatibility Forms  (主要是给竖写方式使用的括号，以及间断线﹉，波浪线﹌等)
	 * UFE10-Vertical Forms (主要是一些竖着写的标点符号，    等等)
	 * @param c
	 * @return
	 */
	@Deprecated
    public static boolean isChinesePunctuation(char c) {
        Character.UnicodeBlock ub = Character.UnicodeBlock.of(c);
        if (ub == Character.UnicodeBlock.GENERAL_PUNCTUATION
                || ub == Character.UnicodeBlock.CJK_SYMBOLS_AND_PUNCTUATION
                || ub == Character.UnicodeBlock.HALFWIDTH_AND_FULLWIDTH_FORMS
                || ub == Character.UnicodeBlock.CJK_COMPATIBILITY_FORMS
                || ub == Character.UnicodeBlock.VERTICAL_FORMS
                ) {
            return true;
        } else {
            return false;
        }
    }
}
