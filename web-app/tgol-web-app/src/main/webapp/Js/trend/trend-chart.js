$(document).ready(function() {
    // Grab the data
    var data = [],
        margin,
        width, 
        height,
        x, 
        y, 
        xAxis, 
        yAxis, 
        area, 
        line, 
        dots,
        svg;

    $("#act-list-table tbody tr").each(function (d, i) {
        if (!isNaN(parseInt($(this).find("[headers=raw-mark]").text().trim(), 10)) && 
            ($(this).find("[headers=scope]").text().trim() === "Site" || 
                $(this).find("[headers=scope]").text().trim() === "Scenario" || 
                $(this).find("[headers=scope]").text().trim() === "Scénario") ) {
            var value = parseInt($(this).find("[headers=raw-mark]").text(), 10)/100,
                myResult = {};
            myResult.label=$(this).find("[headers=date]").text().trim();
            myResult.value=value;
            myResult.ref=$(this).find("[headers=referential]").text().trim();
            data.push(myResult);
        }
    });
    data.reverse(); 
    // if not enough data to display show image sample and return 
    if (data.length <2) {
//        $("#site-audit-history-graph-sample").show();
        return;
    }
    margin = {top: 20, right: 30, bottom: 100, left: 50};
    width = 950 - margin.left - margin.right;
    height = 250 - margin.top - margin.bottom;

    x = d3.scale.linear()
        .range([0, width])
        .domain([0,data.length-1]);

    y = d3.scale.linear()
        .range([height, 0])
        .domain([0,1]);

    xAxis = d3.svg.axis()
        .scale(x)
        .ticks(data.length-1)
        .tickSize(-height)
        .tickFormat(function (d,i) {
            return data[i].label;
        })
        .orient("bottom");

    yAxis = d3.svg.axis()
        .scale(y)
        .tickFormat(d3.format("%"))
        .ticks(4,0)
        .tickSize(-width)
        .tickPadding(10)
        .orient("left");

    area = d3.svg.area()
        .x(function(d,i) {
            return x(i);
        })
        .y0(height)
        .y1(function(d) {return y(d.value);});
        
    line = d3.svg.line()
        .x(function(d,i) {
            return x(i);
        })
        .y(function(d) { return y(d.value); });
    
    svg = d3.select("#holder-site-audit-history-graph").append("svg")
        .attr("width", width + margin.left + margin.right)
        .attr("height", height + margin.top + margin.bottom)
        .append("g")
        .attr("transform", "translate(" + margin.left + "," + margin.top + ")");

    svg.append("g").append("path")
        .datum(data)
        .attr("class", "trend-area")
        .attr("d", area);

    svg.append("g")
        .attr("class", "trend-x-axis")
        .attr("transform", "translate(0," + height + ")")
        .style("text-anchor", "end")
        .call(xAxis);

    svg.append("g")
        .attr("class", "trend-y-axis")
        .call(yAxis)
        .append("text")
        .attr("transform", "rotate(-90)")
        .attr("y", 6)
        .attr("dy", ".71em")
        .style("text-anchor", "end");
        
    svg.append("g").append("path")
        .datum(data)
        .attr("class", "trend-line")
        .attr("d", line);  

    svg.append("g").attr("class", "trend-origin").append("line")
        .attr("y1", height)
        .attr("y2", height)
        .attr("x1", 0)
        .attr("x2", width + 1);
        
    svg.append("g").attr("class", "trend-origin").append("line")
        .attr("y1", 0)
        .attr("y2", height)
        .attr("x1", 0)
        .attr("x2", 0);

    dots = svg.append("g").selectAll(".dot")
        .data(data)
        .enter().append("circle")
        .attr("class", "trend-dot")
        .attr("r", 4)
        .attr("cx", function(d,i) {
            return x(i);
        })
        .attr("cy", function(d) { return y(d.value); });
        
    dots.append("title").text(function(d) { return "Date : " + d.label +"\r" + "Score : " + d.value*100 + "%"+"\r"+"Ref :" + d.ref;});
    
    d3.selectAll(".trend-x-axis text").attr("transform", "rotate(-50)").attr("y", "0").attr("x", "-3").style("text-anchor", "end");
});