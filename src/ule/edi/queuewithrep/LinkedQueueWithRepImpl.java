package ule.edi.queuewithrep;

import java.util.Iterator;
import java.util.NoSuchElementException;

import ule.edi.exceptions.EmptyCollectionException;


public class LinkedQueueWithRepImpl<T> implements QueueWithRep<T> {

	// Atributos
	private QueueWithRepNode<T> front;
	int count;


	// Clase interna
	@SuppressWarnings("hiding")
	public class QueueWithRepNode<T> {
		T elem;
		int num;
		QueueWithRepNode<T> next;

		public QueueWithRepNode (T elem, int num){
			this.elem=elem;
			this.num=num;
		}

	}

	///// ITERADOR //////////
	@SuppressWarnings("hiding")
	public class LinkedQueueWithRepIterator<T> implements Iterator<T> {


		private QueueWithRepNode<T> current;

		public LinkedQueueWithRepIterator(QueueWithRepNode<T> nodo) {
			this.current = nodo;
		}

		@Override
		public boolean hasNext() {
			return (current != null);
		}

		@Override
		public T next() {

			if(!hasNext()) {
				throw new NoSuchElementException();
			}

			T result = current.elem;

			current = current.next;

			return result;

		}



	}
	////// FIN ITERATOR

	public LinkedQueueWithRepImpl() {
		this.count = 0;
		this.front = null;
	}

	/////////////
	@Override
	public void add(T element) {
		//todo

		if(element == null) {
			throw new NullPointerException();
		}else{
			if(!contains(element)) {
				QueueWithRepNode<T> node = new QueueWithRepNode<T>(element, 1);
				node.next = this.front;
				this.front = node;
				count++;				
			}else {
				QueueWithRepNode<T> aux = this.front;

				while(aux != null) {
					if(aux.elem.equals(element)) {
						aux.num +=1;
					}
					aux = aux.next;
				}
			}

		}


	}

	@Override
	public void add(T element, int times) {
		//todo
		if(element == null) {
			throw new NullPointerException();
		}else if(times < 0) {
			throw new IllegalArgumentException();
		}else {
			if(!contains(element)) {
				QueueWithRepNode<T> node = new QueueWithRepNode<T>(element, times);
				node.next = this.front;
				this.front = node;
				count++;
			}else {

				QueueWithRepNode<T> aux = this.front;

				while(aux != null) {
					if(aux.elem.equals(element)) {
						aux.num +=times;
					}
					aux = aux.next;
				}
			}
		}

	}


	@Override
	public void remove(T element, int times) {
		//todo

		QueueWithRepNode<T> aux = this.front;

		if(element == null) {
			throw new NullPointerException();
		}else if(times<0){
			throw new IllegalArgumentException();
		}else if(times == 0) {
			return;

		}else {
			if(!contains(element)) {
				throw new NoSuchElementException();
			}else {
				while(aux != null) {
					if(aux.elem.equals(element)) {
						if(times >= aux.num) {
							throw new IllegalArgumentException();
						}else {
							aux.num -= times;
						}
					}

					aux = aux.next;
				}
			}
		}


	}


	@Override
	public boolean contains(T element) {
		//todo

		QueueWithRepNode<T> aux = this.front;

		if(element == null) {
			throw new NullPointerException();
		}else if(isEmpty()) {
			return false;
		}else {

			while(aux != null) {
				if(aux.elem.equals(element)) {
					return true;
				}
				aux = aux.next;
			}
		}

		return false;

	}

	@Override
	public long size() {
		//todo

		QueueWithRepNode<T> aux = this.front;
		long tamanio = 0;

		while(aux != null) {

			tamanio += aux.num;
			aux = aux.next;
		}

		return tamanio;


	}

	@Override
	public boolean isEmpty() {
		//todo

		return count == 0;


	}

	@Override
	public int remove() throws EmptyCollectionException {
		//todo
		int numero=0;

		if(this.front != null) {
			numero = this.front.num;

		}

		if(isEmpty()) {
			throw new EmptyCollectionException("LinkedQueue");			
		}else {

			this.front.num = 0;
			this.front.elem = null;

			this.front = this.front.next;
		}
		return numero;


	}

	@Override
	public void clear() {
		//todo

		QueueWithRepNode<T> aux = this.front;

		while(aux != null) {
			aux.num = 0;
			aux.elem = null;
			aux = aux.next;

		}

	}

	@Override
	public int count(T element) {
		//todo

		QueueWithRepNode<T> aux = this.front;
		int numero = 0;

		while(aux != null) {
			if(aux.elem.equals(element)) {
				numero = aux.num;
			}
			aux = aux.next;
		}

		return numero;


	}

	@Override
	public Iterator<T> iterator() {
		// TODO 

		return new LinkedQueueWithRepIterator<T>(front);
	}


	@Override
	public String toString() {

		StringBuffer buffer = new StringBuffer();
		QueueWithRepNode<T> aux = this.front;

		buffer.append("(");

		// TODO Ir añadiendo en buffer las cadenas para la representación de la cola. Ejemplo: (A, A, A, B )
		while(aux != null) {
			for(int i = 0;i<aux.num;i++) {
				buffer.append(aux.elem);
				buffer.append(" ");
			}
			aux = aux.next;
		}

		buffer.append(")");
		return buffer.toString();
	}




}
