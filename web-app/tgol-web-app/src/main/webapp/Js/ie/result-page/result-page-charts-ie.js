$(document).ready(function() {
    
    buildResultByThemeChart();
    buildPieChart();
    buildScoreDonut();        

});

buildResultByThemeChart = function() {
/*------------------------------------------------------------------------------
 ----------------------Repartition by theme chart-------------------------------
 -----------------------------------------------------------------------------*/    
    /* 
     * Data Collection
     */ 
    var columns = [], 
        resultTypes = [], 
        margin, 
        width, 
        height, 
        x, 
        y,
        z,
        svg, 
        causes, 
        label, 
        text, 
        rule, 
        cause;
        
    $('#result-by-theme thead tr').find('th').each(function(d) 
    {
        var thematic = {};
        thematic.shortLabel="Th"+(d+1);
        thematic.label=$(this).text();
        columns.push(thematic);
    });
    
    
    $('#result-by-theme tbody tr').find("th").each(function() 
    {
        resultTypes.push($(this).text());
    }
    );      

    $('#result-by-theme tbody tr').each(function(d) 
    { 	
        var resultTypeRank = d;
        $(this).find('td').each(function(d) {
            var data=parseInt($(this).text(), 10);
            if (resultTypeRank ===0) {
                columns[d].failed=data;
            } else if (resultTypeRank==1) {
                columns[d].passed=data;
            } else if (resultTypeRank==2) {
                columns[d].nmi=data;
            } else if (resultTypeRank==3) {
                columns[d].na=data;
            }
        });
    });

    /**
     * Hide the table, overriden by the chart.
     */
    $('#result-by-theme').hide();

    /* 
     * Creation of the chart
     */ 
    margin = {top: 10, right: 50, bottom: 100, left: 50};
    width = 700 - margin.left - margin.right;
    height = 480 - margin.top - margin.bottom;

    x = d3.scale.ordinal()
        .rangeRoundBands([margin.left, width], 0.3);

    y = d3.scale.linear()
        .range([0, height]);

    z = d3.scale.ordinal()
        .domain([0, 3])
        .range(["#ec4d63", "#CCCCCC", "#a0d261", "#5ac2e7"]);//failed - na - passed - nmi

    svg = d3.select("#result-by-theme-graph").append("svg")
        .attr("width", width+margin.right)
        .attr("height", height+margin.bottom)
        .append("g")
        .attr("transform", "translate(" + margin.left +"," + (height + margin.top)+ ")");

    causes = d3.layout.stack()(["failed", "passed", "nmi", "na"].map(function(resultType) {
        return columns.map(function(d) {
            return {
                x: d.label, 
                y: +d[resultType], 
                type:resultTypes[resultType]
            };
        });
    }));
    
    // Compute the x-domain (by date) and y-domain (by top).
    x.domain(causes[0].map(function(d) { 
        return d.x; 
    }));
    y.domain([0, d3.max(causes[causes.length - 1], function(d) {
        return d.y0 + d.y;
    })]);

    // Add a label per thematic.
    label = svg.selectAll("g.xAxis")
        .data(x.domain())
        .enter().append("g");

    text = label.append("text")
        .attr("x", function(d) { 
            return x(d) + x.rangeBand() / 2; 
        })
        .attr("y", 8)
        .attr("text-anchor", "end")
        .attr("dy", ".71em")
        .attr("class", "xAxis-label")
        .style("opacity", 0.5 )
        .text(function (d) {
            return d;
        });

    text.attr("transform", function(d) { 
            return "rotate(-35, " + (x(d) + x.rangeBand() / 2) + ", 8)"; 
    });

    label.append("line")
        .attr("x1", function(d) { 
            return x(d) + x.rangeBand() / 2; 
        })
        .attr("x2", function(d) { 
            return x(d) + x.rangeBand() / 2; 
        })
        .attr("y1", 0)
        .attr("y2", function(d, i) { 
            if (i%2 === 0) {
                return "0"; 
            } else {
                return -height-margin.top;
            }
        })
        .style("stroke", "#000" )
        .style("stroke-opacity", 0.2 );

    // Add y-axis rules.
    rule = svg.selectAll("g.yAxis")
        .data(y.ticks(6))
        .enter().append("g")
        .attr("class", "rule")
        .attr("transform", function(d) {
            return "translate(0," + -y(d) + ")";
        });

    rule.append("line")
        .attr("x1", margin.left-10)
        .attr("x2", width+margin.right)
        .style("stroke", "#000" )
        .style("stroke-opacity", 0.2 );
        
    rule.append("circle")
        .attr("r", 5)
        .attr("cy", 0)
        .attr("cx", margin.left-10)
        .style("stroke", "#000" )
        .style("fill", "#fff" )
        .style("stroke-opacity", 0.2 );

    rule.append("text")
        .attr("x", margin.left - 25)
        .attr("dy", ".35em")
        .style("opacity", 0.5 )
        .text(d3.format(",d"));

    // Add a group for each cause.
    cause = svg.selectAll("g.cause")
        .data(causes)
        .enter().append("g")
        .attr("class", "cause")
        .style("fill", function(d, i) {
            return z(i);
        })
        .style("stroke", "none");

    // Add a rect for each date.
    cause.selectAll("rect")
        .data(Object)
        .enter().append("rect")
        .attr("x", function(d) {
            return x(d.x);
        })
        .attr("y", function(d) {
            return -y(d.y0) - y(d.y);
        })
        .attr("height", function(d) {
            return y(d.y);
        })
        .attr("width", x.rangeBand())
        .attr("stroke", "none")
        .attr("stroke-width", "0")
        .attr("title", function(d, i, z) {
            return columns[i].label+ " : " + d.y + " " +resultTypes[z];
        }); 
        
    label.append("circle")
        .attr("r", 5)
        .attr("cx", function(d) { 
            return x(d) + x.rangeBand() / 2; 
        })
        .attr("cy", -1)
        .style("stroke", "#000" )
        .style("fill", "#fff" )
        .style("stroke-opacity", 0.2 );
    
};

buildPieChart = function() {
    /*------------------------------------------------------------------------------
 ----------------------Pie chart------------------------------------------------
 -----------------------------------------------------------------------------*/       
    var pieData = [], 
        pieMargins, 
        pieWidth, 
        pieHeight, 
        radius, 
        pieColor, 
        arc, 
        pie, 
        pieSvg;

    $('#result-synthetized-text').find(".col02").each(function(d, i) 
    {
        if (! $(this).prev('td').hasClass('nt')) {
            var result = {};
            result.label=$(this).text();
            result.value=Math.abs(parseInt($(this).siblings('td').text(), 10));
            pieData.push(result);
        }
    }
    );

    pieMargins = {top: 5, right: 0, bottom: 0, left: 15}; // margins of the Raphael paper
    pieWidth = 250 - pieMargins.left - pieMargins.right; // inner width of the paper (less margins)
    pieHeight = 175 - pieMargins.top - pieMargins.bottom; // inner height of the paper (less margins) 
    radius = Math.min(pieWidth, pieHeight) / 2;
    
    pieColor = d3.scale.ordinal()
       .range(["#a0d261", "#ec4d63", "#CCCCCC", "#5ac2e7"]);
     
    arc = d3.svg.arc()
        .outerRadius(radius - 10)
        .innerRadius(0);
    
    pie = d3.layout.pie()
        .sort(null)
        .value(function(d) {
            return d.value;
        });
    
    pieSvg = d3.select("#result-repartition-pie-graph").append("svg")
        .attr("width", pieWidth+pieMargins.right)
        .attr("height", pieHeight+pieMargins.bottom)
        .append("g")
        .attr("transform", "translate(" + (pieWidth/2 +pieMargins.left*2) +"," + (pieHeight/2)+ ")");
    
    pieSvg.selectAll(".arc")
        .data(pie(pieData))
        .enter().append("path")
        .attr("class", "arc")
        .attr("d", arc)
        .attr("fill", function(d, i) {
            return pieColor(i);
        })
        .attr("stroke-width", "0")
        .attr("stroke", function(d) {
            return pieColor(d.value);
        })
        .attr("title", function(d) { 
            return d.data.label + " : " + d.value;
        });        

};

buildScoreDonut = function() {
/*------------------------------------------------------------------------------       
 ----------------------Score donut----------------------------------------------
 -----------------------------------------------------------------------------*/
    var scoreSelector = "audit-score", 
        projectScoreElement = $('#'+scoreSelector), 
        scoreElement, 
        rawScore, 
        score;
    if (projectScoreElement.length>0) {
        scoreElement = projectScoreElement.find('div:first-child');
        rawScore = scoreElement.text();
        if (rawScore != 'Echec' || rawScore != 'Fail') {
            score = parseInt(rawScore, 10);
            if (score==0) {
                score=1;
            }
            drawScore(
                      d3.select(projectScoreElement[0]), // selection
                      score,  //score
                      160, // width
                      160, // height
                      75, //radius
                      17, // outerRadius
                      80, // translateX
                      80, // translateY
                      "d3-ie-"+scoreSelector, //Id of div parent
                      null, 
                      null, 
                      true, // addText
                      "OpenSansLight",
                      "13px",
                      0, // tanaguruMeterXOffset
                      -30, // tanaguruMeterXOffset
                      "OpenSansSemibold",
                      "50px",
                      0, // scoreXOffset
                      10, // scoreYOffset
                      "OpenSansRegular",
                      "21px",
                      38, // percentXOffset
                      18, // percentYOffset 
                      -6, // maxScoreXOffset
                      45, // maxScorePercentXOffset
                      22); 
            scoreElement.remove();
        }
    }    
};