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
		private ElemQueueWithRep<T>[] items;

		public ArrayQueueWithRepIterator(ElemQueueWithRep<T>[] cola, int count){
			this.contador = count;
			this.items = cola;
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
			return items[current-1].elem;

		}



	}
	////// FIN ITERATOR


	// Constructores

	@SuppressWarnings("unchecked")
	public ArrayQueueWithRepImpl() {
		this.data = new ElemQueueWithRep[capacityDefault];
		this.count=0;
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

		for(int index=0;index<this.count;index++) {
			nuevo[index] = data[index];
		}

		data = nuevo;

	}


	@Override
	public void add(T element, int times) {
		// TODO

		if(element == null) {
			throw new NullPointerException();
		}else if (times < 0){
			throw new IllegalArgumentException();
		}else {
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
						data[i].num += times;
					}
				}

			}

		}
	}



	@Override
	public void add(T element) {
		// TODO 

		if(element == null) {
			throw new NullPointerException();
		}else {
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

	}

	@Override
	public void remove(T element, int times) {
		//todo
		
		if(element == null) {
			throw new NullPointerException();
		}else if(times == 0) {
			return;
		}else if(times < 0) {
			throw new IllegalArgumentException();
		}else if(!(contains(element))) {
			throw new NoSuchElementException();
		}else {
			for(int i=0;i<count;i++) {
				if(element.equals(data[i].elem)) {
					if(times == data[i].num) {
						throw new IllegalArgumentException();
					}else {
						data[i].num -= times;
					}
				}
			}	
			
		}


	}

	@Override
	public int remove() throws EmptyCollectionException {
		//todo
		
		int numero=0;
		if(data[0]!=null) {
			numero = data[0].num;
		}
		
		if(isEmpty()) {
			throw new EmptyCollectionException("ArrayQueueWithRep");
		}else {
			data[0].num = 0;
			data[0] = null;
			
			count--;
		}
		
		return numero;


	}

	@Override
	public void clear() {
		// TODO 
		
		for(int i=0;i<this.count;i++) {
			this.data[i].elem = null;
			this.data[i].num = 0;
			count--;
		}

	}


	@Override
	public boolean contains(T element) {
		// TODO

		if(element == null) {
			throw new NullPointerException();
		}else if(count == 0){
			return false;
		}else {
			for(int i=0;i<this.count;i++) {
				if(this.data[i].elem.equals(element)) {
					return true;
				}
			}
		}


		return false;


	}

	@Override
	public boolean isEmpty() {
		// TODO 
		return count == 0;

	}

	@Override
	public long size() {
		// TODO 
		
		int contador = 0;
		
		for(int i=0;i<count;i++) {
			contador += data[i].num;
		}

		return contador;

	}

	@Override
	public int count(T element) {
		// TODO 
		
		if(contains(element)) {
			for(int i=0;i<count;i++){
				if(data[i].elem.equals(element)) {
					return data[i].num;
				}
			}
		}
		
		return 0;

	}

	@Override
	public Iterator<T> iterator() {
		// TODO 
		
		return new ArrayQueueWithRepIterator<T>(data,(int)size());
		
		
	}

	@Override
	public String toString() {

		final StringBuffer buffer = new StringBuffer();

		buffer.append("(");

		// TODO Ir añadiendo en buffer las cadenas para la representación de la cola. Ejemplo: (A, A, A, B )
		
		for(int i=0;i<count;i++) {
			for(int j=0;j<data[i].num;j++) {
				buffer.append(data[i].elem);
				buffer.append(" ");
			}
			
		}

		buffer.append(")");

		return buffer.toString();
	}


}
