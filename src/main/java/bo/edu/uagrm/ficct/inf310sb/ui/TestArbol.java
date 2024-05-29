package bo.edu.uagrm.ficct.inf310sb.ui;

import bo.edu.uagrm.ficct.inf310sb.arboles.ArbolBinarioBusqueda;
import bo.edu.uagrm.ficct.inf310sb.arboles.ArbolMViasBusqueda;
import bo.edu.uagrm.ficct.inf310sb.arboles.IArbolBusqueda;
import bo.edu.uagrm.ficct.inf310sb.arboles.NodoBinario;
import java.util.Scanner;

public class TestArbol {
	
	public static void main (String[] arguments) {
		IArbolBusqueda<Integer, String> arbolPrueba =new ArbolBinarioBusqueda<>();
                
                arbolPrueba= new ArbolMViasBusqueda<>(3);
		          Scanner entrada= new Scanner(System.in);
		
		          System.out.println("Elija un tipo de Arbol(ABB, AMV ): ");
                          String tipoArbol= entrada.next();
                          switch(tipoArbol){
                              case "ABB":
                                  arbolPrueba = new  ArbolBinarioBusqueda<>();
                                  break;
                              case "AMV":
                                  arbolPrueba= new ArbolMViasBusqueda<>(4);
                                  break;
                              default:
                                  
                          }
		/* arbolPrueba.insertar(20, "Azul");
		 arbolPrueba.insertar(10, "Naranja");
		 arbolPrueba.insertar(30, "Naranja");
		 arbolPrueba.insertar(29, "Naranja");
		 arbolPrueba.insertar(31, "Naranja");
		 arbolPrueba.insertar(9, "Naranja");
		 arbolPrueba.insertar(14, "Zapato"); 
	         arbolPrueba.insertar(99, "Jeans");
                 arbolPrueba.insertar(12, "tipoArbol");*/
		// arbolPrueba.insertar(100, "Amarillo");
		// arbolPrueba.insertar(77, "Naranja");
	//	 arbolPrueba.insertar(74, "Naranja");
            //     arbolPrueba.insertar(8, "Naranja");

            //       System.out.println("bo.edu.uagrm.ficct.inf310sb.ui.TestArbol.main(): "+arbolPrueba.contarNodosConAmbosHijosPostOrden());
		// arbolPrueba.insertar(98, "Jeans");
        IArbolBusqueda<Integer,String> arbolPrueba3= new ArbolBinarioBusqueda<>();    
         arbolPrueba3= new ArbolMViasBusqueda<>(4);
	
            arbolPrueba.insertar(80, "h");
            arbolPrueba.insertar(120, "j");
            arbolPrueba.insertar(200, "h");
            arbolPrueba.insertar(50, "ñ");
            arbolPrueba.insertar(70, "n");
            arbolPrueba.insertar(75, "y");//75
            arbolPrueba.insertar(72, "j");
            arbolPrueba.insertar(98, "jj");
            arbolPrueba.insertar(110, "jj");
            arbolPrueba.insertar(130, "kk"); 
            arbolPrueba.insertar(140, "kk");
            arbolPrueba.insertar(150, "Negro");
            arbolPrueba.insertar(134, "Naranja");
            arbolPrueba.insertar(160, "Cafe");
            arbolPrueba.insertar(170, "Camisa");
            arbolPrueba.insertar(190, "Mesa");
            arbolPrueba.insertar(158, "Mesa");
           /* arbolPrueba.insertar(400, "Camisa");
            arbolPrueba.insertar(500, "Mesa");
            arbolPrueba.insertar(560, "Mesa");*/
           
            arbolPrueba.insertar(76, "Mesa");
            arbolPrueba.insertar(77, "Mesa");
            arbolPrueba.insertar(78, "Mesa");
         
         /*    arbolPrueba.insertar(125, "kk");
              arbolPrueba.insertar(145, "kk");
              
                 arbolPrueba.insertar(45, "kk");
              arbolPrueba.insertar(55, "kk");
                 arbolPrueba.insertar(76, "kk");
             */
         
            /******************************************/
            /*arbolPrueba3.insertar(80, "h");
            arbolPrueba3.insertar(120, "j");
            arbolPrueba3.insertar(200, "h");
            arbolPrueba3.insertar(50, "ñ");
            arbolPrueba3.insertar(70, "n");
            arbolPrueba3.insertar(75, "y");
            arbolPrueba3.insertar(72, "j");
            arbolPrueba3.insertar(98, "jj");
            arbolPrueba3.insertar(110, "jj");
            arbolPrueba3.insertar(130, "kk");
            arbolPrueba3.insertar(140, "kk");
            arbolPrueba3.insertar(150, "Negro");
            arbolPrueba3.insertar(134, "Naranja");
            arbolPrueba3.insertar(160, "Cafe");
            arbolPrueba3.insertar(170, "Camisa");
            arbolPrueba3.insertar(190, "Mesa");
          //  arbolPrueba3.insertar(158, "Mesa");
            arbolPrueba3.insertar(400, "Camisa");
            arbolPrueba3.insertar(500, "Mesa");
            arbolPrueba3.insertar(560, "Mesa");*/
       

		
	     //     System.out.println( arbolPrueba.cantidadDeHijosIzquierdosNoVaciosIterativo());
		 
		
		NodoBinario<Integer, String> nodoPruebaBinario= new NodoBinario<>();
		 nodoPruebaBinario.setClave(100);
		 nodoPruebaBinario.setValor("Naranja");	 
	
		 System.out.println(arbolPrueba);
                 System.out.println("Cantidad De Nodo completo: "+arbolPrueba.cantidadDeHijosConNodoCompleto());
		 System.out.println("Por Niveles: "+ arbolPrueba.recorridoPorNiveles());
		 System.out.println("PreOrden Iterativo: "+ arbolPrueba.recorridoEnPreOrden());
		 System.out.println("PreOrden Recursivo: "+ arbolPrueba.recorridoEnPreOrdenRec());
		 System.out.println("PostOrden: "+arbolPrueba.recorridoEnPostOrden()); 
		// System.out.println("(PostOrden)El sucesor del numero " + nodoPruebaBinario.getClave()+" es " + arbolPrueba.sucesorDeUnNodoEnPostOrden(nodoPruebaBinario));
		 System.out.println("Recursivo Inorden: " + arbolPrueba.recorridoEnInOrdenRec());
		 System.out.println("Iterativo Inorden: "+arbolPrueba.recorridoEnInOrden());
		 System.out.println("Altura: "+ arbolPrueba.altura());
                 
		
		// System.out.println("Hijos Derechos Iterativo: "+ arbolPrueba.cantidadDeHijosDerechosIterativo());
		 
		System.out.println("Hijos Derechos Recursivo: "+ arbolPrueba.cantidadDeHijosDerechos());
                System.out.println("Tamaño: "+ arbolPrueba.size());
		 
		// System.out.println("Nodos Completos: "+ arbolPrueba.cantidadDeHijosConNodoCompleto());
		 
		 //System.out.println("Nodos Completos en nivel: "+ arbolPrueba.tieneNodosCompletosEnNivel(1));
		 
		// System.out.println("Es Balanceado: "+ arbolPrueba.esArbolBalanceado());
		
		// System.out.println("obtener clave anterior: "+ arbolPrueba.Eliminar(15));
		
		 //System.out.println("Por Niveles: "+ arbolPrueba.recorridoPorNiveles());
		
		// System.out.println(arbolPrueba.dibujarRecorrido((ArrayList<Integer>) arbolPrueba.recorridoPorNiveles()));
		
		 System.out.println(arbolPrueba.claveMayorDeUnNivel(1));

		// System.out.println("(Recursivo)La cantidad de nodos en el nivel es: "+arbolPrueba.contarNodosConAmbosHijos(3));
		 
		// System.out.println("(Iterativo)La cantidad de nodos en el nivel es: "+arbolPrueba.contarNodosConAmbosHijosIterativo(3));
		 
		//System.out.println("(Iterativo)El padre mas cercano es: " + arbolPrueba.padreMasCercano(11, 13));
	
		//System.out.println("(Recursivo)El padre mas cercano es: " + arbolPrueba.padreMasCercanoRecursivo(11, 13));
		 System.out.println("La claves de nivel son: "+ arbolPrueba.mostrarTodasLasClavesDeUnNivel(2));
		 
		// System.out.println( arbolPrueba.mostrarCentroDeNivelesImpares());
		 
		 
		/// System.out.println(arbolPrueba.buscar(11));
		//System.out.println(arbolPrueba.buscarRecursivo(11));
		 
		 
		 //System.out.println("Hijos izquierdos recursivo: "+ arbolPrueba.cantidadDeHijosIzquierdosNoVacios());
		 
		
               //System.out.println("Recorrido Por niveles: "+ arbolPrueba.recorridoPorNiveles());
                
               // System.out.println("Recorrido Por niveles: "+ arbolPrueba.recorridoPorNiveles());

                //System.out.println("Cantidad de Nodos No vacios; "+ arbolPrueba.cantDatosVaciosMVias());
             System.out.println("Obtener Claves Dado Un Nivel: "+ arbolPrueba.obtenerClavesDadoUnNivelDado(3));
              System.out.println("Obtener Claves Dado Un Nivel Recursivo: "+ arbolPrueba.mostrarHijosDeUnNivelDado(3));
              System.out.println("Nivel de una claveABuscar: "+ arbolPrueba.mostrarNivelDeUnaClaveDada(nodoPruebaBinario.getClave()));
               System.out.println("Es balanceado: "+ arbolPrueba.esBalanceado(nodoPruebaBinario.getClave()));
               System.out.println("cantidad de Hijos con mas de 2 hijos sin contar nivel="+ arbolPrueba.cantidadDeNodosCon2HijosEvitandoUnNivel(5));
            //   System.out.println("cantidadDeDatosVacios: "+ arbolPrueba.cantidadDeDatosVacios());
               // System.out.println("cantidadDeDatosVacios: "+ arbolPrueba.mostrarNivelDeUnaClaveDada(75));
            System.err.println("Es Arbol es Perfecto? :"+ arbolPrueba.esArbolPerfectamenteBalanceado());
	         //   System.out.println("Cantidad de Hojas en un arbol MVias: " + arbolPrueba.cantDeNodosHojasMVias());
                    System.out.println("Tiene por lo menos un completo: " + arbolPrueba.verificarSiElArbolTienePorLoMenosUnNodoCompleto());
	           // System.out.println("Son Arboles Similares: " + arbolPrueba.sonArbolesSimilares1((ArbolMViasBusqueda<Integer, String>) arbolPrueba3));
	            
            
   		// System.out.println("Hijos izquierdos recursivo: "+ arbolPrueba.obtenerClavesDadoUnNivelDado(3));
	}

}
