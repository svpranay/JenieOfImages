package com.example.spvenka.jenieofimages.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
/**
 * {
 GsearchResultClass: "GimageSearch",
 width: "1400",
 height: "1119",
 imageId: "ANd9GcRI5noPVLEOj0Yh-SltHXpyIAGvDGFtAwnel8lGVLDw1QfUIsB6V-2LUyhS",
 tbWidth: "150",
 tbHeight: "120",
 unescapedUrl: "http://weknowyourdreams.com/images/monkey/monkey-05.jpg",
 url: "http://weknowyourdreams.com/images/monkey/monkey-05.jpg",
 visibleUrl: "weknowyourdreams.com",
 title: "Interpretation of a dream in which you saw «<b>Monkey</b>»",
 titleNoFormatting: "Interpretation of a dream in which you saw «Monkey»",
 originalContextUrl: "http://weknowyourdreams.com/monkey.html",
 content: "Dreams Interpretation - <b>Monkey</b>",
 contentNoFormatting: "Dreams Interpretation - Monkey",
 tbUrl: "http://t2.gstatic.com/images?q=tbn:ANd9GcRI5noPVLEOj0Yh-SltHXpyIAGvDGFtAwnel8lGVLDw1QfUIsB6V-2LUyhS"
 }
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class GImage {

    public String title;
    public String imageUrl;
    public String width;
    public String height;


    public String thumbnailUrl;
    public String tbWidth;
    public String tbHeight;

    @JsonCreator
    public GImage(@JsonProperty("title") String title,
                  @JsonProperty("url") String imageUrl,
                  @JsonProperty("width") String width,
                  @JsonProperty("height") String height,
                  @JsonProperty("tbUrl") String tbImageUrl,
                  @JsonProperty("tbWidth") String tbWidth,
                  @JsonProperty("tbHeight") String tbHeight) {
        this.title = title;
        this.imageUrl = imageUrl;
        this.width = width;
        this.height = height;
        this.thumbnailUrl = tbImageUrl;
        this.tbHeight = tbHeight;
        this.tbWidth = tbWidth;
    }


}
