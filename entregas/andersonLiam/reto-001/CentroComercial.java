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
                
                
                if (cajaEstaLibre[0]) {
                    clientesAtendidosPorCaja[0]++;
                    cajaEstaLibre[0] = false; 
                    tiempoAtencionRestante[0] = ((int) (Math.random() * 100)) % 11 + 5;
                    totalProductosVendidos += tiempoAtencionRestante[0];
                    clientesEnCola--;
                } else if (cajaEstaLibre[1]) {
                    clientesAtendidosPorCaja[1]++;
                    cajaEstaLibre[1] = false; 
                    tiempoAtencionRestante[1] = ((int) (Math.random() * 100)) % 11 + 5;
                    totalProductosVendidos += tiempoAtencionRestante[1];
                    clientesEnCola--;
                } else if (cajaEstaLibre[2]) {
                    clientesAtendidosPorCaja[2]++;
                    cajaEstaLibre[2] = false; 
                    tiempoAtencionRestante[2] = ((int) (Math.random() * 100)) % 11 + 5;
                    totalProductosVendidos += tiempoAtencionRestante[2];
                    clientesEnCola--;
                } else if (cajaEstaLibre[3]) {
                    clientesAtendidosPorCaja[3]++;
                    cajaEstaLibre[3] = false; 
                    tiempoAtencionRestante[3] = ((int) (Math.random() * 100)) % 11 + 5;
                    totalProductosVendidos += tiempoAtencionRestante[3];
                    clientesEnCola--;
                } else if (esCajaExtraHabilitada && cajaEstaLibre[4]) { 
                    clientesAtendidosPorCaja[4]++;
                    cajaEstaLibre[4] = false; 
                    tiempoAtencionRestante[4] = ((int) (Math.random() * 100)) % 11 + 5;
                    totalProductosVendidos += tiempoAtencionRestante[4];
                    clientesEnCola--;
                }
            }

           
            if (tiempoAtencionRestante[0] > 0) {
                tiempoAtencionRestante[0]--;
                if (tiempoAtencionRestante[0] == 0) cajaEstaLibre[0] = true;
            }
            if (tiempoAtencionRestante[1] > 0) {
                tiempoAtencionRestante[1]--;
                if (tiempoAtencionRestante[1] == 0) cajaEstaLibre[1] = true;
            }
            if (tiempoAtencionRestante[2] > 0) {
                tiempoAtencionRestante[2]--;
                if (tiempoAtencionRestante[2] == 0) cajaEstaLibre[2] = true;
            }
            if (tiempoAtencionRestante[3] > 0) {
                tiempoAtencionRestante[3]--;
                if (tiempoAtencionRestante[3] == 0) cajaEstaLibre[3] = true;
            }
            if (tiempoAtencionRestante[4] > 0) {
                tiempoAtencionRestante[4]--;
                if (tiempoAtencionRestante[4] == 0) cajaEstaLibre[4] = true;
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