/*
 * Tanaguru - Automated webpage assessment
 * Copyright (C) 2008-2015 Tanaguru.org
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
package org.tanaguru.rules.elementchecker.lang.detector;

import java.io.File;
import java.io.IOException;
import junit.framework.TestCase;
import org.apache.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

/**
 *
 * @author meskoj
 */
public class LanguageDetectorTest extends TestCase {
    
    private static final Logger LOGGER = Logger.getLogger(LanguageDetectorTest.class);
    private static final String PATH = "src/test/resources/langDetection/";
    private static final String UTF_8 = "UTF-8";
    
    public LanguageDetectorTest(String testName) {
        super(testName);
    }
    
    @Override
    protected void setUp() throws Exception {
        super.setUp();
    }
    
    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }

    public void testDetectLanguageWithCaseSensitiveText() {
        LOGGER.debug("detectLanguage With case sensitive text");
        LanguageDetector instance = LanguageDetector.getInstance();
        LOGGER.debug("start detection");
        assertEquals("fr",instance.detectLanguage("34808 : CABLE DE COMMANDE DE BOITE A VITESSE RENAULT KANGOO 32.11 € TTC 34808 : CABLE DE COMMANDE DE BOITE A VITESSE RENAULT KANGOO").getDetectedLanguage());
        assertEquals("fr",instance.detectLanguage("CABLE DE COMMANDE DE BOITE A VITESSE RENAULT KANGOO TTC CABLE DE COMMANDE DE BOITE A VITESSE RENAULT KANGOO").getDetectedLanguage());
        assertEquals("fr",instance.detectLanguage("CABLE DE COMMANDE DE BOITE A VITESSE RENAULT KANGOO TTC").getDetectedLanguage());

        assertEquals("fr",instance.detectLanguage("CABLE DE COMMANDE DE BOITE A VITESSE RENAULT TTC").getDetectedLanguage());

        //suspiscion de AULT (suppression de renault)
        assertEquals("fr",instance.detectLanguage("CABLE DE COMMANDE DE BOITE A VITESSE TTC").getDetectedLanguage());

        //suspiscion de ESPACE (suppression de espace)
        assertEquals("fr",instance.detectLanguage("MAITRE CYLINDRE DE FREIN RENAULT 21 ESPACE 50.06 € TTC").getDetectedLanguage());
        assertEquals("fr",instance.detectLanguage("MAITRE CYLINDRE DE FREIN RENAULT ESPACE  TTC").getDetectedLanguage());
        assertEquals("fr",instance.detectLanguage("MAITRE CYLINDRE DE FREIN RENAULT TTC").getDetectedLanguage());

        //suspiscion de ESPACE (suppression de espace ET renault)
        assertEquals("fr",instance.detectLanguage("MAITRE CYLINDRE DE FREIN TTC").getDetectedLanguage());

        assertEquals("es",instance.detectLanguage("NO PODEÍS PREPARAR A VUESTROS ALUMNOS PARA QUE CONSTRUYAN MAÑANA EL MUNDO DE SUS SUEÑOS SI VOSOTROS YA NO CREÉIS EN ESOS SUEÑOS NO PODEÍS PREPARARLOS PARA LA VIDA SINO CREÉIS EN ELLA NO PODRÉIS MOSTRAR EL CAMINO SI OS HABEÍS SENTADO CANSADOS Y DESALENTADOS EN LA ENCRUCIJADA CELESTIN FREINET FRANCIA").getDetectedLanguage());
        LOGGER.debug("detection ended");
    }
    
    /**
     * Test of detectLanguage method, of class LanguageDetector with af pages.
     */
    public void testDetectLanguageAf() {
        LOGGER.debug("detectLanguage Af");
        LanguageDetector instance = LanguageDetector.getInstance();
        Document doc;
        try {
            doc = Jsoup.parse(new File(PATH+"af.wikipedia.org-wiki-Suider-Afrika_20140701.html"), UTF_8);
            LOGGER.debug("start detection");
            assertEquals("af", instance.detectLanguage(doc.text()).getDetectedLanguage());
            assertEquals("af", instance.detectLanguage(doc.text().toLowerCase()).getDetectedLanguage());
            assertEquals("af", instance.detectLanguage(doc.text().toUpperCase()).getDetectedLanguage());
            LOGGER.debug("detection ended");
        } catch (IOException ex) {
            LOGGER.error(ex);
        } catch (NullPointerException npe) {
            LOGGER.error("error while fetching page " + npe);
        }
    }
    
    /**
     * Test of detectLanguage method, of class LanguageDetector with fr pages.
     */
    public void testDetectLanguageFr() {
        LOGGER.debug("detectLanguage fr");
        LanguageDetector instance = LanguageDetector.getInstance();
        Document doc;
        try {
            doc = Jsoup.parse(new File(PATH+"fr.wikipedia.org-wiki-Accessibilite_du_web_20140701.html"), UTF_8);
            assertEquals("fr", instance.detectLanguage(doc.text()).getDetectedLanguage());
            assertEquals("fr", instance.detectLanguage(doc.text().toLowerCase()).getDetectedLanguage());
            assertEquals("fr", instance.detectLanguage(doc.text().toUpperCase()).getDetectedLanguage());
            LOGGER.debug("detection ended");
        } catch (IOException ex) {
            LOGGER.error(ex);
        } catch (NullPointerException npe) {
            LOGGER.error("error while fetching page " + npe);
        }
    }
    
    /**
     * Test of detectLanguage method, of class LanguageDetector with de pages.
     */
    public void testDetectLanguageDe() {
        LOGGER.debug("detectLanguage De");
        LanguageDetector instance = LanguageDetector.getInstance();
        Document doc;
        try {
            doc = Jsoup.parse(new File(PATH+"de.wikipedia.org-wiki-Barrierefreies_Internet_20140701.html"), UTF_8);
            LOGGER.debug("start detection");
            assertEquals("de", instance.detectLanguage(doc.text()).getDetectedLanguage());
            assertEquals("de", instance.detectLanguage(doc.text().toLowerCase()).getDetectedLanguage());
            assertEquals("de", instance.detectLanguage(doc.text().toUpperCase()).getDetectedLanguage());
            LOGGER.debug("detection ended");
        } catch (IOException ex) {
            LOGGER.error(ex);
        } catch (NullPointerException npe) {
            LOGGER.error("error while fetching page " + npe);
        }
    }
    
    /**
     * Test of detectLanguage method, of class LanguageDetector with en pages.
     */
    public void testDetectLanguageEn() {
        LOGGER.debug("detectLanguage en");
        LanguageDetector instance = LanguageDetector.getInstance();
        Document doc;
        try {
            doc = Jsoup.parse(new File(PATH+"en.wikipedia.org-wiki-Web_accessibility_20140701.html"), UTF_8);
            LOGGER.debug("start detection");
            assertEquals("en", instance.detectLanguage(doc.text()).getDetectedLanguage());
            assertEquals("en", instance.detectLanguage(doc.text().toLowerCase()).getDetectedLanguage());
            assertEquals("en", instance.detectLanguage(doc.text().toUpperCase()).getDetectedLanguage());
            LOGGER.debug("detection ended");
        } catch (IOException ex) {
            LOGGER.error(ex);
        } catch (NullPointerException npe) {
            LOGGER.error("error while fetching page " + npe);
        }
    }
    
    
    /**
     * Test of detectLanguage method, of class LanguageDetector with es pages.
     */
    public void testDetectLanguageEs() {
        LOGGER.debug("detectLanguage es");
        LanguageDetector instance = LanguageDetector.getInstance();
        Document doc;
        try {
            doc = Jsoup.parse(new File(PATH+"es.wikipedia.org-wiki-Accesibilidad_web_20140701.html"), UTF_8);
            LOGGER.debug("start detection");
            assertEquals("es", instance.detectLanguage(doc.text()).getDetectedLanguage());
            assertEquals("es", instance.detectLanguage(doc.text().toLowerCase()).getDetectedLanguage());
            assertEquals("es", instance.detectLanguage(doc.text().toUpperCase()).getDetectedLanguage());
            LOGGER.debug("detection ended");
        } catch (IOException ex) {
            LOGGER.error(ex);
        } catch (NullPointerException npe) {
            LOGGER.error("error while fetching page " + npe);
        }
    }
    
    /**
     * Test of detectLanguage method, of class LanguageDetector with fa pages.
     */
    public void testDetectLanguageFa() {
        LOGGER.debug("detectLanguage fa");
        LanguageDetector instance = LanguageDetector.getInstance();
        Document doc;
        try {
            doc = Jsoup.parse(new File(PATH+"fa.wikipedia.org-wiki_20140701.html"), UTF_8);
            LOGGER.debug("start detection");
            assertEquals("fa", instance.detectLanguage(doc.text()).getDetectedLanguage());
            assertEquals("fa", instance.detectLanguage(doc.text().toLowerCase()).getDetectedLanguage());
            assertEquals("fa", instance.detectLanguage(doc.text().toUpperCase()).getDetectedLanguage());
            LOGGER.debug("detection ended");
        } catch (IOException ex) {
            LOGGER.error(ex);
        } catch (NullPointerException npe) {
            LOGGER.error("error while fetching page " + npe);
        }
    }
    
    /**
     * Test of detectLanguage method, of class LanguageDetector with he pages.
     */
    public void testDetectLanguageHe() {
        LOGGER.debug("detectLanguage he");
        LanguageDetector instance = LanguageDetector.getInstance();
        Document doc;
        try {
            doc = Jsoup.parse(new File(PATH+"he.wikipedia.org-wiki_20140701.html"), UTF_8);
            LOGGER.debug("start detection");
            assertEquals("he", instance.detectLanguage(doc.text()).getDetectedLanguage());
            assertEquals("he", instance.detectLanguage(doc.text().toLowerCase()).getDetectedLanguage());
            assertEquals("he", instance.detectLanguage(doc.text().toUpperCase()).getDetectedLanguage());
            LOGGER.debug("detection ended");
        } catch (IOException ex) {
            LOGGER.error(ex);
        } catch (NullPointerException npe) {
            LOGGER.error("error while fetching page " + npe);
        }
    }
    
    /**
     * Test of detectLanguage method, of class LanguageDetector with ko pages.
     */
    public void testDetectLanguageKo() {
        LOGGER.debug("detectLanguage ko");
        LanguageDetector instance = LanguageDetector.getInstance();
        Document doc;
        try {
            doc = Jsoup.parse(new File(PATH+"ko.wikipedia.org-wiki_20140701.html"), UTF_8);
            LOGGER.debug(doc.text());
            LOGGER.debug("start detection");
            assertEquals("ko", instance.detectLanguage(doc.text()).getDetectedLanguage());
            assertEquals("ko", instance.detectLanguage(doc.text().toLowerCase()).getDetectedLanguage());
            assertEquals("ko", instance.detectLanguage(doc.text().toUpperCase()).getDetectedLanguage());
            LOGGER.debug("detection ended");
        } catch (IOException ex) {
            LOGGER.error(ex);
        } catch (NullPointerException npe) {
            LOGGER.error("error while fetching page " + npe);
        }
    }
    
    /**
     * Test of detectLanguage method, of class LanguageDetector with mk pages.
     */
    public void testDetectLanguageMk() {
        LOGGER.debug("detectLanguage mk");
        LanguageDetector instance = LanguageDetector.getInstance();
        Document doc;
        try {
            doc = Jsoup.parse(new File(PATH+"mk.wikipedia.org-wiki_20140701.html"), UTF_8);
            LOGGER.debug("start detection");
            assertEquals("mk", instance.detectLanguage(doc.text()).getDetectedLanguage());
            assertEquals("mk", instance.detectLanguage(doc.text().toLowerCase()).getDetectedLanguage());
            assertEquals("mk", instance.detectLanguage(doc.text().toUpperCase()).getDetectedLanguage());
            LOGGER.debug("detection ended");
        } catch (IOException ex) {
            LOGGER.error(ex);
        } catch (NullPointerException npe) {
            LOGGER.error("error while fetching page " + npe);
        }
    }
    
    /**
     * Test of detectLanguage method, of class LanguageDetector with pl pages.
     */
    public void testDetectLanguagePl() {
        LOGGER.debug("detectLanguage pl");
        LanguageDetector instance = LanguageDetector.getInstance();
        Document doc;
        try {
            doc = Jsoup.parse(new File(PATH+"pl.wikipedia.org-wiki_20140701.html"), UTF_8);
            LOGGER.debug("start detection");
            assertEquals("pl", instance.detectLanguage(doc.text()).getDetectedLanguage());
            assertEquals("pl", instance.detectLanguage(doc.text().toLowerCase()).getDetectedLanguage());
            assertEquals("pl", instance.detectLanguage(doc.text().toUpperCase()).getDetectedLanguage());
            LOGGER.debug("detection ended");
        } catch (IOException ex) {
            LOGGER.error(ex);
        } catch (NullPointerException npe) {
            LOGGER.error("error while fetching page " + npe);
        }
    }
    
    /**
     * Test of detectLanguage method, of class LanguageDetector with pt pages.
     */
    public void testDetectLanguagePt() {
        LOGGER.debug("detectLanguage pt");
        LanguageDetector instance = LanguageDetector.getInstance();
        Document doc;
        try {
            doc = Jsoup.parse(new File(PATH+"pt.wikipedia.org-wiki-Acessibilidade_Web_20140701.html"), UTF_8);
            LOGGER.debug("start detection");
            assertEquals("pt", instance.detectLanguage(doc.text()).getDetectedLanguage());
            assertEquals("pt", instance.detectLanguage(doc.text().toLowerCase()).getDetectedLanguage());
            assertEquals("pt", instance.detectLanguage(doc.text().toUpperCase()).getDetectedLanguage());
            LOGGER.debug("detection ended");
        } catch (IOException ex) {
            LOGGER.error(ex);
        } catch (NullPointerException npe) {
            LOGGER.error("error while fetching page " + npe);
        }
    }
    
    /**
     * Test of detectLanguage method, of class LanguageDetector with th pages.
     */
    public void testDetectLanguageTh() {
        LOGGER.debug("detectLanguage th");
        LanguageDetector instance = LanguageDetector.getInstance();
        Document doc;
        try {
            doc = Jsoup.parse(new File(PATH+"th.wikipedia.org-wiki_20140701.html"), UTF_8);
            LOGGER.debug("start detection");
            assertEquals("th", instance.detectLanguage(doc.text()).getDetectedLanguage());
            assertEquals("th", instance.detectLanguage(doc.text().toLowerCase()).getDetectedLanguage());
            assertEquals("th", instance.detectLanguage(doc.text().toUpperCase()).getDetectedLanguage());
            LOGGER.debug("detection ended");
        } catch (IOException ex) {
            LOGGER.error(ex);
        } catch (NullPointerException npe) {
            LOGGER.error("error while fetching page " + npe);
        }
    }
    
    /**
     * Test of detectLanguage method, of class LanguageDetector with vi pages.
     */
    public void testDetectLanguageVi() {
        LOGGER.debug("detectLanguage vi");
        LanguageDetector instance = LanguageDetector.getInstance();
        Document doc;
        try {
            doc = Jsoup.parse(new File(PATH+"vi.wikipedia.org-wiki_20140701.html"), UTF_8);
            LOGGER.debug("start detection");
            assertEquals("vi", instance.detectLanguage(doc.text()).getDetectedLanguage());
            assertEquals("vi", instance.detectLanguage(doc.text().toLowerCase()).getDetectedLanguage());
            assertEquals("vi", instance.detectLanguage(doc.text().toUpperCase()).getDetectedLanguage());
            LOGGER.debug("detection ended");
        } catch (IOException ex) {
            LOGGER.error(ex);
        } catch (NullPointerException npe) {
            LOGGER.error("error while fetching page " + npe);
        }
    }
    
    /**
     * Test of detectLanguage method, of class LanguageDetector with zh pages.
     */
    public void testDetectLanguageZh() {
        LOGGER.debug("detectLanguage zh");
        LanguageDetector instance = LanguageDetector.getInstance();
        Document doc;
        try {
            doc = Jsoup.parse(new File(PATH+"zh.wikipedia.org-wiki_20140701.html"), UTF_8);
            LOGGER.debug("start detection");
            assertEquals("zh-tw", instance.detectLanguage(doc.text()).getDetectedLanguage());
            assertEquals("zh-tw", instance.detectLanguage(doc.text().toLowerCase()).getDetectedLanguage());
            assertEquals("zh-tw", instance.detectLanguage(doc.text().toUpperCase()).getDetectedLanguage());
            LOGGER.debug("detection ended");
        } catch (IOException ex) {
            LOGGER.error(ex);
        } catch (NullPointerException npe) {
            LOGGER.error("error while fetching page " + npe);
        }
    }
    
    /**
     * Test of detectLanguage method, of class LanguageDetector with ru pages.
     */
    public void testDetectLanguageRu() {
        LOGGER.debug("detectLanguage ru");
        LanguageDetector instance = LanguageDetector.getInstance();
        Document doc;
        try {
            doc = Jsoup.parse(new File(PATH+"timeliner.ru_20140701.html"), UTF_8);
            LOGGER.debug("start detection");
            assertEquals("ru", instance.detectLanguage(doc.text()).getDetectedLanguage());
            LOGGER.debug("detection ended");
            doc = Jsoup.parse(new File(PATH+"atrainings.ru_20140701.html"), UTF_8);
            LOGGER.debug("start detection");
            assertEquals("ru", instance.detectLanguage(doc.text()).getDetectedLanguage());
            LOGGER.debug("detection ended");
            doc = Jsoup.parse(new File(PATH+"alpidos.ru-home_20140701.html"), UTF_8);
            LOGGER.debug("start detection");
            assertEquals("ru", instance.detectLanguage(doc.text()).getDetectedLanguage());
            LOGGER.debug("detection ended");
        } catch (IOException ex) {
            LOGGER.error(ex);
        } catch (NullPointerException npe) {
            LOGGER.error("error while fetching page " + npe);
        }
    }
    
}