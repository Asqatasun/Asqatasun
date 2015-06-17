/*
 * Tanaguru - Automated webpage assessment
 * Copyright (C) 2008-2015  Tanaguru.org
 *
 * This file is part of Tanaguru.
 *
 * Tanaguru is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 *
 * Contact us by mail: tanaguru AT tanaguru DOT org
 */
package org.tanaguru.logging;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;
import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.JoinPoint.StaticPart;

/**
 * 
 * @author jkowalczyk
 */
public class EALoggerImpl {

    /*
     * This method is used to retrieved the name of the server
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
     * package org.tanaguru.service.* est interceptée
     */
    public void logMethodEntry(JoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs();

        // Nom de la méthode interceptée
        String name = joinPoint.getSignature().toLongString();
        StringBuffer sb = new StringBuffer("ENTERING " + name + " called with: [");

        // Liste des valeurs des arguments reçus par la méthode
        for (int i = 0; i < args.length; i++) {
            Object o = args[i];
            sb.append("'");
            sb.append(o);
            sb.append("'");
            sb.append((i == args.length - 1) ? "" : ", ");
        }
        sb.append("]");

        Logger.getLogger(EALoggerImpl.class).info(getHostname() + " - " + sb);

        timeMap.put(name, System.currentTimeMillis());
    }

    /*
     * Cette méthode est appelée à chaque fois (et après) qu'une méthode du
     * package org.tanaguru.service.* est interceptée.
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