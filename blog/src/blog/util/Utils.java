package blog.util;

import java.io.IOException;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.jupiter.api.Test;

import blog.dto.View;
import blog.model.Board;

public class Utils {

	//미리보기 내용 세팅
	public static void setPreviewContent(List<View> viewList) {

		for (View v : viewList) {
			Document doc = Jsoup.parse(v.getContent()); // String content가 html 문서로 바뀜

			// document가 들고있는 모든 태그를 담을수 있는 배열
			Elements ets = doc.select("img");

			// Element 는 한개 담을수 있는것

			// 제거하면 doc에서 날라감.
			if (ets != null) {
				for (Element e : ets) {
					e.remove();
				}
			}

			v.setContent(doc.toString());
		}
	}
	
	//미리보기 이미지 세팅 (유튜브 썸네일 미리보기까지 포함)
	public static void setPreviewImg(List<View> viewList) {
	      for (View view : viewList) {
	         Document doc = Jsoup.parse(view.getContent());
	         Elements etyoutube = doc.select("a");
	         Element et = doc.selectFirst("img");
	         String thumbnail = "";
	         if (etyoutube != null) {
	            for (Element element : etyoutube) {	               
	               String href = element.attr("href");
	               if (href.contains("https://www.youtube.com/watch") && !element.text().equals("")) {
	                  String video[] = href.split("=");
	                  String v = video[1];
	                  thumbnail = "http://i.ytimg.com/vi/" + v + "/0.jpg";
	                  view.setPreviewImg(thumbnail);
	                  break;
	               }
	            }
	         }
	         if (thumbnail.equals("")) {
	            if (et != null) {
	               String previewImg = et.attr("src");// 이미지 소스 찾기
	               view.setPreviewImg(previewImg);
	            } else {
	            	view.setPreviewImg("/blog/img/home-blog/blog-1.jpg");
	            }
	         }
	      }
	   }
	
	public static void setHotBoardPreviewImg(List<Board> hotBoardList) {
	      for (Board board : hotBoardList) {
	         Document doc = Jsoup.parse(board.getContent());
	         Elements etyoutube = doc.select("a");
	         Element et = doc.selectFirst("img");
	         String thumbnail = "";
	         if (etyoutube != null) {
	            for (Element element : etyoutube) {
	               String href = element.attr("href");	               
	               if (href.contains("https://www.youtube.com/watch") && !element.text().equals("")) {
	                  String video[] = href.split("=");
	                  String v = video[1];
	                  thumbnail = "http://i.ytimg.com/vi/" + v + "/0.jpg";
	                  board.setPreviewImg(thumbnail);
	                  break;
	               }
	            }
	         }
	         if (thumbnail.equals("")) {
	            if (et != null) {
	               String previewImg = et.attr("src");// 이미지 소스 찾기
	               board.setPreviewImg(previewImg);
	            } else {
	            	board.setPreviewImg("/blog/img/home-blog/blog-1.jpg");
	            }
	         }
	      }
	   }
	
	
	
	//Youtube 미리보기 세팅
	public static void setPreviewYoutube(View view) {
		String content = view.getContent();
		Document doc = Jsoup.parse(content);
		Elements ets = doc.select("a");

		if(ets != null) {
			for (Element et : ets) {
				String href = et.attr("href");
				String value = et.text();
				if(href.contains("www.youtube.com/watch") && !value.equals("")) {
					String video[] = href.split("=");
					String v = video[1];
					String iframe = "<iframe src=\"https://www.youtube.com/embed/"+ v +"\" width=\"600px\" height=\"350px\" allowfullscreen/>";
					et.after(iframe);					
				}
			}
			view.setContent(doc.toString());
		}
	}
	
	//검색할때 content안에 tag들이 검색되는 것을 막기 위해
	public static String getPureContent(String content) {
	
		String searchContent = "";
		try {			
			Document doc;
			doc = Jsoup.parse(content);			
		    Elements spanTags = doc.getAllElements();
		    for (Element spanTag : spanTags) {
		        searchContent += spanTag.ownText();
		    }
		} catch (Exception e) {
			e.printStackTrace();
		}
		return searchContent;
	}
	
	
	
	
	
	
	
	
	

}
