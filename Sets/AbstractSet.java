/*  Student information for assignment:
 *
 *  On <MY> honor, <Siddharth Potta > , <Samhith Konyala>
 *  this programming assignment is <MY> own work
 *  and <We> have not provided this code to any other student.
 *
 *  Number of slip days used: 0
 *
 *  Student 1 (Student whose Canvas account is being used)
 *  UTEID: sp55697
 *  email address: siddharthpotta@gmail.com
 *  TA name: Bersam
 *
 *  Student 2
 *  UTEID: sk62423
 *  email address: samhith.konyala@gmail.com
 */

import java.util.Iterator;

/**
 * Students are to complete this class. 
 * Students should implement as many methods
 * as they can using the Iterator from the iterator 
 * method and the other methods. 
 *
 */
public abstract class AbstractSet<E> implements ISet<E> {

    /* DELETE THIS COMMENT FROM YOUR SUBMISSION.
     * 
     * RECALL:
     * 
     * NO INSTANCE VARIABLES ALLOWED.
     * 
     * NO DIRECT REFERENCE TO UnsortedSet OR SortedSet ALLOWED.
     * (In other words the data types UnsortedSet and SortedSet
     * will not appear any where in this class.)
     * 
     * NO DIRECT REFERENCES to ArrayList or other Java Collections.
     * 
     * NO METHODS ADDED other than those in ISet and Object.
     */
     
    //QUESTIONS -
    // Do we need a constructor?
    // question on equals 
    

    /**
      * A union operation. Add all items of otherSet that 
      * are not already present in this set to this set.
      * @param otherSet != null
      * @return true if this set changed as a result of this operation, 
      * false otherwise.
      */
    public boolean addAll(ISet<E> otherSet) {
        // precon
        if (otherSet == null) {
            throw new IllegalArgumentException("argument can't be null");
        }
        boolean addedAll = true;    
        for (E value : otherSet) {
            // if current value is added, set boolean to true
            if (!add(value)) {
                addedAll = false;
            }
        }
        return addedAll;
    }

    /**
     * Make this set empty.
     * <br>pre: none
     * <br>post: size() = 0
     */
    public void clear() {
        Iterator<E> iterator = this.iterator();
        while (iterator.hasNext()) {
            iterator.next();
            iterator.remove();
        }
    }
    /**
     * Determine if item is in this set. 
     * <br>pre: item != null
     * @param item element whose presence is being tested. 
     * Item may not equal null.
     * @return true if this set contains the specified item, false otherwise.
     */
    public boolean contains(E item) {
        if (item == null) {
            throw new IllegalArgumentException("argument can't be null");
        }
        for (E value : this) {
            // iterates through this object and looks for item
            if (item.equals(value)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Determine if all of the elements of otherSet are in this set.
     * <br> pre: otherSet != null
     * @param otherSet != null
     * @return true if this set contains all of the elements in otherSet, 
     * false otherwise.
     */
    public boolean containsAll(ISet<E> otherSet) {
        // precon
        if (otherSet == null) {
            throw new IllegalArgumentException("argument can't be null");
        }
        for (E value : otherSet) {
            // if every value in other set is in this set, then contain all
            if (!this.contains(value)) {
                return false;
            }
        }
        return true;
    }
    
    /**
     * Determine if this set is equal to other.
     * Two sets are equal if they have exactly the same elements.
     * The order of the elements does not matter.
     * <br>pre: none
     * @param other the object to compare to this set 
     * @return true if other is a Set and has the same elements as this set
     */
    public boolean equals(Object other) {
        if (other instanceof ISet<?>) {
            ISet<?> otherSet = (ISet<?>) other;
            // if they both contain eachothers' elemetns, then equal
            if (size() != otherSet.size()) {
                return false;
            }
            // if size is same, check if contain all because no dups
            Iterator<?> iterator = otherSet.iterator();
            while (iterator.hasNext()) {
                // for each value in other set, see if there is a matching value in this set
                Object val = iterator.next();
                boolean hasFound = false;
                for (E data : this) {
                    if (data.equals(val)) {
                        hasFound = true;
                    }
                }
                // if we havent found it, then non matching elemtn
                if (!hasFound) {
                    return false;
                }
            }
            // if we never find a value that's not in both sets, then returns false
            return true;

        }
        return false;
    }
    
    /**
     * Return the number of elements of this set.
     * pre: none
     * @return the number of items in this set
     */
    public int size() {
        int size = 0;
        Iterator<E> iterator = this.iterator();
        // loops through this and counts ammt of elements
        while (iterator.hasNext()) {
            iterator.next();
            size++;
        }
        return size;
    }

    /**
     * Remove the specified item from this set if it is present.
     * pre: item != null
     * @param item the item to remove from the set. item may not equal null.
     * @return true if this set changed as a result of this operation, 
     * false otherwise
     */
    public boolean remove(E item) {
        // precon 
        if (item == null) {
            throw new IllegalArgumentException("Can't remove null ");
        }
        Iterator<E> iterator = this.iterator();
        while (iterator.hasNext()) {
            E value = iterator.next();
            // if we find value, remove it
            if (value.equals(item)) {
                iterator.remove();
                return true;
            }
        }
        return false;
    }

    /**
     * Create a new set that is the difference of this set and otherSet. 
     * Return an ISet of elements that are in this Set but not in otherSet. 
     * Also called the relative complement. 
     * <br>Example: If ISet A contains [X, Y, Z] and ISet B contains [W, Z] 
     * then A.difference(B) would return an ISet with elements [X, Y] while
     * B.difference(A) would return an ISet with elements [W]. 
     * <br>pre: otherSet != null
     * <br>post: returns a set that is the difference of this set and otherSet.
     * Neither this set or otherSet are altered as a result of this operation.
     * <br> pre: otherSet != null
     * @param otherSet != null
     * @return a set that is the difference of this set and otherSet
     */
    public ISet<E> union(ISet<E> otherSet) {
        // precon 
        if (otherSet == null) {
            throw new IllegalArgumentException("parameter can't be null");
        }
        ISet<E> union = this.difference(otherSet);
        union.addAll(otherSet);
        return union;
    }

    /**
     * Return a String version of this set. 
     * Format is (e1, e2, ... en)
     * @return A String version of this set.
     */
    public String toString() {
        StringBuilder result = new StringBuilder();
        String seperator = ", ";
        result.append("(");

        Iterator<E> it = this.iterator();
        while (it.hasNext()) {
            result.append(it.next());
            result.append(seperator);
        }
        // get rid of extra separator
        if (this.size() > 0) {
            result.setLength(result.length() - seperator.length());
        }

        result.append(")");
        return result.toString();
    }
}
