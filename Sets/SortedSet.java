
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
import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * In this implementation of the ISet interface the elements in the Set are
 * maintained in ascending order.
 *
 * The data type for E must be a type that implements Comparable.
 *
 * Implement methods that were not implemented in AbstractSet
 * and override methods that can be done more efficiently. An ArrayList must
 * be used as the internal storage container. For methods involving two set,
 * if that method can be done more efficiently if the other set is also a
 * SortedSet, then do so.
 */
public class SortedSet<E extends Comparable<? super E>> extends AbstractSet<E> {
    // QUESTIONS
    // should i make a binary search and then make a get helper to make add faster?
    // is my add all good or is there a better way to do it

    private ArrayList<E> myCon;

    /** order O(1)
     * create an empty SortedSet
     */
    public SortedSet() {
        myCon = new ArrayList<>();
    }

    /** Order O(N(log(N)))
     * Create a copy of other that is sorted.<br>
     * @param other != null
     */
    public SortedSet(ISet<E> other) {
        // precon
        if (other == null) {
            throw new IllegalArgumentException("other can not be null");
        }
        myCon = new ArrayList<>();
        // for each item we add, we are sorting.
        for (E value : other) {

            int index = get(value, 0, size());
            // get the index of where we shoudl add the value

            if (index <= 0) {
                // if index is negative, then we can add it and we add at that location.
                myCon.add(-index, value);
            }
        }
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
            throw new IllegalArgumentException("argument can't be null");
        }
        // finds if item is already there
        if (!contains(item)) {
            int index = get(item, 0, size());
            myCon.add(-index, item);
            return true;
        }
        return false;
    }
    /**
     * Remove the specified item from this set if it is present.
     * pre: item != null
     * @param item the item to remove from the set. item may not equal null.
     * @return true if this set changed as a result of this operation, 
     * false otherwise
     */
    public boolean remove(E item) {
        if (item == null) {
            throw new IllegalArgumentException("argument can't be null");
        }
        // looks for index of element
        if (size() > 0) {
            int index = get(item, 0, size());
            if (index >= 0) {
                // if elem exists, then removes it
                myCon.remove(index);
                return true;
            }
        }

        return false;
    }
    /** Order O(N)
     * A union operation. Add all items of otherSet that
     * are not already present in this set to this set.
     * @param otherSet != null
     * @return true if this set changed as a result of this operation,
     * false otherwise.
     */
    public boolean addAll(ISet<E> otherSet) {
        // precon 
        if (otherSet == null) {
            throw new IllegalArgumentException("argument cant be null");
        }
        ArrayList<E> newMyCon = new ArrayList<>();
        SortedSet<E> other;
        if (otherSet.getClass() != getClass()) {
            // if otherSet is sorted we can add all easier;
            other = new SortedSet<E>(otherSet);
        } else {
            other = (SortedSet<E>) otherSet;
        }
        ArrayList<E> otherCon = other.myCon;
        boolean changed = updateNewArrayList(newMyCon, 0, 0, otherCon);
        // if we changed the list, then we will update myCon
        if (changed) {
            myCon = newMyCon;
        }
        return changed;
    }

    /**
     * Fills an array making the union of two arrays without duplicates
     */
    private boolean updateNewArrayList(ArrayList<E> newMyCon, int index1, int index2,
                                       ArrayList<E> otherCon) {
        boolean changed = false;
        while (index1 < size() && index2 < otherCon.size()) {
            // compares elements in this and other & adds smaller element
            if (myCon.get(index1).compareTo(otherCon.get(index2)) < 0) {
                newMyCon.add(myCon.get(index1));
                index1++;
            } else if (myCon.get(index1).compareTo(otherCon.get(index2)) > 0) {
                newMyCon.add(otherCon.get(index2));
                // if we add something from the other array then we modified
                changed = true;
                index2++;
            } else {
                // if elements are equal, just add one of them
                newMyCon.add(myCon.get(index1));
                index2++;
                index1++;
            }
        }
        // add any remaining elements
        if (index1 < size()) {
            fillRest(newMyCon, index1, myCon);
        }
        else if (index2 < otherCon.size()) {
            fillRest(newMyCon, index2, otherCon);
            changed = true;
        }
        return changed;
    }

    /**
     * fills an array with the items from another array starting from an index
     */
    private void fillRest (ArrayList<E> newMyCon, int start, ArrayList<E> curr) {
        for (int i = start; i < curr.size(); i++) {
            newMyCon.add(curr.get(i));
        }
    }

    /** ORDER O(1)
     * Return the number of elements of this set.
     * pre: none
     * @return the number of items in this set
     */
    public int size() {
        return myCon.size();
    }

    /** ORDER O(N)
     * Make this set empty.
     * <br>pre: none
     * <br>post: size() = 0
     */
    public void clear() {
        myCon.clear();
    }
    /** ORDER O(LOG(n))
     * Determine if item is in this set. 
     * <br>pre: item != null
     * @param item element whose presence is being tested. 
     * Item may not equal null.
     * @return true if this set contains the specified item, false otherwise.
     */
    public boolean contains(E item) {
        // precon
        if (item == null) {
            throw new IllegalArgumentException("argument can't be null");
        }
        if (size() == 0) {
            return false;
        }
        return get(item, 0, size()) > 0 || myCon.getFirst().equals(item);
    }

    /** Order O(N)
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
        SortedSet<E> other = (otherSet.getClass() != getClass()) ? new SortedSet<E>(otherSet)
                : (SortedSet<E>) otherSet;
        ArrayList<E> otherCon = (other).myCon;
        int index = 0;
        int index2 = 0;
        // checks if the other set is larger than this, because then it cant contain all.
        if (otherSet.size() > size()) {
            return false;
        }
        while (index < size() && index2 < otherCon.size()) {
            if (otherCon.get(index2).compareTo(myCon.get(index)) > 0) {
                index++;
            } else if (otherCon.get(index2).compareTo(myCon.get(index)) < 0) {
                // if value is greater than the one we are looking for, then it doesent exist
                // becasue we already attempted smaller values in myCon
                return false;
            } else if (otherCon.get(index2).equals(myCon.get(index))) {
                // if values equal, then look at next
                index2++;
                index++;
            }
        }
        // we can only return true if we check through all values in otherCon
        return index2 == otherCon.size();

    }

    /** oRDER O(LOG(N))
     * Return the index of the specified item in this SortedSet.
     * returns -insert Index if the item is not found. where insertIndex is the index to place the 
     * item if it were to be added.
     * pre: the size of myCon is greater than 0
     **/
    private int get(E item, int start, int stop) {

        //base case
        if (start >= stop) {
            if (start == size()) {
                return -start;
            }
            return (myCon.get(start).equals(item)) ? start : -start;
        }
        int pos = (stop + start) / 2;
        if (myCon.get(pos).equals(item)) {
            return pos;
        }
        if (myCon.get(pos).compareTo(item) > 0) {
            return get(item, start, pos);
        } else {
            return get(item, pos + 1, stop);
        }
    }

    /** O(1)
     * Return the smallest element in this SortedSet.
     * <br> pre: size() != 0
     * @return the smallest element in this SortedSet.
     */
    public E min() {
        // precon
        if (size() == 0) {
            throw new IllegalArgumentException("Can't get min of an empty set");
        }

        return myCon.get(0);

    }

    /** O(1)
     * Return the largest element in this SortedSet.
     * <br> pre: size() != 0
     * @return the largest element in this SortedSet.
     */
    public E max() {
        // precon
        if (size() == 0) {
            throw new IllegalArgumentException("Can't get max of an empty set");
        }
        return myCon.get(size() - 1);
    }


    /** Order (O(N)) if both sorted, else O(n^2)
     * Determine if this set is equal to other.
     * Two sets are equal if they have exactly the same elements.
     * The order of the elements does not matter.
     * <br>pre: none
     * @param other the object to compare to this set 
     * @return true if other is a Set and has the same elements as this set
     */
    //TODO CheCK EFFICIENCYS
    public boolean equals(Object other) {
        if (other instanceof ISet<?>) {
            // two cases - its sorted set or its not
            if (other.getClass() == this.getClass()) {
                // if same class, then check storage containers
                if (size() == ((SortedSet<?>) other).size()) {
                    return myCon.equals(((SortedSet<?>) other).myCon);
                } else {
                    return false;
                }
            } else {
                // if not sorted set, then check manually using abstactSet code
                //TODO ASK ABOUT THISSSS!!! --> how to make mroe efficient
                return super.equals(other);
            }
        }
        return false;
    }

    /**
     * Create a new set that is the difference of this set and otherSet. 
     * Return an ISet of elements that are in this Set but not in otherSet. 
     * Also called the relative complement. 
     * <br>Example: If ISet A contains [X, Y, Z] and ISet B contains [W, Z] 
     * B.difference(A) would return an ISet with elements [W].
     * <br>pre: otherSet != null
     * <br>post: returns a set that is the difference of this set and otherSet.
     * Neither this set or otherSet are altered as a result of this operation.
     * <br> pre: otherSet != null
     * @param otherSet != null
     * @return a set that is the difference of this set and otherSet
     */
    public ISet<E> difference(ISet<E> otherSet) {
        if (otherSet == null) {
            throw new IllegalArgumentException("precondition can't be null");
        }
        // if current set is sorted, then use it, else, make a new sorted set

        SortedSet<E> other = (otherSet.getClass() != getClass()) ? new SortedSet<E>(otherSet)
                : (SortedSet<E>) otherSet;
        return updateDifference(0, 0, (other).myCon,
                new ArrayList<>());


    }


    // helper method that returns a set that is the difference of this set and otherSet
    private ISet<E> updateDifference(int index, int index2, ArrayList<E> otherCon,
                                     ArrayList<E> toReturn) {
        while (index < size() && index2 < otherCon.size()) {
            // if equal then skip both
            if (myCon.get(index).equals(otherCon.get(index2))) {
                index++;
                index2++;
            } else if (myCon.get(index).compareTo(otherCon.get(index2)) < 0) {
                // if the value in this is less, then there is no value in the other
                // becasue in order, so add this
                toReturn.add(myCon.get(index));
                index++;
            } else {
                index2++;
            }
        }
        // if there is anything left in either, then fill
        if (index < size()) {
            fillRest(toReturn, index, myCon);
        }
        SortedSet<E> result = new SortedSet<>();
        result.myCon = toReturn;
        return result;
    }






    /** ORDER O(N)
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
        if (otherSet == null) {
            throw new IllegalArgumentException("precondition can't be null");
        }
        // if current set is sorted, then use it, else, make a new sorted set

        SortedSet<E> other = (otherSet.getClass() != getClass()) ? new SortedSet<E>(otherSet)
                : (SortedSet<E>) otherSet;
        ArrayList<E> toReturn = new ArrayList<>();
        ArrayList<E> otherCon = ((SortedSet<E>) other).myCon;

        return updateIntersection(0, 0, otherCon, toReturn);

    }

    private ISet<E> updateIntersection(int index, int index2, ArrayList<E> otherCon,
                                       ArrayList<E> toReturn) {
        while (index < size() && index2 < otherCon.size()) {
            if (myCon.get(index).equals(otherCon.get(index2))) {
                toReturn.add(myCon.get(index));
                index++;
                index2++;
            } else if (myCon.get(index).compareTo(otherCon.get(index2)) < 0) {
                // if the value in other is greater, then look at next value in myCon
                index++;
            } else {
                // this value is not here, so lets move on and cehck the next value in otherCon
                index2++;
            }
        }
        SortedSet<E> result = new SortedSet<>();
        result.myCon = toReturn;
        return result;
    }

    /**
     * Return an Iterator object for the elements of this set.
     * pre: none
     * @return an Iterator object for the elements of this set
     */
    public Iterator<E> iterator() {
        return myCon.iterator();
    }

    /** ORDER O(N)
     * Create a new set that is the union of this set and otherSet.
     * <br>pre: otherSet != null
     * <br>post: returns a set that is the union of this set and otherSet.
     * Neither this set or otherSet are altered as a result of this operation.
     * <br> pre: otherSet != null
     * @param otherSet != null
     * @return a set that is the union of this set and otherSet
     */
    public ISet<E> union(ISet<E> otherSet) {
        if (otherSet == null) {
            throw new IllegalArgumentException("precondition can't be null");
        }
        // if current set is sorted, then use it, else, make a new sorted set
        SortedSet<E> other = (otherSet.getClass() != getClass()) ? new SortedSet<E>(otherSet)
                : (SortedSet<E>) otherSet;
        ArrayList<E> otherCon = ((SortedSet<E>) other).myCon;

        // if otherSet is sorted we can add all easier;
        ArrayList<E> toReturn = new ArrayList<>();
        updateNewArrayList(toReturn, 0, 0, otherCon);
        SortedSet<E> result = new SortedSet<>();
        result.myCon = toReturn;
        return result;

    }



}
