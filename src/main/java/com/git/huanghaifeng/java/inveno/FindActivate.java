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

public class FindActivate {

	private static final String TAG = "_";
	private static final String REQUEST = "r";
	private static final String PUSH_CLICK = "p";
	private static final String NOT_PUSH_CLICK = "n";

	private static int MIN_GAP = 10 * 60;

	private static Map<String, Set<String>> activate_user_result_data_map = new HashMap<String, Set<String>>();
	private static Map<String, Set<String>> user_data_map = new HashMap<String, Set<String>>();
	private static long request_session_count = 0;
	private static long push_click_session_count = 0;
	private static long not_push_click_session_count = 0;
	private static Set<String> request_session_user_count = new HashSet<String>();
	private static Set<String> push_click_session_user_count = new HashSet<String>();
	private static Set<String> not_push_click_session_user_count = new HashSet<String>();
	
	public static void usage() {
		System.out.println(FindActivate.class.getName()
				+ " <request_file_path> <push_click_file_path> <not_push_click_file_path> <time_gap> <result_file_path>");
	}

	/**
	 * 
	 * @param args
	 *            /tmp/request-reformat-parse.list
	 *            /tmp/click-reformat-parse.list /tmp/result.list 600
	 */
	public static void main(String[] args) {
		if (args.length != 5) {
			usage();
			return;
		}

		FindActivate find_activate = new FindActivate();

		find_activate.readFile(args[0], REQUEST);
		find_activate.readFile(args[1], PUSH_CLICK);
		find_activate.readFile(args[2], NOT_PUSH_CLICK);
		MIN_GAP = Integer.valueOf(args[3]);

		find_activate.analyzeActivateUser();

		find_activate.mapToString(user_data_map);
		System.out.println("------------------");
		find_activate.mapToString(activate_user_result_data_map);

		find_activate.writeFile(args[4]);
	}

	private void analyzeActivateUser() {
		for (String uid : user_data_map.keySet()) {
			Set<String> value_set = user_data_map.get(uid);
			int value_size = value_set.size();
			if (value_size < 2) {
				activate_user_result_data_map.put(uid, value_set);
			} else {
				String[] value_arr = value_set.toArray(new String[0]);
				Arrays.sort(value_arr);
				String start_session = value_arr[0];
				String start_tmp = start_session;

				for (int i = 0; i < value_size; i++) {
					String current_tmp = value_arr[i];
					if (Long.valueOf(current_tmp.split(TAG)[0]) - Long.valueOf(start_tmp.split(TAG)[0]) > MIN_GAP) {
						// gap is big enough, start a new session
						this.insertDataToMap(uid, start_session, activate_user_result_data_map);
						start_session = current_tmp;
					}
					start_tmp = current_tmp;
				}
				this.insertDataToMap(uid, start_session, activate_user_result_data_map);

			}
		}
	}

	private void readFile(String file_path, String tag) {
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
							this.insertDataToMap(tmp_arr[0], tmp_arr[2], tag, user_data_map);
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

	private void insertDataToMap(String key, String value, Map<String, Set<String>> data_map) {
		Set<String> tmp_Set = data_map.get(key);

		if (tmp_Set == null) {
			tmp_Set = new HashSet<String>();
		}

		tmp_Set.add(value);
		data_map.put(key, tmp_Set);
	}

	private void insertDataToMap(String key, String value, String type, Map<String, Set<String>> data_map) {
		Set<String> tmp_Set = data_map.get(key);

		if (tmp_Set == null) {
			tmp_Set = new HashSet<String>();
		}

		tmp_Set.add(value + TAG + type);
		data_map.put(key, tmp_Set);
	}

	private void mapToString(Map<String, Set<String>> data_map) {
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

			for (String key : activate_user_result_data_map.keySet()) {
				Set<String> value_set = activate_user_result_data_map.get(key);

				for (String tmp : value_set) {
					this.accumulateCount(tmp, key);
				}

				writer.write(key + "\t" + value_set.size() + "\n");
			}

			System.out.println("request user session count is " + request_session_count);
			System.out.println("request user count is " + request_session_user_count.size());
			
			System.out.println("push click user session count is " + push_click_session_count);
			System.out.println("push click user count is " + push_click_session_user_count.size());
			
			System.out.println("not push click user session count is " + not_push_click_session_count);
			System.out.println("not push click user count is " + not_push_click_session_user_count.size());
			
			System.out.println("all session user count is " + activate_user_result_data_map.size());			
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

	private void accumulateCount(String data, String uid) {
		String type = data.split(TAG)[1];
		switch (type) {
			case REQUEST:
				request_session_count++;
				request_session_user_count.add(uid);
				break;
			case PUSH_CLICK:
				push_click_session_count++;
				push_click_session_user_count.add(uid);
				break;
			case NOT_PUSH_CLICK:
				not_push_click_session_count++;
				not_push_click_session_user_count.add(uid);
				break;
			default:
				break;
		}

	}
}
