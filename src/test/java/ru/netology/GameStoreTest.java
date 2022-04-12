package ru.netology;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class GameStoreTest {

    private GameStore store = new GameStore();
    private Game game1 = store.publishGame("Civilization XV", "Strategy");
    private Game game2 = store.publishGame("Microsoft Flight Simulator", "Simulators");
    private Game game3 = store.publishGame("Doom", "Shooters");

    @Test
    public void shouldAddGame() {

        GameStore store = new GameStore();
        Game game = store.publishGame("Нетология Баттл Онлайн", "Аркады");

        assertTrue(store.containsGame(game));
    }

    // другие ваши тесты
    /**
     * Lookup game at the beginning of the list
     */
    @Test
    public void shouldFindGameAtTheBeginning(){
        assertTrue(store.containsGame(game1));
    }

    /**
     * Lookup game at the middle of the list
     */
    @Test
    public void shouldFindGameInTheMiddle(){
        assertTrue(store.containsGame(game2));
    }

    /**
     * Lookup game at the end of the list
     */
    @Test
    public void shouldFindGameAtTheEnd(){
        assertTrue(store.containsGame(game3));
    }

    /**
     * Добавление времени, потраченного игроком в первый раз
     */
    @Test
    public void shouldAddInitialPlayTime(){
        store.addPlayTime("Psycho", 12);
        assertEquals(12, store.getSumPlayedTime());
    }

    /**
     * Добавление дополнительного времени, потраченного игроком
     */
    @Test
    public void shouldAccumulatePlayTime(){
        store.addPlayTime("Psycho", 12);
        store.addPlayTime("Psycho", 18);

        assertEquals(30, store.getSumPlayedTime());
    }

    /**
     * Найти, кто играл дольше всех при отсутствии игроков
     */
    @Test
    public void shouldFindWhoPlayedMostNoPlayer(){
        assertNull(store.getMostPlayer());
    }

    /**
     * Найти, кто играл дольше всех при наличии одного игрока
     */
    @Test
    public void shouldFindWhoPlayedMostSinglePlayer(){
        store.addPlayTime("Maniac", 24);
        assertTrue(store.getMostPlayer().equals("Maniac"));
    }

    /**
     * Найти, кто играл дольше всех при наличии нескольких игроков
     */
    @Test
    public void shouldFindWhoPlayedMostSeveralPlayers(){
        store.addPlayTime("Maniac", 24);
        store.addPlayTime("Psycho", 14);
        store.addPlayTime("Ant Agonizer", 6);

        assertTrue(store.getMostPlayer().equals("Maniac"));
    }

    /**
     * Найти, кто играл дольше всех при наличии нескольких игроков, которые играли по 0 часов
     */
    @Test
    public void shouldFindWhoPlayedMostSeveralPlayersZeroTime(){
        store.addPlayTime("Maniac", 0);
        store.addPlayTime("Psycho", 0);
        store.addPlayTime("Ant Agonizer", 0);

        assertNotNull(store.getMostPlayer());
        assertTrue(store.getMostPlayer().equals("Maniac"));
    }

    /**
     * Найти, кто играл дольше всех при наличии нескольких игроков, которые играли по 0-1 часов
     */
    @Test
    public void shouldFindWhoPlayedMostSeveralPlayersZeroOrOneHour(){
        store.addPlayTime("Maniac", 0);
        store.addPlayTime("Psycho", 1);
        store.addPlayTime("Ant Agonizer", 1);

        assertNotNull(store.getMostPlayer());
        assertTrue(store.getMostPlayer().equals("Psycho"));
    }

    /**
     * Суммировать общее время игр при отсутствии игроков
     */
    @Test
    public void shouldTotalPlayTimeNoPlayers(){
        assertEquals(0, store.getSumPlayedTime());
    }

    /**
     * Суммировать общее время игр при наличии игроков
     */
    @Test
    public void shouldTotalPlayTimeSeveralPlayers(){
        store.addPlayTime("Maniac", 24);
        store.addPlayTime("Psycho", 14);
        store.addPlayTime("Cat Alizator", 6);
        store.addPlayTime("Maniac", 36);

        assertEquals(80, store.getSumPlayedTime());
    }
}
