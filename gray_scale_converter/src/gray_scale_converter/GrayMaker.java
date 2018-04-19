package gray_scale_converter;

import java.io.File;

import edu.duke.DirectoryResource;
import edu.duke.ImageResource;
import edu.duke.Pixel;

/*
 * This program allows the user to select an image file and will return that image in a grey scale
 * and return a second image with inverted colors. This program was completed for a Duke Coursera 
 * assignment and utilizes the Edu Duke library. 
 * 
 * 
 * @author Zev Yirmiyahu
 * 
 * @since 20/04/2018
 * 
 */

public class GrayMaker {
	
	public ImageResource makeGray(ImageResource inputImage) {
		
		//Create out image of same size
		ImageResource outputImage = new ImageResource(inputImage.getWidth(), inputImage.getHeight());
		
		for(Pixel pixel : outputImage.pixels()) {
			
			Pixel inputPixel = inputImage.getPixel(pixel.getX(), pixel.getY());
			
			//Gray scale algorithm
			int average = (inputPixel.getRed() + inputPixel.getGreen() + inputPixel.getBlue()) / 3;
			
			pixel.setRed(average);
			pixel.setGreen(average);
			pixel.setBlue(average);
		}
		return outputImage; //return gray image.
	}
	
	public ImageResource makeInverted(ImageResource inputImage) {
		
		//Create out image of same size
		ImageResource outputImage = new ImageResource(inputImage.getWidth(), inputImage.getHeight());
		
		for(Pixel pixel : outputImage.pixels()) {
			
			Pixel inputPixel = inputImage.getPixel(pixel.getX(), pixel.getY());
			
			//Inverted color scale algorithm
			pixel.setRed(255 - inputPixel.getRed());
			pixel.setGreen(255 - inputPixel.getGreen());
			pixel.setBlue(255 - inputPixel.getBlue());
		}
		return outputImage; //return inverted image.
	}
	
	//Method allows for the selection of multiple files
	public void selectFiles() {
		DirectoryResource dr = new DirectoryResource();
		for(File file : dr.selectedFiles()) {
			ImageResource inputImage = new ImageResource(file);
			
			ImageResource grayImage = makeGray(inputImage); //make image grey
			grayImage.draw(); //draw gray image
			String fileName = grayImage.getFileName();
			String newFileName = "COPY-" + fileName;
			
			grayImage.setFileName(newFileName);
			grayImage.save(); //saves file to same directory as image
			
			//saveFiles(grayImage); //Saves all gray scale files to same directory as original image
			
			ImageResource invertedImage = makeInverted(inputImage); //make inverted image
			invertedImage.draw();
		}
	}
	
	
	public void saveFiles(ImageResource image) {
		
			ImageResource newImage = new ImageResource();
		
			String fileName = image.getFileName();
			String newFileName = "COPY-" + fileName;
			
			newImage.setFileName(newFileName);
			newImage.save(); //saves file to same directory as image
	}
	
	
	public void saveFiles() {
		DirectoryResource dr = new DirectoryResource();
		
		for(File file : dr.selectedFiles()) {
			ImageResource image = new ImageResource(file);
			
			String fileName = image.getFileName();
			String newFileName = "COPY-" + fileName;
			
			image.setFileName(newFileName);
			image.save(); //saves file to same directory as image
		}
	}
	
	public static void main(String args[]) {
		GrayMaker ob = new GrayMaker();
		
		ob.selectFiles();
	}

}
