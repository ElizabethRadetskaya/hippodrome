import java.text.SimpleDateFormat;

import java.util.Comparator;
import java.util.List;



import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Hippodrome {

    private static final Logger logger = Logger.getLogger(Hippodrome.class.getName());
    private final List<Horse> horses;

    public Hippodrome(List<Horse> horses) {
        // Форматування часу для лога
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss,SSS");
        String currentTime = sdf.format(new Date());

        if (horses == null) {
            // Логування помилки, якщо список коней null
            logger.log(Level.SEVERE, currentTime + " ERROR Hippodrome: Horses list is null");
            throw new IllegalArgumentException("Horses cannot be null.");
        } else if (horses.isEmpty()) {
            // Логування помилки, якщо список коней порожній
            currentTime = sdf.format(new Date());
            logger.log(Level.SEVERE, currentTime + " ERROR Hippodrome: Horses list is empty");
            throw new IllegalArgumentException("Horses cannot be empty.");
        }

        this.horses = horses;

        // Логування інформації після успішного створення об'єкта Hippodrome
        currentTime = sdf.format(new Date());
        logger.log(Level.FINE, currentTime + " DEBUG Hippodrome: створення Hippodrome, коней [" + horses.size() + "]");
    }

    public List<Horse> getHorses() {
        return horses;
    }

    public void move() {
        horses.forEach(Horse::move);
    }

    public Horse getWinner() {
        return horses.stream()
                .max(Comparator.comparing(Horse::getDistance))
                .orElseThrow();
    }
}