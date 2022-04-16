package ru.netology;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class PlayerTest {
    private GameStore store = new GameStore();

    private Game game = store.publishGame("Нетология Баттл Онлайн", "Аркады");
    private Game game1 = store.publishGame("Веселые Утки", "Хоррор");
    private Game game2 = store.publishGame("Ходячие мертвецы", "Хоррор");
    private Game game3 = store.publishGame("За углом", "Хоррор");

    /**
     * Суммировование времени, проведенного в одной игре одного жанра
     */
    @Test
    public void shouldSumGenreIfOneGame() {
        Player player = new Player("Petya");
        player.installGame(game1);
        player.play(game1, 3);

        int expected = 3;
        int actual = player.sumGenre(game1.getGenre());
        assertEquals(expected, actual);
    }

    /**
     * Суммирование времени, проведенного в двух играх одного жанра
     */
    @Test
    public void shouldSumGenreIfTwoGames() {
        Player player = new Player("Petya");
        player.installGame(game1);
        player.play(game1, 3);
        player.installGame(game2);
        player.play(game2, 5);

        int expected = 8;
        int actual = player.sumGenre(game1.getGenre());
        assertEquals(expected, actual);
    }

    /**
     * Суммирование времени, проведенного в трех играх одного жанра
     */
    @Test
    public void shouldSumGenreIfThreeGames() {
        Player player = new Player("Petya");
        player.installGame(game1);
        player.play(game1, 3);
        player.installGame(game2);
        player.play(game2, 5);
        player.installGame(game3);
        player.play(game3, 5);

        int expected = 13;
        int actual = player.sumGenre(game1.getGenre());
        assertEquals(expected, actual);
    }

    /**
     * Суммирование проигранного времени по жанру при отсутствии игр
     */
    @Test
    public void shouldSumGenreIfNoGames() {
        Player player = new Player("Petya");

        int expected = 0;
        int actual = player.sumGenre("Шутер");
        assertEquals(expected, actual);
    }

    /**
     * Время, проведенное в игре за один подход
     */
    @Test
    public void shouldPlayOnce() {
        Player player = new Player("Вася");
        player.installGame(game2);

        int expected = 5;
        int actual = player.play(game2, 5);
        assertEquals(expected, actual);
    }

    /**
     * Время, проведенное в игре за два подхода
     */
    @Test
    public void shouldPlayManyTimes() {
        Player player = new Player("Вася");
        player.installGame(game2);
        player.play(game2, 10);

        int expected = 15;
        int actual = player.play(game2, 5);
        assertEquals(expected, actual);
    }

    /**
     * Проверка возможности играть в неустановленную игру
     */
    @Test
    public void shouldPlayIfGameNotInstalled() {
        Player player = new Player("Petr");

        assertThrows(RuntimeException.class, () -> {
            player.play(game2, 5);
        });
    }

    /**
     * Установка игры в первый раз
     * (проверка при отсутствии возможности посмотреть через список игр)
     */
    @Test
    public void shouldInstall() {
        Game game = store.publishGame("Веселая ферма", "Стратегия");
        Player player = new Player("Vasya");
        player.installGame(game);

        int expected = 0;
        int actual = player.sumGenre("Стратегия");
        assertEquals(expected, actual);
    }

    /**
     * Переустановка игры - проверка обнуления времени
     */
    @Test
    public void shouldChangePlayTimeIfReinstall() {
        Player player = new Player("Vika");
        player.installGame(game);
        player.play(game, 4);
        player.installGame(game);   // Переустановка игры

        int expected = 4;
        int actual = player.sumGenre("Аркады");
        assertEquals(expected, actual);
    }

    /**
     * Вывод популярной игры жанра при одной игре
     */
    @Test
    public void showMostPlayedIfOneGame() {
        Player player = new Player("Vasya");
        player.installGame(game1);
        player.play(game1, 5);

        Game expected = game1;
        Game actual = player.mostPlayerByGenre("Хоррор");
        assertEquals(expected, actual);
    }

    /**
     * Вывод популярной игры жанра из двух игр при неравном времени
     */
    @Test
    public void showMostPlayedIfTwoGames() {
        Player player = new Player("Vasya");
        player.installGame(game1);
        player.play(game1, 5);
        player.installGame(game2);
        player.play(game2, 3);

        Game expected = game1;
        Game actual = player.mostPlayerByGenre("Хоррор");
        assertEquals(expected, actual);
    }

    /**
     * Вывод популярной игры жанра из трех игр при неравном времени
     */
    @Test
    public void showMostPlayedIfThreeGames() {
        Player player = new Player("Vasya");
        player.installGame(game1);
        player.play(game1, 4);
        player.installGame(game2);
        player.play(game2, 5);
        player.installGame(game3);
        player.play(game3, 6);

        Game expected = game3;
        Game actual = player.mostPlayerByGenre("Хоррор");
        assertEquals(expected, actual);
    }

    /**
     * Если в игры жанра не играли
     */
    @Test
    public void showMostPlayedIfNotPlayed() {
        Player player = new Player("Olya");

        Game expected = null;
        Game actual = player.mostPlayerByGenre("Thriller");
        assertEquals(expected, actual);
    }
}
