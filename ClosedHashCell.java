/**
 * Represents a single cell in the closedHashSet.
 */
public class ClosedHashCell {

    /* default string in each cell. */
    private String EMPTY = null;
    /* the value each cell has. */
    private String value;
    /* a flag which represents if the value i this cell was deleted. */
    private boolean deleteFlag;

    /**
     * a default constructor of a single cell in the closedHashSet. Every cell has two things: value and a
     * deleteFlag.
     */
    public ClosedHashCell(){
        value = EMPTY;
        deleteFlag = false;
    }

    /**
     * adding a value to this cell.
     * @param newVal the value we try adding.
     * @return true if we succeed adding the value.
     */
    public boolean addMe(String newVal){
        // adding a value happens only if the cell is empty. //
        if (isEmpty()){
            value = newVal;
            deleteFlag = false;
            return true;
        }
        return false;
    }

    /**
     * deleting a value to this cell.
     * @param toDelete the value we try deleting.
     * @return true if we succeed deleting the value.
     */
    public boolean deleteMe(String toDelete){
        // deletion happens only if the cell isn't empty and the cell's value equals the deletion's word. //
        if (!isEmpty() && value.equals(toDelete)){
            value = EMPTY;
            deleteFlag = true;
            return true;
        }
        return false;
    }

    /**
     * checks if there was deletion in this cell.
     * @return true if the deleteFlag is true.
     */
    public boolean isDeleteFlag(){return deleteFlag;}

    /**
     * gets the value of this cell.
     * @return the cell's value
     */
    public String getValue() {
        return value;
    }

    /* checks if the call is empty. */
    private boolean isEmpty(){return value == EMPTY;}
}
