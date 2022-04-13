package ru.netology;

import java.util.*;

public class GameStore {
    private List<Game> games = new ArrayList<>();

    /**
     * Информация о том, какой игрок сколько играл в игры этого каталога
     * Ключ - имя игрока
     * Значение - суммарное количество часов в игры этого каталога
     */
    private Map<String, Integer> playedTime = new HashMap<>();

    /**
     * Создание объекта игры с заданными заголовком и жанром
     * Каждый объект игры помнит объект каталога, которому она принадлежит
     */
    public Game publishGame(String title, String genre) {
        Game game = new Game(title, genre, this);
        games.add(game);
        return game;
    }

    /**
     * Проверяет наличие игры в каталоге и возврашает true
     * если игра есть и false иначе
     */
    public boolean containsGame(Game game) {
        // Исправила код, упростила его
        return games.contains(game);
    }

    /**
     * Регистрирует количество времени, которое проиграл игрок
     * за игрой этого каталога. Игрок задаётся по имени. Время должно
     * суммироваться с прошлым значением для этого игрока
     */
    public void addPlayTime(String playerName, int hours) {
        // Добавила суммирование часов
        if (playedTime.containsKey(playerName)) {
            playedTime.put(playerName, playedTime.get(playerName) + hours);
        } else {
            playedTime.put(playerName, hours);
        }
    }

    /**
     * Ищет имя игрока, который играл в игры этого каталога больше всего
     * времени. Если игроков нет, то возвращется null
     */
    public String getMostPlayer() {
        // Исправила код, добавила поиск игрока, игравшего максимальное время
        int mostTime;
        String bestPlayer = null;

        if (!playedTime.isEmpty()) {
            mostTime = Collections.max(playedTime.values());
            for (String player : playedTime.keySet()) {
                if (playedTime.get(player) == mostTime) {
                    bestPlayer = player;
                }
            }
        }

        return bestPlayer;
    }

    /**
     * Суммирует общее количество времени всех игроков, проведённого
     * за играми этого каталога
     */

    public int getSumPlayedTime() {
        // Добавила суммирование времени всех игроков в игре
        int sum = 0;

        for (int hour : playedTime.values()) {
            sum += hour;
        }

        return sum;
    }
}
