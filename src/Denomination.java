// Stuff
enum Form { bill, coin }
public record Denomination(String name, String displayName, double amt, Form form, String img) { }

