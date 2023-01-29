module OopProject {
	exports app.history.person;
	exports app.history.relic;
	exports app.crawler;
	exports app.history.event;
	exports app.history.dynasty;
	exports app.history.festival;
	exports app.crawler.base;
	exports app.screen.main;
	exports app.screen.controller;

	requires com.google.gson;
	requires java.desktop;
	requires javafx.base;
	requires org.jsoup;
    requires javafx.graphics;
	requires javafx.fxml;
	requires org.json;
	requires javafx.controls;
//	requires javafx.web;
    opens app.crawler to com.google.gson;
	opens app.history.person to com.google.gson;
	opens app.history.dynasty to com.google.gson;
	opens app.history.event to com.google.gson;
	opens app.history.festival to com.google.gson;
	opens app.history.relic to com.google.gson;
	opens app.screen.controller to javafx.fxml;
}