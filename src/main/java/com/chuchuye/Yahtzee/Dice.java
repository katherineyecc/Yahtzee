package com.chuchuye.Yahtzee;

import java.util.Random;

public class Dice {
	
	private int[] diceList = new int[5];
	
	public void setDice(int[] dlist) {
		for(int index=0; index<5; index++) {
			diceList[index] = dlist[index];
		}
	}
	
	public int[] getDice() {
		return diceList;
	}
	
	public void genDice() {
		Random random = new Random();
		for(int index=0; index<5; index++) {
			diceList[index] = random.nextInt(6)+1;
		}
	}
	
	public void rowPartDice(String s) {
		String[] ss = s.split(" ");//用户输入的重掷骰子序号用空格隔开
		int[] is = new int[5];//is里面存放的是转换为整型的重掷序号
		int length = ss.length;//length为一共要重掷骰子的个数
		int index;
		for(index=0; index<length; index++) {
			is[index] = Integer.parseInt(ss[index])-1;
		}
		Random random = new Random();
		for(index=0; index<length; index++) {
			diceList[is[index]] = random.nextInt(6)+1;
		}
	}
	
	public int totalScore() {
		int total = 0;
		for(int index=0; index<5; index++) {
			total += diceList[index];
		}
		return total;
	}

}
