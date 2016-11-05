package com.ljheee.codecounter.core;

import java.io.File;

public class ScanFiles {
	
	public File src;
	public String type;
	public SourceFileList list;

	public ScanFiles() {
	}

	public ScanFiles(File src, String type) {
		super();
		this.src = src;
		this.type = type;
		list = new SourceFileList();
	}


	public SourceFileList scan(){
		if(src == null){
			
			return null;
		}
		
		if(src.isFile()){
			scanSangleFile(src);
		}else{
			scanDir(src);
		}
		
		return list;
	}
	
	private void scanSangleFile(File f){
		String name = f.getName();
		if(name.endsWith(type)){   //
			list.add(new SourceFileInfo(f));
		}
		return;
	}
	
	private void scanDir(File dir){
		File[] lists = dir.listFiles();
		for (File file : lists) {
			if(file.isFile()){
				scanSangleFile(file);
			}else{
				scanDir(file);
			}
		}
	}
	
	
}
