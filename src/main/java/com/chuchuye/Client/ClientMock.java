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


public class ClientMock extends Client {
	private String welcomeMsg;
	private String endingMsg;
	
	public Player player = new Player();
	int[] diceList = new int[5];
	boolean roundStart = false;
	String choiceMsg = "What action would you like to perform next?\n"
			+ "(1)Select dice to re-roll?\n"
			+ "(2)Re-roll all the dice?\n"
			+ "(3)Score this round?\n";
	String endSignal = "end";
	String startSignal = "notStart";
	private String[] inputList = new String[13];
	String[] katList = new String[] {"1","2","3","4","5","6","7","8","9","10","11","12","13"};
	String[] kyrieList = new String[] {"13","12","11","10","9","8","7","6","5","4","3","2","1"};
	String[] kyleList = new String[] {"1","3","5","7","9","11","13","2","4","6","8","10","12"};
	
	public static void main(String[] args) {
		ClientMock cm = new ClientMock();
		cm.initClient3();
	}
	
	public void initClient() {
		try {
			Socket client = new Socket(InetAddress.getLocalHost(), 9090);
			final InputStream is = client.getInputStream();
			final OutputStream os = client.getOutputStream();
			welcomeMsg = readMsg(is);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void initClient2() {
		try {
			Socket client = new Socket(InetAddress.getLocalHost(), 9090);
			final InputStream is = client.getInputStream();
			final OutputStream os = client.getOutputStream();
			String msg = readMsg(is);
			String requestName = readMsg(is);
			String username = "kat";
			sendMsg(os, username);
			os.flush();
			msg = readMsg(is);
			String s = "";

			for(int index=0; index<13; index++) {
				continue;
			}
			endingMsg = "You have completed all 13 rounds! End game!";
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public String returnWelcomeMsg() {
		return welcomeMsg;
	}
	
	public String returnEndingMsg() {
		return endingMsg;
	}
	
	public void initClient3() {
		try {
			Socket client = new Socket(InetAddress.getLocalHost(), 9090);
			final InputStream is = client.getInputStream();
			final OutputStream os = client.getOutputStream();
			String msg = readMsg(is);//welcome to the game
			String requestName = readMsg(is);//myName
			if(requestName.contentEquals("kat")) {
				for(int index=0; index<13; index++) {
					inputList[index] = katList[index];
				}
			} else if(requestName.contentEquals("kyrie")) {
				for(int index=0; index<13; index++) {
					inputList[index] = kyrieList[index];
				}
			} else {
				for(int index=0; index<13; index++) {
					inputList[index] = kyleList[index];
				}
			}
			//String username = 
			msg = readMsg(is);//login successfully...
			String s = "";
			for(int index=0; index<13; index++) {
				while(true) {
					startSignal = readMsg(is);//start
					if(startSignal.contentEquals("start")) {
						break;
					}
				}
				startSignal = "notStart";
				Game game = new Game();
				game.rowTheDice();
				diceList = game.getDice();
				int temp = Integer.parseInt(inputList[index]);
				game.playerChoice(temp);
				player.setScoreBoard(temp-1, game.getScore(temp-1));
				
				sendMsg(os, Integer.toString(temp));
				os.flush();
				sendMsg(os, endSignal);
				os.flush();
				player.addRound();
				player.countScore();
				player.countBonus();
				player.countYahtzeeBonus();
			}
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
	
	public void setInputList(String[] il) {
		for(int index=0; index<13; index++) {
			inputList[index] = il[index];
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

}
