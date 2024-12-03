public class Student extends Person {
    public int score;

    public Student (int score, String name, int age) {
        // 默认会调用 super()
        super.setName(name);
        super.setAge(age);
        this.score = score;
    }
}
