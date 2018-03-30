package com.beigebigdata.bdCourt.ca.admin.common.utils;

import org.apache.commons.lang.StringUtils;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;


public class FileUtil {

	/**
	 * 读取模板文件
	 * @param file
	 * @return
	 */
	public static String readResourceFile(String file) {
        String content = "";
        try {
            InputStream is = FileUtil.class.getClassLoader().getResourceAsStream(file);
            byte[] b = null;
            byte[] buf = new byte[1024];
            int num = -1;
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            while ((num = is.read(buf, 0, buf.length)) != -1) {
                baos.write(buf, 0, num);
            }
            b = baos.toByteArray();
            content = new String(b, "UTF-8");
            baos.flush();
            baos.close();
            is.close();
        } catch (Exception e) {

        }
        return content;
    }
	
	/**
	 * 生成文件
	 * @param dir 文件路径
	 * @param file 文件名
	 * @return
	 */
	public static String createFile(String dir, String file) {
        if (!dir.endsWith("/")) {
            dir = dir + "/";
        }
        if (file.startsWith("")) {
            file = file.substring(1);
        }
        String filePath = dir + file;
        String dirPath = getFileDir(filePath);
        File files = new File(dirPath);
		if(!files.exists()){
			files.mkdirs();
		}
        return filePath;
    }
	
	/**
     * 从文件路径获取目录路径
     * 
     * @param filepath
     * @return
     */
	public static String getFileDir(String filepath) {
        String dir = null;
        if (StringUtils.isNotEmpty(filepath) && filepath.indexOf("/") != -1) {
            dir = filepath.substring(0, filepath.lastIndexOf("/") + 1);
        }
        return dir;
    }
	
	public static void writeContentToFile(String file, String content) throws Exception {
		File ffFile = new File(file);
		if (ffFile.exists()) {
			ffFile.delete();
		}
        FileOutputStream fos = new FileOutputStream(ffFile);
        fos.write(content.getBytes("UTF-8"));
        fos.flush();
        fos.close();
    }
	
	public static void main(String[] args) {
//		String content = readResourceFile("风险提示html格式.txt");
//		System.out.println(content);
		System.err.println(createFile("D:\\zlvjinping\\2016-06-15","11111.txt"));
 	}
	
	public static String readAgreement(String file) {
        String content = "";
        try {
            InputStream is = FileUtil.class.getClassLoader().getResourceAsStream(file);
            byte[] b = null;
            byte[] buf = new byte[1024];
            int num = -1;
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            while ((num = is.read(buf, 0, buf.length)) != -1) {
                baos.write(buf, 0, num);
            }
            b = baos.toByteArray();
            content = new String(b, "UTF-8");
            baos.flush();
            baos.close();
            is.close();
        } catch (Exception e) {

        }
        return content;
    }

	
}
