/**
 * @id java/examples/eq-true
 * @kind problem
 * @problem.severity warning
 * @name Equality test on boolean
 * @description Finds tests like `==true`, `==false`, `!=true`, `!=false`
 * @tags equals
 *       test
 *       boolean
 */

import java

from EqualityTest eq, BooleanLiteral bl
where eq.getAnOperand() instanceof BooleanLiteral and eq.getAnOperand() = bl
select eq, "Found equality test on boolean"
