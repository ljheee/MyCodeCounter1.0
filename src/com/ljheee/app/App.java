package com.ljheee.app;

import java.io.File;

import com.ljheee.core.ScanFiles;
import com.ljheee.core.SourceFileInfo;
import com.ljheee.core.SourceFileList;

public class App {

	public static void main(String[] args) {

		SourceFileList  list = new ScanFiles(new File("E:/GitCode/FileIO"), ".txt").scan();;
	
		for (int i = 0; i < list.size(); i++) {
			SourceFileInfo sf  = list.get(i);
			System.out.println(sf.totalLine);
		}
	}

}
