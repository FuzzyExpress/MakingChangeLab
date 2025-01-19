import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

enum Form { bill, coin }
public record Denomination(String name, String displayName, double amt, Form form, String img) { }

