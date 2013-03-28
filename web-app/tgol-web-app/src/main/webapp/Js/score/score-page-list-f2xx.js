$(document).ready(function() {

    var scoreSelector = "markCol";
    $('td.'+scoreSelector).each(function() {
        var scoreElement = d3.select(this).select('div:first-child'), 
            rawScore = scoreElement.text(), 
            score;
        if (rawScore != 'Echec' || rawScore != 'Fail') {
            score = parseInt(rawScore, 10);
            drawScore(
                    d3.select(this), 
                    score, 
                    12, 
                    12, 
                    2, 
                    2, 
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
                    null);
            scoreElement.remove();
            $(this).append(rawScore);
        }
    });

});