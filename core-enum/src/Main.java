// 枚举

public class Main {
    /**  */
    static void demo () {
        // enum 被编译后的样子
        class ColorEnum {
            public final static String RED = "RED";
            public final static String YELLOW = "YELLOW";
            public final static String GREEN = "GREEN";
        }
        String c = ColorEnum.GREEN;
    }

    /** 默认顺序 */
    static void demo1 () {
        // 默认顺序
        enum Color {
            RED, YELLOW, GREEN
        }

        Color color = Color.RED;

        if (color == Color.YELLOW) {

        }
        if (color.equals(Color.YELLOW)) {}

        String yellow = Color.YELLOW.name();
        System.out.println(yellow); // YELLOW
        System.out.println(color.ordinal()); // 序号 0
    }

    /** 自定义顺序 */
    static void demo3 () {
        enum Weekday {
            MON(1), TUE(2), WEB(3), THU(4), FRI(5),SAT(6), SUN(0);

            public final int dayValue;

            private Weekday(int dayValue) {
                this.dayValue = dayValue;
            }
        }
        Weekday mon = Weekday.MON;

        if (mon.dayValue == 1) {
            System.out.println("起来了，老登，该上班了");
        }
    }

    /** 带翻译的类 */
    static void demo4 () {
        enum Weekday {
            MON(1, "星期一"),
            TUE(2, "星期二"),
            WED(3, "星期三"),
            THU(4, "星期四"),
            FIR(5, "星期五"),
            SAT(6, "星期六"),
            SUN(0, "星期天");

            Weekday(int dayValue, String locale) {
                this.dayValue = dayValue;
                this.locale = locale;
            }

            private final int dayValue;
            private final String locale;
        }

        Weekday day = Weekday.SUN;

        var sb = new StringBuilder();
        sb.append(day.locale).append("是个好日子");
        System.out.println(sb.toString());
    }

    public static void main(String[] args) {
        demo4();
    }
}



enum State {

}