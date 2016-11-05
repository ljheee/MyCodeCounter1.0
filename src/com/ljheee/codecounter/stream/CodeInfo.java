package com.ljheee.codecounter.stream;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
/**
 * ͳ��һ��Դ�ļ� �����������С�ע�͡���������
 * @author ljheee
 *
 */
public class CodeInfo {
	
	public static void main(String[] args) {
		//��ȡ�ļ�
		readLine(new File("D:/mm.txt"));
	}

	public static int[] readLine(File file) {
		int[] a = new int[4];

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
				
				line = line.trim();//�ض�ǰ��Ŀո�
				
				//���ڶ���ע����
				if(!isCommentLine){
					
					if(line.length()==0){
						blankLine++;
						continue;//���е�ֻ��2��������ǿ��У��϶����ǵ���ע�ͣ��������if�����������ж���
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
			//���뺯��
			codeLine = totalLine - blankLine - commentLine;
			
//			System.out.printf("���У�%d\n",totalLine);
//			System.out.printf("���У�%d\n",blankLine);
//			System.out.printf("ע�ͣ�%d\n",commentLine);
//			System.out.printf("���룺%d\n",codeLine);
			a[0] = totalLine;
			a[1] = blankLine;
			a[2] = commentLine;
			a[3] = codeLine;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return a;
	}
}
