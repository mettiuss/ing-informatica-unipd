public class ArrayMapTester {

  public static void main(String[] args) {
    ArrayMap map = new ArrayMap();
    map.put("Hello", "Hi");
    System.out.println(map.put("Hello", "Hello"));
    System.out.println(map.put("Hello1", "Hello"));
    System.out.println(map.put("Hello4", "Hello"));
    System.out.println(map.put("Hello2", "Hello"));
    System.out.println(map.put("Hello3", "Hello"));
    System.out.println(map);
  }
}
