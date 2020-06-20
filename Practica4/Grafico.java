import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JPanel;
/*************************************************************************
**************************************************************************
**
** Clase principal de la practica
** Dibuja el entorno grafico con todas las imagenes, de los alienigenas,
** la nave defensora, los disparos y el laser (el disparo de los aliens).
** Detecta las teclas pulsadas y actua en consecuencia
** Ejecuta el hilo 'implicito' verificando siguiendo las reglas del juego:
** - Si impacta laser con nave defensora o llega al final de la pantalla
** los aliens, se pierde la partida.
** - Si se derriban todos los aliens, se gana la partida.
**
**************************************************************************
**************************************************************************/
public class Invaders extends Canvas implements Runnable, KeyListener
{
/*************************************************************************
* Constantes generales de la clase principal (Invaders)
**************************************************************************/
/** Constante que solicita Eclipse */
private static final long serialVersionUID = 1L;
/** Constante que indica maximo tamaño de pantalla de x */
private static final int MAXTAMX=800;
/** Constante que indica maximo tamaño de pantalla de y */
private static final int MAXTAMY=600;
/** Constante que indica velocidad de refresco del hilo */
private static final int VELOCIDAD=80;
/** Constante que indica maximo de alienigenas por fila */
private static final int MAXFIL=10;
/** Constante que indica maximo de alienigenas por columna */
private static final int MAXCOL=5;

/*************************************************************************
* Variables generales de la clase principal (Invaders)
**************************************************************************/
/** Variable que indica lista abierta de invasores */
private ArrayList listInvasor = new ArrayList();
/** Variable que indica lista abierta de nave defensora */
private ArrayList listDisparo = new ArrayList();
/** Variable que indica lista abierta de disparos de aliens */
private ArrayList listLaser = new ArrayList();
/** Variable que indica imagen de la nave espacial */
private Image imNaveEspacial;
/** Variable que indica imagen de la nave alienigena */
private Image imNaveAlien;
/** Variable que indica imagen del disparo */
private Image imDisparo;
/** Variable que indica imagen del disparo de los aliens */
private Image imLaser;
/** Variables que indican las posiciones x e y en pantalla */
private int posX, posY;
/** Variable que indica fin de juego. 0: No lo es, 1: lo es */
private boolean finJuego = false;
/** Variable que indica inicio de juego. 0: No lo es, 1: lo es */
private boolean inicioJuego = true;
/** Variable que indica cadena de mensaje a mostrar en entorno grafico */
private String mensaje = "";
/** Variable que gestiona movimientos de los elementos del juego */
private Movimiento movSpace;
/** Variable que gestiona los movimientos de coordenadas */
private Grafico nave;
/** Variable que indica hilo implicito del juego */
private Thread hilo;
/*************************************************************************
/** Descrip: Constructor de la clase Invaders
* Inicializa las variables generales de la clase
* Inicializa el grafico con todas las imagenes
*
* @param ninguno
* @return nada
**************************************************************************/
public Invaders()
{
/** Posiciones iniciales de x e y */
posX = MAXTAMX/2;
posY = MAXTAMY-100;
/** Instancia nave con posiciones iniciales y orientacion Este */
nave = new Grafico(posX, posY, 'E');
/** Variable local que crea marco del juego */
JFrame marcoJuego = new JFrame("Space Invaders");
/** Ubica el marco empezando por la posicion (0,0) */
marcoJuego.setBounds(0,0,MAXTAMX,MAXTAMY);
/** Hace visible el marco */
marcoJuego.setVisible(true);
/** Sale del programa cuando se cierra el marco del juego */
marcoJuego.addWindowListener(new WindowAdapter(){
public void windowClosing(WindowEvent e) {
System.exit(0);
}
});
/** Variable local que indica contenedor del juego */
JPanel panelJuego = (JPanel)marcoJuego.getContentPane();
/** Reubica el marco */
setBounds(0,0,MAXTAMX,MAXTAMY);
/** Fijamos el color de fondo en negro */
setBackground(Color.black);
/** Configura el panel segun una serie de variables */
panelJuego.setPreferredSize(new Dimension(MAXTAMX,MAXTAMY));
panelJuego.setLayout(null);
panelJuego.add(this);
/** Añadimos listener de tecla */
addKeyListener(this);
/** Instanciamos el movimiento del space invader */
movSpace = new Movimiento(listInvasor, listDisparo, listLaser);
/** Inicializamos el hilo 'implicito'*/

init();
}
//*************************************************************************
/** Nombre: init
* Descrip: Inicializa las imagenes en el hilo principal
*
* @param: Ninguno
* @return: Nada
**************************************************************************/
public void init()
{
hilo = new Thread();
/** Carga imagen de la nave espacial */
imNaveEspacial = getToolkit().getImage("nave.png");
/** Carga imagen del alien */
imNaveAlien = getToolkit().getImage("alien.png");
/** Carga imagen del misil */
imDisparo = getToolkit().getImage("misil2.png");
/** Carga imagen del laser */
imLaser = getToolkit().getImage("laser.png");
/** Añade todos los invasores a la lista abierta */
for (int col=0; col<MAXCOL; col++) {
for (int fil=0; fil<MAXFIL; fil++) {
listInvasor.add(new Grafico(75+(fil*65),(50)+col*50,'E'));
}
}
/** Inicializamos el hilo principal */
hilo.start();
/** Repintamos el grafico */
repaint();
}
//************************************************************************
/** Nombre: paint
* Descrip: Dibuja los elementos del juego, asi como los mensajes
* de inicio y final de juego.
*
* @param: g: Grafico que se dibuja
* @return: Nada
**************************************************************************/
public void paint(Graphics g)
{
/** Si es inicio o fin de juego .. */
if (inicioJuego || finJuego) {
/** .. pone letras de mensaje en rojo */
g.setColor(Color.red);
/** Imprime mensaje de inicio y pide tecla */
if (inicioJuego) mostrarMsjInicio();
/** Imprime en el grafico el mensaje de inicio de juego */
g.drawString(mensaje,
(800-g.getFontMetrics().stringWidth(mensaje))/2,250);
}
/** En cualquier otro caso */
else {
/** El color del fondo sigue siendo negro */
g.setColor(getBackground());
/** Dibuja la nave espacial segun su posicion de x e y actuales */
g.drawImage(imNaveEspacial, nave.posX, nave.posY, this);
/** Dibuja en el grafico todos los invasores
tomando las posiciones x e y actuales */
for (int i=0; i<listInvasor.size(); i++) {
g.drawImage(imNaveAlien,((Grafico)listInvasor.get(i)).devPosX(),
((Grafico)listInvasor.get(i)).devPosY(), this);
}
/** Dibuja en el grafico todos los disparos */
for (int i=0; i<listDisparo.size(); i++)
g.drawImage(imDisparo,((Grafico)listDisparo.get(i)).devPosX(),
((Grafico)listDisparo.get(i)).devPosY(), this);
/** Dibuja en el grafico todos los disparos de los alienigenas */
for (int i=0; i<listLaser.size(); i++)
g.drawImage(imLaser,((Grafico)listLaser.get(i)).devPosX(),

((Grafico)listLaser.get(i)).devPosY(), this);
}
}
//************************************************************************
/** Nombre: mostrarMsjPerder
* Descrip: Guarda el texto del mensaje al perder y finaliza juego
*
* @param: Ninguno
* @return: Nada
**************************************************************************/
private void mostrarMsjPerder()
{
mensaje = "Has perdido. Fin de juego";
finJuego = true;
}
//************************************************************************
/** Nombre: mostrarMsjGanar
* Descrip: Guarda el texto del mensaje al ganar y finaliza juego
*
* @param: Ninguno
* @return: Nada
**************************************************************************/
private void mostrarMsjGanar()
{
mensaje = "ENHORABUENA!! Fin de juego";
finJuego = true;
}
//************************************************************************
/** Nombre: mostrarMsjInicio
* Descrip: Guarda el texto del mensaje al iniciar el juego
*
* @param: Ninguno
* @return: Nada
**************************************************************************/
private void mostrarMsjInicio()
{
mensaje = "Inicio de juego. Pulse una tecla para continuar";
}
//************************************************************************
/** Nombre: run
* Descrip: Metodo principal de cualquier 'Thread', heredado del
* interfaz Runnable y reutilizado, que realiza el bucle
* del juego actualizado cada 80 ms. El bucle es:
* - Verifica que se pulse tecla de inicio
* - Si no hay impactos con aliens, mueve disparos
* - Luego mueve alienigenas
* - Si genera disparo de alien, crea misil y lo mueve
* - Repinta todos los movimientos de los elementos
* Sigue las reglas del juego, que vimos previamente.
* Al perder o ganar se sale del bucle principal,
* que siempre esta en ejecucion.
*
* @param: Ninguno
* @return: Nada
* @exception: e: captura excepcion, pero no hace nada con ella
**************************************************************************/
public void run()
{
while (true) {
try {
/** Duerme el hilo el tiempo fijado de 80 ms. */
Thread.sleep(VELOCIDAD);
} catch (Exception e) {}
/** No empieza el juego hasta que no se haya pulsado tecla */
if (!inicioJuego) {
/** Si no hay impacto con aliens .. */
if (!movSpace.esImpacto()) {
/** .. mueve balas */
movSpace.mueveBalas();
/** Si se matan todos los aliens .. */
if (listInvasor.isEmpty()) {
/** .. muestra msj de ganar, repinta y sale */

mostrarMsjGanar();
repaint();
break;
}
/** Mueve aliens */
movSpace.mueveAliens();
/** Si hay disparo de alien crea su disparo */
if (movSpace.hayDisparoAlien())
movSpace.creaDisparoAlien();
/** Mueve disparo de aliens */
movSpace.mueveDisparoAlien();
/** Si la nave defensora es colisionada o
los aliens llegan al final superior de la pantalla .. */
if ((movSpace.naveColisionada(nave, this)) ||
(movSpace.estaFueraLim())) {
/** .. muestra mensaje de perder, repinta y sale */
mostrarMsjPerder();
repaint();
break;
}
}
}
/** Repinta el conjunto */
repaint();
}
}
//************************************************************************
/** Nombre: keyTyped
* Descrip: Metodo heredado de interfaz KeyListener.
* Detecta una tecla pulsada, que inicia el juego.
* Si se pulsa escape sale del juego.
*
* @param: e: Evento de tecla
* @return: Nada
**************************************************************************/
public void keyTyped(KeyEvent e)
{
/** Si se inicia el juego .. */
if (inicioJuego) {
/** .. pasamos flag a false, ya hemos empezado y repintamos */
inicioJuego = false;
repaint();
}
/** Si pulsamos escape, salimos del juego */
if (e.getKeyChar() == 27) {
System.exit(0);
}
}
//************************************************************************
/** Nombre: keyPressed
* Descrip: Metodo heredado de interfaz KeyListener.
* Detecta una tecla presionada. No usada en el juego.
*
* @param: e: Evento de tecla
* @return: Nada
**************************************************************************/
public void keyPressed(KeyEvent e)
{
}

//************************************************************************
/** Nombre: keyReleased
* Descrip: Metodo heredado de interfaz KeyListener.
* Detecta una tecla liberada.
* Si es o mueve a la izquierda, si es p a la derecha
* y si es espacio en blanco dispara la nave espacial
*
* @param: e: Evento de tecla
* @return: Nada
**************************************************************************/
public void keyReleased(KeyEvent e)
{
/** Si la tecla es 'o' ...*/
if (e.getKeyCode() == KeyEvent.VK_O) {
/** .. cambiamos direccion al Oeste y movemos nave espacial */
nave.cambiarDir('O');
nave.mover(15);
}
/** Si la tecla es 'p' ...*/
if (e.getKeyCode() == KeyEvent.VK_P) {
/** .. cambiamos direccion al Este y movemos nave espacial */
nave.cambiarDir('E');
nave.mover(15);
}
/** Si la tecla es el espacio en blanco */
if (e.getKeyCode() == KeyEvent.VK_SPACE) {
/** .. disparamos con direccion Norte */
listDisparo.add(new Grafico(nave.devPosX()+20,nave.devPosY(),'N'));
}
/** Redibujamos el conjunto del grafico */
repaint();
}
//************************************************************************
/** Nombre: main
* Descrip: Funcion principal de Invaders.
* Se encarga de instanciar y arrancar el juego
*
* @param: args: Array de cadena de argumentos
* @return: Nada
**************************************************************************/
public static void main(String[] args)
{
/** Instancia juego principal y lo arranca */
Invaders spaceInv = new Invaders();
spaceInv.run();
}
}
