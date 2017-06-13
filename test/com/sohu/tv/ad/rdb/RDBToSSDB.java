package com.sohu.tv.ad.rdb;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.concurrent.atomic.AtomicInteger;

import com.sohu.tv.ad.rdb.ParseRDB;

public class RDBToSSDB {
	public static void main(String[] args) throws Exception {
		final AtomicInteger count = new AtomicInteger();

		String input = "E:\\downloads\\redis64-2.6.12.1\\dump.rdb";
		String output = "E:\\downloads\\redis64-2.6.12.1\\dump.txt";
		if (args.length == 2) {
			input = args[0];
			output = args[1];
		}

		BufferedWriter out = new BufferedWriter(new FileWriter(output), 1024 * 1024);
		ParseRDB rdb = new ParseRDB();
		rdb.init(new File(input));
		ParseRDB.Entry entry = rdb.next();

		while (entry != null) {

//			if (entry.type == ParseRDB.REDIS_STRING) {
				String item = entry.key + "\t" + entry.value + "\t" + entry.expire + "\r\n";
				out.write(item);
				count.incrementAndGet();
//			}else if(entry.type == ParseRDB.REDIS_HASH){
//				
//			}else if(entry.type == ParseRDB.REDIS_HASH){
//				
//			}
			entry = rdb.next();
			if (count.get() % 100000 == 0) {
				System.out.print(".");
			}
		}
		System.out.println("");
		rdb.close();
		out.flush();
		out.close();
		System.out.println("totol keys : " + count.get());
	}
}
