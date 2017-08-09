package com.git.huanghaifeng.java.kafka.streams;

import java.util.Arrays;
import java.util.Properties;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.KeyValue;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.KStreamBuilder;
import org.apache.kafka.streams.kstream.KTable;
import org.apache.kafka.streams.kstream.KeyValueMapper;
import org.apache.kafka.streams.kstream.Reducer;
import org.apache.kafka.streams.kstream.ValueMapper;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
/**
 * 统计log中的cost
 * @author huanghaifeng
 * 2017-08-04
 */
public class CostSumDemo {

	public static void main(String[] args) throws Exception {
		Properties props = new Properties();
		props.put(StreamsConfig.APPLICATION_ID_CONFIG, "streams-wordcount");
		props.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
		props.put(StreamsConfig.ZOOKEEPER_CONNECT_CONFIG, "localhost:2181");
		props.put(StreamsConfig.KEY_SERDE_CLASS_CONFIG, Serdes.String().getClass().getName());
		props.put(StreamsConfig.VALUE_SERDE_CLASS_CONFIG, Serdes.String().getClass().getName());
		props.put(StreamsConfig.POLL_MS_CONFIG, 10000);

		// setting offset reset to earliest so that we can re-run the demo code with the same pre-loaded data
		props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");

		KStreamBuilder builder = new KStreamBuilder();

		KStream<String, String> source = builder.stream("streams-file-input");

		KTable<String, String> counts = source.flatMapValues(new ValueMapper<String, Iterable<String>>() {
			@Override
			public Iterable<String> apply(String value) {
				try {
					//"<  10289218> END  :L0301002:11-15:55:00-085160  cost [1]ms"
					if (value.contains("cost")) {
						JSONObject json = JSON.parseObject(value);
						String log = json.getString("LOG");
						String cost = log.split("cost \\[")[1].split("\\]")[0];
						return Arrays.asList(cost);
					}
				} catch (Exception e) {
					e.printStackTrace();
					System.out.println(value);
				}
				return Arrays.asList();
			}
		}).map(new KeyValueMapper<String, String, KeyValue<String, String>>() {
			@Override
			public KeyValue<String, String> apply(String key, String value) {
				return new KeyValue<>("Cost", value);
			}
		}).reduceByKey(new Reducer<String>() {
			@Override
			public String apply(String value1, String value2) {
				return String.valueOf(Integer.valueOf(value1) + Integer.valueOf(value2));
			}
		}, "Sum");

		// need to override value serde to Long type
		counts.to(Serdes.String(), Serdes.String(), "streams-wordcount-output");

		KafkaStreams streams = new KafkaStreams(builder, props);
		streams.start();

		// usually the stream application would be running forever,
		// in this example we just let it run for some time and stop since the input data is finite.
		Thread.sleep(500000L);

		streams.close();
	}
}