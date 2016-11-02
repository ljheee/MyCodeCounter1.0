package com.ljheee.core;

import java.io.File;

import com.ljheee.io.stream.CodeInfo;

public class SourceFileInfo{
	
	
	private static final long serialVersionUID = 1L;
	
	public int totalLine = 0;
	public int blankLine = 0;
	public int commentLine = 0;
	public int codeLine = 0;
	

	public SourceFileInfo(File f) {
		int a[] = CodeInfo.readLine(f);
		totalLine = a[0];
		blankLine = a[1];
		commentLine = a[2];
		codeLine = a[3];
	}
}
