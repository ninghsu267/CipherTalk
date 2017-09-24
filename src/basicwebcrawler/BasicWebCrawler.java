package basicwebcrawler;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.HashSet;
import java.lang.IllegalArgumentException;


public class BasicWebCrawler {

	private HashSet<String> links;
	
	public BasicWebCrawler(){
		links=new  HashSet<String>();
	}
	
	public void getPageLinks(String URL){
		if(!links.contains(URL)){
			try{
				if(links.add(URL)){
					System.out.println(URL);
				}
				Document document=Jsoup.connect(URL).get();
				Elements linksOnPage=document.select("a[href]");
				
				for(Element page:linksOnPage){
					getPageLinks(page.attr("abs:href"));
				}
			}
			catch(IOException e){
				System.err.println("For '"+URL+"': "+e.getMessage());
			}
			catch(IllegalArgumentException ilge){
				System.err.println("For '"+URL+"': "+ilge.getMessage());
			}
		}
	}
	
	public static void main(String[] args) {
        //1. Pick a URL from the frontier
        new BasicWebCrawler().getPageLinks("http://www.sohu.com");
    }
}
