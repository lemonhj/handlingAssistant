package com.septinary.common.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.io.StringWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 文件（系统）操作
 * @Filename: com.septinary.common.util.FileUtil.java of the project [com.septinary.common]
 *     @Type: FileUtil
 *     @Desc: TODO
 *   @Author: macbook[weide<weide001@gmail.com>]
 *  @Created: 2016年4月12日上午11:39:05
 *
 */
abstract public class FileUtil {
	
	public static final String FOLDER_SEPARATOR = new String(File.separatorChar+"");

	public static final String WINDOWS_FOLDER_SEPARATOR = "\\";

	public static final String FOLDERS_SEPARATOR = (WINDOWS_FOLDER_SEPARATOR.equals(FOLDER_SEPARATOR)) ? ";" : ":";

	public static final String TOP_PATH = "..";

	public static final String CURRENT_PATH = ".";

	public static final String EXTENSION_SEPARATOR = ".";
    
    /**
     * 文件路径逐级列举	注：文件分隔符为正则模式
     * @param path
     * @param delimiterPattern
     * @return String[]
     */
    static public String[] SplitPath(String path, String delimiterPattern) {
    	if( null==path ) return null;
    	
    	List<String> result = new ArrayList<String>();
    	String delimiter = StringUtil.Invalid(delimiterPattern) ? PatternMatchUtil.Escape(FOLDER_SEPARATOR) : delimiterPattern;
    	
    	while( true ) {
    		result.add(0,path);
    		if( !path.matches("^.*("+delimiter+")+.*$") || path.matches("^("+delimiter+")+$") ) break;
    		String tmp = path;
    		path = path.substring(0, StringUtil.LastOf(path, delimiter));
    		path = StringUtil.Invalid(path) ? StringUtil.Find(tmp,delimiter) : path;
    	}
    	
    	return result.toArray(new String[result.size()]);
    }
    
    /**
     * 文件路径逐级列举	注：文件分隔符为系统默认分隔符
     * @param path
     * @return
     */
    static public String[] SplitPath(String path) {
    	return SplitPath(path, null);
    }

    /**
     * 获取文件路径中除了文件名称外的基础路径部分（即目录部分）
     @method StringUtil: BasePath()
     @memo TODO
     @param path
     @param separator
     @return String
     */
    static public String FilePath(String path, String separator) {
    	if(null==path) return path;
    	separator = null==separator ? FOLDER_SEPARATOR : separator;
    	int index = path.lastIndexOf(separator);
    	if(0>index) return "";
    	return path.substring(0,index+separator.length());
    }
    
    /**
     * 获取文件路径中除了文件名称外的基础路径部分（即目录部分）	注：文件分隔符为系统默认分隔符
     * @param path
     * @return
     */
    static public String FilePath(String path) {
    	return FilePath(path,null);
    }
    
    /**
     * 获取文件路径中的文件名称（除了目录部分的文件基础名称+扩展名称部分）
     @method StringUtil: FileName()
     @memo TODO
     @param path
     @param separator
     @return String
     */
    static public String FileName(String path, String separator) {
    	if(null==path) return path;
    	separator = null==separator ? FOLDER_SEPARATOR : separator;
    	int index = path.lastIndexOf(separator);
    	if(0>index) return path;
    	return path.substring(index+separator.length());
    }
    
    /**
     * 获取文件路径中的文件名称（除了目录部分的文件基础名称+扩展名称部分）	注：文件分隔符为系统默认分隔符
     * @param path
     * @return
     */
    static public String FileName(String path) {
    	return FileName(path,null);
    }
    
    /**
     * 获取文件路径中文件基础名称（排除了目录部分和扩展名部分）
     * @param path
     * @param separator
     * @param extseparator
     * @return
     */
    static public String FileBase(String path, String separator, String extseparator) {
    	String filename = FileName(path, separator);
    	if(StringUtil.Invalid(filename)) {
    		return filename;
    	}
    	extseparator = null==extseparator ? EXTENSION_SEPARATOR : extseparator;
    	int index = filename.lastIndexOf(extseparator);
    	if(0>index) return filename;
    	return filename.substring(0,index);
    }
    
    /**
     * 获取文件路径中文件基础名称（排除了目录部分和扩展名部分）	注：文件扩展名分隔符为系统默认分隔符
     * @param path
     * @param separator
     * @return
     */
    static public String FileBase(String path, String separator) {
    	return FileBase(path, separator, null);
    }
    
    /**
     * 	获取文件路径中文件基础名称（排除了目录部分和扩展名部分）	注：文件分隔符为系统默认分隔符
     * @param path
     * @return
     */
    static public String FileBase(String path) {
    	return FileBase(path, null);
    }
    
    /**
     * 获取文件路径中的扩展名称
     @method FileUtil: FileExtension()
     @memo TODO
     @param path
     @return String
     */
    public static String FileExtension(String path) {
		if (path == null) {
			return null;
		}
		int extIndex = path.lastIndexOf(EXTENSION_SEPARATOR);
		if (extIndex == -1) {
			return null;
		}
		int folderIndex = path.lastIndexOf(FOLDER_SEPARATOR);
		if (folderIndex > extIndex) {
			return null;
		}
		return path.substring(extIndex + 1);
	}
    
    /**
     * 去除文件路径中的扩展名称
     @method FileUtil: StripFileExtension()
     @memo TODO
     @param path
     @return String
     */
    public static String StripFileExtension(String path) {
		if (path == null) {
			return null;
		}
		int extIndex = path.lastIndexOf(EXTENSION_SEPARATOR);
		if (extIndex == -1) {
			return path;
		}
		int folderIndex = path.lastIndexOf(FOLDER_SEPARATOR);
		if (folderIndex > extIndex) {
			return path;
		}
		return path.substring(0, extIndex);
	}
    
    /**
     * 组装文件名称
     @method FileUtil: MakeFilename()
     @memo TODO
     @param filedir
     @param filename
     @param fileext
     @return String
     */
	public static String MakeFilename(String filedir, String filename, String fileext) {
		filedir = StringUtil.Invalid(filedir) ? "" : filedir.trim();
		filename = StringUtil.Invalid(filename) ? "" : filename.trim();
		fileext = StringUtil.Invalid(fileext) ? "" : fileext.trim();
		
		String fullname = filedir;
		fullname += fullname.endsWith(FOLDER_SEPARATOR) ? filename : FOLDER_SEPARATOR+filename;
		fullname += fullname.matches(PatternMatchUtil.Escape(EXTENSION_SEPARATOR+fileext)+"$") ? "" : fileext.startsWith(EXTENSION_SEPARATOR)?fileext:EXTENSION_SEPARATOR+fileext;
		
		return StringUtil.Invalid(filename) ? null : filename;
	}
	public static String MakeFilename(String filename, String fileext) {
		return MakeFilename(null,filename,fileext);
	}
    
    /**
     * 整合相对路径到path下
     @method FileUtil: CombineRelativePath()
     @memo TODO
     @param path
     @param relativePath
     @return String
     */
    public static String CombineRelativePath(String path, String relativePath) {
		int separatorIndex = path.lastIndexOf(FOLDER_SEPARATOR);
		if (separatorIndex != -1) {
			String newPath = path.substring(0, separatorIndex);
			if (!relativePath.startsWith(FOLDER_SEPARATOR)) {
				newPath += FOLDER_SEPARATOR;
			}
			return newPath + relativePath;
		} else {
			return relativePath;
		}
	}

	/**
	 * 整理路径为简洁可用路径，即处理".",".."等
	 @method FileUtil: CleanPath()
	 @memo TODO
	 @param path
	 @return String
	 */
	public static String CleanPath(String path) {
		if (path == null) {
			return null;
		}
		String pathToUse = StringUtil.Replace(path, WINDOWS_FOLDER_SEPARATOR, FOLDER_SEPARATOR);

		// Strip prefix from path to analyze, to not treat it as part of the
		// first path element. This is necessary to correctly parse paths like
		// "file:core/../core/io/Resource.class", where the ".." should just
		// strip the first "core" directory while keeping the "file:" prefix.
		int prefixIndex = pathToUse.indexOf(":");
		String prefix = "";
		if (prefixIndex != -1) {
			prefix = pathToUse.substring(0, prefixIndex + 1);
			if (prefix.contains("/")) {
				prefix = "";
			} else {
				pathToUse = pathToUse.substring(prefixIndex + 1);
			}
		}
		if (pathToUse.startsWith(FOLDER_SEPARATOR)) {
			prefix = prefix + FOLDER_SEPARATOR;
			pathToUse = pathToUse.substring(1);
		}

		String[] pathArray = StringUtil.DelimitedListToStringArray(pathToUse, FOLDER_SEPARATOR);
		List<String> pathElements = new LinkedList<String>();
		int tops = 0;

		for (int i = pathArray.length - 1; i >= 0; i--) {
			String element = pathArray[i];
			if (CURRENT_PATH.equals(element)) {
				// Points to current directory - drop it.
			} else if (TOP_PATH.equals(element)) {
				// Registering top path found.
				tops++;
			} else {
				if (tops > 0) {
					// Merging path element with element corresponding to top path.
					tops--;
				} else {
					// Normal path element found.
					pathElements.add(0, element);
				}
			}
		}

		// Remaining top paths need to be retained.
		for (int i = 0; i < tops; i++) {
			pathElements.add(0, TOP_PATH);
		}

		return prefix + StringUtil.CollectionToDelimitedString(pathElements, FOLDER_SEPARATOR);
	}

	/**
	 * 路径比较
	 @method FileUtil: PathEquals()
	 @memo TODO
	 @param path1
	 @param path2
	 @return boolean
	 */
	public static boolean PathEquals(String path1, String path2) {
		return CleanPath(path1).equals(CleanPath(path2));
	}

	/**
	 * 递归删除文件	即逐层删除
	 @method FileUtil: DeleteRecursively()
	 @memo TODO
	 @param root
	 @return boolean
	 */
	public static boolean DeleteRecursively(File root) {
		if (root != null && root.exists()) {
			if (root.isDirectory()) {
				File[] children = root.listFiles();
				if (children != null) {
					for (File child : children) {
						DeleteRecursively(child);
					}
				}
			}
			return root.delete();
		}
		return false;
	}

	/**
	 * 递归拷贝	即逐层拷贝
	 @method FileUtil: CopyRecursively()
	 @memo TODO
	 @param src
	 @param dest
	 @throws IOException void
	 */
	public static void CopyRecursively(File src, File dest) throws IOException {
		Assert.IsTrue(src != null && (src.isDirectory() || src.isFile()), "Source File must denote a directory or file");
		Assert.NotNull(dest, "Destination File must not be null");
		do_copy_recursively(src, dest);
	}

	private static void do_copy_recursively(File src, File dest) throws IOException {
		if (src.isDirectory()) {
			dest.mkdir();
			File[] entries = src.listFiles();
			if (entries == null) {
				throw new IOException("Could not list files in directory: " + src);
			}
			for (File entry : entries) {
				do_copy_recursively(entry, new File(dest, entry.getName()));
			}
		} else if (src.isFile()) {
			try {
				dest.createNewFile();
			} catch (IOException ex) {
				IOException ioex = new IOException("Failed to create file: " + dest);
				ioex.initCause(ex);
				throw ioex;
			}
			Copy(src, dest);
		} else {
			// Special File handle: neither a file not a directory.
			// Simply skip it when contained in nested directory...
		}
	}

	//---------------------------------------------------------------------
	// Copy methods for java.io.File
	//---------------------------------------------------------------------

	/**
	 * 从输入文件中拷贝到输出文件中
	 * Copy the contents of the given input File to the given output File.
	 * @param in the file to copy from
	 * @param out the file to copy to
	 * @return the number of bytes copied
	 * @throws IOException in case of I/O errors
	 */
	public static int Copy(File in, File out) throws IOException {
		Assert.NotNull(in, "No input File specified");
		Assert.NotNull(out, "No output File specified");
		return Copy(new BufferedInputStream(new FileInputStream(in)),new BufferedOutputStream(new FileOutputStream(out)));
	}

	/**
	 * 从输入字节流中拷贝到输出文件中
	 * Copy the contents of the given byte array to the given output File.
	 * @param in the byte array to copy from
	 * @param out the file to copy to
	 * @throws IOException in case of I/O errors
	 */
	public static void Copy(byte[] in, File out) throws IOException {
		Assert.NotNull(in, "No input byte array specified");
		Assert.NotNull(out, "No output File specified");
		ByteArrayInputStream inStream = new ByteArrayInputStream(in);
		OutputStream outStream = new BufferedOutputStream(new FileOutputStream(out));
		Copy(inStream, outStream);
	}

	/**
	 * 从输入文件中拷贝到输出字节流中
	 * Copy the contents of the given input File into a new byte array.
	 * @param in the file to copy from
	 * @return the new byte array that has been copied to
	 * @throws IOException in case of I/O errors
	 */
	public static byte[] CopyToByteArray(File in) throws IOException {
		Assert.NotNull(in, "No input File specified");
		return CopyToByteArray(new BufferedInputStream(new FileInputStream(in)));
	}


	//---------------------------------------------------------------------
	// Copy methods for java.io.InputStream / java.io.OutputStream
	//---------------------------------------------------------------------

	/**
	 * 从输入流中拷贝到输出流中
	 * Copy the contents of the given InputStream to the given OutputStream.
	 * Closes both streams when done.
	 * @param in the stream to copy from
	 * @param out the stream to copy to
	 * @return the number of bytes copied
	 * @throws IOException in case of I/O errors
	 */
	public static int Copy(InputStream in, OutputStream out) throws IOException {
		Assert.NotNull(in, "No InputStream specified");
		Assert.NotNull(out, "No OutputStream specified");
		try {
			return StreamUtil.Copy(in, out);
		}
		finally {
			try {
				in.close();
			} catch (IOException ex) {
			}
			try {
				out.close();
			} catch (IOException ex) {
			}
		}
	}

	/**
	 * 从输入字节流中拷贝到输出流中
	 * Copy the contents of the given byte array to the given OutputStream.
	 * Closes the stream when done.
	 * @param in the byte array to copy from
	 * @param out the OutputStream to copy to
	 * @throws IOException in case of I/O errors
	 */
	public static void Copy(byte[] in, OutputStream out) throws IOException {
		Assert.NotNull(in, "No input byte array specified");
		Assert.NotNull(out, "No OutputStream specified");
		try {
			out.write(in);
		}
		finally {
			try {
				out.close();
			} catch (IOException ex) {
			}
		}
	}

	/**
	 * 从输入流中拷贝到输出字节流中
	 * Copy the contents of the given InputStream into a new byte array.
	 * Closes the stream when done.
	 * @param in the stream to copy from
	 * @return the new byte array that has been copied to
	 * @throws IOException in case of I/O errors
	 */
	public static byte[] CopyToByteArray(InputStream in) throws IOException {
		ByteArrayOutputStream out = new ByteArrayOutputStream(StreamUtil.BUFFER_SIZE);
		Copy(in, out);
		return out.toByteArray();
	}


	//---------------------------------------------------------------------
	// Copy methods for java.io.Reader / java.io.Writer
	//---------------------------------------------------------------------

	/**
	 * 从读取器中拷贝到写入器中
	 * Copy the contents of the given Reader to the given Writer.
	 * Closes both when done.
	 * @param in the Reader to copy from
	 * @param out the Writer to copy to
	 * @return the number of characters copied
	 * @throws IOException in case of I/O errors
	 */
	public static int Copy(Reader in, Writer out) throws IOException {
		Assert.NotNull(in, "No Reader specified");
		Assert.NotNull(out, "No Writer specified");
		try {
			int byteCount = 0;
			char[] buffer = new char[StreamUtil.BUFFER_SIZE];
			int bytesRead = -1;
			while ((bytesRead = in.read(buffer)) != -1) {
				out.write(buffer, 0, bytesRead);
				byteCount += bytesRead;
			}
			out.flush();
			return byteCount;
		}
		finally {
			try {
				in.close();
			} catch (IOException ex) {
			}
			try {
				out.close();
			} catch (IOException ex) {
			}
		}
	}

	/**
	 * 从输入字符串中拷贝到写入器中
	 * Copy the contents of the given String to the given output Writer.
	 * Closes the writer when done.
	 * @param in the String to copy from
	 * @param out the Writer to copy to
	 * @throws IOException in case of I/O errors
	 */
	public static void Copy(String in, Writer out) throws IOException {
		Assert.NotNull(in, "No input String specified");
		Assert.NotNull(out, "No Writer specified");
		try {
			out.write(in);
		}
		finally {
			try {
				out.close();
			} catch (IOException ex) {
			}
		}
	}

	/**
	 * 从读取器中输出到字符串
	 * Copy the contents of the given Reader into a String.
	 * Closes the reader when done.
	 * @param in the reader to copy from
	 * @return the String that has been copied to
	 * @throws IOException in case of I/O errors
	 */
	public static String CopyToString(Reader in) throws IOException {
		StringWriter out = new StringWriter();
		Copy(in, out);
		return out.toString();
	}
	
	/**
	 * 解析文本文件头信息中的编码格式
	 @method FileUtil: ParseCharset()
	 @memo TODO
	 @param filename
	 @param defaultCharset
	 @return String
	 */
	public static String ParseCharset(String filename, String defaultCharset) {
		InputStream inputStream = null;
        byte[] head = new byte[3];
		try {
			inputStream = new FileInputStream(filename);
			inputStream.read(head);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				inputStream.close();
			} catch (IOException e) {
			}
		}
        String code = StringUtil.Invalid(defaultCharset) ? "GB2312" : defaultCharset;  
        if (head[0] == -1 && head[1] == -2 ) code = "UTF-16";  
        if (head[0] == -2 && head[1] == -1 ) code = "Unicode";  
        if(head[0]==-17 && head[1]==-69 && head[2] ==-65) code = "UTF-8";  
          
        return code;
	}
}
