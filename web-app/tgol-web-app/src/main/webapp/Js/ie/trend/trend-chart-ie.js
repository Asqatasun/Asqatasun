$(document).ready(function() {
    // Grab the data
    var data = [], 
        margin, 
        width, 
        height, 
        svg, 
        x, 
        y, 
        area, 
        line, 
        label, 
        text,
        dots,
        rule; 

    $("#act-list-table tbody tr").each(function (d, i) {
        
        if (!isNaN(parseInt($(this).find("[headers=raw-mark]").text(), 10)) && 
            $(this).find("[headers=scope]").text() == "site") {
            var value = parseInt($(this).find("[headers=raw-mark]").text(), 10)/100, 
                myResult = {};
            myResult.label=$(this).find("[headers=date]").text();
            myResult.value=value;
            myResult.ref=$(this).find("[headers=referential]").text();
            data.push(myResult);
        }
    });
    data.reverse(); 

    // if not enough data to display show image sample and return 
    if (data.length <2) {
//        $("#site-audit-history-graph-sample").show();
        return;
    }
    margin = {top: 20, right: 40, bottom: 100, left: 70};
    width = 1000 - margin.left - margin.right;
    height = 300 - margin.top - margin.bottom;

    svg = d3.select("#holder-site-audit-history-graph").append("svg")
        .attr("width", width+margin.right)
        .attr("height", height+margin.bottom)
        .append("g")
        .attr("transform", "translate(" + margin.left +"," + margin.top+ ")");

    x = d3.scale.linear()
        .range([0, width - margin.right])
        .domain([0,data.length-1]);

    y = d3.scale.linear()
        .range([height, 0])
        .domain([0,1]);

    area = d3.svg.area()
        .x(function(d,i) {
            return x(i);
        })
        .y0(height)
        .y1(function(d) {
            return y(d.value);
        });

    line = d3.svg.line()
        .x(function(d,i) {
            return x(i);
        })
        .y(function(d) { return y(d.value); });

    svg.append("path")
        .datum(data)
        .attr("class", "ietrend-area")
        .attr("d", area)
        .attr("stroke", "none")
        .attr("fill", "#E8F5FB");

    svg.append("path")
        .datum(data)
        .attr("class", "trend-line")
        .attr("d", line)
        .attr("stroke-width", "2px")
        .attr("stroke", "#2CA4D2")
        .attr("fill", "none");

    dots = svg.selectAll(".dot")
        .data(data)
        .enter().append("circle")
        .attr("class", "ietrend-dot")
        .attr("r", 4)
        .attr("cx", function(d,i) {
            return x(i);
        })
        .attr("cy", function(d) { return y(d.value); })
        .attr("fill", "#E8F5FB")
        .attr("stroke","#2CA4D2")
        .attr("stroke-width", "2px");

    dots.append("title").text(function(d) { return "Date : " + d.label +"\r" + "Score : " + d.value*100 + "%"+"\r"+"Ref :" + d.ref;});
    
    // Add the audit date as abscissa.
    label = svg.selectAll("g.xAxis")
        .data(data)
        .enter()
        .append("g");

    text = label.append("text")
        .attr("x", function(d, i) { 
            return x(i); 
        })
        .attr("y", height)
        .attr("class", "xAxis-label")
        .attr("text-anchor", "end")
        .attr("dy", ".71em")
        .style("opacity", 0.5 )
        .text(function (d, i) {
            return d.label;
        });

    text.attr("transform", function(d, i) { 
        return "rotate(-45, " + (x(i) + 5) + ", "+ (height+5)+ ")"; 
    });

    label.append("line")
        .attr("x1", function(d, i) { 
            return x(i); 
        })
        .attr("x2", function(d, i) { 
            return x(i); 
        })
        .attr("y1", 0)
        .attr("y2", function(d, i) { 
            return height;
        })
        .style("stroke", "#000" )
        .style("stroke-opacity", .1 );

    // Add y-axis rules.
    rule = svg.selectAll("g.yAxis")
        .data(y.ticks(6))
        .enter().append("g")
        .attr("class", "rule")
        .attr("transform", function(d) {
            return "translate(0," + y(d) + ")";
        });

    rule.append("line")
        .attr("x1", 0)
        .attr("x2", width-margin.right)
        .style("stroke", "#000" )
        .style("stroke-opacity", .1 );

    rule.append("text")
        .attr("x", -10)
        .attr("text-anchor", "end")
        .attr("dy", ".71em")
        .style("opacity", .5 )
        .text(function (d, i) {
            return (d*100)+"%";
        });
 });