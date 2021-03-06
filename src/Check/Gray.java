package Check;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

import MethodsOfImages.ImagesName;

public class Gray{
   public static String Gray(String string) throws Exception {
      System.loadLibrary( Core.NATIVE_LIBRARY_NAME );

      String input = string;

      Mat src = Imgcodecs.imread(input);

      Mat dst = new Mat();

      Imgproc.cvtColor(src, dst, Imgproc.COLOR_RGB2GRAY);

      byte[] data1 = new byte[dst.rows() * dst.cols() * (int)(dst.elemSize())];
      dst.get(0, 0, data1);

      BufferedImage bufImage = new BufferedImage(dst.cols(),dst.rows(), 
         BufferedImage.TYPE_BYTE_GRAY);

      bufImage.getRaster().setDataElements(0, 0, dst.cols(), dst.rows(), data1);
      
      System.out.println("Converted to Gray");
      
      String[] strings=ImagesName.ImagesFormat(string);
      String url = ImagesName.setImagesName(strings, 2);
		
      ImageIO.write(bufImage,"jpg",new File(url));
      return url;
   }
}