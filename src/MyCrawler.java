import edu.uci.ics.crawler4j.crawler.Page;
import edu.uci.ics.crawler4j.crawler.WebCrawler;
import edu.uci.ics.crawler4j.parser.HtmlParseData;
import edu.uci.ics.crawler4j.url.WebURL;

import java.util.Set;
import java.util.regex.Pattern;

public class MyCrawler extends WebCrawler
{
	private final static Pattern FILTERS = Pattern.compile(".*(\\.(css|xml|js|gif|jpg" + "|png|mp3|mp3|zip|gz))$");

	/**
	 * This method receives two parameters. The first parameter is the page in
	 * which we have discovered this new url and the second parameter is the new
	 * url. You should implement this function to specify whether the given url
	 * should be crawled or not (based on your crawling logic). * In this
	 * example, we are instructing the crawler to ignore urls that have css, js,
	 * git, ... extensions and to only accept urls that start * with
	 * "http://www.viterbi.usc.edu/". In this case, we didn't need the *
	 * referringPage parameter to make the decision.
	 */
	// @overrides
	public boolean shouldVisit(WebURL url)
	{
		String href = url.getURL().toLowerCase();

		// return !FILTERS.matcher(href).matches()
		// &&
		return href.startsWith("http://www.keck.usc.edu/");
	}

	/**
	 * This function is called when a page is fetched and ready * to be
	 * processed by your program.
	 */
	@Override
	public void visit(Page page)
	{
		String url = page.getWebURL().getURL();
		System.out.println("URL: " + url);
		//HtmlParseData htmlParseData = (HtmlParseData) page.getParseData();
		//String text = htmlParseData.getHtml();
		//System.out.println("-->"+text);
		if (page.getParseData() instanceof HtmlParseData)
		{
			HtmlParseData htmlParseData = (HtmlParseData) page.getParseData();
			String text = htmlParseData.getText();
			String html = htmlParseData.getHtml();
			Set<WebURL> links = (Set<WebURL>) htmlParseData.getOutgoingUrls();
			System.out.println("Text length: " + text.length());
			System.out.println("Html length: " + html.length());
			System.out.println("Number of outgoing links: " + links.size());
		}
	}

}
