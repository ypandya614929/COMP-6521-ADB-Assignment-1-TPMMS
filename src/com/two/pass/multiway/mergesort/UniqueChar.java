package com.two.pass.multiway.mergesort;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class UniqueChar {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader(Constants.INPUT_PATH + Constants.INPUT_FILE1));
		HashMap<String,String> temp1 = new HashMap<>();
		String record = null;
		ArrayList<String> temp = new ArrayList<String>();
		int count1 = 0;
		int count2 = 0;
		int count3 = 0;
		int count4 = 0;
		while ((record = br.readLine()) != null) {
			if (record.trim().length() > 0) {
				
				if(temp1.containsKey(record.substring(0, 8))) {
					if(temp1.get(record.substring(0, 8).trim()).substring(8, 18).compareToIgnoreCase(record.substring(8, 18).trim()) < 0) {
						temp1.replace(record.substring(0, 8).trim(), record);
					}
				} else {
					temp1.put(record.substring(0, 8).trim(), record);
				}
				
				/*if (!temp.contains(record.substring(0, 8).trim())) {
					temp.add(record.substring(0, 8).trim());
					count1++;
				} else {
					count2++;
				}*/
			} /*else {
				count3++;
			}*/
			count4++;
		}
	/*	System.out.println(count1);
		System.out.println(count2);
		System.out.println(count3);
		System.out.println(count4);
		System.out.println(temp.size());*/
		Map<String,String> treeMap = new TreeMap<String,String>(temp1);
		System.out.println(temp1.size());
		BufferedWriter write = new BufferedWriter(new FileWriter("himen.txt"));
		for (Map.Entry<String,String> entry : treeMap.entrySet())  {
			write.write(entry.getKey()+entry.getValue());
			write.newLine();
		}
        write.close();
		br.close();
	}
}
