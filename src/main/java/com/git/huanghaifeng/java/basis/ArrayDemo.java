package com.git.huanghaifeng.java.basis;

public class ArrayDemo {

	public static void main(String[] args) {
		double[] double_arr = { 0.0, 0.5, 0.1, 0.2 };
		
		double double_arr_sum = 0.0;
		for(int i=0; i<double_arr.length; i++){
			double_arr_sum += double_arr[i];
		}
		
		System.out.println(double_arr_sum);
		
		for(double element : double_arr){
			System.out.println(element);
		}
		
		java.util.Arrays.sort(double_arr);
		int index = java.util.Arrays.binarySearch(double_arr, 0.1);
		System.out.println(index);
		System.out.println(java.util.Arrays.toString(double_arr));
		
		
		String s[][] = new String[2][];
		s[0] = new String[2];
		s[1] = new String[3];
		s[0][0] = new String("Good");
		s[0][1] = new String("Luck");
		s[1][0] = new String("to");
		s[1][1] = new String("you");
		s[1][2] = new String("!");

		System.out.println(s[1][2]);
		
		
	}

}
