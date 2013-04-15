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
package org.opens.tgol.charts.tags;

import de.laures.cewolf.CewolfException;
import de.laures.cewolf.ChartHolder;
import de.laures.cewolf.ChartImage;
import de.laures.cewolf.WebConstants;
import de.laures.cewolf.links.CategoryItemLinkGenerator;
import de.laures.cewolf.links.LinkGenerator;
import de.laures.cewolf.links.PieSectionLinkGenerator;
import de.laures.cewolf.links.XYItemLinkGenerator;
import de.laures.cewolf.taglib.ChartImageDefinition;
import de.laures.cewolf.taglib.tags.CewolfTag;
import de.laures.cewolf.taglib.util.PageUtils;
import de.laures.cewolf.tooltips.CategoryToolTipGenerator;
import de.laures.cewolf.tooltips.PieToolTipGenerator;
import de.laures.cewolf.tooltips.ToolTipGenerator;
import de.laures.cewolf.tooltips.XYToolTipGenerator;
import java.awt.geom.Rectangle2D;
import java.io.IOException;
import java.io.Writer;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import javax.servlet.jsp.JspException;
import org.jfree.chart.ChartRenderingInfo;
import org.jfree.chart.entity.CategoryItemEntity;
import org.jfree.chart.entity.ChartEntity;
import org.jfree.chart.entity.EntityCollection;
import org.jfree.chart.entity.LegendItemEntity;
import org.jfree.chart.entity.PieSectionEntity;
import org.jfree.chart.entity.XYItemEntity;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.general.Dataset;
import org.jfree.data.general.PieDataset;
import org.jfree.data.xy.XYDataset;

/**
 * This class extends the CewolfTag class to override
 * @author jkowalczyk
 */
public class TgolChartMapTag extends CewolfTag {

    private static final long serialVersionUID = -5089230129561753112L;
    // constants for the HTML attributes and JavaScript methods
    private static final String MAP_TAGNAME = "map";
    private static final String AREA_TAGNAME = "area";
    private static final String COORD_ATTRIBUTE = "coords";
    private static final String SHAPE_ATTRIBUTE = "shape";
    private static final String NAME_ATTRIBUTE = "name";
    private static final String ID_ATTRIBUTE = "id";
    private static final String ALT_ATTRIBUTE = "alt";
    private static final String TITLE_ATTRIBUTE = "title";
    private static final String TARGET_ATTRIBUTE = "target";
    private static final String HREF_ATTRIBUTE = "href";
    private ToolTipGenerator toolTipGenerator = null;
    private LinkGenerator linkGenerator = null;
    // If the links provided by the JFreeChart renderer should be used.
    private boolean useJFreeChartLinkGenerator = false;
    // If the tooltips provided by the JFreeChart renderer should be used.
    private boolean useJFreeChartTooltipGenerator = false;
    private static final int DEFAULT_TIMEOUT = 300;

    private String chartId = null;
    /**
     *
     * @param chartId
     */
    public void setChartId(String chartId) {
        this.chartId = chartId;
    }

    private String divId = null;
    /**
     *
     * @param divId
     */
    public void setDivId(String divId) {
        this.divId = divId;
    }

    private int width = 0;
    /**
     *
     * @param width
     */
    public void setWidth(int width) {
        this.width = width;
    }

    private int height = 0;
    /**
     *
     * @param height
     */
    public void setHeight(int height) {
        this.height = height;
    }

    private String target;
    /**
     * 
     * @param target
     */
    public void setTarget(String target) {
        this.target = target;
    }

    /**
     *
     * @return
     * @throws JspException
     */
    @Override
    public int doStartTag() throws JspException {
        final ChartHolder chartHolder = PageUtils.getChartHolder(chartId, pageContext);
        ChartImageDefinition chartImageDefinition =
                new ChartImageDefinition(
                chartHolder,
                width,
                height,
                ChartImage.IMG_TYPE_CHART,
                WebConstants.MIME_PNG, DEFAULT_TIMEOUT);

        try {
            Dataset dataset = PageUtils.getDataset(chartId, pageContext);
            Writer out = pageContext.getOut();
            if (hasToolTips()) {
                enableToolTips(out);
            }
            String mapTagContent = "<" + MAP_TAGNAME;
            mapTagContent += " " + NAME_ATTRIBUTE + "=\"" + chartId + "\"";
            mapTagContent += " " + ID_ATTRIBUTE + "=\"" + chartId + "\"";
            mapTagContent += " >";
            out.write(mapTagContent);
            ChartRenderingInfo info = chartImageDefinition.getRenderingInfo();
            List<ChartEntity> chartEntityList = sortChartEntities(info.getEntityCollection());
            Iterator<ChartEntity> entities = chartEntityList.iterator();
            boolean altAttrbInserted, hasContent;
            StringBuffer sb = new StringBuffer(200);
            while (entities.hasNext()) {
                altAttrbInserted = false;
                hasContent = false;
                ChartEntity ce = entities.next();
                sb.append("\n<");
                sb.append(AREA_TAGNAME);
                sb.append(" ");
                sb.append(SHAPE_ATTRIBUTE);
                sb.append("=\"");
                sb.append(ce.getShapeType());
                sb.append("\" ");
                sb.append(COORD_ATTRIBUTE);
                sb.append("=\"");
                sb.append(ce.getShapeCoords());
                sb.append("\" ");
                if (ce instanceof XYItemEntity) {
                    dataset = ((XYItemEntity) ce).getDataset();
                }
                if (!(ce instanceof LegendItemEntity)) {
                    if (hasToolTips()) {
                        if (writeOutToolTip(dataset, sb, ce)) {
                            hasContent = true;
                        }
                        altAttrbInserted = true;
                    }
                    if (hasLinks()) {
                        if (writeOutLink(dataset, sb, ce))
                            hasContent = true;
                    }
                }
                if (!altAttrbInserted) {
                    sb.append(" ");
                    sb.append(ALT_ATTRIBUTE);
                    sb.append("=\"\"");
                }
                sb.append(" />");
                if (hasContent) {
                    out.write(sb.toString());
                }
                sb.setLength(0);
            }
        } catch (IOException ioex) {
            System.err.println("ChartMapTag.doStartTag: " + ioex.getMessage());
            throw new JspException(ioex.getMessage());
        } catch (CewolfException cwex) {
            System.err.println("ChartMapTag.doStartTag: " + cwex.getMessage());
            throw new JspException(cwex.getMessage());
        }
        return EVAL_PAGE;
    }

    /**
     *
     * @return
     * @throws JspException
     */
    @Override
    public int doEndTag() throws JspException {
        // print out image map end
        Writer out = pageContext.getOut();
        try {
            out.write("\n</" + MAP_TAGNAME + ">");
        } catch (IOException ioex) {
            System.err.println("ChartMapTag.doEndTag: " + ioex.getMessage());
            throw new JspException(ioex.getMessage());
        }
        return doAfterEndTag(EVAL_PAGE);
    }

    /**
     *
     */
    @Override
    public void reset() {
        this.toolTipGenerator = null;
    }

    private boolean writeOutLink(Dataset dataset, StringBuffer sb, ChartEntity ce) throws IOException {
        final String link = generateLink(dataset, ce);

        if (null != link) {
            sb.append(HREF_ATTRIBUTE);
            sb.append("=\"");
            sb.append(link);
            sb.append("\"");
            if (target != null) {
                sb.append(" ");
                sb.append(TARGET_ATTRIBUTE);
                sb.append("=\"");
                sb.append(target);
                sb.append("\"");
            }
            return true;
        }

        return false;
    }

    private boolean writeOutToolTip(Dataset dataset, StringBuffer sb, ChartEntity ce) throws IOException, JspException {
        String toolTip = generateToolTip(dataset, ce);
        boolean altAttributeInserted = false;
        boolean hasContent = false;
        if (null != toolTip) {
            hasContent = true;
            sb.append(ALT_ATTRIBUTE);
            sb.append("=\"");
            sb.append(toolTip);
            sb.append("\" ");
            sb.append(TITLE_ATTRIBUTE);
            sb.append("=\"");
            sb.append(toolTip);
            sb.append("\" ");
            altAttributeInserted = true;
        }
        if (!altAttributeInserted) {
            sb.append(" " + ALT_ATTRIBUTE + "=\"\" ");
        }
        return hasContent;
    }

    /**
     *
     * @param out
     * @throws IOException
     */
    public void enableToolTips(Writer out) throws IOException {
        if (!PageUtils.isToolTipsEnabled(pageContext)) {
            out.write("<div id=\"" + divId + "\" style=\"position:absolute; visibility:hidden; z-index:1000;\"></div>\n");
            PageUtils.setToolTipsEnabled(pageContext);
        }
    }

    private String generateLink(Dataset dataset, ChartEntity ce) {
        String link = null;
        if (useJFreeChartLinkGenerator) {
            link = ce.getURLText();
        } else if (linkGenerator instanceof CategoryItemLinkGenerator) {
            if (ce instanceof CategoryItemEntity) {
                CategoryItemEntity catEnt = (CategoryItemEntity) ce;
                CategoryDataset cds = (CategoryDataset) dataset;
                link = ((CategoryItemLinkGenerator) linkGenerator).generateLink(cds, cds.getRowIndex(catEnt.getRowKey()), catEnt.getColumnKey());
            }
        } else if (linkGenerator instanceof XYItemLinkGenerator) {
            if (ce instanceof XYItemEntity) {
                XYItemEntity xyEnt = (XYItemEntity) ce;
                link = ((XYItemLinkGenerator) linkGenerator).generateLink(dataset, xyEnt.getSeriesIndex(), xyEnt.getItem());
            }
        } else if (linkGenerator instanceof PieSectionLinkGenerator) {
            if (ce instanceof PieSectionEntity) {
                PieSectionEntity pieEnt = (PieSectionEntity) ce;
                link = ((PieSectionLinkGenerator) linkGenerator).generateLink(dataset, pieEnt.getSectionKey());
            }
        }
        return link;
    }

    private String generateToolTip(Dataset dataset, ChartEntity ce) throws JspException {
        String tooltip = null;
        if (useJFreeChartTooltipGenerator) {
            tooltip = ce.getToolTipText();
        } else if (toolTipGenerator instanceof CategoryToolTipGenerator) {
            if (ce instanceof CategoryItemEntity) {
                CategoryItemEntity catEnt = (CategoryItemEntity) ce;
                CategoryDataset cds = (CategoryDataset) dataset;
                tooltip = ((CategoryToolTipGenerator) toolTipGenerator).generateToolTip(cds, cds.getRowIndex(catEnt.getRowKey()), cds.getColumnIndex(catEnt.getColumnKey()));
            }
        } else if (toolTipGenerator instanceof XYToolTipGenerator) {
            if (ce instanceof XYItemEntity) {
                XYItemEntity xyEnt = (XYItemEntity) ce;
                tooltip = ((XYToolTipGenerator) toolTipGenerator).generateToolTip((XYDataset) dataset, xyEnt.getSeriesIndex(), xyEnt.getItem());
            }
        } else if (toolTipGenerator instanceof PieToolTipGenerator) {
            if (ce instanceof PieSectionEntity) {
                PieSectionEntity pieEnt = (PieSectionEntity) ce;
                PieDataset ds = (PieDataset) dataset;
                final int index = pieEnt.getSectionIndex();
                tooltip = ((PieToolTipGenerator) toolTipGenerator).generateToolTip(ds, ds.getKey(index), index);
            }
        } else {
            // throw because category is unknown
            throw new JspException("TooltipgGenerator of class " + toolTipGenerator.getClass().getName()
                    + " does not implement the appropriate TooltipGenerator interface for entity type " + ce.getClass().getName());
        }
        return tooltip;
    }

    private boolean hasToolTips() throws JspException {
        if (toolTipGenerator != null && useJFreeChartTooltipGenerator) {
            throw new JspException("Can't have both tooltipGenerator and useJFreeChartTooltipGenerator parameters specified!");
        }
        return toolTipGenerator != null || useJFreeChartTooltipGenerator;
    }

    /**
     *
     * @param id
     */
    public void setTooltipgeneratorid(String id) {
        this.toolTipGenerator = (ToolTipGenerator) pageContext.findAttribute(id);
    }

    private boolean hasLinks() throws JspException {
        if (linkGenerator != null && useJFreeChartLinkGenerator) {
            throw new JspException("Can't have both linkGenerator and useJFreeChartLinkGenerator parameters specified!");
        }
        return linkGenerator != null || useJFreeChartLinkGenerator;
    }

    /**
     *
     * @param id
     */
    public void setLinkgeneratorid(String id) {
        this.linkGenerator = (LinkGenerator) pageContext.findAttribute(id);
    }

    /**
     * Setter of the useJFreeChartTooltipGenerator field.
     * @param useJFreeChartTooltipGenerator the useJFreeChartTooltipGenerator to set.
     */
    public void setUseJFreeChartTooltipGenerator(boolean useJFreeChartTooltipGenerator) {
        this.useJFreeChartTooltipGenerator = useJFreeChartTooltipGenerator;
    }

    /**
     * Setter of the useJFreeChartTooltipGenerator field.
     * @param useJFreeChartLinkGenerator
     */
    public void setUseJFreeChartLinkGenerator(boolean useJFreeChartLinkGenerator) {
        this.useJFreeChartLinkGenerator = useJFreeChartLinkGenerator;
    }

    /**
     * This method sorts the chart entities to avoid masking in case of shape
     * that covers another one. In this case the covered one has to displayed
     * first.
     *
     * @param entityCollection
     * @return
     */
    private List<ChartEntity> sortChartEntities(EntityCollection entityCollection) {
        List<ChartEntity> charEntityList = new LinkedList<ChartEntity>();
        Iterator iter = entityCollection.iterator();
        while (iter.hasNext()) {
            charEntityList.add((ChartEntity)iter.next());
        }
        Collections.sort(charEntityList, new Comparator<ChartEntity>() {
            @Override
            public int compare(ChartEntity o1, ChartEntity o2) {
                Rectangle2D r =o2.getArea().getBounds2D();
                if (o1.getArea().intersects(r)) {
                    return 1;
                } else return -1;
            }
        });
        return charEntityList;
    }

}