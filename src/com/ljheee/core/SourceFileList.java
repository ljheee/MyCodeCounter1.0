package com.ljheee.core;

import java.util.ArrayList;

public class SourceFileList {
	private ArrayList<SourceFileInfo> list = null;

	public SourceFileList() {
		list = new ArrayList<>();
	}
	
	public void add(SourceFileInfo sfile){
		list.add(sfile);
	}
	
	public void remove(int i){
		list.remove(i);
	}

	public int size(){
		return list.size();
	}
	
	public SourceFileInfo get(int i){
		return list.get(i);
	}
}
