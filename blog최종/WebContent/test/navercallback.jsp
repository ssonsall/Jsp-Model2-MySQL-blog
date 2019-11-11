<%@page import="blog.util.OAuthUser"%>
<%@page import="com.google.gson.Gson"%>
<%@ page import="java.net.URLEncoder" %>
<%@ page import="java.net.URL" %>
<%@ page import="java.net.HttpURLConnection" %>
<%@ page import="java.io.BufferedReader" %>
<%@ page import="java.io.InputStreamReader" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>네이버로그인</title>
  </head>
  <body>
  <%
    String clientId = "GOVXuGNH5NGtkEuWnhSB";//애플리케이션 클라이언트 아이디값";
    String clientSecret = "N09GFPMfCV";//애플리케이션 클라이언트 시크릿값";
    String code = request.getParameter("code"); //권한증서라고 생각하면 됨
    String state = request.getParameter("state");
    String redirectURI = URLEncoder.encode("YOUR_CALLBACK_URL", "UTF-8");
    String apiURL;
    apiURL = "https://nid.naver.com/oauth2.0/token?grant_type=authorization_code&";
    apiURL += "client_id=" + clientId;
    apiURL += "&client_secret=" + clientSecret;
    apiURL += "&redirect_uri=" + redirectURI;
    apiURL += "&code=" + code;
    apiURL += "&state=" + state;
    String access_token = "";
    String refresh_token = "";
    System.out.println("apiURL="+apiURL);
    try {
      URL url = new URL(apiURL);
      HttpURLConnection con = (HttpURLConnection)url.openConnection();
      con.setRequestMethod("GET");
      int responseCode = con.getResponseCode();
      BufferedReader br;
      System.out.print("responseCode="+responseCode);
      if(responseCode==200) { // 정상 호출
        br = new BufferedReader(new InputStreamReader(con.getInputStream()));
      } else {  // 에러 발생
        br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
      }
      String inputLine;
      StringBuffer res = new StringBuffer();
      while ((inputLine = br.readLine()) != null) {
        res.append(inputLine);
      }
      br.close();
      if(responseCode==200) {
        //out.println(res.toString());
        Gson gson = new Gson();
        OAuthUser oAuthUser = gson.fromJson(res.toString(), OAuthUser.class);
        
       //확인작업 제대로 Gson 파싱 되었는지
        access_token =  oAuthUser.getAccess_token();
        refresh_token = oAuthUser.getRefresh_token();
        String token_type  = oAuthUser.getToken_type();
        String expire_in = oAuthUser.getExpire_in();
        
        System.out.println("access_token >>" + access_token);
        System.out.println("refresh_token >>" + refresh_token);
        System.out.println("token_type >>" + token_type);
        System.out.println("expire_in >>" + expire_in);
        
        //DB에 oAuthUser Insert
        //했다고 가정하고
        
        
        //JWT 쓰면 세션 저장안해도 됨. JWT 쓰는게 좋음. 개념 찾아보기.
        session.setAttribute("access_token", access_token);
        
      }
    } catch (Exception e) {
      System.out.println(e);
    }
  %>
  
  ${sessionScope.access_token}
  </body>
</html>