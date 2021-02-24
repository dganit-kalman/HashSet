import java.util.LinkedList;

/**
 * Represents a single cell in the closedHashSet.
 */
public class OpenHashCell {

    /* the linkedList in the cell. */
    private LinkedList<String> list;

    /**
     * a default constructor of a single cell in the openHashSet. Each cell has a linkedList.
     */
    public OpenHashCell(){
        list = new LinkedList<String>();
    }

    /**
     * adding a value to this cell.
     * @param newVal the value we try adding.
     */
    public void addMe(String newVal){
        list.add(newVal);
    }

    /**
     * checks if the searchVal is in this cell.
     * @param searchVal the value we are searching in this cell.
     * @return true if the searchVal is in this cell.
     */
    public boolean isIn(String searchVal){
        return list.contains(searchVal);
    }

    /**
     * deleting a value to this cell.
     * @param toDelete the value we try deleting.
     */
    public void deleteMe(String toDelete){
        list.remove(toDelete);
    }

    /**
     * @return returns all the items in this cell in an array
     */
    public Object[] allItems(){
        return list.toArray();
    }
}
