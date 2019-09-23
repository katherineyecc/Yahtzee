package com.chuchuye.Yahtzee;

import junit.framework.TestCase;

public class VerifyTest extends TestCase {
	
	Verify verify;
	
	public void testInit() {
		int[] list = new int[] {1,2,3,4,5};
		Verify verify = new Verify(list);
		for(int index=0; index<5; index++) {
			assertEquals(list[index], verify.getList(index));
		}
	}
	
	public void testVerifyLargeStraight() {
		int[] list = new int[] {2,3,4,5,6};
		verify = new Verify(list);
		assertTrue(verify.verifyLargeStraight());
	}
	
	public void testVerifySmallStraight() {
		int[] list = new int[] {1,3,4,5,6};
		verify = new Verify(list);
		assertTrue(verify.verifySmallStraight());
	}
	
	public void testVerifyFullHouse() {
		int[] list = new int[] {2,2,2,3,3};
		verify = new Verify(list);
		assertTrue(verify.verifyFullHouse());
	}
	
	public void testVerifyThreeOfAKind() {
		int[] list = new int[] {1,1,1,2,3};
		verify = new Verify(list);
		assertTrue(verify.verifyThreeOfAKind());
	}
	
	public void testVerifyFourOfAKind() {
		int[] list = new int[] {1,1,1,1,3};
		verify = new Verify(list);
		assertTrue(verify.verifyFourOfAKind());
	}
	
	public void testVerifyYahtzee() {
		int[] list = new int[] {6,6,6,6,6};
		verify = new Verify(list);
		assertTime(verify.verifyYahtzee());
	}

}
