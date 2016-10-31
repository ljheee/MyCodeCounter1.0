package com.ljheee.io.stream;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
/**
 * 统计一个源文件 总行数、空行、注释、代码行数
 * @author ljheee
 *
 */
public class CodeInfo {
	
	public static void main(String[] args) {
		//读取文件
		readLine(new File("D:/mm.txt"));
	}

	public static void readLine(File file) {

		int totalLine = 0;
		int blankLine = 0;
		int commentLine = 0;
		int codeLine = 0;
		boolean isCommentLine = false;
		
		try (
			BufferedReader in = new BufferedReader(new FileReader(file));	
				
				){
			
			String line;
			
			while(null!=(line = in.readLine())){
				totalLine++;
				
				line = line.trim();//截断前后的空格
				
				//不在多行注释内
				if(!isCommentLine){
					
					if(line.length()==0){
						blankLine++;
						continue;//当行的只有2中情况，是空行，肯定不是单行注释，下面这个if就跳过不用判断了
					}
					if(line.startsWith("//")){
						commentLine++;
					}
				}
				
				
				
				if(line.startsWith("/*")){
					isCommentLine = true;;
				}
				if(isCommentLine){
					commentLine++;
				}
				if(line.endsWith("*/")){
					isCommentLine = false;
				}
				
				
			}
			//代码函数
			codeLine = totalLine - blankLine - commentLine;
			
			System.out.printf("总行：%d\n",totalLine);
			System.out.printf("空行：%d\n",blankLine);
			System.out.printf("注释：%d\n",commentLine);
			System.out.printf("代码：%d\n",codeLine);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
