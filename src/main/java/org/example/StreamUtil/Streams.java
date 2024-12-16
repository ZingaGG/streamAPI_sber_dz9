package org.example.StreamUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class Streams<T> {
    private final List<T> copyOfList;

    private Streams(List<T> inputCollection){
        this.copyOfList = new ArrayList<>(inputCollection);
    }

    public static <T> Streams<? extends T> of(List<? extends T> list) {
        return new Streams<>(list);
    }

    public Streams<T> filter(Predicate<? super T> predicate){
        List<T> result = new ArrayList<>();
        for(T elem : copyOfList){
            if(predicate.test(elem)){
                result.add(elem);
            }
        }

        return new Streams<>(result);
    }

    public <R> Streams<R> transform(Function<? super T, ? extends R> transformer){
        List<R> result = new ArrayList<>();
        for(T elem : copyOfList){
            result.add(transformer.apply(elem));
        }

        return new Streams<>(result);
    }

    public <K,V> Map<K,V> toMap(Function<? super T, ? extends K> keyFunction, Function<? super T, ? extends V> valueFunction){
        Map<K,V> resultMap = new HashMap<>();
        for (T element : copyOfList) {
            resultMap.put(keyFunction.apply(element), valueFunction.apply(element));
        }
        return resultMap;
    }

    // Интересная реализация для большего понимания функциональных интерфейсов
    public <K,V> Map<K,V> toMap(Function<? super T, ? extends K> keyFunction, Supplier<V> value){
        Map<K,V> resultMap = new HashMap<>();
        for (T element : copyOfList) {
            resultMap.put(keyFunction.apply(element), value.get());
        }
        return resultMap;
    }

}
