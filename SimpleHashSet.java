public abstract class SimpleHashSet extends java.lang.Object implements SimpleSet{

    /**
     * Describes the higer load factor of a newly created hash set.
     */
    protected static final float DEFAULT_HIGHER_CAPACITY = 0.75f;
    /**
     * Describes the lower load factor of a newly created hash set.
     */
    protected static final float DEFAULT_LOWER_CAPACITY = 0.25f;
    /**
     * Describes the capacity of a newly created hash set.
     */
    protected static final int INITIAL_CAPACITY = 16;

    private float higherCapacity;
    private float lowerCapacity;
    private int initialCapacity;
    private int capacityMinusOne;

    /**
     * Constructs a new hash set with the default capacities.
     */
    protected SimpleHashSet(){
        higherCapacity = DEFAULT_HIGHER_CAPACITY;
        lowerCapacity = DEFAULT_LOWER_CAPACITY;
    }

    /**
     * Constructs a new hash set with capacity INITIAL_CAPACITY.
     * @param upperLoadFactor the upper load factor before rehashing.
     * @param lowerLoadFactor the lower load factor before rehashing.
     */
    protected SimpleHashSet(float upperLoadFactor, float lowerLoadFactor){
        higherCapacity = upperLoadFactor;
        lowerCapacity = lowerLoadFactor;
        initialCapacity = INITIAL_CAPACITY;
        capacityMinusOne = initialCapacity-1;

    }

    /**
     * @return The current capacity (number of cells) of the table.
     */
    public abstract int capacity();

    public abstract int clamp(int index);

    /**
     * @return The lower load factor of the table.
     */
    protected float getLowerLoadFactor(){ return lowerCapacity; }

    /**
     * @return The higher load factor of the table.
     */
    protected float getUpperLoadFactor(){ return higherCapacity; }

    /**
     * set the lowerLoadFactor to the newLowerCapacity.
     * @param newLowerCapacity the new lowerLoadFactor.
     */
    protected void setLowerCapacity(float newLowerCapacity){lowerCapacity = newLowerCapacity;}

    /**
     * set the upperLoadFactor to the newHigherCapacity.
     * @param newHigherCapacity the new upperLoadFactor
     */
    protected void setHigherCapacity(float newHigherCapacity){higherCapacity = newHigherCapacity;}

    /**
     * set the initial capacity of the hashSet to the newInitialCapacity.
     * @param newInitialCapacity the new initial capacity.
     */
    protected void setInitialCapacity(int newInitialCapacity){initialCapacity = newInitialCapacity;}


}
