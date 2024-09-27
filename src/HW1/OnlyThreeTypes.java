package HW1;

public class OnlyThreeTypes{

    private final int countInt;
    private final String name;
    private ThirdType thirdType = null;
    private long startSpeedometer;
    private long currentSpeed;

    public static void main(String[] args) {
        OnlyThreeTypes example = new OnlyThreeTypes();
        example.present();
    }

    private OnlyThreeTypes() {
        name = "Volodya";
        countInt = 10;
        thirdType = new ThirdType();
    }

    private void present() {
        this.showAllInfo();
        this.comparison(10);
        this.comparison("Volodya");
        this.comparison(new OnlyThreeTypes());
        this.comparison(thirdType);
    }

    private void showAllInfo() {
        System.out.println("Data String " + name + " (если что, String это ссылочный тип)");
        System.out.println("Data int " + countInt);
        System.out.println("Data «own type» " + thirdType);
        this.thirdType.showSomeInfo();
    }

    private <Into> void comparison(Into item) {
        this.startSpeedometer = System.nanoTime();
        System.out.print("Ищем похожие переменные.. ");
        if (item.equals(this.countInt) || item.equals(this.name) || item.equals(this.thirdType)) {
            System.out.print("всё в порядке");
        } else {
            System.out.print("переменные разные");
        }
        this.currentSpeed = System.nanoTime();
        System.out.printf(" (Время выполнения %d нс)%n", printTime());
    }

    private long printTime() {
        return (currentSpeed-startSpeedometer);
    }

    private static class ThirdType {
        private final String someInfo;

        private ThirdType() {
            someInfo = "Ну, чего?";
        }

        private String getSomeInfo() {
            return someInfo;
        }

        private void showSomeInfo() {
            System.out.println("Оставленные сообщения: " + getSomeInfo());
        };
    }
}

/* Напишите программный код, в котором все данные хранятся только в переменных трех типов данных:
Ссылочные, примитивные и своего класса содержащего: конструктор и метод отображения данных.
Выведите написанные данные.

Дополните предыдущий код сравнением ваших данных с другой переменной, данный код должен имитировать
простейший поиск перебором. Оцените время выполнения алгоритма с помощью базового метода System.nanoTime().
* */
// Serega, sure