package com.chuchuye.Yahtzee;

import java.util.Arrays;

public class Verify {
	private int[] List = new int[5];
	
	Verify(int[] list){
		for(int index=0; index<5; index++) {
			List[index] = list[index];
		}
	}
	
	public int getList(int index) {
		return List[index];
	}
	
	public boolean verifyLargeStraight() {
		//验证是否满足LargeStraight的要求，不满足即为0
		//LargeStraight要么是1，2，3，4，5
		//要么是2，3，4，5，6
		//只有这两种情况
		Arrays.sort(List);
		int temp = List[0];
		for(int index=1; index<5; index++) {
			if(List[index] != temp+1) {
				return false;
			} else {
				temp = List[index];
			}
		}
		return true;
	}
	
	public boolean verifySmallStraight() {
		//验证是否满足SmallStraight
		//SmallStraight要么是1，2，3，4，x
		//要么是2，3，4，5，x
		//要么是3，4，5，6，x
		int[][] model = new int[][] {{1,1,2,3,4}, {1,2,2,3,4}, {1,2,3,3,4}, {1,2,3,4,4}, {1,2,3,4,5}, {1,2,3,4,6}, 
			{2,2,3,4,5}, {2,3,3,4,5}, {2,3,4,4,5}, {2,3,4,5,5}, {2,3,4,5,6}, 
			{3,3,4,5,6}, {3,4,4,5,6}, {3,4,5,5,6}, {3,4,5,6,6}, {1,3,4,5,6}};//共有16种情况
		Arrays.parallelSort(List);
		int count = 0;
		boolean flag = false;
		for(int indexA=0; indexA<16; indexA++) {
			if(count == 5) {
				break;
			}
			for(int indexB=0; indexB<5; indexB++) {
				if(List[indexB] == model[indexA][indexB]) {
					count++;
					if(count == 5) {
						//表示已有5个元素都相同
						flag = true;
						break;
					}
				} else {
					count = 0;
					break;
				}
			}
		}
		return flag;
	}
	
	public boolean verifyFullHouse() {
		int[] temp = new int[] {0,0,0,0,0,0};
		for(int index=0; index<5; index++) {
			switch(List[index]) {
				case 1:
					temp[0]++;
					break;
				case 2:
					temp[1]++;
					break;
				case 3:
					temp[2]++;
					break;
				case 4:
					temp[3]++;
					break;
				case 5:
					temp[4]++;
					break;
				case 6:
					temp[5]++;
					break;
			}
		}
		Arrays.parallelSort(temp);
		if(temp[4]==2 && temp[5]==3) {
			return true;
		} else {
			return false;
		}
	}
	
	public boolean verifyThreeOfAKind() {
		int[] temp = new int[] {0,0,0,0,0,0};
		for(int index=0; index<5; index++) {
			switch(List[index]) {
				case 1:
					temp[0]++;
					break;
				case 2:
					temp[1]++;
					break;
				case 3:
					temp[2]++;
					break;
				case 4:
					temp[3]++;
					break;
				case 5:
					temp[4]++;
					break;
				case 6:
					temp[5]++;
					break;
			}
		}
		Arrays.parallelSort(temp);
		if(temp[5] >= 3) {
			return true;
		} else {
			return false;
		}
	}
	
	public boolean verifyFourOfAKind() {
		int[] temp = new int[] {0,0,0,0,0,0};
		for(int index=0; index<5; index++) {
			switch(List[index]) {
				case 1:
					temp[0]++;
					break;
				case 2:
					temp[1]++;
					break;
				case 3:
					temp[2]++;
					break;
				case 4:
					temp[3]++;
					break;
				case 5:
					temp[4]++;
					break;
				case 6:
					temp[5]++;
					break;
			}
		}
		Arrays.parallelSort(temp);
		if(temp[5] >= 4) {
			return true;
		} else {
			return false;
		}
	}
	
	public boolean verifyYahtzee() {
		//5个元素必须全部相同，则返回true
		int temp = List[0];
		for(int index=1; index<5; index++) {
			if(temp == List[index]) {
				continue;
			} else {
				return false;
			}
		}
		return true;
	}

}
