package org.laoguo.c;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

import static org.laoguo.util.Util.P;;

public class Collection {
	
	public static void printList(){
		List<String> list1 = new ArrayList<String>();
		list1.add("A");
		list1.add("B");
		list1.add("C");
		list1.add("D");
		P(list1);
		
		List<String> list2 = new LinkedList<String>();
		list2.add("A");
		list2.add("B");
		list2.add("C");
		list2.add("D");
		P(list2);
		
		Set<String> s1 = new HashSet<String>();
		s1.add("A");
		s1.add("B");
		s1.add("C");
		s1.add("D");
		P(s1);
		
		Set<String> s2 = new TreeSet<String>();
		s2.add("A");
		s2.add("B");
		s2.add("C");
		s2.add("D");
		P(s2);
		
		Map<String, String> m1 = new HashMap<String, String>();
		m1.put("A", "A");
		m1.put("B", "B");
		m1.put("C", "C");
		m1.put("D", "D");
		P(m1);
		
		Map<String, String> m2 = new TreeMap<String, String>();
		m2.put("A", "A");
		m2.put("B", "B");
		m2.put("C", "C");
		m2.put("D", "D");
		P(m2);
	}

	public static void main(String[] args) {
		printList();
	}
}
