package com.sns.user;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sns.common.EncryptUtils;
import com.sns.user.bo.UserBO;
import com.sns.user.model.User;

import jakarta.servlet.http.HttpSession;

@RequestMapping("/user")
@RestController
public class UserRestController {
	
	@Autowired
	UserBO userBO;

	/**
	 * 아이디 중복확인 API
	 * @param loginId
	 * @return
	 */
	@RequestMapping("/is_duplicated_id")
	public Map<String, Object> isDuplicatedId(String loginId) {
		Map<String, Object> result = new HashMap<>();
		
		User user = null;
		user = userBO.getUserByLoginId(loginId);
		
		if(user != null) {
			result.put("code", 1);
			result.put("result", true);
			
		} else {
			result.put("code", 500);
			result.put("result", false);
		}
		
		return result;
	}
	
	/**
	 * 회원가입 API
	 * @param loginId
	 * @param password
	 * @param name
	 * @param email
	 * @return
	 */
	@PostMapping("/sign_up")
	public Map<String, Object> signUp(
			@RequestParam("loginId") String loginId
			, @RequestParam("password") String password
			, @RequestParam("name") String name
			, @RequestParam("email") String email) {
		
		// TODO Map
		Map<String, Object> result = new HashMap<>();
		
		// TODO 비밀번호 해싱
		String hashedPassword = EncryptUtils.md5(password);
		
		// TODO int 로 if 분기
		// TODO insert DB
		int rowCnt = 0;
		rowCnt = userBO.insertUser(loginId, hashedPassword, name, email); 
		
		if (rowCnt > 0) {
			result.put("code", 1);
			result.put("result", "성공");
		} else {
			result.put("code", 500);
			result.put("errorMessage", "회원가입에 실패하였습니다.");
		}
		
		return result;
	}
	
	/**
	 * 로그인 API
	 * @param loginId
	 * @param password
	 * @param session
	 * @return
	 */
	@PostMapping("/sign_in")
	public Map<String, Object> signIn(
			@RequestParam("loginId") String loginId
			, @RequestParam("password") String password
			, HttpSession session) {
		Map<String, Object> result = new HashMap<>();
		
		// 비밀번호 해싱
		String hashedPassword = EncryptUtils.md5(password);
		
		// DB select
		User user = null;
		user = userBO.getUserByLoginIdAndPassword(loginId, hashedPassword);
		
		// 분기문 처리
		if (user != null) {
			result.put("code", 1);
			result.put("result", "로그인에 성공하였습니다.");
			
			// 세션 저장
			session.setAttribute("userId", user.getId());
			session.setAttribute("userLoginId", user.getLoginId());
			session.setAttribute("userName", user.getName());
		} else {
			result.put("code", 500);
			result.put("errorMessage", "로그인에 실패하였습니다.");
		}
		
		return result;
	}
	
}









