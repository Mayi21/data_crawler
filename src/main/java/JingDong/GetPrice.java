package JingDong;

import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.regex.Pattern;

public class GetPrice {
	private final static Pattern pattern = Pattern.compile("[[{}]\",]");
	public static String getPrice(String itermId) throws Exception{
		//直接连接，获取连接的内容
		String urlPath = "http://p.3.cn/prices/mgets?skuIds=J_" + itermId;
		URL url = new URL(urlPath);
		URLConnection urlConnection = url.openConnection();
		InputStream inputStream = urlConnection.getInputStream();
		byte[] b = new byte[100];
		int len = inputStream.read(b);
		String priceText[] = pattern.split(new String(b,0,len));
		return priceText[4];
	}
}
