package com.sns.user;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.sns.user.bo.UserBO;
import com.sns.user.model.User;

@SpringBootTest
class UserBOTest {
	Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	UserBO userBO;
	
	//@Test
	void test() {
		fail("Not yet implemented");
	}
	
	@Transactional
	@Test
	void 회원가입하기() {
		logger.info("############# 회원가입 ##############");
		userBO.insertUser("test", "test12", "name123", "test@gmail.com");
	}
	
	@Test
	void 유저가져오기() {
		logger.info("############# 유저확인 ##############");
		User user = userBO.getUserByLoginId("aaaa");
		assertNotNull(user);
	}

}
