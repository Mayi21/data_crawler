package JingDong;

import org.jsoup.nodes.Document;
import java.io.*;
import java.net.URL;
import java.net.URLConnection;
/**
 * @author may
 * @date 2018/12/17
 * @deprecated connect mysql to download img
 */
public class GetItermImg {
	public static String getItermImgStatusFromMysql(Document document,String itermId) throws Exception {
		String imgFolderPath = getFilePath(itermId);
		File imgFolder = new File(imgFolderPath);
		/*
		 * 如果本地有这张图片，就把数据库中itermImg的状态更新为1
		 * 否则更新为0
		 * */
		if (imgFolder.exists()) {
			return "1";
		} else {
			getItermImgFromNet(document,itermId);
			return "1";
		}
	}
	public static void getItermImgFromNet (Document document,String itermId) throws Exception {
		String id = document.getElementById("spec-img").toString();
		String[] imgArray = id.split("\"");
		String urlImg = "http:" + imgArray[5];
		String itermImgFilePath = getFilePath(itermId);
		File imgPathFolder = new File(itermImgFilePath);
		if (!imgPathFolder.exists()) {
			imgPathFolder.mkdir();
		}
		File file = new File(itermImgFilePath, itermId + ".jpg");
		//根据url建立输出流，将图片输出到本地
		URL url = new URL(urlImg);
		URLConnection urlConnection = url.openConnection();
		InputStream inputStream1 = urlConnection.getInputStream();
		BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream1);
		BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(file));
		int a = -1;
		while ((a = bufferedInputStream.read()) != -1) {
			bufferedOutputStream.write((byte) a);
		}
		bufferedOutputStream.flush();
		bufferedInputStream.close();
		bufferedOutputStream.close();
	}
	public static String getFilePath(String itermId){
		/*TODO 以自己情况，进行修改*/
		return "D:\\Picture\\jingdong\\" + itermId + "\\";
	}
}