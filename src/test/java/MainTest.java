import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;

public class MainTest {

    @Test
    @Timeout(value = 22, unit = TimeUnit.SECONDS)
    @Disabled("Тест вимкнено для економії часу, можна увімкнути за необхідності.")
    public void testMainExecutionTime() throws Exception {
        // Викликаємо метод main і перевіряємо, що він не виконується довше 22 секунд
        Main.main(new String[]{});
    }
}