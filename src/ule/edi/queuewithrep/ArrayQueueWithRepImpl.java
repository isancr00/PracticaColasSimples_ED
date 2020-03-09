package ule.edi.queuewithrep;

import java.util.Iterator;
import java.util.NoSuchElementException;

import ule.edi.exceptions.EmptyCollectionException;

public class ArrayQueueWithRepImpl<T> implements QueueWithRep<T> {

	// atributos

	private final int capacityDefault = 10;

	ElemQueueWithRep<T>[] data;
	private int count;

	// Clase interna 

	@SuppressWarnings("hiding")
	public class ElemQueueWithRep<T> {
		T elem;
		int num;
		public ElemQueueWithRep (T elem, int num){
			this.elem=elem;
			this.num=num;
		}
	}


	///// ITERADOR //////////
	@SuppressWarnings("hiding")
	public class ArrayQueueWithRepIterator<T> implements Iterator<T> {

		private int contador;
		private int current;
		private T[] items;

		public ArrayQueueWithRepIterator(ElemQueueWithRep<T>[] cola, int count){
			this.contador = count;
			this.items = (T[]) cola;
			this.current = 0;

		}

		@Override
		public boolean hasNext() {
			return (current < contador);			

		}

		@Override
		public T next() {
			if(!hasNext()) {
				throw new NoSuchElementException();
			}

			current++;
			return items[current-1];

		}



	}
	////// FIN ITERATOR


	// Constructores

	@SuppressWarnings("unchecked")
	public ArrayQueueWithRepImpl() {
		data =   new ElemQueueWithRep[capacityDefault];
		count=0;
	}

	@SuppressWarnings("unchecked")
	public ArrayQueueWithRepImpl(int capacity) {
		data =  new ElemQueueWithRep[capacity];
		count=0;
	}


	@SuppressWarnings("unchecked")
	private void expandCapacity() {

		ElemQueueWithRep<T>[] nuevo= (ElemQueueWithRep<T>[]) new ElemQueueWithRep[data.length*2];

		// todo

		for(int index=0;index<this.data.length;index++) {
			nuevo[index] = data[index];
		}

		data = nuevo;

	}


	@Override
	public void add(T element, int times) {
		// TODO
		
		if(!(contains(element))) {
			if(size() == data.length) {
				expandCapacity();
			}
			
			ElemQueueWithRep<T> nuevo = new ElemQueueWithRep<T>(element,times);
			data[count] = nuevo;
			data[count].num = times;
			count++;
		}else {
			for(int i=0;i<count;i++) {
				if(data[i].elem.equals(element)) {
					data[i].num = times;
				}
			}
			
		}
		
	}



	@Override
	public void add(T element) {
		// TODO 
		if(!(contains(element))) {
			if(size() == data.length) {
				expandCapacity();
			}
			
			ElemQueueWithRep<T> nuevo = new ElemQueueWithRep<T>(element, 1);
			data[count] = nuevo;
			count++;
			
		}else {
			for(int i=0;i<count;i++) {
				if(data[i].elem.equals(element)) {
					data[i].num += 1;
				}
			}
		}		
	}

	@Override
	public void remove(T element, int times) {
		//todo
		
		
	}

	@Override
	public int remove() throws EmptyCollectionException {
		//todo
		
		
	}

	@Override
	public void clear() {
		// TODO 

	}


	@Override
	public boolean contains(T element) {
		// TODO 


	}

	@Override
	public boolean isEmpty() {
		// TODO 
		return (count == 0);

	}

	@Override
	public long size() {
		// TODO 
		
		return this.count;

	}

	@Override
	public int count(T element) {
		// TODO 

	}

	@Override
	public Iterator<T> iterator() {
		// TODO 

	}

	@Override
	public String toString() {

		final StringBuffer buffer = new StringBuffer();

		buffer.append("(");

		// TODO Ir añadiendo en buffer las cadenas para la representación de la cola. Ejemplo: (A, A, A, B )

		buffer.append(")");

		return buffer.toString();
	}


}
