/*  This file is part of the aspect  project.
 This program is free software; you can redistribute it and/or
 modify it under the terms of the GNU Lesser General Public License
 as published by the Free Software Foundation; version 2.1
 of the License.
 This program is distributed in the hope that it will be useful,
 but WITHOUT ANY WARRANTY; without even the implied warranty of
 MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 GNU Lesser General Public License for more details.
 You should have received a copy of the GNU Lesser General Public License
 along with this program; if not, write to the Free Software
 Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA.

 Copyright 2007-2014 Semochkin Vitaliy Evgenevich aka Yilativs
  
 */

package net.sf.aspect4log;


import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import net.sf.aspect4log.text.CompactFormatMessageBuilderFactory;
import net.sf.aspect4log.text.MessageBuilderFactory;

/**
 * 
 * Defines the logging rules on method or on the entire class.
 * 
 * @author Vitaliy S <a href="mailto:vitaliy.se@gmail.com">
 * 
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.TYPE, ElementType.METHOD })
public @interface Log {
	public static final String ARGUMENTS_DEFAULT_TEMPLATE = "${args}";
	public static final String RESULT_DEFAULT_TEMPLATE = "${result}";
	public static final String EXCEPTION_DEFAULT_TEMPLATE = "${exception}";

	LogLevel enterLevel() default LogLevel.INFO;

	/**
	 * 
	 * @return
	 * 
	 *         WARNING: making returnLevel higher than enterLevel can make the log hard to read5w40
	 */
	LogLevel successfulReturnLevel() default LogLevel.DEBUG;

	LogLevel exceptionReturnLevel() default LogLevel.ERROR;

	String argumentsTemplate() default ARGUMENTS_DEFAULT_TEMPLATE; //

	/**
	 * 
	 * @return A pattern representing value returned from the method. The default value is $result.
	 */
	String resultTemplate() default RESULT_DEFAULT_TEMPLATE;

	/**
	 * 
	 * @return if specified will be set as MDC key.
	 */
	String mdcKey() default "";

	/**
	 * 
	 * @return An mdc patter. Can be any expression using $args bean
	 */
	String mdcTemplate() default "";

	/**
	 * 
	 * @return specifies if indent should be used for methods called inside marked method. The default value is true.
	 */
	boolean useIndent() default true;

	/**
	 * @return a java.lang.String that represents text of Indent. The default value is 1 tab symbol.
	 */
	String indentText() default "\t";

	/**
	 * default value is $exception
	 * @return $exception
	 */
	String exceptionTemplate() default EXCEPTION_DEFAULT_TEMPLATE;

	/*
	 * String enterPattern() default "$method ($args)"; String exceptionPattern() default "$method ($args)";
	 */

	Class<? extends MessageBuilderFactory> messageBuilderFactory() default CompactFormatMessageBuilderFactory.class;

}
