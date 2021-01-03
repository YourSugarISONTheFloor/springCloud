package cn.fan;

import org.springframework.boot.test.context.SpringBootTest;

import java.time.ZonedDateTime;

@SpringBootTest
public class Test {

	@org.junit.Test
	public void TS() {
		ZonedDateTime zonedDateTime = ZonedDateTime.now();//默认时区
		System.out.println(zonedDateTime);
		//2021-01-03T15:22:57.900+08:00[Asia/Shanghai]
	}
}
