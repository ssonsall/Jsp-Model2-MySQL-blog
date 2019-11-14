package blog.action.user;

import java.io.File;
import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import blog.action.Action;
import blog.dao.UserDao;
import blog.model.User;
import blog.util.SHA256;
import blog.util.Script;

/*
 * email 인증 (동일한 사람 회원가입 방지) : email 인증은 구현되어있지만 email 중복방지 안해놔서 하나의 이메일로 여러번 계정상승가능
 * email 인증안되면 글을 못쓰게 하는 방식으로만 구현해놨음
 * OAuth 로그인 (ex) 네이버로그인, 페이스북로그인 등...)
 */

public class UserJoinAction implements Action {
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 목적 : form tag에 있는 name값을 받아서 DB에 insert하고 login page로 이동 (index타고 list page로
		// 이동)
		ServletContext context = request.getServletContext();
		String userProfilePath = context.getRealPath("userprofile");


		// html에서 enctype="multipart/form-data"로 form데이터를 넘기게 되면 request.getParameter로
		// 값을 가져올수 없음. 출력해보면 null이 나옴.
		// 그래서 MultipartRequest라는 객체로 받아냄. 이걸 쓰려면 cos.jar 이라는 라이브러리를 추가해야함.
		// new MultipartRequest(request,"파일저장경로",맥시멈 파일사이즈(1024 = 1kb, 1024*10000 =
		// 10MB, 인코딩타입, 같은 파일명이 있으면 자동으로 파일명을 변경해서 저장함)
		MultipartRequest mr = new MultipartRequest(request, userProfilePath, 1024 * 10000, "utf-8",
				new DefaultFileRenamePolicy());
		System.out.println("origin >> " + mr.getOriginalFileName(userProfilePath));
		// null 값 처리해야함, 유효성(Validation 검사)
		// enctype="multipart/form-data"로 폼데이터를 넘기면 우리가 하던방식대로 request.getParameter로 값을
		// 땡겨올수 없다고 했음.
		// 그래서 MultipartRequest라는 객체로 받아낸다고 했고, 사용방식은 똑같음 그냥 getParameter.
		String username = mr.getParameter("username");
		String rawPassword = mr.getParameter("password");
		String email = mr.getParameter("email");
		String address = mr.getParameter("address");
		String userProfile = "";
		// mr.getFileNames()를 하면 Enum방식으로 리턴해줌. 즉 여러파일을 동시에 올리면 해당 파일명들을 Enum으로 리턴해준다는
		// 말임
		// 근데 우리는 파일 하나만 올릴거니깐 mr.getFileNames().nextElement() 해주면 유저가 올린 파일명이
		// 나올거임(String으로 다운캐스팅 필요함)
		String fileName = (String) mr.getFileNames().nextElement();
		// System.out.println("조인 파일이름 " + fileName); 무조건 userProfile <==폼에서 설정한 name값으로
		// 넘어옴.
		// if (!fileName.equals("userProfile")) {

		// getFilesystemName()에 인자로 위에서 구한 유저가 올린 파일명을 집어넣으면 걔가 실제로 저장된 이름으로 리턴함
		// 동일 파일명이 없으면 문제없지만 동일 파일명이 존재하면 자동으로 리네임하도록 해두었기때문에 실제 저장된 파일이름을 구해서 DB에 저장해야함
		String fileSystemName = mr.getFilesystemName(fileName);
		if(fileSystemName != null) {
		System.out.println("조인 파일 이름 >>" + fileSystemName + "<<");
		


		File file = new File(userProfilePath + "\\" + fileSystemName);
		String fileNameTemp[] = fileSystemName.split("\\.");

		// 파일 이름 유저아이디로 변경하여 저장할 것임.
		File fileNew = new File(userProfilePath + "\\" + username + "." + fileNameTemp[1]);

		// 업로드된 파일 이름 변경
		if (file.exists()) {
			file.renameTo(fileNew);
		}

		// nav.jsp에서 img src에 서버에 저장된 파일 경로를 그대로 적어줄것이기에 DB에는 파일이름만 저장함

		userProfile = fileNew.getName();
		System.out.println("fileNew name >>> "+fileNew.getName());
		file.delete();
		 }else {
		userProfile = "userprofile\\defaultprofile.jpg";
		 }

		String password = SHA256.getEncrypt(rawPassword, "cos");

		User user = new User();
		user.setUsername(username);
		user.setPassword(password);
		user.setEmail(email);
		user.setUserProfile(userProfile);
		user.setAddress(address);

		UserDao dao = new UserDao();
		int result = dao.save(user);

		if (result == 1) {
			// 메일 인증 페이지로 이동
			System.out.println("Join 성공");
			user = dao.findByUsername(username);
			request.setAttribute("user", user); // select해서 가져왔으니깐 id가 있다.
			RequestDispatcher dis = request.getRequestDispatcher("/mail/mailAuthentication.jsp");
			dis.forward(request, response);
			// response.sendRedirect("/blog/mail/mailAuthentication.jsp");
		} else {
			System.out.println("Join 실패");
			Script.alert(response, "Join Fail");
			Script.back(response);
		}
	}
}
