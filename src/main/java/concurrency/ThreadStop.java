package concurrency;

/**
 * Ejemplo de como se detiene un subproceso.
 * <br><br>
 * Fuentes:
 * <a href="https://jenkov.com/tutorials/java-concurrency/creating-and-starting-threads.html">Creating and Starting Java Threads</a>
 * <a href="https://es.sawakinome.com/articles/words/difference-between-pause-and-stop.html">Pausa vs Detener</a>
 *
 * @author Ruso
 */

public class ThreadStop {

	private static class Subproceso implements Runnable {

		private final Thread subproceso;
		private boolean stopped;

		public Subproceso(String name) {
			subproceso = new Thread(this, name);
		}

		@Override
		public void run() {

			// Mientras el subproceso no este detenido
			while (!isStopped()) {

				System.out.println(subproceso.getName() + " running");

				try {
					Thread.sleep(1000L); // Pausa el subproceso
				} catch (InterruptedException e) {
					e.printStackTrace();
				}

			}
		}

		private void ejecutar() {
			subproceso.start();
		}

		public synchronized void stop() { // TODO Hace falta que lo sincronize?
			stopped = true;
		}

		/* Reemplaze el metodo keepRunning() por este, ya que es mas simple y tiene mas sentido comprobar el estado del
		 * subproceso de esta manera. */
		private synchronized boolean isStopped() {
			return stopped;
		}

	}

	public static void main(String[] args) throws InterruptedException {

		Subproceso subproceso = new Subproceso("A");

		subproceso.ejecutar();

		// Pausa el hilo principal antes de detener el subproceso
		Thread.sleep(4L * 1000L);

		subproceso.stop();

	}

}