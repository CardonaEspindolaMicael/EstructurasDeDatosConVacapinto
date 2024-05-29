package bo.edu.uagrm.ficct.inf310sb.arboles;

import java.util.ArrayList;
import java.util.List;

public interface IArbolBusqueda<K extends Comparable<K>,V> {
 void vaciar();
boolean esArbolVacio();
boolean contiene(K clave);
int size();
int altura();
int nivel();
K minimo();
K maximo();
void insertar (K clave, V valor);
V Eliminar (K clave);
V buscar(K clave);
V buscarRecursivo(K clave);
List<K>recorridoEnInOrdenRec();
List<K>recorridoEnPreOrden();
List<K>recorridoEnPostOrden();
List<K>recorridoPorNiveles();
List<K>recorridoEnInOrden();
List<K>recorridoEnPreOrdenRec();
int cantidadDeHijosDerechos();
int cantidadDeHijosDerechosIterativo();
int cantidadDeHijosConNodoCompleto();
boolean tieneNodosCompletosEnNivel(int nivelObjetivo);
boolean esArbolBalanceado();
K sucesorDeUnNodoEnPostOrden(NodoBinario<K,V> nodoABuscarPredecesor);
void insertarRecursivo(K claveAInsertar, V valorAInsertar);
String dibujarRecorrido(ArrayList<K> recorrido);
K claveMayorDeUnNivel(int nivel);
int contarNodosConAmbosHijos(int nivel);
int contarNodosConAmbosHijosIterativo(int nivel);
K padreMasCercano(K hijo1, K hijo2);
K padreMasCercanoRecursivo(K hijo1, K hijo2);
List<K> mostrarTodasLasClavesDeUnNivel(int nivel);
List<K> mostrarCentroDeNivelesImpares();
List<K> recorridoMix();
int cantidadDeHijosIzquierdosNoVacios();
int cantidadDeHijosIzquierdosNoVaciosIterativo();
List<K> obtenerClavesDadoUnNivelDado(int nivelObjetivo);
boolean esArbolPerfectamenteBalanceado();
int cantDatosVaciosMVias();
int cantDatosVaciosMViasRec();
int cantDeNodosHojasMVias();
boolean sonArbolesSimilares(ArbolMViasBusqueda<K, V> arbol);
boolean sonArbolesSimilares1(ArbolMViasBusqueda<K, V> arbol);
//modelo de examen
List<K> mostrarHijosDeUnNivelDado(int nivelObjetivo);
int mostrarNivelDeUnaClaveDada(K claveABuscar);
int cantidadDeDatosVacios();
boolean esBalanceado(K claveABuscar);
int cantidadDeNodosCon2HijosEvitandoUnNivel(int nivelObjetivo);
boolean verificarSiElArbolTienePorLoMenosUnNodoCompleto();
}
