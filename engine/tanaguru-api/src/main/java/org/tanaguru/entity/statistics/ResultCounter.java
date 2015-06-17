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
package org.tanaguru.entity.statistics;

/**
 *
 * @author jkowalczyk
 */
public interface ResultCounter {

        /**
     *
     * @return
     */
    int getNbOfNa();

    /**
     * 
     * @param nbOfNa 
     */
    void setNbOfNa(int nbOfNa) ;

    /**
     *
     * @return
     */
    int getNbOfNmi();

    /**
     * 
     * @param nbOfNmi 
     */
    void setNbOfNmi(int nbOfNmi);
    
    /**
     *
     * @return
     */
    int getNbOfPassed() ;

    /**
     *
     * @param nbOfPassed
     */
    void setNbOfPassed(int nbOfPassed);

    /**
     *
     * @return
     */
    int getNbOfFailed();

    /**
     *
     * @param nbOfFailed
     */
    void setNbOfFailed(int nbOfFailed);

    /**
     *
     * @return
     */
    int getNbOfSuspected();
    
    /**
     *
     * @param nbOfSuspected
     */
    void setNbOfSuspected(int nbOfSuspected);
    
    /**
     *
     * @return
     */
    int getNbOfDetected();
    
    /**
     * 
     * @param nbOfDetected 
     */    
    void setNbOfDetected(int nbOfDetected);
    
    /**
     * 
     * @return 
     */
    int getNbOfNotTested();
    
    /**
     * 
     * @param nbOfNotTested 
     */
    void setNbOfNotTested(int nbOfNotTested);
}
