package com.atguigu.list;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.CopyOnWriteArrayList;

public class ListConcurrence {

	public static void main(String[] args) {
		List<String> list =new CopyOnWriteArrayList<String>();
		/*
		 * list.add("a"); list.add("b"); list.add("c");
		 * 
		 * System.out.println(list);
		 */

		/*
		 * java.util.ConcurrentModificationException(并发修改异常) 
		 * 解决list多线程并发修改异常:
		 * 1.new Vector
		 * 2.Collections.synchronizedList(new ArrayList<>())
		 * 3.new CopyOnWriteArrayList<String>()
		 */

		for (int i = 0; i < 30; i++) {
			new Thread(new Runnable() {

				@Override
				public void run() {
					list.add(UUID.randomUUID().toString().substring(0, 6));
					System.out.println(list);
				}
			}, String.valueOf(i)).start();
		}

	}
}
