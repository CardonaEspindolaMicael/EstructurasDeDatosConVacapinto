package bo.edu.uagrm.ficct.inf310sb.arboles;


import java.util.*;

public class ArbolBinarioBusqueda<K extends Comparable<K>,V> implements


    IArbolBusqueda<K, V> {
	protected NodoBinario<K,V> raiz;
	


	public void vaciar() {
		
		this.raiz= (NodoBinario<K, V>)NodoBinario.nodoVacio();
		
	}


	public boolean esArbolVacio() {
		
		return NodoBinario.esNodoVacio(this.raiz);
	}


	public int size() {

		
		if(esArbolVacio()) {
		return 0;
                
		}
            
		
		int cantidadDeNodos=0;
		Stack<NodoBinario<K,V>> pilaDeNodos= new Stack<>();
		pilaDeNodos.push(this.raiz);
		
		while(!pilaDeNodos.isEmpty()) {
			
			NodoBinario<K, V> nodoActual= pilaDeNodos.pop();
			cantidadDeNodos++;
		
			if(!nodoActual.esVacioHijoDerecho()) {
				pilaDeNodos.push(nodoActual.getHijoDerecho());
			}
			
			if(!nodoActual.esVacioHijoIzquierdo()) {
				pilaDeNodos.push(nodoActual.getHijoIzquierdo());
			}
			
		}
			return cantidadDeNodos ;
		}
	

        @Override
	public int altura() {
		// TODO Auto-generated method stub
		
		return altura(this.raiz);
	}

	protected int altura(NodoBinario<K, V> nodoActual) {
		// TODO Auto-generated method stub
		if(NodoBinario.esNodoVacio(nodoActual)) {
		return 0;
		}
		int alturaPorIzquierda=altura(nodoActual.getHijoIzquierdo());
		int alturaPorDerecha= altura(nodoActual.getHijoDerecho());
		if(alturaPorIzquierda>alturaPorDerecha) {
			return alturaPorIzquierda+1;
		}
		return alturaPorDerecha+1;
	}


	public int nivel() {
		// TODO Auto-generated method stub
		return 0;
	}


	public K minimo() {
		
	if (this.esArbolVacio()) {
    return null;
	}
			 NodoBinario<K, V> nodoActual=this.raiz;
			 NodoBinario<K, V> nodoAnterior= (NodoBinario<K, V>) NodoBinario.nodoVacio();
			 while(!NodoBinario.esNodoVacio(nodoActual)) {
				 nodoAnterior=nodoActual;
				 nodoActual.getHijoIzquierdo();
			 }
			 return nodoAnterior.getClave();
	}


	public K maximo() {
		if (this.esArbolVacio()) {
		    return null;
			}
					 NodoBinario<K, V> nodoActual=this.raiz;
					 NodoBinario<K, V> nodoAnterior= (NodoBinario<K, V>) NodoBinario.nodoVacio();
					 while(!NodoBinario.esNodoVacio(nodoActual)) {
						 nodoAnterior=nodoActual;
						 nodoActual.getHijoDerecho();
					 }
					 return nodoAnterior.getClave();
	}


	public void insertar(K claveAInsertar, V valorAInsertar)
	{
		if (claveAInsertar==null) {
			throw new IllegalArgumentException("Pero que haces boludo? La clave no puede ser nula");	
			}
		if (valorAInsertar==null) {
			throw new IllegalArgumentException("Oye amigo vienes de tontolandia? El valor no puede ser nulo");
		}
		
		if (this.esArbolVacio()) {
			this.raiz= new NodoBinario<>(claveAInsertar,valorAInsertar);
			return;
		}
		
	NodoBinario<K, V> nodoActual=this.raiz;
	NodoBinario<K, V> nodoAnterior= (NodoBinario<K, V>) NodoBinario.nodoVacio();
	
	while(!NodoBinario.esNodoVacio(nodoActual)) {
		
		K claveActual=nodoActual.getClave();
		
		nodoAnterior=nodoActual; 
		if (claveAInsertar.compareTo(claveActual)<0) {
			nodoActual=nodoActual.getHijoIzquierdo();
		} else if(claveAInsertar.compareTo(claveActual)>0) {
			nodoActual=nodoActual.getHijoDerecho();
	
		} else {
          nodoActual.setValor(valorAInsertar);
          return;
		}
	}
	
		
		
		NodoBinario<K, V> nuevoNodo= new NodoBinario<>(claveAInsertar,valorAInsertar);
		K ClavePadre=nodoAnterior.getClave();
		if(claveAInsertar.compareTo(ClavePadre)<0) {
			nodoAnterior.setHijoIzquierdo(nuevoNodo);;
		} else {
			nodoAnterior.setHijoDerecho(nuevoNodo);
		}
		
	
		
		}
	

	public V buscar(K claveABuscar) {
	
		if(claveABuscar==null) {
			throw new IllegalArgumentException("La clave no puede ser nula");
		}
		
		if(this.esArbolVacio()) {
			return null;
		}
		NodoBinario<K, V> nuevoNodo=this.raiz;
		
		while(!NodoBinario.esNodoVacio(nuevoNodo)) {
			K ClaveActual=nuevoNodo.getClave();
			if(claveABuscar.compareTo(ClaveActual)==0) {
				return nuevoNodo.getValor();
				
			}else if(claveABuscar.compareTo(ClaveActual)>0) {
				nuevoNodo=nuevoNodo.getHijoDerecho();
			} else {
				nuevoNodo=nuevoNodo.getHijoIzquierdo();
			}
		}
		return null;
	}

        public List<K> obtenerClavesDadoUnNivelDado(int nivelObjetivo){
            List<K> recorrido= new ArrayList<>();
            if(this.esArbolVacio()){
                return recorrido;
            }
            Queue<NodoBinario<K,V>> colaDeNodos= new LinkedList<>();
            colaDeNodos.offer(this.raiz);
            int nivelActual=0;
            while(!colaDeNodos.isEmpty()){
                int tamañoDeLaCola=colaDeNodos.size();
                int controlaLaCola=0;
                while(controlaLaCola<tamañoDeLaCola){
                                     
                    NodoBinario<K,V> nodoActual= colaDeNodos.poll();
                    if(nivelActual==nivelObjetivo){
                    recorrido.add(nodoActual.getClave());
                    }
                    if(!nodoActual.esVacioHijoIzquierdo()){
                        colaDeNodos.offer(nodoActual.getHijoIzquierdo());                
                    }
                    if(!nodoActual.esVacioHijoDerecho()){
                        colaDeNodos.offer(nodoActual.getHijoDerecho());
                    }
                 controlaLaCola++;   
                }
                nivelActual++;                
            }
            return recorrido;             
        }

	public V Eliminar(K claveAEliminar) {
		
		   if(esArbolVacio()) {
			   return null;
		   }
		   
		   if(buscar(claveAEliminar)==null) {
			   return null;
		   }
		   
		  return hallarNodoPadre(claveAEliminar);
	}

	private V hallarNodoPadre(K claveAEliminar) {
		return null;
		  }

	private void casosPorLaIzquierda(NodoBinario<K, V> nodoPadre) {
		
	}

	private void casoPorDerecha(NodoBinario<K, V> nodoPadre) {
		
	}
	
	
	public boolean contiene(K clave) {
		
	return this.buscar(clave)!=null;
		
		
	}


	public List<K> recorridoEnInOrdenRec() {
		List<K> recorrido = new ArrayList<>();
		recorridoEnInOrdenRec(this.raiz, recorrido);
		return recorrido;
	}

	private void recorridoEnInOrdenRec(NodoBinario<K, V> nodoActual, List<K> recorrido) {
		// TODO Auto-generated method stub
		if(NodoBinario.esNodoVacio(nodoActual)){
			return;
		}
		recorridoEnInOrdenRec(nodoActual.getHijoIzquierdo(),recorrido);
		recorrido.add(nodoActual.getClave());
		recorridoEnInOrdenRec(nodoActual.getHijoDerecho(),recorrido);
		
	}


	public List<K> recorridoEnPreOrden() {
		// TODO Auto-generated method stub
		
	List<K> recorrido= new ArrayList<>();
	
	Stack<NodoBinario<K,V>> pilaDeNodos= new Stack<>();
	pilaDeNodos.push(this.raiz);
	
	while(!pilaDeNodos.isEmpty()) {
		
		NodoBinario<K, V> nodoActual= pilaDeNodos.pop();
		recorrido.add(nodoActual.getClave());
		if(!nodoActual.esVacioHijoDerecho()) {
			pilaDeNodos.push(nodoActual.getHijoDerecho());
		}
		
		if(!nodoActual.esVacioHijoIzquierdo()) {
			pilaDeNodos.push(nodoActual.getHijoIzquierdo());
		}
		
	}
		return recorrido;
	}

	 @Override
	public List<K> recorridoEnPostOrden() {
		// IDR
               List<K> recorrido= new ArrayList<>();
               Stack<NodoBinario<K,V>> pilaDeNodos= new Stack<>();
               NodoBinario<K,V> nodoActual= this.raiz;
               meterPilaParaPostOrden(pilaDeNodos, nodoActual);
               while(!pilaDeNodos.isEmpty()){
                 nodoActual = pilaDeNodos.pop();
                 recorrido.add(nodoActual.getClave());
                 if(!pilaDeNodos.isEmpty()){
                    NodoBinario<K,V> nodoTope=pilaDeNodos.peek();
                    
                    if(nodoTope.getHijoDerecho()!=nodoActual && !nodoTope.esVacioHijoDerecho()){
                        meterPilaParaPostOrden(pilaDeNodos, nodoTope.getHijoDerecho());
                    }
                     
                 }

               }
               
               return recorrido;
	}

	private void meterPilaParaPostOrden(Stack<NodoBinario<K, V>> pilaDeNodos, NodoBinario<K, V> nodoActual) {
		
            while(!NodoBinario.esNodoVacio(nodoActual)){
                pilaDeNodos.push(nodoActual);
                if(!nodoActual.esVacioHijoIzquierdo()){
                    nodoActual=nodoActual.getHijoIzquierdo();
                }else{
                    nodoActual=nodoActual.getHijoDerecho();
                }
            }
			
			
		
	}

	@Override
	public List<K> recorridoPorNiveles() {
		// TODO Auto-generated method stub
	List<K> recorrido= new ArrayList();
	
	Queue<NodoBinario<K,V>> colaDeNodos = new LinkedList<>();
	colaDeNodos.offer(this.raiz);
	
	while (!colaDeNodos.isEmpty()) {
		NodoBinario< K, V> nodoActual=colaDeNodos.poll();
		recorrido.add(nodoActual.getClave());
		if(!nodoActual.esVacioHijoIzquierdo()) {
			
			colaDeNodos.offer(nodoActual.getHijoIzquierdo());
		}
		
		if(!nodoActual.esVacioHijoDerecho()) {
			colaDeNodos.offer(nodoActual.getHijoDerecho());
		}	
	}
	
	
	
		return recorrido;
	}

	@Override
	public List<K> recorridoEnInOrden() {
		// TODO Auto-generated method stub
		List<K> recorrido= new ArrayList<>();
		
		Stack<NodoBinario<K, V>> pilaDeNodos= new Stack<>();
	    NodoBinario<K, V> nodoActual=this.raiz;
	    
		recorrerPilaIzq(pilaDeNodos, nodoActual);
		
		while(!pilaDeNodos.isEmpty()) {
			nodoActual=pilaDeNodos.pop();
			recorrido.add(nodoActual.getClave());
	        if(!nodoActual.esVacioHijoDerecho()) {
	        	recorrerPilaIzq(pilaDeNodos, nodoActual.getHijoDerecho());
	        }
	}
return recorrido;

}

	private void recorrerPilaIzq(Stack<NodoBinario<K, V>> pilaDeNodos, NodoBinario<K, V> nodoActual) {
		while(!NodoBinario.esNodoVacio(nodoActual)) {
			pilaDeNodos.push(nodoActual);
			nodoActual=nodoActual.getHijoIzquierdo();
		}
	}

	@Override
	public List<K> recorridoEnPreOrdenRec() {
		List<K>recorrido=new ArrayList<>();
		
		
		recorridoEnPreoOrdenRec(this.raiz,recorrido );
		return recorrido;
	}

	private void recorridoEnPreoOrdenRec(NodoBinario<K, V> nodoActual, List<K> recorrido) {
		// TODO Auto-generated method stub
		if(NodoBinario.esNodoVacio(nodoActual)) {
			return;		
		}
		recorrido.add(nodoActual.getClave());
		recorridoEnPreoOrdenRec(nodoActual.getHijoIzquierdo(), recorrido);
		recorridoEnPreoOrdenRec(nodoActual.getHijoDerecho(), recorrido); 
		
	}

	@Override
	public int cantidadDeHijosDerechos() {
		return cantidadDeHijosDerechos(this.raiz);
	}

	private int cantidadDeHijosDerechos(NodoBinario<K, V> nodoActual) {
		// TODO Auto-generated method stub
		if(NodoBinario.esNodoVacio(nodoActual)) {
			return 0;
		}
		int nodoPorlaRamaIzquierda=cantidadDeHijosDerechos(nodoActual.getHijoIzquierdo());
		int nodoPorlaRamaDerecha=cantidadDeHijosDerechos(nodoActual.getHijoDerecho());
		if (!nodoActual.esVacioHijoDerecho()) {
			return nodoPorlaRamaIzquierda + nodoPorlaRamaDerecha+1;
			
		}
		return nodoPorlaRamaIzquierda + nodoPorlaRamaDerecha;
	
	}

	@Override
	public int cantidadDeHijosConNodoCompleto() {
	
		Queue<NodoBinario<K,V>> colaDeNodos= new LinkedList<>();
		colaDeNodos.offer(this.raiz);
		int i=0;
		
		while(!colaDeNodos.isEmpty()) {
			NodoBinario<K, V> nodoActual=colaDeNodos.poll();
			if(!nodoActual.esVacioHijoIzquierdo()) {
				colaDeNodos.offer(nodoActual.getHijoIzquierdo());
			}
			if(!nodoActual.esVacioHijoDerecho()) {
				colaDeNodos.offer(nodoActual.getHijoDerecho());
			}
			if(!nodoActual.esVacioHijoDerecho() && !nodoActual.esVacioHijoIzquierdo()) {
				i++;
			}
		}
		return i;
	}

	@Override
	public boolean tieneNodosCompletosEnNivel(int nivelObjetivo) {
	
		return tieneNodosCompletosEnNivel(this.raiz, nivelObjetivo, 0);
	}

	private boolean tieneNodosCompletosEnNivel(NodoBinario<K, V> nodoActual, int nivelObjetivo, int nivelActual) {
		// TODO Auto-generated method stub
		if(NodoBinario.esNodoVacio(nodoActual)) {
			return false;
		}
		
		if(nivelActual==nivelObjetivo) {
			return !nodoActual.esVacioHijoDerecho() && !nodoActual.esVacioHijoIzquierdo();
		}
		boolean esCompletoHijoIzq= tieneNodosCompletosEnNivel(nodoActual.getHijoIzquierdo(),nivelObjetivo,nivelActual+1);
		boolean esCompletoHijoDerecho= tieneNodosCompletosEnNivel(nodoActual.getHijoDerecho(),nivelObjetivo,nivelActual+1);
		return esCompletoHijoDerecho && esCompletoHijoIzq;
		
	}
	public boolean esArbolBalanceado() {
		// TODO Auto-generated method stub
				List<K> recorrido= new ArrayList<>();
				recorrido=recorridoEnInOrden();
				
		if((Math.pow(2, altura())-1==recorrido.size())) {
			return true;
		}
		return false;
	}

	
    
	@Override
	public K sucesorDeUnNodoEnPostOrden(NodoBinario<K, V> nodoABuscarPredecesor) {
	     
		return Sucesor(nodoABuscarPredecesor);
			
	}

	private K Sucesor(NodoBinario<K, V> nodoABuscarPredecesor) {
		if (this.esArbolVacio()) {
			return null;
			}
			Stack<NodoBinario<K, V>> pilaDeNodos= new Stack<>();
			NodoBinario<K, V> nodoActual= this.raiz;
			
			meterPilaParaPostOrden(pilaDeNodos, nodoActual);
		
			
			while(!pilaDeNodos.isEmpty()) {
			nodoActual=pilaDeNodos.pop();    
			if(!pilaDeNodos.isEmpty()) {
			NodoBinario<K, V> nodoDelTope=pilaDeNodos.peek();
			if(!nodoDelTope.esVacioHijoDerecho() && nodoDelTope.getHijoDerecho()!=nodoActual) 
			{
			meterPilaParaPostOrden(pilaDeNodos, nodoDelTope.getHijoDerecho());
			}
			if(nodoActual.getClave()==nodoABuscarPredecesor.getClave()) {
				return  pilaDeNodos.peek().getClave();
			}
			}
			}
			return null;
	}

	@Override
	public void insertarRecursivo(K claveAInsertar, V valorAInsertar) {
		// TODO Auto-generated method stub
             if(esArbolVacio()) {
            	 this.raiz=new NodoBinario<K, V>(claveAInsertar, valorAInsertar);
            	 return;
             }
             insertarRecursivo(this.raiz,claveAInsertar, valorAInsertar);
		
	}

	private  void insertarRecursivo(NodoBinario<K, V> nodoActual, K claveAInsertar, V valorAInsertar) {
	    if(NodoBinario.esNodoVacio(nodoActual)) {
	    	return;
	    }
	    
	    if(claveAInsertar.compareTo(nodoActual.getClave())>0 ) {
	    	if( nodoActual.esVacioHijoDerecho()) {
	    	NodoBinario<K, V> nuevoNodo=new NodoBinario<K, V>(claveAInsertar, valorAInsertar);
	    	nodoActual.setHijoDerecho(nuevoNodo);
	    	return;
	    	}
	    	insertarRecursivo(nodoActual.getHijoDerecho(),claveAInsertar, valorAInsertar);
	    	
	    }
	    
	    if(claveAInsertar.compareTo(nodoActual.getClave())<0 ) {
	    	if( nodoActual.esVacioHijoIzquierdo()) {
	    	NodoBinario<K, V> nuevoNodo=new NodoBinario<K, V>(claveAInsertar, valorAInsertar);
	    	nodoActual.setHijoIzquierdo(nuevoNodo);
	    	return;
	    	}
	    	insertarRecursivo(nodoActual.getHijoIzquierdo(),claveAInsertar, valorAInsertar);
	    }
	    	
	}


	@Override
	public int cantidadDeHijosDerechosIterativo() {
		// TODO Auto-generated method stub
		Queue<NodoBinario<K, V>> colaDeNodos= new LinkedList<>();
		colaDeNodos.offer(this.raiz);
		int c=0;
		while(!colaDeNodos.isEmpty()) {
			NodoBinario<K, V> nodoActual= colaDeNodos.poll();
			if(!nodoActual.esVacioHijoIzquierdo()) {
				colaDeNodos.offer(nodoActual.getHijoIzquierdo());
				
			}
			if(!nodoActual.esVacioHijoDerecho()) {
				colaDeNodos.offer(nodoActual.getHijoDerecho());
				c++;
			}
		}
		
		
		return c;
	}
        
     public String dibujarRecorrido(ArrayList<K> recorrido){
         String R="";
         
         for (int i = 0; i < recorrido.size(); i++) {
             R= R + recorrido.get(i).toString()+" ";
         }
         
         return R;
     }


	@Override
	public K claveMayorDeUnNivel(int nivel) {
		// TODO Auto-generated method stub
		 return claveMayorDeUnNivel(this.raiz, 0, nivel );
	}


	private K claveMayorDeUnNivel(NodoBinario<K, V> nodoActual, int nivelActual, int nivelObjetivo) {
		// TODO Auto-generated method stub
		if(NodoBinario.esNodoVacio(nodoActual)) {
			return null;
		}
		    if (nivelObjetivo==nivelActual) {
		    	return nodoActual.getClave();
		    }
			
	     	K ClaveMayorDer= claveMayorDeUnNivel(nodoActual.getHijoDerecho(), nivelActual+1, nivelObjetivo);
	     	
	     	return ClaveMayorDer;
	     	
		 

	}


	@Override
	public int contarNodosConAmbosHijos(int nivel) {
		// TODO Auto-generated method stub
		return contarNodosConAmbosHijos(this.raiz, 0 ,nivel);
	}


	private int contarNodosConAmbosHijos(NodoBinario<K, V> nodoActual, int nivelActual, int nivelObjetivo) {
		// TODO Auto-generated method stub
		if(NodoBinario.esNodoVacio(nodoActual)) {
			return 0;
		}
		
		if(nivelActual==nivelObjetivo) {
			if(!nodoActual.esVacioHijoDerecho()&& !nodoActual.esVacioHijoIzquierdo()) {
				return 1;
				}
		}
		int contarPorLaIzquierda=contarNodosConAmbosHijos(nodoActual.getHijoIzquierdo(),nivelActual+1,nivelObjetivo);
		int contarPorLaDerecha =contarNodosConAmbosHijos(nodoActual.getHijoDerecho(),nivelActual+1,nivelObjetivo);
		return contarPorLaIzquierda + contarPorLaDerecha ;
		
	}


	@Override
	public int contarNodosConAmbosHijosIterativo(int nivelObjetivo) {
		// TODO Auto-generated method stub
		Queue<NodoBinario<K,V>> colaDeNodos= new LinkedList<>(); 
		colaDeNodos.offer(this.raiz);
		int nivelActual=0;
		int contarNodos=0;
		while (!colaDeNodos.isEmpty()) {
			
			int cantidad = colaDeNodos.size();
			int c=0;
			
			while(c<cantidad){
				
			NodoBinario<K, V> nodoActual=  colaDeNodos.poll();
			
			if(!nodoActual.esVacioHijoIzquierdo()) {
				colaDeNodos.offer(nodoActual.getHijoIzquierdo());
			}
			
			if(!nodoActual.esVacioHijoDerecho()) {
				colaDeNodos.offer(nodoActual.getHijoDerecho());
			
			}
			if(nivelActual==nivelObjetivo) {
				if(!nodoActual.esVacioHijoDerecho() && !nodoActual.esVacioHijoIzquierdo())
				{
					contarNodos++;
				}
				
			}
				c++;
			
			}
			
		 nivelActual++;
		 
		 if(nivelActual-1==nivelObjetivo) {
			 return contarNodos;
		 }
		
		}
		
		return contarNodos;
	}


	@Override
	public K padreMasCercano(K hijo1, K hijo2) {
		
		NodoBinario<K, V> padreTemporal= this.raiz;
		NodoBinario<K, V> padreAnterior= (NodoBinario<K, V>) NodoBinario.nodoVacio();
	  while(!NodoBinario.esNodoVacio(padreTemporal)) {
		  
		  if(padreTemporal.getClave().compareTo(hijo1)<0 && padreTemporal.getClave().compareTo(hijo2)>0 ) 
		  {
			return padreTemporal.getClave();
		  }
		  if(padreTemporal.getClave().compareTo(hijo1)>0 && padreTemporal.getClave().compareTo(hijo2)<0 ) 
		  {
			return padreTemporal.getClave();
		  }
		  
		  if(padreTemporal.getClave().compareTo(hijo1)<0 && padreTemporal.getClave().compareTo(hijo2)<0 ) {
			  padreAnterior=padreTemporal;
			  padreTemporal=padreTemporal.getHijoDerecho();
			  if(padreTemporal.getClave().compareTo(hijo1)==0  || padreTemporal.getClave().compareTo(hijo2)==0) {
				  return padreAnterior.getClave();
			  }
			  
		   }
		  
		  if(padreTemporal.getClave().compareTo(hijo1)>0 && padreTemporal.getClave().compareTo(hijo2)>0 ) {
			  padreAnterior=padreTemporal;
			  padreTemporal=padreTemporal.getHijoIzquierdo();
			  if(padreTemporal.getClave().compareTo(hijo1)==0  || padreTemporal.getClave().compareTo(hijo2)==0) {
				  return padreAnterior.getClave();
			  }
			  
		   }
       
	  }
	    
			return null;
	}


	@Override
	public K padreMasCercanoRecursivo(K hijo1, K hijo2) {
		
		NodoBinario<K, V> nodoAnterior= (NodoBinario<K, V>) NodoBinario.nodoVacio();
		return padreMasCercanoRecursivo(this.raiz,hijo1,hijo2,nodoAnterior);
	}


	private K padreMasCercanoRecursivo(NodoBinario<K, V> padreTemporal, K hijo1, K hijo2, NodoBinario<K, V>  nodoAnterior) {
	    if(NodoBinario.esNodoVacio(padreTemporal)) {
	    		return null;
		}
	    //caso 1
	    if(padreTemporal.getClave().compareTo(hijo1)<0 && padreTemporal.getClave().compareTo(hijo2)>0 ) 
		  {
			return padreTemporal.getClave();
		  }
	    
	    if(padreTemporal.getClave().compareTo(hijo1)>0 && padreTemporal.getClave().compareTo(hijo2)<0 ) 
		  {
			return padreTemporal.getClave();
		  }
	    //caso 2
	    if(padreTemporal.getClave().compareTo(hijo1)<0 && padreTemporal.getClave().compareTo(hijo2)<0 ) {
	    	
	    	nodoAnterior=padreTemporal;
	    	
	    	  return padreMasCercanoRecursivo(padreTemporal.getHijoDerecho(),  hijo1, hijo2,nodoAnterior);
	    }
	    //caso 2.1
	    if(padreTemporal.getClave().compareTo(hijo1)>0 && padreTemporal.getClave().compareTo(hijo2)>0 ) {
	    	nodoAnterior=padreTemporal;
	    	
	    	  return padreMasCercanoRecursivo(padreTemporal.getHijoIzquierdo(),  hijo1, hijo2,nodoAnterior);
	    }
	    //casoEspecial
	    if(padreTemporal.getClave().compareTo(hijo1)==0  || padreTemporal.getClave().compareTo(hijo2)==0) {
			 return nodoAnterior.getClave();
		  }
		return padreTemporal.getClave();
	    
	    
	}


	@Override
	public List<K> mostrarTodasLasClavesDeUnNivel(int nivel) {
		// TODO Auto-generated method stub
	  List<K> clavesDeCiertoNivel=new ArrayList<>();
		mostrarTodasLasClavesDeUnNivel(this.raiz, 0, nivel,clavesDeCiertoNivel );
		return clavesDeCiertoNivel;
	}


	private void mostrarTodasLasClavesDeUnNivel(NodoBinario<K, V> nodoActual, int nivelActual, int nivelObjetivo,
			List<K> clavesDeCiertoNivel) {
		// TODO Auto-generated method stub
		if(NodoBinario.esNodoVacio(nodoActual)) {
			return;
		}
		
		if(nivelActual==nivelObjetivo) {
			
			 clavesDeCiertoNivel.add(nodoActual.getClave());
			 
			return;
		}
		mostrarTodasLasClavesDeUnNivel(nodoActual.getHijoIzquierdo(),nivelActual+1,nivelObjetivo,clavesDeCiertoNivel);
		mostrarTodasLasClavesDeUnNivel(nodoActual.getHijoDerecho(),nivelActual+1,nivelObjetivo,clavesDeCiertoNivel);
	}


	@Override
	public List<K> mostrarCentroDeNivelesImpares() {
		List<K> centrosImpares= new ArrayList<>();
	    Queue<NodoBinario<K, V>> colaDeNodos= new LinkedList<>();
	    colaDeNodos.offer(this.raiz);
	    while (!colaDeNodos.isEmpty()) {
	    	int sizeQueue= colaDeNodos.size();  
	    	int c=1;
	    	while(c<=sizeQueue) {
	    		if( (sizeQueue /2)+1 ==c && sizeQueue%2 !=0 || sizeQueue==1) {
    				centrosImpares.add(colaDeNodos.peek().getClave());
    			}
	    		NodoBinario<K, V>nodoActual= colaDeNodos.poll();
	    		if(!nodoActual.esVacioHijoIzquierdo()) {
	    			colaDeNodos.offer(nodoActual.getHijoIzquierdo());
	    		}
	    		if(!nodoActual.esVacioHijoDerecho()) {
	    		   colaDeNodos.offer(nodoActual.getHijoDerecho());
	    		}
	    		c++;
	    }	
	}
	   return centrosImpares; 
	}


	@Override
	public List<K> recorridoMix() {
		// TODO Auto-generated method stub
		 List<K> recorridoMix=new ArrayList<>();
		 return recorridoMix;
	}



	@Override
	public int cantidadDeHijosIzquierdosNoVacios() {
		// TODO Auto-generated method stub
		return cantidadDeHijosIzquierdosNoVacios(this.raiz);
	}


	private int cantidadDeHijosIzquierdosNoVacios(NodoBinario<K, V> nodoActual) {
		// TODO Auto-generated method stub
		if(NodoBinario.esNodoVacio(nodoActual)) {
		return 0;
		}
		
		int cantidadPorIzquierda= cantidadDeHijosIzquierdosNoVacios(nodoActual.getHijoIzquierdo());
		int cantidadPorDerecha= cantidadDeHijosIzquierdosNoVacios(nodoActual.getHijoDerecho());
		if(nodoActual.esVacioHijoDerecho() && !nodoActual.esVacioHijoIzquierdo()) {
			return cantidadPorDerecha + cantidadPorIzquierda+1;
		}
		return cantidadPorDerecha + cantidadPorIzquierda;
	}


	@Override
	public int cantidadDeHijosIzquierdosNoVaciosIterativo() {

		// TODO Auto-generated method stub
	       Stack<NodoBinario<K, V>> pilaDeNodos= new Stack<>();
	       NodoBinario<K, V> nodoActual=this.raiz;
	       pilaDeNodos.push(nodoActual);
	       int contarHijos=0;
	       while(!NodoBinario.esNodoVacio(nodoActual)) {
	    	   nodoActual=nodoActual.getHijoIzquierdo();
	    	   if(nodoActual!=null ) {
	    		   pilaDeNodos.push(nodoActual);
	    	   }
	    	  
	       }
	       
	       while(!pilaDeNodos.isEmpty()) {
	    	   nodoActual=pilaDeNodos.pop();
	    	   if(!nodoActual.esVacioHijoDerecho()) {
	    		   while(!NodoBinario.esNodoVacio(nodoActual)) {
	    			   nodoActual=nodoActual.getHijoIzquierdo();
	    	    	   if(nodoActual!=null ) {
	    	    		   pilaDeNodos.push(nodoActual);
	    	    		   }
	    	       }
	    		   if(nodoActual.esVacioHijoDerecho() && !nodoActual.esVacioHijoIzquierdo()) {
	    			   contarHijos++;
	    		   }
	    		 
	    		   
	    	   }
	    	   
	       }
	       return contarHijos;

	}


	@Override
	public V buscarRecursivo(K clave) {
		// TODO Auto-generated method stub
		return buscarRecursivo(this.raiz,clave);
	}


	private V buscarRecursivo(NodoBinario<K, V> nodoActual, K clave) {
		// TODO Auto-generated method stub
		if(NodoBinario.esNodoVacio(nodoActual)) {
			return null;
		}
		
		if(clave.compareTo(nodoActual.getClave())==0) {
			return nodoActual.getValor();
		}
		
		V Izq=buscarRecursivo(nodoActual.getHijoIzquierdo(), clave);
		V Der=buscarRecursivo(nodoActual.getHijoDerecho(), clave);
		
		if(clave.compareTo(nodoActual.getClave())<0) {
			return Izq;
		} else {
			return Der;
		}
	}

    @Override
    public boolean esArbolPerfectamenteBalanceado() {
       return esArbolPerfectamenteBalanceado(this.raiz);
       
    }
    private boolean esArbolPerfectamenteBalanceado(NodoBinario<K, V> nodoActual) {
     if(NodoBinario.esNodoVacio(nodoActual)){
         return false;
     }
     if(nodoActual.esVacioHijoIzquierdo()&& !nodoActual.esVacioHijoDerecho()){
             return false;
     }
     if(!nodoActual.esVacioHijoIzquierdo()&& nodoActual.esVacioHijoDerecho()){
             return false;
     }
     
     if(!nodoActual.esVacioHijoIzquierdo()&& !nodoActual.esVacioHijoDerecho()){
             return true;
     }
     boolean esPerfectoPorIzquierda=esArbolPerfectamenteBalanceado(nodoActual.getHijoIzquierdo()); 
     boolean esPerfectoPorDerecha=esArbolPerfectamenteBalanceado(nodoActual.getHijoDerecho());

  
     

         


     return esPerfectoPorDerecha && esPerfectoPorIzquierda;
    }



    @Override
    public int cantDatosVaciosMVias() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public int cantDatosVaciosMViasRec() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

	@Override
	public int cantDeNodosHojasMVias() {
		// TODO Auto-generated method stub
		return 0;
	}



	@Override
	public boolean sonArbolesSimilares(ArbolMViasBusqueda<K, V> arbol) {
		// TODO Auto-generated method stub
		return false;
	}


	@Override
	public boolean sonArbolesSimilares1(ArbolMViasBusqueda<K, V> arbol) {
		// TODO Auto-generated method stub
		return false;
	}

    
    public int contarNodosConAmbosHijosPostOrden() {
        if(this.esArbolVacio()){
            return 0;
        }
        int contar=0;
     Stack<NodoBinario<K,V>> pilaDeNodos= new Stack<>();
     NodoBinario<K,V> nodoActual=this.raiz;
     meterAlapilaPostOrden(pilaDeNodos,nodoActual);
     while(!pilaDeNodos.isEmpty()){
         nodoActual=pilaDeNodos.pop();
         if(!nodoActual.esVacioHijoDerecho() && !nodoActual.esVacioHijoIzquierdo()){
             contar++;
         }
         if(!pilaDeNodos.isEmpty()){
             NodoBinario<K,V> nodoTope= pilaDeNodos.peek();
             if(!nodoTope.esVacioHijoDerecho()&& nodoTope.getHijoIzquierdo()!=nodoActual){
                 meterAlapilaPostOrden(pilaDeNodos, nodoActual.getHijoDerecho());
             }
         }
     }
     return contar;
    }

    private void meterAlapilaPostOrden(Stack<NodoBinario<K,V>> pilaDeNodos, NodoBinario<K, V> nodoActual) {
       while(!NodoBinario.esNodoVacio(nodoActual)){
           pilaDeNodos.push(nodoActual);
           if(!nodoActual.esVacioHijoIzquierdo()){
               nodoActual=nodoActual.getHijoIzquierdo();
           }else{
               nodoActual=nodoActual.getHijoDerecho();
           }
           
       }
    }

    @Override
    public List<K> mostrarHijosDeUnNivelDado(int nivelObjetivo) {
     List<K> recorrido= new ArrayList<>();
        mostrarHijosDeUnNivelDado(this.raiz,recorrido,0,nivelObjetivo);
        return recorrido;
    }
     private void mostrarHijosDeUnNivelDado(NodoBinario<K, V> nodoActual, List<K> recorrido, int nivelActual, int nivelObjetivo) {
      if(NodoBinario.esNodoVacio(nodoActual)){
          return;
      }
      if(nivelActual==nivelObjetivo){
          recorrido.add(nodoActual.getClave());
      }
         mostrarHijosDeUnNivelDado(nodoActual.getHijoIzquierdo(), recorrido, nivelActual+1, nivelObjetivo);
         mostrarHijosDeUnNivelDado(nodoActual.getHijoDerecho(), recorrido, nivelActual+1, nivelObjetivo);
     }


    @Override
    public int mostrarNivelDeUnaClaveDada(K claveABuscar) {
        if(this.buscar(claveABuscar)!=null ){
          return mostrarNivelDeUnaClaveDada(this.raiz,0,claveABuscar);  
        }else{
            return -1;
        }
         
    }
    
    private int mostrarNivelDeUnaClaveDada(NodoBinario<K,V> nodoActual,int nivelActual,K claveABuscar){
        
        if(NodoBinario.esNodoVacio(nodoActual)){
            return 0;
        }
        int buscarPorIzq=mostrarNivelDeUnaClaveDada(nodoActual.getHijoIzquierdo(),nivelActual+1,claveABuscar);
        K claveActual=nodoActual.getClave();
        if(claveABuscar.compareTo(claveActual)==0){
         return nivelActual;   
        }
        int buscarPorDere=mostrarNivelDeUnaClaveDada(nodoActual.getHijoDerecho(),nivelActual+1,claveABuscar);
        
        return buscarPorIzq+buscarPorDere;

    }

    public int cantidadDeDatosVacios() {
    return 0;
    }

    @Override
    public boolean esBalanceado(K claveABuscar) {
     
      Queue<NodoBinario<K,V>> colaDeNodos= new LinkedList<>();
      colaDeNodos.offer(this.raiz);
      boolean valor=true;
      boolean cambio=true;
      while(!colaDeNodos.isEmpty()){
          
          NodoBinario<K,V> nodoActual= colaDeNodos.poll();
          K claveActual=nodoActual.getClave();
          
          if(claveABuscar.compareTo(claveActual)==0||cambio==false ){
              int alturaIzq=altura(nodoActual.getHijoIzquierdo());
              int alturaDer=altura(nodoActual.getHijoDerecho());
              int diferencia=alturaIzq-alturaDer;
              cambio=false;
              if(diferencia<=1 && diferencia>-2){
                  valor=true;
              }else{
                  return false;
              }
              
          }
          
          if(!nodoActual.esVacioHijoIzquierdo()){
              colaDeNodos.offer(nodoActual.getHijoIzquierdo());
          }
          if(!nodoActual.esVacioHijoDerecho()){
              colaDeNodos.offer(nodoActual.getHijoDerecho());
          }
          
      }
      
      return valor;

    }

    @Override
    public int cantidadDeNodosCon2HijosEvitandoUnNivel(int nivelObjetivo) {
    return cantidadDeNodosCon2HijosEvitandoUnNivel(this.raiz, 0,nivelObjetivo);
    }
    
    private int cantidadDeNodosCon2HijosEvitandoUnNivel(NodoBinario<K,V> nodoActual, int nivelActual, int nivelObjetivo){
        if(NodoBinario.esNodoVacio(nodoActual)){
            return 0;
        }
        int cantidadPorIzquierda= cantidadDeNodosCon2HijosEvitandoUnNivel(nodoActual.getHijoIzquierdo(), nivelActual+1, nivelObjetivo);
        int cantidadPorDerecha= cantidadDeNodosCon2HijosEvitandoUnNivel(nodoActual.getHijoDerecho(), nivelActual+1, nivelObjetivo);
        if(nivelActual!=nivelObjetivo ){
            if(!nodoActual.esVacioHijoIzquierdo()&& !nodoActual.esVacioHijoDerecho()){
                return cantidadPorDerecha+cantidadPorIzquierda+1;
            }
        }
        return cantidadPorIzquierda+cantidadPorDerecha;
        
    }

    @Override
    public boolean verificarSiElArbolTienePorLoMenosUnNodoCompleto() {
        Stack<NodoBinario<K,V>> pilaDeNodos= new Stack<>();
        insertarEnPostOrden(pilaDeNodos,this.raiz);
        while(!pilaDeNodos.isEmpty()){
            NodoBinario<K,V> nodoActual= pilaDeNodos.pop();
            if(!nodoActual.esVacioHijoIzquierdo()&& !nodoActual.esVacioHijoDerecho()){
                return true;
            }
            if(!pilaDeNodos.isEmpty()){
                
                NodoBinario<K,V> nodoTope=pilaDeNodos.peek();
                
                if(nodoTope.getHijoDerecho()!=nodoActual && !nodoTope.esVacioHijoDerecho()){
                    insertarEnPostOrden(pilaDeNodos, nodoTope.getHijoDerecho());
                }
       
            }
   
        }
        
        
        return false;
    }
    
    private void insertarEnPostOrden(Stack<NodoBinario<K,V>> pilaDeNodos, NodoBinario<K,V> nodoActual){
        
        while(!NodoBinario.esNodoVacio(nodoActual)){
             pilaDeNodos.push(nodoActual);
             if(!nodoActual.esVacioHijoIzquierdo()){
                 nodoActual=nodoActual.getHijoIzquierdo();
             }else{
                 nodoActual=nodoActual.getHijoIzquierdo();
             }
        }
    }




 
    

    

   

	
}
	


	




	
	
   
	

	


      

