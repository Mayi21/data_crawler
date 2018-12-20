package Test;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * @author may
 * @date 2018/12/19
 * @deprecated single test
 */
public class GetItermImg {
	public static void main(String args[]) throws Exception{
		System.out.println(getItermImgFromNet());
	}
	public static boolean getItermImgFromNet() throws Exception{
		String itermId = "100000078737";
		String contentUrl = "https://item.jd.com/100000078737.html";
		Document document = Jsoup.parse(getItermContent(contentUrl));
		String id = document.getElementById("spec-img").toString();
		String imgArray[] = id.split("\"");
		//System.out.println(imgArray[5]);
		String urlImg = "http:" + imgArray[5];
		String fileName[] = urlImg.split("\\/");
		String itermImgFilePath = "D:\\Picture\\jingdong\\" + itermId + "\\";
		File imgPathFolder = new File(itermImgFilePath);
		if (!imgPathFolder.exists()){
			imgPathFolder.mkdir();
			System.out.println("创建成功");
		}
		File file = new File(itermImgFilePath ,itermId + ".jpg");
		URL url = new URL(urlImg);
		URLConnection urlConnection = url.openConnection();
		InputStream inputStream1 = urlConnection.getInputStream();
		BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream1);
		BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(file));
		int a = -1;
		while ((a = bufferedInputStream.read()) != -1){
			bufferedOutputStream.write((byte)a);
		}
		bufferedOutputStream.flush();
		bufferedInputStream.close();
		bufferedOutputStream.close();
		File file1 = new File(itermImgFilePath);
		if (file1.exists() && file.exists()){
			return true;
		} else {
			return false;
		}
	}
	public static String getItermContent(String contentUrl) throws Exception{
		URL url1 = new URL(contentUrl);
		HttpURLConnection httpURLConnection = (HttpURLConnection)url1.openConnection();
		httpURLConnection.setDoInput(true);
		httpURLConnection.setRequestMethod("GET");
		//获取输入流
		InputStream inputStream = httpURLConnection.getInputStream();
		InputStreamReader inputStreamReader = new InputStreamReader(inputStream,"GBK");
		BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
		//从输入流获取结果
		StringBuffer stringBuffer = new StringBuffer();
		String string = null;
		while ((string = bufferedReader.readLine()) != null){
			stringBuffer.append(string);
		}
		if (bufferedReader != null){
			bufferedReader.close();
		}
		if (inputStreamReader != null){
			inputStreamReader.close();
		}
		if (inputStream != null){
			inputStream.close();
		}
		if (httpURLConnection != null){
			httpURLConnection.disconnect();
		}
		return stringBuffer.toString();
	}

}