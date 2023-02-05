package app.crawler.person;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import javax.imageio.ImageIO;

import java.awt.image.BufferedImage;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import app.crawler.base.ICrawler;
import app.history.person.Person;

public class NguoikesuPersonCrawler implements ICrawler{

	private String getData(Document doc, String query) {
		try {
			return doc.select(query).first().nextElementSibling().text();
		} catch (Exception e) {
			return "Không rõ";
		}
	}

	public void saveImg(String imageUrl, String destinationFile) {
		try {
			URL url = new URL(imageUrl);
			InputStream is = url.openStream();

			BufferedImage img = ImageIO.read(is);
			if (img.getWidth() < 150 || img.getHeight() < 150) {
				System.out.println("Image too small, skipping...");
				return;
			}

			is.close();
			File outputfile = new File(destinationFile);
			ImageIO.write(img, "png", outputfile);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void crawl() throws IOException {
		for (int i = 0; i <= 1455; i += 5) {
			Document doc = Jsoup.connect("https://nguoikesu.com/nhan-vat?start=" + i).get();

			Elements persons = doc.select(
					"#content > div.com-content-category-blog.blog > div.com-content-category-blog__items.blog-items > div");

			for (int k = 0; k < persons.size(); k++) {
				Element detail = persons.get(k).select("div > div > h2 > a[href]").first();
				doc = Jsoup.connect(detail.absUrl("href")).get();

				String name = detail.text();
				if (name.toLowerCase().contains("nhà")) continue;
				System.out.println(name);

				String desc = persons.get(k).select("div > p").text();
				String dob = getData(doc, "th:contains(Sinh), td:contains(Sinh)");
				String dod = getData(doc, "th:contains(Mất), td:contains(Mất)");
				String father = getData(doc, "th:contains(Thân phụ), td:contains(Thân phụ)");
				String reign = getData(doc, "th:containsOwn(Trị vì), th:containsOwn(Tại vị), td:containsOwn(Trị vì), td:containsOwn(Tại vị)");
				String givenName = getData(doc, "th:contains(Húy), td:contains(Húy)");
				String dynasty = getData(doc, "th:contains(Triều đại), td:contains(Triều đại)");
				System.out.println(dynasty);

				Person person = new Person(name, givenName, father, reign, dob, dod, desc, dynasty);
				PersonCrawler.list.add(person);
				System.out.println(person.getId() + " - " + person.getName());

				try {
					saveImg("https://nguoikesu.com/" + persons.get(k).select("img").first().attr("data-src"),
									"src/app/data/img/person/" + person.getId() + ".png");
				} catch (Exception e) {}
			}
		}
	}

}
