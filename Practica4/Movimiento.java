import java.util.ArrayList;
public class Movimiento
{
/*************************************************************************
* Constantes generales de la clase Movimiento
**************************************************************************/
/** Constante que Indica el limite inferior de x */
private static final int LIMINFX=10;
/** Constante que Indica el limite superior de x */
private static final int LIMSUPX=720;
/** Constante que Indica el limite superior de y */
private static final int LIMSUPY=480;
/** Constante que Indica el limite inferior de y de la bala */
private static final int LIMINFYBALA=15;
/** Constante que Indica el limite superior de y de la bala */
private static final int LIMSUPYBALA=750;
/**************************************************************************
* Variables generales de la clase Movimiento
**************************************************************************/
/** Variable que indica lista abierta de invasores */
private ArrayList listInvasor = new ArrayList();
/** Variable que indica lista abierta de nave defensora */
private ArrayList listDisparo = new ArrayList();
/** Variable que indica lista abierta de disparos de aliens */
private ArrayList listLaser = new ArrayList();
/** Variable que indica retardo con el que disparan aliens */
private int retardo;
/** Variable que indica numero aleatorio de alien que dispara */
private int valorAleat;
/*************************************************************************
/** Descrip: Constructor de la clase Movimiento
* Inicializa las variables generales de la clase
*
* @param ninguno
* @return nada
**************************************************************************/
public Movimiento (ArrayList listInvasor, ArrayList listDisparo,ArrayList listLaser)
{
this.listDisparo = listDisparo;
this.listInvasor = listInvasor;
this.listLaser = listLaser;
retardo = 0;
}
//*************************************************************************
/** Nombre: esImpacto
* Descrip: Verifica que se ha impactado la bala disparada
* por la nave defensora con un alien
*
* @param: Ninguno
* @return:
* 0 - hay impacto
* 1 - no lo hay
**************************************************************************/
public boolean esImpacto()
{
/** Recorre toda la lista de invasores y de disparo */
for (int i=0; i<listInvasor.size(); i++) {

for (int j=0; j<listDisparo.size(); j++) {
/** Si hay impacto entre el invasor y la nave defensora .. */
if (((Grafico) listDisparo.get(j)).
hayImpacto((Grafico)listInvasor.get(i))) {
/** .. elimina el invasor de la lista, .. */
listInvasor.remove(i);
/** .. elimina el disparo de la lista y ..*/
listDisparo.remove(j);
/** .. establece capacidad de la lista */
listDisparo.trimToSize();
/** Indica que hay impacto */
return true;
}
}
}
/** En otro caso, no hay impacto */
return false;
}
//*************************************************************************
/** Nombre: estaFueraLim
* Descrip: Verifica que cualquiera de los aliens de la lista
* no llega al final del grafico
*
* @param: Ninguno
* @return:
* 0 - llegan al final
* 1 - no lo hace
**************************************************************************/
public boolean estaFueraLim()
{
if (((Grafico) listInvasor.get(listInvasor.size()-1)).devPosY()>LIMSUPY-10)
return true;
return false;
}
//*************************************************************************
/** Nombre: mueveBalas
* Descrip: Mueve las balas en el grafico
*
* @param: Ninguno
* @return: Nada
**************************************************************************/
public void mueveBalas()
{
/** Recorre toda la lista de disparos .. */
for (int i=0; i<listDisparo.size(); i++) {
/** .. si se salen de los margenes, elimina la bala */
if ((((Grafico) listDisparo.get(i)).devPosY()<LIMINFYBALA) ||(((Grafico) listDisparo.get(i)).devPosY()>LIMSUPYBALA))
listDisparo.remove(i);
/** si no, mueve el disparo */
else
((Grafico) listDisparo.get(i)).mover(20);
/** Vuelve a establecer capacidad de la lista */
listDisparo.trimToSize();
}
}
//*************************************************************************
/** Nombre: mueveAliens
* Descrip: Mueve los aliens en el grafico
*
* @param: Ninguno
* @return: Nada
**************************************************************************/
public void mueveAliens()
{
/** Si es posible mover al Oeste */
if (sePuedeMoverOeste()) {
/** Movemos el alien al Sur y luego al Oeste */
moverSurOeste();
}
/** Si no es asi */
else {

/** Si es posible moverlo al Este */
if (sePuedeMoverEste()) {
/** Movemos al sur y luego al Este */
moverSurEste();
}
/** En otro caso */
else {
/** Solo mueve el alien */
moverSolo();
}
}
}
//*************************************************************************
/** Nombre: sePuedeMoverOeste
* Descrip: Verifica si se puede mover al Oeste, verificando limites
* superior e inferior.
* Previamente tiene movimiento Este
*
* @param: Ninguno
* @return:
* 0 - se puede mover
* 1 - no es asi
**************************************************************************/
private boolean sePuedeMoverOeste()
{
/** Recorre todos la lista de invasores y analiza los limites */
for (int i=0; i<listInvasor.size(); i++)
/** Si esta dentro de los limites, se puede mover al Oeste */
if ((((Grafico) listInvasor.get(i)).devDirecMov()=='E') &&(((Grafico) listInvasor.get(i)).devPosX()>LIMSUPX) &&(((Grafico) listInvasor.get(i)).devPosY()<LIMSUPY)) {
return true;
}
/** En otro caso, no se puede mover */
return false;
}
//*************************************************************************
/** Nombre: sePuedeMoverEste
* Descrip: Verifica si se puede mover al Este, verificando limites
* superior e inferior.
* Previamente tiene movimiento Oeste
*
* @param: Ninguno
* @return:
* 0 - se puede mover
* 1 - no es asi
**************************************************************************/
private boolean sePuedeMoverEste()
{
/** Recorre todos la lista de invasores y analiza los limites */
for (int i=0; i<listInvasor.size(); i++) {
/** Si esta dentro de los limites, se puede mover al Este */
if ((((Grafico) listInvasor.get(i)).devDirecMov()=='O') &&(((Grafico) listInvasor.get(i)).devPosX()<LIMINFX) &&(((Grafico) listInvasor.get(i)).devPosY()<LIMSUPY))
return true;
}
/** En otro caso, no se puede mover */
return false;
}
//*************************************************************************
/** Nombre: moverSurOeste
* Descrip: Cambia la direccion a Sur, mueve el alien y la cambia
* de nuevo a Oeste.
*
* @param: Ninguno
* @return: Nada
**************************************************************************/
private void moverSurOeste()
{
/** Recorre todos los invasores y realiza dichos pasos */
for (int i=0; i<listInvasor.size(); i++) {
((Grafico) listInvasor.get(i)).cambiarDir('S');
((Grafico) listInvasor.get(i)).mover(5);
((Grafico) listInvasor.get(i)).cambiarDir('O');
}
}
//*************************************************************************
/** Nombre: moverSurEste
* Descrip: Cambia la direccion a Sur, mueve el alien y la cambia
* de nuevo a Este.
*
* @param: Ninguno
* @return: Nada
**************************************************************************/
private void moverSurEste()
{
for (int i=0; i<listInvasor.size(); i++) {
((Grafico) listInvasor.get(i)).cambiarDir('S');
((Grafico) listInvasor.get(i)).mover(5);
((Grafico) listInvasor.get(i)).cambiarDir('E');
}
}
//*************************************************************************
/** Nombre: moverSolo
* Descrip: Mueve el alien, teniendo en cuenta su direccion.
* Se ira incrementando las posiciones que se mueven
* cuanto menos numero de aliens haya
*
* @param: Ninguno
* @return: Nada
**************************************************************************/
private void moverSolo()
{
/** Calcula movimiento segun la longitud de la lista de invasores */
int movimiento = 20 - (int) (listInvasor.size() / 4);
/** Mueve todos los aliens */
for (int i=0; i<listInvasor.size(); i++)
((Grafico) listInvasor.get(i)).mover(movimiento);
}
//*************************************************************************
/** Nombre: hayDisparoAlien
* Descrip: Selecciona de modo aleatorio el alien que va a disparar
* Las balas se disparan de modo aleatorio
*
* @param: Ninguno
* @return:
* 0 - existe disparo alien
* 1 - no existe
**************************************************************************/
public boolean hayDisparoAlien()
{
/** Escoge un valor aleat. entre 0 y el tamaño de la lista de inv. */
valorAleat=(int)(Math.random()*listInvasor.size());
if (valorAleat <= listInvasor.size()) {
/** Si no existe retardo posible */
if (retardo <= 0) {
/** vuelve a poner retardo a 15 y devuelve verdadero */
retardo = 15;
return true;
}
/** En otro caso, decrementa el retardo */
else {
retardo --;
return false;
}
}
return false;
}
//*************************************************************************
/** Nombre: creaDisparoAlien
* Descrip: Recalcula las posiciones de disparo de los aliens y
* crea dibujo de dichos disparos
*
* @param: Ninguno
* @return: Nada
**************************************************************************/
public void creaDisparoAlien()
{
int nuevaPosX = ((Grafico) listInvasor.get(valorAleat)).devPosX()+22;
int nuevaPosY = ((Grafico) listInvasor.get(valorAleat)).devPosY()+40;
/** Crea disparo alien con orientacion 'S' segun coordenadas nuevas */
Grafico naveAliens = new Grafico(nuevaPosX, nuevaPosY, 'S');
/** Añade en la lista de laser dicho grafico */
listLaser.add(naveAliens);
}
//*************************************************************************
/** Nombre: mueveDisparoAlien
* Descrip: Mueve los disparos de los aliens
*
* @param: Ninguno
* @return: Nada
**************************************************************************/
public void mueveDisparoAlien()
{
for (int i=0; i<listLaser.size(); i++)
((Grafico) listLaser.get(i)).mover(5);
}
//*************************************************************************
/** Nombre: naveColisionada
* Descrip: Detecta si hay impacto del laser con la nave defensora
*
* @param: nave: Grafico de la nave defensora
* invaders: juego de los Invasores
* @return:
* 0 - se ha colisionado la nave
* 1 - no lo ha hecho
**************************************************************************/
public boolean naveColisionada(Grafico nave, Invaders invaders)
{
/** Recorre toda la lista de laser */
for (int i=0; i<listLaser.size(); i++) {
/** Si hay impacto, elimina el laser de la lista */
if (((Grafico) listLaser.get(i)).hayImpacto(nave)) {
listLaser.remove(i);
return true;
}
}
return false;
}
}
