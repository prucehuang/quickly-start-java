package com.git.huanghaifeng.java.inveno;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class FindActivateEventTimeGap {

	// private static final String TAG = "###";
	private static int MIN_GAP = 10 * 60;

	private static Map<String, Set<Long>> activate_user_result_data_map = new HashMap<String, Set<Long>>();
	private static Map<String, Set<Long>> user_request_data_map = new HashMap<String, Set<Long>>();
	private static Map<String, Set<Long>> user_click_data_map = new HashMap<String, Set<Long>>();

	public static void usage() {
		System.out.println(FindActivateEventTimeGap.class.getName()
				+ " <request_file_path>  <click_file_path>  <result_file_path>  <time_gap>");
	}

	/**
	 * 
	 * @param args
	 *            /tmp/request-reformat-parse.list
	 *            /tmp/click-reformat-parse.list /tmp/result.list 600
	 */
	public static void main(String[] args) {
		if (args.length != 4) {
			usage();
			return;
		}

		FindActivateEventTimeGap find_activate = new FindActivateEventTimeGap();
		MIN_GAP = Integer.valueOf(args[3]);

		find_activate.readFile(args[0], user_request_data_map);
		find_activate.readFile(args[1], user_click_data_map);

		// find_activate.mapToString(user_request_data_map);
		// System.out.println("------------------");
		// find_activate.mapToString(user_click_data_map);

		find_activate.analyzeActivateUser();
		System.out.println("------------------");
		find_activate.mapToString(activate_user_result_data_map);

		find_activate.writeFile(args[2]);
	}

	private void analyzeActivateUser() {
		for (String request_uid : user_request_data_map.keySet()) {
			if (user_click_data_map.containsKey(request_uid)) {
				Set<Long> request_value_Set = user_request_data_map.get(request_uid);
				Set<Long> click_value_Set = user_click_data_map.get(request_uid);

				for (long request_value_tmp : request_value_Set) {
					long gap = 0;
					for (long click_value_tmp : click_value_Set) {
						gap = request_value_tmp - click_value_tmp;

						if (gap >= 0 && gap <= MIN_GAP) {
							break;
						}
					}
					if (gap >= 0 && gap <= MIN_GAP) {
						continue;
					}
					this.insertDataToMap(request_uid, request_value_tmp, activate_user_result_data_map);
				}

			} else {
				// no click
				activate_user_result_data_map.put(request_uid, user_request_data_map.get(request_uid));
			}
		}

	}

	private void readFile(String file_path, Map<String, Set<Long>> user_data_map) {
		File file = new File(file_path);
		FileInputStream file_input = null;
		InputStreamReader reader = null;
		BufferedReader buffer_reader = null;
		try {
			file_input = new FileInputStream(file);
			reader = new InputStreamReader(file_input, "UTF-8");
			buffer_reader = new BufferedReader(reader);

			if (buffer_reader.ready()) {
				String tmp = "";
				while (true) {
					tmp = buffer_reader.readLine();
					if (tmp == null || tmp.contentEquals("")) {
						break;
					} else {
						String[] tmp_arr = tmp.split("\t");
						if (tmp_arr.length >= 3) {
							this.insertDataToMap(tmp_arr[0], Long.parseLong(tmp_arr[2]), user_data_map);
						}
					}
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {

			try {
				reader.close();
				file_input.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	private void insertDataToMap(String key, long value, Map<String, Set<Long>> data_map) {
		Set<Long> tmp_Set = data_map.get(key);

		if (tmp_Set == null) {
			tmp_Set = new HashSet<Long>();
		}

		tmp_Set.add(value);
		data_map.put(key, tmp_Set);
	}

	private void mapToString(Map<String, Set<Long>> data_map) {
		for (String key : data_map.keySet()) {
			System.out.println(key + " : " + Arrays.toString(data_map.get(key).toArray()));
		}
	}

	private void writeFile(String file_path) {
		File file = new File(file_path);

		FileOutputStream file_output = null;
		OutputStreamWriter writer = null;
		try {
			file_output = new FileOutputStream(file);
			writer = new OutputStreamWriter(file_output, "UTF-8");

			long user_count = 0;
			long user_request_count = 0;
			for (String key : activate_user_result_data_map.keySet()) {
				Set<Long> value_set = activate_user_result_data_map.get(key);
				int value_size = value_set.size();

				if (value_size < 2) {
					user_request_count++;
				} else {
					Long[] value_long_arr = value_set.toArray(new Long[0]);
					Arrays.sort(value_long_arr);

					long tmp = value_long_arr[0];
					for (int j = 1; j < value_size; j++) {
						long gap = value_long_arr[j] - tmp;
						if (gap > MIN_GAP) {
							user_request_count++;
						}
						tmp = value_long_arr[j];
					}
					user_request_count++;
				}

				user_count++;
				writer.write(key + "\t" + value_set.size() + "\n");
			}

			System.out.println("user count is " + user_count);
			System.out.println("user request count is " + user_request_count);
			System.out.println("user average count is " + (double)user_request_count / user_count);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				writer.close();
				file_output.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}
}
