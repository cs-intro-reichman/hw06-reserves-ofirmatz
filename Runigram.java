// This class uses the Color class, which is part of a package called awt,
// which is part of Java's standard class library.
import java.awt.Color;

/** A library of image processing functions. */
public class Runigram {

	public static void main(String[] args) {
	    
		//// Hide / change / add to the testing code below, as needed.
		
		// Tests the reading and printing of an image:	
		Color[][] tinypic = read("tinypic.ppm");
		print(tinypic);

		// Creates an image which will be the result of various 
		// image processing operations:
		Color[][] imageOut;

		// Tests the horizontal flipping of an image:
		//imageOut = flippedHorizontally(tinypic);
		//System.out.println();
		//print(imageOut);

		//// Write here whatever code you need in order to test your work.
		//// You can reuse / overide the contents of the imageOut array.

		//Color[][] outputimage;

		//outputimage = flippedVertically(tinypic);
		//System.out.println();
		//print(outputimage);

		//// Write here whatever code you need in order to test your work.
		//// You can reuse / overide the contents of the imageOut array.
		//Color lumTest;

		//lumTest = luminance(tinypic[0][3]);
		//System.out.println();
		//print(lumTest);
		
		//// Write here whatever code you need in order to test your work.
		//// You can reuse / overide the contents of the imageOut array.
		imageOut = grayScaled(tinypic);
		System.out.println();
		print(imageOut);
	}

	/** Returns a 2D array of Color values, representing the image data
	 * stored in the given PPM file. */
	public static Color[][] read(String fileName) {
		In in = new In(fileName);
		// Reads the file header, ignoring the first and the third lines.
		in.readString();
		int numCols = in.readInt();
		int numRows = in.readInt();
		in.readInt();
		// Creates the image array
		Color[][] image = new Color[numRows][numCols];
		int red;
		int green;
		int blue;
		for(int i = 0; i < numRows; i++){
			for(int j = 0; j < numCols; j++){
				red = in.readInt();
				green = in.readInt();
				blue = in.readInt();
				image[i][j] = new Color(red, green, blue);
			}
		}
		// Reads the RGB values from the file, into the image array. 
		// For each pixel (i,j), reads 3 values from the file,
		// creates from the 3 colors a new Color object, and 
		// makes pixel (i,j) refer to that object.
		//// Replace the following statement with your code.
		return image;
	}

    // Prints the RGB values of a given color.
	private static void print(Color c) {
	    System.out.print("(");
		System.out.printf("%3s,", c.getRed());   // Prints the red component
		System.out.printf("%3s,", c.getGreen()); // Prints the green component
        System.out.printf("%3s",  c.getBlue());  // Prints the blue component
        System.out.print(")  ");
	}

	// Prints the pixels of the given image.
	// Each pixel is printed as a triplet of (r,g,b) values.
	// This function is used for debugging purposes.
	// For example, to check that some image processing function works correctly,
	// we can apply the function and then use this function to print the resulting image.
	private static void print(Color[][] image) {
		int numrows = image.length;
		int numcols = image[0].length;

		for(int i = 0; i < numrows; i++){
			for(int j = 0; j < numcols; j++){
				print(image[i][j]);
			}
			System.out.println();
		}
	}
	
	/**
	 * Returns an image which is the horizontally flipped version of the given image. 
	 */
	public static Color[][] flippedHorizontally(Color[][] image) {
		int numRows = image.length;
		int numcols = image[0].length;
		int flipParam = numcols - 1;
		Color[][] flipedImage = new Color[numRows][numcols];
		for(int i = 0; i < numRows; i++){
			for(int j = 0; j < numcols; j++){
				flipedImage[i][j] = image[i][flipParam - j];
			}
		}
		//// Replace the following statement with your code
		return flipedImage;
	}
	
	/**
	 * Returns an image which is the vertically flipped version of the given image. 
	 */
	public static Color[][] flippedVertically(Color[][] image){
		int numRows = image.length;
		int numCols = image[0].length;
		int flipParam = numRows - 1;
		Color[][] verticalyFlipedImage = new Color[numRows][numCols];
		for(int i = 0; i < numRows; i++){
			for(int j = 0; j < numCols; j++){
				verticalyFlipedImage[i][j] = image[flipParam - i][j];
			}
		}
		return verticalyFlipedImage;
	}
	
	// Computes the luminance of the RGB values of the given pixel, using the formula 
	// lum = 0.299 * r + 0.587 * g + 0.114 * b, and returns a Color object consisting
	// the three values r = lum, g = lum, b = lum.
	public static Color luminance(Color pixel) {
		double red = 0.299 * pixel.getRed();
		double green = 0.587 * pixel.getGreen();
		double blue = 0.114 * pixel.getBlue();
		int lum = (int) (red + green + blue);

		Color luminated = new Color(lum, lum, lum);
		//// Replace the following statement with your code
		return luminated;
	}
	
	/**
	 * Returns an image which is the grayscaled version of the given image.
	 */
	public static Color[][] grayScaled(Color[][] image) {
		int numRows = image.length;
		int numcols = image[0].length;
		Color[][] grayImage = new Color[numRows][numcols];
		
		for(int i = 0; i < numRows; i++){
			for(int j = 0; j < numcols; j++){
				grayImage[i][j] = luminance(image[i][j]);
			}
		}
		return grayImage;
	}	
	
	/**
	 * Returns an image which is the scaled version of the given image. 
	 * The image is scaled (resized) to have the given width and height.
	 */
	public static Color[][] scaled(Color[][] image, int width, int height) {
		int numRows = image.length; // the original h0
		int numCols = image[0].length; // the original w0
		Color[][] scaledImage = new Color[height][width];
		// calculating acording to the given formula 
		double newRow = (double) numRows / height; // h0/h 
		double newCol = (double) numCols / width; // w0/w 

		for (int i = 0; i < height; i++){
			for (int j = 0; j < width; j++){
				int rowScaled = (int) (i * newRow);
				int colScaled = (int) (j * newCol);
				scaledImage [i][j] = image [rowScaled][colScaled];
			}
		}
		return scaledImage;
	}
	
	/**
	 * Computes and returns a blended color which is a linear combination of the two given
	 * colors. Each r, g, b, value v in the returned color is calculated using the formula 
	 * v = alpha * v1 + (1 - alpha) * v2, where v1 and v2 are the corresponding r, g, b
	 * values in the two input color.
	 */
	public static Color blend(Color c1, Color c2, double alpha) {
		int red = (int)(alpha * (c1.getRed()) + (1 - alpha) * (c2.getRed()));
		int green = (int)(alpha * (c1.getGreen()) + (1 - alpha) * (c2.getGreen()));
		int blue = (int)(alpha * (c1.getBlue()) + (1 - alpha) * (c2.getBlue()));

		Color blended = new Color(red, green, blue);	
			return blended;
	}
	
	/**
	 * Cosntructs and returns an image which is the blending of the two given images.
	 * The blended image is the linear combination of (alpha) part of the first image
	 * and (1 - alpha) part the second image.
	 * The two images must have the same dimensions.
	 */
	public static Color[][] blend(Color[][] image1, Color[][] image2, double alpha){
		int numRows = image1.length;
		int numCols = image1[0].length;
		Color[][] blendedImage = new Color[numRows][numCols];
		for(int i = 0; i < numRows; i++){
			for(int j = 0; j < numCols; j++){

				blendedImage[i][j] = blend(image1[i][j], image2[i][j], alpha);
			
			} 
		}		

		return blendedImage;
	}

	/**
	 * Morphs the source image into the target image, gradually, in n steps.
	 * Animates the morphing process by displaying the morphed image in each step.
	 * Before starting the process, scales the target image to the dimensions
	 * of the source image.
	 */
	public static void morph(Color[][] source, Color[][] target, int n) {
		int sourceHeight = source.length;
		int sourceWidth = source[0].length;
		double alpha = 0;

		Color[][] morphedDimension = scaled(target, sourceWidth, sourceHeight);
		Color[][] mixed;

		for (int i = 0; i <= n; i++)
		{
			alpha = (n - i) / n;
			mixed = blend(source, morphedDimension, alpha);
			display(mixed);
			StdDraw.pause(500);

		}
	}
	
	/** Creates a canvas for the given image. */
	public static void setCanvas(Color[][] image) {
		StdDraw.setTitle("Runigram 2023");
		int height = image.length;
		int width = image[0].length;
		StdDraw.setCanvasSize(height, width);
		StdDraw.setXscale(0, width);
		StdDraw.setYscale(0, height);
        // Enables drawing graphics in memory and showing it on the screen only when
		// the StdDraw.show function is called.
		StdDraw.enableDoubleBuffering();
	}

	/** Displays the given image on the current canvas. */
	public static void display(Color[][] image) {
		int height = image.length;
		int width = image[0].length;
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				// Sets the pen color to the pixel color
				StdDraw.setPenColor( image[i][j].getRed(),
					                 image[i][j].getGreen(),
					                 image[i][j].getBlue() );
				// Draws the pixel as a filled square of size 1
				StdDraw.filledSquare(j + 0.5, height - i - 0.5, 0.5);
			}
		}
		StdDraw.show();
	}
}

