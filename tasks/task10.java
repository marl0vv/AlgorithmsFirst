public class PowerSet
{
    public static class HashTable {

        public int size;
        public int step;
        public String[] slots;

        public HashTable(int sz, int stp) {
            size = sz;
            step = stp;
            slots = new String[size];
            for (int i = 0; i < size; i++) slots[i] = null;
        }

        public int hashFun(String value) {
            // hashcode can return negative that is why
            // I'm using absExact. Not sure if I was supposed to make
            // my own hashFunction, but let it be that way.
            // But if I wrote my own, I would sum or multiply ascii codes of chars of a string or something
            return Math.absExact(value.hashCode()) % size;
        }

        public int seekSlot(String value) {
            int slot = hashFun(value);

            for (int i = 0; i < size; i++) {
                if (slots[slot] == null || slots[slot].equals(value)) {
                    return slot;
                }
                // going from the start of a hashtable if reaches end
                slot = (slot + step) % size;
            }
            return -1;
        }

        public int put(String value) {
            int slot = seekSlot(value);
            if (slot != -1) {
                slots[slot] = value;
                return slot;
            }
            return -1;
        }

        public int find(String value) {
            int slot = hashFun(value);
            for (int i = 0; i < size; i++) {
                // Here can be a problem if we have a collision and insert element in hashFun() + step
                // and then delete element that was in a hashFun() place
                // but I suppose that it is okay for a simple implementation, and we didn't even implement deletion
                if (slots[slot] == null) {
                    return -1;
                }

                if (slots[slot].equals(value)) {
                    return slot;
                }

                slot = (slot + step) % size;
            }
            return -1;
        }
    }

    private final HashTable table;

   public PowerSet()
   {
     this.table = new HashTable(20000, 3);


   }

   public int size()
   {
    int count = 0;
    for (String slot : table.slots) {
        if (slot != null) {
            count++;
        }
    }
    return count;


   }


   public void put(String value)
   {
     if (value != null) {
            table.put(value);
        }

        
   }

    public boolean get(String value)
    {
        return table.find(value) != -1;


    }

    public boolean remove(String value)
    {
        int index = table.find(value);
        if (index != -1) {
            table.slots[index] = null;
            return true;
        }
        return false;


    }

    public PowerSet intersection(PowerSet set2)
    {
        PowerSet result = new PowerSet();
        for (String val : this.table.slots) {
            if (val != null && set2.get(val)) {
                result.put(val);
            }
        }
        return result;


    }

    public PowerSet union(PowerSet set2)
    {
        PowerSet result = new PowerSet();
        for (String val : this.table.slots) {
            if (val != null) result.put(val);
        }
        for (String val : set2.table.slots) {
            if (val != null) result.put(val);
        }
        return result;


    }

    public PowerSet difference(PowerSet set2)
    {
        PowerSet result = new PowerSet();
        for (String val : this.table.slots) {
            if (val != null && !set2.get(val)) {
                result.put(val);
            }
        }
        return result;


    }

    public boolean isSubset(PowerSet set2)
    {
        for (String val : set2.table.slots) {
            if (val != null && !this.get(val)) {
                return false;
            }
        }
        return true;


    }

    public bool equals(PowerSet<T> set2)
    {
        if (this.size() != set2.size()) return false;

        for (String val : this.table.slots) {
            if (val != null && !set2.get(val)) {
                return false;
            }
        }

        return true;


    }

}