package app.crawler;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.google.gson.Gson;

import app.crawler.base.BaseWebsiteCrawler;
import app.crawler.base.ICrawler;
import app.history.dynasty.Dynasty;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DynastyCrawler extends BaseWebsiteCrawler implements ICrawler {

	public DynastyCrawler()	{
		super("https://nguoikesu.com/dong-lich-su/hong-bang-va-van-lang", "src/app/history/store/json");
	}

	public void crawl()
	{
		DynastyCrawler dynastyCrawler = new DynastyCrawler();
		String url = dynastyCrawler.getUrl();
		try {
			Document doc = Jsoup.connect(url).get();
			Elements elements = doc.select("#Mod88 > div > div > ul > li > ul > li > a:contains(nhà), #Mod88 > div > div > ul > li > a:contains(nhà)");

			List<String> dynastyDataList = new ArrayList<>();
			List <String> dynastyLinkList = new ArrayList<> ();
			List <String> exitedTimeList = new ArrayList <String>();
	        List <String> dynastyNameList = new ArrayList <String> ();
	        List <String> capitalList = new ArrayList <String> ();
	        List <List <String>> kingNameList = new ArrayList <> ();
	        List<String> kingName = new ArrayList <> ();

	       // for (org.jsoup.nodes.Element element : elements3) {
	        for (int j = 0; j < elements.size() - 1; j++) {
	        	if (j == 2 || j == 3 || j == 4 || j == 13) continue;
	        	else
	        	{
	        		dynastyDataList.add(elements.get(j).text());
		            dynastyLinkList.add(elements.get(j).attr("href"));
	        	}

	        }

	        // hàm để tìm exitedTime .
	        for (int j = 0; j < dynastyDataList.size(); j++) {
	        	String name = dynastyDataList.get(j);
	        	dynastyNameList.add(name);
	        	String ggLink = "https://www.google.com/search?q=" + "thời gian tồn tại của " + name;
	        	Document ggInfo = Jsoup.connect(ggLink).get();
	        	String exitedTime = ggInfo.select("span.hgKElc > b").text();
	        	if (exitedTime.equals(""))
	        	{
	        		exitedTimeList.add("chưa có dữ liệu");
	        	}

	        	else
	        	{
	        		exitedTimeList.add(exitedTime);
	        	}

	        }


	        // tìm vua ứng với từng triều đại.
	        for (int j = 0; j < dynastyDataList.size(); j++) {
	        	Document docPage = Jsoup.connect("https://nguoikesu.com" + dynastyLinkList.get(j)).get();
	        	Elements h2Info = docPage.select("h2[itemprop=name]");
	        	for (int i = 0; i < h2Info.size(); i++)
	        	{
	        		String originalString = h2Info.get(i).text();
	        		if (originalString.contains("-")) {
	        		    String[] parts = originalString.split("-");
	        		    String part1 = parts[0].trim();
	        		    String part2 = parts[1].trim();
	        		    part2 = part2.replaceFirst("^\\s+", "");
	        		    kingName.add(part1);
	        		    kingName.add(part2);
	        		}
	        		else
	        		{
	        			kingName.add(originalString);
	        		}
	        		//System.out.println(h2Info.get(i).text());
	        	}
	        	kingNameList.add(kingName);
	        	kingName = new ArrayList <> ();
	        }


	        // tìm kinh thành ứng với từng triều đại.
	        for (int j = 0; j < dynastyNameList.size(); j++)
	        {
	        	try
	        	{
	        		String wikiLink = "https://vi.m.wikipedia.org/wiki/" + dynastyNameList.get(j);
		        	Document docPage = Jsoup.connect(wikiLink).get();
		        	try
		        	{
		        		String thuDo = docPage.select("th:contains(Thủ đô)").first().nextElementSibling().text();
		        		capitalList.add(thuDo);
		        	}
		        	catch (NullPointerException e)
		        	{
		        		capitalList.add("chưa có dữ liệu");
		        	}

	        	}
	        	catch (IOException e)
	        	{
	        		capitalList.add("chưa có dữ liệu");
	        	}
	        }


	        List<Dynasty> dataList = new ArrayList<>();
			for (int i = 0; i < dynastyNameList.size(); i++) {
				String dynasty = dynastyNameList.get(i);
				String capital = capitalList.get(i);
				List kingL = kingNameList.get(i);
				String time = exitedTimeList.get(i);
				Dynasty dynastiesData = new Dynasty();
				dynastiesData.setCapital(capital);
				dynastiesData.setExitedTime(time);
				dynastiesData.setName(dynasty);
				dynastiesData.setKingNameL(kingL);
				dataList.add(dynastiesData);
			}

			// // Chuyển đổi danh sách thành JSON
			Gson gson = new Gson();
			String json = gson.toJson(dataList);

			// Ghi JSON vào file
			try (FileWriter writer = new FileWriter(dynastyCrawler.getJsonStoreUrls() + "/dynasty.json")) {
				writer.write(json);
				System.out.println("Successfully wrote JSON to file.");
			} catch (IOException e) {
				e.printStackTrace();
			}

			}

		catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	public static void main(String[] args) {
		DynastyCrawler dynastyCrawler = new DynastyCrawler();
		dynastyCrawler.crawl();
	}
}
