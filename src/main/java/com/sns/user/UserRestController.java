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

@RequestMapping("/user")
@RestController
public class UserRestController {
	
	@Autowired
	UserBO userBO;

	/**
	 * 아이디 중복확인 api
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
	 * 회원가입 api
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
		EncryptUtils.md5(password);
		
		// TODO int 로 if 분기
		// TODO insert DB
		int rowCnt = 0;
		rowCnt = userBO.insertUser(loginId, password, name, email); 
		
		if (rowCnt > 0) {
			result.put("code", 1);
			result.put("result", "성공");
		} else {
			result.put("code", 500);
			result.put("errorMessage", "회원가입에 실패하였습니다.");
		}
		
		// TODO return result
		return result;
	}
	
}









