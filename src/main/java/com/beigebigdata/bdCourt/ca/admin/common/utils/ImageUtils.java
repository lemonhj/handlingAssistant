package com.beigebigdata.bdCourt.ca.admin.common.utils;

import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGEncodeParam;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.URL;

public class ImageUtils {

	
	public static void generateImage(String outImgPath,BufferedImage  src,int new_w,int new_h) throws IOException
	{
		float per=0.9f;
		//原图片地址
		//File file = new File(inImgPath);
		//BufferedImage  src = ImageIO.read(inStream);
		//System.out.println(inStream.toString());
		//得到原始宽度
		int old_w = src.getWidth();
		//得到原始高度
		int old_h = src.getHeight();
		
		//根据原始大小生成一个空白画布
		BufferedImage tempImage = new BufferedImage(old_w,old_h,BufferedImage.TYPE_INT_BGR);
		// 在新的画布上生成原图的缩略图
		Graphics2D g = tempImage.createGraphics();
		g.setColor(Color.white);
		g.fillRect(0, 0, old_w, old_h);
		g.drawImage(src, 0, 0, old_w, old_h, Color.white, null);
		g.dispose();
		
		 BufferedImage newImg = new BufferedImage(new_w, new_h,  
	                BufferedImage.TYPE_INT_RGB);  
	        newImg.getGraphics().drawImage(  
	        		tempImage.getScaledInstance(new_w, new_h, Image.SCALE_SMOOTH), 0,  
	                0, null);  
	        // 调用方法输出图片文件  
	        File outfile =new File(outImgPath);
	        if (!outfile.getParentFile().exists()) 
	        {  
	        	outfile.getParentFile().mkdirs();  
	        }
	        FileOutputStream newimage = new FileOutputStream(outImgPath);  
	        JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(newimage); 
	        JPEGEncodeParam jep = JPEGCodec.getDefaultJPEGEncodeParam(newImg);  
	        jep.setQuality(per, false);
	        encoder.encode(newImg,jep);  
            newimage.close();
	}
	
	public static void compressionOfProportion(String outImgPath,int new_w,int new_h,InputStream inStream) throws IOException{
		BufferedImage bi = ImageIO.read(inStream);
		double width = bi.getWidth();
		double heigth = bi.getHeight();
		
		if(width > new_w || heigth > new_h){
			double wscale = width/new_w;
			double hscale = heigth/new_h;
			
			if(wscale > hscale){
				new_h = (int)Math.round(heigth/wscale);
			}else{
				new_w = (int)Math.round(width/hscale);
			}
		}else{
			new_h = (int)heigth;
			new_w = (int)width;
		}
		ImageUtils.generateImage(outImgPath, bi, new_w, new_h);
	}
	
	
	
	 // 生成图片函数  
    public static void makeImg(String imgUrl,String fileURL) {  
        try {  
  
            // 创建流  
            BufferedInputStream in = new BufferedInputStream(new URL(imgUrl).openStream());  
  
            // 生成图片名  
            int index = imgUrl.lastIndexOf("/");  
            String sName = imgUrl.substring(index+1, imgUrl.length());  
            System.out.println(sName);  
            // 存放地址  
            File img = new File(fileURL+sName);  
            // 生成图片  
            BufferedOutputStream out = new BufferedOutputStream(  
                    new FileOutputStream(img));  
            byte[] buf = new byte[2048];  
            int length = in.read(buf);  
            while (length != -1) {  
                out.write(buf, 0, length);  
                length = in.read(buf);  
            }  
            in.close();  
            out.close();  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
    } 
	/*public static void main(String[] args) {
//		double d = 800.0/51;
//		System.out.println(d);
		try {
			//File addrFile = new File("D:/1.jpg");
			//compressionOfProportion("D:/2.jpg","D:/1.jpg",600, 600,addrFile);
			//compressionOfProportion("F:/img/2.jpg","F:/img/org.jpg",200, 200,addrFile);
			//compressionOfProportion("F:/img/3.jpg","F:/img/org.jpg",300, 300,addrFile);
			//compressionOfProportion("F:/img/4.jpg","F:/img/org.jpg",400, 400,addrFile);
			//compressionOfProportion("F:/img/5.jpg","F:/img/org.jpg",600, 600,addrFile);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}*/

}
