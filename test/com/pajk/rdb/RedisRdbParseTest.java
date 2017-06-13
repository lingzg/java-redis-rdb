package com.pajk.rdb;

import java.io.File;

import com.pajk.rdb.CallBackHandler;
import com.pajk.rdb.RdbParser;

public class RedisRdbParseTest {
	public static void main(String[] args) {
		String path = RedisRdbParseTest.class.getResource("").getPath();
		String input = path + "dump.rdb";
		RdbParser rdb = new RdbParser();
		rdb.rdbParse(new File(input), new CallBackHandler() {
			public void printlnHandler(long dbid, String type, String key,
					Object val, long expiretime) {
				String item = dbid + "||" + type + "||" + "key=" + key
						+ "||" + "value=" + val + "||" + expiretime;
				System.out.println(item);
			}
		});
		
	}
}
