/*
This is a generic implementation for symbol tables.  The symbol tables are
created using a hashmap. The methods defined on SymbolTable function just as the
methods defined for Map

Written by: Megan Lesha, Andrew Greenwell
*/
import java.util.*;

public class SymbolTableC<K, V> implements SymbolTable<K, V> {

  private Map<K, V> table;
  private int N;            // size of the SymbolTable

  SymbolTableC() {
    table = new HashMap<K, V>();
    N = 0;
  }

  public int getSize() { return N; }
  public void setSize(int N) { this.N = N; }

// implemented using composition of Map ADT
  public void put(K key, V value) { table.put(key, value); }
  public V get(K key) { return table.get(key); }
  public boolean containsKey(K key) { return table.containsKey(key); }
  public Set<K> keySet() { return table.keySet(); }  // useful to iterate over the keys in a SymbolTable


  public String toString() {
    Object[] keyArray = table.keySet().toArray();
    StringBuilder str = new StringBuilder();
    for (int i = 0; i < keyArray.length; i++) {
      Character c = (char) (int) keyArray[i];    // converts each key to its ASCII character equivalent
      str.append(String.format("%c = %s%n", c, table.get(keyArray[i])));
    }
    return str.toString();
  }

// unit testing
  public static void main(String[] args) {
    SymbolTable<Integer, String> table = new SymbolTableC<Integer, String>();
    table.put(65, "Andy");
    table.put(77, "Megan");
    table.put(78, "Nick");
    System.out.format("%n" + table + "%n");
  }
}
