$(document).ready(function() {
    buildResultByThemeChart();
    buildPieChart();
    buildScoreDonut();
});

buildResultByThemeChart = function() {
/*------------------------------------------------------------------------------
 ----------------------Repartition by theme chart-------------------------------
 -----------------------------------------------------------------------------*/

    var columns = [],
        results = [],
        data = [],
        n, // number of layers
        stack,
        layers,
        yStackMax, 
        margin, 
        width,
        height, 
        x,
        y, 
        color, 
        xAxis, 
        yAxis, 
        xAxisBullet,
        yAxisBullet, 
        svg,
        layer, 
        rect;

    d3.selectAll("#result-by-theme thead tr").selectAll("th").each(function(d, i) 
    {
        var thematic = {};
        thematic.label=d3.select(this).text();
        thematic.shortLabel="Th"+(i+1);
        columns.push(thematic);
    }
    );
    
    d3.selectAll("#result-by-theme tbody tr").selectAll("th").each(function() 
    {
        results.push(d3.select(this).text());
    }
    );
    
    d3.selectAll("#result-by-theme tbody tr").each(function(d,i) 
    { 
        var myData = {}, 
            resultData=[],
            resultTypeRank = i;

        myData.name=results[i];
        d3.select(this).selectAll("td").each(function(d,i) {
            var value = parseInt(d3.select(this).text(), 10),
                myResult = {};
            myResult.x=columns[i].label;
            myResult.y=value;
            myResult.themeRank=i+1;
            myResult.resultTypeRank=resultTypeRank;
            resultData.push(myResult);
        });
        myData.values=resultData;
        data.push(myData);

    });
 
    n = results.length; // number of layers
    stack = d3.layout.stack();
    layers = stack(d3.range(n).map(
        function(i) { 
            var myData=data[i].values;
            return myData; 
        })
    );
    yStackMax = d3.max(layers, function(layer) {
        return d3.max(layer, function(d) {
            return d.y0 + d.y;
        });
    });

    margin = {
        top: 30, 
        right: 70, 
        bottom: 100, 
        left: 50
    };
    width = 700 - margin.left - margin.right;
    height = 480 - margin.top - margin.bottom;

    x = d3.scale.ordinal()
    .domain(columns.map(function(d) { 
        return d.label;
    }))
    .rangeRoundBands([0, width], 0.3);

    y = d3.scale.linear()
    .domain([0, yStackMax])
    .range([height, 0]);

    color = d3.scale.ordinal()
        .domain([0, n - 1])
        .range(["#ec4d63", "#CCCCCC", "#a0d261", "#5ac2e7"]);//failed - na - passed - nmi

    xAxis = buildAxis()
        .scale(x)
        .gridSize(-height-15)
        .tickSize(6,0)
        .orient("bottom");
    
    yAxis = buildAxis()
        .scale(y)
        .tickSize(6,0)
        .ticks(6)
        .gridSize(width)
        .tickPadding(10)
        .tickSubdivide(4)
        .tickFormat(d3.format(",.0f"))
        .orient("left");    
    
    yAxisBullet = buildBullet()
        .scale(y)
        .ticks(6)
        .orient("left");
    
    xAxisBullet = buildBullet()
        .scale(x)
        .orient("bottom");
    
    svg = d3.select("#result-by-theme-graph").append("svg")
        .attr("width", width + margin.left + margin.right)
        .attr("height", height + margin.top + margin.bottom)
        .append("g")    
        .attr("transform", "translate(" + margin.left + "," + margin.top + ")");
    
    svg.append("g")
        .attr("class", "x axis")
        .attr("transform", "translate(0," + height + ")")
        .call(xAxis);
    
    svg.append("g")
        .attr("class", "y axis")
        .call(yAxis)
        .append("text")
        .attr("transform", "rotate(-90)")
        .attr("y", 6)
        .attr("dy", ".71em");

    layer = svg.selectAll(".layer")
        .data(layers)
        .enter().append("g")
        .attr("class", "layer")
        .style("fill", function(d, i) {
            return color(i);
        })
        .style("fill-opacity", 1.0);

    rect = layer.selectAll("rect")
        .data(function(d) {
            return d;
        })
        .enter().append("rect")
        
        .attr("x", function(d) {
            return x(d.x);
        })
        .attr("y", height)
        .attr("width", x.rangeBand())
        .attr("height", 0);

    rect.append("title").text(function(d) { 
            return columns[d.themeRank-1].label + " : " + d.y + " " + results[d.resultTypeRank];
        });

    svg.append("g")
        .attr("class", "y bullet")
        .call(yAxisBullet)

    svg.append("g")
        .attr("class", "x bullet")
        .attr("transform", "translate(0," + height + ")")
        .call(xAxisBullet);

    rect.transition()
    .delay(function(d, i) {
        return 500 ;
    })
    .attr("y", function(d) {
        return y(d.y0 + d.y);
    })
    .attr("height", function(d) {
        return y(d.y0) - y(d.y0 + d.y);
    });

    rect.on("click", function(d) {
  	location.href = location.href + '#theme'+d.themeRank;
        var index=location.href.indexOf("#theme");
        if (index != -1) {
            location.href = location.href.substr(0, index) + '#theme'+d.themeRank;
        } else {
            location.href = location.href + '#theme'+d.themeRank;
        }
    })
    $('#result-by-theme').hide();    
};

buildPieChart = function() {
/*------------------------------------------------------------------------------
 ----------------------Pie chart------------------------------------------------
 -----------------------------------------------------------------------------*/    
    var pieData = [], 
        pieMargin,
        pieWidth,
        pieHeight,
        pieRadius,
        pieColor,
        pieArc,
        pie,
        pieSvg,
        pieG;
    
        
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

    pieMargin = {top: 5, right: 0, bottom: 0, left: 15};
    pieWidth = 250;
    pieHeight = 175;
    pieRadius = Math.min(pieWidth, pieHeight) / 2.2;

    pieColor = d3.scale.ordinal()
        .range(["#a0d261", "#ec4d63", "#CCCCCC", "#5ac2e7"]);

    pieArc = d3.svg.arc()
        .outerRadius(pieRadius - 10)
        .innerRadius(0);

    pie = d3.layout.pie()
        .sort(null)
        .value(function(d) {
            return d.value;
        });

    pieSvg = d3.select("#result-repartition-pie-graph").append("svg")
        .attr("width", pieWidth)
        .attr("height", pieHeight)
        .append("g")
        .attr("transform", "translate(" + ((pieWidth / 2) + pieMargin.left) + "," +(( pieHeight / 2) + pieMargin.top) + ")");

    pieG = pieSvg.selectAll(".arc")
        .data(pie(pieData))
        .enter().append("g")
        .attr("class", "arc");

    pieG.append("path")
        .attr("d", pieArc)
        .style("fill", function(d, i) {
            return pieColor(i);
        })
        .style("fill-opacity", 1);    
        
    pieG.append("title").text(function(d) { 
            return d.data.label + " : " + d.value;
        });
};

buildScoreDonut = function() {
    /*------------------------------------------------------------------------       
    ----------------------Score donut-----------------------------------------
    --------------------------------------------------------------------------*/    
    var scoreSelector = "audit-score",
        projectScoreElement = d3.select('#'+scoreSelector), 
        projectRawScore, 
        score;
    if (projectScoreElement != null) {
        projectRawScore = projectScoreElement.select('div:first-child').text();
        if (projectRawScore != 'Echec' || projectRawScore != 'Fail') {
            score = parseInt(projectRawScore, 10);
            drawScore(
                        projectScoreElement, 
                        score, 
                        160, // width
                        160,  // height
                        2.1, //divider
                        17,  // radius
                        "d3-"+scoreSelector, //Id of div parent
                        null, 
                        null, 
                        true, // addText
                        -44, // tanaguruMeterXOffset
                        -28, // tanaguruMeterXOffset
                        -1, // scoreXOffset
                        5, // scoreYOffset
                        36, // percentXOffset
                        15, // percentYOffset 
                        -5, // maxScoreXOffset
                        45, // maxScorePercentXOffset
                        22); 
            projectScoreElement.select('div').remove();
        }
    }    
};

buildAxis = function() {
    var scale = d3.scale.linear(), 
        orient = "bottom", 
        tickMajorSize = 6, 
        gridSize = 6, 
        tickMinorSize = 4, 
        tickEndSize = 0, 
        tickPadding = 3, 
        tickArguments_ = [ 10 ], 
        tickValues = null, 
        tickFormat_;
    function axis(g) {
      g.each(function() {
        var g = d3.select(this);
        
        var ticks = tickValues == null ? 
                scale.ticks ? 
                    scale.ticks.apply(scale, tickArguments_) : scale.domain() : tickValues, tickFormat = tickFormat_ == null ? 
                        scale.tickFormat ? scale.tickFormat.apply(scale, tickArguments_) : String : tickFormat_;
        
        var subticks = svg_axisSubdivide(scale, ticks, tickSubdivide), 
            subtick = g.selectAll(".minor").data(subticks, String), 
            subtickEnter = subtick.enter().insert("line", "g").attr("class", "minor"), 
            subtickExit = d3.transition(subtick.exit()).style("opacity", 1e-6).remove(),
            subtickUpdate = d3.transition(subtick);
            
        var tick = g.selectAll("g").data(ticks, String), 
            tickEnter = tick.enter().insert("g", "path").style("opacity", 1e-6), 
            tickExit = d3.transition(tick.exit()).style("opacity", 1e-6).remove(), 
            tickUpdate = d3.transition(tick).style("opacity", 0.5), tickTransform;
            
        var range = scaleRange(scale), 
            path = g.selectAll(".domain").data([ 0 ]), 
            pathUpdate = d3.transition(path);

        var scale1 = scale.copy(), 
            scale0 = this.__chart__ || scale1;
            this.__chart__ = scale1;
        
        tickEnter.append("line").attr("class", "tick").attr("y2", "0");
        tickEnter.append("text");
        
        var lineEnter = tickEnter.select("line"), 
            lineUpdate = tickUpdate.select("line"), 
            text = tick.select("text").text(tickFormat), 
            textEnter = tickEnter.select("text"), 
            textUpdate = tickUpdate.select("text");
        switch (orient) {
         case "bottom":
          {
            tickTransform = svg_axisX;
            subtickEnter.attr("y2", tickMinorSize);
            subtickUpdate.attr("x2", 0).attr("y2", tickMinorSize);
            lineEnter.attr("y2", gridSize);
            textEnter.attr("y", Math.max(tickMajorSize, 0) + tickPadding);
            lineUpdate.attr("x2", 0).attr("y2", gridSize);
            textUpdate.attr("x", -10).attr("y", 0);
            text.attr("dy", ".3em").style("text-anchor", "end").attr("class", "xAxis-label").attr("transform", "rotate(-35)");
            pathUpdate.attr("d", "M" + range[0] + "," + tickEndSize + "V0H" + range[1] + "V" + tickEndSize);
            break;
          }
         case "left":
          {
            tickTransform = svg_axisY;
            subtickEnter.attr("x2", -tickMinorSize);
            subtickUpdate.attr("x2", -tickMinorSize).attr("y2", 0);
            lineEnter.attr("x2", gridSize);
            textEnter.attr("x", -(Math.max(tickMajorSize, 0) + tickPadding));
            lineUpdate.attr("x2", gridSize).attr("y2", 0);
            textUpdate.attr("x", -(Math.max(tickMajorSize, 0) + tickPadding)).attr("y", 0);
            text.attr("dy", ".32em").style("text-anchor", "end");
/*            pathUpdate.attr("d", "M" + -tickEndSize + "," + range[0] + "H0V" + range[1] + "H" + -tickEndSize);*/
            break;
          }

        }
        if (scale.ticks) {
          tickEnter.call(tickTransform, scale0);
          tickUpdate.call(tickTransform, scale1);
          tickExit.call(tickTransform, scale1);
          subtickEnter.call(tickTransform, scale0);
          subtickUpdate.call(tickTransform, scale1);
          subtickExit.call(tickTransform, scale1);
        } else {
          var dx = scale1.rangeBand() / 2, x = function(d) {
            return scale1(d) + dx;
          };
          tickEnter.call(tickTransform, x);
          tickUpdate.call(tickTransform, x);
        }
      });
    }
    axis.scale = function(x) {
      if (!arguments.length) return scale;
      scale = x;
      return axis;
    };
    axis.orient = function(x) {
      if (!arguments.length) return orient;
      orient = x;
      return axis;
    };
    axis.gridSize = function(x) {
      if (!arguments.length) return gridSize;
      gridSize = x;
      return axis;
    };
    axis.ticks = function() {
      if (!arguments.length) return tickArguments_;
      tickArguments_ = arguments;
      return axis;
    };
    axis.tickValues = function(x) {
      if (!arguments.length) return tickValues;
      tickValues = x;
      return axis;
    };
    axis.tickFormat = function(x) {
      if (!arguments.length) return tickFormat_;
      tickFormat_ = x;
      return axis;
    };
    axis.tickSize = function(x, y) {
      if (!arguments.length) return tickMajorSize;
      var n = arguments.length - 1;
      tickMajorSize = +x;
      tickMinorSize = n > 1 ? +y : tickMajorSize;
      tickEndSize = n > 0 ? +arguments[n] : tickMajorSize;
      return axis;
    };
    axis.tickPadding = function(x) {
      if (!arguments.length) return tickPadding;
      tickPadding = +x;
      return axis;
    };
    axis.tickSubdivide = function(x) {
      if (!arguments.length) return tickSubdivide;
      tickSubdivide = +x;
      return axis;
    };
    return axis;
  };
  
  function svg_axisX(selection, x) {
    selection.attr("transform", function(d) {
      return "translate(" + x(d) + ",0)";
    });
    selection.each(function(d, i) {
      if (i%2 == 0) {
          d3.select(this).select("line").attr("class","no-draw tick");
      }
    });
  }
  
  function svg_axisX_bullet(selection, x) {
    selection.attr("transform", function(d) {
      return "translate(" + x(d) + ",0)";
    });
  }
  
  function svg_axisY(selection, y) {
    selection.attr("transform", function(d) {
      return "translate(0," + y(d) + ")";
    });
  }
  
  function svg_axisSubdivide(scale, ticks, m) {
    subticks = [];
    if (m && ticks.length > 1) {
      var extent = scaleExtent(scale.domain()), subticks, i = -1, n = ticks.length, d = (ticks[1] - ticks[0]) / ++m, j, v;
      while (++i < n) {
        for (j = m; --j > 0; ) {
          if ((v = +ticks[i] - j * d) >= extent[0]) {
            subticks.push(v);
          }
        }
      }
      for (--i, j = 0; ++j < m && (v = +ticks[i] + j * d) < extent[1]; ) {
        subticks.push(v);
      }
    }
    return subticks;
  }
  function scaleExtent(domain) {
    var start = domain[0], stop = domain[domain.length - 1];
    return start < stop ? [ start, stop ] : [ stop, start ];
  }
  function scaleRange(scale) {
    return scale.rangeExtent ? scale.rangeExtent() : scaleExtent(scale.range());
  }

  buildBullet = function() {
    var scale = d3.scale.linear(), 
        orient = "bottom", 
        tickArguments_ = [ 10 ], 
        tickValues = null;
    function axis(g) {
      g.each(function() {
        var g = d3.select(this);
        
        var ticks = tickValues == null ? 
                scale.ticks ? 
                    scale.ticks.apply(scale, tickArguments_) : scale.domain() : tickValues;

        var tick = g.selectAll("g").data(ticks, String), 
            tickOrigin = tick.enter().insert("g", "path"), 
            tickUpdate = d3.transition(tick), tickTransform;

        var scale1 = scale.copy();
        this.__chart__ = scale1;
        
        tickOrigin.append("circle").attr("class", "tick").attr("cy", "0").attr("r", "5");
        
        var tickOriginEnter = tickOrigin.select("circle");

        switch (orient) {
         case "bottom":
          {
            tickTransform = svg_axisX_bullet;
            tickOriginEnter.attr("cx", 15);
            break;
          }

         case "left":
          {
            tickTransform = svg_axisY;
            tickOriginEnter.attr("cx", -3);
            break;
          }
        }
        tickUpdate.call(tickTransform, scale1);
      });
    }
    
    axis.scale = function(x) {
      if (!arguments.length) return scale;
      scale = x;
      return axis;
    };
    axis.orient = function(x) {
      if (!arguments.length) return orient;
      orient = x;
      return axis;
    };
    axis.ticks = function() {
      if (!arguments.length) return tickArguments_;
      tickArguments_ = arguments;
      return axis;
    };
    return axis;
  };