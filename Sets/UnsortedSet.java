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
import java.util.ArrayList;

/**
 * A simple implementation of an ISet. 
 * Elements are not in any particular order.
 * Students are to implement methods that 
 * were not implemented in AbstractSet and override
 * methods that can be done more efficiently. 
 * An ArrayList must be used as the internal storage container.
 *
 */
public class UnsortedSet<E> extends AbstractSet<E> {

    // QUESTIONS
    // Should I redo equals?
    private ArrayList<E> myCon;

    // creates a new unsorted set objection 
    // sets the container to an empyt arraylist of default capacity
    public UnsortedSet() {
        myCon = new ArrayList<E>();
    }
    
    /** Order O(N)
     * Add an item to this set.
     * <br> item != null
     * @param item the item to be added to this set. item may not equal null.
     * @return true if this set changed as a result of this operation, 
     * false otherwise.
     */
    public boolean add(E item) {
        // precon 
        if (item == null) {
            throw new IllegalArgumentException("argumetn can't be null");
        }
        if (!contains(item)) {
            myCon.add(item);
            return true;
        }
        return false;
    }
    
    /** O(1)
     * Return the number of elements of this set.
     * pre: none
     * @return the number of items in this set
     */
    public int size() {
        return myCon.size();
    }
    
    /** Order O(1)
     * Return an Iterator object for the elements of this set.
     * pre: none
     * @return an Iterator object for the elements of this set
     */
    public Iterator<E> iterator() {
        return myCon.iterator();
    }

    /** Order O(N)
     * Make this set empty.
     * <br>pre: none
     * <br>post: size() = 0
     */
    public void clear() {
        myCon.clear();
    }

    /** Order O(N^2)
     * Create a new set that is the union of this set and otherSet.
     * <br>pre: otherSet != null
     * <br>post: returns a set that is the union of this set and otherSet.
     * Neither this set or otherSet are altered as a result of this operation.
     * <br> pre: otherSet != null
     * @param otherSet != null
     * @return a set that is the union of this set and otherSet
     */
    public ISet<E> union(ISet<E> otherSet) {
        // precon 
        if (otherSet == null) {
            throw new IllegalArgumentException("parameter can't be null");
        }
        ISet<E> union = new UnsortedSet<E>();
        // add all values from each set
        union.addAll(this);
        union.addAll(otherSet);
        return union;
    }

    /** Order O(N^2)
     * create a new set that is the intersection of this set and otherSet.
     * <br>pre: otherSet != null<br>
     * <br>post: returns a set that is the intersection of this set 
     * and otherSet.
     * Neither this set or otherSet are altered as a result of this operation.
     * <br> pre: otherSet != null
     * @param otherSet != null
     * @return a set that is the intersection of this set and otherSet
     */
    public ISet<E> intersection(ISet<E> otherSet) {
        // precon 
        if (otherSet == null) {
            throw new IllegalArgumentException("parameter can't be null");
        }
        ISet<E> intersection = new UnsortedSet<E>();
        ISet<E> smaller = (size() < otherSet.size()) ? this : otherSet;
        // looop through smaller set and see what values both sets have in common
        for (E value : smaller) {
            if (otherSet.contains(value)) {
                intersection.add(value);
            }
        }
        return intersection;
    }

    /** order O(N^2)
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
    public ISet<E> difference(ISet<E> otherSet) {
        // precon 
        if (otherSet == null) {
            throw new IllegalArgumentException("parameter can't be null");
        }
        ISet<E> difference = new UnsortedSet<E>();
        // add all values from this set that not in otherSet
        for (E value : this) {
            if (!otherSet.contains(value)) {
                difference.add(value);
            }
        }
        return difference;
    }

}
