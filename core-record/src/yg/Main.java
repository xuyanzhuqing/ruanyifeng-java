package yg;

// 记录类，就是值一旦初始化就不能修改其私有变量的类

import java.util.Objects;



public class Main {
    /** 自己构造一个记录类 */
    static void demo1 () {
        final class Point {
            public Point(int x, int y) {
                this.x = x;
                this.y = y;
            }
            public int x () {
                return this.x;
            }

            public int y () {
                return this.y;
            }

            private final int x;
            private final int y;

            @Override
            public boolean equals(Object o) {
                if (this == o) return true;
                if (o == null || getClass() != o.getClass()) return false;
                Point point = (Point) o;
                return x == point.x && y == point.y;
            }

            @Override
            public int hashCode() {
                return Objects.hash(x, y);
            }

            static Point of () {
                return new Point(0, 0);
            }

            static Point of (int x, int y) {
                return new Point(x, y);
            }
        }

        Point p1 = new Point(1, 2);
        Point p2 = Point.of(1, 3);
    }

    /** 使用record 修饰符 */
    static void demo2 () {
        record Point(int x, int y) {};

        Point p1 = new Point(1, 2);
        Point p2 = new Point(1, 2);

        System.out.println(p1.equals(p2));
    }

    public static void main(String[] args) {
        demo2();
    }
}
// public final class Point extends Record {
//     public Point(int x, int y) {
//         // 这是我们编写的Compact Constructor:
//         if (x < 0 || y < 0) {
//             throw new IllegalArgumentException();
//         }
//         // 这是编译器继续生成的赋值代码:
//         this.x = x;
//         this.y = y;
//     }
// }