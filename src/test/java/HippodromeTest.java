import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class HippodromeTest {

    private List<Horse> horses;

    @BeforeEach
    public void setUp() {
        // Створюємо список 30 коней для використання у тестах
        horses = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            horses.add(new Horse("Horse" + i, Math.random() * 10, Math.random() * 100));
        }
    }

    // Тест на IllegalArgumentException, якщо передати null до конструктора
    @Test
    public void testConstructorNullHorsesThrowsException() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new Hippodrome(null));
        assertEquals("Horses cannot be null.", exception.getMessage());
    }

    // Тест на IllegalArgumentException, якщо передати порожній список до конструктора
    @Test
    public void testConstructorEmptyHorsesThrowsException() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new Hippodrome(Collections.emptyList()));
        assertEquals("Horses cannot be empty.", exception.getMessage());
    }

    // Тест на перевірку, що метод getHorses повертає правильний список
    @Test
    public void testGetHorsesReturnsCorrectList() {
        Hippodrome hippodrome = new Hippodrome(horses);
        List<Horse> returnedHorses = hippodrome.getHorses();

        assertEquals(horses, returnedHorses); // Перевірка, що це той самий список
        assertIterableEquals(horses, returnedHorses); // Перевірка, що елементи однакові
    }

    // Тест на перевірку, що метод move викликає метод move для кожного коня
    @Test
    public void testMoveCallsMoveOnEachHorse() {
        // Створюємо список з 50 моків коней
        List<Horse> mockHorses = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            Horse horse = Mockito.mock(Horse.class);
            mockHorses.add(horse);
        }
        Hippodrome hippodrome = new Hippodrome(mockHorses);
        hippodrome.move();

        // Перевіряємо, що метод move був викликаний для кожного коня
        for (Horse horse : mockHorses) {
            verify(horse, times(1)).move();
        }
    }

    // Тест на перевірку, що метод getWinner повертає коня з найбільшою дистанцією
    @Test
    public void testGetWinnerReturnsHorseWithMaxDistance() {
        // Створюємо кілька коней з різними дистанціями
        Horse horse1 = new Horse("Horse 1", 2.0, 100.0);
        Horse horse2 = new Horse("Horse 2", 2.5, 150.0);
        Horse horse3 = new Horse("Horse 3", 3.0, 200.0);
        Hippodrome hippodrome = new Hippodrome(List.of(horse1, horse2, horse3));

        // Перевіряємо, що кінь з найбільшою дистанцією - horse3
        Horse winner = hippodrome.getWinner();
        assertEquals(horse3, winner);
    }
}