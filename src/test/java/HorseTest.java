import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.MockedStatic;
import org.mockito.Mockito;




import static org.junit.jupiter.api.Assertions.*;

public class HorseTest {

    // Тест перевірки винятку при null значенні імені
    @Test
    @DisplayName("Перевірка викиду IllegalArgumentException при null імені")
    public void testConstructorThrowsExceptionForNullName() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new Horse(null, 10, 0));
        assertEquals("Name cannot be null.", exception.getMessage());
    }

    // Параметризований тест для порожнього рядка і пробільних символів
    @ParameterizedTest
    @DisplayName("Перевірка викиду IllegalArgumentException для порожнього рядка або пробільних символів")
    @ValueSource(strings = {"", " ", "\t", "\n"})
    public void testConstructorThrowsExceptionForBlankName(String blankName) {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new Horse(blankName, 10, 0));
        assertEquals("Name cannot be blank.", exception.getMessage());
    }

    // Тест перевірки винятку для негативної швидкості
    @Test
    @DisplayName("Перевірка викиду IllegalArgumentException для негативної швидкості")
    public void testConstructorThrowsExceptionForNegativeSpeed() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new Horse("Thunder", -10, 0));
        assertEquals("Speed cannot be negative.", exception.getMessage());
    }

    // Тест перевірки винятку для негативної дистанції
    @Test
    @DisplayName("Перевірка викиду IllegalArgumentException для негативної дистанції")
    public void testConstructorThrowsExceptionForNegativeDistance() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new Horse("Thunder", 10, -5));
        assertEquals("Distance cannot be negative.", exception.getMessage());
    }

    // Тест перевірки методу getName
    @Test
    @DisplayName("Перевірка методу getName")
    public void testGetName() {
        Horse horse = new Horse("Thunder", 10, 5);
        assertEquals("Thunder", horse.getName());
    }

    // Тест перевірки методу getSpeed
    @Test
    @DisplayName("Перевірка методу getSpeed")
    public void testGetSpeed() {
        Horse horse = new Horse("Thunder", 10, 5);
        assertEquals(10, horse.getSpeed());
    }

    // Тест перевірки методу getDistance
    @Test
    @DisplayName("Перевірка методу getDistance з трьома параметрами")
    public void testGetDistance() {
        Horse horse = new Horse("Thunder", 10, 5);
        assertEquals(5, horse.getDistance());
    }

    // Тест перевірки методу getDistance з двома параметрами
    @Test
    @DisplayName("Перевірка, що дистанція дорівнює нулю при створенні об'єкта з двома параметрами")
    public void testGetDistanceWithTwoParameters() {
        Horse horse = new Horse("Thunder", 10);
        assertEquals(0, horse.getDistance());
    }

    // Тест перевірки виклику getRandomDouble у методі move
    @Test
    @DisplayName("Перевірка виклику методу getRandomDouble з параметрами 0.2 та 0.9")
    public void testMoveCallsGetRandomDouble() {
        try (MockedStatic<Horse> mockedStatic = Mockito.mockStatic(Horse.class)) {
            Horse horse = new Horse("Thunder", 10, 5);
            horse.move();
            mockedStatic.verify(() -> Horse.getRandomDouble(0.2, 0.9));
        }
    }

    // Параметризований тест для перевірки роботи move і обчислення нової дистанції
    @ParameterizedTest
    @DisplayName("Перевірка правильності обчислення нової дистанції")
    @ValueSource(doubles = {0.3, 0.5, 0.9})
    public void testMoveCalculatesNewDistance(double randomFactor) {
        try (MockedStatic<Horse> mockedStatic = Mockito.mockStatic(Horse.class)) {
            mockedStatic.when(() -> Horse.getRandomDouble(0.2, 0.9)).thenReturn(randomFactor);

            Horse horse = new Horse("Thunder", 10, 5);
            horse.move();

            double expectedDistance = 5 + 10 * randomFactor;
            assertEquals(expectedDistance, horse.getDistance());
        }
    }
}