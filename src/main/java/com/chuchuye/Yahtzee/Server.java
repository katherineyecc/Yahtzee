package com.chuchuye.Yahtzee;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server {
	
	public static ArrayList<ServerThread>list = new ArrayList<ServerThread>();
	
	private int playerNumber = 0;
	private int maxTurn = 13;
	private int currentRound = 1;
	
	private static int[] score = new int[] {-1,-1,-1};
	
	
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
					System.out.println("Press <<1>> if all the players is ready!");
					char signal = (char)System.in.read();
					if(signal == '1')
						break;
				}
				
				Socket socket = server.accept();
				System.out.println("Client is Connected...");
				//当有客户端连接进来以后，开启一个线程，用来处理该客户端的逻辑
				ServerThread st = new ServerThread(socket);
				//st.run();
				st.start();
				//添加该客户端到容器中
				list.add(st);
				
				playerNumber++;
				
			}
			
			while(currentRound <= maxTurn) {
				
				list.get(0).goodToGo = true;
				System.out.println("Player #1 is good to go.");
				while(true) {
					if(list.get(0).currentSignal.contentEquals("end")) {
						//kat结束此轮游戏
						list.get(0).currentSignal = "notend";
						list.get(1).goodToGo = true;//kyrie is good to go
						System.out.println("Player #2 is good to go.");
						break;
					} else {
						System.out.println("Thread #2 and #3 are sleeping...");
						list.get(1).sleep(5000);
						list.get(2).sleep(5000);
					}
				}
				while(true) {
					if(list.get(1).currentSignal.contentEquals("end")) {
						//kyrie结束此轮游戏
						list.get(1).currentSignal = "notend";
						list.get(2).goodToGo = true;//kyle is good to go
						System.out.println("Player #3 is good to go.");
						break;
					} else {
						System.out.println("Thread #3 and #1 are sleeping...");
						list.get(2).sleep(5000);
						list.get(0).sleep(5000);
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
						list.get(0).sleep(5000);
						list.get(1).sleep(5000);
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
	public static void setScore(int index, int i) {
		// TODO Auto-generated method stub
		score[index] = i;
	}

	public static int getScore(int index) {
		// TODO Auto-generated method stub
		return score[index];
	}
	
	public static void main(String[] args) {
		Server server = new Server();
		server.initServer();
		
	}
	
}
