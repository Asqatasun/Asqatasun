$(document).ready(function() {

    /* CAUTION
       if you ever change the values defining the grades, don't forget to modify also:
       web-app/asqatasun-web-app/src/main/webapp/WEB-INF/view/template/grade.jsp
     */


    $('td.markCol').each(function() {
        var scoreElement = $(this).select('div:first-child'),
            rawScore = scoreElement.text().replace('%','').trim(),
            gradeText,
            grade;
        if (rawScore != 'Echec' || rawScore != 'Fail') {
            if (rawScore == 100) {
                gradeText='A';
                grade='grade-a';
            } else if (rawScore > 90) {
                gradeText='B';
                grade='grade-b';
            } else if (rawScore > 85) {
                gradeText='C';
                grade='grade-c';
            } else if (rawScore > 75) {
                gradeText='D';
                grade='grade-d';
            } else if (rawScore > 60) {
                gradeText='E';
                grade='grade-e';
            } else {
                gradeText='F';
                grade='grade-F';
            }
            scoreElement.empty();
            $(this).addClass('page-list-xxx');
            $(this).append('<span class="grade-container ' + grade+'"\> <span> '+gradeText+'</span>');
        }
    });

});
