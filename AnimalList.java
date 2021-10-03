package P4;

import java.io.Serializable;
import java.util.Iterator;

public class AnimalList implements Iterable<Animal>, Serializable{
	private static final long serialVersionUID = 1L;
	private AnimalNode<Animal> head, tail;
	private int size;
	
	public int size() {
		return size;
	}
	public boolean isEmpty() {
		return size == 0;
	}
	public void addFirst(Animal animal) {
		AnimalNode<Animal> n = new AnimalNode<>(animal);
		if(isEmpty()) {
			head = tail = n;
		}
		else {
			n.next = head;
			head =n;
		}
		size++;
	}
	public void addLast(Animal animal) {
		AnimalNode<Animal> n = new AnimalNode<>(animal);
		if(isEmpty()){
			head = tail = n;
		}
		else {
			tail.next = n;
			tail =n;
		}
		size++;
	}
	public void add(int index, Animal animal) {
		getIndex(index);
		if(index == 0) {
			addFirst(animal);
		}
		else if (index == size)
			addLast(animal);
		else {
			AnimalNode<Animal> n = new AnimalNode<>(animal);
			AnimalNode<Animal> previous = head;
			for(int i =0; i <index-1; i++)
				previous = previous.next;
			n.next = previous.next;
			previous.next = n;
			size++;
		}
	}
	public Animal removeFirst() {
		if(head == null)
			return null;
		else {
			AnimalNode<Animal> temp = head;
			head = head.next;
			if(head == null) tail = null;
			size--;
			return temp.element;
		}
		
	}
	public Animal removeLast() {
		if(tail == null)
			return null;
		else if(size ==1){
			return removeFirst();
		}
		else {
			AnimalNode<Animal> temp = tail;
			AnimalNode<Animal> prev = head;
			for(int i = 0; i <size -2; i++)
				prev = prev.next;
			tail = prev;
			tail.next = null;
			size--;
			return temp.element;
		}
			
	}
	public Animal remove(int index) {
		getIndex(index);
		if(index ==0) return removeFirst();
		else if(index == size-1)return removeLast();
		else {
			AnimalNode<Animal> prev = head;
			for(int i =0; i <index-1; i++)
				prev = prev.next;
			AnimalNode<Animal> current = prev.next;
			prev.next = current.next;
			size--;
			return current.element;
		}
	}
	public Animal getFirst() {
		if(size ==0)
			return null;
		else 
			return head.element;
	}
	public Animal getLast() {
		if(size == 0)
			return null;
		else
			return tail.element;
	}
	public Animal get(int index) {
		getIndex(index);
		if(index == 0)
			return getFirst();
		else if (index == size-1) return getLast();
		else {
			AnimalNode<Animal> curr = head;
			for(int i = 0; i <index; i++)
				curr = curr.next;
			return curr.element;
		}
	}
	public Animal set(int index, Animal animal) {
		getIndex(index);
		add(index, animal);
		return remove(index+1);
		
	}
	private void getIndex(int index) {
		if(index < 0 || index >= size) {
			throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
		}
	}
	@Override
	public String toString() {
		AnimalNode<Animal> anim = head;
		String printer  = "";
		while(anim != null) {
			printer += String.format("%s  : %s at (%.1f,%.1f) Energy = %.1f\n", anim.element.getName(),anim.element.isAlive()? "alive":"dead", anim.element.getX(), anim.element.getY(), anim.element.getEnergy());
				anim = anim.next;	
		}
		return printer;
			
	}
	public Iterator<Animal> iterator() {
		return new MyIterator();
	}
	class MyIterator implements Iterator<Animal>{
		private AnimalNode<Animal> curr;
		public MyIterator() {
			curr = head;
		}
		public boolean hasNext() {
			return (curr != null);
		}

		@Override
		public Animal next() {
			Animal temp = curr.element;
			curr = curr.next;
			return temp;
		}
	}
	public AnimalList getHungryAnimals() {
		AnimalList list = new AnimalList();
		AnimalNode<Animal> anim = head;
		while(anim != null) {
			if(anim.element.getEnergy() < 50) {
				list.addLast(anim.element);
			}
			
			anim = anim.next;
		}
		if(list.head == null) 
			System.out.println("There are no hungry animals");
		return list;
	}
	public AnimalList getStarvingAnimals() {
		AnimalNode<Animal> anim = head;
		AnimalList list = new AnimalList();
		while(anim != null) {
			if(anim.element.getEnergy() < 17){
				list.addLast(anim.element);
			}
			anim = anim.next;
		}
		if(list.head == null) 
			System.out.println("There are no Starving Animals");
		return list;
	} 
	public AnimalList getAnimalsInBarn() {
		AnimalNode<Animal> anim = head;
		AnimalList list = new AnimalList();
		while(anim != null) {
			double x = anim.element.getX();
			double y = anim.element.getY();
			if(x >= 450 && x <= 550 && y >= 50 && y <= 150) {
				list.addLast(anim.element);
			}
			anim = anim.next;
		}
		if(list.head == null) 
			System.out.println("There are no animals in the Barn");
		
		return list;
		
		
	}
	public double getRequiredFood() {
		AnimalNode<Animal> anim =head;
		double total = 0;
		while(anim != null) {
			total += (100 - anim.element.getEnergy());
			anim = anim.next;
		}
		return total;
	}
	public AnimalList getByType(Class<Animal> anim) {
		AnimalNode<Animal> node = head;
		AnimalList list = new AnimalList();
		while(node != null) {
			if(anim.isAssignableFrom(node.getClass())) {
				list.addLast(node.element);
			}
		}
		return list;
	}
		
}
@SuppressWarnings("hiding")
class AnimalNode<Animal> implements Serializable{
	private static final long serialVersionUID = 1L;
	Animal element;
	AnimalNode<Animal> next;
	public AnimalNode(Animal element) {
		this.element = element;
	}
	
}
