package com.chuchuye.Yahtzee;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server {
	
	public static ArrayList<ServerThread>list = new ArrayList<ServerThread>();
	
	private int playerNumber = 0;
	
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
					System.out.println("Press <<1>> if all the game is over!");
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
	
	
}
