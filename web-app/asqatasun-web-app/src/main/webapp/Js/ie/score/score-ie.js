function drawScore(
            selection, 
            score, 
            width, 
            height,
            radius, 
            outerRadius, 
            translateX, 
            translateY, 
            selectionId,
            selectionClass, 
            selectionReplacementClass,
            addText, 
            tanaguruMeterFont,
            tanaguruMeterFontSize,
            tanaguruMeterXOffset, 
            tanaguruMeterYOffset, 
            scoreFont, 
            scoreFontSize, 
            scoreXOffset, 
            scoreYOffset, 
            percentFont, 
            percentFontSize,
            percentXOffset,
            percentYOffset, 
            maxScoreXOffset, 
            maxScorePercentXOffset, 
            minScorePercentXOffset ) {

    var twoPi = 2 * Math.PI, 
        foregroundValue = "#ed4b63",
        arc, 
        donutSvg, 
        endAngle, 
        text, 
        percent;

    if (score > 90) {
        foregroundValue = "#a0d261";
    } else if (score > 80) {
        foregroundValue = "#bbda58";
    } else if (score > 70) {
        foregroundValue = "#f9a535";
    } else if (score > 60) {
        foregroundValue = "#f28739";
    } else if (score > 50) {
        foregroundValue = "#eb6240";
    }

    arc = d3.svg.arc()
        .startAngle(0)
        .outerRadius(radius - outerRadius)
        .innerRadius(radius);

    if (selectionId !== null) {
        selection.attr("id",selectionId);
    } else if (selectionClass !== null && selectionReplacementClass !== null) {
        selection.classed(selectionClass, false);
        selection.classed(selectionReplacementClass, true);        
    }    

    donutSvg = d3.select(selection[0][0]).append("svg")
        .attr("width", width)
        .attr("height", height)
        .append("g")
        .attr("transform", "translate(" + translateX +"," + translateY + ")");

    donutSvg.append("path")
        .attr("fill", foregroundValue)
        .attr("opacity", "0.5")
        .attr("stroke-width", "0")
        .attr("d", arc.endAngle(twoPi));

    endAngle = (twoPi  * score) / 100;
    donutSvg.append("path")
        .attr("fill", foregroundValue)
        .attr("opacity", "1.0")
        .attr("stroke-width", "0")
        .attr("d", arc.endAngle(endAngle));

    if(addText) {
        if (tanaguruMeterXOffset !== null && tanaguruMeterYOffset !== null) {
            donutSvg.append("text")
                .attr("text-anchor", "start")
                .attr("x", tanaguruMeterXOffset)
                .attr("y", tanaguruMeterYOffset)
                .attr("dy", ".35em")
                .attr("font-family", tanaguruMeterFont)
                .attr("font-size", tanaguruMeterFontSize)
                .attr("opacity", "0.7")
                .attr("class", "d3-score-tanaguru-meter")
                .text("TanaguruMeter");
        }
        text = donutSvg.append("text")
            .attr("text-anchor", "middle")
            .attr("dy", ".35em")
            .attr("x", scoreXOffset)
            .attr("y", scoreYOffset)
            .attr("font-family", scoreFont)
            .attr("font-size", scoreFontSize)
            .attr("opacity", "0.7")
            .attr("class", "d3-score-text");

        percent = donutSvg.append("text")
            .attr("text-anchor", "middle")
            .attr("x", percentXOffset)
            .attr("y", percentYOffset)
            .attr("dy", ".35em")
            .attr("font-family", percentFont)
            .attr("font-size", percentFontSize)
            .attr("opacity", "0.7")
            .attr("class", "d3-score-percent")
            .text('%');

        text.text(score);
        if (score == 100) {
            text.attr("x", maxScoreXOffset);
            percent.attr("x", maxScorePercentXOffset);
        } else if (score < 10) {
            percent.attr("x", minScorePercentXOffset);
        }
    }
}