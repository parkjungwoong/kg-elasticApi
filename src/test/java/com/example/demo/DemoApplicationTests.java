package com.example.demo;

import org.json.JSONException;
import org.json.JSONObject;
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

	@Test
	public void 포스트_전송() throws JSONException {
		String url = null;
		JSONObject obj = new JSONObject();

		obj.put("123","222");

		Map result = elApi.callElasticApi("POST",url,null,obj.toString());
	}

	/**
	 * Index == DataBase
	 *
	 */
	@Test
	public void 인덱스_생성(){
		String url = "/user-demo"; //user-demo라는 인덱스(데이터베이스)를 생성

		Map result = elApi.callElasticApi("PUT",url,null,"");

		System.out.println(result);
	}

	/**
	 * 문서 입력 하는건데 같은 아이디로 보내면 값 업데이트 됨
	 *
	 */
	@Test
	public void 인덱스에_문서_입력() throws Exception{

		String indexName = "user-demo";

		String typeName  = "user";

		String id		 = "1";

		JSONObject document = new JSONObject();

		document.put("name","박정웅");
		document.put("age","29");
		document.put("phone","01084226318");

		String url = "/"+indexName+"/"+typeName+"/"+id; // 인덱스명/타입명/아이디/

		Map result = elApi.callElasticApi("PUT",url,null,document.toString());

		System.out.println(result);

	}

	@Test
	public void 문서_조회() {
		String indexName = "user-demo";

		String typeName  = "user";

		String id		 = "1";

		String url = "/"+indexName+"/"+typeName+"/"+id; // 인덱스명/타입명/아이디/

		Map result = elApi.callElasticApi("GET",url,null,null);

		System.out.println(result);
	}

	/**
	 *  "script" : "ctx._source.age += 5" 이런식으로도 사용 가능 ( ctx._source는 현재 수정될 문서 )
	 *
	 */
	@Test
	public void 문서_수정() throws Exception{
		String indexName = "user-demo";

		String typeName  = "user";

		String id		 = "1";

		String document = "{\n" +
				"  \"doc\": { \"name\": \"PJW\", \"age\": 29 }\n" +
				"}";

		String url = "/"+indexName+"/"+typeName+"/"+id+"/_update"; // 인덱스명/타입명/아이디_update


		Map result = elApi.callElasticApi("POST",url,null,document);

		System.out.println(result);
	}

	@Test
	public void 문서_삭제() {
		String indexName = "user-demo";

		String typeName  = "user";

		String id		 = "1";

		String url = "/"+indexName+"/"+typeName+"/"+id; // 인덱스명/타입명/아이디/

		Map result = elApi.callElasticApi("DELETE",url,null,null);

		System.out.println(result);
	}

	@Test
	public void 인덱스_조회() {
		String url = "/_cat/indices?v&pretty";

		Map result = elApi.callElasticApi("GET",url, null,null);

		System.out.println(result);
	}

}
