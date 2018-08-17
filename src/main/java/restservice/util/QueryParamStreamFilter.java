package restservice.util;

import spark.QueryParamsMap;

import java.util.stream.Stream;

@FunctionalInterface()
public interface QueryParamStreamFilter<T> {
    Stream<T> filter(Stream<T> stream, QueryParamsMap queryParamsMap);
}
