package org.opens.tanaguru.logging;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;
import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.JoinPoint.StaticPart;

/**
 * 
 * @author ADEX
 */
public class EALoggerImpl {

    /*
     * Cette méthode est appelée pour récupérer le nom du serveur où s'exécute
     * l'application.
     */
    public static String getHostname() {
        String hostname = "";
        try {
            InetAddress addr = InetAddress.getLocalHost();
            // Get hostname
            hostname = addr.getHostName();
        } catch (UnknownHostException ex) {
            Logger.getLogger(EALoggerImpl.class).error(null, ex);
        }
        return hostname;
    }
    protected Map<String, Long> timeMap = new HashMap<String, Long>();

    public void logException(Throwable exception) {
        Logger.getLogger(EALoggerImpl.class).error(getHostname() + " - " + exception, exception.getCause());
    }

    /*
     * Cette méthode est appelée à chaque fois (et avant) qu'une méthode du
     * package org.opens.tanaguru.service.* est interceptée
     */
    public void logMethodEntry(JoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs();

        // Nom de la méthode interceptée
        String name = joinPoint.getSignature().toLongString();
        StringBuffer sb = new StringBuffer("ENTERING " + name + " called with: [");

        // Liste des valeurs des arguments reçus par la méthode
        for (int i = 0; i < args.length; i++) {
            Object o = args[i];
            sb.append("'" + o + "'");
            sb.append((i == args.length - 1) ? "" : ", ");
        }
        sb.append("]");

        Logger.getLogger(EALoggerImpl.class).info(getHostname() + " - " + sb);

        timeMap.put(name, System.currentTimeMillis());
    }

    /*
     * Cette méthode est appelée à chaque fois (et après) qu'une méthode du
     * package org.opens.tanaguru.service.* est interceptée.
     * Elle reçoit en argument 'result' qui est le retour de la méthode
     * interceptée.
     */
    public void logMethodExit(StaticPart staticPart, Object result) {
        // Nom de la méthode interceptée
        String name = staticPart.getSignature().toLongString();

        float time = ((float) (System.currentTimeMillis() - timeMap.get(name))) / 1000f;
        timeMap.remove(name);

        Logger.getLogger(EALoggerImpl.class).info(getHostname() + " - " + "LEAVING " + name + " returning: [" + result + "]");

        Logger.getLogger(EALoggerImpl.class).info("Execution Time is : " + time);
    }
}
