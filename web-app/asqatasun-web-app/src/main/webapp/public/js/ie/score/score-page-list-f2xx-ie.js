$(document).ready(function() {

    var scoreSelector = "markCol";
    $('td.'+scoreSelector).each(function() {
        var scoreElement = $(this).find('div:first-child'), 
            rawScore = scoreElement.text(), 
            score;
        if (rawScore != 'Echec' || rawScore != 'Fail') {
            score = parseInt(rawScore, 10);
            drawScore(
                    d3.select(this), // selection
                    score,  //score
                    14, // width
                    14, // height
                    5, //radius
                    2, // outerRadius
                    5, // translateX
                    8, // translateY
                    null, 
                    scoreSelector, 
                    scoreSelector, 
                    false, 
                    null, 
                    null, 
                    null, 
                    null, 
                    null, 
                    null, 
                    null, 
                    null, 
                    null, 
                    null, 
                    null, 
                    null, 
                    null, 
                    null, 
                    null);
            scoreElement.remove();
            $(this).append(rawScore);
        }
    });
});