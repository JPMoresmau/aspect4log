package net.sf.aspect4log.text;

import org.junit.Assert;
import org.junit.Test;

public class LogFormatterTest {

	@Test
	public void isToStringOverriden() {
		Assert.assertFalse(new LogFormatter().isToStringOverriden(Object.class));
		Assert.assertFalse(new LogFormatter().isToStringOverriden(Object[].class));
		Assert.assertTrue(new LogFormatter().isToStringOverriden(String.class));
		Assert.assertTrue(new LogFormatter().isToStringOverriden(Integer.class));
	}
	
	@Test
	public void testSubstitute(){
		int i = 5;
		String s = "s";
		int[] ii = { 1, 2 };
		Object[] args = { i, s, ii };
		Assert.assertEquals( "args are: s, [5, s, [1, 2]]", new LogFormatter().toString("args are: ${args[1]}, ${args}",  args));
		Assert.assertEquals("${5}, [1, 2]",new LogFormatter().toString("${${args[0]}}, ${args[2]}",  args));
	}

}

