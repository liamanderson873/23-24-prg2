public class CentroComercial {
    public static void main(String[] args) {
        final int MINUTOS_JORNADA = 12 * 60; 
        
        int[] clientesAtendidosPorCaja = { 0, 0, 0, 0, 0 }; 
        int[] tiempoAtencionRestante = { 0, 0, 0, 0, 0 }; 
        boolean[] cajaEstaLibre = { true, true, true, true, true };
        
        int clientesEnCola = 0;
        int minutosSinCola = 0;
        int totalProductosVendidos = 0;
        boolean haLlegadoNuevoCliente = false;
        boolean esCajaExtraHabilitada = false; 

        for (int minutoActual = 1; minutoActual < MINUTOS_JORNADA; minutoActual++) {

           
            if (Math.random() * 100 < 40) { 
                haLlegadoNuevoCliente = true;
                clientesEnCola++; 

                for (int i = 0; i < cajaEstaLibre.length; i++) {
                    if (i == 4 && !esCajaExtraHabilitada) continue;

                    if (cajaEstaLibre[i] && clientesEnCola > 0) {
                        clientesAtendidosPorCaja[i]++;
                        cajaEstaLibre[i] = false; 
                        // Genera un tiempo de atención aleatorio entre 5 y 15 minutos
                        tiempoAtencionRestante[i] = ((int) (Math.random() * 11)) + 5;
                        totalProductosVendidos += tiempoAtencionRestante[i];
                        clientesEnCola--;
                        break; 
                    }
                }
            }

            for (int i = 0; i < tiempoAtencionRestante.length; i++) {
                if (tiempoAtencionRestante[i] > 0) {
                    tiempoAtencionRestante[i]--;
                    if (tiempoAtencionRestante[i] == 0) {
                        cajaEstaLibre[i] = true;
                    }
                }
            }

            if (clientesEnCola == 0) minutosSinCola++;
          
           
            imprimirEstadoMinuto(minutoActual, haLlegadoNuevoCliente, clientesEnCola, tiempoAtencionRestante, esCajaExtraHabilitada);
            haLlegadoNuevoCliente = false;
            esCajaExtraHabilitada = (clientesEnCola >= 15);
        }

        imprimirReporteFinal(clientesAtendidosPorCaja, totalProductosVendidos, minutosSinCola, clientesEnCola);
    }


    private static void imprimirEstadoMinuto(int min, boolean llego, int cola, int[] cajas, boolean extra) {
        System.out.println("--------------------------------------------------------------");
        System.out.printf("MINUTO %03d | %-12s | COLA: %d\n", 
            min, (llego ? "NUEVO CLIENTE" : "SIN CAMBIOS"), cola);
        
        for (int i = 0; i < 4; i++) {
            System.out.printf(" Caja%d:[%d] |", (i + 1), cajas[i]);
        }

        if (cajas[4] > 0 || extra) {
            System.out.printf(" Caja5:[%d]\n", cajas[4]);
        } else {
            System.out.println(" Caja5:[CERRADA]");
        }
    }

    
    private static void imprimirReporteFinal(int[] atendidos, int vendidos, int sinCola, int colaFinal) {
        System.out.println("\n" + "=".repeat(20) + " RESUMEN FINAL " + "=".repeat(20));
        for (int i = 0; i < atendidos.length; i++) {
            System.out.printf("Caja %d atendió a: %d clientes\n", (i + 1), atendidos[i]);
        }
        
        int total = 0;
        for (int n : atendidos) total += n;

        System.out.println("-".repeat(55));
        System.out.println("Total personas atendidas  : " + total);
        System.out.println("Total productos vendidos  : " + vendidos);
        System.out.println("Minutos con cola vacía    : " + sinCola);
        System.out.println("Clientes finales en cola  : " + colaFinal);
        System.out.println("=".repeat(55));
    }
}