/**
 * @id java/examples/unusedparam
 * @kind problem
 * @problem.severity warning
 * @name Unused parameter
 * @description Finds parameters that are not accessed
 * @tags parameter
 *       access
 */

import java

from Parameter p
where not exists(p.getAnAccess())
select p, "Parameter is not used"
