package Test;

import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.regex.Pattern;
/*
* 从网上得到价格，单机测试用
*
* */
public class GetItermPrice {
	public static void main(String[] args) throws Exception{
		getItermPrice();
	}
	public static void getItermPrice() throws  Exception{
		String urlPath = "http://p.3.cn/prices/mgets?skuIds=J_100000001004";
		URL url = new URL(urlPath);
		URLConnection urlConnection = url.openConnection();
		InputStream inputStream = urlConnection.getInputStream();
		byte [] b = new byte[100];
		int len = inputStream.read(b);
		Pattern pattern = Pattern.compile("[[{}]\",]");
		String priceText[] = pattern.split(new String(b,0,len));
		for (int i = 0;i < priceText.length;i++){
			System.out.println(priceText[i] + "   " + i);
		}
		System.out.println(priceText[4]);
	}
}