public class DynArray<T>
{
     public T [] array;
     public int count;
     public int capacity;
     Class clazz;
     private static int MIN_CAPACITY = 16;
     private static double MIN_OCCUPANCY_PERCENTAGE = 0.5;

     public DynArray(Class clz)
     {
       clazz = clz;
        // new DynArray<Integer>(Integer.class);

        count = 0;
        makeArray(16);
     }

     public void makeArray(int new_capacity)
     {
       if (new_capacity == this.capacity) {
            return;
        }

        if (new_capacity < MIN_CAPACITY) {
            this.capacity = MIN_CAPACITY;
            return;
        }

        if (new_capacity < count) {
            throw new IllegalArgumentException("New capacity can't be smaller than current count");
        }

        T[] newArray = (T[]) java.lang.reflect.Array.newInstance(this.clazz, new_capacity);
        if (array != null) {
            System.arraycopy(this.array, 0, newArray, 0, count);
        }
        this.array = newArray;
        this.capacity = new_capacity;

        
     }

     public T getItem(int index)
     {
       if (index >= this.count || index < 0) {
            throw new IndexOutOfBoundsException("Invalid element position");
        }

        return this.array[index];


     }

     public void append(T itm)
     {
       if (this.count >= this.capacity) {
            makeArray(this.capacity * 2);
        }
        this.array[this.count] = itm;
        this.count++;


     }

     public void insert(T itm, int index)
     {
       if (index > this.count || index < 0) {
            throw new IndexOutOfBoundsException("Invalid insert position");
        }
        if (this.count >= this.capacity) {
            makeArray(this.capacity * 2);
        }

        for (int i = this.count - 1; i >= index; i--) {
            this.array[i + 1] = this.array[i];
        }
        this.array[index] = itm;
        this.count++;


     }

     public void remove(int index)
     {
       if (index >= this.count || index < 0) {
            throw new IndexOutOfBoundsException("Invalid remove position");
        }

        for (int i = index; i < this.count - 1; i++) {
            this.array[i] = this.array[i+1];
        }
        this.array[count - 1] = null;

        this.count--;
        int decreasedCapacity = (int) (this.capacity / 1.5);
        if ((double) this.count / this.capacity < MIN_OCCUPANCY_PERCENTAGE) {
            makeArray(Math.max(decreasedCapacity, MIN_CAPACITY));
        }


     }

}