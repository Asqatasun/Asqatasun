/*
 * Asqatasun - Automated webpage assessment
 * Copyright (C) 2008-2015  Asqatasun.org
 *
 * This file is part of Asqatasun.
 *
 * Asqatasun is free software: you can redistribute it and/or modify
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
 * Contact us by mail: asqatasun AT asqatasun DOT org
 */
package org.asqatasun.entity.dao.subject;

import java.util.Iterator;
import java.util.List;
import org.dbunit.operation.DatabaseOperation;
import org.asqatasun.entity.audit.Audit;
import org.asqatasun.entity.dao.audit.AuditDAO;
import org.asqatasun.entity.dao.test.AbstractDaoTestCase;
import org.asqatasun.entity.subject.WebResource;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author jkowalczyk
 */
public class WebResourceDAOImplTest extends AbstractDaoTestCase {

    private static final String URL1="http://www.mock-url1.org/";
    private static final String URL2="http://www.mock-url2.org/";
    private static final String URL3="http://www.mock-url3.org/";
    private static final String URL4="http://www.mock-url4.org/";

    /**
     * Nom du fichier xml contenant le jeu de données à importer
     */
    private static final String INPUT_DATA_SET_FILENAME = "webresourceFlatXmlDataSet.xml";
    @Autowired
    private WebResourceDAO webresourceDAO;
    @Autowired
    private AuditDAO auditDAO;

    public WebResourceDAOImplTest() {
        super();
        setTeardownOperationValue(DatabaseOperation.DELETE);
    }

    @Test
    public void testFindByAuditAndUrl() {
        Audit audit1 = auditDAO.read(Long.valueOf(1));
        Audit audit2 = auditDAO.read(Long.valueOf(2));
        Audit audit3 = auditDAO.read(Long.valueOf(3));
        assertEquals(Long.valueOf(1), webresourceDAO.findByAuditAndUrl(audit1, URL1).getId());
        assertEquals(Long.valueOf(5), webresourceDAO.findByAuditAndUrl(audit2, URL1).getId());
        assertEquals(Long.valueOf(6), webresourceDAO.findByAuditAndUrl(audit3, URL1).getId());
        assertNull(webresourceDAO.findByAuditAndUrl(audit1, URL2));
        assertNull(webresourceDAO.findByAuditAndUrl(audit2, URL2));
        assertNull(webresourceDAO.findByAuditAndUrl(audit3, URL2));
    }

    @Test
    public void testFindByUrlAndParentWebResource(){
        WebResource parentWr = webresourceDAO.read(Long.valueOf(1));
        WebResource wr = webresourceDAO.findByUrlAndParentWebResource(URL2, parentWr);
        assertEquals(Long.valueOf(2), wr.getId());
        wr = webresourceDAO.findByUrlAndParentWebResource(URL1, parentWr);
        assertNull(wr);
    }

    @Test
    public void testRetrieveWebResourceFromItsParent(){
        WebResource parentWr = webresourceDAO.read(Long.valueOf(1));
        assertEquals(Long.valueOf(3), webresourceDAO.findNumberOfChildWebResource(parentWr));
        List<WebResource> wrList = webresourceDAO.findWebResourceFromItsParent(parentWr, 0, 1);
        assertEquals(1, wrList.size());
        assertEquals(Long.valueOf(2), ((WebResource)wrList.iterator().next()).getId());
        wrList = webresourceDAO.findWebResourceFromItsParent(parentWr, 1, 10);
        assertEquals(2, wrList.size());
        Iterator iter = wrList.iterator();
        assertEquals(Long.valueOf(3), ((WebResource)iter.next()).getId());
        assertEquals(Long.valueOf(4), ((WebResource)iter.next()).getId());
    }

    @Override
    protected String getDataSetFilename() throws Exception {
        return getInputDataFilePath()+INPUT_DATA_SET_FILENAME;
    }
}
