/**
 * Wraps an underlying Collection and serves to both simplify its API and give it a common type with the implemented SimpleHashSets.
 */
public class CollectionFacadeSet extends java.lang.Object implements SimpleSet {

    private java.util.Collection<String> facadeCollection;

    /**
     * Creates a new facade wrapping the specified collection.
     * @param collection the collection we want to wrap.
     */
    CollectionFacadeSet(java.util.Collection<String> collection){
        facadeCollection = collection;
        for (Object element : collection.toArray()){
            delete((String)element);
            add((String)element);
        }
    }

    @Override
    public boolean add(String newValue) {
        if (!contains(newValue)){
            facadeCollection.add(newValue);
            return true;
        }
        return false;
    }

    @Override
    public boolean contains(String searchVal) {
        return facadeCollection.contains(searchVal);
    }

    @Override
    public boolean delete(String toDelete) {
        if (contains(toDelete)){
            facadeCollection.remove(toDelete);
            return true;
        }
        return false;
    }

    @Override
    public int size() {
        return facadeCollection.size();
    }
}
