package com.atguigu.list;

import java.util.ArrayList;
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
		 * java.util.ConcurrentModificationException(并发修改异常) 解决list多线程并发修改异常:
		 * 1.newVector 2.Collections.synchronizedList(new ArrayList<>()) 3.new
		 * CopyOnWriteArrayList<String>()读写分离，写时复制
		 * 
		 * Lambda表达式格式：拷贝小括号 固定小箭头 落地大括号
		 */

		/*
		 * for (int i = 0; i < 30; i++) { new Thread(new Runnable() {
		 * 
		 * @Override public void run() {
		 * list.add(UUID.randomUUID().toString().substring(0, 6));
		 * System.out.println(list); } }, String.valueOf(i)).start(); }
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

/**
 * 写时复制
 CopyOnWrite容器即写时复制的容器。往一个容器添加元素的时候，不直接往当前容器Object[]添加，而是先将当前容器Object[]进行Copy，
 复制出一个新的容器Object[] newElements，然后新的容器Object[] newElements里添加元素，添加完元素之后，
 再将原容器的引用指向新的容器 setArray(newElements);。这样做的好处是可以对CopyOnWrite容器进行并发的读，
 而不需要加锁，因为当前容器不会添加任何元素。所以CopyOnWrite容器也是一种读写分离的思想，读和写不同的容器
 public boolean add(E e)
 {
 final ReentrantLock lock = this.lock;

 lock.lock();

 try{
 	Object[] ele ments = getArray();
 	int len = elements.length;
 	Object[] newElements = Arrays.copyOf(elements, len + 1);
 	newElements[len] = e;
 	setArray(newElements);
 	return true;
 }
 finally {
 	lock.unlock();
 	}
 }
 */
