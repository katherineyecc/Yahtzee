package com.chuchuye.Client;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;

import com.chuchuye.Yahtzee.Game;
import com.chuchuye.Yahtzee.Player;
import com.chuchuye.Yahtzee.Server;

public class Client {
	
	public Player player = new Player();
	public Player[] enemy = new Player[2];
	int[] diceList = new int[5];
	boolean roundStart = false;//一轮游戏结束变为false，得到Server的信号变为true
	
	String choiceMsg = "What action would you like to perform next?\n"
			+ "(1)Select dice to re-roll?\n"
			+ "(2)Re-roll all the dice?\n"
			+ "(3)Score this round?\n";
	String endSignal = "end";
	String startSignal = "notStart";
	
	
	public static void main(String[] args) {
		Client client = new Client();
		client.initClient();
	}
	
	public void initClient() {
		try {
		
			//创建客户端对象
			Socket client = new Socket(InetAddress.getLocalHost(), 9090);
			//获取客户端的输入输出流
			final InputStream is = client.getInputStream();
			final OutputStream os = client.getOutputStream();
			//接收服务器端发来的welcome
			String msg = readMsg(is);
			System.out.println(msg);
			//接收服务器端发来的输入玩家姓名的请求
			String requestName = readMsg(is);
			System.out.println(requestName);
			//从控制台输入姓名

			final Scanner scanner = new Scanner(System.in);
			String username = scanner.nextLine();
			//nextLine()方法会吞掉输入最后的回车
			
			//发送玩家姓名
			sendMsg(os, username);
			os.flush();
			//接收加入成功的通知
			msg = readMsg(is);
			System.out.println(msg);//login successfully...
			
			player.setName(username);
			
			
			String s = "";
			//游戏开始
			for(int index=0; index<13; index++) {
				clearWindow();
				printScoreBoard(); 
				Game game = new Game();
				System.out.println("Press <<1>> to roll the dice.");
				String input = scanner.nextLine();
				if(input == "1") {}
				game.rowTheDice();
				diceList = game.getDice();
				s =  "Your Rolled:|"+diceList[0]+"|   |"+diceList[1]+"|   |"+diceList[2]+"|   |"+diceList[3]+"|   |"+
						diceList[4]+"|";
				System.out.println(s);
				System.out.println(choiceMsg);
				input = scanner.nextLine();
				while(game.getRowCount() <= 3) {
					if(input.contentEquals("1") == true) {
						//...
						System.out.println("Please enter in the dice position that you want to re-roll. Please seperate each number with a <<SPACE>>.");
						s = scanner.nextLine();
						game.rowPartDice(s);
						diceList = game.getDice();
						System.out.println("Your Rolled:|"+diceList[0]+"|   |"+diceList[1]+"|   |"+diceList[2]+"|   |"+diceList[3]+"|   |"+
								diceList[4]+"|");
						System.out.println(choiceMsg);
						input = scanner.nextLine();
						continue;
					}
					else if(input.contentEquals("2") == true) {
						game.rowTheDice();
						diceList = game.getDice();
						System.out.println("Your Rolled:|"+diceList[0]+"|   |"+diceList[1]+"|   |"+diceList[2]+"|   |"+diceList[3]+"|   |"+
								diceList[4]+"|");
						System.out.println(choiceMsg);
						input = scanner.nextLine();
						continue;
					}
					else if(input.contentEquals("3") == true) {
						System.out.println("What category do you want to score this round against? Please enter the category number:");
						input = scanner.nextLine();
						int temp = Integer.parseInt(input);
						game.playerChoice(temp);
						player.setScoreBoard(temp-1, game.getScore(temp-1));
						break;
					}
					else {
						System.out.println("Invalid input! Please input again!");
						System.out.println(choiceMsg);
					}
				}
				//一轮游戏结束
				System.out.println("You have completed this round!");
				
				sendMsg(os, endSignal);
				os.flush();
				while(true) {
					//等待服务器端的信号才能开始下一轮
					startSignal = readMsg(is);
					if(startSignal.contentEquals("start")) {
						break;
					} else {
						System.out.println("You still need to wait...");
					}
				}
				startSignal = "notStart";
				
				System.out.println("Press <<1>> to start next round...");
				s = scanner.nextLine();
				if(s == "1") {}
				player.addRound();
				player.countScore();
				player.countBonus();
				player.countYahtzeeBonus();
			}
			
			
			//剩下来的工作就是把最后的总分发送给服务器端
			System.out.println("You have completed all 13 rounds!");
			System.out.println("Please wait for other player to complete...");
			
			msg = Integer.toString(player.getScore());
			sendMsg(os, msg);
			
			String winnerName = null;
			
			while(true) {
				winnerName = readMsg(is);
				if(winnerName != null)
					break;
			}
			System.out.println("The Winner is:"+winnerName+"!");		
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
	public String readMsg(InputStream is) throws Exception {
		int value = is.read();
		String str = "";
		while(value != 10) {
			//代表客户端不正常关闭
			if(value == -1) {
				throw new Exception();
			}
			str = str + (char)value;
			value = is.read();
		}
		str = str.trim();
		return str;
	}
	
	public void sendMsg(OutputStream os, String str) throws Exception {
		byte[] bytes = str.getBytes();
		os.write(bytes);
		os.write(13);
		os.write(10);
		os.flush();
	}
	
	public void printScoreBoard(){
		
		String str;
		str = "-----------------------------------------------------------------------------------------";
		System.out.println(str);
		str = "Name:"+player.getName()+"      Current Score:"+player.getScore()+
				"      Current Round:"+player.getCurrentRound();
		System.out.println(str);
		str = "|(1)Ones:"+player.getSpecificScore(0)+"    |(2)Twos:"+player.getSpecificScore(1)+
				"    |(3)Threes:"+player.getSpecificScore(2)+"    |";
		System.out.println(str);
		str = "|(4)Fours:"+player.getSpecificScore(3)+"    |(5)Fives:"+player.getSpecificScore(4)+
				"    |(6)Sixes:"+player.getSpecificScore(5)+"    |";
		System.out.println(str);
		str = "Bonus:"+player.getBonus();
		System.out.println(str);
		str = "|(7)Large Straight:"+player.getSpecificScore(6)+"    |(8)Small Straight:"+player.getSpecificScore(7)+
				"    |(9)Full House:"+player.getSpecificScore(8)+"    |";
		System.out.println(str);
		str = "|(10)Three of a Kind:"+player.getSpecificScore(9)+"    |(11)Four of a Kind:"+player.getSpecificScore(10)+
				"    |(12)Chance:"+player.getSpecificScore(11)+"    |";
		System.out.println(str);
		str = "|(13)Yatzee:"+player.getSpecificScore(12)+"    |Yahtzee Bonus:"+player.getYahtzeeBonus()+"    |";
		System.out.println(str);
		str = "-----------------------------------------------------------------------------------------";
		System.out.println(str);
		str = "\r\n";
		System.out.println(str);
	}
	
	public void clearWindow() {
		String str =  "\r\n"+"\r\n"+"\r\n"+"\r\n"+"\r\n"+"\r\n"+"\r\n"+"\r\n"+"\r\n"+"\r\n"+
				"\r\n"+"\r\n"+"\r\n"+"\r\n"+"\r\n"+"\r\n"+"\r\n"+"\r\n"+"\r\n"+"\r\n"+
				"\r\n"+"\r\n"+"\r\n"+"\r\n"+"\r\n"+"\r\n"+"\r\n"+"\r\n"+"\r\n"+"\r\n"+
				"\r\n"+"\r\n"+"\r\n"+"\r\n"+"\r\n"+"\r\n"+"\r\n"+"\r\n"+"\r\n"+"\r\n"+
				"\r\n"+"\r\n"+"\r\n"+"\r\n"+"\r\n"+"\r\n"+"\r\n"+"\r\n"+"\r\n"+"\r\n";
		System.out.println(str);
	}

}
