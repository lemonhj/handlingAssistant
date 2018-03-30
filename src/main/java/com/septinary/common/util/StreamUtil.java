package com.septinary.common.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FilterInputStream;
import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.nio.charset.Charset;

/**
 * 流操作
 * @Filename: com.septinary.common.util.StreamUtil.java of the project [com.septinary.common]
 *     @Type: StreamUtil
 *     @Desc: TODO
 *   @Author: macbook[weide<weide001@gmail.com>]
 *  @Created: 2016年4月13日上午12:30:37
 *
 */
abstract public class StreamUtil {

	public static final int BUFFER_SIZE = 4096;

	private static final byte[] EMPTY_CONTENT = new byte[0];


	/**
	 * 从输入流中拷贝字节	即读取
	 @method StreamUtil: CopyToByteArray()
	 @memo TODO
	 @param in
	 @return
	 @throws IOException byte[]
	 */
	public static byte[] CopyToByteArray(InputStream in) throws IOException {
		ByteArrayOutputStream out = new ByteArrayOutputStream(BUFFER_SIZE);
		Copy(in, out);
		return out.toByteArray();
	}

	/**
	 * 从输入流中拷贝字符串	即读取
	 @method StreamUtil: CopyToString()
	 @memo TODO
	 @param in
	 @param charset
	 @return
	 @throws IOException String
	 */
	public static String CopyToString(InputStream in, Charset charset) throws IOException {
		Assert.NotNull(in, "No InputStream specified");
		StringBuilder out = new StringBuilder();
		InputStreamReader reader = new InputStreamReader(in, charset);
		char[] buffer = new char[BUFFER_SIZE];
		int bytesRead = -1;
		while ((bytesRead = reader.read(buffer)) != -1) {
			out.append(buffer, 0, bytesRead);
		}
		return out.toString();
	}

	/**
	 * 向输出流中拷贝字节流	即输出
	 @method StreamUtil: Copy()
	 @memo TODO
	 @param in
	 @param out
	 @throws IOException void
	 */
	public static void Copy(byte[] in, OutputStream out) throws IOException {
		Assert.NotNull(in, "No input byte array specified");
		Assert.NotNull(out, "No OutputStream specified");
		out.write(in);
	}

	/**
	 * 向输出流中拷贝字符串	即输出
	 * Copy the contents of the given String to the given output OutputStream.
	 * Leaves the stream open when done.
	 * @param in the String to copy from
	 * @param charset the Charset
	 * @param out the OutputStream to copy to
	 * @throws IOException in case of I/O errors
	 */
	public static void Copy(String in, Charset charset, OutputStream out) throws IOException {
		Assert.NotNull(in, "No input String specified");
		Assert.NotNull(charset, "No charset specified");
		Assert.NotNull(out, "No OutputStream specified");
		Writer writer = new OutputStreamWriter(out, charset);
		writer.write(in);
		writer.flush();
	}

	/**
	 * 向输出流中拷贝	即输出
	 * Copy the contents of the given InputStream to the given OutputStream.
	 * Leaves both streams open when done.
	 * @param in the InputStream to copy from
	 * @param out the OutputStream to copy to
	 * @return the number of bytes copied
	 * @throws IOException in case of I/O errors
	 */
	public static int Copy(InputStream in, OutputStream out) throws IOException {
		Assert.NotNull(in, "No InputStream specified");
		Assert.NotNull(out, "No OutputStream specified");
		int byteCount = 0;
		byte[] buffer = new byte[BUFFER_SIZE];
		int bytesRead = -1;
		while ((bytesRead = in.read(buffer)) != -1) {
			out.write(buffer, 0, bytesRead);
			byteCount += bytesRead;
		}
		out.flush();
		return byteCount;
	}

	/**
	 * 获取空的输入流
	 @method StreamUtil: EmptyInput()
	 @memo TODO
	 @return InputStream
	 */
	public static InputStream EmptyInput() {
		return new ByteArrayInputStream(EMPTY_CONTENT);
	}

	/**
	 * 获取未关闭的输入流
	 * Return a variant of the given {@link InputStream} where calling
	 * {@link InputStream#close() close()} has no effect.
	 * @param in the InputStream to decorate
	 * @return a version of the InputStream that ignores calls to close
	 */
	public static InputStream NonClosing(InputStream in) {
		Assert.NotNull(in, "No InputStream specified");
		return new NonClosingInputStream(in);
	}

	/**
	 * 获取未关闭的输出流
	 * Return a variant of the given {@link OutputStream} where calling
	 * {@link OutputStream#close() close()} has no effect.
	 * @param out the OutputStream to decorate
	 * @return a version of the OutputStream that ignores calls to close
	 */
	public static OutputStream NonClosing(OutputStream out) {
		Assert.NotNull(out, "No OutputStream specified");
		return new NonClosingOutputStream(out);
	}


	private static class NonClosingInputStream extends FilterInputStream {

		public NonClosingInputStream(InputStream in) {
			super(in);
		}

		@Override
		public void close() throws IOException {
		}
	}


	private static class NonClosingOutputStream extends FilterOutputStream {

		public NonClosingOutputStream(OutputStream out) {
			super(out);
		}

		@Override
		public void write(byte[] b, int off, int let) throws IOException {
			// It is critical that we override this method for performance
			out.write(b, off, let);
		}

		@Override
		public void close() throws IOException {
		}
	}
}
