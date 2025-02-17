/**
 * @id java/examples/toomanyparams
 * @kind problem
 * @problem.severity recommendation
 * @name Methods with many parameters
 * @description Finds methods with more than ten parameters
 * @tags method
 *       parameter
 *       argument
 */

import java

from Method m
where m.getNumberOfParameters() > 10
select m, "Method with more than ten parameters"
