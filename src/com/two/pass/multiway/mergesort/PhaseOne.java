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
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class PhaseOne {
	QuickSort quickSort = new QuickSort();
	static int inputCount = 0;
	static int outputCount = 0;
	static int recordCount;
	long sortingTime = 0;
	public long getSortingTime() {
		return sortingTime;
	}

	public void setSortingTime(long sortingTime) {
		this.sortingTime = sortingTime;
	}

	int currentBlock = 0;
	BufferedReader br;

	public ArrayList<String> sortTuple(String tuple, String path) {
		if (tuple.equals("T2"))
			currentBlock++;
		long data_count = 0;
		ArrayList<String> temp = new ArrayList<>();
		try {
			br = new BufferedReader(new FileReader(path));
			boolean run = true;
			long blockSize = (Constants.TOTAL_MEMORY / 1000); // Using 10% memory for reading data from disk
			long begin = System.currentTimeMillis();
			while (run) {
				String record = null;
				ArrayList<String> subList = new ArrayList<>();

				while((record = br.readLine()) != null) {
					subList.add(record);
					recordCount++;
					++data_count;
					if (data_count == blockSize) {
						data_count = 0;
						++inputCount;
						++outputCount;
						break;
					}	
				}
				subList = quickSort.executeQuickSort(subList);

				String outputFile = Constants.BLOCK_PATH + "/Block-" + currentBlock;

				BufferedWriter write = new BufferedWriter(new FileWriter(outputFile));
				for (int i = 0; i < subList.size(); i++) {
					write.write(subList.get(i));
					write.newLine();
				}
				write.close();
				temp.add(outputFile);

				if (record == null)
					break;
				currentBlock++;
			}
			sortingTime += (System.currentTimeMillis() - begin);
			System.out.println("Time taken by Phase 1 for " + tuple + " : " + (System.currentTimeMillis() - begin)
					+ "ms ("  + (System.currentTimeMillis() - begin) / 1000.0 + "sec)");
		} catch (FileNotFoundException e) {
			System.out.println("The File doesn't Exist : " + path);
			System.exit(1);
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(1);
		}
		return temp;
	}

	public int getInputCount() {
		return inputCount;
	}

	public void setInputCount(int inputCount) {
		PhaseOne.inputCount = inputCount;
	}

	public int getRecordCount() {
		return recordCount;
	}

	public static void setRecordCount(int recordCount) {
		PhaseOne.recordCount = recordCount;
	}


	public int getOutputCount() {
		return outputCount;
	}

	public void setOutputCount(int outputCount) {
		PhaseOne.outputCount = outputCount;
	}

	public int getCurrentBlock() {
		return currentBlock;
	}

	public void setCurrentBlock(int currentBlock) {
		this.currentBlock = currentBlock;
	}
	
	public static int getTotalBlocks(int fileSize) {
		return (int) Math.ceil((double) fileSize / Constants.BLOCK_SIZE);
	}
}
