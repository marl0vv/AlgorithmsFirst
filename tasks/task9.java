import java.lang.reflect.Array;

class NativeDictionary<T>
{
    public int size;
    public String [] slots;
    public T [] values;

    public NativeDictionary(int sz, Class clazz)
    {
      size = sz;
      slots = new String[size];
      values = (T[]) Array.newInstance(clazz, this.size);
    }

    public int hashFun(String key)
    {
      return Math.absExact(key.hashCode()) % size;

      
    }

    public boolean isKey(String key)
    {
      return get(key) != null;


    }

    public void put(String key, T value)
    {
        int slot = hashFun(key);
        int startSlot = slot;

        do {
            if (slots[slot] == null || slots[slot].equals(key)) {
                slots[slot] = key;
                values[slot] = value;
                return;
            }
            slot = (slot + 1) % size;
        } while (slot != startSlot);


    }

    public T get(String key)
    {
        int slot = hashFun(key);
        int startSlot = slot;

        do {
            // Means that if there was no collision and element empty then element is not in a dictionary
            if (slots[slot] == null) {
                return null;
            }
            if (key.equals(slots[slot])) {
                return values[slot];
            }
            slot = (slot + 1) % size;
        } while (slot != startSlot);

        return null;


    }
}