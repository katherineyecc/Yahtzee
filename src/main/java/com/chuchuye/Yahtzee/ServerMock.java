package com.chuchuye.Yahtzee;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class ServerMock {
	
	public static ArrayList<ServerThreadMock>list = new ArrayList<ServerThreadMock>();
	
	private int playerNumber = 0;
	private int maxTurn = 13;
	private int currentRound = 1;
	
	private static int[] score = new int[] {-1,-1,-1};
	public boolean playerReady = false;
	public int[] ongoingPlayer = new int[39];
	public String[] player1Game = new String[13];
	public String[] player2Game = new String[13];
	public String[] player3Game = new String[13];//用来记录每一位玩家的选择
	public String[] playerName = new String[] {"kat", "kyrie", "kyle"};
	
	
	public void initServer() {
		
		try {
			//创建服务器对象，并制定端口号
			ServerSocket server = new ServerSocket(9090);
			System.out.println(InetAddress.getLocalHost());//显示当前服务器的ip地址
			System.out.println("Game engine is starting. Waiting for players to join...");
			//不断获取客户端的连接
			playerNumber = list.size();
			while(true) {
				
				if(playerNumber == 3) {
					playerReady = true;
					System.out.println("Press <<1>> if all the players is ready!");
					System.out.println("Press <<2>> if test is done!");
					char signal = (char)System.in.read();
					if(signal == '1') {
						break;
					} else {
						return;
					}
				}
				
				Socket socket = server.accept();
				System.out.println("Client is Connected...");
				//当有客户端连接进来以后，开启一个线程，用来处理该客户端的逻辑
				ServerThreadMock st = new ServerThreadMock(socket);
				//st.run();
				st.start();
				//添加该客户端到容器中
				list.add(st);
				list.get(playerNumber).setPlayerName(playerName[playerNumber]);
				
				playerNumber++;
				
			}
			int temp = 0;
			while(currentRound <= maxTurn) {
				
				list.get(0).goodToGo = true;
				//ongoingPlayer[temp++] = 1;
				System.out.println("Player #1 is good to go.");
				while(true) {
					if(list.get(0).currentSignal.contentEquals("end")) {
						//kat结束此轮游戏
						list.get(0).currentSignal = "notend";
						list.get(1).goodToGo = true;//kyrie is good to go
						System.out.println("Player #2 is good to go.");
						//ongoingPlayer[temp++] = 2;
						break;
					} else {
						System.out.println("Thread #2 and #3 are sleeping...");
						list.get(1).sleep(2000);
						list.get(2).sleep(2000);
					}
				}
				while(true) {
					if(list.get(1).currentSignal.contentEquals("end")) {
						//kyrie结束此轮游戏
						list.get(1).currentSignal = "notend";
						list.get(2).goodToGo = true;//kyle is good to go
						System.out.println("Player #3 is good to go.");
						//ongoingPlayer[temp++] = 3;
						break;
					} else {
						System.out.println("Thread #3 and #1 are sleeping...");
						list.get(2).sleep(2000);
						list.get(0).sleep(2000);
					}
				}
				while(true) {
					if(list.get(2).currentSignal.contentEquals("end")) {
						//kyle结束此轮游戏，即三名玩家本轮结束
						list.get(2).currentSignal = "notend";
						System.out.println("Round " + currentRound + " is over!");
						currentRound++;
						break;
					} else {
						System.out.println("Thread #1 and #2 are sleeping...");
						list.get(0).sleep(2000);
						list.get(1).sleep(2000);
					}
				}
			}
			
			
			System.out.println("The game is over!");
			//给3个玩家的线程清屏
			
			while(true) {
				if(list.get(0).getScore() != -1) {
					score[0] = list.get(0).getScore();
					System.out.println(score[0]);
					break;
				}
			}
			
			while(true) {
				if(list.get(1).getScore() != -1) {
					score[1] = list.get(1).getScore();
					System.out.println(score[1]);
					break;
				}
			}
			
			while(true) {
				if(list.get(2).getScore() != -1) {
					score[2] = list.get(2).getScore();
					System.out.println(score[2]);
					break;
				}
			}
			
			//找胜者
			int max = score[0];
			int mark = 0;
			for(int index=1; index<3; index++) {
				if(max < score[index]) {
					max = score[index];
					mark = index;
				} else {
					continue;
				}
			}
			
			String winnerName = list.get(mark).getPlayerName();
			System.out.println(winnerName);
			
			for(int index=0; index<3; index++) {
				list.get(index).setWinnerName(winnerName);
			}
			
			
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void initServer2() {
		try {
			//创建服务器对象，并制定端口号
			ServerSocket server = new ServerSocket(9090);
			System.out.println(InetAddress.getLocalHost());//显示当前服务器的ip地址
			System.out.println("Game engine is starting. Waiting for players to join...");
			//不断获取客户端的连接
			playerNumber = list.size();
			while(true) {
				
				if(playerNumber == 3) {
					playerReady = true;
					System.out.println("Press <<1>> if all the players is ready!");
					System.out.println("Press <<2>> if test is done!");
					char signal = (char)System.in.read();
					if(signal == '1') {
						break;
					} else {
						return;
					}
				}
				
				Socket socket = server.accept();
				System.out.println("Client is Connected...");
				//当有客户端连接进来以后，开启一个线程，用来处理该客户端的逻辑
				ServerThreadMock st = new ServerThreadMock(socket);
				//st.run();
				st.start();
				//添加该客户端到容器中
				list.add(st);
				list.get(playerNumber).setPlayerName(playerName[playerNumber]);
				
				playerNumber++;
				
			}
			int temp = 0;
			while(currentRound <= maxTurn) {
				
				list.get(0).goodToGo = true;
				ongoingPlayer[temp] = 1;
				temp++;
				System.out.println("Player #1 is good to go.");
				while(true) {
					if(list.get(0).currentSignal.contentEquals("end")) {
						//kat结束此轮游戏
						list.get(0).currentSignal = "notend";
						list.get(1).goodToGo = true;//kyrie is good to go
						System.out.println("Player #2 is good to go.");
						ongoingPlayer[temp] = 2;
						temp++;
						break;
					} else {
						System.out.println("Thread #2 and #3 are sleeping...");
						list.get(1).sleep(2000);
						list.get(2).sleep(2000);
					}
				}
				while(true) {
					if(list.get(1).currentSignal.contentEquals("end")) {
						//kyrie结束此轮游戏
						list.get(1).currentSignal = "notend";
						list.get(2).goodToGo = true;//kyle is good to go
						System.out.println("Player #3 is good to go.");
						ongoingPlayer[temp] = 3;
						temp++;
						break;
					} else {
						System.out.println("Thread #3 and #1 are sleeping...");
						list.get(2).sleep(2000);
						list.get(0).sleep(2000);
					}
				}
				while(true) {
					if(list.get(2).currentSignal.contentEquals("end")) {
						//kyle结束此轮游戏，即三名玩家本轮结束
						list.get(2).currentSignal = "notend";
						System.out.println("Round " + currentRound + " is over!");
						currentRound++;
						break;
					} else {
						System.out.println("Thread #1 and #2 are sleeping...");
						list.get(0).sleep(2000);
						list.get(1).sleep(2000);
					}
				}
			}
			
			setPlayerInput(list.get(0).playerInput, list.get(1).playerInput, list.get(2).playerInput);
			System.out.println("The game is over!");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public static void setScore(int index, int i) {
		// TODO Auto-generated method stub
		score[index] = i;
	}

	public static int getScore(int index) {
		// TODO Auto-generated method stub
		return score[index];
	}
	
	public void setPlayerInput(String[] s1, String[] s2, String[] s3) {
		for(int index=0; index<13; index++) {
			player1Game[index] = s1[index];
			player2Game[index] = s2[index];
			player3Game[index] = s3[index];
		}
	}
	
	public static void main(String[] args) {
		Server server = new Server();
		server.initServer();
		
	}
	
}
