function drawScore(selection, 
                   score, 
                   width, 
                   height, 
                   radiusDivider, 
                   outerRadius, 
                   selectionId,
                   selectionClass, 
                   selectionReplacementClass,
                   addText, 
                   tanaguruMeterXOffset, 
                   tanaguruMeterYOffset, 
                   scoreXOffset, 
                   scoreYOffset, 
                   percentXOffset,
                   percentYOffset, 
                   maxScoreXOffset, 
                   maxScorePercentXOffset, 
                   minScorePercentXOffset) {

    var twoPi = 2 * Math.PI, 
        endAngle, 
        foregroundClass = "grade-f", 
        radius, 
        arc, 
        donutSvg, 
        foreground, 
        donutText, 
        donutPercent,
        donutProgressScore = 0, 
        interval;
        
    if (score > 90) {
        foregroundClass = "grade-a";
    } else if (score > 80) {
        foregroundClass = "grade-b";
    } else if (score > 70) {
        foregroundClass = "grade-c";
    } else if (score > 60) {
        foregroundClass = "grade-d";
    } else if (score > 50) {
        foregroundClass = "grade-e";
    }
    radius = Math.min(width, height) / radiusDivider;

    arc = d3.svg.arc()
        .startAngle(0)
        .outerRadius(radius - outerRadius)
        .innerRadius(radius);

    donutSvg = selection.insert("svg", ":first-child")
        .attr("width", width)
        .attr("height", height)
        .attr("class", "d3-score-graph")
        .append("g")
        .attr("transform", "translate(" + ((width / 2)) + "," +(( height / 2)) + ")");

    donutSvg.append("path")
        .attr("class", "background "+foregroundClass)
        .attr("d", arc.endAngle(twoPi));
        
    foreground = donutSvg.append("path")
        .attr("class", foregroundClass);

    if (addText) {
        if (tanaguruMeterXOffset !== null && tanaguruMeterYOffset !== null) {
            donutSvg.append("text")
                .attr("text-anchor", "start")
                .attr("x", tanaguruMeterXOffset)
                .attr("y", tanaguruMeterYOffset)
                .attr("dy", ".35em")
                .attr("class", "d3-score-tanaguru-meter")
                .text("TanaguruMeter");
        }
        donutText = donutSvg.append("text")
            .attr("text-anchor", "middle")
            .attr("x", scoreXOffset)
            .attr("y", scoreYOffset)
            .attr("dy", ".35em")
            .attr("class", "d3-score-text");

        donutPercent = donutSvg.append("text")
            .attr("text-anchor", "middle")
            .attr("x", percentXOffset)
            .attr("y", percentYOffset)
            .attr("dy", ".35em")
            .attr("class", "d3-score-percent")
            .text('%');
    } 
    if (selectionId !== null) {
        selection.attr("id",selectionId);
    } else if (selectionClass !== null && selectionReplacementClass !== null) {
        selection.classed(selectionClass, false);
        selection.classed(selectionReplacementClass, true);        
    }


    interval = setInterval(function () {
        endAngle = (twoPi  * donutProgressScore) / 100;
        if (addText) {
            donutText.text(donutProgressScore);
        }
        foreground.attr("d", arc.endAngle(endAngle));
        if (donutProgressScore == score) {
            if (addText && score == 100) {
                donutText.attr("x", maxScoreXOffset);
                donutPercent.attr("x", maxScorePercentXOffset);
            } else if (addText && score < 10) {
                donutPercent.attr("x", minScorePercentXOffset);
            }
            clearInterval(interval);
        }
        donutProgressScore ++;
    }, 
    1 );

}