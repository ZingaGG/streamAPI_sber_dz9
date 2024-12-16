package org.example;

import org.example.Model.BigUser;
import org.example.Model.User;
import org.example.StreamUtil.Streams;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class Main {
    public static void main(String[] args) {
        List<User> users = new ArrayList<>();
        users.add(new User("a1", 1));
        users.add(new User("a2", 10));
        users.add(new User("a3", 20));
        users.add(new User("a4", 30));

        // Пример работы of, filter, toMap

        Map<String, Integer> reuslt = Streams.of(users).filter((User user) -> user.getAge() > 15)
                .toMap((User user) -> user.getName(), (User user) -> user.getAge());

        System.out.println(users); // Показывает что исходная коллекция не изменилась
        System.out.println(reuslt);

        // Пример работы с transform (Из User -> BigUser)

        Map<String, Class<? extends BigUser>> result2 = Streams.of(users)
                .transform((User user) -> new BigUser(user.getName(), user.getAge()))
                .toMap((BigUser::getName), (BigUser::getClass));


        // Учебный эксперимент
        Map<String, Class<BigUser>> result2WithSupplier = Streams.of(users)
                .transform((User user) -> new BigUser(user.getName(), user.getAge()))
                .toMap((BigUser::getName), (() -> BigUser.class));


        System.out.println(result2);
        // Учебный эксперимент
        System.out.println(result2WithSupplier);
    }
}