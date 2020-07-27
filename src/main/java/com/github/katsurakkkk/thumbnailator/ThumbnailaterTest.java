//package com.github.katsurakkkk.thumbnailator;
//
//import java.awt.Image;
//import java.io.File;
//import java.io.IOException;
//import java.util.Iterator;
//
//import javax.imageio.ImageIO;
//import javax.imageio.ImageReader;
//import javax.imageio.stream.ImageInputStream;
//
//import net.coobird.thumbnailator.Thumbnails;
//
//public class ThumbnailaterTest {
//	public static void main(String[] args) {
//		ThumbnailaterTest TT = new ThumbnailaterTest();
//		TT.testScale();
//	}
//
//	public void testScale() {
//		try {
//			//通过文件的类型获取
////			 Iterator readers = ImageIO.getImageReadersByFormatName("jpg");
//			//通过文件的后缀获取
//			 Iterator readers = ImageIO.getImageReadersBySuffix("jpg");
//			ImageReader reader = (ImageReader) readers.next();
//			File file = new File("src/main/resources/saber.jpg");
//			ImageInputStream input = ImageIO.createImageInputStream(file);
//			reader.setInput(input, true);
//			int width = reader.getWidth(0);
//			int height = reader.getHeight(0);
////			Image image = ImageIO.read(new File("src/main/resources/saber.jpg"));
//			System.out.println("width:" + width + "   height" + height);
//			Thumbnails.of("src/main/resources/saber.jpg").scale(0.25).outputFormat("jpg").toFile("src/main/resources/saber_025.jpg");
////			Thumbnails.of("src/resources/5M_jpg.jpg").scale(0.25).outputFormat("jpg").outputQuality(1f).toFile("src/resources/5M_jpg_025.jpg");
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
//}
