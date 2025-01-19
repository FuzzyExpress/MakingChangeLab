package Print;

public class Print {
    // Python style print function made using Cursor
    public static void print(Object... objects) {
        StringBuilder sb = new StringBuilder();
        for (Object obj : objects) {
            sb.append(obj).append(" ");
        }
        System.out.println(sb.toString().trim());
    }
}

