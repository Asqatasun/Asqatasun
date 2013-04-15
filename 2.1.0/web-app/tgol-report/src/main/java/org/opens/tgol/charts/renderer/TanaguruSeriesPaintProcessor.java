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
package org.opens.tgol.charts.renderer;

import de.laures.cewolf.cpp.SeriesPaintProcessor;
import java.awt.BasicStroke;
import java.awt.Color;
import java.util.Map;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.CategoryLabelPositions;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.Plot;
import org.jfree.chart.renderer.AreaRendererEndType;
import org.jfree.chart.renderer.category.AreaRenderer;
import org.jfree.chart.renderer.category.CategoryItemRenderer;

/**
 *
 * @author jkowalczyk
 */
public class TanaguruSeriesPaintProcessor extends SeriesPaintProcessor {

    static final long serialVersionUID = -2290498142826058256L;

    /**
     *
     * @param chart
     * @param params
     */
    @Override
    public void processChart(Object chart, Map params) {
        JFreeChart localChart = (JFreeChart) chart;
        Plot plot = localChart.getPlot();
//        if (plot instanceof PiePlot) {
            // overidde to remove shadow on pie graphics
//            PiePlot piePlot = (PiePlot) plot;
//            piePlot.setShadowPaint(null);
//            piePlot.setShadowXOffset(0);
//            piePlot.setShadowYOffset(0);
            // category plots
//        } else if (plot instanceof CategoryPlot) {
        if (plot instanceof CategoryPlot) {
            // overidde to remove shadow and gradient on stacked bar graphics
            CategoryItemRenderer renderer = ((CategoryPlot) plot).getRenderer();
            if (isRangeGridlineVisible) {
                ((CategoryPlot) plot).setRangeGridlinePaint(Color.LIGHT_GRAY);
                ((CategoryPlot) plot).setDomainGridlinePaint(Color.GRAY);
                ((CategoryPlot) plot).setDomainGridlineStroke(new BasicStroke(0.3F));
                ((CategoryPlot) plot).setDomainGridlinesVisible(true);
                ((CategoryPlot) plot).setForegroundAlpha(0.75F);
            }
            if (isLabelVertical) {
                CategoryAxis axis = ((CategoryPlot) plot).getDomainAxis();
                axis.setTickMarksVisible(true);
                axis.setCategoryLabelPositions(
                    CategoryLabelPositions.createUpRotationLabelPositions(Math.PI / 4));
            }
            if (isPercent) {
                NumberAxis numberaxis = (NumberAxis)((CategoryPlot) plot).getRangeAxis();
                numberaxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
                numberaxis.setRange(0.0,100.0);
            }
            if (renderer instanceof AreaRenderer) {
                ((AreaRenderer)renderer).setEndType(AreaRendererEndType.LEVEL);
            }
        }
        super.processChart(chart, params);
    }

    private boolean isRangeGridlineVisible = false;
    /**
     *
     * @param isRangeGridlineVisible
     */
    public void setRangeGridlineVisible(boolean isRangeGridlineVisible) {
        this.isRangeGridlineVisible = isRangeGridlineVisible;
    }

    private boolean isLabelVertical = false;
    /**
     *
     * @param isLabelVertical
     */
    public void setLabelVertical(boolean isLabelVertical) {
        this.isLabelVertical = isLabelVertical;
    }

    private boolean isPercent = true;
    /**
     * 
     * @param isPercent
     */
    public void setPercent(boolean isPercent) {
        this.isPercent = isPercent;
    }

}