package JingDong;
import java.util.HashSet;
import org.apache.http.message.BasicHeader;
import edu.uci.ics.crawler4j.crawler.CrawlConfig;
import edu.uci.ics.crawler4j.crawler.CrawlController;
import edu.uci.ics.crawler4j.fetcher.PageFetcher;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtConfig;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtServer;
public class Controller {
	public static void main(String[] args) throws Exception{
		/*TODO 定义本地位置*/
		String crawStorageFoler = "d:/crawler";
		/*TODO 定义爬虫的数量*/
		int numberOfCrawlers = 7;
		CrawlConfig config = new CrawlConfig();
		config.setCrawlStorageFolder(crawStorageFoler);
		HashSet<BasicHeader> collections  = new HashSet<BasicHeader>();
		/*TODO 定义header，不是很懂这些东西怎么用，所以自己修改下*/
		collections.add(new BasicHeader("User-Agent","Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) " +
				"Chrome/62.0.3192.0 Safari/537.36"));
		collections.add(new BasicHeader("Accept","text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8"));
		collections.add(new BasicHeader("Accept-Encoding", "gzip,deflate,sdch"));
		collections.add(new BasicHeader("Accept-Language", "zh-CN,zh;q=0.8,en;q=0.6"));
		collections.add(new BasicHeader("Content-Type","application/x-www-form-urlencoded;charset=UTF-8"));
		collections.add(new BasicHeader("Connection", "keep-alive"));
		collections.add(new BasicHeader("Cookie", "bid=fp-BlwmyeTY; __yadk_uid=dLpMqMsIGD1N38NzhbcG3E6QA33NQ9bE; ps=y; _pk_ref.100" +
				"001.8cb4=%5B%22%22%2C%22%22%2C1506515077%2C%22https%3A%2F%2Faccounts.douban.com%2Flogin%3Falias%3D793890838%2540qq.com%26redir%3Dh" +
				"ttps%253A%252F%252Fwww.douban.com%26source%3DNone%26error%3D1013%22%5D; ll=\"108296\"; ue=\"793890838@qq.com\"; __utmt=1; _ga=GA1.2.3" +
				"88925103.1505404043; _gid=GA1.2.1409223546.1506515083; dbcl2=\"161927939:ZDwWtUnYaH4\"; ck=rMaO; ap=1; push_noty_num=0; push_doumail_n" +
				"um=0; __utma=30149280.388925103.1505404043.1506510959.1506515077.8; __utmb=30149280.22.9.1506516374528; __utmc=30149280; __utmz=3014928" +
				"0.1506510959.7.5.utmcsr=accounts.douban.com|utmccn=(referral)|utmcmd=referral|utmcct=/login; __utmv=30149280.16192; _pk_id.100001.8cb4=" +
				"1df4f52fdf296b72.1505404042.8.1506516380.1506512502.; _pk_ses.100001.8cb4=*"));
		config.setDefaultHeaders(collections);
		/*TODO 下面是一些配置，以自己需求修改*/
		//设置请求频率
		config.setPolitenessDelay(5000);
		//设置网页请求深度
		config.setMaxDepthOfCrawling(-1);
		//设置爬取网页多少次
		config.setMaxPagesToFetch(10000000);
		//设置是否爬取二进制网页
		config.setIncludeBinaryContentInCrawling(false);
		PageFetcher pageFetcher = new PageFetcher(config);
		RobotstxtConfig robotstxtConfig = new RobotstxtConfig();
		RobotstxtServer robotstxtServer = new RobotstxtServer(robotstxtConfig,pageFetcher);
		CrawlController crawlController = new CrawlController(config,pageFetcher,robotstxtServer);
		/*TODO 添加种子*/
		crawlController.addSeed("https://item.jd.com/7641991.html");
		crawlController.addSeed("https://item.jd.com/2883267.html");
		crawlController.addSeed("https://item.jd.com/6384654.html");
		crawlController.addSeed("https://item.jd.com/3459483.html");
		crawlController.addSeed("https://item.jd.com/100001367868.html");
		crawlController.addSeed("https://item.jd.com/3826890.html");
		crawlController.addSeed("https://item.jd.com/7360341.html");
		crawlController.addSeed("https://item.jd.com/1430834.html");
		crawlController.addSeed("https://item.jd.com/4393925.html");
		crawlController.addSeed("https://item.jd.com/7081550.html");
		crawlController.addSeed("https://item.jd.com/5189241.html");
		crawlController.addSeed("http://item.jd.com/100000538392.html");
		crawlController.addSeed("https://item.jd.com/1780297.html");
		crawlController.addSeed("http://item.jd.com/5658235.html");
		crawlController.addSeed("https://item.jd.com/3342608.html");
		crawlController.addSeed("https://item.jd.com/100000769432.html");
		crawlController.addSeed("https://item.jd.com/8249576.html");
		crawlController.addSeed("https://item.jd.com/8249576.html");
		crawlController.addSeed("https://item.jd.com/7108208.html");
		crawlController.addSeed("https://item.jd.com/7191176.html");
		crawlController.addSeed("https://item.jd.com/12377271882.html");

		crawlController.start(MyCrawler.class,numberOfCrawlers);
	}
}
