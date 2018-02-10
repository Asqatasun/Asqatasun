$(document).ready(function() {

    var data = [],
        thematics = [],
        margin, 
        width, 
        height, 
        x0, 
        x, 
        y, 
        xAxis, 
        yAxis, 
        bars,
        svg;
        
    $("#top5-failed-theme tr:nth-child(1)").find("th").each(function(){
            thematics.push($(this).text());
        }
    );
    $("#top5-failed-theme tr:nth-child(2)").find("td").each(function() {
            data.push(parseInt($(this).text(), 10));
        }
    );
    $("#top5-failed-theme").hide();
    margin = {
        top: 30, 
        right: 10, 
        bottom: 10, 
        left: 250
    };
    width = 900 - margin.left - margin.right;
    height = 300 - margin.top - margin.bottom;

    x0 = Math.max(-d3.min(data), d3.max(data));

    x = d3.scale.linear()
        .domain([0, x0])
        .range([0, width])
        .nice();

    y = d3.scale.ordinal()
        .rangeRoundBands([0, height], 0.3)
        .domain(d3.range(thematics.length));

    xAxis = d3.svg.axis()
        .scale(x)
        .tickSize(0,0)
        .orient("top");
        
    yAxis = d3.svg.axis()
        .scale(y)
        .tickSize(0,0)
        .ticks(thematics.length)
        .tickPadding(10)
        .tickFormat(function (d,i) {
            return thematics[i];
        })
        .orient("left");

    svg = d3.select("#top5-failed-theme-graph").append("svg")
        .attr("width", width + margin.left + margin.right)
        .attr("height", height + margin.top + margin.bottom)
        .append("g")
        .attr("transform", "translate(" + margin.left + "," + margin.top + ")");

    bars = svg.selectAll(".bar")
        .data(data)
        .enter().append("rect")
        .attr("fill", "#ED4D60")
        .attr("class", function(d) {
            return d < 0 ? "bar negative" : "bar positive";
        })
        .attr("x", function(d) {
            return x(Math.min(0, d));
        })
        .attr("y", function(d, i) {
            return y(i);
        })
        .attr("width", function(d) {
            return Math.abs(x(d) - x(0));
        })
        .attr("height", y.rangeBand());

    bars.append("title").text(function(d, i) {
            return thematics[i] + " : " + d;
    });
    svg.append("g")
        .attr("class", "repartition-chart-x-axis")
        .call(xAxis);
        
    svg.append("g")
        .attr("class", "repartition-chart-y-axis")
        .call(yAxis);

});