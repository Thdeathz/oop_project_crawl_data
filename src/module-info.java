/**
 *
 */
/**
 * @author huytran
 *
 */
module OopProject {
	requires org.jsoup;
	requires org.json;
	requires com.google.gson;
	requires java.desktop;
	opens app.crawler to com.google.gson;
	opens app.history.person to com.google.gson;
	opens app.history.dynasty to com.google.gson;
	opens app.history.event to com.google.gson;
}
