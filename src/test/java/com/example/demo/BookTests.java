package com.example.demo;


import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertEquals;

import java.util.Arrays;

import org.apache.http.HttpStatus;
import org.junit.Test;

import io.restassured.response.Response;


public class BookTests
{
	private static String URL = "http://localhost:9090/city";
	private static String ENDPOINT_GET_BOOK_BY_ISBN = "https://www.googleapis.com/books/v1/volumes";

	@Test
	public void testGetByISBN(){
		String isbn = "isbn:9781451648546";

		given().
		param("q", isbn).
		when().
		get(ENDPOINT_GET_BOOK_BY_ISBN)
		.then().
		statusCode(HttpStatus.SC_OK).
		body(	"totalItems", equalTo(1),
				"kind", equalTo("books#volumes"),
				"items.volumeInfo.title", containsInAnyOrder("Steve Jobs"),
				"items.volumeInfo.authors", containsInAnyOrder((Object)Arrays.asList("Walter Isaacson")),
				"items.volumeInfo.publisher", containsInAnyOrder("Simon and Schuster"),
				"items.volumeInfo.pageCount", containsInAnyOrder(630));
	}
	@Test
	public void test1(){
		Response resp = given().get(URL);
		assertEquals(HttpStatus.SC_OK, resp.getStatusCode());
	}
	@Test
	public void test2(){
		 given().get(URL+"/1").then().statusCode(HttpStatus.SC_OK).
		 body("id", equalTo(1),
				 "name",equalTo("Mumbai"),
				 "state",equalTo("Maharastra"));
	}
	
	@Test
	public void test3(){
		 given().delete(URL+"/5").then().statusCode(HttpStatus.SC_OK);
	}
}
