package com.two.pass.multiway.mergesort;
/*
1.	https://www.geeksforgeeks.org/java-program-for-quicksort/
2.	https://examples.javacodegeeks.com/core-java/nio/bytebuffer/write-append-to-file-with-byte-buffer/
3.	https://www.geeksforgeeks.org/bytebuffer-get-method-in-java-with-examples/
4.	http://rosettacode.org/wiki/Binary_search#Java
5.	https://en.wikipedia.org/wiki/Sorting_algorithm
6.	https://crunchify.com/increase-eclipse-memory-size-to-avoid-oom-on-startup/
7.	https://github.com/sagarvetal/ADB_Project_1_TPMMS
8.	https://howtodoinjava.com/java/io/how-to-check-if-file-exists-in-java/
9.	https://www.javadevjournal.com/java/java-copy-file-directory/
10.	https://mkyong.com/java/how-to-delete-directory-in-java/
11.	https://www.tutorialspoint.com/how-to-create-a-new-directory-by-using-file-object-in-java
12.	https://www.tutorialspoint.com/java/io/file_isfile.htm
13.	http://www.mathcs.emory.edu/~cheung/Courses/554/Syllabus/4-query-exec/2-pass=TPMMS.html
14.	http://www.mathcs.emory.edu/~cheung/Courses/554/Syllabus/4-query-exec/TPMMS=join2.html
*/
import java.util.ArrayList;

public class QuickSort {
	int partition(ArrayList<String> list, int low, int high) {
		String pivot = list.get(high);
		int i = (low - 1);
		for (int j = low; j < high; j++) {
			if (list.get(j).substring(0, 18).compareToIgnoreCase(pivot.substring(0, 18)) < 0) {
				i++;
				String temp = list.get(i);
				list.set(i, list.get(j));
				list.set(j, temp);
			}
		}
		String temp = list.get(i + 1);
		list.set(i + 1, list.get(high));
		list.set(high, temp);
		return i + 1;
	}

	void sort(ArrayList<String> list, int low, int high) {
		if (low < high) {
			int pi = partition(list, low, high);
			sort(list, low, pi - 1);
			sort(list, pi + 1, high);
		}
	}

	public ArrayList<String> executeQuickSort(ArrayList<String> list) {
		int n = list.size();
		sort(list, 0, n - 1);
		return list;
	}
}
