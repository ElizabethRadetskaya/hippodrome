import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class Horse {

    private static final Logger logger = LogManager.getLogger(Horse.class);

    private String name;
    private double speed;
    private double distance;

    public Horse(String name, double speed) {
        this(name, speed, 0);
    }

    public Horse(String name, double speed, double distance) {
        // Форматування часу для лога
        if (name == null) {
            logger.error("Name is null");
            throw new IllegalArgumentException("Name cannot be null.");
        }

        if (name.trim().isEmpty()) {
            logger.error("Name is blank");
            throw new IllegalArgumentException("Name cannot be blank.");
        }

        if (speed < 0) {
            logger.error("Speed is negative");
            throw new IllegalArgumentException("Speed cannot be negative.");
        }

        if (distance < 0) {
            logger.error("Distance is negative");
            throw new IllegalArgumentException("Distance cannot be negative.");
        }

        this.name = name;
        this.speed = speed;
        this.distance = distance;

        logger.debug("Створення Horse, ім'я [" + name + "], швидкість [" + speed + "]");
    }

    public String getName() {
        return name;
    }

    public double getSpeed() {
        return speed;
    }

    public double getDistance() {
        return distance;
    }

    public void move() {
        distance += speed * Math.random();
    }
    public static double getRandomDouble(double min, double max) {
        return Math.random() * (max - min) + min;
    }
}