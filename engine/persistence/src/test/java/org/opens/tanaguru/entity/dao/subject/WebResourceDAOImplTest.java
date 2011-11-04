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
package org.opens.tanaguru.entity.dao.subject;

import java.util.Iterator;
import java.util.List;
import org.opens.tanaguru.entity.audit.Audit;
import org.opens.tanaguru.entity.dao.audit.AuditDAO;
import org.opens.tanaguru.entity.dao.test.AbstractDaoTestCase;
import org.opens.tanaguru.entity.subject.WebResource;

/**
 *
 * @author jkowalczyk
 */
public class WebResourceDAOImplTest extends AbstractDaoTestCase {

    private static final String URL1="http://www.mock-url.org/";
    private static final String URL2="http://www.mock-url3.org/";

    /**
     * Nom du fichier xml contenant le jeu de données à importer
     */
    private static final String INPUT_DATA_SET_FILENAME = "webresourceFlatXmlDataSet.xml";
    private WebResourceDAO webresourceDAO;
    private AuditDAO auditDAO;

    public WebResourceDAOImplTest(String testName) {
        super(testName);
        setInputDataFileName(getInputDataFilePath()+INPUT_DATA_SET_FILENAME);
        auditDAO = (AuditDAO)
                springBeanFactory.getBean("auditDAO");
        webresourceDAO = (WebResourceDAO)
                springBeanFactory.getBean("webresourceDAO");
    }

    public void testFindByAuditAndUrl() {
        Audit audit1 = auditDAO.read(Long.valueOf(1));
        Audit audit2 = auditDAO.read(Long.valueOf(2));
        Audit audit3 = auditDAO.read(Long.valueOf(3));
        assertEquals(Long.valueOf(1), webresourceDAO.findByAuditAndUrl(audit1, URL1).getId());
        assertEquals(Long.valueOf(2), webresourceDAO.findByAuditAndUrl(audit2, URL1).getId());
        assertEquals(Long.valueOf(3), webresourceDAO.findByAuditAndUrl(audit3, URL2).getId());
        assertNull(webresourceDAO.findByAuditAndUrl(audit1, URL2));
        assertNull(webresourceDAO.findByAuditAndUrl(audit2, URL2));
        assertNull(webresourceDAO.findByAuditAndUrl(audit3, URL1));
    }

    public void testFindByUrlAndParentWebResource(){
        WebResource parentWr = webresourceDAO.read(Long.valueOf(9));
        WebResource wr = webresourceDAO.findByUrlAndParentWebResource("http://www.mock-url.org/", parentWr);
        assertEquals(Long.valueOf(5), wr.getId());
        wr = webresourceDAO.findByUrlAndParentWebResource("http://www.mock-url.org/testpage", parentWr);
        assertNull(wr);
    }

    public void testRetrieveWebResourceFromItsParent(){
        WebResource parentWr = webresourceDAO.read(Long.valueOf(9));
        assertEquals(Long.valueOf(3), webresourceDAO.findNumberOfChildWebResource(parentWr));
        List<WebResource> wrList = webresourceDAO.findWebResourceFromItsParent(parentWr, 0, 1);
        assertEquals(1, wrList.size());
        assertEquals(Long.valueOf(5), ((WebResource)wrList.iterator().next()).getId());
        wrList = webresourceDAO.findWebResourceFromItsParent(parentWr, 1, 10);
        assertEquals(2, wrList.size());
        Iterator iter = wrList.iterator();
        assertEquals(Long.valueOf(6), ((WebResource)iter.next()).getId());
        assertEquals(Long.valueOf(7), ((WebResource)iter.next()).getId());
    }

}
