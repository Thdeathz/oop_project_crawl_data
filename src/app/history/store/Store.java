package app.history.store;

import java.io.FileNotFoundException;
import java.io.FileReader;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import app.crawler.DynastyCrawler;
import app.crawler.EventCrawler;
import app.crawler.FestivalCrawler;
import app.crawler.PersonCrawler;
import app.crawler.RelicCrawler;
import app.crawler.base.ICrawler;
import app.history.dynasty.Dynasty;
import app.history.event.Event;
import app.history.festival.Festival;
import app.history.person.Person;
import app.history.relic.Relic;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.io.File;
import java.io.IOException;


public class Store<T>{
  public static ObservableList<Person> persons = FXCollections.observableArrayList();
  public static ObservableList<Relic> relics = FXCollections.observableArrayList();
  public static ObservableList<Event> events = FXCollections.observableArrayList();
  public static ObservableList<Festival> festivals = FXCollections.observableArrayList();
  public static ObservableList<Dynasty> dynasties = FXCollections.observableArrayList();

  public static void init() throws IOException {

		File directoryDynasty = new File("src/app/history/store/json/dynasty.json");
		File directoryPerson = new File("src/app/history/store/json/person.json");
		File directoryRelic = new File("src/app/history/store/json/relic.json");
		File directoryFestival = new File("src/app/history/store/json/festival.json");
		File directoryEvent = new File("src/app/history/store/json/event.json");
		if (!directoryDynasty.exists()) {
			ICrawler dynastyCrawler = new DynastyCrawler();
			dynastyCrawler.crawl();
		}
		if (!directoryPerson.exists()) {
			ICrawler personCrawler = new PersonCrawler();
			personCrawler.crawl();
		}
		if (!directoryRelic.exists()) {
			ICrawler relicCrawler = new RelicCrawler();
			relicCrawler.crawl();
		}
		if (!directoryFestival.exists()) {
			ICrawler festivalCrawler = new FestivalCrawler();
			festivalCrawler.crawl();
		}
		if (!directoryEvent.exists()) {
			ICrawler eventCrawler = new EventCrawler();
			eventCrawler.crawl();
		}
		dynasties = readFromFile("src/app/history/store/json/dynasty.json", Dynasty[].class);
		persons = readFromFile("src/app/history/store/json/person.json", Person[].class);
		events = readFromFile("src/app/history/store/json/event.json", Event[].class);
		relics = readFromFile("src/app/history/store/json/relic.json", Relic[].class);
		festivals = readFromFile("src/app/history/store/json/festival.json", Festival[].class);
		for (int i = 0; i < dynasties.size(); i++) {
			dynasties.get(i).addKing();
		}
		for (int i = 0; i < persons.size(); i++) {
			persons.get(i).setDynasty();
		}
		for (int i = 0; i < events.size(); i++) {
			events.get(i).addPerson();
		}
		for (int i = 0; i < relics.size(); i++) {
			relics.get(i).addPerson();
		}
	}

  public static <T> ObservableList<T> readFromFile(String fileName, Class<T[]> clazz) {
    FileReader reader;
		try {
			reader = new FileReader(fileName);
			GsonBuilder gsonBuilder = new GsonBuilder();
      gsonBuilder.registerTypeAdapter(ObservableList.class, new ObservableListDeserializer<T>());
      Gson gson = gsonBuilder.create();
      T[] arr = gson.fromJson(reader, clazz);
      return FXCollections.observableArrayList(arr);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return null;
		}
  }

  public static void main(String[] args) throws IOException {
	  init();
		// dynasties.get(0).addKing();
  }
}
