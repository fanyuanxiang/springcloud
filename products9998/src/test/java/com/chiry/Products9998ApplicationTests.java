package com.chiry;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.ZonedDateTime;


class Products9998ApplicationTests {
//获取本地时间
	public static void main(String[] args) {
		ZonedDateTime zonedDateTime =ZonedDateTime.now();

		System.out.println(zonedDateTime);
	}

}
