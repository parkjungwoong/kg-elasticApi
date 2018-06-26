package com.example.demo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoApplicationTests {

	@Autowired
	ElasticApi elApi;

	private final String EL_INDEX = "demo";

	private final String EL_TYPE = "1";

	@Test
	public void 엘라스틱_겟_전송() {
		String url = EL_INDEX  + "/_search?";

		Map result = elApi.callElasticApi("GET",url,null,null);
		System.out.println(result);

	}

}
