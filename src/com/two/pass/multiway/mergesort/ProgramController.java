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
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class ProgramController {
	static String fileName1 = Constants.INPUT_PATH + Constants.INPUT_FILE1;
	static String fileName2 = Constants.INPUT_PATH + Constants.INPUT_FILE2;

	public static void main(String[] args) throws InterruptedException {
		System.out
		.println("****************************Cleaning Directory*********************************************");
		buildBlockDirectory();
		buildOutputDirectory();
		System.out.println("Diretory Cleaned");
		System.out.println("****************************TPMMS Console*********************************************");
		System.gc();
		System.out.println("Memory Size :  " + getMemorySize());
		System.out.println("Tuple Size : " + Constants.TUPLE_SIZE);
		PhaseOne phaseOne = new PhaseOne();
		System.out.println("****************************Phase 1 for T1*********************************************");
		List<String> T1 = phaseOne.sortTuple("T1", fileName1);
		int blockCount1 = 0;
		int recordCount1 = phaseOne.getRecordCount();
		if( recordCount1 % Constants.MAX_RECORD  == 0) {
			blockCount1 = recordCount1 / Constants.MAX_RECORD;
		} else {
			blockCount1 = (recordCount1 / Constants.MAX_RECORD) +1 ;

		}
		System.out.println("Records in T1 : " + recordCount1);
		System.out.println("Block for T1 : " + blockCount1);
		System.out.println("****************************Phase 1 for T2*********************************************");
		List<String> T2 = phaseOne.sortTuple("T2", fileName2);
		int blockCount2 = 0;
		int recordCount2 = phaseOne.getRecordCount() - recordCount1;
		if( recordCount2 % Constants.MAX_RECORD  == 0) {
			blockCount2 = recordCount2 / Constants.MAX_RECORD;
		} else {
			blockCount2 = (recordCount2 / Constants.MAX_RECORD) +1 ;

		}		
		System.out.println("Records in T2 : " + recordCount2);
		System.out.println("Block for T2 : " + blockCount2);
		System.out.println("****************************Phase 1 Overview*********************************************");
		System.out.println("Total number of records " + phaseOne.getRecordCount());
		System.out.println(
				"Total number of Block " + (phaseOne.getRecordCount() * Constants.TUPLE_SIZE) / Constants.BLOCK_SIZE);
		int phaseOneDiskIO = 2 * (blockCount1 + blockCount2);
		System.out.println("Sorted Disk IO " + phaseOneDiskIO);
		System.gc();
		System.out.println("****************************Phase 2*********************************************");
		PhaseTwo phaseTwo = new PhaseTwo(T1, T2);
		phaseTwo.performMergeSort();
		System.out.println("Phase 2 Time : " + phaseTwo.getMergeTtime() + "ms" + " ("
				+ phaseTwo.getMergeTtime() / 1000.0 + " sec)");
		System.out.println("Merge Phase IO of I/O :" + (phaseTwo.getReadCount() + phaseTwo.getWriteCount()));
		System.out.println("****************************Output Overview*********************************************");
		System.out.println(
				"Total time Phase 1 & Phase 2 : " + (phaseTwo.getMergeTtime() + phaseOne.getSortingTime()) + "ms");
		System.out.println("Total time Phase 1 & Phase 2 : "
				+ ((phaseTwo.getMergeTtime() + phaseOne.getSortingTime()) / 1000.0) + " sec");
		System.out.println(
				"Total Number of I/O : " + (phaseOneDiskIO + phaseTwo.getReadCount() + phaseTwo.getWriteCount()));

		buildOutputDirectory(phaseTwo.getOutputPath());
	}

	private static void buildOutputDirectory(String outputPath) {
		try {
			File outputDir = new File(Constants.OUTPUT_PATH);
			if (!outputDir.exists()) {
				System.out.println("Output Directory Created : " + outputDir.mkdir());
			}
			BufferedReader br = new BufferedReader(new FileReader(outputPath));
			FileWriter writer = new FileWriter(Constants.OUTPUT_PATH + "output.txt", true);
			String temp;

			while ((temp = br.readLine()) != null) {
				writer.write(temp + "\n");
				writer.flush();
			}
			br.close();
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void buildOutputDirectory() {
		File outputDir = new File(Constants.OUTPUT_PATH);
		if (!outputDir.exists()) {
			System.out.println("Output Directory Created : " + outputDir.mkdir());
		} else if (outputDir.isFile()) {
		} else {
			String fileList[] = outputDir.list();
			for (int i = 0; i < fileList.length; i++) {
				if (fileList[i].trim().length() >= 1) {
					File currentBlockFiles = new File(outputDir.getPath(), fileList[i]);
					currentBlockFiles.delete();
				}
			}
			System.out.println("Output Directory Deleted :- " + outputDir.delete());
			System.out.println("Output Directory Created :- " + outputDir.mkdir());
		}
	}

	public static void buildBlockDirectory() {
		File deleteBlocks = new File(Constants.BLOCK_PATH);
		if (!deleteBlocks.exists()) {
			System.out.println("Block Directory Created : " + deleteBlocks.mkdir());
		} else if (deleteBlocks.isFile()) {
		} else {
			String fileList[] = deleteBlocks.list();
			for (int i = 0; i < fileList.length; i++) {
				if (fileList[i].trim().length() >= 1) {
					File currentBlockFiles = new File(deleteBlocks.getPath(), fileList[i]);
					currentBlockFiles.delete();
				}
			}
			System.out.println("Block Directory Deleted :- " + deleteBlocks.delete());
			System.out.println("Block Directory Created :- " + deleteBlocks.mkdir());
		}
	}

	private static int getMemorySize() {
		return (int) (Runtime.getRuntime().totalMemory() / (1024 * 1024));
	}
	
	public static int getTotalBlocks(final int fileSize, final int blockSize) {
		return (int) Math.ceil((double) fileSize / blockSize);
	}
}
