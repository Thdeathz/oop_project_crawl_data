package app.crawler;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import app.crawler.base.ICrawler;
import app.history.person.Person;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class PersonCrawler implements ICrawler {
    private List<Person> list = new ArrayList<>();

    String url;
    String jsonStoreUrls;
    String imgStoreUrls;

    public PersonCrawler() {
        this.url = "https://nguoikesu.com/nhan-vat";
        this.jsonStoreUrls = "src/app/history/store/json/person.json";
        this.imgStoreUrls = "src/app/history/store/img/person/";
    }

    public String getUrl() {
        return url;
    }

    public String getJsonStoreUrls() {
        return jsonStoreUrls;
    }

    public String getImgStoreUrls() {
        return imgStoreUrls;
    }

    public void crawl() throws IOException {
        for (int i = 0; i <= 1455; i += 5) {
            Document doc = Jsoup.connect(getUrl() + "?start=" + i).get();
            Elements kings = doc.select(
                    "#content > div.com-content-category-blog.blog > div.com-content-category-blog__items.blog-items > div");
            String dob;
            String dod;
            String father;
            String givenName;
            String dynasty;
            String reign;
            // Elements desc = kings.select("div > h2 > a");
            for (int k = 0; k < kings.size(); k++) {
                // for (int k = 0; k < 1; k++) {
                // BufferedImage image = ImageIO.read(new
                // URL(kings.get(k).select("img[src]").first().absUrl("src")));
                Element detail = kings.get(k).select("div > div > h2 > a[href]").first();
                System.out.println(detail.text());
                String name = detail.text();
                String desc = kings.get(k).select("div > p").text();
                doc = Jsoup.connect(detail.absUrl("href")).get();
                Document docWiki;
                try {
                    docWiki = Jsoup.connect("https://vi.wikipedia.org/wiki/" + name).get();
                } catch (Exception e) {
                    docWiki = null;
                }
                try {
                    dob = docWiki.select("th:contains(Sinh)").first().nextElementSibling().text();
                } catch (Exception e) {
                    try {
                        dob = doc.select("th:contains(Sinh)").first().nextElementSibling().text();
                    } catch (Exception f) {
                        dob = "Không rõ";
                    }
                }
                try {
                    dod = docWiki.select("th:contains(Mất)").first().nextElementSibling().text();
                } catch (Exception e) {
                    try {
                        dod = doc.select("th:contains(Mất)").first().nextElementSibling().text();
                    } catch (Exception f) {
                        dod = "Không rõ";
                    }
                }
                try {
                    father = doc.select("th:contains(Thân phụ)").first().nextElementSibling().text();
                } catch (Exception e) {
                    Document docGG = Jsoup.connect("https://www.google.com/search?q=cha của " + name).get();
                    father = docGG.select("a.FLP8od").text();
                    if (father.equals("")) {
                        father = docGG.select("span.hgKElc > b").text();
                        if (father.equals("")) {
                            father = "Không rõ";
                        }
                    }
                }
                try {
                    reign = docWiki.select("th:containsOwn(Trị vì), th:containsOwn(Tại vị)").first().nextElementSibling().text();
                } catch (Exception e) {
                    reign = "Không rõ";
                }
                try {
                    givenName = doc.select("th:contains(húy)").first().nextElementSibling().text();
                } catch (Exception e) {
                    givenName = "Không rõ";
                }
                try {
                    dynasty = doc.select("th:contains(Triều đại)").first().nextElementSibling().text();
                } catch (Exception e) {
                    Document docGG = Jsoup.connect("https://www.google.com/search?q=triều đại của " + name).get();
                    dynasty = docGG.select("a.FLP8od").text();
                    if (dynasty.equals("")) {
                        dynasty = docGG.select("span.hgKElc > b").text();
                        if (dynasty.equals("")) {
                            dynasty = "Không rõ";
                        }
                    }
                }
                Person person = new Person(name, givenName, father, reign, dob, dod, desc, dynasty);
                try {
                    saveImg("https://nguoikesu.com/" + kings.get(k).select("img").first().attr("data-src"),
                            getImgStoreUrls() + person.getId() + ".png");
                    ;
                } catch (Exception e) {
                    System.out.println("Img not found");
                }
                list.add(person);
            }
        }
    }

    public void store() {
        Gson gson = new Gson();
        try (FileWriter file = new FileWriter(getJsonStoreUrls())) {
            file.write(gson.toJson(list));
            file.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // get data from local json file
    public void readFromJson() {
        try {
            FileReader reader = new FileReader(getJsonStoreUrls());
            Gson gson = new Gson();
            list = gson.fromJson(reader, new TypeToken<List<Person>>() {
            }.getType());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void saveImg(String imageUrl, String destinationFile) {
        try {
            URL url = new URL(imageUrl);
            InputStream is = url.openStream();
            OutputStream os = new FileOutputStream(destinationFile);

            byte[] b = new byte[2048];
            int length;

            while ((length = is.read(b)) != -1) {
                os.write(b, 0, length);
            }

            is.close();
            os.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
