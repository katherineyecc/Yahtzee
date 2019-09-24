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
		
		int[] list2 = new int[] {2,2,3,4,5};
		Verify verify2 = new Verify(list2);
		assertFalse(verify2.verifyLargeStraight());
	}
	
	public void testVerifySmallStraight() {
		int[] list = new int[] {1,3,4,5,6};
		verify = new Verify(list);
		assertTrue(verify.verifySmallStraight());
		
		int[] list2 = new int[] {3,2,3,4,6};
		Verify verify2 = new Verify(list2);
		assertFalse(verify2.verifySmallStraight());
	}
	
	public void testVerifyFullHouse() {
		int[] list = new int[] {2,2,2,3,3};
		verify = new Verify(list);
		assertTrue(verify.verifyFullHouse());
		
		int[] list2 = new int[] {5,5,4,6,6};
		Verify verify2 = new Verify(list2);
		assertFalse(verify2.verifyFullHouse());
	}
	
	public void testVerifyThreeOfAKind() {
		int[] list = new int[] {5,1,1,2,3};
		verify = new Verify(list);
		assertFalse(verify.verifyThreeOfAKind());
		
		int[] list2 = new int[] {4,3,4,2,4};
		Verify verify2 = new Verify(list2);
		assertTrue(verify2.verifyThreeOfAKind());
	}
	
	public void testVerifyFourOfAKind() {
		int[] list = new int[] {1,1,1,1,3};
		verify = new Verify(list);
		assertTrue(verify.verifyFourOfAKind());
		
		int[] list2 = new int[] {3,3,2,3,1};
		Verify verify2 = new Verify(list2);
		assertFalse(verify2.verifyFourOfAKind());
	}
	
	public void testVerifyYahtzee() {
		int[] list = new int[] {6,6,6,6,6};
		verify = new Verify(list);
		assertTrue(verify.verifyYahtzee());
		
		int[] list2 = new int[] {2,3,2,3,2};
		Verify verify2 = new Verify(list2);
		assertFalse(verify2.verifyYahtzee());
	}

}
