package com.youche.flink;

import org.apache.flink.api.common.functions.FlatMapFunction;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.datastream.SingleOutputStreamOperator;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.windowing.time.Time;
import org.apache.flink.util.Collector;

public class SocketTextStreamWordCount {

    public static void main(String[] args) throws Exception {
        if (args.length != 2) {
            System.out.println("SocketTextStreamWordCount");
            return ;
        }

        String hostname = args[0];
        System.out.println(hostname);
        Integer port = Integer.parseInt(args[1]);
        System.out.println(port);

        final StreamExecutionEnvironment environment = StreamExecutionEnvironment.getExecutionEnvironment();

        // 获取数据
        DataStreamSource<String> stream = environment.socketTextStream(hostname, port);

        // 记数数量
        SingleOutputStreamOperator<Tuple2<String, Integer>> sum = stream
                .flatMap(new LineSplitter())
                // .keyBy((KeySelector<Tuple2<String, Integer>, Integer>) stringIntegerTuple2 -> 0)
                .keyBy(0)
                .timeWindow(Time.seconds(5))
                .sum(1);

        sum.print();

        environment.execute("SocketTextStreamWordCount");
        System.out.println("main finished");
    }

    public static final class LineSplitter implements FlatMapFunction<String, Tuple2<String, Integer>> {

        @Override
        public void flatMap(String s, Collector<Tuple2<String, Integer>> collector) throws Exception {
            String[] tokens = s.toLowerCase().split("\\W+");

            for (String token : tokens) {
                if (token.length() > 0) {
                    collector.collect(new Tuple2<String, Integer>(token, 1));
                }
            }
        }
    }
}
