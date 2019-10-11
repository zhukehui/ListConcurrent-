package com.atguigu.list;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.CopyOnWriteArrayList;

public class ListConcurrence {

	public static void main(String[] args) {
		List<String> list = new CopyOnWriteArrayList<String>();
		/*
		 * list.add("a"); list.add("b"); list.add("c");
		 * 
		 * System.out.println(list);
		 */

		/*
		 * List list = Arrays.asList("a","b","c"); list.forEach(System.out::println);
		 */

		/*
		 * java.util.ConcurrentModificationException(并发修改异常) 
		 * 解决list多线程并发修改异常:
		 * 1.newVector 
		 * 2.Collections.synchronizedList(new ArrayList<>()) 
		 * 3.new  CopyOnWriteArrayList<String>()
		 * 
		 * Lambda表达式格式：拷贝小括号 固定小箭头 落地大括号
		 */

		for (int i = 0; i < 30; i++) {
			new Thread(() -> {
				list.add(UUID.randomUUID().toString().substring(0, 6));
				System.out.println(list);
			}, String.valueOf(i)).start();
		}

		/*
		 * for (int i = 0; i < 30; i++) { new Thread(new Runnable() {
		 * 
		 * @Override public void run() {
		 * list.add(UUID.randomUUID().toString().substring(0, 6));
		 * System.out.println(list); } }, String.valueOf(i)).start(); }
		 */

	}
}
