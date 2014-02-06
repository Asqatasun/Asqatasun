/*
 * Tanaguru - Automated webpage assessment
 * Copyright (C) 2008-2011  Open-S Company
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
 * Contact us by mail: open-s AT open-s DOT com
 */
package org.opens.tanaguru.rules.elementchecker.lang.detector;

import java.io.IOException;
import java.net.URL;
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
        System.out.println("detectLanguage With case sensitive text");
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
        System.out.println("detectLanguage Af");
        LanguageDetector instance = LanguageDetector.getInstance();
        Document doc;
        try {
            doc = Jsoup.parse(new URL("https://af.wikipedia.org/wiki/Suider-Afrika"), 10000);
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
        System.out.println("detectLanguage fr");
        LanguageDetector instance = LanguageDetector.getInstance();
        Document doc;
        try {
            doc = Jsoup.parse(new URL("http://fr.wikipedia.org/wiki/Accessibilit%C3%A9_du_web"), 10000);
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
        System.out.println("detectLanguage De");
        LanguageDetector instance = LanguageDetector.getInstance();
        Document doc;
        try {
            doc = Jsoup.parse(new URL("http://de.wikipedia.org/wiki/Barrierefreies_Internet"), 10000);
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
        System.out.println("detectLanguage en");
        LanguageDetector instance = LanguageDetector.getInstance();
        Document doc;
        try {
            doc = Jsoup.parse(new URL("http://en.wikipedia.org/wiki/Web_accessibility"), 10000);
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
        System.out.println("detectLanguage es");
        LanguageDetector instance = LanguageDetector.getInstance();
        Document doc;
        try {
            doc = Jsoup.parse(new URL("http://es.wikipedia.org/wiki/Accesibilidad_web"), 10000);
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
        System.out.println("detectLanguage fa");
        LanguageDetector instance = LanguageDetector.getInstance();
        Document doc;
        try {
            doc = Jsoup.parse(new URL("http://fa.wikipedia.org/wiki/%D8%AF%D8%B3%D8%AA%D8%B1%D8%B3%DB%8C%E2%80%8C%D9%BE%D8%B0%DB%8C%D8%B1%DB%8C_%D9%88%D8%A8"), 10000);
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
        System.out.println("detectLanguage he");
        LanguageDetector instance = LanguageDetector.getInstance();
        Document doc;
        try {
            doc = Jsoup.parse(new URL("http://he.wikipedia.org/wiki/%D7%A0%D7%92%D7%99%D7%A9%D7%95%D7%AA_%D7%90%D7%99%D7%A0%D7%98%D7%A8%D7%A0%D7%98"), 10000);
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
        System.out.println("detectLanguage ko");
        LanguageDetector instance = LanguageDetector.getInstance();
        Document doc;
        try {
            doc = Jsoup.parse(new URL("http://ko.wikipedia.org/wiki/%EC%9B%B9_%EC%A0%91%EA%B7%BC%EC%84%B1"), 10000);
            System.out.println(doc.text());
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
        System.out.println("detectLanguage mk");
        LanguageDetector instance = LanguageDetector.getInstance();
        Document doc;
        try {
            doc = Jsoup.parse(new URL("http://mk.wikipedia.org/wiki/%D0%98%D0%BD%D1%82%D0%B5%D1%80%D0%BD%D0%B5%D1%82-%D0%BF%D1%80%D0%B8%D1%81%D1%82%D0%B0%D0%BF%D0%BD%D0%BE%D1%81%D1%82"), 10000);
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
        System.out.println("detectLanguage pl");
        LanguageDetector instance = LanguageDetector.getInstance();
        Document doc;
        try {
            doc = Jsoup.parse(new URL("http://pl.wikipedia.org/wiki/Dost%C4%99pno%C5%9B%C4%87_%28WWW%29"), 10000);
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
        System.out.println("detectLanguage pt");
        LanguageDetector instance = LanguageDetector.getInstance();
        Document doc;
        try {
            doc = Jsoup.parse(new URL("http://pt.wikipedia.org/wiki/Acessibilidade_Web"), 10000);
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
        System.out.println("detectLanguage th");
        LanguageDetector instance = LanguageDetector.getInstance();
        Document doc;
        try {
            doc = Jsoup.parse(new URL("http://th.wikipedia.org/wiki/%E0%B8%84%E0%B8%A7%E0%B8%B2%E0%B8%A1%E0%B8%AA%E0%B8%B2%E0%B8%A1%E0%B8%B2%E0%B8%A3%E0%B8%96%E0%B9%83%E0%B8%99%E0%B8%81%E0%B8%B2%E0%B8%A3%E0%B9%80%E0%B8%82%E0%B9%89%E0%B8%B2%E0%B8%96%E0%B8%B6%E0%B8%87%E0%B9%80%E0%B8%A7%E0%B9%87%E0%B8%9A"), 10000);
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
        System.out.println("detectLanguage vi");
        LanguageDetector instance = LanguageDetector.getInstance();
        Document doc;
        try {
            doc = Jsoup.parse(new URL("http://vi.wikipedia.org/wiki/C%C3%B4ng_ngh%E1%BB%87_th%C3%B4ng_tin_ti%E1%BA%BFp_c%E1%BA%ADn"), 10000);
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
        System.out.println("detectLanguage zh");
        LanguageDetector instance = LanguageDetector.getInstance();
        Document doc;
        try {
            doc = Jsoup.parse(new URL("http://zh.wikipedia.org/wiki/%E7%B6%B2%E9%A0%81%E8%A6%AA%E5%92%8C%E5%8A%9B"), 10000);
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
        System.out.println("detectLanguage ru");
        LanguageDetector instance = LanguageDetector.getInstance();
        Document doc;
        try {
            doc = Jsoup.parse(new URL("http://timeliner.ru/"), 100000);
            LOGGER.debug("start detection");
            assertEquals("ru", instance.detectLanguage(doc.text()).getDetectedLanguage());
            LOGGER.debug("detection ended");
            doc = Jsoup.parse(new URL("http://atrainings.ru/"), 100000);
            LOGGER.debug("start detection");
            assertEquals("ru", instance.detectLanguage(doc.text()).getDetectedLanguage());
            LOGGER.debug("detection ended");
            doc = Jsoup.parse(new URL("http://alpidos.ru/home/"), 100000);
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