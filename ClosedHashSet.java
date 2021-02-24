/**
 * represents a kind of hashSet which has hashing scheme in which each cell contains at most one item.
 */
public class ClosedHashSet extends SimpleHashSet {

    /* the basic hashSet. */
    private ClosedHashCell[] hashSet = new ClosedHashCell[INITIAL_CAPACITY];
    /* current amount of elements in the hashSet. */
    private int currentElementSetSize = 0;
    private int i = 0;
    private static final int CAPACITY_CHANGE= 2;

    /**
     * A default constructor. Constructs a new, empty table with default initial capacity (16), upper load
     * factor (0.75) and lower load factor (0.25).
     */
    public ClosedHashSet(){
        super();
        buildHashSet(capacity(), hashSet);
    }

    /**
     * Constructs a new, empty table with the specified load factors, and the default initial capacity (16).
     * @param upperLoadFactor The upper load factor of the hash table.
     * @param lowerLoadFactor The upper load factor of the hash table.
     */
    public ClosedHashSet(float upperLoadFactor,float lowerLoadFactor){
        super(upperLoadFactor,lowerLoadFactor);
        buildHashSet(capacity(), hashSet);
    }

    /**
     * builds the hash set by adding the elements one by one. Duplicate values should be ignored. The new
     * table has the default values of initial capacity (16), upper load factor (0.75), and lower load
     * factor (0.25).
     * @param data Values to add to the set.
     */
    public ClosedHashSet(String[] data){
        setLowerCapacity(DEFAULT_LOWER_CAPACITY);
        setHigherCapacity(DEFAULT_HIGHER_CAPACITY);
        setInitialCapacity(INITIAL_CAPACITY);
        buildHashSet(capacity(), hashSet);
        for (String element : data){
            add(element);
        }
    }

    /* builds hashSet which its size is the capacity parameter and rhe cells are OpenHashCell type. */
    private void buildHashSet(int capacity, ClosedHashCell[] hashSet){
        for (int i=0; i < capacity; i++){
            hashSet[i] = new ClosedHashCell();
        }
    }

    @Override
    public int capacity() {
        return hashSet.length;
    }

    @Override
    public int clamp(int index) {
        return (index+(i+i*i)/2)&(capacity()-1);
    }

    @Override
    public boolean add(String newValue) {
        if (!contains(newValue)){
            currentElementSetSize++;
            if ((double)size()/capacity() > getUpperLoadFactor()){
                rehashing(true);
            }
            i = 0;
            int index = clamp(newValue.hashCode());
            boolean addingNewVal = hashSet[index].addMe(newValue);
            while (!addingNewVal){
                i++;
                addingNewVal = hashSet[index].addMe(newValue);
            }
            return true;
        }
        return false;
    }

    @Override
    public boolean contains(String searchVal) {
        i = 0;
        while (i < capacity()){
            int currIndex = clamp(searchVal.hashCode());
            if (hashSet[currIndex].isDeleteFlag()){i++;}
            else if (hashSet[currIndex].getValue() == null){
                return false;
            }
            else if (hashSet[currIndex].getValue().equals(searchVal)){
                return true;
            }
            else {
                i++;
            }
        }
        return false;
    }

    @Override
    public boolean delete(String toDelete) {
        i = 0;
        if (contains(toDelete)){
            while (!hashSet[clamp(toDelete.hashCode())].deleteMe(toDelete)){
                i++;
            }
            currentElementSetSize--;
            if ((double)size()/capacity() < getLowerLoadFactor() && capacity() > 1){
                rehashing(false);
            }
            return true;
        }
        return false;
    }

    @Override
    public int size() {
        return currentElementSetSize;
    }

    /* re-hashing the hashSet when the loadFactor is too big or small. */
    private void rehashing(boolean increase){
        int newLength;
        // if loadFactor is too big. //
        if (increase){
            newLength = capacity()*CAPACITY_CHANGE;
        }
        // if loadFactor is too small. //
        else{newLength = capacity()/CAPACITY_CHANGE;}
        // builds the new hashSet with the new capacity. //
        ClosedHashCell[] newHashSet = new ClosedHashCell[newLength];
        buildHashSet(newLength, newHashSet);
        for (ClosedHashCell cell : hashSet){
            if (cell.getValue() != null) {
                int j = 0;
                int clamping = (cell.getValue().hashCode() + (j+j*j) / 2) & (newLength - 1);
                while (!newHashSet[clamping].addMe(cell.getValue())) {
                    j++;
                    clamping = (cell.getValue().hashCode() + (j+j*j) / 2) & (newLength - 1);
                }
            }
        }
        hashSet = newHashSet;
    }
}
