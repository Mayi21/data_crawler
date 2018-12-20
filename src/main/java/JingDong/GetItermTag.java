package JingDong;

import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
/**
 * @author may
 * @date 2018/12/19
 *
 */
public class GetItermTag {
	public static String getItermTag(Document document) throws Exception {
		Elements elements = document.getElementsByClass("item");
		String elementsText = elements.text();
		String[] elementsTexts = elementsText.split(">");
		return elementsTexts[1];
	}
}
