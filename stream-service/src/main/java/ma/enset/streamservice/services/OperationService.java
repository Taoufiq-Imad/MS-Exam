package ma.enset.streamservice.services;

import ma.enset.streamservice.entities.Operation;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.KeyValue;
import org.apache.kafka.streams.kstream.Grouped;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.Materialized;
import org.apache.kafka.streams.kstream.TimeWindows;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.function.Function;

@Service
public class OperationService {
    @Bean
    public Function<KStream<Long,Operation>, KStream<Long,Long>> kStreamFunction(){
        return (input)->{
          return input
                  .map((k,v)->new KeyValue<>(v.getCompte(),0L))
                  .groupBy((k,v)->k,Grouped.with(Serdes.Long(),Serdes.Long()))
                  .windowedBy(TimeWindows.of(Duration.ofSeconds(5)))
                  .count(Materialized.as("operation-count"))
                  .toStream()
                  .map((k,v)->new KeyValue<>(k.key(),v));
        };
    }
}

