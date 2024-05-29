package bo.edu.uagrm.ficct.inf310sb.arboles;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;


public class ArbolMViasBusqueda<K extends Comparable<K>,V> implements 
IArbolBusqueda<K,V> {
	protected NodoMVias<K, V> raiz;
	protected int orden;
        protected int POSICION_INVALIDA=-1;
	
    public ArbolMViasBusqueda() {
    	this.orden=3;
    }
    
    public ArbolMViasBusqueda(int orden) throws RuntimeException{
    	if(orden<3) {
    		
    	}
    	this.orden=orden;
    	
    }
	@Override
	public void vaciar() {
		// TODO Auto-generated method stub
		this.raiz= NodoMVias.nodoVacio(); 
	}

	@Override
	public boolean esArbolVacio() {
		// TODO Auto-generated method stub
		return NodoMVias.esNodoVacio(raiz);
	}

	@Override
	public boolean contiene(K clave) {
		// TODO Auto-generated method stub
		return this.buscar(clave)!=null;
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return size(this.raiz);
	}
        private int size(NodoMVias<K,V> nodoActual){
          
            if(NodoMVias.esNodoVacio(nodoActual)){
                return 0;
            }
            
            int cantIzq=0;
            for(int i=0; i<orden-1; i++){
                cantIzq= cantIzq +size(nodoActual.getHijo(i)); // me cuenta todos los valores por izquierda
            }
            
            int cantDer=0;
              cantDer=cantDer+size(nodoActual.getHijo(orden-1)); // me cuenta todos los valores por derecha
            
                
                 return cantIzq+cantDer+1;  //entonces sumo izquierda y derecha y le sumo el que faltaba
         
        }

	@Override
	public int altura() {
		// TODO Auto-generated method stub
		return altura(this.raiz);
	}
        
        private int altura(NodoMVias<K,V> nodoActual){
            if(NodoMVias.esNodoVacio(nodoActual)){
                return 0;
            }
            
            int alturaMayor=0;
            for(int i=0; i<orden; i++){
                
                int alturaDeHijo= altura(nodoActual.getHijo(i));// alturaDeHijo va aser igual a la alturaMayor porque la estamos retornando y sera de la posicion del nodoActual principal
                //Es decir que nodoRaiz es 0 me va a dar la altura por 0, entonces podria decir que si altura me da altura de cada uno de las posiciones de los hijos
                //Entonces yo tengo que comparar cual es mayor y guardar el mayor
                if(alturaDeHijo> alturaMayor){
                    alturaMayor= alturaDeHijo;
                }
                
            }
            return alturaMayor + 1;// Entonce la altura mayor sera la altura mayor mas en hijo principal osea la raiz donde 1 es la raiz
        }

	@Override
	public int nivel() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public K minimo() {
		// TODO Auto-generated method stub
                NodoMVias<K,V> nodoActual= this.raiz;
                NodoMVias<K,V> nodoAnterior= NodoMVias.nodoVacio();
                while(!NodoMVias.esNodoVacio(nodoActual))
                {
                    nodoAnterior=nodoActual;
                    nodoActual=nodoActual.getHijo(0);                
                }
		return nodoAnterior.getClave(0);
	}

	@Override
	public K maximo() {
		// TODO Auto-generated method stub
                NodoMVias<K,V> nodoActual= this.raiz;
                NodoMVias<K,V> nodoAnterior= NodoMVias.nodoVacio();
                while(!NodoMVias.esNodoVacio(nodoActual)){
                 nodoAnterior=nodoActual;
                 nodoActual=nodoActual.getHijo(nodoActual.nroDeClavesNoVacias());
                }
                return nodoAnterior.getClave(nodoAnterior.nroDeClavesNoVacias()-1) ;
	}

	@Override
	public void insertar(K claveAInsertar, V valorAInsertar) {
        if(esArbolVacio()){
             this.raiz=new NodoMVias<>(orden,claveAInsertar,valorAInsertar);
             return;
         }
         NodoMVias<K,V>nodoActual=this.raiz;
                while(!NodoMVias.esNodoVacio(nodoActual)){
                    int posicionClaveEnNodo=existeClaveEnNodo(nodoActual,claveAInsertar);
                    if(posicionClaveEnNodo!=POSICION_INVALIDA){
                        nodoActual.setValor(posicionClaveEnNodo, valorAInsertar);
                        nodoActual=NodoMVias.nodoVacio();
                    }
                    if(nodoActual.esHoja()){
                        if(!nodoActual.estanClavesLlenas()){
                            insertarDatosOrdenadosEnNodo(nodoActual,claveAInsertar,valorAInsertar); 
                        }else{
                             int posicionPorDondeBajar=porDondeBajar(nodoActual,claveAInsertar);
                             NodoMVias<K,V>nuevo=new NodoMVias<>(this.orden,claveAInsertar,valorAInsertar);
                             nodoActual.setHijo(posicionPorDondeBajar,nuevo);
                        }
                        nodoActual=NodoMVias.nodoVacio();
                    }else{
                        int posicionBajar=porDondeBajar(nodoActual,claveAInsertar);                            
                            if(NodoMVias.esNodoVacio(nodoActual.getHijo(posicionBajar))){
                                NodoMVias<K,V>nuevoHijo=new NodoMVias<>(this.orden,claveAInsertar,valorAInsertar);
                                nodoActual.setHijo(posicionBajar,nuevoHijo);
                                nodoActual=NodoMVias.nodoVacio();
                            }else{
                                nodoActual=nodoActual.getHijo(posicionBajar);
                            }
                    }
                }
    }
    public int porDondeBajar(NodoMVias<K,V>nodoActual,K claveABuscar){
        int i=0;
        boolean llegoAlFinal=false;
            while(i<nodoActual.nroDeClavesNoVacias()){
                K claveActual=nodoActual.getClave(i);
                    if(claveActual.compareTo(claveABuscar)<0){
                        i++;
                    }else{
                        break;
                    }       
            }
            if(nodoActual.getClave(nodoActual.nroDeClavesNoVacias()-1).compareTo(claveABuscar)<0){
                return nodoActual.nroDeClavesNoVacias();
            }
            return i;
    }
    public void insertarDatosOrdenadosEnNodo(NodoMVias<K,V>nodoActual,K claveAInsertar,V valorAInsertar){
        int res=0;
        for(int i=nodoActual.nroDeClavesNoVacias()-1;i>=0;i--){
                K claveActual=nodoActual.getClave(i);
                    if(claveActual.compareTo(claveAInsertar)>0){
                        nodoActual.setClave(i+1, claveActual);
                    }else{
                     res=i;
                     break;
                    }
          
        }
        nodoActual.setClave(res+1, claveAInsertar);
        nodoActual.setValor(res+1, valorAInsertar);
       
    }
    private int existeClaveEnNodo(NodoMVias<K,V>nodoActual,K claveABuscar){
        for(int i=0;i<nodoActual.nroDeClavesNoVacias();i++){
            K claveActual=nodoActual.getClave(i);
                if(claveActual.compareTo(claveABuscar)==0){
                    return i;
                }
        }
        return -1;
    }



	@Override
	public V Eliminar(K clave) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	   public V buscar(K claveABuscar) {
        Queue<NodoMVias<K,V>>colaDeNodos= new LinkedList<>();
        colaDeNodos.offer(this.raiz);
        
        while(!colaDeNodos.isEmpty()){
            NodoMVias<K,V> nodoActual= colaDeNodos.poll();
            for(int i=0; i<nodoActual.nroDeClavesNoVacias(); i++){
               if(claveABuscar.compareTo(nodoActual.getClave(i))==0){
                   return nodoActual.getValor(i);
               } 
               if(!nodoActual.esHijoVacio(i)){
                   colaDeNodos.offer(nodoActual.getHijo(i));
               }
               
            }
          if(!nodoActual.esHijoVacio(nodoActual.nroDeClavesNoVacias())){
               colaDeNodos.offer(nodoActual.getHijo(nodoActual.nroDeClavesNoVacias()));
          }
        }
       return null;
    }
        

	@Override
	public List<K> recorridoEnInOrdenRec() {
		// TODO Auto-generated method stub
                List<K> recorrido= new ArrayList<>();
                recorridoEnInOrdenRec(this.raiz,recorrido);
		return recorrido;
	}
        
        private void recorridoEnInOrdenRec(NodoMVias<K,V> nodoActual, List<K> recorrido){
            
            if(NodoMVias.esNodoVacio(nodoActual)){
                return;
            }
           for(int i=0; i<nodoActual.nroDeClavesNoVacias(); i++){
               recorridoEnInOrdenRec(nodoActual.getHijo(i), recorrido);
               recorrido.add(nodoActual.getClave(i));
               
           }
            recorridoEnInOrdenRec(nodoActual.getHijo(nodoActual.nroDeClavesNoVacias()), recorrido);
        }

	@Override
        public List<K> recorridoEnPreOrden() {
        // TODO Auto-generated method stub
        List<K> recorrido = new ArrayList<>();
        recorridoEnPreOrden(this.raiz,recorrido);
        return recorrido;
    }
           private void recorridoEnPreOrden(NodoMVias<K,V> nodoActual, List<K> recorrido){
               if(NodoMVias.esNodoVacio(nodoActual)){
                   return;
               }
               
               for(int i=0; i<nodoActual.nroDeClavesNoVacias(); i++){
                recorrido.add(nodoActual.getClave(i));
                recorridoEnPreOrden(nodoActual.getHijo(i), recorrido);
                   
               }
               recorridoEnPreOrden(nodoActual.getHijo(nodoActual.nroDeClavesNoVacias()), recorrido);
               
           }
           
           

	@Override
	public List<K> recorridoEnPostOrden() {
		// TODO Auto-generated method stub
                List<K> recorrido= new ArrayList<>();
                recorridoEnPostOrden(this.raiz,recorrido);
		return recorrido; 
	}
        
        private void recorridoEnPostOrden(NodoMVias<K,V> nodoActual,List<K> recorrido){
            if(NodoMVias.esNodoVacio(nodoActual)){
                return;
            }
            recorridoEnPostOrden(nodoActual.getHijo(0), recorrido);
            for(int i=0; i<nodoActual.nroDeClavesNoVacias(); i++){
                recorridoEnPostOrden(nodoActual.getHijo(i+1),recorrido);
                recorrido.add(nodoActual.getClave(i));
            }
           
         
        }

	@Override
	public List<K> recorridoPorNiveles() {
		// TODO Auto-generated method stub
                List<K> recorrido= new ArrayList<>();
          if(this.esArbolVacio()){
              return recorrido;
          }      
          
          Queue<NodoMVias<K,V>> colaDeNodos= new LinkedList<>();
          colaDeNodos.offer(this.raiz);
          
          while(!colaDeNodos.isEmpty()){
              NodoMVias<K,V> nodoActual= colaDeNodos.poll();
              for (int i=0; i<nodoActual.nroDeClavesNoVacias(); i++){
                  recorrido.add(nodoActual.getClave(i));
                  if(!nodoActual.esHijoVacio(i)){
                      colaDeNodos.offer(nodoActual.getHijo(i));
                  } 
              }
              
             if(!nodoActual.esHijoVacio(nodoActual.nroDeClavesNoVacias())){
                 colaDeNodos.offer(nodoActual.getHijo(nodoActual.nroDeClavesNoVacias()));
             }
        
          }
        
		return recorrido;
                
	}
        public boolean sonArbolesSimilares(ArbolMViasBusqueda<K,V> arbol3){
            return false;
        }
	@Override
	public List<K> recorridoEnInOrden() {
		// TODO Auto-generated method stub
		return null;
                
	}

	@Override
	public List<K> recorridoEnPreOrdenRec() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int cantidadDeHijosDerechos() {
		// TODO Auto-generated method stub
		return cantidadDeHijosDerechos(this.raiz);
	}
        
        private int cantidadDeHijosDerechos(NodoMVias<K,V> nodoActual){
            if(NodoMVias.esNodoVacio(nodoActual)){
                return 0;
            }
            
            int cantidad=0;
            
            for(int i=0; i<orden-1; i++){
                cantidad=cantidad+ cantidadDeHijosDerechos(nodoActual.getHijo(i));  // la antidad de todos los hijos por isquierda
            }
            cantidad=cantidad+cantidadDeHijosDerechos(nodoActual.getHijo(orden-1));// y este me da la cantidad de todos los hijos por derecha
            if(!nodoActual.esHijoVacio(orden-1)){ // entonces como solo me importan los hijos derechos 
                return cantidad + 1; // sumo +1 en este porque por lo menos tengo 1 hijo derecho
            }
            return cantidad; //caso contrario no tengo hijo derecho
            
        }

	@Override
	public int cantidadDeHijosDerechosIterativo() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int cantidadDeHijosConNodoCompleto() {
		// TODO Auto-generated method stub
		return cantidadDeHijosConNodoCompleto(this.raiz);
	}
         private int cantidadDeHijosConNodoCompleto(NodoMVias<K, V> nodoActual) {
         if(NodoMVias.esNodoVacio(nodoActual)){
             return 0;
         }
         int cantidad=0;
         
         for(int i=0; i<orden; i++){
             cantidad= cantidadDeHijosConNodoCompleto(nodoActual.getHijo(i));
             
             if (nodoActual.esHijoVacio(i)){//Nota: si nunca entro a este if significa que tiene nodo completo osea tiene todos sus hijos
                 return cantidad;//entonces retorna la cantidad
             }
                        
         }
        
         return cantidad+1; // caso contrario significa que busco de el nodo actual todos sus hijos y comprobo que ni uno era vacio
         }


	@Override
	public boolean tieneNodosCompletosEnNivel(int nivelObjetivo) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean esArbolBalanceado() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public K sucesorDeUnNodoEnPostOrden(NodoBinario<K, V> nodoABuscarPredecesor) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void insertarRecursivo(K claveAInsertar, V valorAInsertar) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String dibujarRecorrido(ArrayList<K> recorrido) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public K claveMayorDeUnNivel(int nivel) {
		// TODO Auto-generated method stub
                
		return claveMayorDeUnNivel(this.raiz,0,nivel);
	}
        
        private K claveMayorDeUnNivel(NodoMVias<K,V> nodoActual, int nivelActual, int nivelObjetivo){
         return null;
        }

	@Override
	public int contarNodosConAmbosHijos(int nivel) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int contarNodosConAmbosHijosIterativo(int nivel) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public K padreMasCercano(K hijo1, K hijo2) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public K padreMasCercanoRecursivo(K hijo1, K hijo2) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<K> mostrarTodasLasClavesDeUnNivel(int nivel) {
		// TODO Auto-generated method stub
                List<K> recorrido= new ArrayList<>();
	         mostrarTodasLasClavesDeUnNivel(this.raiz,0,nivel,recorrido);
                 return recorrido;
	}
        private void mostrarTodasLasClavesDeUnNivel(NodoMVias<K,V> nodoActual,int nivelActual,int nivelObjetivo,List<K> recorrido){
            if(NodoMVias.esNodoVacio(nodoActual)){
                return;
            }
            mostrarTodasLasClavesDeUnNivel(nodoActual.getHijo(0), nivelActual+1, nivelObjetivo,recorrido);
            for(int i=0; i<nodoActual.nroDeClavesNoVacias(); i++){
                mostrarTodasLasClavesDeUnNivel(nodoActual.getHijo(i+1), nivelActual+1, nivelObjetivo,recorrido);
                if(nivelActual==nivelObjetivo){
                    recorrido.add(nodoActual.getClave(i));
                }
            } 
        }

	@Override
	public List<K> mostrarCentroDeNivelesImpares() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<K> recorridoMix() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int cantidadDeHijosIzquierdosNoVacios() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int cantidadDeHijosIzquierdosNoVaciosIterativo() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public V buscarRecursivo(K clave) {
		// TODO Auto-generated method stub
		return null;
	}

    @Override
    public List<K> obtenerClavesDadoUnNivelDado(int nivelObjetivo) {
      
    	List<K> recorrido= new ArrayList<>();
    	if(this.esArbolVacio()) {
    		return recorrido;
    	}
    	Queue<NodoMVias<K,V>> colaDeNodos= new LinkedList<>();
    	NodoMVias<K,V> nodoActual=this.raiz;
    	colaDeNodos.offer(nodoActual);
    	int nivel=0;
    	while(!colaDeNodos.isEmpty()) {
    		int tamañoDeLaCola=colaDeNodos.size();
    		int controlaLaCola=0;
    		while(controlaLaCola<tamañoDeLaCola) {
    			nodoActual=colaDeNodos.poll();
    		for(int i=0; i<nodoActual.nroDeClavesNoVacias();i++) {
    			if(!nodoActual.esHijoVacio(i)) {
    				colaDeNodos.offer(nodoActual.getHijo(i));
    			}
    			if(nivel==nivelObjetivo) {
    				recorrido.add(nodoActual.getClave(i));
        		}
    			
    		}
    		
    		if(!nodoActual.esHijoVacio(nodoActual.nroDeClavesNoVacias())) {
    			colaDeNodos.offer(nodoActual.getHijo(nodoActual.nroDeClavesNoVacias()));
    		}
    		
    		controlaLaCola++;
    	}
    		
    		nivel++;
    		
    	}
    	return recorrido;
    }

    @Override
    public boolean esArbolPerfectamenteBalanceado() {
       return false;
        }

    @Override
    public int cantDatosVaciosMVias() {
   // TODO Auto-generated method stub
           
          if(this.esArbolVacio()){
           return 0;
          }      
          
          Queue<NodoMVias<K,V>> colaDeNodos= new LinkedList<>();
          colaDeNodos.offer(this.raiz);
          int vacio=0;
          while(!colaDeNodos.isEmpty()){
              NodoMVias<K,V> nodoActual= colaDeNodos.poll();
              for (int i=0; i<this.orden-1; i++){
      
                  if(!nodoActual.esHijoVacio(i)){
                      colaDeNodos.offer(nodoActual.getHijo(i));
                  } 
                   if(nodoActual.esClavesVacia(i)){
                 vacio++;
             }
              }
              
             if(!nodoActual.esHijoVacio(this.orden-1)){
                 colaDeNodos.offer(nodoActual.getHijo(this.orden-1));
             }
               
          }
        
		return vacio;
                
	}


    @Override
    public int cantDatosVaciosMViasRec() {
        return cantDatosVaciosMViasRec(this.raiz);
    }
    
    private int cantDatosVaciosMViasRec(NodoMVias<K,V> nodoActual){
        if(NodoMVias.esNodoVacio(nodoActual)){
             return 0;
        }
            return 0;
       
    }

	@Override
	public int cantDeNodosHojasMVias() {
		if(this.esArbolVacio()) {
			return 0;
		}
		Queue<NodoMVias<K,V>> colaDeNodos= new LinkedList<>();
		colaDeNodos.offer(this.raiz);
		int contadorDeHojas=0;
		while(!colaDeNodos.isEmpty()) {
			NodoMVias<K,V> nodoActual= colaDeNodos.poll();
			for(int i=0; i<nodoActual.nroDeClavesNoVacias(); i++) {
				if(!nodoActual.esHijoVacio(i)) {
					colaDeNodos.offer(nodoActual.getHijo(i));
				}
			}
			if(!nodoActual.esHijoVacio(nodoActual.nroDeClavesNoVacias())) {
				colaDeNodos.offer(nodoActual.getHijo(nodoActual.nroDeClavesNoVacias()));
			}
			if(nodoActual.esHoja()) {
				contadorDeHojas++;
			}
		}
		return contadorDeHojas;
	}

	@Override
	public boolean sonArbolesSimilares1(ArbolMViasBusqueda<K, V> arbol) {
		// TODO Auto-generated method stub
		
		 if (this.esArbolVacio() || arbol.esArbolVacio()) {
			 return false;
		 }
		
		 Queue<NodoMVias<K,V>> colaDeNodos1= new LinkedList<>();
		 Queue<NodoMVias<K,V>> colaDeNodos2= new LinkedList<>();
		 colaDeNodos1.offer(this.raiz);
		 colaDeNodos2.offer(arbol.raiz);
		 
		 while(!colaDeNodos1.isEmpty() && !colaDeNodos2.isEmpty()) {
			 NodoMVias<K,V> nodoActual1= colaDeNodos1.poll();
			 NodoMVias<K,V> nodoActual2= colaDeNodos2.poll();
			 for(int i=0; i<orden-1; i++) {
				 if(!nodoActual1.esHijoVacio(i)&&!nodoActual2.esHijoVacio(i) ) {
					 colaDeNodos1.offer(nodoActual1.getHijo(i));
					 colaDeNodos2.offer(nodoActual2.getHijo(i));
				 }
				 if(nodoActual1.esHijoVacio(i)!=nodoActual2.esHijoVacio(i)) {
					 return false;
				 }
			 }
			 
			 if(!nodoActual1.esHijoVacio(orden-1)&&!nodoActual2.esHijoVacio(orden-1) ) {
				 colaDeNodos1.offer(nodoActual1.getHijo(orden-1));
				 colaDeNodos2.offer(nodoActual2.getHijo(orden-1));
			 }
			 if(nodoActual1.esHijoVacio(orden-1)!=nodoActual2.esHijoVacio(orden-1)) {
				 return false;
			 }
			 
		 }

		return true;
	}

    @Override
    public List<K> mostrarHijosDeUnNivelDado(int nivelObjetivo) {
        List<K> recorrido= new ArrayList<>();
        mostrarHijosDeUnNivelDado(this.raiz,recorrido,0,nivelObjetivo);
     return recorrido;
    }

    private void mostrarHijosDeUnNivelDado(NodoMVias<K, V> nodoActual,List<K> recorrido,int nivelActual,int nivelObjetivo) {
    if(NodoMVias.esNodoVacio(nodoActual)){
          return;
    }
    
    for(int i=0; i<nodoActual.nroDeClavesNoVacias(); i++){
        mostrarHijosDeUnNivelDado(nodoActual.getHijo(i), recorrido, nivelActual+1, nivelObjetivo);// este me da todos los hijos de la izquierda
        if(nivelActual==nivelObjetivo){
           recorrido.add(nodoActual.getClave(i)); 
        }
    }
        mostrarHijosDeUnNivelDado(nodoActual.getHijo(nodoActual.nroDeClavesNoVacias()), recorrido, nivelActual+1, nivelObjetivo); // y este me da todos los hijos de la derecha
    }

    @Override
    public int mostrarNivelDeUnaClaveDada(K claveABuscar) {
        if(this.buscar(claveABuscar)!=null){
         return mostrarNivelDeUnaClaveDada(this.raiz,claveABuscar,0);   
        }
         return -1;
    }
    
    private int mostrarNivelDeUnaClaveDada(NodoMVias<K,V> nodoActual,K claveABuscar,int nivelActual){
        if(NodoMVias.esNodoVacio(nodoActual)){
            return 0;
        }
        int nivel=0;
        for(int i=0; i<nodoActual.nroDeClavesNoVacias(); i++){
        nivel+=mostrarNivelDeUnaClaveDada(nodoActual.getHijo(i), claveABuscar, nivelActual+1);
        if(claveABuscar.compareTo(nodoActual.getClave(i))==0){
            return nivelActual;
        }
        }
        nivel+=mostrarNivelDeUnaClaveDada(nodoActual.getHijo(nodoActual.nroDeClavesNoVacias()), claveABuscar, nivelActual+1);
        return nivel;        
    }
    
   

    @Override
    public int cantidadDeDatosVacios() {
     return cantidadDeDatosVacios(this.raiz);
    }
    
    private int cantidadDeDatosVacios(NodoMVias<K,V> nodoActual){
        if(NodoMVias.esNodoVacio(nodoActual)){
            return 0;
        }
        int cantDatoVacio=0;
        for(int i=0; i<orden-1; i++){
           cantDatoVacio+=cantidadDeDatosVacios(nodoActual.getHijo(i));
         if(nodoActual.esClavesVacia(i)){
             cantDatoVacio++;
         }
        } 
        cantDatoVacio+=cantidadDeDatosVacios(nodoActual.getHijo(orden-1));
        return cantDatoVacio;
    }

    @Override
    public boolean esBalanceado(K claveABuscar) {
      return false; 
    }

    @Override
    public int cantidadDeNodosCon2HijosEvitandoUnNivel(int nivelObjetivo) {
        return cantidadDeNodosCon2HijosEvitandoUnNivel(this.raiz,0,nivelObjetivo);
    }

    private int cantidadDeNodosCon2HijosEvitandoUnNivel(NodoMVias<K, V> nodoActual, int nivelActual, int nivelObjetivo) {
        if (NodoMVias.esNodoVacio(nodoActual)) {
            return 0;
        }

        int cantidad = 0;
        cantidad += cantidadDeNodosCon2HijosEvitandoUnNivel(nodoActual.getHijo(0), nivelActual + 1, nivelObjetivo);
        
        for (int i = 0; i < orden-1; i++) {

            cantidad += cantidadDeNodosCon2HijosEvitandoUnNivel(nodoActual.getHijo(i+1), nivelActual + 1, nivelObjetivo);
            int cantInd=nodoActual.cantidadDeHijosNoVacios();
            if(cantInd==2 && nivelActual!=nivelObjetivo){
                return cantidad+1;
            }
        }
       // cantidad+=cantidadDeNodosCon2HijosEvitandoUnNivel(nodoActual.getHijo(orden-1), nivelActual+1, nivelObjetivo);
        return cantidad;

    }

    @Override
    public boolean verificarSiElArbolTienePorLoMenosUnNodoCompleto() {
     
        Queue<NodoMVias<K,V>> colaDeNodos=new LinkedList<>();
        colaDeNodos.offer(this.raiz);
        boolean esNodoCompleto=true;
        while(!colaDeNodos.isEmpty()){
            NodoMVias<K,V> nodoActual= colaDeNodos.poll();
            for(int i=0; i<orden-1; i++){
                if(!nodoActual.esHijoVacio(i)){
                    colaDeNodos.offer(nodoActual.getHijo(i));
                } else {
                    esNodoCompleto=false;
                }
            }
            if(!nodoActual.esHijoVacio(orden-1)){
                colaDeNodos.offer(nodoActual.getHijo(orden-1));
            }else {
                esNodoCompleto=false;
            }
            if(esNodoCompleto){
                return esNodoCompleto;
            }
        }
       
        return false;
    }


}
