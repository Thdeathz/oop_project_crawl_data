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

import app.history.dynasty.Dynasty;

import java.io.IOException;
import java.io.StringReader;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class DynastyCrawler {
	String url;
	String jsonStoreUrls;
	
	public DynastyCrawler()
	{
		this.url = "https://vi.wikipedia.org/wiki/Vua_Vi%E1%BB%87t_Nam";
		this.jsonStoreUrls = "src/app/history/store/json";
	}
	
	public String getUrl() {
		return url;
	}
	
	public String getJsonStoreUrls() {
		return jsonStoreUrls;
	}
	
	public void crawl()
	{
		DynastyCrawler dynastyCrawler = new DynastyCrawler();
		String url = dynastyCrawler.getUrl();
		try 
		{
			Document doc = Jsoup.connect(url).get();
			
			Elements elements = doc.getElementsByTag("h3");
			Elements elements3 = doc.select("#mw-content-text > div.mw-parser-output > table > tbody > tr:nth-child(2) > td:nth-child(6)");
			Elements elements4 = doc.select("#mw-content-text > div.mw-parser-output > table > tbody > tr > td:nth-child(6)");	
			Elements elements5 = doc.select("#mw-content-text > div.mw-parser-output > table:nth-child(91) > tbody > tr > td:nth-child(4)");
			
			
			List <String> dynastyName = new ArrayList <String> ();
			List <String> ele3ToString = new ArrayList <String>(); // list các vua đầu tiên của từng triều đại.
			List <String> ele4ToString = new ArrayList <String> (); // list tất cả vua crawl được.
			List <String> capital = new ArrayList <String> ();
			
			List <ArrayList <String> > listKing = new ArrayList <> ();
			List <String> king = new ArrayList <> ();
			int start = 0;
			
			List <String> exitedTimeL = new ArrayList <String> ();
			
			// loại bỏ [] thừa của data. ex Bắc Ninh[gk] thì bỏ [gk].
			for (int j = 0; j < elements.size(); j++)
			{
				String result = elements.get(j).text();
				result = result.replaceAll("\\[.*?\\]", "");
				dynastyName.add(result);
			}
			for (int j = 0; j < elements3.size(); j++)
			{
				String result = elements3.get(j).text();
			    result = result.replaceAll("\\[.*?\\]", "");
			    ele3ToString.add(result);
			}
			
			for (int j = 0; j < elements4.size(); j++) {
			    String result = elements4.get(j).text();
			    result = result.replaceAll("\\[.*?\\]", "");
			    ele4ToString.add(result);
			}
			
			// tìm danh sách các vua ứng với từng triều đại.
			for (int j = 0; j < ele4ToString.size(); j++)
			{
				String ele4 = ele4ToString.get(j);
				if (ele4.equals("không rõ")) continue; // một số chỗ crawl ra cái này thì bỏ qua.
				int index = ele3ToString.indexOf(ele4);
				if (index > -1) // nếu vua hiện tại trùng với vua đầu tiên của triều đại thì tức là đã sang tới triều đại mới.
				{
					if (start == 0)
					{
						start += 1;
					}
					else
					{
						listKing.add((ArrayList<String>) king);
						king = new ArrayList<>();
					}
				}
				king.add(ele4);
			}
			listKing.add((ArrayList<String>) king); // bổ sung nốt thông tin về triều đại cuối.
			
			// tách xâu crawl được gồm tên + thời gian thành 2 xâu tên và thời gian riêng biệt.
			for (int i = 0; i < dynastyName.size(); i++) {
			    String s = dynastyName.get(i);
			    Pattern p = Pattern.compile("\\(([^)]+)\\)");
			    Matcher m = p.matcher(s);
			    //System.out.print(i + " ");
			    if (m.find()) {
			    	exitedTimeL.add(m.group(1));
			        //System.out.println(m.group(1));
			    } else {
			    	exitedTimeL.add("chưa có dữ liệu");
			        //System.out.println("Không tách được");
			    }
			    s = s.replaceAll("\\s*\\(.*?\\)\\s*", ""); //replaceAll(pattern, replacement)
			    s = s.replace("hoặc", "");
			    s = s.replace("và", " và");
			    dynastyName.set(i, s);
			}
			
			
			// crawl về kinh thành.
			for (int j = 1; j < elements5.size(); j++)
			{
				if (j == 5) continue; // trường hợp đặc biệt khi bảng triều đại gộp luôn "Nhà Tiền Lý và Triệu Việt Vương"
				// còn bảng kinh thành không gộp, may là nó trùng kinh thành.
				String s = elements5.get(j).text();
				s = s.replaceAll("\\[.*?\\]", "");
				capital.add(s);
			}
			// trường hợp đặc biệt bảng kinh thành match cột thiếu với bảng triều đại - vua.
			capital.add(17, "chưa có dữ liệu"); 
			capital.add(20, "chưa có dữ liệu");

			List<Dynasty> dataList = new ArrayList<>();
	        for (int i = 0; i < dynastyName.size(); i++) {
	            String dynasty = dynastyName.get(i);
	            String place = capital.get(i);
	            List kingL = listKing.get(i);
	            String time = exitedTimeL.get(i);
	            Dynasty dynastiesData = new Dynasty();
	            dynastiesData.setCapital(place);
	            dynastiesData.setExitedTime(time);
	            dynastiesData.setKingNameL(kingL);
	            dynastiesData.setName(dynasty);
	            dataList.add(dynastiesData);
	        }
	        
	        // Chuyển đổi danh sách thành JSON
	        Gson gson = new Gson();
	        String json = gson.toJson(dataList);
	        
	        // Ghi JSON vào file
	        try (FileWriter writer = new FileWriter(dynastyCrawler.getJsonStoreUrls() + "dynastiesData.json")) {
	            writer.write(json);
	            System.out.println("Successfully wrote JSON to file.");
	        } catch (IOException e) {
	            e.printStackTrace();
	        }

		}
		
		
		catch (IOException e)
		{
			
		}
	
	}
	public static void main(String [] args) 
	{
		DynastyCrawler dynastyCrawler = new DynastyCrawler();
		dynastyCrawler.crawl();
	}
}
