package chart;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class viewdataweekSummaryChartMES
 */
@WebServlet("/viewdataweekSummaryChartMES")
public class viewdataweekSummaryChartMES extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public viewdataweekSummaryChartMES() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * Displays the chart as a JPEG image
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// TODO Auto-generated method stub

		 File file = new File("dataweekSummchartMES.jpg");

	 
	        FileInputStream fis = new FileInputStream(file);
	        //create FileInputStream which obtains input bytes from a file in a file system
	        //FileInputStream is meant for reading streams of raw bytes such as image data. For reading streams of characters, consider using FileReader.
	 
	        //InputStream in = resource.openStream();
	        ByteArrayOutputStream bos = new ByteArrayOutputStream();
	        byte[] buf = new byte[1024];
	        try {
	            for (int readNum; (readNum = fis.read(buf)) != -1;) {
	                bos.write(buf, 0, readNum); 
	                //no doubt here is 0
	                /*Writes len bytes from the specified byte array starting at offset 
	                off to this byte array output stream.*/

	            }
	        } catch (IOException ex) {
	           
	        }
	        byte[] bytes = bos.toByteArray();
		byte[] imageBytes = bytes;

		response.setContentType("image/jpeg");
		response.setContentLength(imageBytes.length);

		response.getOutputStream().write(imageBytes);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
