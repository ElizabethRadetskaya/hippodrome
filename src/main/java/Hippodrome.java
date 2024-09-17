import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import static java.util.Objects.isNull;

import org.slf4j.LoggerFactory;

import java.util.*;

import org.slf4j.Logger;


public class Hippodrome {

    // Створюємо логер для класу Hippodrome з використанням SLF4J
    private static final Logger logger = LoggerFactory.getLogger(Hippodrome.class);

    private final List<Horse> horses;

    public Hippodrome(List<Horse> horses) {
        // Якщо список коней є null, записуємо в лог і кидаємо виняток
        if (horses == null) {
            logger.error("Horses list is null");
            throw new IllegalArgumentException("Horses cannot be null.");
        }

        // Якщо список коней порожній, записуємо в лог і кидаємо виняток
        if (horses.isEmpty()) {
            logger.error("Horses list is empty");
            throw new IllegalArgumentException("Horses cannot be empty.");
        }

        this.horses = horses;

        // Лог з рівнем DEBUG (створення Hippodrome)
        logger.debug("Створення Hippodrome, кількість коней: [{}]", horses.size());
    }

    public List<Horse> getHorses() {
        return Collections.unmodifiableList(horses);
    }

    public void move() {
        horses.forEach(Horse::move);
    }

    public Horse getWinner() {
        Horse winner = horses.stream()
                .max(Comparator.comparing(Horse::getDistance))
                .get();
        logger.info("Winner is {}", winner.getName());
        return winner;
    }
}
