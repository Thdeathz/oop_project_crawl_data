/**
 * 
 */
/**
 * @author huytran
 *
 */
module OopProject {
	requires org.jsoup;
	requires com.google.gson;
	requires javafx.base;
	requires java.desktop;
    opens app.crawler.base to com.google.gson;
    opens app.history.festival to com.google.gson;
    opens app.history.event to com.google.gson;
    opens app.history.person to com.google.gson;
    opens app.history.dynasty to com.google.gson;
    opens app.history.relic to com.google.gson;
}