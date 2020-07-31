package com.tarpitout.aio.jdk.file;

import java.io.File;

public class FilePathTest {
	public static void main(String[] args) {
		FilePathTest fpt = new FilePathTest();
		fpt.printPath();
	}
	
	public void printPath() {
		File directory = new File("katsura");
		printFilePath(directory);
		File dot = new File(".");
		printFilePath(dot);
		File dotdot = new File("..");
		printFilePath(dotdot);
		File backSlass = new File("\\");
		printFilePath(backSlass);
	}

	private void printFilePath(File directory) {
		try {
			System.out.println("System.getProperty(\"user.dir\")\t" + System.getProperty("user.dir"));
			System.out.println("directory.getCanonicalPath():\t" + directory.getCanonicalPath());
			System.out.println("directory.getAbsolutePath():\t" + directory.getAbsolutePath());
			System.out.println("directory.getPath():\t" + directory.getPath());
		} catch(Exception e) {
			
		}
	}
}
