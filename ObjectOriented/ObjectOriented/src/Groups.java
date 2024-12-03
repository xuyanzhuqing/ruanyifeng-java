public class Groups {
    public String name;

    public Lady[] ladies;

    public int fee;

    public int getFee () {
        return fee;
    }

    public void setFee (int fee) {
        this.fee = fee;
    }

    public Groups (String name, Lady ...ladies) {
        this.name = name;
        this.ladies = ladies;
    }

    @Override
    public String toString() {
        var str = new StringBuilder("\n");

        for (var lady : ladies) {
            str.append(lady + "\n");
        }

        return name + "共在前女友们\n「" + str + "」\n身上花了" + fee + "钱";
    }
}
