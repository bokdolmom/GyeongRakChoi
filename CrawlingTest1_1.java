package crawlingTest;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLConnection;

// 네이버 뉴스여행/레저 뉴스 제목 크롤링
// https://news.naver.com/breakingnews/section/103/237

public class CrawlingTest1_1 {

	public static void main(String[] args) {

		URI uri = null;
		URL url = null;
		URLConnection conn = null;
		BufferedReader br = null;
		
		try {
			
			uri = new URI("https://news.naver.com/breakingnews/section/103/237");
			url = uri.toURL();
			conn = url.openConnection();
			br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			
			String line = "";
			String startStr = "<strong class=\"sa_text_strong\">";
			String endStr = "</strong>";
			
			while ((line=br.readLine()) != null) {
				String subject = "";
				if (line.trim().startsWith(startStr)) {
					int startIdx = line.indexOf(startStr) + startStr.length();
					int endIdx = line.indexOf(endStr, startIdx);
					subject = line.substring(startIdx, endIdx);
					System.out.println(subject);
				}
			}
			
		} catch (URISyntaxException urise) {
			urise.printStackTrace();
		} catch (MalformedURLException mue) {
			mue.printStackTrace();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		} finally {
			try {
				if (br!=null) br.close();
			} catch (IOException ioe) {
				ioe.printStackTrace();
			}
		}
		
		
	} // main
	
} // class
