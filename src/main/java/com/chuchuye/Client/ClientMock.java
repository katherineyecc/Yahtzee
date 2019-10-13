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
	
	public String returnWelcomeMsg() {
		return welcomeMsg;
	}
	
	public String returnEndingMsg() {
		return endingMsg;
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