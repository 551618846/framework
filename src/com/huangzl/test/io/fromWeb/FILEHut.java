package com.huangzl.test.io.fromWeb;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;

//文件合并类
public class FILEHut {
	private static File[] fileList;
	private static RandomAccessFile saveFile;
	private static InputStream in;
	private static byte[] buf;
	
	private static Boolean isCutFile(File f, String tg)
	{
		String file = f.getName();
		String hz = file.substring(file.lastIndexOf(".")+1).toLowerCase();
		if (hz.compareTo(tg)==0)
		{
			return true;
		}
		return false;
	}
	public static void Hut(String fname, String tgt) throws IOException
	{
		File f = new File(new File(fname).getParent());
		fileList = f.listFiles();
		int seeklen = 0;
			saveFile = new RandomAccessFile(fname, "rw");
			saveFile.seek(0);

			for (int i=0; i<fileList.length; i++)
			{
				if (isCutFile(fileList[i], tgt)==true)
				{
					in = new FileInputStream(fileList[i]);
					buf = new byte[(int) fileList[i].length()];
					in.read(buf);
					saveFile.write(buf);
					seeklen+=(int) fileList[i].length();
					saveFile.seek(seeklen);
				}
			}
			saveFile.close();
			
			
	}
}

