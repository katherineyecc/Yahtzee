package com.chuchuye.Yahtzee;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.ArrayList;

public class ServerThread extends Thread {
	
	private int score = -1;
	
	public String myName;
	
	private String winnerName = null;
	
	public Socket socket;
	public InputStream is;
	public OutputStream os;
	
	public String currentSignal = "notend";
	public int maxTurn = 13;
	public int currentRound = 1;
	public boolean goodToGo = false;
	
	public ServerThread(Socket socket) {
		this.socket = socket;
	}
	
	public void run() {
		try {
			//获取输入输出流
			is = socket.getInputStream();
			os = socket.getOutputStream();
			//发送消息给客户端
			String msg = "Welcome to the Game!";
			sendMsg(os, msg);
			//发送要求登录信息给客户端
			String userInfo = "Please input your name:";
			sendMsg(os, userInfo);
			//获取客户端输入的用户名
			String playerName = readMsg(is);//***
			myName = playerName;
			//signal = true;
			//发送加入成功的结果给客户端
			msg = "login successfully! Waiting for game start...";
			sendMsg(os, msg);
			
			
			System.out.println(playerName + " has entered the game!");
			
			while(currentRound <= maxTurn) {
				currentSignal = readMsg(is);
				if(currentSignal.contentEquals("end")) {
					//玩家一轮游戏结束后发来end信号
					currentRound++;
					while(true) {
						if(goodToGo == false) {
							System.out.println("goodToGo is still FALSE.");
							sleep(2000);
						} else {
							sendMsg(os, "start");
							os.flush();
							goodToGo = false;
							//currentSignal = "notend";
							break;
						}
					}
				}
			}
			
			String input = readMsg(is);
			score = Integer.parseInt(input);

			while(true) {
				if(winnerName == null) {
					sleep(1000);
				}
				else {
					msg = winnerName;
					sendMsg(os, msg);
					break;
				}
			}
			
			
		} catch (Exception e) {
			System.out.println("Server is closed abnormally.");
			e.printStackTrace();
		}
		//异常出现后统一将流关闭
		try {
			is.close();
			os.close();
			socket.close();
			//将当前已经关闭的客户端从容器中移除
			Server.list.remove(this);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void sendMsg(OutputStream os, String s) throws IOException {
		//向客户端发送信息
		byte[] bytes = s.getBytes();
		os.write(bytes);
		os.write(13);
		os.write(10);
		os.flush();
	}
	
	public String readMsg(InputStream is) throws Exception {
		//读取客户端输入的信息
		int value = is.read();
		//读取整行，读取到回车（13）和换行（10）时停止读
		String str = "";
		while(value != 10) {
			//点击关闭客户端时会返回-1
			if(value == -1) {
				throw new Exception();
			}
			str = str + ((char) value);
			value = is.read();
		}
		str = str.trim();
		return str;
	}
	
	
	public String getPlayerName() {
		return myName;
	}
	
	public int getScore() {
		return score;
	}
	
	public void setWinnerName(String name) {
		winnerName = name;
	}
	
}
