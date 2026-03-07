public class CentroComercial {
    public static void main(String[] args) {
        final int MINUTOS_JORNADA = 12 * 60; 
        int[] clientesAtendidosPorCaja = { 0, 0, 0, 0, 0 }; 
        int[] tiempoAtencionRestante = { 0, 0, 0, 0, 0 }; 
        boolean[] cajaEstaLibre = { true, true, true, true, true };
        int clientesEnCola = 0;
        boolean haLlegadoNuevoCliente = false;
        int minutosSinCola = 0;
        int totalProductosVendidos = 0;
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
                        tiempoAtencionRestante[i] = ((int) (Math.random() * 100)) % 11 + 5;
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
          
            System.out.println("--------------------------------------------------------------");
            System.out.print("MINUTO " + minutoActual);
            if (haLlegadoNuevoCliente) {
                System.out.println(" - Llega 1 persona - En cola: " + clientesEnCola);
                haLlegadoNuevoCliente = false;
            } else {
                System.out.println(" - Llega 0 persona - En cola: " + clientesEnCola);
            }
            System.out.print(" Caja1:[" + tiempoAtencionRestante[0] + "] | Caja2:[" + tiempoAtencionRestante[1] + "] | Caja3:[" + tiempoAtencionRestante[2] + "] | Caja4:[" + tiempoAtencionRestante[3] + "]");
            
            if (tiempoAtencionRestante[4] > 0 || esCajaExtraHabilitada) {
                System.out.println(" Caja5:[" + tiempoAtencionRestante[4] + "] ");
            } else {
                System.out.println();
            }

            esCajaExtraHabilitada = (clientesEnCola >= 15);
        }

        for (int i = 0; i < clientesAtendidosPorCaja.length; i++) {
            System.out.println("Clientes totales en caja " + (i + 1) + ": " + clientesAtendidosPorCaja[i]);
        }
        
        int totalPersonas = 0;
        for (int n : clientesAtendidosPorCaja) totalPersonas += n;

        System.out.println("Total personas atendidas: " + totalPersonas);
        System.out.println("Total productos vendidos: " + totalProductosVendidos);
        System.out.println("Minutos con cola vacía: " + minutosSinCola);
        System.out.println("Clientes finales en cola: " + clientesEnCola);
    }
}