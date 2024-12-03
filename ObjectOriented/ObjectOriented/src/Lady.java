public class Lady {
    private String name;
    private int birth;

    public int currentYear = 2024;

    public Lady (String name,  int birth) {
        this.birth = birth;
        this.name = name;
    }

    public Lady (String name) {
        this.name = name;
    }

    public Lady () {}

    // 真实年龄计算方法
    private int calculateAge () {
        // 女生永远年轻 10 岁
        return currentYear - birth;
    }

    public void callme () {
        System.out.println("靓女");
    }

    public void callme(int age) {
        if (age < 18) {
            System.out.println("阿姨");
        } else if(age >= 18 && age <= 60){
            callme();
        } else {
            System.out.println("小姑娘");
        }
    }

    // 对外宣称的年纪
    public int getAge () {
        return calculateAge() - 10;
    }

    @Override
    public String toString() {
        return currentYear + "年" + name + "对外宣称" + getAge() + "岁，实际上已经" + calculateAge();
    }
}
