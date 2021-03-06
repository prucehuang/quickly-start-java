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
import org.apache.kafka.streams.kstream.ValueMapper;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

/**
 * 统计log中的user请求次数
 * @author huanghaifeng
 * 2017-08-04
 */
public class UserCountDemo {

	public static void main(String[] args) throws Exception {
		Properties props = new Properties();
		props.put(StreamsConfig.APPLICATION_ID_CONFIG, "streams-wordcount");
		props.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
		props.put(StreamsConfig.ZOOKEEPER_CONNECT_CONFIG, "localhost:2181");
		props.put(StreamsConfig.KEY_SERDE_CLASS_CONFIG, Serdes.String().getClass().getName());
		props.put(StreamsConfig.VALUE_SERDE_CLASS_CONFIG, Serdes.String().getClass().getName());
		props.put(StreamsConfig.COMMIT_INTERVAL_MS_CONFIG, 10000);

		// setting offset reset to earliest so that we can re-run the demo code with the same pre-loaded data
		props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");

		KStreamBuilder builder = new KStreamBuilder();

		KStream<String, String> source = builder.stream("streams-file-input");

		KTable<String, Long> counts = source.flatMapValues(new ValueMapper<String, Iterable<String>>() {
			@Override
			public Iterable<String> apply(String value) {
				try {
					//<  17039536> BEGIN:L0106002:11-15:55:00-078885  [_CA=2.3&_ENDIAN=0&F_OP_ROLE=1&F_OP_USER=27707874&F_OP_BRANCH=8086&F_SESSION=675932164188152&F_CHANNEL=7&F_OP_SITE=3@4028C2DDD9043E@40183253132094&CUSTOMER=27707874&NO_CHECK_STATUS=1]\n
					if (value.contains("F_OP_USER")) {
						JSONObject json = JSON.parseObject(value);
						String log = json.getString("LOG");
						String user = log.split("F_OP_USER=")[1].split("&")[0];
						return Arrays.asList(user);
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
				return new KeyValue<>(value, value);
			}
		}).countByKey("Counts");

		// need to override value serde to Long type
		counts.to(Serdes.String(), Serdes.Long(), "streams-wordcount-output");

		KafkaStreams streams = new KafkaStreams(builder, props);
		streams.start();

		// usually the stream application would be running forever,
		// in this example we just let it run for some time and stop since the input data is finite.
		Thread.sleep(500000L);

		streams.close();
	}
}