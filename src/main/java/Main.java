import java.util.List;
import java.util.concurrent.TimeUnit;

public class Main {

   import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.logging.*;

public class Main {



    private static final Logger logger = Logger.getLogger(Main.class.getName());

    static {
        try {
            // Створюємо FileHandler для запису логів у файл
            FileHandler fileHandler = new FileHandler("app.log", true);

            // Налаштовуємо формат виводу логів
            SimpleFormatter formatter = new SimpleFormatter() {
                private static final String FORMAT = "%1$tF %1$tT,%1$tL %4$s %2$s: %5$s%6$s%n";
                @Override
                public synchronized String format(LogRecord lr) {
                    return String.format(FORMAT,
                            new java.util.Date(lr.getMillis()),
                            lr.getSourceClassName(),
                            lr.getSourceMethodName(),
                            lr.getLevel().getLocalizedName(),
                            lr.getMessage(),
                            (lr.getThrown() == null ? "" : "\n" + lr.getThrown())
                    );
                }
            };

            fileHandler.setFormatter(formatter);
            fileHandler.setLevel(Level.ALL);  // Записуємо всі рівні логів
            Logger rootLogger = Logger.getLogger("");  // Кореневий логер
            rootLogger.addHandler(fileHandler);  // Додаємо FileHandler до кореневого логера
            rootLogger.setLevel(Level.ALL);  // Приймаємо всі рівні логування

        } catch (Exception e) {
            logger.log(Level.SEVERE, "Failed to initialize file handler", e);
        }
    }

    public static void main(String[] args) throws Exception {
        logger.log(Level.INFO, "Program started.");
        // Інша логіка програми


        List<Horse> horses = List.of(
                new Horse("Bucephalus", 2.4),
                new Horse("Ace of Spades", 2.5),
                new Horse("Zephyr", 2.6),
                new Horse("Blaze", 2.7),
                new Horse("Lobster", 2.8),
                new Horse("Pegasus", 2.9),
                new Horse("Cherry", 3)
        );
        Hippodrome hippodrome = new Hippodrome(horses);

        // Логування інформаційного рівня про початок стрибків
        logger.log(Level.INFO, "Початок стрибків. Кількість учасників: " + horses.size());

        for (int i = 0; i < 100; i++) {

            hippodrome.move();
            watch(hippodrome);
            TimeUnit.MILLISECONDS.sleep(200);
        }

        String winnerName = hippodrome.getWinner().getName();
        logger.log(Level.INFO, winnerName + " wins!");
        System.out.println(winnerName + " wins!");

        // Логування інформаційного про переможця
        logger.log(Level.INFO, "Закінчення стрибків. Переможець: " + winnerName);
    }


    private static void watch(Hippodrome hippodrome) throws Exception {
        hippodrome.getHorses().stream()
                .map(horse -> ".".repeat((int) horse.getDistance()) + horse.getName())
                .forEach(System.out::println);
        System.out.println("\n".repeat(10));
    }
}
