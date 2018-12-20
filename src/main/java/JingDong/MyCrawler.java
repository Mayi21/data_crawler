package JingDong;

import Dao.SqlServer_Co;
import edu.uci.ics.crawler4j.crawler.WebCrawler;
import edu.uci.ics.crawler4j.crawler.Page;
import edu.uci.ics.crawler4j.parser.HtmlParseData;
import edu.uci.ics.crawler4j.url.WebURL;
import java.util.Set;
import java.util.regex.Pattern;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import java.sql.Connection;
import java.sql.PreparedStatement;

/**
* MyCrawler.class
* @author may
* @date 2018/12/16
*
*/
public class MyCrawler extends WebCrawler {
	//正则匹配文件
	private final static Pattern FILTERS= Pattern.compile(".*(\\.(css|js|gif|jpg|png|mp3|zip|gz))$");
	private final static Pattern pattern = Pattern.compile("[/.]");
	@Override
	public boolean shouldVisit(Page referringPage,WebURL url){
		String href = url.getURL().toLowerCase();
		return !FILTERS.matcher(href).matches()
				&& href.startsWith("https://item.jd.com");
	}
	@Override
	public void visit(Page page) {
		//实例化操作,获得数据库连接
		SqlServer_Co sqlServer_co = new SqlServer_Co();
		Connection conn = SqlServer_Co.AD(1);
		//获取url
		String url = page.getWebURL().getURL();
		System.out.println("url:"+url);
		//获取商品编号
		String[] b = pattern.split(url);
		//判断是否是html数据
		if(page.getParseData() instanceof HtmlParseData){
			//强制类型转换，获取html数据对象
			HtmlParseData htmlParseData= (HtmlParseData)page.getParseData();
			//获得页面html
			String html=htmlParseData.getHtml();
			Document document = Jsoup.parse(html);
			//打印商品编号
			System.out.println(b[5]);
			//获取tag
			String itermTag = null;
			try {
				itermTag = GetItermTag.getItermTag(document);
			}catch (Exception e){}
			System.out.println(itermTag.trim());
			//获得标题
			Elements elementsTitle = document.getElementsByClass("sku-name");
			String titl = elementsTitle.text();
			System.out.println(titl);
			//获取价格
			String price = null;
			try {
				price = GetPrice.getPrice(b[5]);
			}catch (Exception e){}
			System.out.println(price);
			//get itermImg
			/**
			 *先获取itermImg的值，判断在数据库中有没有记录
			 *
			 */
			String status = null;
			try {
				status = GetItermImg.getItermImgStatusFromMysql(document,b[5]);
			}catch (Exception e){
			}
			//获取页面输出链接
            try {
            	Set<WebURL> links=htmlParseData.getOutgoingUrls();
                String sql = "insert iterm(itermId,itermTag,itermName,itermPrice,itermImg,itermUrl) values (?,?,?,?,?,?)";
                PreparedStatement pst = (PreparedStatement) conn.prepareStatement(sql);
                pst.setString(1, b[5]);
				pst.setString(2, itermTag.trim());
                pst.setString(3, titl);
                pst.setString(4, price);
				pst.setString(5, status);
                pst.setString(6, url);

            	pst.executeUpdate();
            	conn.close();
            }
            catch (Exception e) {
                e.printStackTrace();
            }
		}
	}
}
