# Asqatasun meter

Asqatasun meter is a metric that gives you a performance score. It is computed for each audit.

The formula is:

> Percentage = Passed / ( Passed + Failed )

More precisely, it is:

> Percentage = Sum of Passed / (Sum of Passed + Sum of Failed )

Once the percentage is computed, a grade is determined against the following grid:

Percentage greater than | Grade
----------------------- | -----
99%                     | A
90%                     | B
85%                     | C
75%                     | D
60%                     | E
0%                      | F


