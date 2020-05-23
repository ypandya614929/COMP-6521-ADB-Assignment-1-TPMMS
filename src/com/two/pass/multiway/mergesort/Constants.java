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
public class Constants {

	public static final int TUPLE_SIZE = 100;
	public static final int BLOCK_SIZE = (4096 / TUPLE_SIZE) * TUPLE_SIZE;
	public static final int TOTAL_MEMORY = ((int) (Runtime.getRuntime().totalMemory()));
	public static final int MEMORY_SIZE = (TOTAL_MEMORY / BLOCK_SIZE) * BLOCK_SIZE;
	public static final int MAX_RECORD = 40;
	public static final String INPUT_PATH = "./inputfiles/";
	public static final String BLOCK_PATH = "./blocks/";
	public static final String OUTPUT_PATH = "./outputfiles/";
	public static final String INPUT_FILE1 = "sample3.txt";
	public static final String INPUT_FILE2 = "sample4.txt";
	public static final String T1 = "T1";
	public static final String T2 = "T2";

}
