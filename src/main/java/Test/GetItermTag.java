package Test;


import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GetItermTag {
	public static void main(String[] args) throws Exception{
		getInfo(getHtmlContent());
	}
	public static String getHtmlContent() throws Exception {
		//建立GET请求
		String url = "https://item.jd.com/100000001004.html";
		URL url1 = new URL(url);
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
	public static void getInfo(String string){
		System.out.println(string);
		Document document = Jsoup.parse(string);
		Elements elements = document.getElementsByClass("item");
		String elementsText = elements.text();
		String elementsTexts[] = elementsText.split(">");
		for (int i = 0;i < elementsTexts.length;i++){
			System.out.println(elementsTexts[i] + "    " + i);
		}
		System.out.println(elementsTexts[1]);
	}

}
