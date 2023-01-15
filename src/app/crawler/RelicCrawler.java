package app.crawler;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.reflect.TypeToken;

import app.history.relic.Relic;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class RelicCrawler {

	String url;
	String jsonStoreUrls;
	String imgStoreUrls;

	public RelicCrawler() {
		this.url = "https://nguoikesu.com";
		this.jsonStoreUrls = "src/app/history/store/json";
		this.imgStoreUrls = "src/app/history/store/img/relic";
	}

	public String getImgStoreUrls() {
		return imgStoreUrls;
	}

	public String getJsonStoreUrls() {
		return jsonStoreUrls;
	}

	public String getUrl() {
		return url;
	}

	// dùng title để get addr
	public String getAddress(String title) {
		Document doc2;
		// Nếu mà kết nối không thành công sẽ trả về Đang cập nhật
		try {
			doc2 = Jsoup.connect("https://www.google.com/search?q=" + title).get();
		} catch (IOException e) {
			System.out.print(e);
			return "Đang cập nhật ...";
		}
		// Lấy thẻ
		Elements addressTag = doc2.select(
				"#kp-wp-tab-overview > div.TzHB6b.cLjAic.LMRCfc > div > div > div > div > div > div.QsDR1c > div > div > div");

		// Nếu mà dữ liệu giống sẽ bị error lúc này ta trả về đang cập nhật
		try {
			// Địa chỉ: QFFF+R7J, Tân Trào, Sơn Dương, Tuyên Quang
			// dữ liệu thu được như thế này ta cần substring để bỏ chữ Địa Chỉ
			return addressTag.get(0).text().substring(9);
		} catch (Exception e) {

			return "Đang cập nhật";
		}
	}

	/**
	 * Hàm dùng để lấy một danh sách các người liên quan đến di tích lịch sử
	 * 
	 * @return đanh sách tên các người liên quan
	 */
	public List<String> getPersonName(String link) {
		Document doc2;
		List<String> nameList = new ArrayList<String>();
		String name;
		// Nếu mà kết nối không thành công sẽ trả về rỗng
		try {
			doc2 = Jsoup.connect(getUrl() + link).get();
			// Lấy thẻ
			Elements nameTag = doc2.select("#list-ref-characters > ul > li");
			for (int i = 0; i < nameTag.size(); i++) {
				// lấy thẻ chứa name
				// bỏ trường hợp nó chỉ là kí tự
				name = nameTag.get(i).text();
				if (name.length() > 1) {
					// thêm tên vào
					nameList.add(name);
				}
			}
		} catch (IOException e) {

			return nameList;
		}
		return nameList;
	}

	/**
	 * Hàm này sẽ lấy title và ghi hoa chữ cái đầu
	 * 
	 */
	public String getTitle(String title) {
		if (title.isEmpty() || title == null)
			return title;
		return title.substring(0, 1).toUpperCase() + title.substring(1);
	}

	/**
	 * @return 1 nếu mà save thành công 0 thì ngc lại
	 */
	public int saveImg(String filename, String link) {
		try {
			// Create a URL object for the image
			URL imgUrl = new URL(getUrl() + link);

			// Read the image from the URL
			BufferedImage image = ImageIO.read(imgUrl);
			File directory = new File(getImgStoreUrls());
			if (!directory.exists()) {
				directory.mkdir();
				System.out.println("Directory created successfully");
			}

			// Create a new file for the new image
			File outputFile = new File(getImgStoreUrls() + "/" + filename);

			// Save the image to the new file
			ImageIO.write(image, "png", outputFile);

		} catch (IOException e) {

			return 0;
		}
		return 1;
	}

	public void writeJsonFile(List<Relic> relicList) {

		try {
			File directory = new File(getJsonStoreUrls());
			if (!directory.exists()) {
				directory.mkdir();
				System.out.println("Directory created successfully");
			}

			Gson gson = new Gson();

			FileWriter writer = new FileWriter(getJsonStoreUrls() + "/relic.json");
			gson.toJson(relicList, writer);
			writer.close();

		} catch (IOException e) {
			System.out.println(e);
			// TODO Auto-generated catch block

		}
	}

	public void crawl() {
		Document doc1;
		List<Relic> relicList = new ArrayList<Relic>();
		try {
			// Lặp các trang

			for (int k = 0; k <= 20; k += 10) {
				doc1 = Jsoup.connect(url + "/di-tich-lich-su?start=" + k).get();
				// Lấy các thẻ liên quan đến name
				Elements titles = doc1
						.select("#content > div.com-tags-tag.tag-category > div.com-tags__items > ul > li > h3 > a");

				// lấy các thể liên quan đến content
				Elements contents = doc1
						.select("#content > div.com-tags-tag.tag-category > div.com-tags__items > ul > li > span");

				// lấy các thể liên quan đến content

				Elements imgs = doc1
						.select("#content > div.com-tags-tag.tag-category > div.com-tags__items > ul > li > a > img");

				// Nếu lấy dữ liệu thành công thì mới crawl

				// lặp mỗi title để lấy ra vì 1 title chỉ có 1 content
				for (int i = 0; i < titles.size(); i++) {
					// get title,content,...
					String title = getTitle(titles.get(i).text());
					String href = titles.get(i).attr("href");
					String content = contents.get(i).text();
					// get address
					String address = getAddress(title);
					// get person name
					List<String> nameList = getPersonName(href);
					// get img list relate to this historical site
					String imgUrl = "";

					// Nếu save thành công
					if (saveImg((Relic.cnt + 1) + ".png", imgs.get(i).attr("src")) == 1) {
						// add root to imgUrl
						imgUrl = (Relic.cnt + 1) + ".png";
					}
					Relic relic = new Relic(title, content, address, nameList, imgUrl);
					// nếu relic không tồn tại trong đó
					if (!relicList.contains(relic))
						relicList.add(relic);

				}
			}

			// Ghi vào file json
			writeJsonFile(relicList);

		} catch (IOException e) {
			System.out.print(e);
		}
	}

	private static class ObservableListDeserializer implements JsonDeserializer<ObservableList> {
		@Override
		public ObservableList deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
				throws JsonParseException {
			JsonArray jsonArray = json.getAsJsonArray();
			ArrayList<Relic> list = context.deserialize(jsonArray, new TypeToken<ArrayList<Relic>>() {
			}.getType());
			return FXCollections.observableArrayList(list);
		}
	}

	public static void main(String args[]) {
		RelicCrawler relicCrawler = new RelicCrawler();
		File directory = new File(relicCrawler.getJsonStoreUrls() + "/" + "relic.json");
		if (!directory.exists()) {
			relicCrawler.crawl();
		}
		// Read JSON file
		FileReader reader;
		try {
			reader = new FileReader(relicCrawler.getJsonStoreUrls() + "/" + "relic.json");
			GsonBuilder gsonBuilder = new GsonBuilder();
			// đổi kiểu trả về thành observableArrayList
			gsonBuilder.registerTypeAdapter(ObservableList.class, new ObservableListDeserializer());
			Gson gson = gsonBuilder.create();
			List<Relic> relicList = gson.fromJson(reader, new TypeToken<List<Relic>>() {
			}.getType());
			System.out.println(relicList.get(0).getTitle());
			System.out.println(relicList.get(0).getNameList());
			System.out.println(relicList.get(0).getImgUrl());
			System.out.println(relicList.get(0).getAddress());
			System.out.println(relicList.get(0).getContent());
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
