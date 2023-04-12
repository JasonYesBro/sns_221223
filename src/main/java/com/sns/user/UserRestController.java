package com.sns.user;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sns.user.bo.UserBO;
import com.sns.user.model.User;

@RequestMapping("/user")
@RestController
public class UserRestController {
	
	@Autowired
	UserBO userBO;

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
}
