/**
 * represents a kind of hashSet has a hashing scheme which allows several items to be hashed to the same cell.
 */
public class OpenHashSet extends SimpleHashSet {

    /* the basic hashSet. */
    private OpenHashCell[] hashSet = new OpenHashCell[INITIAL_CAPACITY];
    /* the current amount of elements in the set. */
    private int currentElementSetSize = 0;

    /**
     * A default constructor. Constructs a new, empty table with default initial capacity (16), upper load
     * factor (0.75) and lower load factor (0.25).
     */
    public OpenHashSet(){
        super();
        buildHashSet(capacity(), hashSet);
    }

    /**
     * Constructs a new, empty table with the specified load factors, and the default initial capacity (16).
     * @param upperLoadFactor The upper load factor of the hash table.
     * @param lowerLoadFactor The upper load factor of the hash table.
     */
    public OpenHashSet(float upperLoadFactor, float lowerLoadFactor){
        super(upperLoadFactor, lowerLoadFactor);
        buildHashSet(capacity(), hashSet);
    }

    /**
     * builds the hash set by adding the elements one by one. Duplicate values should be ignored. The new
     * table has the default values of initial capacity (16), upper load factor (0.75), and lower load factor (0.25).
     * @param data Values to add to the set.
     */
    public OpenHashSet(String[] data){
        setLowerCapacity(DEFAULT_LOWER_CAPACITY);
        setHigherCapacity(DEFAULT_HIGHER_CAPACITY);
        setInitialCapacity(INITIAL_CAPACITY);
        buildHashSet(capacity(), hashSet);
        for (String element : data){
            add(element);
        }
    }

    /* builds hashSet which its size is the capacity parameter and rhe cells are OpenHashCell type. */
    private void buildHashSet(int capacity, OpenHashCell[] hashSet){
        for (int i=0; i < capacity; i++){
            hashSet[i] = new OpenHashCell();
        }
    }

    @Override
    public int capacity() {
        return hashSet.length;
    }

    @Override
    public int clamp(int index) {
        return index&(capacity()-1);
    }

    @Override
    public boolean add(String newValue) {
        if (!contains(newValue)){
            currentElementSetSize++;
            if (((double)size()/capacity()) > getUpperLoadFactor()){
                rehashing(true);
            }
            hashSet[clamp(newValue.hashCode())].addMe(newValue);
            return true;
        }
        return false;
    }

    @Override
    public boolean contains(String searchVal) {
        return hashSet[clamp(searchVal.hashCode())].isIn(searchVal);
    }

    @Override
    public boolean delete(String toDelete) {
        if (contains(toDelete)){
            currentElementSetSize--;
            if (((double)size()/capacity()) < getLowerLoadFactor() && capacity() > 1){
                rehashing(false);
            }
            hashSet[clamp(toDelete.hashCode())].deleteMe(toDelete);
            return true;
        }
        return false;
    }

    @Override
    public int size() {
        return currentElementSetSize;
    }

    /* increase or decrease the size of the hashSet and rehashing it. */
    private void rehashing(boolean increase){
        int newLength;
        // if loadFactor is too big. //
        if (increase){newLength = capacity()*2;}
        // if loadFactor is too small. //
        else{newLength = capacity()/2;}
        // builds the new hashSet with the new capacity. //
        OpenHashCell[] newHashSet = new OpenHashCell[newLength];
        buildHashSet(newLength, newHashSet);
        for (OpenHashCell cell : hashSet){
            for (Object item : cell.allItems()){
                newHashSet[((String)item).hashCode()&(newLength-1)].addMe((String)item);
            }
        }
        hashSet = newHashSet;
    }
}
