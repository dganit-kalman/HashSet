import java.util.HashSet;
import java.util.LinkedList;
import java.util.TreeSet;

/**
 * A class which analyze the differences of runningTime of 5 hashSet's types.
 */
public class SimpleSetPerformanceAnalyzer {

    /* array with all hashSet's types. */
    private SimpleSet[] performnceArray = new SimpleSet[5];
    /* array with the names of the hashSets' types. */
    private String[] arrayType = new String[5];
    /* the names of the hashSets' types. */
    private static final String OPEN_HASH_SET = "OpenHashSet";
    private static final String CLOSED_HASH_SET = "ClosedHashSet";
    private static final String TREE_SET = "TreeSet";
    private static final String LINKED_LIST = "LinkedList";
    private static final String HASH_SET = "HashSet";
    private static final String ADD_MSG_OPEN = "#These values correspond to the time it takes (in ms) to " +
            "insert data%s to all data structures";
    private static final String ADD_DATA_TYPE = "_AddData%s = ";
    private static final String CONTAINS_OPEN_MSG = "#These values correspond to the time it takes (in ns) " +
            "to check if %s is contained in the data structures initialized with data%s";
    private static final String CONTAINS_WORD_MSG = "_Contains_%s%s = ";
    private static final int NANO_TO_MICRO = 1000000;

    /**
     * a default constructor which contains all 5 hashSet's types.
     */
    public SimpleSetPerformanceAnalyzer(){
        performnceArray[0] = new OpenHashSet();
        performnceArray[1] = new ClosedHashSet();
        performnceArray[2] = new CollectionFacadeSet(new TreeSet<>());
        performnceArray[3] = new CollectionFacadeSet(new LinkedList<>());
        performnceArray[4] = new CollectionFacadeSet(new HashSet<>());
    }

    /* builds an array with the hashSets' names. */
    private void buildArrayType(){
        arrayType[0] = OPEN_HASH_SET;
        arrayType[1] = CLOSED_HASH_SET;
        arrayType[2] = TREE_SET;
        arrayType[3] = LINKED_LIST;
        arrayType[4] = HASH_SET;
    }

    /* adding data for each type of hashSet, and calculating the time it takes to add the data*/
    private void addData(String[] data, String num) {
        System.out.println(String.format(ADD_MSG_OPEN, num));
        buildArrayType();
        for (int i = 0; i < 5; i++) {
            long beforei = System.nanoTime();
            for (String item : data) {
                performnceArray[i].add(item);
            }
            long afteri = System.nanoTime() - beforei;
            System.out.println(arrayType[i]+ String.format(ADD_DATA_TYPE, num) + afteri/NANO_TO_MICRO);
        }
    }

    /* checks if a specific word appears in a data. Calculating the time it takes to check it in the data. */
    private void containData(String num, String word){
        System.out.println(String.format(CONTAINS_OPEN_MSG, word, num));
        buildArrayType();
        for (int i = 0; i < 5; i++) {
            long beforei = System.nanoTime();
            performnceArray[i].contains(word);
            long afteri = System.nanoTime() - beforei;
            System.out.println(arrayType[i]+ String.format(CONTAINS_WORD_MSG, word, num) + afteri);
        }
    }

    private void warmUp(String word){
        buildArrayType();
        for (int i = 0; i < 5; i++) {
            performnceArray[i].contains(word);
        }
    }

    public static void main(String[] args){
        String[] data1 = Ex3Utils.file2array(args[0]);
        String[] data2 = Ex3Utils.file2array(args[1]);
        SimpleSetPerformanceAnalyzer results1 = new SimpleSetPerformanceAnalyzer();
        SimpleSetPerformanceAnalyzer results2 = new SimpleSetPerformanceAnalyzer();
        results1.addData(data1, "1");
        System.out.println();
        results2.addData(data2, "2");
        System.out.println();
        for (int i=0; i<50000; i++){
            results1.warmUp("hi");
            results2.warmUp("hi");
            results1.warmUp("-13170890158");
            results2.warmUp("23");
        }
        results1.containData("1", "hi");
        System.out.println();
        results1.containData("1", "-13170890158");
        System.out.println();
        results2.containData("2", "23");
        System.out.println();
        results2.containData("2", "hi");
    }

}
