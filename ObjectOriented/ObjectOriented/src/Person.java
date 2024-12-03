public class Person {
    public static int number;

    // final 关键字， 不可修改
    private final String category = "human";

    public Person () {
        Person.number++;
        System.out.printf("-第 %d 个人被 new 出来", Person.number);
    }

    private String name;
    private int age;

    public int getAge () {
        return age;
    }

    public void setAge (int age) {
        if (age <= 0 || age > 120) {
            throw new IllegalArgumentException("age is illegal");
        }
        this.age = age;
    }

    public String getName () {
        return name;
    }

    public void setName (String name) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("name is illegal");
        }
        this.name = name;
    }

    public String toString () {
        return name + "今年" + age + "岁";
    }
}
